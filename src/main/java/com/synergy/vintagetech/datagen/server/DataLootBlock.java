package com.synergy.vintagetech.datagen.server;

import java.util.*;

import com.synergy.vintagetech.init.types.zBlocks;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister.Blocks;

public class DataLootBlock extends BlockLootSubProvider {

        public DataLootBlock(HolderLookup.Provider l) {
                super(Set.of(), FeatureFlags.DEFAULT_FLAGS, l);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
                return getBlocks(zBlocks.zBlock, zBlocks.zBlockItem);
        }

        @Override
        protected void generate() {
                getBlocks(zBlocks.zBlockItem).forEach(this::dropSelf);
        }

        // TODO move to api
        public List<Block> getBlocks(Blocks... blocks) {
                List<Block> result = new ArrayList<>();
                List.of(blocks).forEach(t -> result.addAll(t.getEntries()
                                .stream()
                                .map(DeferredHolder::get)
                                .toList()));
                return result;
        }

}
