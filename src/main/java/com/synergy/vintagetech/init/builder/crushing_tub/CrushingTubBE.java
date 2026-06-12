package com.synergy.vintagetech.init.builder.crushing_tub;

import java.util.Optional;
import com.synergy.vintagetech.init.builder.crushing_tub.recipe.CrushingTubRecipe;
import com.devdyna.cakesticklib.api.RandomUtil;
import com.devdyna.cakesticklib.api.aspect.logic.ItemStorageBlock;
import com.devdyna.cakesticklib.api.aspect.logic.NoGuiStorage;
import com.devdyna.cakesticklib.api.aspect.logic.SimpleFluidStorage;
import com.devdyna.cakesticklib.api.aspect.templates.TickingBE;
import com.devdyna.cakesticklib.api.recipe.recipeInput.ItemInput;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.LibHandlers;
import com.synergy.vintagetech.init.types.zBlockEntities;
import com.synergy.vintagetech.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

@SuppressWarnings("null")
public class CrushingTubBE extends TickingBE implements NoGuiStorage, ItemStorageBlock, SimpleFluidStorage {

    // private BlockCapabilityCache<IItemHandler, Direction> cache;

    public CrushingTubBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.CRUSHING_TUB.get(), pos, blockState);
    }

    public ItemStack insertItem(ItemStack stack) {
        update();
        var inserted = 0;

        try (Transaction tx = Transaction.openRoot()) {
            inserted = getItemStorage().insert(0, ItemResource.of(stack), stack.getCount(), tx);
            tx.commit();
        }

        return x.item(stack.getItem(), stack.getCount() - inserted);
    }

    public ItemStack extractItem() {
        update();
        var resource = getItemStorage().getResource(0);

        if (resource.isEmpty())
            return ItemStack.EMPTY;

        try (Transaction tx = Transaction.openRoot()) {

            var extracted = getItemStorage()
                    .extract(0, resource, getItemStorage().getAmountAsInt(0), tx);
            tx.commit();

            return resource.toStack(extracted);
        }
    }

    public void craft(boolean dropWhenCrafted) {

        if (level == null)
            return;

        if (getItemStorage() == null)
            return;

        update();

        if (getStackInSlot(0).isEmpty())
            return;

        Optional<RecipeHolder<CrushingTubRecipe>> r = level.getServer().getRecipeManager()
                .getRecipeFor(zRecipeTypes.CRUSHING_TUB.getType(),
                        new ItemInput.simple(getStackInSlot(0)), level);

        if (r.isEmpty())
            return;

        var recipe = r.get().value();

        if (recipe.getFluid() != null)
            if (!getFluidStorage().getResource(0).isEmpty())
                if (!recipe.getFluid().is(getFluidStorage().getResource(0).getFluid())
                        || getFluidStorage().getAmountAsInt(0) + recipe.getFluid().amount() > getTankCapacity())
                    return;

        try (var tx = Transaction.openRoot()) {
            if (recipe.getFluid() != null)
                getFluidStorage().insert(0, FluidResource.of(recipe.getFluid()), recipe.getFluid().amount(), tx);
            getItemStorage().extract(0, ItemResource.of(getStackInSlot(0)), 1, tx);
            tx.commit();
        }

        if (recipe.getOutput() != null)
            if (level.getRandom().nextFloat() < recipe.getOutput().chance())
                if (dropWhenCrafted)
                    Block.popResource(level, getBlockPos().above(),
                            recipe.getOutput().item().create().copy());

        level.playSound(null, getBlockPos(),
                RandomUtil.chance(level, 50) ? SoundEvents.SLIME_BLOCK_FALL : SoundEvents.SNIFFER_EGG_CRACK,
                SoundSource.BLOCKS, 1f, 1f);

        update();
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        getItemStorage().serialize(output);
        getFluidStorage().serialize(output);
        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        getItemStorage().deserialize(input);
        getFluidStorage().deserialize(input);
        super.loadAdditional(input);
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