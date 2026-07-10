package com.synergy.vintagetech.init.builder.treetap;

import javax.annotation.Nullable;

import com.devdyna.cakesticklib.api.aspect.templates.TickingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class TreeTapBlock extends TickingBlock {

    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

    public TreeTapBlock(Properties p) {
        super(p);
    }

    // TODO IMP : tree tap model and properties

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(FACING);
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var facing = state.getValue(FACING);
        return level.getBlockState(pos.relative(facing.getOpposite())).isFaceSturdy(level, pos, facing);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
        var state = this.defaultBlockState();

        for (Direction dir : ctx.getNearestLookingDirections()) {
            if (dir.getAxis().isHorizontal()) {
                state = state.setValue(FACING, dir.getOpposite());
                if (canSurvive(state, ctx.getLevel(), ctx.getClickedPos()))
                    return state;
            }
        }

        return null;
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
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        var opposite = state.getValue(FACING).getOpposite();

        if (RandomUtil.chance(level, 0.05))
            if (level.getBlockState(pos.below()).getBlock() instanceof EvaporationBasinBlock)
                level.addParticle(ParticleTypes.DRIPPING_DRIPSTONE_WATER,
                        pos.getX() + 0.5 + 0.09 * opposite.getStepX(),
                        pos.getY(),
                        pos.getZ() + 0.5 + 0.09 * opposite.getStepZ(),
                        0.0, 0.0, 0.0);
    }

    @Override
    public @org.jspecify.annotations.Nullable BlockEntity newBlockEntity(BlockPos worldPosition,
            BlockState blockState) {
        return new TreeTapBE(worldPosition, blockState);
    }

}
