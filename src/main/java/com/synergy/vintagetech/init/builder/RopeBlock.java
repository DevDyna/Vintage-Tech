package com.synergy.vintagetech.init.builder;

import com.mojang.serialization.MapCodec;
import com.synergy.vintagetech.api.RopeHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class RopeBlock extends PipeBlock
// implements Connectable //TODO API : rework Connectable
{

    public static final BooleanProperty HAS_CORNER = BooleanProperty.create("has_corner");

    public RopeBlock(Properties properties) {
        super(4, properties.sound(SoundType.WOOL));
    }

    @Override
    protected MapCodec<? extends PipeBlock> codec() {
        return simpleCodec(RopeBlock::new);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(NORTH, SOUTH, EAST, WEST, UP, DOWN, HAS_CORNER);
    }

    public boolean connectsTo(BlockGetter level, BlockPos pos, Direction dir) {
        var state = level.getBlockState(pos.relative(dir));
        return !isExceptionForConnection(state)
                && state.isFaceSturdy(level, pos.relative(dir.getOpposite()), dir)
                || state.is(this) || state.getBlock() instanceof RopeHandler;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var level = context.getLevel();
        var pos = context.getClickedPos();

        return super.getStateForPlacement(context)
                .setValue(NORTH, this.connectsTo(level, pos, Direction.NORTH))
                .setValue(SOUTH, this.connectsTo(level, pos, Direction.SOUTH))
                .setValue(EAST, this.connectsTo(level, pos, Direction.EAST))
                .setValue(WEST, this.connectsTo(level, pos, Direction.WEST))
                .setValue(UP, this.connectsTo(level, pos, Direction.UP))
                .setValue(DOWN, this.connectsTo(level, pos, Direction.DOWN))
                .setValue(HAS_CORNER, hasCorner(level, pos))

        ;
    }

    private boolean hasCorner(
            LevelReader level, BlockPos pos) {

        var north = connectsTo(level, pos, Direction.NORTH);
        var south = connectsTo(level, pos, Direction.SOUTH);
        var east = connectsTo(level, pos, Direction.EAST);
        var west = connectsTo(level, pos, Direction.WEST);
        var up = connectsTo(level, pos, Direction.UP);
        var down = connectsTo(level, pos, Direction.DOWN);

        return !((north && south
                && !east && !west && !up && !down)
                || (east && west &&
                        !north && !south && !up && !down)
                || (up && down &&
                        !north && !south && !east && !west));
    }

    @Override
    protected BlockState updateShape(
            BlockState state,
            LevelReader level,
            ScheduledTickAccess ticks,
            BlockPos pos,
            Direction directionToNeighbour,
            BlockPos neighbourPos,
            BlockState neighbourState,
            RandomSource random) {
        return state.setValue(PROPERTY_BY_DIRECTION.get(directionToNeighbour),
                this.connectsTo(level, pos, directionToNeighbour))
                .setValue(HAS_CORNER, hasCorner(level, pos));
    }

}
