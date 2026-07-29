package com.synergy.vintagetech.init.builder.windmill;

import com.synergy.vintagetech.api.blockfactory.engine.BaseEngineBE;
import com.synergy.vintagetech.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class WindMillBE extends BaseEngineBE {

    public WindMillBE(BlockPos p, BlockState s) {
        super(zBlockEntities.WINDMILL.get(), p, s);
    }

    @Override
    public float getActiveSpeed() {
        return 8f;
    }

}