package com.synergy.vintagetech.init.builder.basket;

import java.util.Random;

import com.devdyna.cakesticklib.api.client.utils.SimpleItemRender;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer.CrumblingOverlay;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;

public class BasketRenderer<BE extends BasketBE> implements BlockEntityRenderer<BE, BasketRenderState> {

    protected final ItemModelResolver itemModels;

    public BasketRenderer(BlockEntityRendererProvider.Context ctx) {
        this.itemModels = ctx.itemModelResolver();
    }

    @Override
    public BasketRenderState createRenderState() {
        return new BasketRenderState();
    }

    @Override
    public void extractRenderState(BE be, BasketRenderState state, float partialTicks, Vec3 cameraPosition,
            CrumblingOverlay overlay) {

        BlockEntityRenderState.extractBase(be, state, overlay);

        var storage = be.getItemStorage();

        if (storage != null)
            for (var i = 0; i < BasketBE.INVENTORY_SLOTS; i++) {
                var item = storage.getResource(i).toStack(storage.getAmountAsInt(i));
                state.items[i] = item;
                itemModels.updateForTopItem(state.renders[i], item, ItemDisplayContext.GROUND, be.getLevel(), null, 0);
            }

        state.dir = be.getBlockState().getValue(BasketBlock.FACING);

    }






@Override
public void submit(
        BasketRenderState state,
        PoseStack stack,
        SubmitNodeCollector collector,
        CameraRenderState camera) {

    // Interior bounds, accounting for the basket walls.
    final double minX = 0.1875 + 0.07;
    final double maxX = 0.8125 - 0.07;
    final double minZ = 0.1875 + 0.07;
    final double maxZ = 0.8125 - 0.07;

    // Keep rendered items below the top edge.
    final double minY = 0.27;
    final double maxY = 0.78;
    final double layerHeight = 0.12;

    for (int i = 0; i < BasketBE.INVENTORY_SLOTS; i++) {
        if (state.items[i].isEmpty())
            continue;

        Random random = new Random(state.blockPos.asLong() + i * 31L);

        int seed = random.nextInt(360);

        // Pick a position within the usable interior.
        double baseX = minX + random.nextDouble() * (maxX - minX);
        double baseZ = minZ + random.nextDouble() * (maxZ - minZ);

        double dx = baseX - 0.5;
        double dz = baseZ - 0.5;

        double x;
        double z;

        // Rotate the position to match the basket facing.
        switch (state.dir) {
            case NORTH -> {
                x = 0.5 + dx;
                z = 0.5 + dz;
            }
            case EAST -> {
                x = 0.5 - dz;
                z = 0.5 + dx;
            }
            case SOUTH -> {
                x = 0.5 - dx;
                z = 0.5 - dz;
            }
            case WEST -> {
                x = 0.5 + dz;
                z = 0.5 - dx;
            }
            default -> {
                x = baseX;
                z = baseZ;
            }
        }

        // Spread inventory slots across vertical layers.
        int layer = i / 6;
        double y = Math.min(minY + layer * layerHeight, maxY);

        SimpleItemRender.of()
                .move(x, y, z)
                .rotateXP(90)
                .rotateYN(seed)
                .scale(0.30f, 0.30f, 0.30f)
                .build(state, state.renders[i], stack, collector);
    }
}




}
