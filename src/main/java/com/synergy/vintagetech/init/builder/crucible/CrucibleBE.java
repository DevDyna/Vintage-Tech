package com.synergy.vintagetech.init.builder.crucible;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.devdyna.cakesticklib.api.RandomUtil;
import com.devdyna.cakesticklib.api.aspect.logic.*;
import com.devdyna.cakesticklib.api.primitive.Ticker;
import com.devdyna.cakesticklib.setup.registry.LibHandlers;
import com.devdyna.cakesticklib.api.recipe.recipeOutput.ChanceOutput;
import com.devdyna.cakesticklib.api.templates.SynchedBlockEntity;
import com.synergy.vintagetech.api.TickerRecipe;
import com.synergy.vintagetech.api.factories.handlers.TreeTapHandler;
import com.synergy.vintagetech.api.inputs.CrucibleInput;
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

public class CrucibleBE extends SynchedBlockEntity
        implements ItemStorageBlock, NoGuiStorage, SimpleFluidStorage, DropCollector, TreeTapHandler, Tickable,
        TickerRecipe {

    public static final List<Integer> ITEM_SLOTS = List.of(0, 1, 2, 3, 4, 5, 6, 7);

    public static final int FLUID_TANK = 0;

    private int multiplier = 1;

    public CrucibleBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.CRUCIBLE.get(), pos, state);
    }

    @Override
    public ItemStack insertItem(ItemStack stack) {
        return simpleInsertItem(stack);
    }

    @Override
    public ItemStack extractItem() {

        for (int slot : ITEM_SLOTS) {

            var stack = simpleExtractItemByIndex(slot);

            if (!stack.isEmpty())
                return stack;
        }

        return ItemStack.EMPTY;
    }

    @Override
    public void update() {
        super.update();

        if (ticker != null)
            ticker = null;

    }

    private Ticker ticker;

    @Override
    public void tickBoth() {

        if (getFluidStorage() == null)
            return;

        if (getFluidStorage().size() <= 0)
            return;

        var fluid = getAsStack(FLUID_TANK);

        if (fluid.isEmpty()) {
            multiplier = 1;
            ticker = null;
            return;
        }

        if (getItemStorage() == null)
            return;

        if (!getBlockState().getValue(CrucibleBlock.HEATED))
            return;

        List<ItemStack> items = new ArrayList<>();
        List<Integer> slots = new ArrayList<>();

        for (int slot : ITEM_SLOTS) {

            var stack = getStackInSlot(slot);

            if (!stack.isEmpty()) {
                items.add(stack);
                slots.add(slot);
            }
        }

        if (items.isEmpty()) {
            multiplier = 1;
            ticker = null;
            return;
        }

        if (level.isClientSide())
            return;

        Optional<RecipeHolder<CrucibleRecipe>> r = level.getServer().getRecipeManager()
                .getRecipeFor(zRecipeTypes.CRUCIBLE.getType(), new CrucibleInput(items, fluid), level);

        if (r.isEmpty()) {
            multiplier = 1;
            ticker = null;
            update();
            return;
        }

        var recipe = r.get().value();

        multiplier = fluid.amount() / recipe.getInputFluid().amount();

        for (var ingredient : recipe.getItemInputs()) {

            var count = 0;

            for (var stack : items)
                if (ingredient.ingredient().test(stack))
                    count += stack.getCount();

            multiplier = Math.min(16, // TODO config
                    Math.min(multiplier, count / ingredient.count()));
        }

        update();

        if (multiplier <= 0)
            return;

        var inputFluidAmount = recipe.getInputFluid().amount() * multiplier;

        var outputFluidAmount = recipe.getOutputFluid() != null
                ? recipe.getOutputFluid().amount() * multiplier
                : 0;

        var finalFluidAmount = fluid.amount()
                - inputFluidAmount
                + outputFluidAmount;

        if (finalFluidAmount < 0)
            return;

        if (finalFluidAmount > getTankCapacity())
            return;

        if (RandomUtil.chance(level, 0.0125f))
            level.playSound(null, getBlockPos(), SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 0.15f,
                    1.25f + (RandomUtil.chance(level, 50) ? 0.5f : 0.25f));

        if (ticker == null)
            ticker = Ticker.of(recipe.getTicks() * multiplier);

        if (!ticker.commit())
            return;

        level.playSound(null, getBlockPos(), SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 0.3f,
                0.25f + (RandomUtil.chance(level, 50) ? 0.5f : 0.25f));

        try (var tx = Transaction.openRoot()) {

            for (var ingredient : recipe.getItemInputs()) {

                int needed = ingredient.count() * multiplier;

                for (int i = 0; i < items.size() && needed > 0; i++) {

                    var stack = items.get(i);

                    if (!ingredient.ingredient().test(stack))
                        continue;

                    var remove = Math.min(
                            needed,
                            stack.getCount());

                    var extracted = getItemStorage().extract(
                            slots.get(i),
                            ItemResource.of(
                                    getStackInSlot(slots.get(i))),
                            remove,
                            tx);

                    if (extracted != remove)
                        return;

                    needed -= remove;
                }

                if (needed > 0)
                    return;
            }

            for (ChanceOutput.Item output : recipe.getOutputItems()) {

                if (!RandomUtil.chance(level, output.chance()))
                    continue;

                var amount = output.item().count() * multiplier;

                var inserted = getItemStorage().insert(
                        ItemResource.of(output.item()),
                        amount,
                        tx);

                if (inserted != amount)
                    return;
            }

            var extractedFluid = getFluidStorage().extract(
                    FLUID_TANK,
                    FluidResource.of(fluid),
                    inputFluidAmount,
                    tx);

            if (extractedFluid != inputFluidAmount)
                return;

            if (recipe.getOutputFluid() != null) {

                var insertedFluid = getFluidStorage().insert(
                        FLUID_TANK,
                        FluidResource.of(recipe.getOutputFluid()),
                        outputFluidAmount,
                        tx);

                if (insertedFluid != outputFluidAmount)
                    return;
            }

            tx.commit();
        }

        ticker = null;
    }

    @Override
    public ItemStacksResourceHandler getItemStorage() {
        return getData(LibHandlers.ITEM_STORAGE);
    }

    @Override
    public int getSlots() {
        return 8;
    }

    @Override
    public FluidStacksResourceHandler getFluidStorage() {
        return getData(LibHandlers.FLUID_STORAGE);
    }

    @Override
    public int getTankCapacity() {
        return 16_000;
    }

    @Override
    public int getTanks() {
        return 1;
    }

    @Override
    public Ticker getTicker() {
        return ticker;
    }

    @Override
    public float getMultiplier() {
        return 1.0f / multiplier;
    }

}