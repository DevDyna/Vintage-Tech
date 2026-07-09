package com.synergy.vintagetech.init.builder.basket;

import com.devdyna.cakesticklib.api.aspect.logic.DropCollector;
import com.devdyna.cakesticklib.api.aspect.logic.ItemStorageBlock;
import com.devdyna.cakesticklib.setup.registry.LibHandlers;
import com.synergy.vintagetech.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

public class BasketBE extends BlockEntity implements ItemStorageBlock, DropCollector {

    public BasketBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.BASKET.get(), pos, state);
    }

    protected void saveAdditional(ValueOutput output) {
        this.getItemStorage().serialize(output);
        super.saveAdditional(output);
    }

    protected void loadAdditional(ValueInput input) {
        this.getItemStorage().deserialize(input);
        super.loadAdditional(input);
    }

    @Override
    public ItemStacksResourceHandler getItemStorage() {
        return getData(LibHandlers.ITEM_STORAGE);
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public boolean ignoreIndex() {
        return true;
    }

}
