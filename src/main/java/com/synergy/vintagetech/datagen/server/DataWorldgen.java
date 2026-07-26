package com.synergy.vintagetech.datagen.server;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.api.datagen.WorldgenUtils;
import com.synergy.vintagetech.init.builder.plants.CaveWheat;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zTags;
import com.synergy.vintagetech.init.types.zWorldGenFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class DataWorldgen extends DatapackBuiltinEntriesProvider {

        private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
                        .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, DataWorldgen::biomeModifiers)
                        .add(Registries.CONFIGURED_FEATURE, DataWorldgen::configuredFeatures)
                        .add(Registries.PLACED_FEATURE, DataWorldgen::placedFeatures);

        public DataWorldgen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
                super(output, registries, BUILDER, Set.of(MODULE_ID));
        }

        protected static void biomeModifiers(BootstrapContext<BiomeModifier> c) {

                WorldgenUtils.registerBiomeModifer(c, zWorldGenFeatures.BiomeModifiers.CAVE_WHEAT,
                                c.lookup(Registries.BIOME).getOrThrow(zTags.Biomes.WILD_CAVE_WHEAT_SPAWN),
                                HolderSet.direct(c.lookup(Registries.PLACED_FEATURE)
                                                .getOrThrow(zWorldGenFeatures.PlacedFeatures.WILD_CAVE_WHEAT)),
                                Decoration.VEGETAL_DECORATION);

        }

        protected static void configuredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> c) {

                registerPatchConfig(c, zWorldGenFeatures.ConfiguredFeatures.CAVE_WHEAT,
                                zBlocks.CAVE_WHEAT.get());

        }

        protected static void placedFeatures(BootstrapContext<PlacedFeature> c) {

                registerPatchPlaced(c, zWorldGenFeatures.PlacedFeatures.WILD_CAVE_WHEAT,
                                zWorldGenFeatures.ConfiguredFeatures.CAVE_WHEAT, 15, -56, 24);

        }

        // TODO API : move to api

        public static void registerPatchConfig(
                        BootstrapContext<ConfiguredFeature<?, ?>> c, ResourceKey<ConfiguredFeature<?, ?>> k,
                        Block block) {

                FeatureUtils.register(
                                c,
                                k,
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(

                                                BlockStateProvider.simple(block.defaultBlockState()
                                                                .setValue(CaveWheat.AGE, 5))));

        }

        public static void registerPatchPlaced(BootstrapContext<PlacedFeature> c,
                        ResourceKey<PlacedFeature> k,
                        ResourceKey<ConfiguredFeature<?, ?>> configured, int rarity, int minY, int maxY) {

                PlacementUtils.register(
                                c,
                                k,
                                c.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(configured),
                                RarityFilter.onAverageOnceEvery(rarity),
                                InSquarePlacement.spread(),
                                PlacementUtils.HEIGHTMAP,
                                BiomeFilter.biome(),
                                CountPlacement.of(96),
                                HeightRangePlacement.uniform(
                                                VerticalAnchor.absolute(minY),
                                                VerticalAnchor.absolute(maxY)),
                                RandomOffsetPlacement.ofTriangle(7, 3),
                                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE));

        }

}