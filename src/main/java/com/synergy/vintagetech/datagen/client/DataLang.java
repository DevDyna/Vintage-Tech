package com.synergy.vintagetech.datagen.client;

import static com.devdyna.cakesticklib.api.datagen.LangUtils.*;
import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.datagen.LangUtils;
import com.synergy.vintagetech.init.types.zBlocks;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("unused")
public class DataLang extends LanguageProvider {

        public DataLang(PackOutput o) {
                super(o, MODULE_ID, "en_us");
        }

        @Override
        protected void addTranslations() {

                zBlocks.zBlockItem.getEntries().forEach(b->addBlock(b, LangUtils.named(b, MODULE_ID)));

        }

}
