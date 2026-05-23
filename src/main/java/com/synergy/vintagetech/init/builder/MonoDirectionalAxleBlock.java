package com.synergy.vintagetech.init.builder;

import java.util.List;

import com.mojang.serialization.MapCodec;
import com.synergy.vintagetech.api.AxleHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.PushReaction;

public abstract class MonoDirectionalAxleBlock extends Block implements AxleHandler {

    public MonoDirectionalAxleBlock(Properties p) {
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
        return List.of();
    }

    @Override
    public boolean canInputFrom(Direction dir, BlockState state) {
        return dir == getInputRotation(state);
    }

    public abstract Direction getInputRotation(BlockState state);
}
