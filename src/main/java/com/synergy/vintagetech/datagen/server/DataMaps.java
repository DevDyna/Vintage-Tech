package com.synergy.vintagetech.datagen.server;

import java.util.concurrent.CompletableFuture;

import com.synergy.vintagetech.init.types.zBlocks;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.conditions.AlwaysCondition;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Strippable;

public class DataMaps extends DataMapProvider {

        public DataMaps(PackOutput o, CompletableFuture<Provider> p) {
                super(o, p);
        }

        @Override
        protected void gather(Provider p) {

                builder(NeoForgeDataMaps.STRIPPABLES)
                                .add(zBlocks.IRONWOOD_LOG.getKey(),
                                                new Strippable(zBlocks.STRIPPED_IRONWOOD_LOG.get()),
                                                false, AlwaysCondition.INSTANCE);

                builder(NeoForgeDataMaps.STRIPPABLES)
                                .add(zBlocks.IRONWOOD_WOOD.getKey(),
                                                new Strippable(zBlocks.STRIPPED_IRONWOOD_WOOD.get()),
                                                false, AlwaysCondition.INSTANCE);

        }

}
