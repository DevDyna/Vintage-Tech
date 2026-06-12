package com.synergy.vintagetech.datagen.server;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.concurrent.CompletableFuture;

import com.devdyna.cakesticklib.api.datagen.RecipeGenerators;
import com.synergy.vintagetech.init.builder.crushing_tub.recipe.CrushingTubBuilder;
import com.synergy.vintagetech.init.builder.evaporation_basin.recipe.EvaporationBasinBuilder;

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