package com.synergy.vintagetech.api.factories.handlers;

import java.util.Arrays;

import com.synergy.vintagetech.init.builder.RopeBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public interface RopeHandler {

    default boolean getWhenRopeConnect(BlockState state, Direction dir) {
        return true;
    }

    public static final BooleanProperty HAS_ROPE = BooleanProperty.create("has_rope");

    default boolean hasRope(BlockGetter level, BlockPos pos, Direction.Axis... axis) {
        for (var dir : Direction.values()) {

            if (axis.length > 0)
                if (Arrays.asList(axis).contains(dir.getAxis()))
                    continue;

            var relative = level.getBlockState(pos.relative(dir));

            if (relative.getBlock() instanceof RopeBlock)
                if (relative.getValue(RopeBlock.PROPERTY_BY_DIRECTION.get(dir.getOpposite())))
                    return true;

        }

        return false;

    }
}
