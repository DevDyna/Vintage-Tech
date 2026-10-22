package com.synergy.vintagetech.init.builder.centrifuge;

import com.synergy.vintagetech.api.factories.motion.MotionRenderState;

import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

public class CentrifugeRenderState extends MotionRenderState {
        public ItemStackRenderState[] renders = new ItemStackRenderState[2];
        public ItemStack[] items = new ItemStack[2];
        public FluidStack fluid;

        public CentrifugeRenderState() {
                for (int i = 0; i < 2; i++) {
                        items[i] = ItemStack.EMPTY;
                        renders[i] = new ItemStackRenderState();
                }
        }

}
