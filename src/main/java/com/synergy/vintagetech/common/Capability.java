package com.synergy.vintagetech.common;

import com.devdyna.cakesticklib.api.CapabilityUtils;
import com.synergy.vintagetech.init.types.zBlocks;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class Capability {

    @SubscribeEvent
    public static void register(RegisterCapabilitiesEvent event) {
        // CapabilityUtils.registerFluidBlocks(event, zBlocks.ENGINE.get());

        CapabilityUtils.registerItemBlock(event, zBlocks.BASKET.get());
    }
}
