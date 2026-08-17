package com.synergy.vintagetech.init.types;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.RegistryUtils;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;

public class zTags {

        public static void register(IEventBus bus) {
                zTags.Blocks.register(bus);
                zTags.Items.register(bus);
                zTags.Entities.register(bus);
                zTags.Biomes.register(bus);
        }

        public class Blocks {

                public static void register(IEventBus bus) {
                }

                public static final TagKey<Block> SAW_DENY_BREAK = RegistryUtils
                                .tagBlock(MODULE_ID, "saw/destroy_blacklist");

                public static final TagKey<Block> MINEABLE_WITH_SAW = RegistryUtils
                                .tagBlock("minecraft", "mineable/saw");

                public static final TagKey<Block> EVAPORATION_BASIC_HEATER = RegistryUtils
                                .tagBlock(MODULE_ID, "evaporation_basin/heater");

                public static final TagKey<Block> DRYING_RACK_HEATER = RegistryUtils
                                .tagBlock(MODULE_ID, "drying_rack/heater");

                public static final TagKey<Block> SUPPORT_SOYBEANS_PLANT = RegistryUtils
                                .tagBlock(MODULE_ID, "support/soybeans");

                public static final TagKey<Block> SUPPORT_CAVE_WHEAT_PLANT = RegistryUtils
                                .tagBlock(MODULE_ID, "support/cave_wheat");

                public static final TagKey<Block> SUPPORT_HEMP_PLANT = RegistryUtils
                                .tagBlock(MODULE_ID, "support/hemp");

                public static final TagKey<Block> SUPPORT_NATURAL_HEMP_PLANT = RegistryUtils
                                .tagBlock(MODULE_ID, "support/natural_hemp");

                public static final TagKey<Block> SUPPORT_NATURAL_SOYBEANS_PLANT = RegistryUtils
                                .tagBlock(MODULE_ID, "support/natural_soybeans");

                public static final TagKey<Block> SUPPORT_LAVENDER = RegistryUtils
                                .tagBlock(MODULE_ID, "support/lavender");

                public static final TagKey<Block> SUPPORT_ALOE = RegistryUtils
                                .tagBlock(MODULE_ID, "support/aloe");

                public static final TagKey<Block> SUPPORT_BLUEBERRY = RegistryUtils
                                .tagBlock(MODULE_ID, "support/blueberry");

                public static final TagKey<Block> TRANSMISSION = RegistryUtils
                                .tagBlock(MODULE_ID, "rpm/transmission");

                public static final TagKey<Block> GENERATOR = RegistryUtils
                                .tagBlock(MODULE_ID, "rpm/generator");

                public static final TagKey<Block> CONSUMER = RegistryUtils
                                .tagBlock(MODULE_ID, "rpm/consumer");

                public static final TagKey<Block> TREE_TAP_LEAVES = RegistryUtils
                                .tagBlock(MODULE_ID, "treetap/valid_leaves");

                public static final TagKey<Block> TREE_TAP_LOGS = RegistryUtils
                                .tagBlock(MODULE_ID, "treetap/valid_logs");

                public static final TagKey<Block> SAW_GENERATOR_BLOCKS = RegistryUtils
                                .tagBlock(MODULE_ID, "saw/generator_blocks");

                public static final TagKey<Block> ROPE_IGNORE_CONNECTION = RegistryUtils
                                .tagBlock(MODULE_ID, "rope/ignore_connection");

                public static final TagKey<Block> BEAM_NORMAL = RegistryUtils
                                .tagBlock(MODULE_ID, "beam/normal");

                public static final TagKey<Block> BEAM_STRIPPED = RegistryUtils
                                .tagBlock(MODULE_ID, "beam/stripped");

                public static final TagKey<Block> BEAMS = RegistryUtils
                                .tagBlock(MODULE_ID, "beam");

                public static final TagKey<Block> IRONWOOD_LOGS = RegistryUtils
                                .tagBlock(MODULE_ID, "ironwood_logs");

                public static final TagKey<Block> CRUCIBLE_HEAT_SOURCES = RegistryUtils
                                .tagBlock(MODULE_ID, "crucible/heat_sources");

                public static final TagKey<Block> CHEESE_BOOSTER = RegistryUtils
                                .tagBlock(MODULE_ID, "cheese/booster");

        }

        public class Items {

                public static void register(IEventBus bus) {
                }

                public static final TagKey<Item> IRONWOOD_LOGS = RegistryUtils
                                .tagItem(MODULE_ID, "ironwood_logs");

                public static final TagKey<Item> BEAM_NORMAL = RegistryUtils
                                .tagItem(MODULE_ID, "beam/normal");

                public static final TagKey<Item> BEAM_STRIPPED = RegistryUtils
                                .tagItem(MODULE_ID, "beam/stripped");

                public static final TagKey<Item> BEAMS = RegistryUtils
                                .tagItem(MODULE_ID, "beam");

                public static final TagKey<Item> STONE_SLABS = RegistryUtils
                                .tagItem("c", "slabs/stone");

                public static final TagKey<Item> RECYCLE_COPPER_1 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/copper/1");
                public static final TagKey<Item> RECYCLE_COPPER_2 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/copper/2");
                public static final TagKey<Item> RECYCLE_COPPER_3 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/copper/3");
                public static final TagKey<Item> RECYCLE_COPPER_4 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/copper/4");
                public static final TagKey<Item> RECYCLE_COPPER_5 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/copper/5");
                public static final TagKey<Item> RECYCLE_COPPER_6 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/copper/6");
                public static final TagKey<Item> RECYCLE_COPPER_7 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/copper/7");
                public static final TagKey<Item> RECYCLE_COPPER_8 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/copper/8");
                public static final TagKey<Item> RECYCLE_COPPER_9 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/copper/9");

                public static final TagKey<Item> RECYCLE_IRON_1 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/iron/1");
                public static final TagKey<Item> RECYCLE_IRON_2 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/iron/2");
                public static final TagKey<Item> RECYCLE_IRON_3 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/iron/3");
                public static final TagKey<Item> RECYCLE_IRON_4 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/iron/4");
                public static final TagKey<Item> RECYCLE_IRON_5 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/iron/5");
                public static final TagKey<Item> RECYCLE_IRON_6 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/iron/6");
                public static final TagKey<Item> RECYCLE_IRON_7 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/iron/7");
                public static final TagKey<Item> RECYCLE_IRON_8 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/iron/8");
                public static final TagKey<Item> RECYCLE_IRON_9 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/iron/9");

                public static final TagKey<Item> RECYCLE_GOLD_1 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/gold/1");
                public static final TagKey<Item> RECYCLE_GOLD_2 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/gold/2");
                public static final TagKey<Item> RECYCLE_GOLD_3 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/gold/3");
                public static final TagKey<Item> RECYCLE_GOLD_4 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/gold/4");
                public static final TagKey<Item> RECYCLE_GOLD_5 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/gold/5");
                public static final TagKey<Item> RECYCLE_GOLD_6 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/gold/6");
                public static final TagKey<Item> RECYCLE_GOLD_7 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/gold/7");
                public static final TagKey<Item> RECYCLE_GOLD_8 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/gold/8");
                public static final TagKey<Item> RECYCLE_GOLD_9 = RegistryUtils.tagItem(MODULE_ID,
                                "recipe_recycle/gold/9");

                public static final TagKey<Item> WINDMILL_REPAIR = RegistryUtils.tagItem(MODULE_ID,
                                "windmill/repair");

                public static final TagKey<Item> BASKET_DENY = RegistryUtils.tagItem(MODULE_ID,
                                "basket/deny");

                public static final TagKey<Item> CHEESE_SEALER = RegistryUtils.tagItem(MODULE_ID,
                                "cheese/sealer");

                public static final TagKey<Item> CHEESE_UNSEALER = RegistryUtils.tagItem(MODULE_ID,
                                "cheese/unsealer");

                public static final TagKey<Item> SALT_DUST = RegistryUtils.tagItem("c",
                                "dusts/salt");

        }

        public class Biomes {

                public static void register(IEventBus bus) {
                }

                public static final TagKey<Biome> LAVENDER_SPAWN = RegistryUtils.tagBiome(MODULE_ID,
                                "worldgen/lavender");

                public static final TagKey<Biome> ALOE_SPAWN = RegistryUtils.tagBiome(MODULE_ID,
                                "worldgen/aloe");

                public static final TagKey<Biome> BLUEBERRIES_SPAWN = RegistryUtils.tagBiome(MODULE_ID,
                                "worldgen/blueberries");

                public static final TagKey<Biome> CAVE_WHEAT_SPAWN = RegistryUtils.tagBiome(MODULE_ID,
                                "worldgen/cave_wheat");

                public static final TagKey<Biome> IRONWOOD_TREE_SPAWN = RegistryUtils.tagBiome(MODULE_ID,
                                "worldgen/ironwood_tree");

                public static final TagKey<Biome> HEMP_SPAWN = RegistryUtils.tagBiome(MODULE_ID,
                                "worldgen/hemp");

                public static final TagKey<Biome> SOYBEANS_SPAWN = RegistryUtils.tagBiome(MODULE_ID,
                                "worldgen/soybeans");

        }

        public class Entities {

                public static void register(IEventBus bus) {

                }

                public static final TagKey<EntityType<?>> CRUSHING_TUB_ALLOW_CRUSHING = RegistryUtils.tagEntity(
                                MODULE_ID,
                                "crushing_tub/allow_crushing");

                public static final TagKey<EntityType<?>> SAW_DENY_DAMAGING = RegistryUtils.tagEntity(MODULE_ID,
                                "saw/deny_damaging");

                public static final TagKey<EntityType<?>> FAN_DENY_MOTION = RegistryUtils.tagEntity(MODULE_ID,
                                "fan/deny_motion");

        }
}
