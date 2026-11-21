package com.synergy.vintagetech.api.factories.trees;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.List;
import java.util.function.Function;

import com.devdyna.cakesticklib.api.RegistryUtils;
import com.synergy.vintagetech.api.factories.trees.TreeBuilders.TreeGrowerBuilder;
import com.synergy.vintagetech.api.factories.trees.TreeOptions.TreeParticleLeaves;
import com.synergy.vintagetech.api.factories.trees.TreeOptions.WoodOptionType;
import com.synergy.vintagetech.init.Material;
import com.synergy.vintagetech.init.types.*;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.Direction;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.ShelfBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.*;

/**
 * Everything related to registry and datagen of trees and derivated
 */
public class TreeSet {

        private String id;

        private DeferredHolder<Block, Block> log = null;
        private DeferredHolder<Block, Block> strippedLog = null;

        private DeferredHolder<Block, Block> wood = null;
        private DeferredHolder<Block, Block> strippedWood = null;

        private DeferredHolder<Block, Block> planks = null;
        private DeferredHolder<Block, Block> leaves = null;

        private DeferredHolder<Block, Block> sapling = null;
        private DeferredBlock<FlowerPotBlock> pottedSapling = null;

        private DeferredHolder<Block, Block> stairs = null;
        private DeferredHolder<Block, Block> slab = null;
        private DeferredHolder<Block, Block> shelf = null;

        private DeferredHolder<Block, Block> fence = null;
        private DeferredHolder<Block, Block> fenceGate = null;

        private DeferredHolder<Block, Block> door = null;
        private DeferredHolder<Block, Block> trapdoor = null;

        private DeferredHolder<Block, Block> button = null;
        private DeferredHolder<Block, Block> pressurePlate = null;

        private DeferredBlock<? extends Block> sign = null;
        private DeferredBlock<? extends Block> wallSign = null;

        private DeferredBlock<? extends Block> hangingSign = null;
        private DeferredBlock<? extends Block> wallHangingSign = null;

        private DeferredItem<? extends Item> signItem = null;
        private DeferredItem<? extends Item> hangingSignItem = null;

        private TagKey<Item> itemTag = null;
        private TagKey<Block> blockTag = null;

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

        public TreeSet setLog(Function<TreeProperties, DeferredHolder<Block, Block>> f) {
                this.log = f.apply(this.properties);
                return this;
        }

        public TreeSet setLog(DeferredHolder<Block, Block> v) {
                this.log = v;
                return this;
        }

        public TreeSet setStrippedLog(Function<TreeProperties, DeferredHolder<Block, Block>> f) {
                this.strippedLog = f.apply(this.properties);
                return this;
        }

        public TreeSet setStrippedLog(DeferredHolder<Block, Block> v) {
                this.strippedLog = v;
                return this;
        }

        public TreeSet setWood(Function<TreeProperties, DeferredHolder<Block, Block>> f) {
                this.wood = f.apply(this.properties);
                return this;
        }

        public TreeSet setWood(DeferredHolder<Block, Block> v) {
                this.wood = v;
                return this;
        }

        public TreeSet setStrippedWood(Function<TreeProperties, DeferredHolder<Block, Block>> f) {
                this.strippedWood = f.apply(this.properties);
                return this;
        }

        public TreeSet setStrippedWood(DeferredHolder<Block, Block> v) {
                this.strippedWood = v;
                return this;
        }

        public TreeSet setPlanks(Function<TreeProperties, DeferredHolder<Block, Block>> f) {
                this.planks = f.apply(this.properties);
                return this;
        }

        public TreeSet setPlanks(DeferredHolder<Block, Block> v) {
                this.planks = v;
                return this;
        }

        public TreeSet setLeaves(Function<TreeProperties, DeferredHolder<Block, Block>> f) {
                this.leaves = f.apply(this.properties);
                return this;
        }

        public TreeSet setLeaves(DeferredHolder<Block, Block> v) {
                this.leaves = v;
                return this;
        }

        public TreeSet setSapling(Function<TreeProperties, DeferredHolder<Block, Block>> f) {
                this.sapling = f.apply(this.properties);
                return this;
        }

        public TreeSet setSapling(DeferredHolder<Block, Block> v) {
                this.sapling = v;
                return this;
        }

        public TreeSet setPottedSapling(Function<TreeProperties, DeferredBlock<FlowerPotBlock>> f) {
                this.pottedSapling = f.apply(this.properties);
                return this;
        }

        public TreeSet setPottedSapling(DeferredBlock<FlowerPotBlock> v) {
                this.pottedSapling = v;
                return this;
        }

        public TreeSet setStairs(Function<TreeProperties, DeferredHolder<Block, Block>> f) {
                this.stairs = f.apply(this.properties);
                return this;
        }

        public TreeSet setStairs(DeferredHolder<Block, Block> v) {
                this.stairs = v;
                return this;
        }

        public TreeSet setSlab(Function<TreeProperties, DeferredHolder<Block, Block>> f) {
                this.slab = f.apply(this.properties);
                return this;
        }

        public TreeSet setSlab(DeferredHolder<Block, Block> v) {
                this.slab = v;
                return this;
        }

        public TreeSet setShelf(Function<TreeProperties, DeferredHolder<Block, Block>> f) {
                this.shelf = f.apply(this.properties);
                return this;
        }

        public TreeSet setShelf(DeferredHolder<Block, Block> v) {
                this.shelf = v;
                return this;
        }

        public TreeSet setFence(Function<TreeProperties, DeferredHolder<Block, Block>> f) {
                this.fence = f.apply(this.properties);
                return this;
        }

        public TreeSet setFence(DeferredHolder<Block, Block> v) {
                this.fence = v;
                return this;
        }

        public TreeSet setFenceGate(Function<TreeProperties, DeferredHolder<Block, Block>> f) {
                this.fenceGate = f.apply(this.properties);
                return this;
        }

        public TreeSet setFenceGate(DeferredHolder<Block, Block> v) {
                this.fenceGate = v;
                return this;
        }

        public TreeSet setDoor(Function<TreeProperties, DeferredHolder<Block, Block>> f) {
                this.door = f.apply(this.properties);
                return this;
        }

        public TreeSet setDoor(DeferredHolder<Block, Block> v) {
                this.door = v;
                return this;
        }

        public TreeSet setTrapdoor(Function<TreeProperties, DeferredHolder<Block, Block>> f) {
                this.trapdoor = f.apply(this.properties);
                return this;
        }

        public TreeSet setTrapdoor(DeferredHolder<Block, Block> v) {
                this.trapdoor = v;
                return this;
        }

        public TreeSet setButton(Function<TreeProperties, DeferredHolder<Block, Block>> f) {
                this.button = f.apply(this.properties);
                return this;
        }

        public TreeSet setButton(DeferredHolder<Block, Block> v) {
                this.button = v;
                return this;
        }

        public TreeSet setPressurePlate(Function<TreeProperties, DeferredHolder<Block, Block>> f) {
                this.pressurePlate = f.apply(this.properties);
                return this;
        }

        public TreeSet setPressurePlate(DeferredHolder<Block, Block> v) {
                this.pressurePlate = v;
                return this;
        }

        public TreeSet setSign(Function<TreeProperties, DeferredBlock<? extends Block>> f) {
                this.sign = f.apply(this.properties);
                return this;
        }

        public TreeSet setSign(DeferredBlock<? extends Block> v) {
                this.sign = v;
                return this;
        }

        public TreeSet setWallSign(Function<TreeProperties, DeferredBlock<? extends Block>> f) {
                this.wallSign = f.apply(this.properties);
                return this;
        }

        public TreeSet setWallSign(DeferredBlock<? extends Block> v) {
                this.wallSign = v;
                return this;
        }

        public TreeSet setHangingSign(Function<TreeProperties, DeferredBlock<? extends Block>> f) {
                this.hangingSign = f.apply(this.properties);
                return this;
        }

        public TreeSet setHangingSign(DeferredBlock<? extends Block> v) {
                this.hangingSign = v;
                return this;
        }

        public TreeSet setWallHangingSign(Function<TreeProperties, DeferredBlock<? extends Block>> f) {
                this.wallHangingSign = f.apply(this.properties);
                return this;
        }

        public TreeSet setWallHangingSign(DeferredBlock<? extends Block> v) {
                this.wallHangingSign = v;
                return this;
        }

        public TreeSet setSignItem(Function<TreeProperties, DeferredItem<? extends Item>> f) {
                this.signItem = f.apply(this.properties);
                return this;
        }

        public TreeSet setSignItem(DeferredItem<? extends Item> v) {
                this.signItem = v;
                return this;
        }

        public TreeSet setHangingSignItem(Function<TreeProperties, DeferredItem<? extends Item>> f) {
                this.hangingSignItem = f.apply(this.properties);
                return this;
        }

        public TreeSet setHangingSignItem(DeferredItem<? extends Item> v) {
                this.hangingSignItem = v;
                return this;
        }

        public TreeSet setItemTag(Function<TagKey<Item>, TagKey<Item>> f) {
                this.itemTag = f.apply(this.itemTag);
                return this;
        }

        public TreeSet setItemTag(TagKey<Item> v) {
                this.itemTag = v;
                return this;
        }

        public TreeSet setBlockTag(Function<TagKey<Block>, TagKey<Block>> f) {
                this.blockTag = f.apply(this.blockTag);
                return this;
        }

        public TreeSet setBlockTag(TagKey<Block> v) {
                this.blockTag = v;
                return this;
        }

        public TreeSet build() {

                var plank = properties.plankColor();
                var log_side = properties.logSideColor();

                var hardness = properties.hardness();
                var resistance = properties.resistance();

                var soundType = properties.soundType();
                var woodType = properties.woodType();
                var blockSetType = properties.blockset();

                var burnable = properties.burnable();

                var particles = properties.particles();
                var type = properties.woodOptionType();

                var grower = properties.grower();

                if (this.log == null)
                        this.log = Material.registerItemBlock(id + type.suffix4(), p -> {
                                p.strength(hardness, resistance).sound(soundType);

                                if (burnable)
                                        p.ignitedByLava();

                                return new RotatedPillarBlock(
                                                p.mapColor(state -> state
                                                                .getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y
                                                                                ? log_side
                                                                                : plank));
                        });

                if (this.strippedLog == null)
                        this.strippedLog = Material.registerItemBlock(
                                        WoodOptionType.STRIPPED + id + type.suffix4(), p -> {
                                                p.strength(hardness, resistance).sound(soundType);

                                                if (burnable)
                                                        p.ignitedByLava();

                                                return new RotatedPillarBlock(p.mapColor(plank));
                                        });

                if (this.wood == null)
                        this.wood = Material.registerItemBlock(id + type.suffix6(), p -> {
                                p.strength(hardness, resistance).sound(soundType);

                                if (burnable)
                                        p.ignitedByLava();

                                return new RotatedPillarBlock(p.mapColor(log_side));
                        });

                if (this.strippedWood == null)
                        this.strippedWood = Material.registerItemBlock(
                                        WoodOptionType.STRIPPED + id + type.suffix6(), p -> {
                                                p.strength(hardness, resistance).sound(soundType);

                                                if (burnable)
                                                        p.ignitedByLava();

                                                return new RotatedPillarBlock(p.mapColor(plank));
                                        });

                if (this.planks == null)
                        this.planks = Material.registerItemBlock(id + "_planks", p -> {
                                p.strength(hardness, resistance).sound(soundType);

                                if (burnable)
                                        p.ignitedByLava();

                                return new Block(p.mapColor(plank));
                        });

                if (this.leaves == null)
                        this.leaves = Material.registerItemBlock(id + "_leaves", p -> new TintedParticleLeavesBlock(
                                        particles.get(),
                                        p.strength(0.2F)
                                                        .sound(SoundType.GRASS)
                                                        .pushReaction(PushReaction.DESTROY)
                                                        .noOcclusion()
                                                        .ignitedByLava()
                                                        .randomTicks()
                                                        .isValidSpawn(Blocks::ocelotOrParrot)
                                                        .isSuffocating((s, l, ps) -> false)
                                                        .isViewBlocking((s, l, ps) -> false)
                                                        .isRedstoneConductor((s, l, ps) -> false)
                                                        .mapColor(MapColor.PLANT)));

                if (this.sapling == null)
                        this.sapling = Material.registerItemBlock(id + "_sapling", p -> new SaplingBlock(
                                        grower.apply(id).build(),
                                        p.pushReaction(PushReaction.DESTROY)
                                                        .instabreak()
                                                        .sound(SoundType.GRASS)
                                                        .noCollision()
                                                        .randomTicks()
                                                        .ignitedByLava()
                                                        .mapColor(MapColor.PLANT)));

                if (this.pottedSapling == null)
                        this.pottedSapling = zBlocks.zTreeBlock.registerBlock(
                                        "potted_" + id + "_sapling",
                                        p -> new FlowerPotBlock(
                                                        () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                                                        sapling,
                                                        p.instabreak()
                                                                        .noOcclusion()
                                                                        .pushReaction(PushReaction.DESTROY)));

                if (this.stairs == null)
                        this.stairs = Material.registerItemBlock(id + "_stairs", p -> new StairBlock(
                                        planks.get().defaultBlockState(),
                                        p.strength(hardness, resistance)
                                                        .sound(soundType)
                                                        .ignitedByLava()
                                                        .mapColor(plank)));

                if (this.slab == null)
                        this.slab = Material.registerItemBlock(id + "_slab", p -> new SlabBlock(
                                        p.strength(hardness, resistance)
                                                        .sound(soundType)
                                                        .ignitedByLava()
                                                        .mapColor(plank)));

                if (this.shelf == null)
                        this.shelf = Material.registerItemBlock(id + "_shelf", p -> new ShelfBlock(
                                        p.strength(hardness, resistance)
                                                        .sound(soundType)
                                                        .ignitedByLava()
                                                        .mapColor(plank)));

                if (this.fence == null)
                        this.fence = Material.registerItemBlock(id + "_fence", p -> new FenceBlock(
                                        p.strength(hardness, resistance)
                                                        .sound(soundType)
                                                        .ignitedByLava()
                                                        .mapColor(plank)));

                if (this.fenceGate == null)
                        this.fenceGate = Material.registerItemBlock(id + "_fence_gate", p -> new FenceGateBlock(
                                        woodType,
                                        p.strength(hardness, resistance)
                                                        .sound(soundType)
                                                        .ignitedByLava()
                                                        .mapColor(plank)));

                if (this.door == null)
                        this.door = Material.registerItemBlock(id + "_door", p -> new DoorBlock(
                                        blockSetType,
                                        p.strength(3.0F)
                                                        .ignitedByLava()
                                                        .mapColor(plank)));

                if (this.trapdoor == null)
                        this.trapdoor = Material.registerItemBlock(id + "_trapdoor", p -> new TrapDoorBlock(
                                        blockSetType,
                                        p.strength(3.0F)
                                                        .ignitedByLava()
                                                        .noOcclusion()
                                                        .mapColor(plank)));

                if (this.button == null)
                        this.button = Material.registerItemBlock(id + "_button", p -> new ButtonBlock(
                                        blockSetType,
                                        30,
                                        p.strength(0.5F)
                                                        .noCollision()
                                                        .ignitedByLava()
                                                        .mapColor(plank)));

                if (this.pressurePlate == null)
                        this.pressurePlate = Material.registerItemBlock(id + "_pressure_plate",
                                        p -> new PressurePlateBlock(
                                                        blockSetType,
                                                        p.strength(0.5F)
                                                                        .ignitedByLava()
                                                                        .mapColor(plank)));

                if (this.sign == null)
                        this.sign = zBlocks.zTreeBlock.registerBlock(id + "_sign", p -> new StandingSignBlock(
                                        woodType,
                                        p.forceSolidOn().noCollision().strength(1.0F)));

                if (this.wallSign == null)
                        this.wallSign = zBlocks.zTreeBlock.registerBlock(id + "_wall_sign", p -> new WallSignBlock(
                                        woodType,
                                        p.forceSolidOn().noCollision().strength(1.0F)));

                if (this.hangingSign == null)
                        this.hangingSign = zBlocks.zTreeBlock.registerBlock(id + "_hanging_sign",
                                        p -> new CeilingHangingSignBlock(
                                                        woodType,
                                                        p.forceSolidOn().noCollision().strength(1.0F)));

                if (this.wallHangingSign == null)
                        this.wallHangingSign = zBlocks.zTreeBlock.registerBlock(
                                        id + "_wall_hanging_sign", p -> new WallHangingSignBlock(
                                                        woodType,
                                                        p.forceSolidOn().noCollision().strength(1.0F)));

                if (this.signItem == null)
                        this.signItem = zItems.zTreeItem.registerItem(id + "_sign",
                                        p -> new SignItem(sign.get(), wallSign.get(), p));

                if (this.hangingSignItem == null)
                        this.hangingSignItem = zItems.zTreeItem.registerItem(
                                        id + "_hanging_sign", p -> new HangingSignItem(
                                                        hangingSign.get(),
                                                        wallHangingSign.get(),
                                                        p));

                if (this.itemTag == null)
                        this.itemTag = RegistryUtils.tagItem(MODULE_ID, id + "_logs");

                if (this.blockTag == null)
                        this.blockTag = RegistryUtils.tagBlock(MODULE_ID, id + "_logs");

                return this;
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
