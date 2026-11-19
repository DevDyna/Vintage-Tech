package com.synergy.vintagetech.init.builder;

import com.devdyna.cakesticklib.api.primitive.QueueUtil;
import com.devdyna.cakesticklib.api.primitive.QueueUtil.QueueStatus;
import com.mojang.serialization.MapCodec;
import com.synergy.vintagetech.api.factories.handlers.RopeHandler;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class RopeBlock extends PipeBlock {

    public static final BooleanProperty HAS_CORNER = BooleanProperty.create("has_corner");

    public RopeBlock(Properties p) {
        super(4, p);
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

        if (state.is(zTags.Blocks.ROPE_IGNORE_CONNECTION))
            return false;

        if (state.is(this))
            return true;

        if (state.getBlock() instanceof RopeHandler h)
            return h.getWhenRopeConnect(state, dir);

        if (canSupportCenter((LevelReader) level, pos.relative(dir), dir))
            return true;

        return false;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        var level = ctx.getLevel();
        var pos = ctx.getClickedPos();

        return super.getStateForPlacement(ctx)
                .setValue(NORTH, connectsTo(level, pos, Direction.NORTH))
                .setValue(SOUTH, connectsTo(level, pos, Direction.SOUTH))
                .setValue(EAST, connectsTo(level, pos, Direction.EAST))
                .setValue(WEST, connectsTo(level, pos, Direction.WEST))
                .setValue(UP, connectsTo(level, pos, Direction.UP))
                .setValue(DOWN, connectsTo(level, pos, Direction.DOWN))
                .setValue(HAS_CORNER, hasCorner(level, pos))  ;
    }

    private boolean hasCorner(LevelReader level, BlockPos pos) {

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
    protected BlockState updateShape(BlockState s, LevelReader l, ScheduledTickAccess t, BlockPos p, Direction d,
            BlockPos np, BlockState ns, RandomSource r) {
        return !this.canSurvive(s, l, p)
                ? Blocks.AIR.defaultBlockState()
                : s.setValue(PROPERTY_BY_DIRECTION.get(d),
                        this.connectsTo(l, p, d))
                        .setValue(HAS_CORNER, hasCorner(l, p));
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return QueueUtil.of(pos).clear()
                .define((queue, off) -> {

                    boolean flag = false;

                    for (var dir : Direction.values()) {
                        var offset = off.relative(dir);

                        if (level.getBlockState(offset).is(this)) {
                            queue.add(offset);
                            flag = true;
                            continue;
                        }

                        if (connectsTo(level, off, dir))
                            return QueueStatus.SUCCESS;
                    }

                    return flag ? QueueStatus.CONTINUE : QueueStatus.FAIL;

                }).run();
    }

}
