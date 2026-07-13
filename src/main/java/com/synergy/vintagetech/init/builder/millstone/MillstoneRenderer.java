package com.synergy.vintagetech.init.builder.millstone;

import com.synergy.vintagetech.api.blockfactory.transmission.TransmissionRenderer;
import com.synergy.vintagetech.init.types.zBlocks;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.Block;

public class MillstoneRenderer extends TransmissionRenderer<MillstoneBE> {

    public MillstoneRenderer(BlockEntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public Block getBlockRendered(MillstoneBE be) {
        return zBlocks.RENDER_MILLSTONE.get();
    }

}