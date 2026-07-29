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

                tag(BlockTags.MINEABLE_WITH_AXE)
                                .add(

                                                zBlocks.AXLE.get(),
                                                zBlocks.BASKET.get(),
                                                zBlocks.CRUSHING_TUB.get(),
                                                zBlocks.DRYING_RACK.get(),
                                                zBlocks.JUNCTION.get(),
                                                zBlocks.SAW.get(),
                                                zBlocks.MECHANICAL_FARMLAND.get(),
                                                zBlocks.CENTRIFUGE.get(),

                                                zBlocks.OAK_BEAM.get(),
                                                zBlocks.SPRUCE_BEAM.get(),
                                                zBlocks.BIRCH_BEAM.get(),
                                                zBlocks.JUNGLE_BEAM.get(),
                                                zBlocks.ACACIA_BEAM.get(),
                                                zBlocks.DARK_OAK_BEAM.get(),
                                                zBlocks.MANGROVE_BEAM.get(),
                                                zBlocks.CHERRY_BEAM.get(),
                                                zBlocks.PALE_OAK_BEAM.get(),

                                                zBlocks.IRONWOOD_SAPLING.get(),
                                                zBlocks.IRONWOOD_PLANKS.get(),
                                                zBlocks.IRONWOOD_SLAB.get(),
                                                zBlocks.IRONWOOD_STAIRS.get()

                                )

                                .addTags(zTags.Blocks.IRONWOOD_LOGS);

                tag(BlockTags.MINEABLE_WITH_SHOVEL)
                                .add(
                                                zBlocks.MECHANICAL_FARMLAND.get());

                tag(BlockTags.MINEABLE_WITH_HOE)
                                .add(
                                                zBlocks.IRONWOOD_LEAVES.get());

                tag(BlockTags.MINEABLE_WITH_PICKAXE)
                                .add(
                                                zBlocks.CREATIVE_ENGINE.get(),
                                                zBlocks.EVAPORATION_BASIN.get(),
                                                zBlocks.FAN.get(),
                                                zBlocks.GEARSHIFT.get(),
                                                zBlocks.CLUTCH.get(),
                                                zBlocks.SAW.get(),
                                                zBlocks.WINDMILL.get(),
                                                zBlocks.TREE_TAP.get(),
                                                zBlocks.MILLSTONE.get());

                tag(zTags.Blocks.SAW_DENY_BREAK).addTag(Tags.Blocks.RELOCATION_NOT_SUPPORTED);
                tag(zTags.Blocks.EVAPORATION_BASIC_HEATER).add(Blocks.MAGMA_BLOCK);
                tag(zTags.Blocks.DRYING_RACK_HEATER).addTag(BlockTags.CAMPFIRES);
                tag(zTags.Blocks.MINEABLE_WITH_SAW).addTag(BlockTags.MINEABLE_WITH_AXE);

                tag(BlockTags.GROWS_CROPS).add(zBlocks.MECHANICAL_FARMLAND.get());
                tag(BlockTags.SUPPORTS_CROPS).add(zBlocks.MECHANICAL_FARMLAND.get());

                tag(zTags.Blocks.SUPPORT_CAVE_WHEAT_PLANT)
                                .addTags(BlockTags.SUPPORTS_CROPS,
                                                BlockTags.BASE_STONE_OVERWORLD,
                                                BlockTags.SUPPORTS_AZALEA);

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

                tag(zTags.Blocks.SUPPORT_ALOE)
                                .addTags(
                                                BlockTags.SUPPORTS_DRY_VEGETATION,
                                                BlockTags.SUPPORTS_VEGETATION);

                tag(zTags.Blocks.SUPPORT_BLUEBERRY)
                                .addTags(BlockTags.SUPPORTS_VEGETATION);

                tag(zTags.Blocks.TRANSMISSION)
                                .add(
                                                zBlocks.AXLE.get(),
                                                zBlocks.JUNCTION.get(),
                                                zBlocks.CLUTCH.get(),
                                                zBlocks.GEARSHIFT.get());

                tag(zTags.Blocks.GENERATOR)
                                .add(
                                                zBlocks.WINDMILL.get(),
                                                zBlocks.CREATIVE_ENGINE.get());

                tag(zTags.Blocks.CONSUMER)
                                .add(
                                                zBlocks.SAW.get(),
                                                zBlocks.FAN.get());

                tag(BlockTags.SUPPORTS_VEGETATION)
                                .add(zBlocks.MECHANICAL_FARMLAND.get());

                tag(BlockTags.SUPPORTS_BIG_DRIPLEAF)
                                .add(zBlocks.MECHANICAL_FARMLAND.get());

                tag(Tags.Blocks.FLOWERS)
                                .add(zBlocks.LAVENDER.get());

                tag(BlockTags.FLOWERS)
                                .add(zBlocks.LAVENDER.get());

                tag(BlockTags.BEE_ATTRACTIVE)
                                .add(zBlocks.LAVENDER.get(), zBlocks.BLUEBERRY_BUSH.get());

                tag(BlockTags.MAINTAINS_FARMLAND)
                                .add(
                                                zBlocks.SOYBEANS.get(),
                                                zBlocks.HEMP.get());

                tag(BlockTags.CROPS)
                                .add(
                                                zBlocks.SOYBEANS.get(),
                                                zBlocks.ALOE_PLANT.get(),
                                                zBlocks.BLUEBERRY_BUSH.get(),
                                                zBlocks.HEMP.get(),
                                                zBlocks.CAVE_WHEAT.get());

                tag(zTags.Blocks.TREE_TAP_LEAVES)
                                .addTags(BlockTags.LEAVES, BlockTags.WART_BLOCKS);

                tag(zTags.Blocks.TREE_TAP_LOGS)
                                .addTags(BlockTags.LOGS, Tags.Blocks.NATURAL_LOGS);

                tag(zTags.Blocks.SAW_GENERATOR_BLOCKS)
                                .add(Blocks.STONE, Blocks.COBBLESTONE);

                tag(BlockTags.CLIMBABLE)
                                .add(zBlocks.ROPE.get());

                tag(zTags.Blocks.ROPE_IGNORE_CONNECTION)
                                .add(
                                                zBlocks.CRUSHING_TUB.get(),
                                                zBlocks.EVAPORATION_BASIN.get(),
                                                Blocks.BARRIER,
                                                Blocks.JACK_O_LANTERN,
                                                Blocks.MELON)
                                .addTags(
                                                Tags.Blocks.PUMPKINS,
                                                BlockTags.SHULKER_BOXES);

                tag(zTags.Blocks.WOODEN_BEAMS)
                                .add(
                                                zBlocks.OAK_BEAM.get(),
                                                zBlocks.SPRUCE_BEAM.get(),
                                                zBlocks.BIRCH_BEAM.get(),
                                                zBlocks.JUNGLE_BEAM.get(),
                                                zBlocks.ACACIA_BEAM.get(),
                                                zBlocks.DARK_OAK_BEAM.get(),
                                                zBlocks.MANGROVE_BEAM.get(),
                                                zBlocks.CHERRY_BEAM.get(),
                                                zBlocks.PALE_OAK_BEAM.get());

                tag(zTags.Blocks.STRIPPED_BEAMS)// TODO IMP : Stripped beams
                                .add();

                tag(zTags.Blocks.IRONWOOD_LOGS)
                                .add(
                                                zBlocks.IRONWOOD_LOG.get(),
                                                zBlocks.IRONWOOD_WOOD.get(),
                                                zBlocks.STRIPPED_IRONWOOD_LOG.get(),
                                                zBlocks.STRIPPED_IRONWOOD_WOOD.get()

                                );

                tag(BlockTags.LOGS)
                                .add(zBlocks.IRONWOOD_LOG.get());

                tag(BlockTags.SAPLINGS)
                                .add(zBlocks.IRONWOOD_SAPLING.get());

                tag(BlockTags.LOGS_THAT_BURN)
                                .addTag(zTags.Blocks.IRONWOOD_LOGS);

                tag(Tags.Blocks.STRIPPED_LOGS)
                                .add(zBlocks.STRIPPED_IRONWOOD_LOG.get());

                tag(Tags.Blocks.STRIPPED_WOODS)
                                .add(zBlocks.STRIPPED_IRONWOOD_WOOD.get());

                tag(BlockTags.LEAVES)
                                .add(zBlocks.IRONWOOD_LEAVES.get());

                tag(BlockTags.PLANKS)
                                .add(zBlocks.IRONWOOD_PLANKS.get());

                tag(BlockTags.WOODEN_SLABS)
                                .add(zBlocks.IRONWOOD_SLAB.get());
                tag(BlockTags.SLABS)
                                .add(zBlocks.IRONWOOD_SLAB.get());

                tag(BlockTags.WOODEN_STAIRS)
                                .add(zBlocks.IRONWOOD_STAIRS.get());

                tag(BlockTags.STAIRS)
                                .add(zBlocks.IRONWOOD_STAIRS.get());

                tag(zTags.Blocks.SUPPORT_NATURAL_HEMP_PLANT)
                                .add(Blocks.GRASS_BLOCK)
                                .addTag(zTags.Blocks.SUPPORT_HEMP_PLANT);

                tag(zTags.Blocks.SUPPORT_NATURAL_SOYBEANS_PLANT)
                                .add(Blocks.GRASS_BLOCK)
                                .addTag(zTags.Blocks.SUPPORT_SOYBEANS_PLANT);

                tag(zTags.Blocks.CRUCIBLE_HEAT_SOURCES)
                                .addTags(BlockTags.FIRE, BlockTags.CAMPFIRES);

        }

}