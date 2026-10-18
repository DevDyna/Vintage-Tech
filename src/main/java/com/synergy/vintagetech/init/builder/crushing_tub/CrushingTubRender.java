package com.synergy.vintagetech.init.builder.crushing_tub;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.devdyna.cakesticklib.api.client.utils.FluidRenderHelper;
import com.devdyna.cakesticklib.api.client.utils.SimpleItemRender;
import com.mojang.blaze3d.vertex.PoseStack;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;

public class CrushingTubRender<BE extends CrushingTubBE>
        implements BlockEntityRenderer<BE, CrushingTubRenderState> {

    protected final ItemModelResolver resolver;
    private List<Integer> seeds = new ArrayList<Integer>();

    public CrushingTubRender(BlockEntityRendererProvider.Context ctx) {
        this.resolver = ctx.itemModelResolver();

        for (int i = 0; i < 16; i++)
            seeds.add(new Random().nextInt(360));
    }

    @Override
    public CrushingTubRenderState createRenderState() {
        return new CrushingTubRenderState();
    }

    @Override
    public void extractRenderState(BE be, CrushingTubRenderState state, float partialTicks,
            Vec3 cameraPosition,
            ModelFeatureRenderer.CrumblingOverlay overlay) {

        BlockEntityRenderState.extractBase(be, state, overlay);

        var storage = ((CrushingTubBE) be).getItemStorage();

        var item = storage.getResource(0).toStack(storage.getAmountAsInt(0));

        state.item = item;

        resolver.updateForTopItem(state.render, item, ItemDisplayContext.GROUND,
                be.getLevel(), null, 0);

        var tank = ((CrushingTubBE) be).getFluidStorage();

        state.fluid = tank.getResource(0).toStack(tank.getAmountAsInt(0));
    }

    @Override
    public void submit(CrushingTubRenderState state, PoseStack stack,
            SubmitNodeCollector collector,
            CameraRenderState camera) {

        var item = state.item;
        var fluid = state.fluid;

        var time = Minecraft.getInstance().level.getGameTime();

        if (!item.isEmpty()) {

            if (item.is(zTags.Items.UNSUSPICIOUS_ITEMS))
                SimpleItemRender.of()
                        .move(0.5,
                                0.5 + (0.15 * (1 - Math.cos(Math.PI * Math.abs(time - 180) / 180.0)) / 2),
                                0.5)
                        .rotateYP(time)
                        .scale(1.5f, 1.5F, 1.5F)
                        .build(state, state.render, stack, collector);
            else
                for (int i = 0; i < Math.min((item.getCount() / 4) + 1, 16); i++) {

                    SimpleItemRender.of()
                            .rotateYN(seeds.get(i) * state.blockPos.asLong())
                            .rotateXP(90)
                            .move(0.5, 0.15 + (i * 0.025f), 0.5)
                            .scale(0.75f, 0.75f, 0.75f)
                            .build(state, state.render, stack, collector);
                }
        }

        if (fluid.amount() > 0)
            FluidRenderHelper.of()
                    .textureAndColor(fluid)
                    .offset(0f, 0.15f, 0f)
                    .amount((fluid.amount() / 1000 * 0.15f) / 8)
                    .build(stack, collector, LightCoordsUtil.FULL_BRIGHT);

    }

}