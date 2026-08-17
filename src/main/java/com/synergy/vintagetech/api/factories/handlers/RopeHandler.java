package com.synergy.vintagetech.api.factories.handlers;

import com.synergy.vintagetech.init.builder.RopeBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public interface RopeHandler {

    default boolean getWhenRopeConnect(Direction dir) {
        return true;
    }

    public static final BooleanProperty HAS_ROPE = BooleanProperty.create("has_rope");

    default boolean hasRope(BlockGetter level, BlockPos pos, Direction.Axis axis) {
        for (Direction dir : Direction.values()) {

            if (dir.getAxis() == axis)
                continue;

            var relative = level.getBlockState(pos.relative(dir));

            if (relative.getBlock() instanceof RopeBlock) 
                if (relative.getValue(RopeBlock.PROPERTY_BY_DIRECTION.get(dir.getOpposite())))
                    return true;
            
        }

        return false;

    }
}
