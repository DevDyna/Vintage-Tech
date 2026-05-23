package com.synergy.vintagetech.init.builder;

import java.util.List;
import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.devdyna.cakesticklib.api.aspect.logic.Connectable;
import com.synergy.vintagetech.api.AxleHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AxleBlock extends RotatedPillarBlock implements AxleHandler {

    public AxleBlock(Properties p) {
        super(p.pushReaction(PushReaction.BLOCK));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(AXIS)) {
            case Direction.Axis.X ->
                axleX;
            case Direction.Axis.Y ->
                axleY;
            case Direction.Axis.Z ->
                axleZ;
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return this.defaultBlockState()
                .setValue(ENABLED, false)
                .setValue(AXIS, c.getClickedFace().getAxis());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(AXIS, ENABLED);
    }

    @Override
    public List<Direction> getOutputDirections(BlockState state) {
        return List.of(state.getValue(AXIS).getDirections());
    }

    @Override
    public boolean canInputFrom(Direction dir, BlockState state) {
        return dir.getAxis() == state.getValue(AXIS);
    }

    

   


}
