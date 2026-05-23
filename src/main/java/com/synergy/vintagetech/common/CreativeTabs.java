package com.synergy.vintagetech.common;

import com.devdyna.cakesticklib.api.CreativeTabUtils;
import com.synergy.vintagetech.init.types.zItems;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class CreativeTabs {

    @SubscribeEvent
    public static void register(BuildCreativeModeTabContentsEvent event) {

        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS)
            CreativeTabUtils.accept(event, zItems.zBlockItem, zItems.zItem);

    }
}
