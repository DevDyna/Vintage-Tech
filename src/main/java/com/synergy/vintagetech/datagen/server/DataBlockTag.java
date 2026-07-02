package com.synergy.vintagetech.datagen.server;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.concurrent.CompletableFuture;

import com.synergy.vintagetech.init.types.zBlocks;
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

                tag(BlockTags.GROWS_CROPS).add(zBlocks.SOIL.get());
                tag(BlockTags.SUPPORTS_CROPS).add(zBlocks.SOIL.get());

                tag(zTags.Blocks.SUPPORT_CAVE_WHEAT_PLANT)
                                .addTags(BlockTags.SUPPORTS_CROPS,
                                                BlockTags.BASE_STONE_OVERWORLD);

                tag(zTags.Blocks.SUPPORT_HEMP_PLANT)
                                .addTags(BlockTags.SUPPORTS_CROPS)
                                .add(zBlocks.HEMP.get());

                tag(zTags.Blocks.SUPPORT_SOYBEANS_PLANT)
                                .addTags(BlockTags.SUPPORTS_CROPS);

                tag(zTags.Blocks.SUPPORT_LAVENDER)
                                .addTags(
                                                BlockTags.SUPPORTS_DRY_VEGETATION,
                                                Tags.Blocks.SANDSTONE_BLOCKS,
                                                BlockTags.SUPPORTS_VEGETATION);

                tag(zTags.Blocks.TRANSMISSION)
                                .add(
                                                zBlocks.AXLE.get(),
                                                zBlocks.JUNCTION.get(),
                                                zBlocks.GEARSHIFT.get());

                tag(zTags.Blocks.GENERATOR)
                                .add(
                                                zBlocks.STEAM_ENGINE.get(),
                                                zBlocks.CREATIVE_ENGINE.get());

                tag(zTags.Blocks.CONSUMER)
                                .add(
                                                zBlocks.SAW.get(),
                                                zBlocks.FAN.get());

                tag(BlockTags.MINEABLE_WITH_AXE)
                                .add(

                                                zBlocks.AXLE.get(),
                                                zBlocks.BASKET.get(),
                                                zBlocks.CRUSHING_TUB.get(),
                                                zBlocks.DRYING_RACK.get(),
                                                zBlocks.JUNCTION.get(),
                                                zBlocks.SAW.get()

                                );

                tag(BlockTags.MINEABLE_WITH_SHOVEL)
                                .add(
                                                zBlocks.SOIL.get());

                tag(BlockTags.MINEABLE_WITH_PICKAXE)
                                .add(
                                                zBlocks.CREATIVE_ENGINE.get(),
                                                zBlocks.EVAPORATION_BASIN.get(),
                                                zBlocks.FAN.get(),
                                                zBlocks.GEARSHIFT.get(),
                                                zBlocks.SAW.get(),
                                                zBlocks.STEAM_ENGINE.get());


                        tag(BlockTags.SUPPORTS_VEGETATION)
                                .add(
                                                zBlocks.SOIL.get());

        }

}