package com.synergy.vintagetech.api.factories.beams;

import com.synergy.vintagetech.api.factories.handlers.RopeHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public abstract class BaseWoodenBeam extends RotatedPillarBlock implements RopeHandler {

    public BaseWoodenBeam(Properties p) {
        super(p);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return this.defaultBlockState()
                .setValue(HAS_ROPE, hasRope(c.getLevel(), c.getClickedPos(), c.getClickedFace().getAxis()));
    }

    @Override
    protected BlockState updateShape(BlockState s, LevelReader l, ScheduledTickAccess t, BlockPos p, Direction d,
            BlockPos np, BlockState ns, RandomSource r) {
        return s.setValue(HAS_ROPE, hasRope(l, p, s.getValue(AXIS)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(HAS_ROPE);
    }

    @Override
    public boolean isLadder(BlockState state, LevelReader level, BlockPos pos, LivingEntity entity) {
        return state.getValue(HAS_ROPE);
    }

}
