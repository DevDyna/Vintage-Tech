package com.synergy.vintagetech.init.builder.creative_engine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.synergy.vintagetech.api.blockfactory.engine.BaseEngineBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class CreativeEngineBlock extends BaseEngineBlock {

    public CreativeEngineBlock(Properties p) {
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
    public Map<Direction, Boolean> getAxis(BlockState state) {
        Map<Direction, Boolean> map = new HashMap<>();

        for (Direction d : Direction.values())
            map.put(d, getDefaultRotationState());

        return map;
    }

    @Override
    public List<Direction> getGenDirections(Level level, BlockPos blockPos, BlockState blockState) {
        return Arrays.asList(Direction.values());
    }

    @Override
    public boolean getWhenActive(Level level, BlockPos blockPos, BlockState blockState) {
        return true;
    }

}
