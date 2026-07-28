package com.synergy.vintagetech.datagen.server;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.api.datagen.RecipeGenerators;
import com.devdyna.cakesticklib.setup.registry.LibItems;
import com.devdyna.cakesticklib.setup.registry.LibTags;
import com.synergy.vintagetech.init.builder.centrifuge.recipe.CentrifugeBuilder;
import com.synergy.vintagetech.init.builder.crushing_tub.recipe.CrushingTubBuilder;
import com.synergy.vintagetech.init.builder.drying_rack.recipe.DryingRackBuilder;
import com.synergy.vintagetech.init.builder.evaporation_basin.recipe.EvaporationBasinBuilder;
import com.synergy.vintagetech.init.builder.mechanical_farmland.recipe.FarmlandFuelsBuilder;
import com.synergy.vintagetech.init.builder.millstone.recipe.MillstoneBuilder;
import com.synergy.vintagetech.init.builder.treetap.recipe.TreeTapBuilder;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zFluids;
import com.synergy.vintagetech.init.types.zItems;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.Tags;

public class DataRecipe extends RecipeProvider implements RecipeGenerators {

        protected DataRecipe(Provider registries, RecipeOutput output) {
                super(registries, output);
        }

        @Override
        protected void buildRecipes() {

                shapeless(RecipeCategory.BUILDING_BLOCKS, zBlocks.IRONWOOD_PLANKS.get(), 4)
                                .requires(zTags.Items.IRONWOOD_LOGS)
                                .unlockedBy(getHasName(zTags.Items.IRONWOOD_LOGS), has(zTags.Items.IRONWOOD_LOGS))
                                .save(output);

                slab(zBlocks.IRONWOOD_SLAB.get(), zBlocks.IRONWOOD_PLANKS.get(), output);

                stair(zBlocks.IRONWOOD_STAIRS.get(), zBlocks.IRONWOOD_PLANKS.get(), output);

                CrushingTubBuilder.of(registries)
                                .input(ItemTags.LEAVES)
                                .output(Items.STICK, 0.5f)
                                .output(Fluids.WATER, 1000)
                                .unlockedBy(getHasName(ItemTags.LEAVES), has(ItemTags.LEAVES))
                                .save(output);

                EvaporationBasinBuilder.of(registries)
                                .fluid(Fluids.WATER, 125)
                                .output(zItems.SALT)
                                .unlockedBy(getHasName(zBlocks.EVAPORATION_BASIN.get()),
                                                has(zBlocks.EVAPORATION_BASIN.get()))
                                .save(output);

                EvaporationBasinBuilder.of(registries)
                                .fluid(zFluids.LATEX.getFluid(), 125)
                                .output(zItems.GLUE)
                                .unlockedBy(getHasName(zBlocks.EVAPORATION_BASIN.get()),
                                                has(zBlocks.EVAPORATION_BASIN.get()))
                                .save(output);

                EvaporationBasinBuilder.of(registries)
                                .fluid(zFluids.RESIN.getFluid(), 125)
                                .output(zItems.AMBER)
                                .unlockedBy(getHasName(zBlocks.EVAPORATION_BASIN.get()),
                                                has(zBlocks.EVAPORATION_BASIN.get()))
                                .save(output);

                EvaporationBasinBuilder.of(registries)
                                .fluid(zFluids.OIL.getFluid(), 125)
                                .output(zItems.BITUMEN)
                                .unlockedBy(getHasName(zBlocks.EVAPORATION_BASIN.get()),
                                                has(zBlocks.EVAPORATION_BASIN.get()))
                                .save(output);

                EvaporationBasinBuilder.of(registries)
                                .fluid(zFluids.SAP.getFluid(), 125)
                                .output(zItems.SAP)
                                .unlockedBy(getHasName(zBlocks.EVAPORATION_BASIN.get()),
                                                has(zBlocks.EVAPORATION_BASIN.get()))
                                .save(output);

                EvaporationBasinBuilder.of(registries)
                                .fluid(zFluids.IRONBERRY_JUICE.getFluid(), 125)
                                .output(zItems.TINY_IRON_DUST)
                                .unlockedBy(getHasName(zBlocks.EVAPORATION_BASIN.get()),
                                                has(zBlocks.EVAPORATION_BASIN.get()))
                                .save(output);

                EvaporationBasinBuilder.of(registries)
                                .fluid(zFluids.SULFURIC_ACID.getFluid(), 25)
                                .output(LibItems.SULFUR_DUST)
                                .unlockedBy(getHasName(zBlocks.EVAPORATION_BASIN.get()),
                                                has(zBlocks.EVAPORATION_BASIN.get()))
                                .save(output);

                DryingRackBuilder.of(registries)
                                .input(ItemTags.SAPLINGS)
                                .output(Items.DEAD_BUSH)
                                .unlockedBy(getHasName(ItemTags.SAPLINGS), has(ItemTags.SAPLINGS))
                                .save(output);

                MillstoneBuilder.of(registries)
                                .input(Tags.Items.CROPS_WHEAT)
                                .output(LibItems.FLOUR, 2)
                                .unlockedBy(getHasName(Tags.Items.CROPS_WHEAT), has(Tags.Items.CROPS_WHEAT))
                                .save(output);

                MillstoneBuilder.of(registries)
                                .input(zItems.SOYBEANS)
                                .output(zItems.MASHED_SOYBEANS, 2)
                                .unlockedBy(getHasName(zItems.SOYBEANS.get()), has(zItems.SOYBEANS.get()))
                                .save(output);

                CentrifugeBuilder.of(registries)
                                .input(zItems.MASHED_SOYBEANS)
                                .fluid(Fluids.WATER, 125)
                                .output(zFluids.SOYMILK.getFluid(), 125)
                                .output(zItems.SOY_DOUGH, 2, 1f)
                                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                                .save(output);

                TreeTapBuilder.of(registries)
                                .log(Blocks.OAK_LOG)
                                .leaves(Blocks.OAK_LEAVES)
                                .output(zFluids.SAP.getFluid(), 25)
                                .unlockedBy(getHasName(zBlocks.TREE_TAP.get()), has(zBlocks.TREE_TAP.get()))
                                .save(output, "_from_oak");

                TreeTapBuilder.of(registries)
                                .log(Blocks.DARK_OAK_LOG)
                                .leaves(Blocks.DARK_OAK_LEAVES)
                                .output(zFluids.SAP.getFluid(), 50)
                                .unlockedBy(getHasName(zBlocks.TREE_TAP.get()), has(zBlocks.TREE_TAP.get()))
                                .save(output, "_from_dark_oak");

                TreeTapBuilder.of(registries)
                                .log(Blocks.PALE_OAK_LOG)
                                .leaves(Blocks.PALE_OAK_LEAVES)
                                .output(zFluids.SAP.getFluid(), 75)
                                .unlockedBy(getHasName(zBlocks.TREE_TAP.get()), has(zBlocks.TREE_TAP.get()))
                                .save(output, "_from_pale_oak");

                TreeTapBuilder.of(registries)
                                .log(Blocks.BIRCH_LOG)
                                .leaves(Blocks.BIRCH_LEAVES)
                                .output(zFluids.SAP.getFluid(), 15)
                                .unlockedBy(getHasName(zBlocks.TREE_TAP.get()), has(zBlocks.TREE_TAP.get()))
                                .save(output, "_from_birch");

                TreeTapBuilder.of(registries)
                                .log(Blocks.SPRUCE_LOG)
                                .leaves(Blocks.SPRUCE_LEAVES)
                                .output(zFluids.RESIN.getFluid(), 25)
                                .unlockedBy(getHasName(zBlocks.TREE_TAP.get()), has(zBlocks.TREE_TAP.get()))
                                .save(output, "_from_spruce");

                TreeTapBuilder.of(registries)
                                .log(Blocks.JUNGLE_LOG)
                                .leaves(Blocks.JUNGLE_LEAVES)
                                .output(zFluids.LATEX.getFluid(), 50)
                                .unlockedBy(getHasName(zBlocks.TREE_TAP.get()), has(zBlocks.TREE_TAP.get()))
                                .save(output, "_from_jungle");

                TreeTapBuilder.of(registries)
                                .log(Blocks.MANGROVE_LOG)
                                .leaves(Blocks.MANGROVE_LEAVES)
                                .output(zFluids.LATEX.getFluid(), 75)
                                .unlockedBy(getHasName(zBlocks.TREE_TAP.get()), has(zBlocks.TREE_TAP.get()))
                                .save(output, "_from_mangrove");

                TreeTapBuilder.of(registries)
                                .log(Blocks.CHERRY_LOG)
                                .leaves(Blocks.CHERRY_LEAVES)
                                .output(zFluids.LATEX.getFluid(), 15)
                                .unlockedBy(getHasName(zBlocks.TREE_TAP.get()), has(zBlocks.TREE_TAP.get()))
                                .save(output, "_from_cherry");

                TreeTapBuilder.of(registries)
                                .log(Blocks.ACACIA_LOG)
                                .leaves(Blocks.ACACIA_LEAVES)
                                .output(zFluids.OIL.getFluid(), 25)
                                .unlockedBy(getHasName(zBlocks.TREE_TAP.get()), has(zBlocks.TREE_TAP.get()))
                                .save(output, "_from_acacia");

                TreeTapBuilder.of(registries)
                                .log(Tags.Blocks.NETHER_NATURAL_LOGS)
                                .leaves(BlockTags.WART_BLOCKS)
                                .output(zFluids.SULFURIC_ACID.getFluid(), 25)
                                .unlockedBy(getHasName(zBlocks.TREE_TAP.get()), has(zBlocks.TREE_TAP.get()))
                                .save(output);

                TreeTapBuilder.of(registries)
                                .log(zBlocks.IRONWOOD_LOG.get())
                                .leaves(zBlocks.IRONWOOD_LEAVES.get())
                                .output(zFluids.IRONBERRY_JUICE.getFluid(), 250)
                                .unlockedBy(getHasName(zBlocks.TREE_TAP.get()), has(zBlocks.TREE_TAP.get()))
                                .save(output);

                shapeless(RecipeCategory.MISC, zItems.HEMP_FIBER.get(), 3)
                                .requires(zItems.HEMP.get())
                                .unlockedBy(getHasName(zItems.HEMP.get()), has(zItems.HEMP.get()))
                                .save(output);

                pillar(zBlocks.OAK_BEAM.get(), Items.OAK_LOG);
                pillar(zBlocks.SPRUCE_BEAM.get(), Items.SPRUCE_LOG);
                pillar(zBlocks.BIRCH_BEAM.get(), Items.BIRCH_LOG);
                pillar(zBlocks.JUNGLE_BEAM.get(), Items.JUNGLE_LOG);
                pillar(zBlocks.ACACIA_BEAM.get(), Items.ACACIA_LOG);
                pillar(zBlocks.DARK_OAK_BEAM.get(), Items.DARK_OAK_LOG);
                pillar(zBlocks.MANGROVE_BEAM.get(), Items.MANGROVE_LOG);
                pillar(zBlocks.CHERRY_BEAM.get(), Items.CHERRY_LOG);
                pillar(zBlocks.PALE_OAK_BEAM.get(), Items.PALE_OAK_LOG);

                shapeless(RecipeCategory.MISC, zBlocks.AXLE.get())
                                .requires(zTags.Items.BEAM_LOGS_NORMAL)
                                .requires(zBlocks.ROPE.get())
                                .unlockedBy(getHasName(zBlocks.ROPE.get()), has(zBlocks.ROPE.get()))
                                .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS,
                                zBlocks.TREE_TAP.get(), 1)
                                .define('#', Items.IRON_NUGGET)
                                .pattern("# #")
                                .pattern(" # ")
                                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                                .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS,
                                zBlocks.ROPE.get(), 4)
                                .define('#', Items.STRING)
                                .pattern("###")
                                .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                                .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS,
                                zBlocks.ROPE.get(), 8)
                                .define('#', zItems.HEMP_FIBER.get())
                                .pattern("###")
                                .unlockedBy(getHasName(zItems.HEMP_FIBER.get()), has(zItems.HEMP_FIBER.get()))
                                .save(output, getSimpleRecipeName(zBlocks.ROPE.get()) + "_alt");

                twoByTwoPacker(output, zItems.TINY_IRON_DUST.get(), Items.RAW_IRON,
                                MODULE_ID + ":raw_iron_from_tiny_iron_dust");

                shapeless(RecipeCategory.MISC, zBlocks.JUNCTION.get())
                                .requires(zBlocks.AXLE.get())
                                .requires(zBlocks.AXLE.get())
                                .requires(zBlocks.AXLE.get())
                                .requires(zBlocks.ROPE.get())
                                .unlockedBy(getHasName(zBlocks.ROPE.get()), has(zBlocks.ROPE.get()))
                                .save(output);

                shapeless(RecipeCategory.MISC, Items.TORCH)
                                .requires(zItems.AMBER.get())
                                .requires(Items.STRING)
                                .requires(Items.STICK)
                                .unlockedBy(getHasName(zItems.AMBER.get()), has(zItems.AMBER.get()))
                                .save(output);

                shapeless(RecipeCategory.MISC, Items.MAGENTA_DYE, 2)
                                .requires(zBlocks.LAVENDER.get())
                                .unlockedBy(getHasName(zBlocks.LAVENDER.get()), has(zBlocks.LAVENDER.get()))
                                .save(output);

                shapeless(RecipeCategory.MISC, Items.LIME_DYE, 2)
                                .requires(zItems.ALOE.get())
                                .unlockedBy(getHasName(zItems.ALOE.get()), has(zItems.ALOE.get()))
                                .save(output);

                MillstoneBuilder.of(registries)
                                .input(zItems.ALOE.get())
                                .output(Items.LIME_DYE, 4)
                                .unlockedBy(getHasName(zItems.ALOE.get()), has(zItems.ALOE.get()))
                                .save(output);

                MillstoneBuilder.of(registries)
                                .input(zBlocks.LAVENDER.get())
                                .output(Items.MAGENTA_DYE, 4)
                                .unlockedBy(getHasName(zBlocks.LAVENDER.get()), has(zBlocks.LAVENDER.get()))
                                .save(output);

                shapeless(RecipeCategory.MISC, zItems.BLUEBERRIES_SOUP.get(), 1)
                                .requires(Items.BOWL)
                                .requires(zItems.BLUEBERRIES.get())
                                .requires(zItems.BLUEBERRIES.get())
                                .requires(zItems.BLUEBERRIES.get())
                                .requires(zItems.BLUEBERRIES.get())
                                .requires(zItems.BLUEBERRIES.get())
                                .requires(zItems.BLUEBERRIES.get())
                                .unlockedBy(getHasName(zItems.BLUEBERRIES.get()), has(zItems.BLUEBERRIES.get()))
                                .save(output);

                shapeless(RecipeCategory.MISC, zItems.IRONBERRIES_SOUP.get(), 1)
                                .requires(Items.BOWL)
                                .requires(zItems.IRONBERRIES.get())
                                .requires(zItems.IRONBERRIES.get())
                                .requires(zItems.IRONBERRIES.get())
                                .requires(zItems.IRONBERRIES.get())
                                .requires(zItems.IRONBERRIES.get())
                                .requires(zItems.IRONBERRIES.get())
                                .unlockedBy(getHasName(zItems.IRONBERRIES.get()), has(zItems.IRONBERRIES.get()))
                                .save(output);

                shapeless(RecipeCategory.MISC, zItems.GLOW_BERRIES_SOUP.get(), 1)
                                .requires(Items.BOWL)
                                .requires(Items.GLOW_BERRIES)
                                .requires(Items.GLOW_BERRIES)
                                .requires(Items.GLOW_BERRIES)
                                .requires(Items.GLOW_BERRIES)
                                .requires(Items.GLOW_BERRIES)
                                .requires(Items.GLOW_BERRIES)
                                .unlockedBy(getHasName(Items.GLOW_BERRIES), has(Items.GLOW_BERRIES))
                                .save(output);

                shapeless(RecipeCategory.MISC, zItems.SWEET_BERRIES_SOUP.get(), 1)
                                .requires(Items.BOWL)
                                .requires(Items.SWEET_BERRIES)
                                .requires(Items.SWEET_BERRIES)
                                .requires(Items.SWEET_BERRIES)
                                .requires(Items.SWEET_BERRIES)
                                .requires(Items.SWEET_BERRIES)
                                .requires(Items.SWEET_BERRIES)
                                .unlockedBy(getHasName(Items.SWEET_BERRIES), has(Items.SWEET_BERRIES))
                                .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS,
                                zBlocks.DRYING_RACK.get(), 1)
                                .define('R', zBlocks.ROPE.get())
                                .define('L', Tags.Items.NATURAL_LOGS)
                                .pattern(" L ")
                                .pattern("R R")
                                .pattern("LLL")
                                .unlockedBy(getHasName(Tags.Items.NATURAL_LOGS), has(Tags.Items.NATURAL_LOGS))
                                .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS,
                                zBlocks.SAW.get(), 1)
                                .define('I', Tags.Items.INGOTS_IRON)
                                .define('P', ItemTags.PLANKS)
                                .pattern(" I ")
                                .pattern("PPP")
                                .unlockedBy(getHasName(Tags.Items.INGOTS_IRON), has(Tags.Items.INGOTS_IRON))
                                .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS,
                                zBlocks.MILLSTONE.get(), 1)
                                .define('H', Items.HOPPER)
                                .define('G', LibTags.Items.WOODEN_GEAR)
                                .define('S', zTags.Items.STONE_SLABS)
                                .define('I', Tags.Items.INGOTS_IRON)
                                .pattern(" H ")
                                .pattern("SGS")
                                .pattern("PIP")
                                .unlockedBy(getHasName(Tags.Items.INGOTS_IRON), has(Tags.Items.INGOTS_IRON))
                                .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS,
                                zBlocks.FAN.get(), 1)
                                .define('N', Tags.Items.NUGGETS_IRON)
                                .define('I', Tags.Items.INGOTS_IRON)
                                .pattern(" I ")
                                .pattern("INI")
                                .pattern(" I ")
                                .unlockedBy(getHasName(Tags.Items.INGOTS_IRON), has(Tags.Items.INGOTS_IRON))
                                .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, zBlocks.CRUSHING_TUB.get().asItem())
                                .pattern("# #")
                                .pattern("###")
                                .define('#', ItemTags.WOODEN_SLABS)
                                .unlockedBy(getHasName(ItemTags.WOODEN_SLABS), has(ItemTags.WOODEN_SLABS))
                                .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, zBlocks.EVAPORATION_BASIN.get().asItem())
                                .pattern("# #")
                                .pattern("###")
                                .define('#', Items.TERRACOTTA)
                                .unlockedBy(getHasName(Items.TERRACOTTA), has(Items.TERRACOTTA))
                                .save(output);

                CentrifugeBuilder.of(registries)
                                .input(zItems.SAP)
                                .fluid(Fluids.WATER, 1000)
                                .output(zFluids.FERTILIZER_NATURAL.getFluid(), 250)
                                .unlockedBy(getHasName(zItems.SAP.get()), has(zItems.SAP.get()))
                                .save(output);

                CentrifugeBuilder.of(registries)
                                .input(zItems.OKARA)
                                .fluid(Fluids.WATER, 1000)
                                .output(zFluids.FERTILIZER_NATURAL.getFluid(), 750)
                                .unlockedBy(getHasName(zItems.OKARA.get()), has(zItems.OKARA.get()))
                                .save(output);

                CentrifugeBuilder.of(registries)
                                .input(Items.BONE_MEAL)
                                .fluid(Fluids.WATER, 1000)
                                .output(zFluids.FERTILIZER_CHEMICAL.getFluid(), 250)
                                .unlockedBy(getHasName(Items.BONE_MEAL), has(Items.BONE_MEAL))
                                .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, zBlocks.MECHANICAL_FARMLAND.get().asItem())
                                .pattern("SDS")
                                .pattern("SAS")
                                .pattern("SCS")
                                .define('C', Items.COMPOSTER)
                                .define('D', ItemTags.DIRT)
                                .define('A', zBlocks.AXLE.get())
                                .define('S', ItemTags.WOODEN_SLABS)
                                .unlockedBy(getHasName(Items.COMPOSTER), has(Items.COMPOSTER))
                                .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, zBlocks.CENTRIFUGE.get().asItem())
                                .pattern("SFS")
                                .pattern("SAS")
                                .pattern("SBS")
                                .define('B', Items.BARREL)
                                .define('F', zBlocks.FAN.get())
                                .define('A', zBlocks.AXLE.get())
                                .define('S', ItemTags.WOODEN_SLABS)
                                .unlockedBy(getHasName(Items.BARREL), has(Items.BARREL))
                                .save(output);

                shapeless(RecipeCategory.MISC, zBlocks.CLUTCH.get())
                                .requires(zBlocks.AXLE.get())
                                .requires(Tags.Items.INGOTS_COPPER)
                                .requires(Tags.Items.DUSTS_REDSTONE)
                                .requires(Items.LEVER)
                                .unlockedBy(getHasName(zBlocks.AXLE.get()), has(zBlocks.AXLE.get()))
                                .save(output);

                shapeless(RecipeCategory.MISC, zBlocks.GEARSHIFT.get())
                                .requires(zBlocks.AXLE.get())
                                .requires(Tags.Items.INGOTS_COPPER)
                                .requires(Items.REPEATER)
                                .unlockedBy(getHasName(zBlocks.AXLE.get()), has(zBlocks.AXLE.get()))
                                .save(output);

                shapeless(RecipeCategory.MISC, zItems.BLUEBERRIES_MUFFIN.get(), 2)
                                .requires(LibItems.FLOUR.get())
                                .requires(zItems.BLUEBERRIES.get())
                                .requires(zItems.BLUEBERRIES.get())
                                .requires(Items.SUGAR)
                                .unlockedBy(getHasName(LibItems.FLOUR.get()), has(LibItems.FLOUR.get()))
                                .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, zItems.MESH.get(), 2)
                                .pattern("RR")
                                .pattern("RR")
                                .define('R', zBlocks.ROPE.get())
                                .unlockedBy(getHasName(zBlocks.ROPE.get()), has(zBlocks.ROPE.get()))
                                .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, zBlocks.BASKET.get())
                                .pattern("R R")
                                .pattern("R R")
                                .pattern("RBR")
                                .define('R', zItems.MESH.get())
                                .define('B', Items.BARREL)
                                .unlockedBy(getHasName(zItems.MESH.get()), has(zItems.MESH.get()))
                                .save(output);

                CrushingTubBuilder.of(registries)
                                .input(zItems.SOY_DOUGH)
                                .requireMesh()
                                .output(zItems.OKARA, 1f)
                                .output(zFluids.SOYMILK.getFluid(), 100)
                                .unlockedBy(getHasName(zItems.SOY_DOUGH.get()), has(zItems.SOY_DOUGH.get()))
                                .save(output);

                MillstoneBuilder.of(registries)
                                .input(Tags.Items.COBBLESTONES_NORMAL)
                                .output(Items.GRAVEL)
                                .unlockedBy(getHasName(Tags.Items.COBBLESTONES_NORMAL),
                                                has(Tags.Items.COBBLESTONES_NORMAL))
                                .save(output);

                MillstoneBuilder.of(registries)
                                .input(Tags.Items.GRAVELS)
                                .output(Items.SAND)
                                .unlockedBy(getHasName(Tags.Items.GRAVELS), has(Tags.Items.GRAVELS))
                                .save(output);

                MillstoneBuilder.of(registries)
                                .input(Items.STONE)
                                .output(Items.COBBLESTONE)
                                .unlockedBy(getHasName(Items.STONE), has(Items.STONE))
                                .save(output);

                MillstoneBuilder.of(registries)
                                .input(Items.DEEPSLATE)
                                .output(Items.COBBLED_DEEPSLATE)
                                .unlockedBy(getHasName(Items.DEEPSLATE), has(Items.DEEPSLATE))
                                .save(output);

                MillstoneBuilder.of(registries)
                                .input(Tags.Items.RAW_MATERIALS_COPPER)
                                .output(LibItems.COPPER_DUST.get(), 2)
                                .unlockedBy(getHasName(Tags.Items.RAW_MATERIALS_COPPER),
                                                has(Tags.Items.RAW_MATERIALS_COPPER))
                                .save(output, "_from_raw");

                MillstoneBuilder.of(registries)
                                .input(Tags.Items.RAW_MATERIALS_GOLD)
                                .output(LibItems.GOLD_DUST.get(), 2)
                                .unlockedBy(getHasName(Tags.Items.RAW_MATERIALS_GOLD),
                                                has(Tags.Items.RAW_MATERIALS_GOLD))
                                .save(output, "_from_raw");

                MillstoneBuilder.of(registries)
                                .input(Tags.Items.RAW_MATERIALS_IRON)
                                .output(LibItems.IRON_DUST.get(), 2)
                                .unlockedBy(getHasName(Tags.Items.RAW_MATERIALS_IRON),
                                                has(Tags.Items.RAW_MATERIALS_IRON))
                                .save(output, "_from_raw");

                MillstoneBuilder.of(registries)
                                .input(Tags.Items.INGOTS_COPPER)
                                .output(LibItems.COPPER_DUST.get())
                                .unlockedBy(getHasName(Tags.Items.INGOTS_COPPER), has(Tags.Items.INGOTS_COPPER))
                                .save(output, "_from_ingot");

                MillstoneBuilder.of(registries)
                                .input(Tags.Items.INGOTS_GOLD)
                                .output(LibItems.GOLD_DUST.get())
                                .unlockedBy(getHasName(Tags.Items.INGOTS_GOLD), has(Tags.Items.INGOTS_GOLD))
                                .save(output, "_from_ingot");

                MillstoneBuilder.of(registries)
                                .input(Tags.Items.INGOTS_IRON)
                                .output(LibItems.IRON_DUST.get())
                                .unlockedBy(getHasName(Tags.Items.INGOTS_IRON), has(Tags.Items.INGOTS_IRON))
                                .save(output, "_from_ingot");

                MillstoneBuilder.of(registries)
                                .input(ItemTags.COALS)
                                .output(LibItems.CARBON_DUST.get())
                                .unlockedBy(getHasName(ItemTags.COALS), has(ItemTags.COALS))
                                .save(output);

                MillstoneBuilder.of(registries)
                                .input(Tags.Items.CROPS_SUGAR_CANE)
                                .output(Items.SUGAR, 2)
                                .unlockedBy(getHasName(Tags.Items.CROPS_SUGAR_CANE), has(Tags.Items.CROPS_SUGAR_CANE))
                                .save(output);

                MillstoneBuilder.of(registries)
                                .input(ItemTags.LOGS)
                                .output(LibItems.SAWDUST.get(), 2)
                                .unlockedBy(getHasName(ItemTags.LOGS), has(ItemTags.LOGS))
                                .save(output);

                MillstoneBuilder.of(registries)
                                .input(Tags.Items.GEMS_QUARTZ)
                                .output(LibItems.QUARTZ_DUST.get())
                                .unlockedBy(getHasName(Tags.Items.GEMS_QUARTZ), has(Tags.Items.GEMS_QUARTZ))
                                .save(output);
                MillstoneBuilder.of(registries)
                                .input(Tags.Items.GEMS_LAPIS)
                                .output(LibItems.LAPIS_DUST.get())
                                .unlockedBy(getHasName(Tags.Items.GEMS_LAPIS), has(Tags.Items.GEMS_LAPIS))
                                .save(output);
                MillstoneBuilder.of(registries)
                                .input(Tags.Items.GEMS_EMERALD)
                                .output(LibItems.EMERALD_DUST.get())
                                .unlockedBy(getHasName(Tags.Items.GEMS_EMERALD), has(Tags.Items.GEMS_EMERALD))
                                .save(output);
                MillstoneBuilder.of(registries)
                                .input(Tags.Items.GEMS_DIAMOND)
                                .output(LibItems.DIAMOND_DUST.get())
                                .unlockedBy(getHasName(Tags.Items.GEMS_DIAMOND), has(Tags.Items.GEMS_DIAMOND))
                                .save(output);

                MillstoneBuilder.of(registries)
                                .input(Tags.Items.GEMS_AMETHYST)
                                .output(LibItems.AMETHYST_DUST.get())
                                .unlockedBy(getHasName(Tags.Items.GEMS_AMETHYST), has(Tags.Items.GEMS_AMETHYST))
                                .save(output);

                MillstoneBuilder.of(registries)
                                .input(Items.PRISMARINE_SHARD)
                                .output(Items.PRISMARINE_CRYSTALS)
                                .unlockedBy(getHasName(Items.PRISMARINE_SHARD), has(Items.PRISMARINE_CRYSTALS))
                                .save(output);

                MillstoneBuilder.of(registries)
                                .input(Items.AMETHYST_BLOCK)
                                .output(Items.AMETHYST_SHARD, 4)
                                .unlockedBy(getHasName(Items.AMETHYST_BLOCK), has(Items.AMETHYST_BLOCK))
                                .save(output);

                FarmlandFuelsBuilder.of(registries)
                                .fluid(Fluids.WATER, 1000)
                                .unlockedBy(getHasName(zBlocks.MECHANICAL_FARMLAND.get()),
                                                has(zBlocks.MECHANICAL_FARMLAND.get()))
                                .save(output);

                FarmlandFuelsBuilder.of(registries)
                                .fluid(zFluids.SOY_WHEY.getFluid(), 250)
                                .unlockedBy(getHasName(zBlocks.MECHANICAL_FARMLAND.get()),
                                                has(zBlocks.MECHANICAL_FARMLAND.get()))
                                .save(output);

                FarmlandFuelsBuilder.of(registries)
                                .fluid(zFluids.FERTILIZER_CHEMICAL.getFluid(), 450)
                                .unlockedBy(getHasName(zBlocks.MECHANICAL_FARMLAND.get()),
                                                has(zBlocks.MECHANICAL_FARMLAND.get()))
                                .save(output);

                FarmlandFuelsBuilder.of(registries)
                                .fluid(zFluids.FERTILIZER_NATURAL.getFluid(), 350)
                                .unlockedBy(getHasName(zBlocks.MECHANICAL_FARMLAND.get()),
                                                has(zBlocks.MECHANICAL_FARMLAND.get()))
                                .save(output);

        }

        public static final class RecipeRunner extends RecipeProvider.Runner {
                public RecipeRunner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
                        super(output, lookupProvider);
                }

                @Override
                protected RecipeProvider createRecipeProvider(
                                HolderLookup.Provider lookupProvider,
                                RecipeOutput output) {
                        return new DataRecipe(lookupProvider, output);
                }

                @Override
                public String getName() {
                        return "Vintage Tech";
                }
        }

        @Override
        public HolderGetter<Item> getItems() {
                return items;
        }

        @Override
        public String getModName() {
                return MODULE_ID;
        }

        @Override
        public Provider getProvider() {
                return registries;
        }

        // TODO API : change pillar to tweak count
        private void pillar(ItemLike beam, ItemLike log) {
                shaped(RecipeCategory.BUILDING_BLOCKS,
                                beam, 8)
                                .define('#', log)
                                .pattern("#")
                                .pattern("#")
                                .unlockedBy(getHasName(log), has(log))
                                .save(output);
        }

}