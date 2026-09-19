package com.synergy.vintagetech.api.factories.beams;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.function.Supplier;

import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.init.types.zBlocks;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public enum WoodType {

        OAK("oak", TreeType.TREE,
                        () -> Blocks.OAK_LOG, () -> Blocks.STRIPPED_OAK_LOG,
                        x.mcLoc("block/oak_log"), x.mcLoc("block/stripped_oak_log")),

        SPRUCE("spruce", TreeType.TREE,
                        () -> Blocks.SPRUCE_LOG, () -> Blocks.STRIPPED_SPRUCE_LOG,
                        x.mcLoc("block/spruce_log"), x.mcLoc("block/stripped_spruce_log")),

        BIRCH("birch", TreeType.TREE,
                        () -> Blocks.BIRCH_LOG, () -> Blocks.STRIPPED_BIRCH_LOG,
                        x.mcLoc("block/birch_log"), x.mcLoc("block/stripped_birch_log")),

        JUNGLE("jungle", TreeType.TREE,
                        () -> Blocks.JUNGLE_LOG, () -> Blocks.STRIPPED_JUNGLE_LOG,
                        x.mcLoc("block/jungle_log"), x.mcLoc("block/stripped_jungle_log")),

        ACACIA("acacia", TreeType.TREE,
                        () -> Blocks.ACACIA_LOG, () -> Blocks.STRIPPED_ACACIA_LOG,
                        x.mcLoc("block/acacia_log"), x.mcLoc("block/stripped_acacia_log")),

        DARK_OAK("dark_oak", TreeType.TREE,
                        () -> Blocks.DARK_OAK_LOG, () -> Blocks.STRIPPED_DARK_OAK_LOG,
                        x.mcLoc("block/dark_oak_log"), x.mcLoc("block/stripped_dark_oak_log")),

        MANGROVE("mangrove", TreeType.TREE,
                        () -> Blocks.MANGROVE_LOG, () -> Blocks.STRIPPED_MANGROVE_LOG,
                        x.mcLoc("block/mangrove_log"), x.mcLoc("block/stripped_mangrove_log")),

        CHERRY("cherry", TreeType.TREE,
                        () -> Blocks.CHERRY_LOG, () -> Blocks.STRIPPED_CHERRY_LOG,
                        x.mcLoc("block/cherry_log"), x.mcLoc("block/stripped_cherry_log")),

        PALE_OAK("pale_oak", TreeType.TREE,
                        () -> Blocks.PALE_OAK_LOG, () -> Blocks.STRIPPED_PALE_OAK_LOG,
                        x.mcLoc("block/pale_oak_log"), x.mcLoc("block/stripped_pale_oak_log")),

        BAMBOO("bamboo", TreeType.SPECIAL,
                        () -> Blocks.BAMBOO_BLOCK, () -> Blocks.STRIPPED_BAMBOO_BLOCK,
                        x.rl(MODULE_ID, "block/beam/normal/bamboo"), x.rl(MODULE_ID, "block/beam/stripped/bamboo")),

        CRIMSON("crimson", TreeType.FUNGUS,
                        () -> Blocks.CRIMSON_STEM, () -> Blocks.STRIPPED_CRIMSON_STEM,
                        x.mcLoc("block/crimson_stem"), x.mcLoc("block/stripped_crimson_stem")),

        WARPED("warped", TreeType.FUNGUS,
                        () -> Blocks.WARPED_STEM, () -> Blocks.STRIPPED_WARPED_STEM,
                        x.mcLoc("block/warped_stem"), x.mcLoc("block/stripped_warped_stem")),

        IRONWOOD("ironwood", TreeType.TREE,
                        () -> zBlocks.IRONWOOD_LOG.get(), () -> zBlocks.STRIPPED_IRONWOOD_LOG.get(),
                        x.rl(MODULE_ID, "block/ironwood_log"), x.rl(MODULE_ID, "block/stripped_ironwood_log"));

        private final String name;
        private final TreeType type;

        private final Supplier<Block> log;
        private final Supplier<Block> strippedLog;

        private final Identifier logTexture;
        private final Identifier strippedLogTexture;

        // supplier required to prevent net.neoforged.fml.ModLoader.waitForFuture(...)
        WoodType(String name, TreeType type, Supplier<Block> log, Supplier<Block> strippedLog, Identifier logTexture,
                        Identifier strippedLogTexture) {
                this.name = name;
                this.type = type;
                this.log = log;
                this.strippedLog = strippedLog;
                this.logTexture = logTexture;
                this.strippedLogTexture = strippedLogTexture;
        }

        public String id() {
                return name;
        }

        public boolean isTree() {
                return type.equals(TreeType.TREE);
        }

        public boolean isFungus() {
                return type.equals(TreeType.FUNGUS);
        }

        public boolean isSpecial() {
                return type.equals(TreeType.SPECIAL);
        }

        public Block log() {
                return log.get();
        }

        public Block stripped() {
                return strippedLog.get();
        }

        public Identifier logTexture() {
                return logTexture;
        }

        public Identifier strippedTexture() {
                return strippedLogTexture;
        }

        private enum TreeType {
                TREE,
                FUNGUS,
                SPECIAL
        }

}