package com.synergy.vintagetech.init.builder.crucible;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

public class CrucibleRenderState extends BlockEntityRenderState {
    public ItemStackRenderState[] renders = new ItemStackRenderState[] {
            new ItemStackRenderState(), new ItemStackRenderState(), new ItemStackRenderState(),
            new ItemStackRenderState(), new ItemStackRenderState(), new ItemStackRenderState(),
            new ItemStackRenderState(), new ItemStackRenderState(), new ItemStackRenderState() };
    public ItemStack[] items = new ItemStack[] {
            ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,
            ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,
            ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY
    };

    public FluidStack fluid;

}
