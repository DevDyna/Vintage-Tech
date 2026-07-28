package com.synergy.vintagetech.datagen.server;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.concurrent.CompletableFuture;

import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zItems;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

public class DataItemTag extends ItemTagsProvider {

        public DataItemTag(PackOutput o, CompletableFuture<HolderLookup.Provider> p,
                        CompletableFuture<TagLookup<Block>> b) {
                super(o, p, MODULE_ID);
        }

        @Override
        protected void addTags(Provider p) {

                tag(ItemTags.RABBIT_FOOD).add(zBlocks.LAVENDER.get().asItem());

                tag(Tags.Items.CROPS).add(
                                zItems.ALOE.get(),
                                zItems.HEMP.get(),
                                zItems.BLUEBERRIES.get(),
                                zItems.SOYBEANS.get());

                tag(ItemTags.FLOWERS).add(
                                zBlocks.LAVENDER.get().asItem());
                tag(ItemTags.BEE_FOOD).add(
                                zBlocks.LAVENDER.get().asItem());

                tag(ItemTags.LOGS)
                                .add(

                                                zBlocks.IRONWOOD_LOG.get().asItem(),
                                                zBlocks.STRIPPED_IRONWOOD_LOG.get().asItem(),
                                                zBlocks.IRONWOOD_WOOD.get().asItem(),
                                                zBlocks.STRIPPED_IRONWOOD_WOOD.get().asItem()

                                );

                tag(ItemTags.SAPLINGS)
                                .add(zBlocks.IRONWOOD_SAPLING.get().asItem());

                tag(ItemTags.LOGS_THAT_BURN)
                                .add(zBlocks.IRONWOOD_LOG.get().asItem(),
                                                zBlocks.STRIPPED_IRONWOOD_LOG.get().asItem(),
                                                zBlocks.IRONWOOD_WOOD.get().asItem(),
                                                zBlocks.STRIPPED_IRONWOOD_WOOD.get().asItem());

                tag(Tags.Items.STRIPPED_LOGS)
                                .add(
                                                zBlocks.STRIPPED_IRONWOOD_LOG.get().asItem());

                tag(Tags.Items.STRIPPED_WOODS)
                                .add(
                                                zBlocks.STRIPPED_IRONWOOD_WOOD.get().asItem());

                tag(ItemTags.LEAVES)
                                .add(zBlocks.IRONWOOD_LEAVES.get().asItem());

                tag(ItemTags.PLANKS)
                                .add(zBlocks.IRONWOOD_PLANKS.get().asItem());

                tag(ItemTags.WOODEN_SLABS)
                                .add(zBlocks.IRONWOOD_SLAB.get().asItem());

                tag(ItemTags.SLABS)
                                .add(zBlocks.IRONWOOD_SLAB.get().asItem());

                tag(ItemTags.WOODEN_STAIRS)
                                .add(zBlocks.IRONWOOD_STAIRS.get().asItem());

                tag(ItemTags.STAIRS)
                                .add(zBlocks.IRONWOOD_STAIRS.get().asItem());

                tag(zTags.Items.IRONWOOD_LOGS)
                                .add(zBlocks.IRONWOOD_LOG.get().asItem(),
                                                zBlocks.STRIPPED_IRONWOOD_LOG.get().asItem(),
                                                zBlocks.IRONWOOD_WOOD.get().asItem(),
                                                zBlocks.STRIPPED_IRONWOOD_WOOD.get().asItem());

                tag(zTags.Items.BEAM_LOGS_NORMAL).add(
                                zBlocks.OAK_BEAM.get().asItem(),
                                zBlocks.SPRUCE_BEAM.get().asItem(),
                                zBlocks.BIRCH_BEAM.get().asItem(),
                                zBlocks.JUNGLE_BEAM.get().asItem(),
                                zBlocks.ACACIA_BEAM.get().asItem(),
                                zBlocks.DARK_OAK_BEAM.get().asItem(),
                                zBlocks.MANGROVE_BEAM.get().asItem(),
                                zBlocks.CHERRY_BEAM.get().asItem(),
                                zBlocks.PALE_OAK_BEAM.get().asItem());

                tag(zTags.Items.STONE_SLABS)
                                .add(
                                                Items.STONE_SLAB,
                                                Items.ANDESITE_SLAB,
                                                Items.DIORITE_SLAB,
                                                Items.GRANITE_SLAB,
                                                Items.TUFF_SLAB);

                tag(Tags.Items.FOODS_SOUP)
                                .add(
                                                zItems.BLUEBERRIES_SOUP.get(),
                                                zItems.SWEET_BERRIES_SOUP.get(),
                                                zItems.GLOW_BERRIES_SOUP.get(),
                                                zItems.IRONBERRIES_SOUP.get(),
                                                zItems.BLUEBERRIES.get(),
                                                zItems.BLUEBERRIES_MUFFIN.get(),
                                                zItems.IRONBERRIES.get()
                                );

                tag(Tags.Items.SLIME_BALLS).add(zItems.GLUE.get());
                // tag(Tags.Items.FOODS).add(zItems.BLUEBERRIES_SOUP.get());

        }

}