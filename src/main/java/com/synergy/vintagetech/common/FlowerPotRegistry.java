package com.synergy.vintagetech.common;

import java.util.concurrent.CompletableFuture;

import com.synergy.vintagetech.api.factories.trees.TreeFactory;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

public class FlowerPotRegistry {
    public static void register(final FMLCommonSetupEvent e) {

        TreeFactory.getAll().forEach(s -> addFlowerPot(e, s.sapling(), s.pottedSapling()));

    }

    // TODO api : move to RegistryUtils

    public static CompletableFuture<Void> addFlowerPot(FMLCommonSetupEvent e, DeferredHolder<Block, Block> sapling,
            DeferredBlock<? extends Block> potted) {
        return e.enqueueWork(() -> {

            ((FlowerPotBlock) Blocks.FLOWER_POT)
                    .addPlant(sapling.getId(), potted);

        });
    }
}
