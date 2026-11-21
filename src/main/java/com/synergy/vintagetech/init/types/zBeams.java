package com.synergy.vintagetech.init.types;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.api.factories.beams.BeamFactory;
import com.synergy.vintagetech.api.factories.beams.BeamSet;
import com.synergy.vintagetech.api.factories.trees.TreeOptions.WoodOptionType;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;

public class zBeams {

        public static void register(IEventBus bus) {
        }

        public static final BeamSet OAK = BeamFactory.register(
                        "oak", WoodOptionType.TREE,
                        () -> Blocks.OAK_LOG,
                        () -> Blocks.STRIPPED_OAK_LOG,
                        x.mcLoc("block/oak_log"),
                        x.mcLoc("block/stripped_oak_log"),
                        MapColor.WOOD,
                        MapColor.PODZOL);

        public static final BeamSet SPRUCE = BeamFactory.register(
                        "spruce", WoodOptionType.TREE,
                        () -> Blocks.SPRUCE_LOG,
                        () -> Blocks.STRIPPED_SPRUCE_LOG,
                        x.mcLoc("block/spruce_log"),
                        x.mcLoc("block/stripped_spruce_log"),
                        MapColor.PODZOL,
                        MapColor.PODZOL);

        public static final BeamSet BIRCH = BeamFactory.register(
                        "birch", WoodOptionType.TREE,
                        () -> Blocks.BIRCH_LOG,
                        () -> Blocks.STRIPPED_BIRCH_LOG,
                        x.mcLoc("block/birch_log"),
                        x.mcLoc("block/stripped_birch_log"),
                        MapColor.SAND,
                        MapColor.SAND);

        public static final BeamSet JUNGLE = BeamFactory.register(
                        "jungle", WoodOptionType.TREE,
                        () -> Blocks.JUNGLE_LOG,
                        () -> Blocks.STRIPPED_JUNGLE_LOG,
                        x.mcLoc("block/jungle_log"),
                        x.mcLoc("block/stripped_jungle_log"),
                        MapColor.DIRT,
                        MapColor.DIRT);

        public static final BeamSet ACACIA = BeamFactory.register(
                        "acacia", WoodOptionType.TREE,
                        () -> Blocks.ACACIA_LOG,
                        () -> Blocks.STRIPPED_ACACIA_LOG,
                        x.mcLoc("block/acacia_log"),
                        x.mcLoc("block/stripped_acacia_log"),
                        MapColor.COLOR_ORANGE,
                        MapColor.COLOR_ORANGE);

        public static final BeamSet DARK_OAK = BeamFactory.register(
                        "dark_oak", WoodOptionType.TREE,
                        () -> Blocks.DARK_OAK_LOG,
                        () -> Blocks.STRIPPED_DARK_OAK_LOG,
                        x.mcLoc("block/dark_oak_log"),
                        x.mcLoc("block/stripped_dark_oak_log"),
                        MapColor.COLOR_BROWN,
                        MapColor.COLOR_BROWN);

        public static final BeamSet MANGROVE = BeamFactory.register(
                        "mangrove", WoodOptionType.TREE,
                        () -> Blocks.MANGROVE_LOG,
                        () -> Blocks.STRIPPED_MANGROVE_LOG,
                        x.mcLoc("block/mangrove_log"),
                        x.mcLoc("block/stripped_mangrove_log"),
                        MapColor.COLOR_RED,
                        MapColor.COLOR_RED);

        public static final BeamSet CHERRY = BeamFactory.register(
                        "cherry", WoodOptionType.TREE,
                        () -> Blocks.CHERRY_LOG,
                        () -> Blocks.STRIPPED_CHERRY_LOG,
                        x.mcLoc("block/cherry_log"),
                        x.mcLoc("block/stripped_cherry_log"),
                        MapColor.TERRACOTTA_WHITE,
                        MapColor.TERRACOTTA_PINK);

        public static final BeamSet PALE_OAK = BeamFactory.register(
                        "pale_oak", WoodOptionType.TREE,
                        () -> Blocks.PALE_OAK_LOG,
                        () -> Blocks.STRIPPED_PALE_OAK_LOG,
                        x.mcLoc("block/pale_oak_log"),
                        x.mcLoc("block/stripped_pale_oak_log"),
                        Blocks.PALE_OAK_PLANKS.defaultMapColor(),
                        Blocks.PALE_OAK_PLANKS.defaultMapColor());

        public static final BeamSet BAMBOO = BeamFactory.register(
                        "bamboo", WoodOptionType.OTHER,
                        () -> Blocks.BAMBOO_BLOCK,
                        () -> Blocks.STRIPPED_BAMBOO_BLOCK,
                        x.rl(MODULE_ID, "block/beam/normal/bamboo"),
                        x.rl(MODULE_ID, "block/beam/stripped/bamboo"),
                        MapColor.COLOR_YELLOW,
                        MapColor.COLOR_YELLOW);

        public static final BeamSet CRIMSON = BeamFactory.register(
                        "crimson",
                        WoodOptionType.FUNGUS,
                        () -> Blocks.CRIMSON_STEM,
                        () -> Blocks.STRIPPED_CRIMSON_STEM,
                        x.mcLoc("block/crimson_stem"),
                        x.mcLoc("block/stripped_crimson_stem"),
                        MapColor.CRIMSON_STEM,
                        MapColor.CRIMSON_STEM);

        public static final BeamSet WARPED = BeamFactory.register(
                        "warped",
                        WoodOptionType.FUNGUS,
                        () -> Blocks.WARPED_STEM,
                        () -> Blocks.STRIPPED_WARPED_STEM,
                        x.mcLoc("block/warped_stem"),
                        x.mcLoc("block/stripped_warped_stem"),
                        MapColor.WARPED_STEM,
                        MapColor.WARPED_STEM);

        public static final BeamSet IRONWOOD = BeamFactory.register(
                        "ironwood",
                        WoodOptionType.TREE,
                        () -> zTrees.IRONWOOD.getLog().get(),
                        () -> zTrees.IRONWOOD.getStrippedLog().get(),
                        x.rl(MODULE_ID, "block/ironwood_log"),
                        x.rl(MODULE_ID, "block/stripped_ironwood_log"),
                        MapColor.COLOR_LIGHT_GRAY,
                        MapColor.COLOR_BLACK);

        public static final BeamSet OLIVE = BeamFactory.register(
                        "olive",
                        WoodOptionType.TREE,
                        () -> zTrees.OLIVE.getLog().get(),
                        () -> zTrees.OLIVE.getStrippedLog().get(),
                        x.rl(MODULE_ID, "block/olive_log"),
                        x.rl(MODULE_ID, "block/stripped_olive_log"),
                        MapColor.COLOR_ORANGE,
                        MapColor.COLOR_GRAY);
}
