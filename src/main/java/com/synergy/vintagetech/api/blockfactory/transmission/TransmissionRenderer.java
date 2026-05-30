package com.synergy.vintagetech.api.blockfactory.transmission;

import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.synergy.vintagetech.api.AxleHandler;
import com.synergy.vintagetech.init.types.zBlocks;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public class TransmissionRenderer implements BlockEntityRenderer<TransmissionBE, TransmissionRenderState> {

    private final BlockModelResolver resolver;

    public TransmissionRenderer(BlockEntityRendererProvider.Context ctx) {
        this.resolver = ctx.blockModelResolver();
    }

    @Override
    public TransmissionRenderState createRenderState() {
        return new TransmissionRenderState();
    }

    @Override
    public void extractRenderState(TransmissionBE be, TransmissionRenderState state, float partialTicks, Vec3 cameraPosition,
            ModelFeatureRenderer.CrumblingOverlay overlay) {

        BlockEntityRenderer.super.extractRenderState(be, state, partialTicks, cameraPosition, overlay);

        resolver.update(state.block, zBlocks.HALF_RENDER.get().defaultBlockState(), BlockDisplayContext.create());

        state.rotation = be.getRotation(partialTicks);
        state.dirs = be.getBlockState().getBlock() instanceof AxleHandler axle
                ? axle.getRotationAxis(be.getBlockState())
                : List.of();
    }

    @Override
    public void submit(TransmissionRenderState state, PoseStack stack, SubmitNodeCollector collector,
            CameraRenderState camera) {

        if (state.dirs.isEmpty())
            return;

        for (Direction dir : state.dirs) {

            stack.pushPose();
            stack.translate(0.5, 0.5, 0.5);

            switch (dir) {
                case EAST:
                    stack.mulPose(Axis.XP.rotationDegrees(state.rotation));
                    stack.mulPose(Axis.ZP.rotationDegrees(90));
                    break;
                    
                case WEST:
                    stack.mulPose(Axis.XP.rotationDegrees(state.rotation));
                    stack.mulPose(Axis.ZP.rotationDegrees(180));
                    stack.mulPose(Axis.ZP.rotationDegrees(90));
                    break;

                case UP:
                    stack.mulPose(Axis.YP.rotationDegrees(state.rotation));
                    break;

                case DOWN:
                    stack.mulPose(Axis.YP.rotationDegrees(state.rotation));
                    stack.mulPose(Axis.ZP.rotationDegrees(180));
                    break;

                case NORTH:
                    stack.mulPose(Axis.ZP.rotationDegrees(state.rotation));
                    stack.mulPose(Axis.XP.rotationDegrees(90));
                    break;

                case SOUTH:
                    stack.mulPose(Axis.ZP.rotationDegrees(state.rotation));
                    stack.mulPose(Axis.XP.rotationDegrees(90));
                    stack.mulPose(Axis.ZP.rotationDegrees(180));
                    break;
            }

            stack.translate(-0.5, -0.5, -0.5);

            state.block.submit(stack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);

            stack.popPose();

        }
    }
}