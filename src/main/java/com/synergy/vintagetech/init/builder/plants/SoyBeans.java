package com.synergy.vintagetech.init.builder.plants;

import com.devdyna.cakesticklib.api.factories.plants.builder.BaseShortCropBlock;
import com.synergy.vintagetech.api.NaturalCrop;
import com.synergy.vintagetech.init.types.zItems;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.MapColor;

public class SoyBeans extends BaseShortCropBlock implements NaturalCrop {

    public SoyBeans(Properties p) {
        super(p.mapColor(MapColor.COLOR_BROWN));
        registerDefaultState(stateDefinition.any()
                .setValue(AGE, 0)
                .setValue(NATURAL, false));
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.SOYBEANS.get();
    }

    @Override
    public int getBonemealAgeIncrease(Level level) {
        return super.getBonemealAgeIncrease(level);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        // TODO IMP : maybe this is overkill
        return level.getBlockState(pos.above()).getValue(NATURAL)
                ? state.is(zTags.Blocks.SUPPORT_NATURAL_SOYBEANS_PLANT)
                : state.is(zTags.Blocks.SUPPORT_SOYBEANS_PLANT);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var soil = level.getBlockState(pos.below());
        return state.getValue(NATURAL)
                ? soil.is(zTags.Blocks.SUPPORT_NATURAL_SOYBEANS_PLANT)
                : soil.is(zTags.Blocks.SUPPORT_SOYBEANS_PLANT);
    }

  @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, NATURAL);
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlock(pos, getStateForAge(Math.min(getMaxAge(), getAge(state) +
                getBonemealAgeIncrease(level)))
                .setValue(NATURAL, state.getValue(NATURAL)), 2);
    }

    @Override
    public void replant(Context ctx) {
        var level = ctx.level();
        var pos = ctx.pos();

        if (level.getBlockState(pos).getValue(NATURAL)) {
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            return;
        }
        super.replant(ctx);
    }

}