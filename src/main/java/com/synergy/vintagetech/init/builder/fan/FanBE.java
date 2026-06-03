package com.synergy.vintagetech.init.builder.fan;

import com.synergy.vintagetech.api.blockfactory.transmission.TransmissionBE;
import com.synergy.vintagetech.init.types.zBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class FanBE extends TransmissionBE {

    public FanBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.FAN.get(), pos, state);
    }

    @Override
    public void tickServer() {

        if (!getBlockState().getValue(FanBlock.ENABLED)) {
            return;
        }

        //TODO fan logic

    }

}
