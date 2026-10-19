package com.synergy.vintagetech.init.builder.evaporation_basin;

import java.util.Random;

import com.devdyna.cakesticklib.api.client.utils.FluidRenderHelper;
import com.devdyna.cakesticklib.api.client.utils.SimpleItemRender;
import com.mojang.blaze3d.vertex.PoseStack;
import com.synergy.vintagetech.api.RenderStates;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;

public class EvaporationBasinRender<BE extends EvaporationBasinBE>
        implements BlockEntityRenderer<BE, RenderStates.SimpleBasin> {

    protected final ItemModelResolver resolver;
    private int seed;

    public EvaporationBasinRender(BlockEntityRendererProvider.Context ctx) {
        this.resolver = ctx.itemModelResolver();

        this.seed = new Random().nextInt(360);
    }

    @Override
    public RenderStates.SimpleBasin createRenderState() {
        return new RenderStates.SimpleBasin();
    }

    @Override
    public void extractRenderState(BE be, RenderStates.SimpleBasin state, float partialTicks,
            Vec3 cameraPosition,
            ModelFeatureRenderer.CrumblingOverlay overlay) {

        BlockEntityRenderState.extractBase(be, state, overlay);

        var storage = be.getItemStorage();

        if (storage != null && storage.getResource(0) != null) {
            var item = storage.getResource(0).toStack(storage.getAmountAsInt(0));

            state.item = item;

            resolver.updateForTopItem(state.render, item, ItemDisplayContext.GROUND,
                    be.getLevel(), null, 0);
        }

        var tank = be.getFluidStorage();

        if (tank != null && tank.getResource(0) != null)
            state.fluid = tank.getResource(0).toStack(tank.getAmountAsInt(0));
    }

    @Override
    public void submit(RenderStates.SimpleBasin state, PoseStack stack,
            SubmitNodeCollector collector,
            CameraRenderState camera) {

        var item = state.item;
        var fluid = state.fluid;

        if (!item.isEmpty())
            SimpleItemRender.of()
                    .rotateYN(seed * state.blockPos.asLong())
                    .rotateXP(90)
                    .move(0.5, 0.15, 0.5)
                    .scale(0.75f, 0.75f, 0.75f)
                    .build(state, state.render, stack, collector);

        if (fluid.amount() > 0)
            FluidRenderHelper.of()
                    .scaleMultiplier(0.9f)
                    .offset(0.1f, 0.15f, 0.1f)
                    .textureAndColor(fluid)
                    .amount(fluid.amount() * 0.075f / 16000)
                    .build(stack, collector, seed);

    }

}