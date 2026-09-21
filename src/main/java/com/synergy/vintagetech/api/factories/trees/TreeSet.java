package com.synergy.vintagetech.api.factories.trees;

import java.util.List;
import java.util.function.Function;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.*;

/**
 * Everything related to registry and datagen
 */
public record TreeSet(

                String id,

                DeferredHolder<Block, Block> log,
                DeferredHolder<Block, Block> strippedLog,

                DeferredHolder<Block, Block> wood,
                DeferredHolder<Block, Block> strippedWood,

                DeferredHolder<Block, Block> planks,
                DeferredHolder<Block, Block> leaves,

                DeferredHolder<Block, Block> sapling,
                DeferredBlock<FlowerPotBlock> pottedSapling,

                DeferredHolder<Block, Block> stairs,
                DeferredHolder<Block, Block> slab,
                DeferredHolder<Block, Block> shelf,

                DeferredHolder<Block, Block> fence,
                DeferredHolder<Block, Block> fenceGate,

                DeferredHolder<Block, Block> door,
                DeferredHolder<Block, Block> trapdoor,

                DeferredHolder<Block, Block> button,
                DeferredHolder<Block, Block> pressurePlate,

                DeferredBlock<? extends Block> sign,
                DeferredBlock<? extends Block> wallSign,

                DeferredBlock<? extends Block> hangingSign,
                DeferredBlock<? extends Block> wallHangingSign,

                DeferredItem<? extends Item> signItem,
                DeferredItem<? extends Item> hangingSignItem,

                TagKey<Item> itemTag,
                TagKey<Block> blockTag

) {

        public List<Block> getLootBlocks() {
                return List.of(
                                log.get(), strippedLog.get(),
                                wood.get(), strippedWood.get(),
                                planks.get(),
                                sapling.get(), pottedSapling.get(),
                                stairs.get(), slab.get(), shelf.get(),
                                fence.get(), fenceGate.get(),
                                door.get(), trapdoor.get(),
                                button.get(), pressurePlate.get());
        }

        public List<Block> getSpecialLootBlocks() {
                return List.of(
                                leaves.get(),
                                sign.get(),
                                hangingSign.get());
        }

        public void createModels(BlockModelGenerators models) {

                models.woodProvider(log.get())
                                .logWithHorizontal(log.get())
                                .wood(wood.get());

                models.woodProvider(strippedLog.get())
                                .logWithHorizontal(strippedLog.get())
                                .wood(strippedWood.get());

                models.createTintedLeaves(leaves.get(), TexturedModel.LEAVES, FoliageColor.FOLIAGE_DEFAULT);

                models.createPlantWithDefaultItem(sapling.get(), pottedSapling.get(),
                                BlockModelGenerators.PlantType.NOT_TINTED);

                models.createHangingSign(strippedLog.get(), hangingSign.get(), wallHangingSign.get());

                models.createShelf(shelf.get(), strippedLog.get());

                models.family(planks.get())
                                .generateFor(
                                                new BlockFamily.Builder(planks.get())
                                                                .stairs(stairs.get())
                                                                .slab(slab.get())
                                                                .fence(fence.get())
                                                                .fenceGate(fenceGate.get())
                                                                .door(door.get())
                                                                .trapdoor(trapdoor.get())
                                                                .button(button.get())
                                                                .pressurePlate(pressurePlate.get())
                                                                .sign(sign.get(), wallSign.get())
                                                                .getFamily());
        }

        public void createBlockTags(Function<TagKey<Block>, TagAppender<Block, Block>> tags) {

                tags.apply(BlockTags.MINEABLE_WITH_AXE)
                                .add(sign.get())
                                .add(hangingSign.get())
                                .add(wallSign.get())
                                .add(wallHangingSign.get());

                tags.apply(BlockTags.MINEABLE_WITH_HOE)
                                .add(leaves.get());

                tags.apply(BlockTags.SLABS)
                                .add(slab.get());

                tags.apply(BlockTags.WOODEN_SHELVES)
                                .add(shelf.get());

                tags.apply(BlockTags.STAIRS)
                                .add(stairs.get());

                tags.apply(Tags.Blocks.STRIPPED_LOGS)
                                .add(strippedLog.get());

                tags.apply(Tags.Blocks.STRIPPED_WOODS)
                                .add(strippedWood.get());

                tags.apply(blockTag)
                                .add(log.get())
                                .add(wood.get())
                                .add(strippedLog.get())
                                .add(strippedWood.get());

                tags.apply(BlockTags.LOGS)
                                .addTag(blockTag);

                tags.apply(BlockTags.LOGS_THAT_BURN)
                                .add(log.get())
                                .add(wood.get())
                                .add(strippedLog.get())
                                .add(strippedWood.get());

                tags.apply(BlockTags.PLANKS)
                                .add(planks.get());

                tags.apply(BlockTags.LEAVES)
                                .add(leaves.get());

                tags.apply(BlockTags.SAPLINGS)
                                .add(sapling.get());

                tags.apply(BlockTags.WOODEN_STAIRS)
                                .add(stairs.get());

                tags.apply(BlockTags.WOODEN_SLABS)
                                .add(slab.get());

                tags.apply(BlockTags.WOODEN_FENCES)
                                .add(fence.get());

                tags.apply(BlockTags.FENCE_GATES)
                                .add(fenceGate.get());

                tags.apply(BlockTags.WOODEN_DOORS)
                                .add(door.get());

                tags.apply(BlockTags.WOODEN_TRAPDOORS)
                                .add(trapdoor.get());

                tags.apply(BlockTags.WOODEN_BUTTONS)
                                .add(button.get());

                tags.apply(BlockTags.WOODEN_PRESSURE_PLATES)
                                .add(pressurePlate.get());
        }

        public void createItemTags(Function<TagKey<Item>, TagAppender<Item, Item>> tags) {

                tags.apply(ItemTags.SAPLINGS)
                                .add(sapling.get().asItem());

                tags.apply(ItemTags.SLABS)
                                .add(slab.get().asItem());

                tags.apply(ItemTags.WOODEN_SHELVES)
                                .add(shelf.get().asItem());

                tags.apply(ItemTags.STAIRS)
                                .add(stairs.get().asItem());

                tags.apply(ItemTags.LOGS_THAT_BURN)
                                .addTag(itemTag);

                tags.apply(ItemTags.LEAVES)
                                .add(leaves.get().asItem());

                tags.apply(Tags.Items.STRIPPED_LOGS)
                                .add(strippedLog.get().asItem());

                tags.apply(Tags.Items.STRIPPED_WOODS)
                                .add(strippedWood.get().asItem());

                tags.apply(itemTag)
                                .add(log.get().asItem())
                                .add(wood.get().asItem())
                                .add(strippedLog.get().asItem())
                                .add(strippedWood.get().asItem());

                tags.apply(ItemTags.PLANKS)
                                .add(planks.get().asItem());

                tags.apply(ItemTags.LOGS)
                                .addTag(itemTag);

                tags.apply(ItemTags.WOODEN_STAIRS)
                                .add(stairs.get().asItem());

                tags.apply(ItemTags.WOODEN_SLABS)
                                .add(slab.get().asItem());

                tags.apply(ItemTags.WOODEN_FENCES)
                                .add(fence.get().asItem());

                tags.apply(ItemTags.WOODEN_DOORS)
                                .add(door.get().asItem());

                tags.apply(ItemTags.WOODEN_TRAPDOORS)
                                .add(trapdoor.get().asItem());

                tags.apply(ItemTags.WOODEN_BUTTONS)
                                .add(button.get().asItem());

                tags.apply(ItemTags.WOODEN_PRESSURE_PLATES)
                                .add(pressurePlate.get().asItem());

        }

}
