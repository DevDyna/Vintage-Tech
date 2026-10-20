package com.synergy.vintagetech.api.factories.transmission;

import com.synergy.vintagetech.api.factories.motion.BaseMotionRenderer;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;

public class TransmissionRenderer<BE extends TransmissionBE> extends BaseMotionRenderer<BE, TransmissionRenderState> {

    public TransmissionRenderer(Context ctx) {
        super(ctx);
    }

    @Override
    public TransmissionRenderState createRenderState() {
        return new TransmissionRenderState();
    }

}
