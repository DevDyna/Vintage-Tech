package com.synergy.vintagetech.api.factories.transmission;

import com.devdyna.cakesticklib.api.aspect.templates.TickingBE;
import com.synergy.vintagetech.api.factories.handlers.AxleHandler;
import com.synergy.vintagetech.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TransmissionBE extends TickingBE {

    public TransmissionBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.TRANSMISSION.get(), pos, state);
    }

    public TransmissionBE(BlockEntityType<? extends BlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public float getRotation(float partialTicks) {
        if (level == null)
            return getDeactiveSpeed();

        var speed = getBlockState().getValue(AxleHandler.ENABLED) ? getActiveSpeed() : getDeactiveSpeed();

        return ((level.getGameTime() + partialTicks) * speed) % 360;
    }

    public float getActiveSpeed() {
        return 12f;
    }

    public float getDeactiveSpeed() {
        return 0f;
    }

}