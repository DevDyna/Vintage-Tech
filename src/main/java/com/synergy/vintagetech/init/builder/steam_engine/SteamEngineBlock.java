package com.synergy.vintagetech.init.builder.steam_engine;

import java.util.List;
import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.synergy.vintagetech.api.blockfactory.HorizontalAxleBlock;
import com.synergy.vintagetech.api.blockfactory.engine.BaseEngineBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SteamEngineBlock extends BaseEngineBlock
        implements // BucketInteraction, FluidTooltipWhenEmpty,
        HorizontalAxleBlock {

    public SteamEngineBlock(Properties p) {
        super(p);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(HORIZONTAL_FACING).getAxis()) {
            case Direction.Axis.X -> axle_X;
            case Direction.Axis.Y -> axle_Y;
            case Direction.Axis.Z -> axle_Z;
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return this.defaultBlockState()
                .setValue(ENABLED, false)
                .setValue(HORIZONTAL_FACING, c.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(HORIZONTAL_FACING, ENABLED);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new SteamEngineBE(p, s);
    }

    @Override
    public Map<Direction, Boolean> getAxis(BlockState state) {
        return Map.of(state.getValue(HORIZONTAL_FACING), false);
    }

    @Override
    public List<Direction> getGenDirections(Level level, BlockPos pos, BlockState state) {

        return List.of(state.getValue(SteamEngineBlock.HORIZONTAL_FACING));
    }

    @Override
    public boolean getWhenActive(Level level, BlockPos pos, BlockState state) {
        return CampfireBlock.isLitCampfire(
                level.getBlockState(pos.below()));
    }

}
