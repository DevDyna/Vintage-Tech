package com.synergy.vintagetech.init.builder.centrifuge;

import com.synergy.vintagetech.api.blockfactory.transmission.TransmissionRenderer;
import com.synergy.vintagetech.init.types.zBlocks;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.Block;

public class CentrifugeRenderer extends TransmissionRenderer<CentrifugeBE> {

    public CentrifugeRenderer(BlockEntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public Block getBlockRendered() {
        return zBlocks.RENDER_CENTRIFUGE.get();
    }

}