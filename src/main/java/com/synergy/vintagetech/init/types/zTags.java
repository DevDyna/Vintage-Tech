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

    }

    public class Items {

        public static void register(IEventBus bus) {
        }

    }

    public class Entities {

        public static void register(IEventBus bus) {

        }

        public static final TagKey<EntityType<?>> CRUSHING_TUB_ALLOW = RegistryUtils.tagEntity(MODULE_ID,
                "crushing_tub_allow");

    }
}
