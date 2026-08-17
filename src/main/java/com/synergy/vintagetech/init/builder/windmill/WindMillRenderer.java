package com.synergy.vintagetech.init.builder.windmill;

import com.synergy.vintagetech.api.factories.transmission.TransmissionRenderer;
import com.synergy.vintagetech.init.types.zBlocks;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.Block;

public class WindMillRenderer extends TransmissionRenderer<WindMillBE> {

    public WindMillRenderer(BlockEntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public Block getBlockRendered(WindMillBE be) {
        return zBlocks.RENDER_WINDMILL.get();
    }

}