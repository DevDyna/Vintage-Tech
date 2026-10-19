
package com.synergy.vintagetech.init.builder.crucible;

import java.util.Random;

import com.devdyna.cakesticklib.api.client.utils.FluidRenderHelper;
import com.devdyna.cakesticklib.api.client.utils.SimpleItemRender;
import com.mojang.blaze3d.vertex.PoseStack;
import com.synergy.vintagetech.api.RenderStates.Basin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer.CrumblingOverlay;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;

public class CrucibleRender<BE extends CrucibleBE> implements BlockEntityRenderer<BE, Basin> {

    protected final ItemModelResolver resolver;

    public CrucibleRender(BlockEntityRendererProvider.Context ctx) {
        this.resolver = ctx.itemModelResolver();
    }

    @Override
    public Basin createRenderState() {
        return new Basin();
    }

    @Override
    public void extractRenderState(BE be, Basin state, float partialTicks, Vec3 cameraPosition,
            CrumblingOverlay overlay) {

        BlockEntityRenderState.extractBase(be, state, overlay);

        var storage = be.getItemStorage();

        if (storage != null)
            for (var i = 0; i < CrucibleBE.ITEM_SLOTS.size(); i++) {
                var item = storage.getResource(i).toStack(storage.getAmountAsInt(i));
                state.items[i] = item;
                resolver.updateForTopItem(state.renders[i], item, ItemDisplayContext.GROUND, be.getLevel(), null, 0);
            }

        var tank = be.getFluidStorage();

        if (tank != null && tank.getResource(0) != null)
            state.fluid = tank.getResource(0).toStack(tank.getAmountAsInt(0));
    }

    @Override
    public void submit(Basin state, PoseStack stack, SubmitNodeCollector collector, CameraRenderState camera) {

        var time = Minecraft.getInstance().level.getGameTime();
        var fluid = state.fluid;

        for (var i = 0; i < CrucibleBE.ITEM_SLOTS.size(); i++) {

            if (state.items[i].isEmpty())
                continue;

            var random = new Random(state.blockPos.asLong() + i * 31L);
            var seed = random.nextInt(360);

            // required to be a variable
            var angle = random.nextDouble() * Math.PI * 2;
            var radius = Math.sqrt(random.nextDouble()) * 0.2;

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
