package com.synergy.vintagetech.common;

import com.devdyna.cakesticklib.api.RegistryUtils;
import com.synergy.vintagetech.api.factories.trees.TreeFactory;

import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

public class FlowerPotRegistry {
    public static void register(final FMLCommonSetupEvent e) {
        TreeFactory.getAll().forEach(s -> RegistryUtils.addFlowerPot(e, s.sapling(), s.pottedSapling()));
    }

}
