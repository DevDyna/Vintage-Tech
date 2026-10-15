package com.synergy.vintagetech.init.types;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.api.factories.beams.BeamFactory;
import com.synergy.vintagetech.api.factories.beams.BeamSet;
import com.synergy.vintagetech.api.factories.trees.TreeOptions.WoodType;

import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;

public class zBeams {

        public static void register(IEventBus bus) {
        }

        public static final BeamSet OAK = BeamFactory.register(
                        "oak", WoodType.TREE,
                        () -> Blocks.OAK_LOG,
                        () -> Blocks.STRIPPED_OAK_LOG,
                        x.mcLoc("block/oak_log"),
                        x.mcLoc("block/stripped_oak_log"));

        public static final BeamSet SPRUCE = BeamFactory.register(
                        "spruce", WoodType.TREE,
                        () -> Blocks.SPRUCE_LOG,
                        () -> Blocks.STRIPPED_SPRUCE_LOG,
                        x.mcLoc("block/spruce_log"),
                        x.mcLoc("block/stripped_spruce_log"));

        public static final BeamSet BIRCH = BeamFactory.register(
                        "birch", WoodType.TREE,
                        () -> Blocks.BIRCH_LOG,
                        () -> Blocks.STRIPPED_BIRCH_LOG,
                        x.mcLoc("block/birch_log"),
                        x.mcLoc("block/stripped_birch_log"));

        public static final BeamSet JUNGLE = BeamFactory.register(
                        "jungle", WoodType.TREE,
                        () -> Blocks.JUNGLE_LOG,
                        () -> Blocks.STRIPPED_JUNGLE_LOG,
                        x.mcLoc("block/jungle_log"),
                        x.mcLoc("block/stripped_jungle_log"));

        public static final BeamSet ACACIA = BeamFactory.register(
                        "acacia", WoodType.TREE,
                        () -> Blocks.ACACIA_LOG,
                        () -> Blocks.STRIPPED_ACACIA_LOG,
                        x.mcLoc("block/acacia_log"),
                        x.mcLoc("block/stripped_acacia_log"));

        public static final BeamSet DARK_OAK = BeamFactory.register(
                        "dark_oak", WoodType.TREE,
                        () -> Blocks.DARK_OAK_LOG,
                        () -> Blocks.STRIPPED_DARK_OAK_LOG,
                        x.mcLoc("block/dark_oak_log"),
                        x.mcLoc("block/stripped_dark_oak_log"));

        public static final BeamSet MANGROVE = BeamFactory.register(
                        "mangrove", WoodType.TREE,
                        () -> Blocks.MANGROVE_LOG,
                        () -> Blocks.STRIPPED_MANGROVE_LOG,
                        x.mcLoc("block/mangrove_log"),
                        x.mcLoc("block/stripped_mangrove_log"));

        public static final BeamSet CHERRY = BeamFactory.register(
                        "cherry", WoodType.TREE,
                        () -> Blocks.CHERRY_LOG,
                        () -> Blocks.STRIPPED_CHERRY_LOG,
                        x.mcLoc("block/cherry_log"),
                        x.mcLoc("block/stripped_cherry_log"));

        public static final BeamSet PALE_OAK = BeamFactory.register(
                        "pale_oak", WoodType.TREE,
                        () -> Blocks.PALE_OAK_LOG,
                        () -> Blocks.STRIPPED_PALE_OAK_LOG,
                        x.mcLoc("block/pale_oak_log"),
                        x.mcLoc("block/stripped_pale_oak_log"));

        public static final BeamSet BAMBOO = BeamFactory.register(
                        "bamboo", WoodType.OTHER,
                        () -> Blocks.BAMBOO_BLOCK,
                        () -> Blocks.STRIPPED_BAMBOO_BLOCK,
                        x.rl(MODULE_ID, "block/beam/normal/bamboo"),
                        x.rl(MODULE_ID, "block/beam/stripped/bamboo"));

        public static final BeamSet CRIMSON = BeamFactory.register(
                        "crimson",
                        WoodType.FUNGUS,
                        () -> Blocks.CRIMSON_STEM,
                        () -> Blocks.STRIPPED_CRIMSON_STEM,
                        x.mcLoc("block/crimson_stem"),
                        x.mcLoc("block/stripped_crimson_stem"));

        public static final BeamSet WARPED = BeamFactory.register(
                        "warped",
                        WoodType.FUNGUS,
                        () -> Blocks.WARPED_STEM,
                        () -> Blocks.STRIPPED_WARPED_STEM,
                        x.mcLoc("block/warped_stem"),
                        x.mcLoc("block/stripped_warped_stem"));

        public static final BeamSet IRONWOOD = BeamFactory.register(
                        "ironwood",
                        WoodType.TREE,
                        () -> zTrees.IRONWOOD.log().get(),
                        () -> zTrees.IRONWOOD.strippedLog().get(),
                        x.rl(MODULE_ID, "block/ironwood_log"),
                        x.rl(MODULE_ID, "block/stripped_ironwood_log"));

        public static final BeamSet OLIVE = BeamFactory.register(
                        "olive",
                        WoodType.TREE,
                        () -> zTrees.OLIVE.log().get(),
                        () -> zTrees.OLIVE.strippedLog().get(),
                        x.rl(MODULE_ID, "block/olive_log"),
                        x.rl(MODULE_ID, "block/stripped_olive_log"));
}
