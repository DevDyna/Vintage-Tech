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
import com.synergy.vintagetech.init.types.zFluids;
import com.synergy.vintagetech.init.types.zItems;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;

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
                                .output(Items.SUGAR)
                                .unlockedBy(getHasName(Items.WATER_BUCKET), has(Items.WATER_BUCKET))
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
        .input(Items.SUGAR)//TODO IMP : item salt
        .fluid(Fluids.WATER,125)
        .output(zFluids.SALT_SOLUTION.getFluid(),125)
        .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
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