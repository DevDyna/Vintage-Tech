package com.synergy.vintagetech.init.builder.crucible;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

public class CrucibleRenderState extends BlockEntityRenderState {
    public ItemStackRenderState[] renders = new ItemStackRenderState[9] ;
    public ItemStack[] items = new ItemStack[9] ;

    public FluidStack fluid;

    public CrucibleRenderState(){

        for (int i = 0; i < 2; i++) {
            items[i] = ItemStack.EMPTY;
            renders[i] = new ItemStackRenderState(); 
        }

    }

}
