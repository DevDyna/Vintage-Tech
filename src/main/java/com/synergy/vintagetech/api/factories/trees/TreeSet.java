package com.synergy.vintagetech.api.factories.trees;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.synergy.vintagetech.api.factories.trees.TreeBuilders.TreeGrowerBuilder;
import com.synergy.vintagetech.api.factories.trees.TreeOptions.TreeParticleLeaves;
import com.synergy.vintagetech.api.factories.trees.TreeOptions.WoodOptionType;

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
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.*;

/**
 * Everything related to registry and datagen of trees and derivated
 */
public class TreeSet {

        private String id;

        private DeferredHolder<Block, Block> log;
        private DeferredHolder<Block, Block> strippedLog;

        private DeferredHolder<Block, Block> wood;
        private DeferredHolder<Block, Block> strippedWood;

        private DeferredHolder<Block, Block> planks;
        private DeferredHolder<Block, Block> leaves;

        private DeferredHolder<Block, Block> sapling;
        private DeferredBlock<FlowerPotBlock> pottedSapling;

        private DeferredHolder<Block, Block> stairs;
        private DeferredHolder<Block, Block> slab;
        private DeferredHolder<Block, Block> shelf;

        private DeferredHolder<Block, Block> fence;
        private DeferredHolder<Block, Block> fenceGate;

        private DeferredHolder<Block, Block> door;
        private DeferredHolder<Block, Block> trapdoor;

        private DeferredHolder<Block, Block> button;
        private DeferredHolder<Block, Block> pressurePlate;

        private DeferredBlock<? extends Block> sign;
        private DeferredBlock<? extends Block> wallSign;

        private DeferredBlock<? extends Block> hangingSign;
        private DeferredBlock<? extends Block> wallHangingSign;

        private DeferredItem<? extends Item> signItem;
        private DeferredItem<? extends Item> hangingSignItem;

        private TagKey<Item> itemTag;
        private TagKey<Block> blockTag;

        private TreeProperties properties;

        public record TreeProperties(String id, MapColor plankColor, MapColor logSideColor, float hardness,
                        float resistance,
                        SoundType soundType, WoodType woodType, boolean burnable, TreeParticleLeaves particles,
                        BlockSetType blockset, WoodOptionType woodOptionType,
                        Function<String, TreeGrowerBuilder> grower) {

                public static TreeProperties of(String id, MapColor plankColor, MapColor logSideColor, float hardness,
                                float resistance,
                                SoundType soundType, WoodType woodType, boolean burnable, TreeParticleLeaves particles,
                                BlockSetType blockset, WoodOptionType woodOptionType,
                                Function<String, TreeGrowerBuilder> grower) {
                        return new TreeSet.TreeProperties(id, plankColor, logSideColor, hardness, resistance, soundType,
                                        woodType, burnable, particles, blockset, woodOptionType, grower);
                }
        }

        public TreeSet(TreeProperties p) {
                this.properties = p;
                this.id = properties.id();
        }

        public String getId() {
                return id;
        }

        public DeferredHolder<Block, Block> getLog() {
                return log;
        }

        public DeferredHolder<Block, Block> getStrippedLog() {
                return strippedLog;
        }

        public DeferredHolder<Block, Block> getWood() {
                return wood;
        }

        public DeferredHolder<Block, Block> getStrippedWood() {
                return strippedWood;
        }

        public DeferredHolder<Block, Block> getPlanks() {
                return planks;
        }

        public DeferredHolder<Block, Block> getLeaves() {
                return leaves;
        }

        public DeferredHolder<Block, Block> getSapling() {
                return sapling;
        }

        public DeferredBlock<FlowerPotBlock> getPottedSapling() {
                return pottedSapling;
        }

        public DeferredHolder<Block, Block> getStairs() {
                return stairs;
        }

        public DeferredHolder<Block, Block> getSlab() {
                return slab;
        }

        public DeferredHolder<Block, Block> getShelf() {
                return shelf;
        }

        public DeferredHolder<Block, Block> getFence() {
                return fence;
        }

        public DeferredHolder<Block, Block> getFenceGate() {
                return fenceGate;
        }

        public DeferredHolder<Block, Block> getDoor() {
                return door;
        }

        public DeferredHolder<Block, Block> getTrapdoor() {
                return trapdoor;
        }

        public DeferredHolder<Block, Block> getButton() {
                return button;
        }

        public DeferredHolder<Block, Block> getPressurePlate() {
                return pressurePlate;
        }

        public DeferredBlock<? extends Block> getSign() {
                return sign;
        }

        public DeferredBlock<? extends Block> getWallSign() {
                return wallSign;
        }

        public DeferredBlock<? extends Block> getHangingSign() {
                return hangingSign;
        }

        public DeferredBlock<? extends Block> getWallHangingSign() {
                return wallHangingSign;
        }

        public DeferredItem<? extends Item> getSignItem() {
                return signItem;
        }

        public DeferredItem<? extends Item> getHangingSignItem() {
                return hangingSignItem;
        }

        public TagKey<Item> getItemTag() {
                return itemTag;
        }

        public TagKey<Block> getBlockTag() {
                return blockTag;
        }

        public TreeSet setLog(Function<DeferredHolder<Block, Block>, DeferredHolder<Block, Block>> f) {
                this.log = f.apply(this.log);
                return this;
        }

        public TreeSet setLog(
                        BiFunction<DeferredHolder<Block, Block>, TreeProperties, DeferredHolder<Block, Block>> f) {
                this.log = f.apply(this.log, this.properties);
                return this;
        }

        public TreeSet setLog(DeferredHolder<Block, Block> v) {
                return setLog(_ -> v);
        }

        public TreeSet setStrippedLog(Function<DeferredHolder<Block, Block>, DeferredHolder<Block, Block>> f) {
                this.strippedLog = f.apply(this.strippedLog);
                return this;
        }

        public TreeSet setStrippedLog(
                        BiFunction<DeferredHolder<Block, Block>, TreeProperties, DeferredHolder<Block, Block>> f) {
                this.strippedLog = f.apply(this.strippedLog, this.properties);
                return this;
        }

        public TreeSet setStrippedLog(DeferredHolder<Block, Block> v) {
                return setStrippedLog(_ -> v);
        }

        public TreeSet setWood(Function<DeferredHolder<Block, Block>, DeferredHolder<Block, Block>> f) {
                this.wood = f.apply(this.wood);
                return this;
        }

        public TreeSet setWood(
                        BiFunction<DeferredHolder<Block, Block>, TreeProperties, DeferredHolder<Block, Block>> f) {
                this.wood = f.apply(this.wood, this.properties);
                return this;
        }

        public TreeSet setWood(DeferredHolder<Block, Block> v) {
                return setWood(_ -> v);
        }

        public TreeSet setStrippedWood(Function<DeferredHolder<Block, Block>, DeferredHolder<Block, Block>> f) {
                this.strippedWood = f.apply(this.strippedWood);
                return this;
        }

        public TreeSet setStrippedWood(
                        BiFunction<DeferredHolder<Block, Block>, TreeProperties, DeferredHolder<Block, Block>> f) {
                this.strippedWood = f.apply(this.strippedWood, this.properties);
                return this;
        }

        public TreeSet setStrippedWood(DeferredHolder<Block, Block> v) {
                return setStrippedWood(_ -> v);
        }

        public TreeSet setPlanks(Function<DeferredHolder<Block, Block>, DeferredHolder<Block, Block>> f) {
                this.planks = f.apply(this.planks);
                return this;
        }

        public TreeSet setPlanks(
                        BiFunction<DeferredHolder<Block, Block>, TreeProperties, DeferredHolder<Block, Block>> f) {
                this.planks = f.apply(this.planks, this.properties);
                return this;
        }

        public TreeSet setPlanks(DeferredHolder<Block, Block> v) {
                return setPlanks(_ -> v);
        }

        public TreeSet setLeaves(Function<DeferredHolder<Block, Block>, DeferredHolder<Block, Block>> f) {
                this.leaves = f.apply(this.leaves);
                return this;
        }

        public TreeSet setLeaves(
                        BiFunction<DeferredHolder<Block, Block>, TreeProperties, DeferredHolder<Block, Block>> f) {
                this.leaves = f.apply(this.leaves, this.properties);
                return this;
        }

        public TreeSet setLeaves(DeferredHolder<Block, Block> v) {
                return setLeaves(_ -> v);
        }

        public TreeSet setSapling(Function<DeferredHolder<Block, Block>, DeferredHolder<Block, Block>> f) {
                this.sapling = f.apply(this.sapling);
                return this;
        }

        public TreeSet setSapling(
                        BiFunction<DeferredHolder<Block, Block>, TreeProperties, DeferredHolder<Block, Block>> f) {
                this.sapling = f.apply(this.sapling, this.properties);
                return this;
        }

        public TreeSet setSapling(DeferredHolder<Block, Block> v) {
                return setSapling(_ -> v);
        }

        public TreeSet setPottedSapling(Function<DeferredBlock<FlowerPotBlock>, DeferredBlock<FlowerPotBlock>> f) {
                this.pottedSapling = f.apply(this.pottedSapling);
                return this;
        }

        public TreeSet setPottedSapling(
                        BiFunction<DeferredBlock<FlowerPotBlock>, TreeProperties, DeferredBlock<FlowerPotBlock>> f) {
                this.pottedSapling = f.apply(this.pottedSapling, this.properties);
                return this;
        }

        public TreeSet setPottedSapling(DeferredBlock<FlowerPotBlock> v) {
                return setPottedSapling(_ -> v);
        }

        public TreeSet setStairs(Function<DeferredHolder<Block, Block>, DeferredHolder<Block, Block>> f) {
                this.stairs = f.apply(this.stairs);
                return this;
        }

        public TreeSet setStairs(
                        BiFunction<DeferredHolder<Block, Block>, TreeProperties, DeferredHolder<Block, Block>> f) {
                this.stairs = f.apply(this.stairs, this.properties);
                return this;
        }

        public TreeSet setStairs(DeferredHolder<Block, Block> v) {
                return setStairs(_ -> v);
        }

        public TreeSet setSlab(Function<DeferredHolder<Block, Block>, DeferredHolder<Block, Block>> f) {
                this.slab = f.apply(this.slab);
                return this;
        }

        public TreeSet setSlab(
                        BiFunction<DeferredHolder<Block, Block>, TreeProperties, DeferredHolder<Block, Block>> f) {
                this.slab = f.apply(this.slab, this.properties);
                return this;
        }

        public TreeSet setSlab(DeferredHolder<Block, Block> v) {
                return setSlab(_ -> v);
        }

        public TreeSet setShelf(Function<DeferredHolder<Block, Block>, DeferredHolder<Block, Block>> f) {
                this.shelf = f.apply(this.shelf);
                return this;
        }

        public TreeSet setShelf(
                        BiFunction<DeferredHolder<Block, Block>, TreeProperties, DeferredHolder<Block, Block>> f) {
                this.shelf = f.apply(this.shelf, this.properties);
                return this;
        }

        public TreeSet setShelf(DeferredHolder<Block, Block> v) {
                return setShelf(_ -> v);
        }

        public TreeSet setFence(Function<DeferredHolder<Block, Block>, DeferredHolder<Block, Block>> f) {
                this.fence = f.apply(this.fence);
                return this;
        }

        public TreeSet setFence(
                        BiFunction<DeferredHolder<Block, Block>, TreeProperties, DeferredHolder<Block, Block>> f) {
                this.fence = f.apply(this.fence, this.properties);
                return this;
        }

        public TreeSet setFence(DeferredHolder<Block, Block> v) {
                return setFence(_ -> v);
        }

        public TreeSet setFenceGate(Function<DeferredHolder<Block, Block>, DeferredHolder<Block, Block>> f) {
                this.fenceGate = f.apply(this.fenceGate);
                return this;
        }

        public TreeSet setFenceGate(
                        BiFunction<DeferredHolder<Block, Block>, TreeProperties, DeferredHolder<Block, Block>> f) {
                this.fenceGate = f.apply(this.fenceGate, this.properties);
                return this;
        }

        public TreeSet setFenceGate(DeferredHolder<Block, Block> v) {
                return setFenceGate(_ -> v);
        }

        public TreeSet setDoor(Function<DeferredHolder<Block, Block>, DeferredHolder<Block, Block>> f) {
                this.door = f.apply(this.door);
                return this;
        }

        public TreeSet setDoor(
                        BiFunction<DeferredHolder<Block, Block>, TreeProperties, DeferredHolder<Block, Block>> f) {
                this.door = f.apply(this.door, this.properties);
                return this;
        }

        public TreeSet setDoor(DeferredHolder<Block, Block> v) {
                return setDoor(_ -> v);
        }

        public TreeSet setTrapdoor(Function<DeferredHolder<Block, Block>, DeferredHolder<Block, Block>> f) {
                this.trapdoor = f.apply(this.trapdoor);
                return this;
        }

        public TreeSet setTrapdoor(
                        BiFunction<DeferredHolder<Block, Block>, TreeProperties, DeferredHolder<Block, Block>> f) {
                this.trapdoor = f.apply(this.trapdoor, this.properties);
                return this;
        }

        public TreeSet setTrapdoor(DeferredHolder<Block, Block> v) {
                return setTrapdoor(_ -> v);
        }

        public TreeSet setButton(Function<DeferredHolder<Block, Block>, DeferredHolder<Block, Block>> f) {
                this.button = f.apply(this.button);
                return this;
        }

        public TreeSet setButton(
                        BiFunction<DeferredHolder<Block, Block>, TreeProperties, DeferredHolder<Block, Block>> f) {
                this.button = f.apply(this.button, this.properties);
                return this;
        }

        public TreeSet setButton(DeferredHolder<Block, Block> v) {
                return setButton(_ -> v);
        }

        public TreeSet setPressurePlate(Function<DeferredHolder<Block, Block>, DeferredHolder<Block, Block>> f) {
                this.pressurePlate = f.apply(this.pressurePlate);
                return this;
        }

        public TreeSet setPressurePlate(
                        BiFunction<DeferredHolder<Block, Block>, TreeProperties, DeferredHolder<Block, Block>> f) {
                this.pressurePlate = f.apply(this.pressurePlate, this.properties);
                return this;
        }

        public TreeSet setPressurePlate(DeferredHolder<Block, Block> v) {
                return setPressurePlate(_ -> v);
        }

        public TreeSet setSign(Function<DeferredBlock<? extends Block>, DeferredBlock<? extends Block>> f) {
                this.sign = f.apply(this.sign);
                return this;
        }

        public TreeSet setSign(
                        BiFunction<DeferredBlock<? extends Block>, TreeProperties, DeferredBlock<? extends Block>> f) {
                this.sign = f.apply(this.sign, this.properties);
                return this;
        }

        public TreeSet setSign(DeferredBlock<? extends Block> v) {
                return setSign(_ -> v);
        }

        public TreeSet setWallSign(Function<DeferredBlock<? extends Block>, DeferredBlock<? extends Block>> f) {
                this.wallSign = f.apply(this.wallSign);
                return this;
        }

        public TreeSet setWallSign(
                        BiFunction<DeferredBlock<? extends Block>, TreeProperties, DeferredBlock<? extends Block>> f) {
                this.wallSign = f.apply(this.wallSign, this.properties);
                return this;
        }

        public TreeSet setWallSign(DeferredBlock<? extends Block> v) {
                return setWallSign(_ -> v);
        }

        public TreeSet setHangingSign(Function<DeferredBlock<? extends Block>, DeferredBlock<? extends Block>> f) {
                this.hangingSign = f.apply(this.hangingSign);
                return this;
        }

        public TreeSet setHangingSign(
                        BiFunction<DeferredBlock<? extends Block>, TreeProperties, DeferredBlock<? extends Block>> f) {
                this.hangingSign = f.apply(this.hangingSign, this.properties);
                return this;
        }

        public TreeSet setHangingSign(DeferredBlock<? extends Block> v) {
                return setHangingSign(_ -> v);
        }

        public TreeSet setWallHangingSign(Function<DeferredBlock<? extends Block>, DeferredBlock<? extends Block>> f) {
                this.wallHangingSign = f.apply(this.wallHangingSign);
                return this;
        }

        public TreeSet setWallHangingSign(
                        BiFunction<DeferredBlock<? extends Block>, TreeProperties, DeferredBlock<? extends Block>> f) {
                this.wallHangingSign = f.apply(this.wallHangingSign, this.properties);
                return this;
        }

        public TreeSet setWallHangingSign(DeferredBlock<? extends Block> v) {
                return setWallHangingSign(_ -> v);
        }

        public TreeSet setSignItem(Function<DeferredItem<? extends Item>, DeferredItem<? extends Item>> f) {
                this.signItem = f.apply(this.signItem);
                return this;
        }

        public TreeSet setSignItem(
                        BiFunction<DeferredItem<? extends Item>, TreeProperties, DeferredItem<? extends Item>> f) {
                this.signItem = f.apply(this.signItem, this.properties);
                return this;
        }

        public TreeSet setSignItem(DeferredItem<? extends Item> v) {
                return setSignItem(_ -> v);
        }

        public TreeSet setHangingSignItem(Function<DeferredItem<? extends Item>, DeferredItem<? extends Item>> f) {
                this.hangingSignItem = f.apply(this.hangingSignItem);
                return this;
        }

        public TreeSet setHangingSignItem(
                        BiFunction<DeferredItem<? extends Item>, TreeProperties, DeferredItem<? extends Item>> f) {
                this.hangingSignItem = f.apply(this.hangingSignItem, this.properties);
                return this;
        }

        public TreeSet setHangingSignItem(DeferredItem<? extends Item> v) {
                return setHangingSignItem(_ -> v);
        }

        public TreeSet setItemTag(Function<TagKey<Item>, TagKey<Item>> f) {
                this.itemTag = f.apply(this.itemTag);
                return this;
        }

        public TreeSet setItemTag(BiFunction<TagKey<Item>, TreeProperties, TagKey<Item>> f) {
                this.itemTag = f.apply(this.itemTag, this.properties);
                return this;
        }

        public TreeSet setItemTag(TagKey<Item> v) {
                return setItemTag(_ -> v);
        }

        public TreeSet setBlockTag(Function<TagKey<Block>, TagKey<Block>> f) {
                this.blockTag = f.apply(this.blockTag);
                return this;
        }

        public TreeSet setBlockTag(BiFunction<TagKey<Block>, TreeProperties, TagKey<Block>> f) {
                this.blockTag = f.apply(this.blockTag, this.properties);
                return this;
        }

        public TreeSet setBlockTag(TagKey<Block> v) {
                return setBlockTag(_ -> v);
        }

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
                return List.of(leaves.get(), sign.get(), hangingSign.get());
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
