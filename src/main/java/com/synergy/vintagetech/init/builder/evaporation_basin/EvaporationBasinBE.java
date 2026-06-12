package com.synergy.vintagetech.init.builder.evaporation_basin;

import java.util.Optional;

import com.devdyna.cakesticklib.api.RandomUtil;
import com.devdyna.cakesticklib.api.aspect.logic.ItemStorageBlock;
import com.devdyna.cakesticklib.api.aspect.logic.NoGuiStorage;
import com.devdyna.cakesticklib.api.aspect.logic.SimpleFluidStorage;
import com.devdyna.cakesticklib.api.aspect.templates.TickingBE;
import com.devdyna.cakesticklib.api.primitive.Ticker;
import com.devdyna.cakesticklib.api.recipe.recipeInput.FluidInput;
import com.devdyna.cakesticklib.setup.registry.LibHandlers;
import com.synergy.vintagetech.api.aspects.EnvironmentModifier;
import com.synergy.vintagetech.api.aspects.TimeredRecipe;
import com.synergy.vintagetech.init.builder.evaporation_basin.recipe.EvaporationBasinRecipe;
import com.synergy.vintagetech.init.types.zBlockEntities;
import com.synergy.vintagetech.init.types.zRecipeTypes;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

@SuppressWarnings("null")
public class EvaporationBasinBE extends TickingBE
        implements NoGuiStorage, ItemStorageBlock, SimpleFluidStorage, TimeredRecipe, EnvironmentModifier {

    public EvaporationBasinBE(BlockPos pos, BlockState blockState) {
        super(zBlockEntities.EVAPORATION_BASIN.get(), pos, blockState);
    }

    public ItemStack insertItem(ItemStack stack) {
        return stack;
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

    private Ticker ticker = null;

    @Override
    public void tickBoth() {

        if (getFluidStorage() == null) {
            fail();
            return;
        }

        if (getAsStack(0).isEmpty()) {
            fail();
            return;
        }

        update();

        Optional<RecipeHolder<EvaporationBasinRecipe>> r = level.getServer().getRecipeManager()
                .getRecipeFor(zRecipeTypes.EVAPORATION_BASIN.getType(),
                        new FluidInput.withNumber(getAsStack(0), getFluidStorage().getAmountAsInt(0)), level);

        if (r.isEmpty()) {
            fail();
            return;
        }

        var recipe = r.get().value();

        if (getStackInSlot(0).getMaxStackSize() < recipe.getOutput().count()
                + getStackInSlot(0).getCount()) {
            fail();
            return;
        }

        if (RandomUtil.chance(level, 5))
            level.addParticle(ParticleTypes.CLOUD, getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(),
                    0.5, 0.5, 0.5);

        if (ticker == null)
            ticker = Ticker.of(calcTicks(recipe.getTicks()));

        if (ticker.commit()) {
            try (var tx = Transaction.openRoot()) {
                getFluidStorage().extract(0, FluidResource.of(getAsStack(0)), recipe.getFluid().amount(), tx);
                getItemStorage().insert(0, ItemResource.of(recipe.getOutput()), recipe.getOutput().count(), tx);
                tx.commit();
            }

            ticker = null;

        }

        update();

    }

    public void fail() {
        ticker = null;
    }

    private int calcTicks(int base) {
        return (int) Math.max(1,
                base / getTickerSpeed());
    }

    @Override
    public boolean extractOnly() {
        return true;
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
    public ItemStacksResourceHandler getItemStorage() {
        return getData(LibHandlers.ITEM_STORAGE);
    }

    @Override
    public int getSlots() {
        return 1;
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
    public Ticker getTicker() {
        return ticker;
    }

    @Override
    public float getTickerSpeed() {
        return 1.0f * getSpeedModifier();
    }

    @Override
    public float getSpeedModifier() {
        return (level.getBlockState(getBlockPos().below()).is(zTags.Blocks.EVAPORATION_BASIC_HEATER) ? 2 : 1);
    }

    @Override
    public boolean isRequired() {
        return false;
    }

}