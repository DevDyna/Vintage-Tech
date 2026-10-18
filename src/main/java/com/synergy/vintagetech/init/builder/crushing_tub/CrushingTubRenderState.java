package com.synergy.vintagetech.init.builder.crushing_tub;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

public class CrushingTubRenderState extends BlockEntityRenderState {

    public ItemStackRenderState render = new ItemStackRenderState();
    public ItemStack item;
    public FluidStack fluid;

}