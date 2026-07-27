package com.synergy.vintagetech.datagen.server;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.api.datagen.WorldgenUtils;
import com.synergy.vintagetech.init.builder.plants.Aloe;
import com.synergy.vintagetech.init.builder.plants.BlueBerry;
import com.synergy.vintagetech.init.builder.plants.CaveWheat;
import com.synergy.vintagetech.init.builder.plants.Hemp;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zTags;
import com.synergy.vintagetech.init.types.zWorldGenFeatures;
import com.synergy.vintagetech.init.types.zWorldGenFeatures.PlacedFeatures;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
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
import net.neoforged.neoforge.common.world.BiomeModifiers.AddFeaturesBiomeModifier;
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

                WorldgenUtils.registerBiomeModifer(c, zWorldGenFeatures.BiomeModifiers.BLUEBERRIES,
                                c.lookup(Registries.BIOME).getOrThrow(zTags.Biomes.BLUEBERRIES_SPAWN),
                                HolderSet.direct(c.lookup(Registries.PLACED_FEATURE)
                                                .getOrThrow(zWorldGenFeatures.PlacedFeatures.BLUEBERRIES)),
                                Decoration.VEGETAL_DECORATION);

                WorldgenUtils.registerBiomeModifer(c, zWorldGenFeatures.BiomeModifiers.ALOE,
                                c.lookup(Registries.BIOME).getOrThrow(zTags.Biomes.ALOE_SPAWN),
                                HolderSet.direct(c.lookup(Registries.PLACED_FEATURE)
                                                .getOrThrow(zWorldGenFeatures.PlacedFeatures.ALOE)),
                                Decoration.VEGETAL_DECORATION);

                WorldgenUtils.registerBiomeModifer(c, zWorldGenFeatures.BiomeModifiers.LAVENDER,
                                c.lookup(Registries.BIOME).getOrThrow(zTags.Biomes.LAVENDER_SPAWN),
                                HolderSet.direct(c.lookup(Registries.PLACED_FEATURE)
                                                .getOrThrow(zWorldGenFeatures.PlacedFeatures.LAVENDER)),
                                Decoration.VEGETAL_DECORATION);

                WorldgenUtils.registerBiomeModifer(c, zWorldGenFeatures.BiomeModifiers.CAVE_WHEAT,
                                c.lookup(Registries.BIOME).getOrThrow(zTags.Biomes.CAVE_WHEAT_SPAWN),
                                HolderSet.direct(c.lookup(Registries.PLACED_FEATURE)
                                                .getOrThrow(zWorldGenFeatures.PlacedFeatures.CAVE_WHEAT)),
                                Decoration.VEGETAL_DECORATION);

                c.register(zWorldGenFeatures.BiomeModifiers.IRONWOOD,
                                new AddFeaturesBiomeModifier(
                                                c.lookup(Registries.BIOME).getOrThrow(zTags.Biomes.IRONWOOD_TREE_SPAWN),
                                                HolderSet.direct(c.lookup(Registries.PLACED_FEATURE)
                                                                .getOrThrow(PlacedFeatures.IRONWOOD)),
                                                GenerationStep.Decoration.VEGETAL_DECORATION));

                c.register(zWorldGenFeatures.BiomeModifiers.HEMP,
                                new AddFeaturesBiomeModifier(
                                                c.lookup(Registries.BIOME).getOrThrow(zTags.Biomes.HEMP_SPAWN),
                                                HolderSet.direct(c.lookup(Registries.PLACED_FEATURE)
                                                                .getOrThrow(PlacedFeatures.HEMP)),
                                                GenerationStep.Decoration.VEGETAL_DECORATION));

        }

        protected static void configuredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> c) {

                registerPatchConfig(c, zWorldGenFeatures.ConfiguredFeatures.BLUEBERRIES,

                                zBlocks.BLUEBERRY_BUSH
                                                .get().defaultBlockState().setValue(BlueBerry.AGE, 0),
                                8,
                                zBlocks.BLUEBERRY_BUSH
                                                .get().defaultBlockState().setValue(BlueBerry.AGE, 1),
                                4,
                                zBlocks.BLUEBERRY_BUSH.get().defaultBlockState().setValue(BlueBerry.AGE,
                                                2),
                                2,
                                zBlocks.BLUEBERRY_BUSH.get().defaultBlockState().setValue(BlueBerry.AGE,
                                                3),
                                1);

                registerPatchConfig(c, zWorldGenFeatures.ConfiguredFeatures.ALOE,

                                zBlocks.ALOE_PLANT.get().defaultBlockState().setValue(Aloe.AGE, 0), 2,
                                zBlocks.ALOE_PLANT.get().defaultBlockState().setValue(Aloe.AGE, 1), 2,
                                zBlocks.ALOE_PLANT.get().defaultBlockState().setValue(Aloe.AGE, 2), 1

                );

                registerPatchConfig(c, zWorldGenFeatures.ConfiguredFeatures.LAVENDER,

                                zBlocks.LAVENDER.get().defaultBlockState(), 1

                );

                registerPatchConfig(c, zWorldGenFeatures.ConfiguredFeatures.CAVE_WHEAT,

                                zBlocks.CAVE_WHEAT.get().defaultBlockState().setValue(CaveWheat.AGE, 3),
                                4,
                                zBlocks.CAVE_WHEAT.get().defaultBlockState().setValue(CaveWheat.AGE, 4),
                                2,
                                zBlocks.CAVE_WHEAT.get().defaultBlockState().setValue(CaveWheat.AGE, 5),
                                1

                );

                registerPatchConfig(c, zWorldGenFeatures.ConfiguredFeatures.HEMP,

                                zBlocks.HEMP.get().defaultBlockState().setValue(Hemp.AGE, 1)
                                                .setValue(Hemp.NATURAL, true),
                                4,
                                zBlocks.HEMP.get().defaultBlockState().setValue(Hemp.AGE, 2)
                                                .setValue(Hemp.NATURAL, true),
                                2,
                                zBlocks.HEMP.get().defaultBlockState().setValue(Hemp.AGE, 3)
                                                .setValue(Hemp.NATURAL, true),
                                1

                );

                c.register(zWorldGenFeatures.ConfiguredFeatures.IRONWOOD,
                                new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                                                BlockStateProvider.simple(zBlocks.IRONWOOD_LOG.get()),
                                                new StraightTrunkPlacer(5, 8, 0),
                                                BlockStateProvider.simple(zBlocks.IRONWOOD_LEAVES.get()),
                                                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                                                new TwoLayersFeatureSize(1, 0, 1))
                                                .ignoreVines()
                                                .build()));

        }

        protected static void placedFeatures(BootstrapContext<PlacedFeature> c) {

                registerPatchPlaced(c, zWorldGenFeatures.PlacedFeatures.LAVENDER,
                                zWorldGenFeatures.ConfiguredFeatures.LAVENDER, 5, 15, 20, 120);

                registerPatchPlaced(c, zWorldGenFeatures.PlacedFeatures.BLUEBERRIES,
                                zWorldGenFeatures.ConfiguredFeatures.BLUEBERRIES, 6, 18, 40, 80);

                registerPatchPlaced(c, zWorldGenFeatures.PlacedFeatures.ALOE,
                                zWorldGenFeatures.ConfiguredFeatures.ALOE, 5, 16, 40, 80);

                registerPatchPlaced(c, zWorldGenFeatures.PlacedFeatures.HEMP,
                                zWorldGenFeatures.ConfiguredFeatures.HEMP, 4, 3, 50, 120);

                registerUndergroundPatchPlaced(c, zWorldGenFeatures.PlacedFeatures.CAVE_WHEAT,
                                zWorldGenFeatures.ConfiguredFeatures.CAVE_WHEAT, 32, 1, -56, 24);

                c.register(zWorldGenFeatures.PlacedFeatures.IRONWOOD,
                                new PlacedFeature(
                                                c.lookup(Registries.CONFIGURED_FEATURE)
                                                                .getOrThrow(zWorldGenFeatures.ConfiguredFeatures.IRONWOOD),
                                                VegetationPlacements.treePlacement(
                                                                CountPlacement.of(new WeightedListInt(
                                                                                WeightedList
                                                                                                .<IntProvider>builder()
                                                                                                .add(UniformInt.of(3,
                                                                                                                4), 1)
                                                                                                .add(UniformInt.of(2,
                                                                                                                3), 2)
                                                                                                .add(UniformInt.of(1,
                                                                                                                2), 3)
                                                                                                .add(ConstantInt.of(0),
                                                                                                                194)
                                                                                                .build())),
                                                                zBlocks.IRONWOOD_SAPLING.get())));

        }

        // TODO API : move to api?

        public static void registerPatchConfig(
                        BootstrapContext<ConfiguredFeature<?, ?>> c,
                        ResourceKey<ConfiguredFeature<?, ?>> k,
                        Object... states) {

                var builder = WeightedList.<BlockState>builder();

                for (int i = 0; i < states.length; i += 2)
                        builder.add((BlockState) states[i], (int) states[i + 1]);

                FeatureUtils.register(
                                c,
                                k,
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                                new WeightedStateProvider(builder)));
        }

        public static void registerPatchPlaced(
                        BootstrapContext<PlacedFeature> c,
                        ResourceKey<PlacedFeature> k,
                        ResourceKey<ConfiguredFeature<?, ?>> configured,
                        int count,
                        int rarity,
                        int minY,
                        int maxY) {

                PlacementUtils.register(
                                c,
                                k,
                                c.lookup(Registries.CONFIGURED_FEATURE)
                                                .getOrThrow(configured),

                                RarityFilter.onAverageOnceEvery(rarity),
                                InSquarePlacement.spread(),

                                CountPlacement.of(count),

                                HeightRangePlacement.uniform(
                                                VerticalAnchor.absolute(minY),
                                                VerticalAnchor.absolute(maxY)),

                                RandomOffsetPlacement.ofTriangle(3, 2),

                                PlacementUtils.HEIGHTMAP,

                                BiomeFilter.biome(),

                                BlockPredicateFilter.forPredicate(
                                                BlockPredicate.anyOf(BlockPredicate.matchesTag(
                                                                BlockTags.REPLACEABLE),
                                                                BlockPredicate.ONLY_IN_AIR_PREDICATE)));

        }

        public static void registerUndergroundPatchPlaced(
                        BootstrapContext<PlacedFeature> c,
                        ResourceKey<PlacedFeature> k,
                        ResourceKey<ConfiguredFeature<?, ?>> configured,
                        int count,
                        int rarity,
                        int minY,
                        int maxY) {

                PlacementUtils.register(
                                c,
                                k,
                                c.lookup(Registries.CONFIGURED_FEATURE)
                                                .getOrThrow(configured),

                                CountPlacement.of(count),
                                InSquarePlacement.spread(),
                                RarityFilter.onAverageOnceEvery(rarity),

                                HeightRangePlacement.uniform(
                                                VerticalAnchor.absolute(minY),
                                                VerticalAnchor.absolute(maxY)),

                                BiomeFilter.biome(),

                                BlockPredicateFilter.forPredicate(
                                                BlockPredicate.allOf(
                                                                BlockPredicate.ONLY_IN_AIR_PREDICATE,

                                                                BlockPredicate.matchesTag(
                                                                                new Vec3i(0, -1, 0),
                                                                                zTags.Blocks.SUPPORT_CAVE_WHEAT_PLANT

                                                                ))));
        }

}