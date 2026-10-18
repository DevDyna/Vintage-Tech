package com.synergy.vintagetech.init.builder.drying_rack;

import com.devdyna.cakesticklib.api.client.utils.SimpleItemRender;
import com.mojang.blaze3d.vertex.PoseStack;
import com.synergy.vintagetech.api.RenderStates.DryingRackState;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;

public class DryingRackRender<BE extends DryingRackBE>
        implements BlockEntityRenderer<BE, DryingRackState> {

    protected final ItemModelResolver resolver;

    public DryingRackRender(BlockEntityRendererProvider.Context ctx) {
        this.resolver = ctx.itemModelResolver();
    }

    @Override
    public DryingRackState createRenderState() {
        return new DryingRackState();
    }

    @Override
    public void extractRenderState(BE be, DryingRackState state, float partialTicks,
            Vec3 cameraPosition,
            ModelFeatureRenderer.CrumblingOverlay overlay) {

        BlockEntityRenderState.extractBase(be, state, overlay);

        var storage = ((DryingRackBE) be).getItemStorage();

        var item = storage.getResource(0).toStack(storage.getAmountAsInt(0));

        state.item = item;

        state.dir = be.getBlockState().getValue(DryingRackBlock.FACING);

        resolver.updateForTopItem(state.render, item, ItemDisplayContext.GROUND,
                be.getLevel(), null, 0);

    }

    @Override
    public void submit(DryingRackState state, PoseStack stack,
            SubmitNodeCollector collector,
            CameraRenderState camera) {

        var item = state.item;

        if (!item.isEmpty())
            SimpleItemRender.of()
                    .rotateYN(state.dir.toYRot())
                    .move(0.5, -0.425, 0.5)
                    .scale(1.75f, 1.75f, 1.75f)
                    .build(state, state.render, stack, collector);

    }

}