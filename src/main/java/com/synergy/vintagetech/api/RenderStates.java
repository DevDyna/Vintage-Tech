package com.synergy.vintagetech.api;

import java.util.Map;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

//TODO maybe useless to be a standard
public class RenderStates {
    public static class SimpleItemContainer extends BlockEntityRenderState {
        public ItemStackRenderState render = new ItemStackRenderState();
        public ItemStack item;
    }

    public static class DryingRackState extends SimpleItemContainer {
        public Direction dir;
    }

    public static class SimpleBasin extends SimpleItemContainer {
        public FluidStack fluid;
    }

    public static class ItemContainer extends BlockEntityRenderState {
        public ItemStackRenderState[] renders = new ItemStackRenderState[] {
                new ItemStackRenderState(), new ItemStackRenderState(), new ItemStackRenderState(),
                new ItemStackRenderState(), new ItemStackRenderState(), new ItemStackRenderState(),
                new ItemStackRenderState(), new ItemStackRenderState(), new ItemStackRenderState() };
        public ItemStack[] items = new ItemStack[] {
                ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,
                ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,
                ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY
        };

    }

    public static class Basin extends ItemContainer {
        public FluidStack fluid;
    }

    public static class TransmissionRenderState extends BlockEntityRenderState {

        public BlockModelRenderState block = new BlockModelRenderState();
        public float rotation;
        public Map<Direction, Boolean> dirs;
    }
}
