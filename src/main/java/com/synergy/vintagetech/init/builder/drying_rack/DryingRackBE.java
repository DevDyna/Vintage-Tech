package com.synergy.vintagetech.init.builder.drying_rack;

import java.util.List;
import java.util.Optional;

import com.devdyna.cakesticklib.api.RandomUtil;
import com.devdyna.cakesticklib.api.aspect.logic.*;
import com.devdyna.cakesticklib.api.primitive.Ticker;
import com.devdyna.cakesticklib.api.recipe.recipeInput.ItemInput;
import com.devdyna.cakesticklib.api.templates.SynchedBlockEntity;
import com.devdyna.cakesticklib.setup.registry.LibHandlers;

import com.synergy.vintagetech.init.builder.drying_rack.recipe.DryingRackRecipe;
import com.synergy.vintagetech.init.types.zBlockEntities;
import com.synergy.vintagetech.init.types.zRecipeTypes;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class DryingRackBE extends SynchedBlockEntity
        implements NoGuiStorage, ItemStorageBlock, TimeredRecipe, EnvironmentModifier, Tickable {

    public DryingRackBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.DRYING_RACK.get(), pos, blockState);
    }

    @Override
    public ItemStacksResourceHandler getItemStorage() {
        return getData(LibHandlers.ITEM_STORAGE);
    }

    @Override
    public int getSlots() {
        return 1;
    }

    public ItemStack insertItem(ItemStack stack) {
        return simpleInsertItem(stack);
    }

    public ItemStack extractItem() {
        return simpleExtractItemByIndex(0);
    }

    private Ticker ticker = null;

    @Override
    public void tickBoth() {

        if (getItemStorage() == null) {
            fail();
            return;
        }

        if (level.isClientSide())
            return;

        Optional<RecipeHolder<DryingRackRecipe>> r = level.getServer().getRecipeManager()
                .getRecipeFor(zRecipeTypes.DRYING_RACK.getType(),
                        new ItemInput.simple(getStackInSlot(0)), level);

        if (r.isEmpty()) {
            fail();
            return;
        }

        var recipe = r.get().value();

        if (RandomUtil.chance(level, 5))
            level.addParticle(ParticleTypes.CLOUD, getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(),
                    0.5, 0.5, 0.5);

        if (ticker == null)
            ticker = Ticker.of(calcTicks(recipe.getTicks()));

        if (ticker.commit()) {
            // maybe useless but could be useful *maybe?*
            try (var tx = Transaction.openRoot()) {
                getItemStorage().set(0, ItemResource.of(recipe.getOutput()),
                        recipe.getOutput().count() * getStackInSlot(0).getCount());

                tx.commit();
            }

            ticker = null;
        }

    }

    public void fail() {
        ticker = null;
    }

    private int calcTicks(int base) {
        return (int) Math.max(1, base / getTickerSpeed());
    }

    @Override
    public Ticker getTicker() {
        return ticker;
    }

    @Override
    public float getTickerSpeed() {
        return (float) (1.0f / getStackInSlot(0).getCount() * getSpeedModifier());
    }

    @Override
    public float getSpeedModifier() {
        var range = List.of(
                getBlockPos().below(),
                getBlockPos().below().below(),
                getBlockPos().below().below().below(),
                getBlockPos().below().below().below().below(),
                getBlockPos().below().below().below().below().below());

        var result = range
                .stream()
                .map(level::getBlockState)
                .anyMatch(s -> s.is(zTags.Blocks.DRYING_RACK_HEATER));
        return (result ? 2 : 1);
    }

    @Override
    public boolean isRequired() {
        return false;
    }

}