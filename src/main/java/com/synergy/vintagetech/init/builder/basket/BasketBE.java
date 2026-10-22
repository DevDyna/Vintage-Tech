package com.synergy.vintagetech.init.builder.basket;

import com.devdyna.cakesticklib.api.aspect.logic.DropCollector;
import com.devdyna.cakesticklib.api.aspect.logic.ItemStorageBlock;
import com.devdyna.cakesticklib.api.aspect.logic.NoGuiStorage;
import com.devdyna.cakesticklib.api.templates.SynchedBlockEntity;
import com.devdyna.cakesticklib.setup.registry.LibHandlers;
import com.synergy.vintagetech.init.types.zBlockEntities;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class BasketBE extends SynchedBlockEntity implements ItemStorageBlock, DropCollector, NoGuiStorage {

    public static final int INVENTORY_SLOTS = 27;

    public BasketBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.BASKET.get(), pos, state);
    }

    // protected void saveAdditional(ValueOutput output) {
    //     this.getItemStorage().serialize(output);
    //     super.saveAdditional(output);
    // }

    // protected void loadAdditional(ValueInput input) {
    //     this.getItemStorage().deserialize(input);
    //     super.loadAdditional(input);
    // }

    @Override
    public ItemStacksResourceHandler getItemStorage() {
        return getData(LibHandlers.ITEM_STORAGE);
    }

    @Override
    public int getSlots() {
        return INVENTORY_SLOTS;
    }

    @Override
    public boolean ignoreIndex() {
        return true;
    }

    public ItemStack insertItem(ItemStack stack) {
        if (stack.is(zTags.Items.BASKET_DENY))
            return stack;

            update();

        return simpleInsertItem(stack);
    }

    public ItemStack extractItem() {

        update();

        return simpleExtractItemFromSize();
    }

    @Override
    public boolean dropOnBreak(Player player) {
        return player.isCrouching();
    }

    @Deprecated
    public void clear() {

        try (var tx = Transaction.openRoot()) {

            for (int i = 0; i < getSlots(); i++) {
                if (!isSlotsEmpty())
                    getItemStorage().extract(i, getItemStorage().getResource(i), getItemStorage().getAmountAsInt(i),
                            tx);
            }
        }
    }
}
