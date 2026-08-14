package com.synergy.vintagetech;

import com.devdyna.cakesticklib.api.utils.ModAddonUtil;
import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.init.Material;

import guideme.Guide;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(Main.MODULE_ID)
public class Main {

    public static final String MODULE_ID = "vintagetech";

    public Main(IEventBus bus, ModContainer c) {
        Material.register(bus);
        GameEvents.build(bus, c);

        if (ModAddonUtil.checkMod("guideme"))
            Guide.builder(x.rl(MODULE_ID, "guide")).build();
    }

}
