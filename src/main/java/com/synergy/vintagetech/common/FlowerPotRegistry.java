package com.synergy.vintagetech.common;

import com.synergy.vintagetech.init.types.zBlocks;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

public class FlowerPotRegistry {
      public static void common(final FMLCommonSetupEvent e) {
        e.enqueueWork(() -> {

            ((FlowerPotBlock) Blocks.FLOWER_POT)
                    .addPlant(zBlocks.IRONWOOD_SAPLING.getId(),
                            zBlocks.POTTED_IRONWOOD_SAPLING);

        });
    }
}
