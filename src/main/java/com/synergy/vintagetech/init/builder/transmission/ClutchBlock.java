package com.synergy.vintagetech.init.builder.transmission;

import com.synergy.vintagetech.api.factories.handlers.GeneratorHandler.NetworkState;
import com.synergy.vintagetech.api.factories.transmission.MotionAlteratorBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ClutchBlock extends MotionAlteratorBlock {

    public ClutchBlock(Properties p) {
        super(p);
    }

    @Override
    public NetworkState modifyNetwork(Level level, BlockPos pos, BlockState state, NetworkState status) {
        if (!isActive(level, pos))
            return status;

        return NetworkState.of(
                false,
                status.rotation());
    }

}
