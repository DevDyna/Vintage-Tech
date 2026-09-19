package com.synergy.vintagetech.init.types;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.utils.ColorUtils;
import com.synergy.vintagetech.api.FluidRegister;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;

// @Deprecated
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

        public static final FluidRegister WHEY = FluidRegister.simple("whey",
                        ColorUtils.argb(255, 255, 236, 236));

        public static final FluidRegister OLIVE_OIL = FluidRegister.simple("olive_oil",
                        ColorUtils.argb(255, 128, 130, 70));

        public static final FluidRegister IRONBERRY_JUICE = FluidRegister.simple("ironberry_juice",
                        ColorUtils.argb(255, 192, 192, 192));

        public static final FluidRegister FERTILIZER_NATURAL = FluidRegister.simple("natural_fertilizer",
                        ColorUtils.argb(255, 177, 236, 177));

        public static final FluidRegister CALCIUM_CARBONATE = FluidRegister.simple("calcium_carbonate",
                        ColorUtils.argb(255, 236, 177, 177));

        public static final FluidRegister SOYMILK = FluidRegister.simple("soymilk",
                        ColorUtils.argb(255, 200, 200, 200));

        // public static final FluidRegister MILK_CURD =
        // FluidRegister.create("milk_curd",
        // ColorUtils.argb(200, 250, 250, 250));

}
