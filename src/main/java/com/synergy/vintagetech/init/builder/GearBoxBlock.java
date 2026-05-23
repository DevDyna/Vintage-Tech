package com.synergy.vintagetech.init.builder;

import java.util.Arrays;
import java.util.List;

import com.synergy.vintagetech.api.AxleHandler;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.PushReaction;

public class GearBoxBlock extends Block implements AxleHandler {

    public GearBoxBlock(Properties p) {
        super(p.pushReaction(PushReaction.BLOCK));
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

}
