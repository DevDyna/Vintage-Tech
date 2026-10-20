package com.synergy.vintagetech.init.builder.centrifuge;

import java.util.Random;

import com.devdyna.cakesticklib.api.client.utils.FluidRenderHelper;
import com.devdyna.cakesticklib.api.client.utils.SimpleItemRender;
import com.mojang.blaze3d.vertex.PoseStack;
import com.synergy.vintagetech.api.factories.motion.BaseMotionRenderer;
import com.synergy.vintagetech.init.types.zBlocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer.CrumblingOverlay;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

public class CentrifugeRenderer extends BaseMotionRenderer<CentrifugeBE, CentrifugeRenderState> {

    protected final ItemModelResolver itemModels;

    public CentrifugeRenderer(BlockEntityRendererProvider.Context ctx) {
        super(ctx);
        itemModels = ctx.itemModelResolver();
    }

    @Override
    public Block getBlockRendered(CentrifugeBE be) {
        return zBlocks.RENDER_CENTRIFUGE.get();
    }

    @Override
    public CentrifugeRenderState createRenderState() {
        return new CentrifugeRenderState();
    }

    @Override
    public void extractRenderState(CentrifugeBE be, CentrifugeRenderState state, float partialTicks,
            Vec3 cameraPosition,
            CrumblingOverlay overlay) {
        super.extractRenderState(be, state, partialTicks, cameraPosition, overlay);

        var storage = be.getItemStorage();

        if (storage != null)
            for (var i = 0; i < 2; i++) {
                var item = storage.getResource(i).toStack(storage.getAmountAsInt(i));
                state.items[i] = item;
                itemModels.updateForTopItem(state.renders[i], item, ItemDisplayContext.GROUND, be.getLevel(), null, 0);
            }

        var tank = be.getFluidStorage();

        if (tank != null && tank.getResource(0) != null)
            state.fluid = tank.getResource(0).toStack(tank.getAmountAsInt(0));

    }

    @Override
    public void submit(CentrifugeRenderState state, PoseStack stack, SubmitNodeCollector collector,
            CameraRenderState camera) {
        super.submit(state, stack, collector, camera);

        var time = Minecraft.getInstance().level.getGameTime();
        var fluid = state.fluid;

        for (var i = 0; i < 2; i++) {

            if (state.items[i].isEmpty())
                continue;

            for (var s = 0; s < Math.max(1, state.items[i].count() / 16); s++) {

                var random = new Random(state.blockPos.asLong() + (s + i) * 31L);
                var seed = random.nextInt(360);

                // required to be a variable
                var angle = random.nextDouble() * Math.PI * 2;
                var radius = Math.sqrt(0.0196 + random.nextDouble() * 0.0704);

                var x = 0.5 + Math.cos(angle) * radius;
                var z = 0.5 + Math.sin(angle) * radius;

                var y = 0.16f;

                if (!fluid.isEmpty())
                    y += (float) ((Math.sin(time * 0.025 + seed * 0.01) + 1.0)
                            * 0.175f * (fluid.amount() / 8000f));

                SimpleItemRender.of()
                        .move(x, y, z)
                        .rotateXP(90)
                        .rotateYN(seed)
                        .scale(0.55f, 0.55f, 0.55f)
                        .build(state, state.renders[i], stack, collector);
            }
        }

        if (!fluid.isEmpty())
            FluidRenderHelper.of()
                    .textureAndColor(fluid)
                    .offset(0f, 0.15f, 0f)
                    .modify((p) -> {
                        p.scale(0.95f, 1, 0.95f);
                        p.translate(0.025f, 0.15f, 0.025f);
                    })
                    .amount(fluid.amount() * 0.15f / 3000f)
                    .build(stack, collector, state.lightCoords);

    }

}