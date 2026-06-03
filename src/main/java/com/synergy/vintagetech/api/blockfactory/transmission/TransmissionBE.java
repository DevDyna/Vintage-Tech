package com.synergy.vintagetech.api.blockfactory.transmission;

import com.devdyna.cakesticklib.api.aspect.templates.TickingBE;
import com.synergy.vintagetech.api.AxleHandler;
import com.synergy.vintagetech.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TransmissionBE extends TickingBE {

    protected float speed;

    public TransmissionBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.TRANSMISSION.get(), pos, state);
    }

    public TransmissionBE(BlockEntityType<? extends BlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public float getRotation(float partialTicks) {
        if (level == null)
            return 0;

        return (level.getGameTime() + partialTicks) *
                (getBlockState().getValue(AxleHandler.ENABLED) ? 16f : 0f);
    }

}