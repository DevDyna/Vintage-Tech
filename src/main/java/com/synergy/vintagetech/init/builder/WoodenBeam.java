package com.synergy.vintagetech.init.builder;

import com.synergy.vintagetech.api.factories.handlers.AxleHandler;
import com.synergy.vintagetech.api.factories.handlers.RopeHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WoodenBeam extends RotatedPillarBlock implements RopeHandler {

    public WoodenBeam(Properties p) {
        super(p);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(AXIS)) {
            case X -> AxleHandler.axle_X;
            case Y -> AxleHandler.axle_Y;
            case Z -> AxleHandler.axle_Z;
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return this.defaultBlockState()
                .setValue(AXIS, c.getClickedFace().getAxis())
                .setValue(HAS_ROPE, hasRope(c.getLevel(), c.getClickedPos(), c.getClickedFace().getAxis()));
    }

    @Override
    protected BlockState updateShape(
            BlockState state,
            LevelReader level,
            ScheduledTickAccess ticks,
            BlockPos pos,
            Direction direction,
            BlockPos neighbourPos,
            BlockState neighbourState,
            RandomSource random) {

        return state.setValue(HAS_ROPE, hasRope(level, pos, state.getValue(AXIS)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(AXIS, HAS_ROPE);
    }
}