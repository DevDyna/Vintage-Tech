package com.synergy.vintagetech.api;

import com.synergy.vintagetech.init.builder.RopeBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public interface RopeHandler {

    public static final BooleanProperty HAS_ROPE = BooleanProperty.create("has_rope");

    default boolean hasRope(BlockGetter level, BlockPos pos) {
        for (Direction dir : Direction.values()) {
            var relative = level.getBlockState(pos.relative(dir));

            if (relative.getBlock() instanceof RopeBlock)
                if (relative.getValue(RopeBlock.PROPERTY_BY_DIRECTION.get(dir.getOpposite())))
                    return true;

        }

        return false;
    }
}
