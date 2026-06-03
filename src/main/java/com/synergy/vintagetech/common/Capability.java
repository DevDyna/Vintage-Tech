package com.synergy.vintagetech.common;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class Capability {

    @SubscribeEvent
    public static void register(RegisterCapabilitiesEvent event) {
        // CapabilityUtils.registerFluidBlocks(event, zBlocks.ENGINE.get());
    }
}
