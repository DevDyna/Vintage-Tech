package com.synergy.vintagetech.init.builder.axle;

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

public class KineticRenderer implements BlockEntityRenderer<KineticBE, KineticRenderState> {

    private final BlockModelResolver resolver;

    public KineticRenderer(BlockEntityRendererProvider.Context ctx) {
        this.resolver = ctx.blockModelResolver();
    }

    @Override
    public KineticRenderState createRenderState() {
        return new KineticRenderState();
    }

    @Override
    public void extractRenderState(KineticBE be, KineticRenderState state, float partialTicks, Vec3 cameraPosition,
            ModelFeatureRenderer.CrumblingOverlay overlay) {

        BlockEntityRenderer.super.extractRenderState(be, state, partialTicks, cameraPosition, overlay);

        resolver.update(state.block, zBlocks.AXLE.get().defaultBlockState(), BlockDisplayContext.create());

        state.rotation = be.getRotation(partialTicks);
        state.axis = be.getBlockState().getBlock() instanceof AxleHandler axle
                ? axle.getRotationAxis(be.getBlockState())
                : List.of();
    }

    @Override
    public void submit(KineticRenderState state, PoseStack stack, SubmitNodeCollector collector,
            CameraRenderState camera) {

        if (state.axis.isEmpty())
            return;

        for (Direction.Axis axis : state.axis) {

            stack.pushPose();
            stack.translate(0.5, 0.5, 0.5);

            switch (axis) {
                case Direction.Axis.X:
                    stack.mulPose(Axis.XP.rotationDegrees(state.rotation));
                    // stack.mulPose(Axis.ZP.rotationDegrees(90));
                    break;
                case Direction.Axis.Y:
                    stack.mulPose(Axis.YP.rotationDegrees(state.rotation));
                    stack.mulPose(Axis.ZP.rotationDegrees(90));
                    break;
                case Direction.Axis.Z:
                    stack.mulPose(Axis.ZP.rotationDegrees(state.rotation));
                    stack.mulPose(Axis.YP.rotationDegrees(90));
                    break;

            }

            stack.translate(-0.5, -0.5, -0.5);

            state.block.submit(stack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);

            stack.popPose();

        }
    }
}