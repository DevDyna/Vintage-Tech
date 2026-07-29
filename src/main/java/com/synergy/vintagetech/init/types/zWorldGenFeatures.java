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
                                .createPlacedFeature(MODULE_ID, "ironwood_tree");

                public static final ResourceKey<PlacedFeature> CAVE_WHEAT = RegistryUtils
                                .createPlacedFeature(MODULE_ID, "cave_wheat");

                public static final ResourceKey<PlacedFeature> BLUEBERRIES = RegistryUtils
                                .createPlacedFeature(MODULE_ID, "blueberries");

                public static final ResourceKey<PlacedFeature> ALOE = RegistryUtils
                                .createPlacedFeature(MODULE_ID, "aloe");

                public static final ResourceKey<PlacedFeature> LAVENDER = RegistryUtils
                                .createPlacedFeature(MODULE_ID, "lavender");

                public static final ResourceKey<PlacedFeature> HEMP = RegistryUtils
                                .createPlacedFeature(MODULE_ID, "hemp");

                public static final ResourceKey<PlacedFeature> SOYBEANS = RegistryUtils
                                .createPlacedFeature(MODULE_ID, "soybeans");

        }

        public class ConfiguredFeatures {
                public static final ResourceKey<ConfiguredFeature<?, ?>> IRONWOOD = RegistryUtils
                                .createConfiguredFeature(MODULE_ID, "ironwood_tree");

                public static final ResourceKey<ConfiguredFeature<?, ?>> CAVE_WHEAT = RegistryUtils
                                .createConfiguredFeature(MODULE_ID, "cave_wheat");

                public static final ResourceKey<ConfiguredFeature<?, ?>> BLUEBERRIES = RegistryUtils
                                .createConfiguredFeature(MODULE_ID, "blueberries");

                public static final ResourceKey<ConfiguredFeature<?, ?>> ALOE = RegistryUtils
                                .createConfiguredFeature(MODULE_ID, "aloe");

                public static final ResourceKey<ConfiguredFeature<?, ?>> LAVENDER = RegistryUtils
                                .createConfiguredFeature(MODULE_ID, "lavender");

                public static final ResourceKey<ConfiguredFeature<?, ?>> HEMP = RegistryUtils
                                .createConfiguredFeature(MODULE_ID, "hemp");

                public static final ResourceKey<ConfiguredFeature<?, ?>> SOYBEANS = RegistryUtils
                                .createConfiguredFeature(MODULE_ID, "soybeans");

        }

        public class BiomeModifiers {
                public static final ResourceKey<BiomeModifier> IRONWOOD = RegistryUtils.createBiomeModifier(MODULE_ID,
                                "ironwood");

                public static final ResourceKey<BiomeModifier> CAVE_WHEAT = RegistryUtils
                                .createBiomeModifier(MODULE_ID, "cave_wheat");

                public static final ResourceKey<BiomeModifier> BLUEBERRIES = RegistryUtils
                                .createBiomeModifier(MODULE_ID, "blueberries");

                public static final ResourceKey<BiomeModifier> ALOE = RegistryUtils
                                .createBiomeModifier(MODULE_ID, "aloe");

                public static final ResourceKey<BiomeModifier> LAVENDER = RegistryUtils
                                .createBiomeModifier(MODULE_ID, "lavender");
                                
                public static final ResourceKey<BiomeModifier> HEMP = RegistryUtils
                                .createBiomeModifier(MODULE_ID, "hemp");

                public static final ResourceKey<BiomeModifier> SOYBEANS = RegistryUtils
                                .createBiomeModifier(MODULE_ID, "soybeans");

        }

}
