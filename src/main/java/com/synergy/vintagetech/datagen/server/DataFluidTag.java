package com.synergy.vintagetech.datagen.server;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.neoforged.neoforge.common.Tags;

public class DataFluidTag extends FluidTagsProvider {

        public DataFluidTag(PackOutput o, CompletableFuture<Provider> l) {
                super(o, l, MODULE_ID);
        }

        @Override
        protected void addTags(Provider p) {

                tag(Tags.Fluids.MILK).add();

        }

}