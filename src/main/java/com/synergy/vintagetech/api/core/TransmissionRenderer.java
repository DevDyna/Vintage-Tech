package com.synergy.vintagetech.api.core;

import com.synergy.vintagetech.api.factories.handlers.TransmissionHandler;
import com.synergy.vintagetech.api.factories.motion.BaseMotionRenderer;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.world.level.block.entity.BlockEntity;

public class TransmissionRenderer<BE extends BlockEntity & TransmissionHandler> extends BaseMotionRenderer<BE, TransmissionRenderState> {

    public TransmissionRenderer(Context ctx) {
        super(ctx);
    }

    @Override
    public TransmissionRenderState createRenderState() {
        return new TransmissionRenderState();
    }

}
