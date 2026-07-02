package com.synergy.vintagetech.init.builder.plants;

import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class Lavender extends FlowerBlock {

    public Lavender(Holder<MobEffect> eff, float sec, BlockBehaviour.Properties p) {
        super(eff, sec, p
                .noCollision()
                .mapColor(MapColor.COLOR_PINK)
                .instabreak()
                .sound(SoundType.SWEET_BERRY_BUSH)
                .offsetType(BlockBehaviour.OffsetType.XZ)
                .pushReaction(PushReaction.DESTROY));
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(zTags.Blocks.SUPPORT_LAVENDER);
    }

}