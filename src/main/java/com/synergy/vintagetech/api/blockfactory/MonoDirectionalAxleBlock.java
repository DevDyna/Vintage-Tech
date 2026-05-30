package com.synergy.vintagetech.api.blockfactory;

import java.util.List;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public abstract class MonoDirectionalAxleBlock extends BaseKineticBlock {

    public MonoDirectionalAxleBlock(Properties p) {
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
        return List.of();
    }

    @Override
    public boolean canInputFrom(Direction dir, BlockState state) {
        return dir == getInputRotation(state);
    }

    public abstract Direction getInputRotation(BlockState state);
}
