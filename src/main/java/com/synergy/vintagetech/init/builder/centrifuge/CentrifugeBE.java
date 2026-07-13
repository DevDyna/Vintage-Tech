package com.synergy.vintagetech.init.builder.centrifuge;

import java.util.Optional;

import com.devdyna.cakesticklib.api.aspect.logic.DropCollector;
import com.devdyna.cakesticklib.api.aspect.logic.ItemStorageBlock;
import com.devdyna.cakesticklib.api.aspect.logic.NoGuiStorage;
import com.devdyna.cakesticklib.api.aspect.logic.SimpleFluidStorage;
import com.devdyna.cakesticklib.api.primitive.Ticker;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.LibHandlers;
import com.synergy.vintagetech.api.blockfactory.transmission.TransmissionBE;
import com.synergy.vintagetech.api.recipeinput.CentrifugeInput;
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

    public static final int ITEM_CATALYST = 0;

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
        return simpleExtractItem();
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

        var item = getStackInSlot(ITEM_CATALYST);

        if (item.isEmpty())
            return;

        Optional<RecipeHolder<CentrifugeRecipe>> r = level.getServer().getRecipeManager()
                .getRecipeFor(zRecipeTypes.CENTRIFUGE.getType(),
                        new CentrifugeInput(fluid, item), level);

        if (r.isEmpty())
            return;

        var recipe = r.get().value();

        if (recipe.getInputFluid().amount() > fluid.amount())
            return;

        int recipeMultiplier = fluid.amount() / recipe.getInputFluid().amount();

        if (item.count() < recipe.getCatalyst().count() * recipeMultiplier)
            return;

        if (fluid.amount() % recipe.getInputFluid().amount() != 0)
            return;

        if (recipe.getOutputFluid().amount() * recipeMultiplier > getTankCapacity())
            return;

        if (ticker == null)
            ticker = Ticker.of(recipe.getTicks() * recipeMultiplier);

        if (ticker.commit()) {
            try (var tx = Transaction.openRoot()) {

                getFluidStorage().extract(FLUID_TANK, FluidResource.of(fluid), fluid.amount(), tx);

                getFluidStorage().insert(FLUID_TANK, FluidResource.of(recipe.getOutputFluid()),
                        recipe.getOutputFluid().amount() * recipeMultiplier, tx);

                getItemStorage().extract(ITEM_CATALYST, ItemResource.of(item),
                        recipe.getCatalyst().count() * recipeMultiplier, tx);
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
        return 1;
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
