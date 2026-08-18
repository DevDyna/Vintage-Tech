package com.synergy.vintagetech.init.builder;

import com.devdyna.cakesticklib.api.RandomUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.FarmlandWaterManager;

public class StickyFarmlandBlock extends FarmlandBlock {

    public StickyFarmlandBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos,
            Entity entity, double fallDistance) {
        entity.causeFallDamage(fallDistance, 1.0F, entity.damageSources().fall());
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        var moisture = state.getValue(MOISTURE);
        var above = pos.above();
        var crop = level.getBlockState(above);

        if (moisture > 0)
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

        if (!nearWater && !level.isRainingAt(pos.above())) {
            if (moisture > 0)
                level.setBlock(pos, state.setValue(MOISTURE, moisture - 1), 2);

        } else if (moisture < 7)
            level.setBlock(pos, state.setValue(MOISTURE, 7), 2);

    }
}