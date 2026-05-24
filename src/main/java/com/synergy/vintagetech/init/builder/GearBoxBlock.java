package com.synergy.vintagetech.init.builder;

import java.util.Arrays;
import java.util.List;

import com.synergy.vintagetech.api.BaseAxleBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GearBoxBlock extends BaseAxleBlock {

    public GearBoxBlock(Properties p) {
        super(p);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return this.defaultBlockState()
                .setValue(ENABLED, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(ENABLED);
    }

    @Override
    public List<Direction> getOutputDirections(BlockState state) {
        return Arrays.asList(Direction.values());
    }

    @Override
    public boolean canInputFrom(Direction dir, BlockState state) {
        return true;
    }

    @Override
    public List<Axis> getRotationAxis(BlockState state) {
        return Arrays.asList(Axis.values());
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.join(axleX, Shapes.join(axleZ, axleY, BooleanOp.OR), BooleanOp.OR).optimize();
    }

}
