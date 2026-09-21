package com.synergy.vintagetech.init.builder;

import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zParticles;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockState;

//TODO IMP : JEI category/info?
public class SulfurGooItem extends Item {

    public SulfurGooItem(Properties p) {
        super(p);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        var level = ctx.getLevel();
        var pos = ctx.getClickedPos();
        var face = ctx.getClickedFace();
        var offset = pos.relative(face.getOpposite());

        var clicked = level.getBlockState(pos);
        var below = level.getBlockState(offset);

        var player = ctx.getPlayer();
        var hand = ctx.getHand();
        var item = ctx.getItemInHand();

        if (clicked.getBlock() instanceof FarmlandBlock && !(clicked.getBlock() instanceof StickyFarmlandBlock))
            return success(level, pos, clicked, player, hand, item);

        if (below.getBlock() instanceof FarmlandBlock && !(below.getBlock() instanceof StickyFarmlandBlock))
            return success(level, offset, below, player, hand, item);

        return InteractionResult.PASS;
    }

    private InteractionResult success(Level level, BlockPos pos, BlockState state, Player player, InteractionHand hand,
            ItemStack item) {

        if (player != null) {
            player.swing(hand);

            if (!player.isCreative())
                item.shrink(1);

        }

        ParticleUtils.spawnParticlesOnBlockFaces(level, pos, zParticles.SULFUR_PARTICLES.get(), UniformInt.of(3, 5));

        level.playSound(player, pos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 0.1F);

        level.setBlockAndUpdate(pos, zBlocks.STICKY_FARMLAND.get().defaultBlockState()
                .setValue(FarmlandBlock.MOISTURE, state.getValue(FarmlandBlock.MOISTURE)));

        return InteractionResult.SUCCESS_SERVER;
    }

}
