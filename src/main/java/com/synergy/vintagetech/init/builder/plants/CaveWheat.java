package com.synergy.vintagetech.init.builder.plants;

import com.devdyna.cakesticklib.api.factories.plants.builder.BaseShortCropBlock;
import com.synergy.vintagetech.init.types.zItems;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class CaveWheat extends BaseShortCropBlock {

    public CaveWheat(Properties p) {
        super(p.mapColor(MapColor.COLOR_LIGHT_GRAY));
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.CAVE_WHEAT_SEEDS.get();
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(zTags.Blocks.SUPPORT_CAVE_WHEAT_PLANT);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return mayPlaceOn(level.getBlockState(pos.below()), level, pos.below());
    }

    @Override
    public int getChanceToGrow(BlockState state, ServerLevel level, BlockPos pos) {
        return super.getChanceToGrow(state, level, pos) / (level.getRawBrightness(pos, 0) < 10 ? 3 : 1);
    }

}