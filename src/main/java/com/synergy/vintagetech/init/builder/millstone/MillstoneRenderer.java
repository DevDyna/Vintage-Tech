package com.synergy.vintagetech.init.builder.millstone;

import com.synergy.vintagetech.api.factories.motion.BaseMotionRenderer;
import com.synergy.vintagetech.init.builder.millstone.MillstoneRenderer.MillstoneState;
import com.synergy.vintagetech.init.types.zBlocks;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.Block;

public class MillstoneRenderer extends BaseMotionRenderer<MillstoneBE, MillstoneState> {

    public MillstoneRenderer(BlockEntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public Block getBlockRendered(MillstoneBE be) {
        return zBlocks.RENDER_MILLSTONE.get();
    }

    @Override
    public MillstoneState createRenderState() {
        return new MillstoneState();
    }

    public static class MillstoneState extends BaseMotionRenderer.Simple {

    }

}