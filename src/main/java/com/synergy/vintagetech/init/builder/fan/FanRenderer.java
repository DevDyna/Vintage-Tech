package com.synergy.vintagetech.init.builder.fan;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.synergy.vintagetech.api.blockfactory.transmission.TransmissionRenderer;
import com.synergy.vintagetech.init.types.zBlocks;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;

public class FanRenderer extends TransmissionRenderer<FanBE> {

    public FanRenderer(BlockEntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public Block getBlockRendered() {
        return zBlocks.RENDER_FAN_BLADE.get();
    }

    public void rotate(Direction dir, boolean inverted, float rotation, PoseStack stack) {
        switch (dir) {
            case EAST:
                if (inverted)
                    stack.mulPose(Axis.XP.rotationDegrees(rotation));
                else
                    stack.mulPose(Axis.XN.rotationDegrees(rotation));
                stack.mulPose(Axis.ZP.rotationDegrees(90));
                break;

            case WEST:
                if (inverted)
                    stack.mulPose(Axis.XP.rotationDegrees(rotation));
                else
                    stack.mulPose(Axis.XN.rotationDegrees(rotation));
                stack.mulPose(Axis.ZP.rotationDegrees(180));
                stack.mulPose(Axis.ZP.rotationDegrees(90));
                break;

            case UP:
                if (inverted)
                    stack.mulPose(Axis.YP.rotationDegrees(rotation));
                else
                    stack.mulPose(Axis.YN.rotationDegrees(rotation));
                stack.mulPose(Axis.XP.rotationDegrees(180));
                break;

            case DOWN:
                if (inverted)
                    stack.mulPose(Axis.YP.rotationDegrees(rotation));
                else
                    stack.mulPose(Axis.YN.rotationDegrees(rotation));
                stack.mulPose(Axis.ZP.rotationDegrees(180));
                stack.mulPose(Axis.XP.rotationDegrees(180));
                break;

            case NORTH:
                if (inverted)
                    stack.mulPose(Axis.ZP.rotationDegrees(rotation));
                else
                    stack.mulPose(Axis.ZN.rotationDegrees(rotation));
                stack.mulPose(Axis.XP.rotationDegrees(90));
                break;

            case SOUTH:
                if (inverted)
                    stack.mulPose(Axis.ZP.rotationDegrees(rotation));
                else
                    stack.mulPose(Axis.ZN.rotationDegrees(rotation));
                stack.mulPose(Axis.XP.rotationDegrees(90));
                stack.mulPose(Axis.ZP.rotationDegrees(180));
                break;
        }
    }

}