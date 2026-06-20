package com.synergy.vintagetech.common;

import com.devdyna.cakesticklib.api.utils.ModAddonUtil;
import com.synergy.vintagetech.init.types.zRecipeTypes;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;

public class RecipeSender {
    @SubscribeEvent
    public static void onDatapackSync(OnDatapackSyncEvent event) {
        if (ModAddonUtil.checkMod("jei")) {
            event.sendRecipes(
                    zRecipeTypes.CRUSHING_TUB.getType(),
                    zRecipeTypes.DRYING_RACK.getType(),
                    zRecipeTypes.EVAPORATION_BASIN.getType(),
                    zRecipeTypes.MILLSTONE.getType());
        }

    }
}
