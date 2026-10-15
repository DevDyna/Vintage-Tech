package com.synergy.vintagetech.init.types;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.RegistryUtils;
import com.synergy.vintagetech.api.datagen.builders.OliveTrunkPlacer;
import com.synergy.vintagetech.api.datagen.builders.deposits.DepositFeature;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zWorldGenFeatures {
        public static void register(IEventBus bus) {
                TrunkPlacer.register(bus);
                Features.register(bus);
        }

        public class Features {
                public static void register(IEventBus bus) {
                        zFeatures.register(bus);
                }

                public static final DeferredRegister<Feature<?>> zFeatures = DeferredRegister
                                .create(Registries.FEATURE, MODULE_ID);

                public static final DeferredHolder<Feature<?>, DepositFeature> DEPOSIT = zFeatures
                                .register("deposit", () -> new DepositFeature());

        }

        public class TrunkPlacer {
                public static void register(IEventBus bus) {
                        zTrunks.register(bus);
                }

                public static final DeferredRegister<TrunkPlacerType<?>> zTrunks = DeferredRegister
                                .create(Registries.TRUNK_PLACER_TYPE, MODULE_ID);

                public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<OliveTrunkPlacer>> OLIVE_TRUNK_PLACER = zTrunks
                                .register("olive_trunk_placer", () -> new TrunkPlacerType<>(OliveTrunkPlacer.CODEC));

        }

        public class PlacedFeatures {
                public static final ResourceKey<PlacedFeature> IRONWOOD = RegistryUtils
                                .createPlacedFeature(MODULE_ID, "ironwood_tree");

                public static final ResourceKey<PlacedFeature> OLIVE_COMMON = RegistryUtils
                                .createPlacedFeature(MODULE_ID, "olive_tree/common");

                public static final ResourceKey<PlacedFeature> OLIVE_RARE = RegistryUtils
                                .createPlacedFeature(MODULE_ID, "olive_tree/rare");

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

                public static final ResourceKey<PlacedFeature> SALT_DEPOSIT = RegistryUtils
                                .createPlacedFeature(MODULE_ID, "salt_deposit");

        }

        public class ConfiguredFeatures {
                public static final ResourceKey<ConfiguredFeature<?, ?>> IRONWOOD = RegistryUtils
                                .createConfiguredFeature(MODULE_ID, "ironwood_tree");

                public static final ResourceKey<ConfiguredFeature<?, ?>> OLIVE = RegistryUtils
                                .createConfiguredFeature(MODULE_ID, "olive_tree");

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

                public static final ResourceKey<ConfiguredFeature<?, ?>> SALT_DEPOSIT = RegistryUtils
                                .createConfiguredFeature(MODULE_ID, "salt_deposit");

        }

        public class BiomeModifiers {
                public static final ResourceKey<BiomeModifier> IRONWOOD = RegistryUtils
                                .createBiomeModifier(MODULE_ID, "ironwood_tree");

                public static final ResourceKey<BiomeModifier> OLIVE_COMMON = RegistryUtils
                                .createBiomeModifier(MODULE_ID, "olive_tree/common");
                public static final ResourceKey<BiomeModifier> OLIVE_RARE = RegistryUtils
                                .createBiomeModifier(MODULE_ID, "olive_tree/rare");

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

                public static final ResourceKey<BiomeModifier> SALT_DEPOSIT = RegistryUtils
                                .createBiomeModifier(MODULE_ID, "salt_deposit");
        }

}
