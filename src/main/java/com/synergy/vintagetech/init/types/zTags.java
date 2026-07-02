package com.synergy.vintagetech.init.types;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.RegistryUtils;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;

public class zTags {

    public static void register(IEventBus bus) {
        zTags.Blocks.register(bus);
        zTags.Items.register(bus);
        zTags.Entities.register(bus);
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

    }

    public class Items {

        public static void register(IEventBus bus) {
        }

    }

    public class Entities {

        public static void register(IEventBus bus) {

        }

        public static final TagKey<EntityType<?>> CRUSHING_TUB_ALLOW_CRUSHING = RegistryUtils.tagEntity(MODULE_ID,
                "crushing_tub/allow_crushing");

        public static final TagKey<EntityType<?>> SAW_DENY_DAMAGING = RegistryUtils.tagEntity(MODULE_ID,
                "saw/deny_damaging");

        public static final TagKey<EntityType<?>> FAN_DENY_MOTION = RegistryUtils.tagEntity(MODULE_ID,
                "fan/deny_motion");

    }
}
