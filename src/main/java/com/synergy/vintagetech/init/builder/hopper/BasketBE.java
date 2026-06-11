package com.synergy.vintagetech.init.builder.hopper;

import com.devdyna.cakesticklib.api.aspect.logic.ItemStorageBlock;
import com.devdyna.cakesticklib.api.aspect.templates.TickingBE;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.LibHandlers;
import com.synergy.vintagetech.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class BasketBE extends TickingBE implements ItemStorageBlock {

    public BasketBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.BASKET.get(), pos, state);
    }

    @Override
    public void tickServer() {

        if (level.getGameTime() % 20 != 0)
            return;

        var related = getBlockPos().relative(getBlockState().getValue(BasketBlock.FACING));

        var area = AABB.encapsulatingFullBlocks(getBlockPos(), related);

        for (ItemEntity item : level.getEntitiesOfClass(ItemEntity.class, area)) {
            try (var tx = Transaction.openRoot()) {

                var copy = item.getItem().copy();

                if (copy.isEmpty())
                    tx.close();

                var insered = getItemStorage().insert(ItemResource.of(copy), copy.count(), tx);

                if (insered > 0) {
                    if (insered >= item.getItem().count()) {
                        item.setItem(ItemStack.EMPTY);
                        item.discard();
                    } else {
                        item.setItem(x.item(item.getItem().getItem(), item.getItem().count() - insered));
                    }
                    tx.commit();
                }

                tx.close();
            }

        }

    }

    @Override
    public ItemStacksResourceHandler getItemStorage() {
        return getData(LibHandlers.ITEM_STORAGE);
    }

    @Override
    public int getSlots() {
        return 27;
    }

}
