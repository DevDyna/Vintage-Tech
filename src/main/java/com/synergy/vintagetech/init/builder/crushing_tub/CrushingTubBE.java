package com.synergy.vintagetech.init.builder.crushing_tub;

import java.util.Optional;

import com.synergy.vintagetech.api.factories.handlers.TreeTapHandler;
import com.synergy.vintagetech.init.builder.crushing_tub.recipe.CrushingTubRecipe;
import com.devdyna.cakesticklib.api.RandomUtil;
import com.devdyna.cakesticklib.api.aspect.logic.DropCollector;
import com.devdyna.cakesticklib.api.aspect.logic.ItemStorageBlock;
import com.devdyna.cakesticklib.api.aspect.logic.NoGuiStorage;
import com.devdyna.cakesticklib.api.aspect.logic.SimpleFluidStorage;
import com.devdyna.cakesticklib.api.aspect.templates.TickingBE;
import com.devdyna.cakesticklib.api.recipe.recipeInput.ItemInput;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.LibHandlers;
import com.synergy.vintagetech.init.types.zBlockEntities;
import com.synergy.vintagetech.init.types.zItems;
import com.synergy.vintagetech.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class CrushingTubBE extends TickingBE
        implements NoGuiStorage, ItemStorageBlock, SimpleFluidStorage, DropCollector, TreeTapHandler {

    public static final int FLUID_TANK = 0;

    public CrushingTubBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.CRUSHING_TUB.get(), pos, blockState);
    }

    public ItemStack insertItem(ItemStack stack) {
        update();
        var inserted = 0;

        if (stack.is(zItems.MESH) && !getBlockState().getValue(CrushingTubBlock.MESH)) {
            inserted = 1;
            level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(CrushingTubBlock.MESH, true));
        } else
            try (Transaction tx = Transaction.openRoot()) {
                inserted = getItemStorage().insert(ItemResource.of(stack), stack.getCount(), tx);
                tx.commit();
            }

        return x.item(stack.getItem(), stack.getCount() - inserted);
    }

    public ItemStack extractItem() {
        update();

        var resource = getItemStorage().getResource(0);
        var extracted = 0;

        if (resource.isEmpty())
            if (getBlockState().getValue(CrushingTubBlock.MESH)) {
                level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(CrushingTubBlock.MESH, false));
                return x.item(zItems.MESH.get());
            } else
                return ItemStack.EMPTY;

        try (Transaction tx = Transaction.openRoot()) {
            extracted = getItemStorage()
                    .extract(0, resource, getItemStorage().getAmountAsInt(0), tx);
            tx.commit();
        }
        return resource.toStack(extracted);
    }

    public void craft() {

        if (level == null)
            return;

        if (getItemStorage() == null)
            return;

        update();

        var item = getStackInSlot(0);

        if (item.isEmpty())
            return;

        Optional<RecipeHolder<CrushingTubRecipe>> r = level.getServer().getRecipeManager()
                .getRecipeFor(zRecipeTypes.CRUSHING_TUB.getType(),
                        new ItemInput.simple(item), level);

        if (r.isEmpty())
            return;

        var recipe = r.get().value();

        if (recipe.getRequireMesh())
            if (!getBlockState().getValue(CrushingTubBlock.MESH))
                return;

        if (recipe.getFluid() != null)
            if (!getFluidStorage().getResource(FLUID_TANK).isEmpty())
                if (!recipe.getFluid().is(getFluidStorage().getResource(FLUID_TANK).getFluid())
                        || getFluidStorage().getAmountAsInt(FLUID_TANK)
                                + recipe.getFluid().amount() > getTankCapacity())
                    return;

        var output = recipe.getOutput().item();

        try (var tx = Transaction.openRoot()) {
            if (recipe.getFluid() != null)
                getFluidStorage().insert(FLUID_TANK, FluidResource.of(recipe.getFluid()), recipe.getFluid().amount(),
                        tx);
            getItemStorage().extract(0, ItemResource.of(item), 1, tx);

            if (recipe.getOutput() != null)
                if (RandomUtil.chance(level, recipe.getOutput().chance()))
                    Block.popResource(level, getBlockPos().above(),
                            x.item(output.create().copy().getItem(),
                                    output.count()));

            tx.commit();
        }

        level.playSound(null, getBlockPos(),
                RandomUtil.chance(level, 50) ? SoundEvents.SLIME_BLOCK_FALL : SoundEvents.SNIFFER_EGG_CRACK,
                SoundSource.BLOCKS, 1f, 1f);

        update();
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

}