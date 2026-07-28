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

        public static final FluidRegister SALT_SOLUTION = FluidRegister.create("salt_solution",
                        ColorUtils.argb(255, 236, 202, 202));
        public static final FluidRegister SOY_WHEY = FluidRegister.create("soy_whey",
                        ColorUtils.argb(255, 255, 236, 236));

        public static final FluidRegister RESIN = FluidRegister.create("resin", ColorUtils.argb(255, 175, 119, 56));
        public static final FluidRegister LATEX = FluidRegister.create("latex", ColorUtils.argb(255, 246, 238, 228));
        public static final FluidRegister SAP = FluidRegister.create("sap", ColorUtils.argb(255, 255, 211, 72));
        public static final FluidRegister OIL = FluidRegister.create("oil", ColorUtils.argb(255, 10, 10, 10));
        public static final FluidRegister IRONBERRY_JUICE = FluidRegister.create("ironberry_juice",
                        ColorUtils.argb(255, 192, 192, 192));

        public static final FluidRegister FERTILIZER_NATURAL = FluidRegister.create("natural_fertilizer",
                        ColorUtils.argb(255, 177, 236, 177));

        public static final FluidRegister FERTILIZER_CHEMICAL = FluidRegister.create("chemical_fertilizer",
                        ColorUtils.argb(255, 236, 177, 177));

        public static final FluidRegister FERTILIZER_MINERAL = FluidRegister.create("mineral_fertilizer",
                        ColorUtils.argb(255, 177, 177, 236));

        public static final FluidRegister SULFURIC_ACID = FluidRegister.create("sulfuric_acid",
                        ColorUtils.argb(255, 75, 200, 200));

        public static final FluidRegister SOYMILK = FluidRegister.create("soymilk",
                        ColorUtils.argb(255, 200, 200, 200));

}
