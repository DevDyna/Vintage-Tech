package com.synergy.vintagetech.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zItems;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.neoforged.neoforge.common.conditions.AlwaysCondition;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Strippable;

public class DataMaps extends DataMapProvider {

        public DataMaps(PackOutput o, CompletableFuture<Provider> p) {
                super(o, p);
        }

        @Override
        protected void gather(Provider p) {

                builder(NeoForgeDataMaps.STRIPPABLES)
                                .add(zBlocks.OAK_BEAM.getId(),
                                                new Strippable(zBlocks.STRIPPED_OAK_BEAM.get()),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zBlocks.BIRCH_BEAM.getId(),
                                                new Strippable(zBlocks.STRIPPED_BIRCH_BEAM.get()),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zBlocks.SPRUCE_BEAM.getId(),
                                                new Strippable(zBlocks.STRIPPED_SPRUCE_BEAM.get()),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zBlocks.JUNGLE_BEAM.getId(),
                                                new Strippable(zBlocks.STRIPPED_JUNGLE_BEAM.get()),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zBlocks.ACACIA_BEAM.getId(),
                                                new Strippable(zBlocks.STRIPPED_ACACIA_BEAM.get()),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zBlocks.DARK_OAK_BEAM.getId(),
                                                new Strippable(zBlocks.STRIPPED_DARK_OAK_BEAM.get()),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zBlocks.CHERRY_BEAM.getId(),
                                                new Strippable(zBlocks.STRIPPED_CHERRY_BEAM.get()),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zBlocks.MANGROVE_BEAM.getId(),
                                                new Strippable(zBlocks.STRIPPED_MANGROVE_BEAM.get()),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zBlocks.PALE_OAK_BEAM.getId(),
                                                new Strippable(zBlocks.STRIPPED_PALE_OAK_BEAM.get()),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zBlocks.BAMBOO_BEAM.getId(),
                                                new Strippable(zBlocks.STRIPPED_BAMBOO_BEAM.get()),
                                                false, AlwaysCondition.INSTANCE)

                                .add(zBlocks.IRONWOOD_LOG.getId(),
                                                new Strippable(zBlocks.STRIPPED_IRONWOOD_LOG.get()),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zBlocks.IRONWOOD_WOOD.getId(),
                                                new Strippable(zBlocks.STRIPPED_IRONWOOD_WOOD.get()),
                                                false, AlwaysCondition.INSTANCE);

                builder(NeoForgeDataMaps.COMPOSTABLES)
                                .add(zBlocks.IRONWOOD_LEAVES.getId(),
                                                new Compostable(0.3F, false),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zBlocks.IRONWOOD_SAPLING.getId(),
                                                new Compostable(0.3F, false),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zItems.ALOE.getId(),
                                                new Compostable(0.3F, false),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zItems.BLUEBERRIES.getId(),
                                                new Compostable(0.3F, false),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zBlocks.LAVENDER.getId(),
                                                new Compostable(0.3F, false),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zItems.HEMP.getId(),
                                                new Compostable(0.3F, false),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zItems.HEMP_SEEDS.getId(),
                                                new Compostable(0.3F, false),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zItems.CAVE_WHEAT_SEEDS.getId(),
                                                new Compostable(0.3F, false),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zItems.IRONBERRIES.getId(),
                                                new Compostable(0.3F, false),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zItems.SOYBEANS.getId(),
                                                new Compostable(0.3F, false),
                                                false, AlwaysCondition.INSTANCE);

                builder(NeoForgeDataMaps.FURNACE_FUELS)
                                .add(zItems.GLUE.getId(),
                                                new FurnaceFuel(AbstractFurnaceBlockEntity.BURN_TIME_STANDARD * 2),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zItems.BITUMEN.getId(),
                                                new FurnaceFuel(AbstractFurnaceBlockEntity.BURN_TIME_STANDARD * 4),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zBlocks.CRUSHING_TUB.getId(),
                                                new FurnaceFuel(AbstractFurnaceBlockEntity.BURN_TIME_STANDARD * 2),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zTags.Items.BEAMS.location(),
                                                new FurnaceFuel(AbstractFurnaceBlockEntity.BURN_TIME_STANDARD * 3 / 4),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zItems.MESH.getId(),
                                                new FurnaceFuel(AbstractFurnaceBlockEntity.BURN_TIME_STANDARD / 2),
                                                false, AlwaysCondition.INSTANCE);

        }

}
