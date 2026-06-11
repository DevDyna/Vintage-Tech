package com.synergy.vintagetech.init;

import com.synergy.vintagetech.init.types.*;

import net.neoforged.bus.api.IEventBus;

public class Material {
        public static void register(IEventBus bus) {
                zItems.register(bus);
                zBlocks.register(bus);
                zBlockEntities.register(bus);
                zRecipeTypes.register(bus);
                zTags.register(bus);
                zParticles.register(bus);
        }


}
