package com.synergy.vintagetech.datagen.server;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.api.datagen.RecipeGenerators;
import com.devdyna.cakesticklib.setup.registry.LibItems;
import com.synergy.vintagetech.init.builder.centrifuge.recipe.CentrifugeBuilder;
import com.synergy.vintagetech.init.builder.crushing_tub.recipe.CrushingTubBuilder;
import com.synergy.vintagetech.init.builder.drying_rack.recipe.DryingRackBuilder;
import com.synergy.vintagetech.init.builder.evaporation_basin.recipe.EvaporationBasinBuilder;
import com.synergy.vintagetech.init.builder.millstone.recipe.MillstoneBuilder;
import com.synergy.vintagetech.init.builder.treetap.recipe.TreeTapBuilder;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zFluids;
import com.synergy.vintagetech.init.types.zItems;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.Tags;

public class DataRecipe extends RecipeProvider implements RecipeGenerators {

        protected DataRecipe(Provider registries, RecipeOutput output) {
                super(registries, output);
        }

        @Override
        protected void buildRecipes() {

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
                                .output(zItems.OIL)
                                .unlockedBy(getHasName(zBlocks.EVAPORATION_BASIN.get()),
                                                has(zBlocks.EVAPORATION_BASIN.get()))
                                .save(output);

                EvaporationBasinBuilder.of(registries)
                                .fluid(zFluids.SAP.getFluid(), 125)
                                .output(zItems.SAP)
                                .unlockedBy(getHasName(zBlocks.EVAPORATION_BASIN.get()),
                                                has(zBlocks.EVAPORATION_BASIN.get()))
                                .save(output);

                DryingRackBuilder.of(registries)
                                .input(ItemTags.SAPLINGS)
                                .output(Items.DEAD_BUSH)
                                .unlockedBy(getHasName(ItemTags.SAPLINGS), has(ItemTags.SAPLINGS))
                                .save(output);

                MillstoneBuilder.of(registries)
                                .input(Items.WHEAT)
                                .output(LibItems.FLOUR, 2)
                                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                                .save(output);

                MillstoneBuilder.of(registries)
                                .input(zItems.SOYBEANS)
                                .output(zItems.MASHED_SOYBEANS, 2)
                                .unlockedBy(getHasName(zItems.SOYBEANS.get()), has(zItems.SOYBEANS.get()))
                                .save(output);

                CentrifugeBuilder.of(registries)
                                .input(zItems.SALT)
                                .fluid(Fluids.WATER, 125)
                                .output(zFluids.SALT_SOLUTION.getFluid(), 125)
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
                                .output(zFluids.LATEX.getFluid(), 25)
                                .unlockedBy(getHasName(zBlocks.TREE_TAP.get()), has(zBlocks.TREE_TAP.get()))
                                .save(output, "_from_acacia");

                TreeTapBuilder.of(registries)
                                .log(Tags.Blocks.NETHER_NATURAL_LOGS)
                                .leaves(BlockTags.WART_BLOCKS)
                                .output(zFluids.OIL.getFluid(), 25)
                                .unlockedBy(getHasName(zBlocks.TREE_TAP.get()), has(zBlocks.TREE_TAP.get()))
                                .save(output);

                shapeless(RecipeCategory.MISC, zItems.FIBER.get())
                                .requires(zItems.HEMP.get())
                                .unlockedBy(getHasName(zItems.HEMP.get()), has(zItems.HEMP.get()))
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

}