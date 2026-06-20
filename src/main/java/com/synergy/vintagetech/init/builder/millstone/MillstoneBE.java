package com.synergy.vintagetech.init.builder.millstone;

import java.util.Optional;
import com.devdyna.cakesticklib.api.aspect.logic.ItemStorageBlock;
import com.devdyna.cakesticklib.api.aspect.logic.NoGuiStorage;
import com.devdyna.cakesticklib.api.primitive.Ticker;
import com.devdyna.cakesticklib.api.recipe.recipeInput.ItemInput;
import com.devdyna.cakesticklib.setup.registry.LibHandlers;
import com.synergy.vintagetech.api.blockfactory.transmission.TransmissionBE;
import com.synergy.vintagetech.init.builder.millstone.recipe.MillstoneRecipe;
import com.synergy.vintagetech.init.types.zBlockEntities;
import com.synergy.vintagetech.init.types.zRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class MillstoneBE extends TransmissionBE implements ItemStorageBlock, NoGuiStorage {

    public static final int INPUT = 0;
    public static final int OUTPUT = 1;

    public MillstoneBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.MILLSTONE.get(), pos, state);
    }

    @Override
    public ItemStack insertItem(ItemStack i) {
        return simpleInsertItem(i);
    }

    @Override
    public ItemStack extractItem() {

        if (!getStackInSlot(OUTPUT).isEmpty())
            try (Transaction tx = Transaction.openRoot()) {

                var resource = getItemStorage().getResource(OUTPUT);

                var extracted = getItemStorage()
                        .extract(OUTPUT, resource, getItemStorage().getAmountAsInt(OUTPUT), tx);
                tx.commit();

                return resource.toStack(extracted);
            }

        return simpleExtractItem();
    }

    private Ticker ticker = null;

    @Override
    public void tickServer() {

        if (getItemStorage() == null)
            return;

        var item = getStackInSlot(INPUT);

        if (item.isEmpty())
            return;

        Optional<RecipeHolder<MillstoneRecipe>> r = level.getServer().getRecipeManager()
                .getRecipeFor(zRecipeTypes.MILLSTONE.getType(),
                        new ItemInput.simple(item), level);

        if (r.isEmpty())
            return;

        var recipe = r.get().value();

        if (recipe.getOutput().count() + getItemStorage().getAmountAsInt(OUTPUT) > getItemStorage()
                .getCapacityAsInt(OUTPUT, ItemResource.of(recipe.getOutput())))
            return;

        if (ticker == null)
            ticker = Ticker.of(recipe.getTicks());

        if (ticker.commit()) {
            try (var tx = Transaction.openRoot()) {
                getItemStorage().insert(OUTPUT, ItemResource.of(recipe.getOutput()),
                        recipe.getOutput().count(), tx);

                getItemStorage().extract(INPUT, ItemResource.of(item), 1, tx);
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

    // TODO API : move to api
    public boolean isFull() {
        return !getItemStorage().getResource(0).isEmpty()
                && getItemStorage().getCapacityAsInt(0, getItemStorage().getResource(0)) <= getItemStorage()
                        .getAmountAsInt(0);
    }
}
