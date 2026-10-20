package com.synergy.vintagetech.init.builder.centrifuge;

import com.synergy.vintagetech.api.factories.motion.BaseMotionRenderer;
import com.synergy.vintagetech.api.factories.motion.MotionRenderState;
import com.synergy.vintagetech.init.builder.centrifuge.CentrifugeRenderer.CentrifugeState;
import com.synergy.vintagetech.init.types.zBlocks;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.Block;

public class CentrifugeRenderer extends BaseMotionRenderer<CentrifugeBE,CentrifugeState> {

    public CentrifugeRenderer(BlockEntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public Block getBlockRendered(CentrifugeBE be) {
        return zBlocks.RENDER_CENTRIFUGE.get();
    }

     @Override
    public CentrifugeState createRenderState() {
        return new CentrifugeState();
    }

    public static class CentrifugeState extends MotionRenderState {

    }

   

}