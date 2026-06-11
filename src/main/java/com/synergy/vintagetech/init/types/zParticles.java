package com.synergy.vintagetech.init.types;

import static com.synergy.vintagetech.Main.MODULE_ID;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zParticles {
    public static void register(IEventBus bus) {
        zParticle.register(bus);
    }


public static final DeferredRegister<ParticleType<?>> zParticle =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, MODULE_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FAN_AIR_FLOW =
            zParticle.register(
                    "fan_air_flow",
                    () -> new SimpleParticleType(false)
            );


}
