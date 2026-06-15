package com.synergy.vintagetech.datagen.server;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.concurrent.CompletableFuture;

import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

public class DataBlockTag extends BlockTagsProvider {

        public DataBlockTag(PackOutput output, CompletableFuture<Provider> lookupProvider) {
                super(output, lookupProvider, MODULE_ID);
        }

        @Override
        protected void addTags(Provider p) {

                tag(zTags.Blocks.SAW_DENY_BREAK).addTag(Tags.Blocks.RELOCATION_NOT_SUPPORTED);
                tag(zTags.Blocks.EVAPORATION_BASIC_HEATER).add(Blocks.MAGMA_BLOCK);
                tag(zTags.Blocks.DRYING_RACK_HEATER).addTag(BlockTags.CAMPFIRES);
                tag(zTags.Blocks.MINEABLE_WITH_SAW).addTag(BlockTags.MINEABLE_WITH_AXE);

        }

}