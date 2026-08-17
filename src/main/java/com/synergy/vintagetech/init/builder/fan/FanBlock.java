package com.synergy.vintagetech.init.builder.fan;

import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.synergy.vintagetech.api.factories.MonoDirectionalAxleBlock;
import com.synergy.vintagetech.api.factories.handlers.AxleHandler;
import com.synergy.vintagetech.api.factories.handlers.RotableAxleBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FanBlock extends MonoDirectionalAxleBlock implements RotableAxleBlock {

    public FanBlock(Properties p) {
        super(p);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var facing = state.getValue(FACING);
        return level.getBlockState(pos.relative(facing.getOpposite())).isFaceSturdy(level, pos, facing)
                || (level.getBlockState(pos.relative(facing.getOpposite())).getBlock() instanceof AxleHandler axle
                        && axle.getOutputDirections(level.getBlockState(pos.relative(facing.getOpposite())))
                                .contains(facing.getOpposite()));
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos,
            Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        return directionToNeighbour.getOpposite() == state.getValue(FACING)
                && !canSurvive(state, level, pos)
                        ? Blocks.AIR.defaultBlockState()
                        : state;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return this.defaultBlockState()
                .setValue(INVERTED, false)
                .setValue(ENABLED, false)
                .setValue(FACING, c.getClickedFace());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(FACING, ENABLED, INVERTED);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> Shapes.box(0, 0, 0.875, 1, 1, 1);
            case SOUTH -> Shapes.box(0, 0, 0, 1, 1, 0.125);
            case WEST -> Shapes.box(0.875, 0, 0, 1, 1, 1);
            case EAST -> Shapes.box(0, 0, 0, 0.125, 1, 1);
            case UP -> Shapes.box(0, 0, 0, 1, 0.125, 1);
            case DOWN -> Shapes.box(0, 0.875, 0, 1, 1, 1);
        };

    }

    @Override
    public Map<Direction, Boolean> getAxis(BlockState state) {
        return Map.of(state.getValue(FACING).getOpposite(), state.getValue(INVERTED));
    }

    @Override
    public Direction getInputRotation(BlockState state) {
        return state.getValue(FACING).getOpposite();
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new FanBE(p, s);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level l, BlockState s, BlockEntityType<T> ty) {
        return (lvl, pos, b, t) -> {
            if (t instanceof FanBE be) {

                if (l == null)
                    return;

                be.tickBoth();
                if (l.isClientSide())
                    be.tickClient();
                else
                    be.tickServer();

            }

        };
    }

}
