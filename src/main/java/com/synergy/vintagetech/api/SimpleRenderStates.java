package com.synergy.vintagetech.api;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;
//TODO maybe move to API?
public class SimpleRenderStates {

    public static class ItemContainer extends BlockEntityRenderState {
        public ItemStackRenderState render = new ItemStackRenderState();
        public ItemStack item;
    }

    public static class FluidBasin extends ItemContainer {
        public FluidStack fluid;
    }

}
