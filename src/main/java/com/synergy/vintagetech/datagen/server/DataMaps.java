package com.synergy.vintagetech.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.synergy.vintagetech.api.factories.beams.BeamFactory;
import com.synergy.vintagetech.api.factories.trees.TreeFactory;
import com.synergy.vintagetech.api.factories.trees.TreeSet;
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

                var strippable = builder(NeoForgeDataMaps.STRIPPABLES);
                var compostable = builder(NeoForgeDataMaps.COMPOSTABLES);
                var furnace_fuels = builder(NeoForgeDataMaps.FURNACE_FUELS);

                furnace_fuels.add(zTags.Items.BEAMS_THAT_BURN,
                                new FurnaceFuel(AbstractFurnaceBlockEntity.BURN_TIME_STANDARD * 3 / 4),
                                false, AlwaysCondition.INSTANCE);

                for (var beam : BeamFactory.getAll()) {

                        strippable.add(beam.beam_log().getId(),
                                        new Strippable(beam.beam_stripped_log().get()), false,
                                        AlwaysCondition.INSTANCE);

                        if (beam.isSpecial())
                                continue;

                        strippable.add(beam.beam_wood().getId(),
                                        new Strippable(beam.beam_stripped_wood().get()),
                                        false, AlwaysCondition.INSTANCE);

                }

                for (TreeSet tree : TreeFactory.getAll()) {
                        strippable.add(tree.log().getId(),
                                        new Strippable(tree.strippedLog().get()),
                                        false, AlwaysCondition.INSTANCE)
                                        .add(tree.wood().getId(),
                                                        new Strippable(tree.strippedWood().get()),
                                                        false, AlwaysCondition.INSTANCE);

                        compostable.add(tree.leaves().getId(),
                                        new Compostable(0.3F, false),
                                        false, AlwaysCondition.INSTANCE)
                                        .add(tree.sapling().getId(),
                                                        new Compostable(0.3F, false),
                                                        false, AlwaysCondition.INSTANCE);

                        furnace_fuels.add(tree.signItem().getId(),
                                        new FurnaceFuel(AbstractFurnaceBlockEntity.BURN_TIME_STANDARD),
                                        false, AlwaysCondition.INSTANCE);

                        furnace_fuels.add(tree.hangingSignItem().getId(),
                                        new FurnaceFuel(AbstractFurnaceBlockEntity.BURN_TIME_STANDARD),
                                        false, AlwaysCondition.INSTANCE);

                }

                compostable
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

                furnace_fuels
                                .add(zItems.GLUE.getId(),
                                                new FurnaceFuel(AbstractFurnaceBlockEntity.BURN_TIME_STANDARD * 2),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zItems.BITUMEN.getId(),
                                                new FurnaceFuel(AbstractFurnaceBlockEntity.BURN_TIME_STANDARD * 4),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zBlocks.CRUSHING_TUB.getId(),
                                                new FurnaceFuel(AbstractFurnaceBlockEntity.BURN_TIME_STANDARD * 2),
                                                false, AlwaysCondition.INSTANCE)
                                .add(zItems.MESH.getId(),
                                                new FurnaceFuel(AbstractFurnaceBlockEntity.BURN_TIME_STANDARD / 2),
                                                false, AlwaysCondition.INSTANCE);

        }

}
