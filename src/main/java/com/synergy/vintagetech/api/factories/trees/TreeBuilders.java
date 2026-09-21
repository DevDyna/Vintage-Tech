package com.synergy.vintagetech.api.factories.trees;

import java.util.Optional;
import java.util.function.Function;

import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockSetType.PressurePlateSensitivity;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class TreeBuilders {

    public static class BlockSetBuilder {

        private final String id;

        private boolean openByHand = true;
        private boolean openByWind = true;
        private boolean activeByArrow = true;

        private PressurePlateSensitivity sensibility = PressurePlateSensitivity.EVERYTHING;

        private SoundType sound = SoundType.WOOD;

        private SoundEvent doorClose = SoundEvents.WOODEN_DOOR_CLOSE;
        private SoundEvent doorOpen = SoundEvents.WOODEN_DOOR_OPEN;

        private SoundEvent trapdoorClose = SoundEvents.WOODEN_TRAPDOOR_CLOSE;
        private SoundEvent trapdoorOpen = SoundEvents.WOODEN_TRAPDOOR_OPEN;

        private SoundEvent pressureOff = SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF;
        private SoundEvent pressureOn = SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON;

        private SoundEvent buttonOff = SoundEvents.WOODEN_BUTTON_CLICK_OFF;

        private SoundEvent buttonOn = SoundEvents.WOODEN_BUTTON_CLICK_ON;

        private BlockSetBuilder(String id) {
            this.id = id;
        }

        public BlockSetBuilder canOpenByHand(boolean v) {
            this.openByHand = v;
            return this;
        }

        public BlockSetBuilder canActiveByArrow(boolean v) {
            this.activeByArrow = v;
            return this;
        }

        public BlockSetBuilder canOpenByWind(boolean v) {
            this.openByWind = v;
            return this;
        }

        public BlockSetBuilder sensibility(PressurePlateSensitivity v) {
            this.sensibility = v;
            return this;
        }

        public BlockSetBuilder sound(SoundType v) {
            this.sound = v;
            return this;
        }

        public BlockSetBuilder doorClose(SoundEvent v) {
            this.doorClose = v;
            return this;
        }

        public BlockSetBuilder doorOpen(SoundEvent v) {
            this.doorOpen = v;
            return this;
        }

        public BlockSetBuilder trapdoorClose(SoundEvent v) {
            this.trapdoorClose = v;
            return this;
        }

        public BlockSetBuilder trapdoorOpen(SoundEvent v) {
            this.trapdoorOpen = v;
            return this;
        }

        public BlockSetBuilder pressureOff(SoundEvent v) {
            this.pressureOff = v;
            return this;
        }

        public BlockSetBuilder pressureOn(SoundEvent v) {
            this.pressureOn = v;
            return this;
        }

        public BlockSetBuilder buttonOff(SoundEvent v) {
            this.buttonOff = v;
            return this;
        }

        public BlockSetBuilder buttonOn(SoundEvent v) {
            this.buttonOn = v;
            return this;
        }

        public BlockSetType build() {
            return BlockSetType.register(new BlockSetType(
                    id,
                    openByHand, openByWind, activeByArrow,
                    sensibility,
                    sound,
                    doorClose, doorOpen,
                    trapdoorClose, trapdoorOpen,
                    pressureOff, pressureOn,
                    buttonOff, buttonOn));
        }

        /**
         * Default include<br/>
         * <br/>
         * - Oak<br/>
         * <br/>
         * - Spruce<br/>
         * <br/>
         * - Birch<br/>
         * <br/>
         * - Acacia<br/>
         * <br/>
         * - Jungle<br/>
         * <br/>
         * - Dark oak<br/>
         * <br/>
         * - Pale oak<br/>
         * <br/>
         * - Mangrove<br/>
         * <br/>
         */
        public static BlockSetBuilder of(String id) {
            return new BlockSetBuilder(id);
        }

        public final static Function<String, BlockSetBuilder> SIMPLE = BlockSetBuilder::of;

        private static BlockSetBuilder metal(String id) {
            return of(id)
                    .canOpenByHand(false)
                    .canOpenByWind(false)
                    .doorClose(SoundEvents.IRON_DOOR_CLOSE)
                    .doorOpen(SoundEvents.IRON_DOOR_OPEN)
                    .trapdoorClose(SoundEvents.IRON_TRAPDOOR_CLOSE)
                    .trapdoorOpen(SoundEvents.IRON_TRAPDOOR_OPEN)
                    .pressureOff(SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF)
                    .pressureOn(SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON)
                    .buttonOff(SoundEvents.STONE_BUTTON_CLICK_OFF)
                    .buttonOn(SoundEvents.STONE_BUTTON_CLICK_ON);
        }

        /**
         * Include Crimson and Warped Types
         */
        public static BlockSetBuilder nether(String id) {
            return of(id)
                    .sound(SoundType.NETHER_WOOD)
                    .doorClose(SoundEvents.NETHER_WOOD_DOOR_CLOSE)
                    .doorOpen(SoundEvents.NETHER_WOOD_DOOR_OPEN)
                    .trapdoorClose(SoundEvents.NETHER_WOOD_TRAPDOOR_CLOSE)
                    .trapdoorOpen(SoundEvents.NETHER_WOOD_TRAPDOOR_OPEN)
                    .pressureOff(SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF)
                    .pressureOn(SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_ON)
                    .buttonOff(SoundEvents.NETHER_WOOD_BUTTON_CLICK_OFF)
                    .buttonOn(SoundEvents.NETHER_WOOD_BUTTON_CLICK_ON);
        }

        public static BlockSetBuilder iron(String id) {
            return metal(id).sound(SoundType.IRON).canActiveByArrow(false);
        }

        public static BlockSetBuilder gold(String id) {
            return metal(id).sound(SoundType.METAL).canActiveByArrow(true);
        }

        public static BlockSetBuilder copper(String id) {
            return of(id)
                    .canOpenByWind(false)
                    .sound(SoundType.COPPER)
                    .doorClose(SoundEvents.COPPER_DOOR_CLOSE)
                    .doorOpen(SoundEvents.COPPER_DOOR_OPEN)
                    .trapdoorClose(SoundEvents.COPPER_TRAPDOOR_CLOSE)
                    .trapdoorOpen(SoundEvents.COPPER_TRAPDOOR_OPEN)
                    .pressureOff(SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF)
                    .pressureOn(SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON)
                    .buttonOff(SoundEvents.STONE_BUTTON_CLICK_OFF)
                    .buttonOn(SoundEvents.STONE_BUTTON_CLICK_ON);
        }

        /*
         * Include Stone and Blackstone Types
         */
        public static BlockSetBuilder stone(String id) {
            return of(id)
                    .canOpenByWind(false)
                    .sensibility(PressurePlateSensitivity.MOBS)
                    .sound(SoundType.STONE)
                    .doorClose(SoundEvents.IRON_DOOR_CLOSE)
                    .doorOpen(SoundEvents.IRON_DOOR_OPEN)
                    .trapdoorClose(SoundEvents.IRON_TRAPDOOR_CLOSE)
                    .trapdoorOpen(SoundEvents.IRON_TRAPDOOR_OPEN)
                    .pressureOff(SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF)
                    .pressureOn(SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON)
                    .buttonOff(SoundEvents.STONE_BUTTON_CLICK_OFF)
                    .buttonOn(SoundEvents.STONE_BUTTON_CLICK_ON);
        }

        public static BlockSetBuilder cherry(String id) {
            return of(id)
                    .sound(SoundType.CHERRY_WOOD)
                    .doorClose(SoundEvents.CHERRY_WOOD_DOOR_CLOSE)
                    .doorOpen(SoundEvents.CHERRY_WOOD_DOOR_OPEN)
                    .trapdoorClose(SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE)
                    .trapdoorOpen(SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN)
                    .pressureOff(SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF)
                    .pressureOn(SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON)
                    .buttonOff(SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF)
                    .buttonOn(SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON);
        }

        public static BlockSetBuilder bamboo(String id) {
            return of(id)
                    .sound(SoundType.BAMBOO_WOOD)
                    .doorClose(SoundEvents.BAMBOO_WOOD_DOOR_CLOSE)
                    .doorOpen(SoundEvents.BAMBOO_WOOD_DOOR_OPEN)
                    .trapdoorClose(SoundEvents.BAMBOO_WOOD_TRAPDOOR_CLOSE)
                    .trapdoorOpen(SoundEvents.BAMBOO_WOOD_TRAPDOOR_OPEN)
                    .pressureOff(SoundEvents.BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF)
                    .pressureOn(SoundEvents.BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON)
                    .buttonOff(SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_OFF)
                    .buttonOn(SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_ON);
        }
    }

    public static class TreeGrowerBuilder {

        private final String id;

        private float secondaryChance = 0.5f;

        private Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree = Optional.empty();
        private Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree = Optional.empty();
        private Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree = Optional.empty();
        private Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree = Optional.empty();
        private Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers = Optional.empty();
        private Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryFlowers = Optional.empty();

        private TreeGrowerBuilder(String id) {
            this.id = id;
        }

        public static TreeGrowerBuilder of(String id) {
            return new TreeGrowerBuilder(id);
        }

        public static Function<String, TreeGrowerBuilder> of() {
            return TreeGrowerBuilder::of;
        }

        public TreeGrowerBuilder secondary(float c) {
            this.secondaryChance = c;
            return this;
        }

        public TreeGrowerBuilder megaTree(ResourceKey<ConfiguredFeature<?, ?>> megaTree) {
            this.megaTree = Optional.ofNullable(megaTree);
            return this;
        }

        public TreeGrowerBuilder secondaryMegaTree(ResourceKey<ConfiguredFeature<?, ?>> secondaryMegaTree) {
            this.secondaryMegaTree = Optional.ofNullable(secondaryMegaTree);
            return this;
        }

        public TreeGrowerBuilder tree(ResourceKey<ConfiguredFeature<?, ?>> tree) {
            this.tree = Optional.ofNullable(tree);
            return this;
        }

        public TreeGrowerBuilder secondaryTree(ResourceKey<ConfiguredFeature<?, ?>> secondaryTree) {
            this.secondaryTree = Optional.ofNullable(secondaryTree);
            return this;
        }

        public TreeGrowerBuilder flowers(ResourceKey<ConfiguredFeature<?, ?>> flowers) {
            this.flowers = Optional.ofNullable(flowers);
            return this;
        }

        public TreeGrowerBuilder secondaryFlowers(ResourceKey<ConfiguredFeature<?, ?>> secondaryFlowers) {
            this.secondaryFlowers = Optional.ofNullable(secondaryFlowers);
            return this;
        }

        public TreeGrower build() {
            return new TreeGrower(
                    id, secondaryChance,
                    megaTree, secondaryMegaTree,
                    tree, secondaryTree,
                    flowers, secondaryFlowers);
        }
    }

    public static class WoodTypeBuilder {

        private final String id;

        private BlockSetType type;
        private SoundType sound = SoundType.WOOD;
        private SoundType hangingSound = SoundType.HANGING_SIGN;
        private SoundEvent gateClose = SoundEvents.FENCE_GATE_CLOSE;
        private SoundEvent gateOpen = SoundEvents.FENCE_GATE_OPEN;

        private WoodTypeBuilder(String id) {
            this.id = id;
        }

        public WoodTypeBuilder setType(BlockSetType v) {
            this.type = v;
            return this;
        }

        public WoodTypeBuilder sound(SoundType v) {
            this.sound = v;
            return this;
        }

        public WoodTypeBuilder hangingSignSound(SoundType v) {
            this.hangingSound = v;
            return this;
        }

        public WoodTypeBuilder fenceGateClose(SoundEvent v) {
            this.gateClose = v;
            return this;
        }

        public WoodTypeBuilder fenceGateOpen(SoundEvent v) {
            this.gateOpen = v;
            return this;
        }

        public WoodType build() {
            return WoodType.register(
                    new WoodType(id, type,
                            sound, hangingSound,
                            gateClose, gateOpen));
        }

        public static WoodTypeBuilder of(String id) {
            return new WoodTypeBuilder(id);
        }

        public final static Function<String, WoodTypeBuilder> SIMPLE = WoodTypeBuilder::of;

        public static WoodTypeBuilder generic(String id) {
            return of(id)
                    .sound(SoundType.WOOD)
                    .hangingSignSound(SoundType.HANGING_SIGN)
                    .fenceGateClose(SoundEvents.FENCE_GATE_CLOSE)
                    .fenceGateOpen(SoundEvents.FENCE_GATE_OPEN);
        }

        public static WoodTypeBuilder cherry(String id) {
            return generic(id)
                    .sound(SoundType.CHERRY_WOOD)
                    .hangingSignSound(SoundType.CHERRY_WOOD_HANGING_SIGN)
                    .fenceGateClose(SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE)
                    .fenceGateOpen(SoundEvents.CHERRY_WOOD_FENCE_GATE_OPEN);
        }

        public static WoodTypeBuilder nether(String id) {
            return generic(id)
                    .sound(SoundType.NETHER_WOOD)
                    .hangingSignSound(SoundType.NETHER_WOOD_HANGING_SIGN)
                    .fenceGateClose(SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE)
                    .fenceGateOpen(SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN);
        }

        public static WoodTypeBuilder bamboo(String id) {
            return generic(id)
                    .sound(SoundType.BAMBOO_WOOD)
                    .hangingSignSound(SoundType.BAMBOO_WOOD_HANGING_SIGN)
                    .fenceGateClose(SoundEvents.BAMBOO_WOOD_FENCE_GATE_CLOSE)
                    .fenceGateOpen(SoundEvents.BAMBOO_WOOD_FENCE_GATE_OPEN);
        }

    }

}
