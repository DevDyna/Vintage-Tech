package com.synergy.vintagetech.init.types;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.RegistryUtils;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.world.BiomeModifier;

public class zWorldGenFeatures {
        public static void register(IEventBus bus) {
        }

        public class PlacedFeatures {
                public static final ResourceKey<PlacedFeature> IRONWOOD = RegistryUtils
                                .createPlacedFeature(MODULE_ID,"ironwood_tree");

                public static final ResourceKey<PlacedFeature> CAVE_WHEAT = RegistryUtils
                                .createPlacedFeature(MODULE_ID, "cave_wheat");

                public static final ResourceKey<PlacedFeature> WILD_CAVE_WHEAT = RegistryUtils
                                .createPlacedFeature(MODULE_ID, "wild_cave_wheat");

        }

        public class ConfiguredFeatures {
                public static final ResourceKey<ConfiguredFeature<?, ?>> IRONWOOD = RegistryUtils
                                .createConfiguredFeature(MODULE_ID, "ironwood_tree");

                public static final ResourceKey<ConfiguredFeature<?, ?>> CAVE_WHEAT = RegistryUtils
                                .createConfiguredFeature(MODULE_ID, "cave_wheat");

        }

        public class BiomeModifiers {
                public static final ResourceKey<BiomeModifier> IRONWOOD = RegistryUtils.createBiomeModifier(MODULE_ID,
                                "add_ironwood");

                public static final ResourceKey<BiomeModifier> CAVE_WHEAT = RegistryUtils
                                .createBiomeModifier(MODULE_ID, "add_cave_wheat");

        }

}
