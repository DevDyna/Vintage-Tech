package com.synergy.vintagetech.common;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.List;

import com.devdyna.cakesticklib.api.ToolTipHelper;
import com.devdyna.cakesticklib.setup.registry.LibComponents;
import com.synergy.vintagetech.api.factories.EffectConsumableItem;
import com.synergy.vintagetech.init.types.*;

import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

public class ItemToolTipped {

        @SubscribeEvent
        public static void main(ItemTooltipEvent event) {

                var item = event.getItemStack();
                var t = event.getToolTip();

                ToolTipHelper.addToggle(t, item.is(zItems.HEMP_SEEDS),
                                MODULE_ID + ".hemp.spawn");
                ToolTipHelper.addToggle(t, item.is(zItems.SOYBEANS),
                                MODULE_ID + ".soy.spawn");
                ToolTipHelper.addToggle(t, item.is(zItems.ALOE),
                                MODULE_ID + ".aloe.spawn");
                ToolTipHelper.addToggle(t, item.is(zItems.CAVE_WHEAT_SEEDS),
                                MODULE_ID + ".cave_wheat.spawn");
                ToolTipHelper.addToggle(t, item.is(zItems.BLUEBERRIES),
                                MODULE_ID + ".blueberries.spawn");
                ToolTipHelper.addToggle(t, item.is(zBlocks.LAVENDER.get().asItem()),
                                MODULE_ID + ".lavender.spawn");
                ToolTipHelper.addToggle(t, item.is(zBlocks.IRONWOOD_SAPLING.get().asItem()),
                                MODULE_ID + ".ironwood.spawn");

                ToolTipHelper.addToggle(t, item.is(zTags.Items.WINDMILL_REPAIR),
                                MODULE_ID + ".windmill.repair");

                ToolTipHelper.addToggle(t, item.is(zBlocks.ROPE.get().asItem()),
                                MODULE_ID + ".rope.info");

                ToolTipHelper.addToggle(t, item.is(zBlocks.CENTRIFUGE.get().asItem()),
                                MODULE_ID + ".treetap.handler",
                                MODULE_ID + ".centrifuge.info");
                ToolTipHelper.addToggle(t, item.is(zBlocks.CRUCIBLE.get().asItem()),
                                MODULE_ID + ".treetap.handler",
                                MODULE_ID + ".crucible.info");
                ToolTipHelper.addToggle(t, item.is(zBlocks.CRUSHING_TUB.get().asItem()),
                                MODULE_ID + ".treetap.handler",
                                MODULE_ID + ".crushing_tub.info");
                ToolTipHelper.addToggle(t, item.is(zBlocks.DRYING_RACK.get().asItem()),
                                MODULE_ID + ".drying_rack.info");
                ToolTipHelper.addToggle(t, item.is(zBlocks.EVAPORATION_BASIN.get().asItem()),
                                MODULE_ID + ".treetap.handler",
                                MODULE_ID + ".evaporation_basin.info");
                ToolTipHelper.addToggle(t, item.is(zBlocks.FAN.get().asItem()),
                                MODULE_ID + ".fan.info");
                ToolTipHelper.addToggle(t, item.is(zBlocks.MECHANICAL_FARMLAND.get().asItem()),
                                MODULE_ID + ".mechanical_farmland.info");
                ToolTipHelper.addToggle(t, item.is(zBlocks.MILLSTONE.get().asItem()),
                                MODULE_ID + ".millstone.info");
                ToolTipHelper.addToggle(t, item.is(zBlocks.SAW.get().asItem()),
                                MODULE_ID + ".saw.info");
                ToolTipHelper.addToggle(t, item.is(zBlocks.CLUTCH.get().asItem()),
                                MODULE_ID + ".clutch.info");
                ToolTipHelper.addToggle(t, item.is(zBlocks.GEARSHIFT.get().asItem()),
                                MODULE_ID + ".gearshift.info");
                ToolTipHelper.addToggle(t, item.is(zBlocks.TREE_TAP.get().asItem()),
                                MODULE_ID + ".treetap.info",
                                MODULE_ID + ".treetap.output");
                ToolTipHelper.addToggle(t, item.is(zBlocks.WINDMILL.get().asItem()),
                                MODULE_ID + ".windmill.rain",
                                MODULE_ID + ".windmill.info");

                if (!item.has(LibComponents.ITEM_CONTAINER) || item.get(LibComponents.ITEM_CONTAINER) == null)
                        ToolTipHelper.addToggle(t, item.is(zBlocks.BASKET.get().asItem()),
                                        MODULE_ID + ".basket.info");

                // TODO API : ADD add(List<Component>,Component...)
                if (item.getItem() instanceof EffectConsumableItem effect)
                        add(t, effect.getEffectToolTip());

        }

        private static void add(List<Component> t, Component... s) {
                for (var c : s)
                        t.add(ToolTipHelper.INDEX, c);
        }

        private static void add(List<Component> t, List<Component> s) {
                add(t, s.toArray(Component[]::new));
        }

}