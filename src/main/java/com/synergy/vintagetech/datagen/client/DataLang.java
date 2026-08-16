package com.synergy.vintagetech.datagen.client;

import static com.devdyna.cakesticklib.api.datagen.LangUtils.*;
import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.List;

import com.devdyna.cakesticklib.CakeStickLib;
import com.devdyna.cakesticklib.api.datagen.LangGenerators;
import com.devdyna.cakesticklib.api.datagen.LangUtils;
import com.devdyna.cakesticklib.api.datagen.LangUtils.TipColors;
import com.devdyna.cakesticklib.api.utils.StringUtil;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zItems;
import com.synergy.vintagetech.init.types.zFluids;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

@SuppressWarnings("unused")
public class DataLang extends LanguageProvider implements LangGenerators {

        public DataLang(PackOutput o) {
                super(o, MODULE_ID, "en_us");
        }

        @Override
        protected void addTranslations() {

                zBlocks.zRender.getEntries()
                                .forEach(b -> addBlock(b, LangUtils.TipColors.RED + "DEV BLOCK ONLY, DON'T USE!"));
                zFluids.zFluidTypes.getEntries()
                                .forEach(f -> addFluid(f.get(), named(f, MODULE_ID).replace(" Type", "")));
                zItems.zBucketItems.getEntries().forEach(i -> addItem(i, named(i, MODULE_ID)));
                zBlocks.zBlockItem.getEntries().forEach(b -> addBlock(b, LangUtils.named(b, MODULE_ID)));
                zItems.zItem.getEntries().forEach(b -> addItem(b, LangUtils.named(b, MODULE_ID)));
                zBlocks.zBlockFluids.getEntries().forEach(b -> addBlock(b, LangUtils.named(b, MODULE_ID)));

                // crops blocks required
                List.of(
                                zBlocks.HEMP,
                                zBlocks.ALOE_PLANT,
                                zBlocks.BLUEBERRY_BUSH,
                                zBlocks.CAVE_WHEAT).forEach(b -> addBlock(b, named(b, MODULE_ID)));

                add(MODULE_ID + ".jei.alias.rpm.base", "Mechanical Rotation Block");
                add(MODULE_ID + ".jei.alias.rpm.transmission", "Transmission");
                add(MODULE_ID + ".jei.alias.rpm.rotation.change", "Change Rotation");
                add(MODULE_ID + ".jei.alias.rpm.activation.break", "Disable Network");
                add(MODULE_ID + ".jei.alias.rpm.generator", "Generator");

                add(MODULE_ID + ".creative_tab." + MODULE_ID, "Vintage Tech");

                List.of(
                                "centrifuge",
                                "crushing_tub",
                                "drying_rack",
                                "evaporation_basin",
                                "millstone",
                                "tree_tap",
                                "crucible"

                ).forEach(s -> add(MODULE_ID + ".jei." + s, StringUtil.formatToDisplay(s) + " Recipes"));

                add(MODULE_ID + ".jei.cheese", "Cheese Processing Info");

                add(MODULE_ID + ".jei.farmland_fuels", "Mechanical Farmland Fuels");

                add(MODULE_ID + ".basket.info",
                                TipColors.ITEM_TOOLTIP + "A portable chest that can absorb items");

                add(MODULE_ID + ".centrifuge.info",
                                TipColors.ITEM_TOOLTIP + "Mix ingredients to create new ones");

                add(MODULE_ID + ".crucible.info",
                                TipColors.ITEM_TOOLTIP + "Processes items and fluids when heated from below");

                add(MODULE_ID + ".crushing_tub.info",
                                TipColors.ITEM_TOOLTIP + "Processes items when a living entity jumps on it");

                add(MODULE_ID + ".drying_rack.info",
                                TipColors.ITEM_TOOLTIP + "Dries items over time");

                add(MODULE_ID + ".evaporation_basin.info",
                                TipColors.ITEM_TOOLTIP + "Evaporates fluids into items");

                add(MODULE_ID + ".fan.info",
                                TipColors.ITEM_TOOLTIP + "Creates an airflow that pushes items and entities");

                add(MODULE_ID + ".mechanical_farmland.info",
                                TipColors.ITEM_TOOLTIP
                                                + "Speeds up crops planted above it and automatically harvests them when fully grown");

                add(MODULE_ID + ".millstone.info",
                                TipColors.ITEM_TOOLTIP + "Crushes items into other items");

                add(MODULE_ID + ".saw.info",
                                TipColors.ITEM_TOOLTIP + "Breaks blocks and entire trees when possible");

                add(MODULE_ID + ".clutch.info",
                                TipColors.ITEM_TOOLTIP + "Disconnects part of the network when actived by redstone");
                add(MODULE_ID + ".gearshift.info",
                                TipColors.ITEM_TOOLTIP
                                                + "Reverses the rotation of part of the network when actived by redstone");

                add(MODULE_ID + ".treetap.info",
                                TipColors.ITEM_TOOLTIP + "Extracts fluids from trees");
                add(MODULE_ID + ".treetap.output",
                                TipColors.ITEM_TOOLTIP
                                                + "Inserts the output into any valid container below");

                add(MODULE_ID + ".windmill.info",
                                TipColors.ITEM_TOOLTIP + "Generates Rotational Power");

                add(MODULE_ID + ".windmill.rain",
                                TipColors.ITEM_TOOLTIP + "Can break during rain if not enclosed");

                add(MODULE_ID + ".rpm.require",
                                TipColors.ITEM_TOOLTIP + "Requires Rotational Power to operate");

                add(MODULE_ID + ".hemp.spawn",
                                TipColors.ITEM_TOOLTIP + "Can be found in jungle biomes");

                add(MODULE_ID + ".soy.spawn",
                                TipColors.ITEM_TOOLTIP + "Can be found in savanna biomes");

                add(MODULE_ID + ".aloe.spawn",
                                TipColors.ITEM_TOOLTIP + "Can be found in desert biomes");

                add(MODULE_ID + ".cave_wheat.spawn",
                                TipColors.ITEM_TOOLTIP + "Can be found underground");

                add(MODULE_ID + ".blueberries.spawn",
                                TipColors.ITEM_TOOLTIP + "Can be found in forests, coniferous biomes, and meadows");

                add(MODULE_ID + ".lavender.spawn",
                                TipColors.ITEM_TOOLTIP + "Can be found in plains biomes");

                add(MODULE_ID + ".ironwood.spawn",
                                TipColors.ITEM_TOOLTIP + "Can be found in forests , windswept biomes and meadows");

                add(MODULE_ID + ".treetap.handler",
                                TipColors.ITEM_TOOLTIP + "Can receive fluids extracted by a Tree Tap");

                add(MODULE_ID + ".windmill.repair",
                                TipColors.ITEM_TOOLTIP + "Can be used to repair a Cracked Windmill");

                add(MODULE_ID + ".rope.info",
                                TipColors.ITEM_TOOLTIP + "Decorative block that can connect to various blocks");

                add(MODULE_ID + ".jei.cheese.aging.condition","Require light level below 5");
                add(MODULE_ID + ".jei.cheese.aging", "Sealed cheese can age over time");
                add(MODULE_ID + ".jei.cheese.sealing", "Right click to seal");
                add(MODULE_ID + ".jei.cheese.unsealing", "Right click to unseal");
                add(MODULE_ID + ".jei.cheese.slicing", "When broken it will drop slices");

        }

}
