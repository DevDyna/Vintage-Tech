package com.synergy.vintagetech.api.blockfactory.transmission;

import java.util.List;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;

public class TransmissionRenderState extends BlockEntityRenderState {

    public BlockModelRenderState block = new BlockModelRenderState();
    public float rotation;
    public List<Direction> dirs;
}