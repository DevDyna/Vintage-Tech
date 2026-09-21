package com.synergy.vintagetech.init.builder;

import com.devdyna.cakesticklib.api.RandomUtil;
import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.init.types.zItems;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.FarmlandWaterManager;

public class StickyFarmlandBlock extends FarmlandBlock {

    public StickyFarmlandBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos,
            Entity entity, double fallDistance) {

    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (itemStack.is(zTags.Items.STICKY_FARMLAND_SCRAPPABLE)) {
            if (itemStack.isDamageableItem() && !player.isCreative())
                itemStack.hurtAndBreak(1, player, hand);

            player.swing(hand);

            ParticleUtils.spawnParticlesOnBlockFaces(level, pos, ParticleTypes.SCRAPE, UniformInt.of(3, 5));

            level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 0.1F);

            level.setBlockAndUpdate(pos, Blocks.FARMLAND.defaultBlockState().setValue(FarmlandBlock.MOISTURE,
                    state.getValue(FarmlandBlock.MOISTURE)));

            if (!player.isCreative())
                popResourceFromFace(level, pos, Direction.UP, x.item(zItems.SULFUR_GOO.get()));
        }

        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        var moisture = state.getValue(MOISTURE);
        var above = pos.above();
        var crop = level.getBlockState(above);

        if (moisture > 0)
            if (!crop.is(zTags.Blocks.DENY_STICKY_FARMLAND_BOOST))
                if (crop.getBlock() instanceof BonemealableBlock meal)
                    if (crop.isRandomlyTicking())
                        if (meal.isValidBonemealTarget(level, above, crop))
                            if (RandomUtil.chance(level, 0.05f * moisture))
                                meal.performBonemeal(level, level.getRandom(), above, crop);

        var nearWater = false;

        var area = BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4)).iterator();

        while (area.hasNext()) {
            var waterPos = area.next();

            if (state.canBeHydrated(level, pos, level.getFluidState(waterPos), waterPos)) {
                nearWater = true;
                break;
            }
        }

        if (!nearWater)
            nearWater = FarmlandWaterManager.hasBlockWaterTicket(level, pos);

        if (nearWater || level.isRainingAt(pos.above()))
            level.setBlock(pos, state.setValue(MOISTURE, 7), 2);
        else {

            if (moisture > 0)
                level.setBlock(pos, state.setValue(MOISTURE, moisture - 1), 2);
            else {

                level.playSound(null, pos, SoundEvents.GENERIC_BURN, SoundSource.BLOCKS,
                        0.4F, 2.0F + level.getRandom().nextFloat() * 0.4F);

                turnToDirt(null, state, level, pos);

                if (crop.getBlock() instanceof CropBlock || crop.getBlock() instanceof BonemealableBlock
                        || crop.getBlock() instanceof VegetationBlock)
                    if (!crop.is(zTags.Blocks.DENY_STICKY_FARMLAND_BURN_ON_DEHYDRATATION))
                        level.setBlockAndUpdate(above, Blocks.FIRE.defaultBlockState());
            }

        }

    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity,
            InsideBlockEffectApplier effectApplier, boolean isPrecise) {

        if (entity instanceof LivingEntity)
            entity.makeStuckInBlock(
                    state,
                    new Vec3(0.85, 0.4, 0.85));

        super.entityInside(state, level, pos, entity, effectApplier, isPrecise);
    }
}