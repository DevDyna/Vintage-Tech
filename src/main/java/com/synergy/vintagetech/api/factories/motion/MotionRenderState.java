package com.synergy.vintagetech.api.factories.motion;

import java.util.Map;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;

public class MotionRenderState extends BlockEntityRenderState {
    public BlockModelRenderState block = new BlockModelRenderState();
    public float rotation;
    public Map<Direction, Boolean> dirs;
}
