package com.synergy.vintagetech.datagen.server;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.setup.registry.LibTags;
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

                tag(zTags.Items.BEAM_NORMAL).add(
                                zBlocks.OAK_BEAM.get().asItem(),
                                zBlocks.SPRUCE_BEAM.get().asItem(),
                                zBlocks.BIRCH_BEAM.get().asItem(),
                                zBlocks.JUNGLE_BEAM.get().asItem(),
                                zBlocks.ACACIA_BEAM.get().asItem(),
                                zBlocks.DARK_OAK_BEAM.get().asItem(),
                                zBlocks.MANGROVE_BEAM.get().asItem(),
                                zBlocks.CHERRY_BEAM.get().asItem(),
                                zBlocks.PALE_OAK_BEAM.get().asItem(),
                                zBlocks.BAMBOO_BEAM.get().asItem());

                tag(zTags.Items.BEAM_STRIPPED).add(
                                zBlocks.STRIPPED_OAK_BEAM.get().asItem(),
                                zBlocks.STRIPPED_SPRUCE_BEAM.get().asItem(),
                                zBlocks.STRIPPED_BIRCH_BEAM.get().asItem(),
                                zBlocks.STRIPPED_JUNGLE_BEAM.get().asItem(),
                                zBlocks.STRIPPED_ACACIA_BEAM.get().asItem(),
                                zBlocks.STRIPPED_DARK_OAK_BEAM.get().asItem(),
                                zBlocks.STRIPPED_MANGROVE_BEAM.get().asItem(),
                                zBlocks.STRIPPED_CHERRY_BEAM.get().asItem(),
                                zBlocks.STRIPPED_PALE_OAK_BEAM.get().asItem(),
                                zBlocks.STRIPPED_BAMBOO_BEAM.get().asItem());

                tag(zTags.Items.BEAMS).addTags(zTags.Items.BEAM_NORMAL, zTags.Items.BEAM_STRIPPED);

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
                                                zItems.IRONBERRIES.get());

                tag(Tags.Items.SLIME_BALLS).add(zItems.GLUE.get());

                tag(Tags.Items.FOODS_RAW_MEAT).add(zItems.TOFU.get());
                tag(Tags.Items.FOODS_RAW_FISH).add(zItems.TOFU.get());

                tag(zTags.Items.RECYCLE_COPPER_1).add(
                                Items.COPPER_SHOVEL,
                                Items.COPPER_SPEAR)
                                .addTags(LibTags.Items.COPPER_PLATE,
                                                LibTags.Items.COPPER_COIL,
                                                LibTags.Items.COPPER_FOIL,
                                                LibTags.Items.COPPER_DUST);

                tag(zTags.Items.RECYCLE_COPPER_2).add(
                                Items.COPPER_HOE,
                                Items.COPPER_SWORD,
                                Items.COPPER_DOOR);

                tag(zTags.Items.RECYCLE_COPPER_3).add(
                                Items.COPPER_PICKAXE,
                                Items.COPPER_AXE);

                tag(zTags.Items.RECYCLE_COPPER_4).add(
                                Items.COPPER_BOOTS,
                                Items.COPPER_TRAPDOOR)
                                .addTags(LibTags.Items.COPPER_GEAR);

                tag(zTags.Items.RECYCLE_COPPER_5).add(
                                Items.COPPER_HELMET);

                tag(zTags.Items.RECYCLE_COPPER_6).add(
                                Items.COPPER_HORSE_ARMOR);

                tag(zTags.Items.RECYCLE_COPPER_7).add(
                                Items.COPPER_LEGGINGS);

                tag(zTags.Items.RECYCLE_COPPER_8).add(
                                Items.COPPER_CHESTPLATE);

                tag(zTags.Items.RECYCLE_COPPER_9).add(
                                Items.COPPER_NAUTILUS_ARMOR);

                tag(zTags.Items.RECYCLE_IRON_1).add(
                                Items.SHIELD,
                                Items.IRON_SHOVEL,
                                Items.IRON_SPEAR)
                                .addTags(LibTags.Items.IRON_PLATE,
                                                LibTags.Items.IRON_COIL,
                                                LibTags.Items.IRON_FOIL,
                                                LibTags.Items.IRON_DUST);

                tag(zTags.Items.RECYCLE_IRON_2).add(
                                Items.IRON_HOE,
                                Items.SHEARS,
                                Items.IRON_SWORD,
                                Items.IRON_DOOR,
                                Items.HEAVY_WEIGHTED_PRESSURE_PLATE);

                tag(zTags.Items.RECYCLE_IRON_3).add(
                                Items.BUCKET,
                                Items.IRON_PICKAXE,
                                Items.IRON_AXE);

                tag(zTags.Items.RECYCLE_IRON_4).add(
                                Items.IRON_BOOTS,
                                Items.IRON_TRAPDOOR)
                                .addTags(LibTags.Items.IRON_GEAR);

                tag(zTags.Items.RECYCLE_IRON_5).add(
                                Items.IRON_HELMET);

                tag(zTags.Items.RECYCLE_IRON_6).add(
                                Items.IRON_HORSE_ARMOR);

                tag(zTags.Items.RECYCLE_IRON_7).add(
                                Items.IRON_LEGGINGS);

                tag(zTags.Items.RECYCLE_IRON_8).add(
                                Items.IRON_CHESTPLATE);

                tag(zTags.Items.RECYCLE_IRON_9).add(
                                Items.IRON_NAUTILUS_ARMOR);

                tag(zTags.Items.RECYCLE_GOLD_1).add(
                                Items.GOLDEN_SHOVEL,
                                Items.GOLDEN_SPEAR)
                                .addTags(LibTags.Items.GOLD_PLATE,
                                                LibTags.Items.GOLD_COIL,
                                                LibTags.Items.GOLD_FOIL,
                                                LibTags.Items.GOLD_DUST);

                tag(zTags.Items.RECYCLE_GOLD_2).add(
                                Items.GOLDEN_HOE,
                                Items.GOLDEN_SWORD,
                                Items.LIGHT_WEIGHTED_PRESSURE_PLATE);

                tag(zTags.Items.RECYCLE_GOLD_3).add(
                                Items.GOLDEN_PICKAXE,
                                Items.GOLDEN_AXE);

                tag(zTags.Items.RECYCLE_GOLD_4).add(
                                Items.GOLDEN_BOOTS)
                                .addTags(LibTags.Items.GOLD_GEAR);

                tag(zTags.Items.RECYCLE_GOLD_5).add(
                                Items.GOLDEN_HELMET);

                tag(zTags.Items.RECYCLE_GOLD_6).add(
                                Items.GOLDEN_HORSE_ARMOR);

                tag(zTags.Items.RECYCLE_GOLD_7).add(
                                Items.GOLDEN_LEGGINGS);

                tag(zTags.Items.RECYCLE_GOLD_8).add(
                                Items.GOLDEN_CHESTPLATE);

                tag(zTags.Items.RECYCLE_GOLD_9).add(
                                Items.GOLDEN_NAUTILUS_ARMOR);

                tag(Tags.Items.STRINGS).add(zItems.HEMP_FIBER.get());
                tag(Tags.Items.LEATHERS).add(zItems.CLOTH.get());

                tag(zTags.Items.WINDMILL_REPAIR).add(zItems.CLOTH.get());

                tag(zTags.Items.BASKET_DENY)
                                .add(zBlocks.BASKET.get().asItem())
                                .addTag(ItemTags.SHULKER_BOXES);

                

        }

}