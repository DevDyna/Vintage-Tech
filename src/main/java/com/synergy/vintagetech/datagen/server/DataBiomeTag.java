package com.synergy.vintagetech.datagen.server;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.concurrent.CompletableFuture;

import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.Tags;

public class DataBiomeTag extends BiomeTagsProvider {

        public DataBiomeTag(PackOutput o, CompletableFuture<Provider> p) {
                super(o, p, MODULE_ID);
        }

        @Override
        protected void addTags(Provider p) {

                tag(zTags.Biomes.CAVE_WHEAT_SPAWN)
                                .addTag(Tags.Biomes.IS_OVERWORLD);

                tag(zTags.Biomes.IRONWOOD_TREE_SPAWN)
                                .addTag(Tags.Biomes.IS_FOREST)
                                .addTag(Tags.Biomes.IS_TAIGA)
                                .add(Biomes.MEADOW)
                                .addTag(Tags.Biomes.IS_WINDSWEPT);

                tag(zTags.Biomes.ALOE_SPAWN)
                                .addTag(Tags.Biomes.IS_DESERT);

                tag(zTags.Biomes.LAVENDER_SPAWN)
                                .addTag(Tags.Biomes.IS_PLAINS);

                tag(zTags.Biomes.BLUEBERRIES_SPAWN)
                                .addTag(Tags.Biomes.IS_FOREST)
                                .addTag(Tags.Biomes.IS_TAIGA)
                                .add(Biomes.MEADOW)
                                .addTag(Tags.Biomes.IS_WINDSWEPT)
                                .addTag(Tags.Biomes.IS_CONIFEROUS_TREE);

                tag(zTags.Biomes.HEMP_SPAWN)
                                .addTag(Tags.Biomes.IS_JUNGLE);

                tag(zTags.Biomes.SOYBEANS_SPAWN)
                                .addTag(Tags.Biomes.IS_SAVANNA);

        }

}
