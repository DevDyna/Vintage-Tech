package com.synergy.vintagetech.init.builder.cheese;

import javax.annotation.Nullable;

import com.devdyna.cakesticklib.api.RandomUtil;
import com.synergy.vintagetech.api.factories.cheese.BaseCheeseBlock;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class SealedCheeseBlock extends BaseCheeseBlock {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    public static final int MAX_AGE = BlockStateProperties.MAX_AGE_2;

    public SealedCheeseBlock(Properties p) {
        super(p);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return defaultBlockState()
                .setValue(PIECES, 4)
                .setValue(AGE, 0)
                .setValue(FACING, c.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(AGE, PIECES, FACING);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < MAX_AGE;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

        if (level.canSeeSky(pos))
            return;

        if (level.getEffectiveSkyBrightness(pos) > 5)
            return;

        var pieces = state.getValue(PIECES);

        var chance = 25f + 5f * pieces;

        if (level.getBlockState(pos.below()).is(zTags.Blocks.CHEESE_BOOSTER))
            chance *= 0.75f;

        if (pieces == 4 || pieces == 8)
            chance *= 0.75f;

        if (RandomUtil.chance(level, chance))
            return;

        if (RandomUtil.chance(level, 0.5f))
            level.playSound(null, pos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1, 0);

        level.setBlockAndUpdate(pos, state.setValue(AGE, state.getValue(AGE) + 1));

    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (itemStack.is(zTags.Items.CHEESE_UNSEALER)) {
            level.setBlockAndUpdate(pos,
                    getStage(state.getValue(AGE)).get()
                            .defaultBlockState()
                            .setValue(PIECES, state.getValue(PIECES))
                            .setValue(FACING, state.getValue(FACING)));

            return InteractionResult.SUCCESS_SERVER;
        }

        if (itemStack.is(this.asItem()) && state.getValue(PIECES) == 4 && state.getValue(AGE) == 0) {

            if (!player.isCreative())
                itemStack.shrink(1);

            level.setBlockAndUpdate(pos, state.setValue(PIECES, 8));

            return InteractionResult.SUCCESS_SERVER;
        }

        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        double x = pos.getX() + 0.5;
        double y = pos.getY() + (state.getValue(PIECES) > 3 ? 1 : 0.5);
        double z = pos.getZ() + 0.5;

        if (level.canSeeSky(pos))
            return;

        if (level.getEffectiveSkyBrightness(pos) > 5)
            return;

        if (state.getValue(AGE) < MAX_AGE) {
            if (random.nextInt(6) == 0)
                level.addParticle(ParticleTypes.WHITE_SMOKE, x, y, z, 0.0, 0.02, 0.0);
            if (random.nextInt(6) == 0)
                level.addParticle(ParticleTypes.WHITE_ASH, x, y, z, 0.0, 0.02, 0.0);
        } else {
            if (random.nextInt(6) == 0)
                level.addParticle(ParticleTypes.DUST_PLUME, x, y, z, 0.0, 0.02, 0.0);
        }
    }

}
