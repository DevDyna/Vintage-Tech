package com.synergy.vintagetech.api.recipeinput;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.neoforged.neoforge.fluids.FluidStack;

public record CentrifugeInput(FluidStack fluid ,ItemStack catalyst) implements RecipeInput{

    @Override
    public ItemStack getItem(int index) {
        return fluid.getFluidType().getBucket(fluid);
    }

    @Override
    public int size() {
        return 1;
    }
    
}
