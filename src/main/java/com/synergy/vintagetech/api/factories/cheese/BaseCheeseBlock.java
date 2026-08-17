package com.synergy.vintagetech.api.factories.cheese;

import java.util.List;
import java.util.stream.IntStream;

import javax.annotation.Nullable;

import com.synergy.vintagetech.init.types.zBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredHolder;

public abstract class BaseCheeseBlock extends Block {

    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final int MAX_PIECES = 8;
    public static final IntegerProperty PIECES = IntegerProperty.create("pieces", 1, MAX_PIECES);

    public BaseCheeseBlock(Properties p) {
        super(p);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState()
                .setValue(PIECES, 4)
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(PIECES, FACING);
    }

    public static final VoxelShape NE_D = Block.box(8, 0, 1, 15, 8, 8);
    public static final VoxelShape NW_D = Block.box(1, 0, 1, 8, 8, 8);
    public static final VoxelShape SE_D = Block.box(1, 0, 8, 8, 8, 15);
    public static final VoxelShape SW_D = Block.box(8, 0, 8, 15, 8, 15);

    public static final VoxelShape NE_U = Block.box(8, 8, 1, 15, 16, 8);
    public static final VoxelShape NW_U = Block.box(1, 8, 1, 8, 16, 8);
    public static final VoxelShape SE_U = Block.box(1, 8, 8, 8, 16, 15);
    public static final VoxelShape SW_U = Block.box(8, 8, 8, 15, 16, 15);

    private VoxelShape getPieceShape(Direction facing, int piece) {
        return switch (facing) {
            case NORTH -> switch (piece) {
                case 1 -> SW_D;
                case 2 -> SE_D;
                case 3 -> NW_D;
                case 4 -> NE_D;
                case 5 -> SW_U;
                case 6 -> SE_U;
                case 7 -> NW_U;
                case 8 -> NE_U;
                default -> Shapes.empty();
            };

            case EAST -> switch (piece) {
                case 1 -> SE_D;
                case 2 -> NW_D;
                case 3 -> NE_D;
                case 4 -> SW_D;
                case 5 -> SE_U;
                case 6 -> NW_U;
                case 7 -> NE_U;
                case 8 -> SW_U;
                default -> Shapes.empty();
            };

            case SOUTH -> switch (piece) {
                case 1 -> NW_D;
                case 2 -> NE_D;
                case 3 -> SW_D;
                case 4 -> SE_D;
                case 5 -> NW_U;
                case 6 -> NE_U;
                case 7 -> SW_U;
                case 8 -> SE_U;
                default -> Shapes.empty();
            };

            case WEST -> switch (piece) {
                case 1 -> NE_D;
                case 2 -> SW_D;
                case 3 -> SE_D;
                case 4 -> NW_D;
                case 5 -> NE_U;
                case 6 -> SW_U;
                case 7 -> SE_U;
                case 8 -> NW_U;
                default -> Shapes.empty();
            };

            default -> Shapes.empty();
        };
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        var shape = Shapes.empty();

        for (var piece = 1; piece <= state.getValue(PIECES); piece++)
            shape = Shapes.or(shape, getPieceShape(state.getValue(FACING), piece));

        return shape.optimize();
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos,
            Direction neiDir, BlockPos neiPos, BlockState neiState, RandomSource random) {

        return (neiDir.getAxis().isVertical()
                && !state.canSurvive(level, pos))
                        ? Blocks.AIR.defaultBlockState()
                        : super.updateShape(state, level, ticks, pos, neiDir, neiPos, neiState, random);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).isSolid() || level.getBlockState(pos.above()).is(zBlocks.ROPE);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }

    public static List<DeferredHolder<Block, Block>> ALL_CHEESES = List.of(
            zBlocks.FRESH_CHEESE,
            zBlocks.MATURED_CHEESE,
            zBlocks.AGED_CHEESE);

    public static DeferredHolder<Block, Block> getStage(int age) {
        return ALL_CHEESES.get(age);
    }

    public static int getAge(Block block) {
        return IntStream.range(0, ALL_CHEESES.size())
                .filter(i -> ALL_CHEESES.get(i).get() == block)
                .findFirst()
                .orElse(-1);
    }
}