package com.synergy.vintagetech.api.datagen.builders.deposits;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class DepositFeature extends Feature<DepositConfiguration> {

    public DepositFeature() {
        super(DepositConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<DepositConfiguration> ctx) {

        var level = ctx.level();
        var origin = ctx.origin();
        var random = ctx.random();
        var config = ctx.config();

        int radius = config.radius();

        var success = false;

        for (int x = -radius; x <= radius; x++) 
            for (int z = -radius; z <= radius; z++) {

                if (x * x + z * z > radius * radius)
                    continue;

                var offset = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, origin.offset(x, 0, z));

                if (!config.replace())
                    if (!level.getBlockState(offset).isAir())
                        continue;

                level.setBlock(offset, config.getBlock().getState(level, random, offset), Block.UPDATE_ALL);
                success = true;
            }
        

        return success;
    }
}