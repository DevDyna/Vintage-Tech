package com.synergy.vintagetech.init.types;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.synergy.vintagetech.api.FluidRegister;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;

public class zFluids {
        public static void register(IEventBus bus) {
                zFluids.register(bus);
                zFluidTypes.register(bus);
        }

        // ---------------------------------------------------------------------------------------//
        public static final DeferredRegister<Fluid> zFluids = DeferredRegister.create(BuiltInRegistries.FLUID,
                        MODULE_ID);
        public static final DeferredRegister<FluidType> zFluidTypes = DeferredRegister.create(Keys.FLUID_TYPES,
                        MODULE_ID);

        public static final FluidRegister SALT_SOLUTION = FluidRegister.create("salt_solution");
        public static final FluidRegister SOY_WHEY = FluidRegister.create("soy_whey");

}
