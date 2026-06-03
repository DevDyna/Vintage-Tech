package com.synergy.vintagetech.init.builder.transmission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.synergy.vintagetech.api.blockfactory.BaseKineticBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AxleBlock extends BaseKineticBlock {

    public static final EnumProperty<Axis> AXIS = RotatedPillarBlock.AXIS;

    public AxleBlock(Properties p) {
        super(p);
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return RotatedPillarBlock.rotatePillar(state, rotation);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(AXIS)) {
            case Direction.Axis.X -> axle_X;
            case Direction.Axis.Y -> axle_Y;
            case Direction.Axis.Z -> axle_Z;
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return this.defaultBlockState()
                .setValue(ENABLED, false)
                .setValue(INVERTED, false)
                .setValue(AXIS, c.getClickedFace().getAxis());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(AXIS, ENABLED, INVERTED);
    }

    @Override
    public List<Direction> getOutputDirections(BlockState state) {
        return List.of(state.getValue(AXIS).getDirections());
    }

    @Override
    public boolean canInputFrom(Direction dir, BlockState state) {
        return dir.getAxis() == state.getValue(AXIS);
    }

    @Override
    public Map<Direction, Boolean> getAxis(BlockState state) {
        Map<Direction, Boolean> map = new HashMap<>();

        for (Direction d : state.getValue(AXIS).getDirections())
            map.put(d,state.getValue(INVERTED));

        return map;
    }

}
