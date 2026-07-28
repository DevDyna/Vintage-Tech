package com.synergy.vintagetech.init.builder.crucible;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.devdyna.cakesticklib.api.RandomUtil;
import com.devdyna.cakesticklib.api.aspect.logic.DropCollector;
import com.devdyna.cakesticklib.api.aspect.logic.ItemStorageBlock;
import com.devdyna.cakesticklib.api.aspect.logic.NoGuiStorage;
import com.devdyna.cakesticklib.api.aspect.logic.SimpleFluidStorage;
import com.devdyna.cakesticklib.api.aspect.templates.TickingBE;
import com.devdyna.cakesticklib.api.primitive.Ticker;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.LibHandlers;
import com.synergy.vintagetech.api.recipeinput.FluidAndItemInput;
import com.synergy.vintagetech.init.builder.crucible.recipe.CrucibleRecipe;
import com.synergy.vintagetech.init.types.zBlockEntities;
import com.synergy.vintagetech.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class CrucibleBE extends TickingBE
        implements ItemStorageBlock, NoGuiStorage, SimpleFluidStorage, DropCollector {

    public static final List<Integer> ITEM_SLOTS = List.of(0, 1, 2, 3);

    public static final int FLUID_TANK = 0;

    public CrucibleBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.CRUCIBLE.get(), pos, state);
    }

    @Override
    public ItemStack insertItem(ItemStack i) {
        return simpleInsertItem(i);
    }

    @Override
    public ItemStack extractItem() {

        var extracted = ItemStack.EMPTY;

        for (int slot : ITEM_SLOTS) {
            extracted = simpleExtractItemByIndex(slot);
            if (!extracted.isEmpty())
                break;
        }

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

        if (!getBlockState().getValue(CrucibleBlock.HEATED))
            return;

        List<ItemStack> items = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();

        for (var slot : ITEM_SLOTS)
            if (!getStackInSlot(slot).isEmpty()) {
                items.add(getStackInSlot(slot));
                indexes.add(slot);
            }

        if (items.isEmpty())
            return;

        for (ItemStack item : items) {

            var index = indexes.get(items.indexOf(item));

            Optional<RecipeHolder<CrucibleRecipe>> r = level.getServer().getRecipeManager()
                    .getRecipeFor(zRecipeTypes.CRUCIBLE.getType(),
                            new FluidAndItemInput(fluid, item), level);

            if (r.isEmpty())
                continue;

            var recipe = r.get().value();

            if (recipe.getInputFluid().amount() > fluid.amount())
                continue;

            if (recipe.getItemInput().count() > item.count())
                continue;

            int recipeMultiplier = Math.min(item.count() / recipe.getItemInput().count(),
                    fluid.amount() / recipe.getInputFluid().amount());

            if (item.count() < recipe.getItemInput().count() * recipeMultiplier)
                return;

            if (fluid.amount() % recipe.getInputFluid().amount() != 0)
                return;

            if (recipe.getOutputFluid() != null)
                if (recipe.getOutputFluid().amount() * recipeMultiplier > getTankCapacity())
                    continue;

            var canInsert = true;

            if (recipe.getOutputItem() != null)
                try (var tx = Transaction.openRoot()) {
                    canInsert = getItemStorage().insert(
                            ItemResource.of(recipe.getOutputItem().item()),
                            recipe.getOutputItem().item().count() * recipeMultiplier,
                            tx) >= recipe.getOutputItem().item().count() * recipeMultiplier;

                    tx.close();
                }

            if (!canInsert)
                continue;

            if (RandomUtil.chance(level, 25))
                level.playSound(null, getBlockPos(),
                        SoundEvents.BREWING_STAND_BREW,
                        SoundSource.BLOCKS, 0.3f, 1.5f + (RandomUtil.chance(level, 50) ? 0.5f : 0.25f));

            if (ticker == null)
                ticker = Ticker.of(recipe.getTicks() * recipeMultiplier);

            if (ticker.commit()) {

                try (var tx = Transaction.openRoot()) {

                    if (recipe.getOutputItem() != null)
                        if (RandomUtil.chance(level, recipe.getOutputItem().chance()))
                            getItemStorage().insert(
                                    ItemResource.of(recipe.getOutputItem().item()),
                                    recipe.getOutputItem().item().count(),
                                    tx);

                    getFluidStorage().extract(FLUID_TANK, FluidResource.of(fluid), fluid.amount(), tx);

                    if (recipe.getOutputFluid() != null)
                        getFluidStorage().insert(FLUID_TANK, FluidResource.of(recipe.getOutputFluid()),
                                recipe.getOutputFluid().amount(), tx);

                    getItemStorage().extract(index, ItemResource.of(item),
                            recipe.getItemInput().count(), tx);

                    tx.commit();
                }

                ticker = null;
            }

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
