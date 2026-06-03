package com.synergy.vintagetech.api.blockfactory.transmission;

import java.util.Map;

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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

public class TransmissionRenderer<BE extends TransmissionBE>
        implements BlockEntityRenderer<BE, TransmissionRenderState> {

    protected final BlockModelResolver resolver;

    public TransmissionRenderer(BlockEntityRendererProvider.Context ctx) {
        this.resolver = ctx.blockModelResolver();
    }

    @Override
    public TransmissionRenderState createRenderState() {
        return new TransmissionRenderState();
    }

    public Block getBlockRendered() {
        return zBlocks.RENDER_HALF_AXLE.get();
    }

    @Override
    public void extractRenderState(BE be, TransmissionRenderState state, float partialTicks,
            Vec3 cameraPosition,
            ModelFeatureRenderer.CrumblingOverlay overlay) {

        BlockEntityRenderer.super.extractRenderState(be, state, partialTicks, cameraPosition, overlay);

        resolver.update(state.block, getBlockRendered().defaultBlockState(), BlockDisplayContext.create());

        state.rotation = be.getRotation(partialTicks);
        state.dirs = be.getBlockState().getBlock() instanceof AxleHandler axle
                ? axle.getAxis(be.getBlockState())
                : Map.of();
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
                break;

            case DOWN:
                if (inverted)
                    stack.mulPose(Axis.YP.rotationDegrees(rotation));
                else
                    stack.mulPose(Axis.YN.rotationDegrees(rotation));
                stack.mulPose(Axis.ZP.rotationDegrees(180));
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

    @Override
    public void submit(TransmissionRenderState state, PoseStack stack, SubmitNodeCollector collector,
            CameraRenderState camera) {

        if (state.dirs.isEmpty())
            return;

        for (Direction dir : state.dirs.keySet()) {

            stack.pushPose();
            stack.translate(0.5, 0.5, 0.5);

            rotate(dir, state.dirs.get(dir), state.rotation, stack);

            stack.translate(-0.5, -0.5, -0.5);

            state.block.submit(stack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);

            stack.popPose();

        }
    }
}