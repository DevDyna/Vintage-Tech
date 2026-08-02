package com.synergy.vintagetech.init.builder.cheese;

import com.devdyna.cakesticklib.api.RandomUtil;
import com.synergy.vintagetech.init.types.zBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class SealedCheeseBlock extends BaseCheeseBlock {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    public static final int MAX_AGE = BlockStateProperties.MAX_AGE_3;

    public SealedCheeseBlock(Properties p) {
        super(p);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(PIECES, 0)
                .setValue(AGE, 0));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(AGE, PIECES);
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

        if (RandomUtil.chance(level, (0.15f + 0.15f * state.getValue(PIECES))))
            return;

        level.setBlockAndUpdate(pos, state.setValue(AGE, state.getValue(AGE) + 1));

    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {

        // TODO API : jei custom waxing and scrapping
        if (itemStack.is(ItemTags.AXES)) {

            var block = (state.getValue(AGE) == MAX_AGE) ? zBlocks.AGED_CHEESE : zBlocks.PLAIN_CHEESE;

            level.setBlockAndUpdate(pos,
                    block.get().defaultBlockState()
                            .setValue(PIECES, state.getValue(PIECES)));

            return InteractionResult.SUCCESS_SERVER;
        }

        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;

        if(state.getValue(AGE) < MAX_AGE)
        if (random.nextInt(6) == 0)
            level.addParticle(ParticleTypes.WHITE_SMOKE, x, y, z, 0.0, 0.02, 0.0);

    }

}
