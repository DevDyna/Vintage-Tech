package com.synergy.vintagetech.init.builder.plants;

import com.devdyna.cakesticklib.api.factories.plants.builder.BaseShortCropBlock;
import com.synergy.vintagetech.init.types.zItems;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class SoyBeans extends BaseShortCropBlock {

    public SoyBeans(Properties p) {
        super(p.mapColor(MapColor.COLOR_BROWN));
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.SOYBEANS.get();
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(zTags.Blocks.SUPPORT_SOYBEANS_PLANT);
    }

}