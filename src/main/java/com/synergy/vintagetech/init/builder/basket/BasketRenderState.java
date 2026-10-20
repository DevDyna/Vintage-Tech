package com.synergy.vintagetech.init.builder.basket;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;

public class BasketRenderState extends BlockEntityRenderState {
    public ItemStackRenderState[] renders = new ItemStackRenderState[27] ;
    public ItemStack[] items = new ItemStack[27];
    public Direction dir ;

    public BasketRenderState(){

        for (int i = 0; i < BasketBE.INVENTORY_SLOTS; i++) {
            items[i] = ItemStack.EMPTY;
            renders[i] = new ItemStackRenderState(); 
        }

    }

}