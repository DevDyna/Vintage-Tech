package com.synergy.vintagetech.init.builder.engine;

import com.synergy.vintagetech.api.blockfactory.engine.BaseEngineBE;
import com.synergy.vintagetech.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class SteamEngineBE extends BaseEngineBE {

    public SteamEngineBE(BlockPos p, BlockState s) {
        super(zBlockEntities.STEAM_ENGINE.get(), p, s);
    }
    //TODO IMP : rework
}