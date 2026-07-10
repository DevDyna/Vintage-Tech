package com.synergy.vintagetech.init.builder.treetap;

import javax.annotation.Nullable;

import com.devdyna.cakesticklib.api.aspect.templates.TickingBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RotatedPillarBlock;
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

    //TODO IMP : tree tap model and properties

   

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
        var related = level.getBlockState(pos.relative(state.getValue(FACING).getOpposite()));
        return related.is(BlockTags.LOGS) && related.getBlock() instanceof RotatedPillarBlock
                && related.getValue(RotatedPillarBlock.AXIS).isVertical();
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
        var state = this.defaultBlockState();

        for (Direction dir : ctx.getNearestLookingDirections()) {
            if (dir.getAxis().isHorizontal()) {
                state = state.setValue(FACING, dir.getOpposite());
                if (state.canSurvive(ctx.getLevel(), ctx.getClickedPos()))
                    return state;
            }
        }

        return null;
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos,
            Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        return directionToNeighbour.getOpposite() == state.getValue(FACING)
                && !state.canSurvive(level, pos)
                        ? Blocks.AIR.defaultBlockState()
                        : state;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        var dir = state.getValue(FACING);
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;

        var opposite = dir.getOpposite();

        level.addParticle(ParticleTypes.END_ROD,
                x + 0.27 * opposite.getStepX(),
                y + 0.22,
                z + 0.27 * opposite.getStepZ(),
                0.0, 0.0, 0.0);
    }

    @Override
    public @org.jspecify.annotations.Nullable BlockEntity newBlockEntity(BlockPos worldPosition,
            BlockState blockState) {
        return new TreeTapBE(worldPosition, blockState);
    }

}
