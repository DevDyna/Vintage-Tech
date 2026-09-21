package com.synergy.vintagetech.datagen.server;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.setup.registry.LibTags;
import com.synergy.vintagetech.api.factories.beams.BeamFactory;
import com.synergy.vintagetech.api.factories.trees.TreeFactory;
import com.synergy.vintagetech.api.factories.trees.TreeSet;
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

                for (TreeSet wood : TreeFactory.getAll())
                        wood.createItemTags(this::tag);

                for (var beam : BeamFactory.getAll())
                        beam.createItemTags(this::tag);

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

                tag(zTags.Items.BEAMS).addTags(
                                zTags.Items.BEAM_LOG,
                                zTags.Items.BEAM_STRIPPED_LOG,
                                zTags.Items.BEAM_WOOD,
                                zTags.Items.BEAM_STRIPPED_WOOD);

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

                tag(Tags.Items.STRINGS).add(zItems.HEMP_FIBER.get());
                tag(Tags.Items.LEATHERS).add(zItems.CLOTH.get());

                tag(zTags.Items.WINDMILL_REPAIR).add(zItems.CLOTH.get());

                tag(zTags.Items.BASKET_DENY)
                                .add(zBlocks.BASKET.get().asItem())
                                .addTag(ItemTags.SHULKER_BOXES);

                tag(zTags.Items.CHEESE_SEALER).addTag(LibTags.Items.WAXING);
                tag(zTags.Items.CHEESE_UNSEALER).addTag(ItemTags.AXES);

                tag(Tags.Items.DUSTS).add(zItems.SALT.get());
                tag(zTags.Items.SALT_DUST).add(zItems.SALT.get());

                tag(zTags.Items.STICKY_GOO).add(zItems.AMBER.get(), zItems.SULFUR_GOO.get());

                tag(zTags.Items.STICKY_FARMLAND_SCRAPPABLE).addTag(ItemTags.AXES);

        }

}