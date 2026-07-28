package com.synergy.vintagetech.init.builder.centrifuge;

import java.util.Optional;

import com.devdyna.cakesticklib.api.RandomUtil;
import com.devdyna.cakesticklib.api.aspect.logic.DropCollector;
import com.devdyna.cakesticklib.api.aspect.logic.ItemStorageBlock;
import com.devdyna.cakesticklib.api.aspect.logic.NoGuiStorage;
import com.devdyna.cakesticklib.api.aspect.logic.SimpleFluidStorage;
import com.devdyna.cakesticklib.api.primitive.Ticker;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.LibHandlers;
import com.synergy.vintagetech.api.blockfactory.transmission.TransmissionBE;
import com.synergy.vintagetech.api.recipeinput.FluidAndItemInput;
import com.synergy.vintagetech.init.builder.centrifuge.recipe.CentrifugeRecipe;
import com.synergy.vintagetech.init.types.zBlockEntities;
import com.synergy.vintagetech.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class CentrifugeBE extends TransmissionBE
        implements ItemStorageBlock, NoGuiStorage, SimpleFluidStorage, DropCollector {

    public static final int ITEM_INPUT = 0;
    public static final int ITEM_OUTPUT = 1;

    public static final int FLUID_TANK = 0;

    public CentrifugeBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.CENTRIFUGE.get(), pos, state);
    }

    @Override
    public ItemStack insertItem(ItemStack i) {
        return simpleInsertItem(i);
    }

    @Override
    public ItemStack extractItem() {

        var extracted = simpleExtractItemByIndex(ITEM_OUTPUT);

        if (extracted.isEmpty())
            extracted = simpleExtractItemByIndex(ITEM_INPUT);

        return extracted;
    }

    private Ticker ticker = null;

    @Override
    public void tickServer() {

        if (getFluidStorage() == null)
            return;

        var fluid = x.fluid(getFluidStorage().getResource(FLUID_TANK).getFluid(),
                getFluidStorage().getAmountAsInt(FLUID_TANK));

        if (fluid.isEmpty())
            return;

        if (getItemStorage() == null)
            return;

        var item = getStackInSlot(ITEM_INPUT);

        if (item.isEmpty())
            return;

        Optional<RecipeHolder<CentrifugeRecipe>> r = level.getServer().getRecipeManager()
                .getRecipeFor(zRecipeTypes.CENTRIFUGE.getType(),
                        new FluidAndItemInput(fluid, item), level);

        if (r.isEmpty())
            return;

        var recipe = r.get().value();

        if (recipe.getInputFluid().amount() > fluid.amount())
            return;

        if (recipe.getItemInput().count() > item.count())
            return;

        int recipeMultiplier = Math.min(item.count() / recipe.getItemInput().count(),
                fluid.amount() / recipe.getInputFluid().amount());

        if (item.count() < recipe.getItemInput().count() * recipeMultiplier)
            return;

        if (fluid.amount() % recipe.getInputFluid().amount() != 0)
            return;

        if (recipe.getOutputFluid() != null)
                if (recipe.getOutputFluid().amount() * recipeMultiplier > getTankCapacity())
                    return;

        if (recipe.getOutputItem() != null)
            if (!getStackInSlot(ITEM_OUTPUT).isEmpty())
                if (getItemStorage().getResource(ITEM_OUTPUT).is(recipe.getOutputItem().item().item()))
                        if((recipe.getOutputItem().item().count() * recipeMultiplier)
                                + getItemStorage().getAmountAsInt(ITEM_OUTPUT) > getItemStorage().getCapacityAsInt(
                                        ITEM_OUTPUT,
                                        getItemStorage().getResource(ITEM_OUTPUT)))
                    return;

        if (ticker == null)
            ticker = Ticker.of(recipe.getTicks() * recipeMultiplier);

        if (ticker.commit()) {
            try (var tx = Transaction.openRoot()) {

                getFluidStorage().extract(FLUID_TANK, FluidResource.of(fluid),
                        recipe.getInputFluid().amount() * recipeMultiplier, tx);

                if (recipe.getOutputFluid() != null)
                    getFluidStorage().insert(FLUID_TANK, FluidResource.of(recipe.getOutputFluid()),
                            recipe.getOutputFluid().amount() * recipeMultiplier, tx);

                getItemStorage().extract(ITEM_INPUT, ItemResource.of(item),
                        recipe.getItemInput().count() * recipeMultiplier, tx);

                if (recipe.getOutputItem() != null)
                    for (int i = 0; i < recipeMultiplier; i++)
                        if (RandomUtil.chance(level, recipe.getOutputItem().chance()))
                            getItemStorage().insert(ITEM_OUTPUT, ItemResource.of(recipe.getOutputItem().item()),
                                    recipe.getOutputItem().item().count(), tx);

                tx.commit();
            }

            ticker = null;
        }

    }

    @Override
    public ItemStacksResourceHandler getItemStorage() {
        return getData(LibHandlers.ITEM_STORAGE);
    }

    @Override
    public int getSlots() {
        return 2;
    }

    @Override
    public FluidStacksResourceHandler getFluidStorage() {
        return getData(LibHandlers.FLUID_STORAGE);
    }

    @Override
    public int getTankCapacity() {
        return 1000;
    }

    @Override
    public int getTanks() {
        return 1;
    }

}
