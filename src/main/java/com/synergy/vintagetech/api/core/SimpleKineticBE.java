package com.synergy.vintagetech.api.core;

import com.synergy.vintagetech.api.factories.handlers.TransmissionHandler;
import com.synergy.vintagetech.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SimpleKineticBE extends BlockEntity implements TransmissionHandler {

    public SimpleKineticBE(BlockPos worldPosition, BlockState blockState) {
        super(zBlockEntities.TRANSMISSION.get(), worldPosition, blockState);
    }

}
