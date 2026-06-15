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

                zBlocks.zBlockItem.getEntries().forEach(b -> addBlock(b, LangUtils.named(b, MODULE_ID)));

                add(MODULE_ID + ".jei.alias.block.break", "Block Breaker");
                add(MODULE_ID + ".jei.alias.drill", "Drill");
                add(MODULE_ID + ".jei.alias.miner", "Miner");
                add(MODULE_ID + ".jei.alias.tree.cutter", "Tree cutter");
                add(MODULE_ID + ".jei.alias.tree.feller", "Tree feller");
                add(MODULE_ID + ".jei.alias.entity.killer", "Entity killer");
                add(MODULE_ID + ".jei.alias.fakeplayer", "FakePlayer");
                add(MODULE_ID + ".jei.alias.hopper", "Hopper");
                add(MODULE_ID + ".jei.alias.item.collector", "Item Collector");
                add(MODULE_ID + ".jei.alias.entity.mover", "Entity Mover");
                add(MODULE_ID + ".jei.alias.redstone.sensible", "Redstone Sensible");

                add(MODULE_ID + ".jei.alias.rpm.base", "Mechanical Rotation block");
                add(MODULE_ID + ".jei.alias.rpm.transmission", "Transmission");
                add(MODULE_ID + ".jei.alias.rpm.rotation.change", "Change Rotation");
                add(MODULE_ID + ".jei.alias.rpm.generator", "Generator");

        }

}
