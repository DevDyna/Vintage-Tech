package com.synergy.vintagetech.datagen.client;

import static com.devdyna.cakesticklib.api.datagen.LangUtils.*;
import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.datagen.LangUtils;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zItems;
import com.synergy.vintagetech.init.types.zFluids;
import com.synergy.vintagetech.api.LangAddition;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("unused")
public class DataLang extends LanguageProvider implements LangAddition {

        public DataLang(PackOutput o) {
                super(o, MODULE_ID, "en_us");
        }

        @Override
        protected void addTranslations() {

                zBlocks.zRender.getEntries().forEach(b -> addBlock(b, LangUtils.TipColors.RED + "DEV BLOCK ONLY, DON'T USE!"));
                zFluids.zFluidTypes.getEntries().forEach(f -> addFluid(f.get(), named(f, MODULE_ID).replace(" Type", "")));
                zItems.zBucketItems.getEntries().forEach(i -> addItem(i, named(i, MODULE_ID)));
                zBlocks.zBlockItem.getEntries().forEach(b -> addBlock(b, LangUtils.named(b, MODULE_ID)));
                zItems.zItem.getEntries().forEach(b -> addItem(b, LangUtils.named(b, MODULE_ID)));
                zBlocks.zBlockFluids.getEntries().forEach(b -> addBlock(b, LangUtils.named(b, MODULE_ID)));

                add(MODULE_ID + ".jei.alias.rpm.base", "Mechanical Rotation Block");
                add(MODULE_ID + ".jei.alias.rpm.transmission", "Transmission");
                add(MODULE_ID + ".jei.alias.rpm.rotation.change", "Change Rotation");
                add(MODULE_ID + ".jei.alias.rpm.generator", "Generator");

                add(MODULE_ID + ".creative_tab." + MODULE_ID, "Vintage Tech");

        }

}
