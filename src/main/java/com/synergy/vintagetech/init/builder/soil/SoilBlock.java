package com.synergy.vintagetech.init.builder.soil;

import com.devdyna.cakesticklib.api.ItemLogisticUtils;
import com.devdyna.cakesticklib.api.factories.plants.VanillaPlants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SoilBlock extends FarmlandBlock {

    public SoilBlock(Properties p) {
        super(p.randomTicks());
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rnd) {

        if (state.getValue(MOISTURE) < 7)
            level.setBlock(pos, state.setValue(MOISTURE, state.getValue(MOISTURE) + 1), 2);

        if (level.getBlockState(pos.above()).getBlock() instanceof BonemealableBlock meal)
            for (int i = 0; i < Math.max(state.getValue(MOISTURE), 1) / 2; i++)
                if (meal.isValidBonemealTarget(level, pos.above(), level.getBlockState(pos.above())))
                    meal.performBonemeal(level, rnd, pos.above(), level.getBlockState(pos.above()));

        VanillaPlants.checkReplant(level, pos.above(), null, null)
                .forEach(i -> ItemLogisticUtils.createLazyItemEntity(i, level, pos.above(), 1200, true));

    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rnd) {
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos,
            Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {

        if (directionToNeighbour == Direction.UP && level instanceof ServerLevel server)
            VanillaPlants.checkReplant(server, neighbourPos, null, null)
                    .forEach(i -> ItemLogisticUtils.createLazyItemEntity(i, server, neighbourPos, 1200, true));

        return super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos,
            Entity entity, double fallDistance) {
        entity.causeFallDamage(fallDistance, 1.0F, entity.damageSources().fall());
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.block();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState();
    }

}
