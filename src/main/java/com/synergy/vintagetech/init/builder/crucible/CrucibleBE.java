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
import com.devdyna.cakesticklib.setup.registry.LibHandlers;

import com.devdyna.cakesticklib.api.recipe.recipeOutput.ChanceOutput;
import com.synergy.vintagetech.api.TreeTapHandler;
import com.synergy.vintagetech.api.recipeinput.CrucibleInput;
import com.synergy.vintagetech.init.builder.crucible.recipe.CrucibleRecipe;
import com.synergy.vintagetech.init.types.zBlockEntities;
import com.synergy.vintagetech.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.state.BlockState;

import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class CrucibleBE extends TickingBE implements ItemStorageBlock, NoGuiStorage, SimpleFluidStorage, DropCollector, TreeTapHandler {

    public static final List<Integer> ITEM_SLOTS = List.of(0, 1, 2, 3,4,5,6,7);

    public static final int FLUID_TANK = 0;

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

    private Ticker ticker;

    @Override
    public void tickServer() {

        if (getFluidStorage() == null)
            return;

        var fluid = getAsStack(FLUID_TANK);

        if (fluid.isEmpty())
            return;

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

        if (items.isEmpty())
            return;

        Optional<RecipeHolder<CrucibleRecipe>> r = level.getServer().getRecipeManager()
                .getRecipeFor(zRecipeTypes.CRUCIBLE.getType(), new CrucibleInput(items, fluid), level);

        if (r.isEmpty())
            return;

        var recipe = r.get().value();

        if (fluid.amount() < recipe.getInputFluid().amount())
            return;

        int multiplier = fluid.amount() / recipe.getInputFluid().amount();

        for (var ingredient : recipe.getItemInputs()) {

            int count = 0;

            for (ItemStack stack : items)

                if (ingredient.ingredient().test(stack))
                    count += stack.getCount();

            multiplier = Math.min(multiplier, count / ingredient.count());
        }

        if (multiplier <= 0)
            return;

        if (recipe.getOutputFluid() != null)

            if (recipe.getOutputFluid().amount() * multiplier > getTankCapacity())

                return;

        if (RandomUtil.chance(level, 0.05f))

            level.playSound(null, getBlockPos(), SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 0.15f,
                    1.25f + (RandomUtil.chance(level, 50) ? 0.5f : 0.25f));

        if (ticker == null)
            ticker = Ticker.of(recipe.getTicks() * multiplier);

        if (!ticker.commit())
            return;

        level.playSound(null, getBlockPos(), SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 0.3f,
                0.25f + (RandomUtil.chance(level, 50) ? 0.5f : 0.25f));

        try (var tx = Transaction.openRoot()) {

            for (SizedIngredient ingredient : recipe.getItemInputs()) {

                int needed = ingredient.count() * multiplier;

                for (int i = 0; i < items.size() && needed > 0; i++) {

                    var stack = items.get(i);

                    if (ingredient.ingredient().test(stack)) {

                        int remove = Math.min(needed, stack.getCount());

                        getItemStorage().extract(slots.get(i), ItemResource.of(getStackInSlot(slots.get(i))), remove,
                                tx);

                        needed -= remove;
                    }
                }
            }

            for (ChanceOutput.Item output : recipe.getOutputItems()) {
                int amount = output.item().count() * multiplier;

                if (RandomUtil.chance(level, output.chance()))
                    if (getItemStorage().insert(ItemResource.of(output.item()), amount, tx) < amount)
                        return;

            }

            getFluidStorage().extract(FLUID_TANK, FluidResource.of(fluid), recipe.getInputFluid().amount() * multiplier,
                    tx);

            if (recipe.getOutputFluid() != null)
                getFluidStorage().insert(FLUID_TANK, FluidResource.of(recipe.getOutputFluid()),
                        recipe.getOutputFluid().amount() * multiplier, tx);

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
}