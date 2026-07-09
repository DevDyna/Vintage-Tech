package com.synergy.vintagetech.init.builder.treetap;

import java.util.Optional;

import javax.annotation.Nullable;

import com.synergy.vintagetech.api.recipeinput.TreeTapInput;
import com.synergy.vintagetech.init.builder.evaporation_basin.EvaporationBasinBE;
import com.synergy.vintagetech.init.builder.treetap.recipe.TreeTapRecipe;
import com.synergy.vintagetech.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class TreeTapBlock extends Block {

    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

    public TreeTapBlock(Properties p) {
        super(p.randomTicks());
    }

    //TODO IMP : tree tap model and properties

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

        if (level.getBlockEntity(pos.below()) instanceof EvaporationBasinBE basin) {

            BlockState leaves = null;

            var trunk = pos.relative(state.getValue(FACING));

            if (!level.getBlockState(trunk).is(BlockTags.LOGS))
                return;

            var log = level.getBlockState(trunk);

            for (int y = 0; y < 32 && leaves == null; y++) {
                var current = trunk.above(y);

                if (!level.getBlockState(current).is(BlockTags.LOGS))
                    break;

                for (int dx = -2; dx <= 2 && leaves == null; dx++)
                    for (int dz = -2; dz <= 2 && leaves == null; dz++)
                        if (level.getBlockState(current.offset(dx, 0, dz))
                                .is(BlockTags.LEAVES)) {
                            leaves = level.getBlockState(current.offset(dx, 0, dz));
                        }

            }

            if (leaves == null)
                return;

            Optional<RecipeHolder<TreeTapRecipe>> r = level.getServer().getRecipeManager()
                    .getRecipeFor(zRecipeTypes.TREE_TAP.getType(),
                            new TreeTapInput(log, leaves), level);

            if (r.isEmpty())
                return;

            var recipe = r.get().value();
            try (var tx = Transaction.openRoot()) {
                if (basin.getFluidStorage().getResource(0).is(recipe.getFluid().create().getFluidType())
                        && basin.getFluidStorage().getAmountAsInt(0) < basin.getFluidStorage().getCapacityAsInt(0,
                                basin.getFluidStorage().getResource(0))) {
                    basin.getFluidStorage().insert(0, FluidResource.of(recipe.getFluid()), recipe.getFluid().amount(),
                            tx);
                    tx.commit();
                }
            }

        }

    }

    public static boolean isFacingTree(Level level, BlockPos pos, Direction facing) {
        var trunk = pos.relative(facing);

        if (!level.getBlockState(trunk).is(BlockTags.LOGS))
            return false;

        for (int y = 0; y < 32; y++) {
            var current = trunk.above(y);
            var state = level.getBlockState(current);

            if (!state.is(BlockTags.LOGS))
                break;

            for (int dx = -2; dx <= 2; dx++)
                for (int dz = -2; dz <= 2; dz++)
                    if (level.getBlockState(current.offset(dx, 0, dz))
                            .is(BlockTags.LEAVES))
                        return true;

        }

        return false;
    }

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

}
