package com.synergy.vintagetech.init.builder.axle;

import java.util.List;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction.Axis;

public class KineticRenderState extends BlockEntityRenderState {

    public BlockModelRenderState block = new BlockModelRenderState();
    public float rotation;
    public List<Axis> axis;
}