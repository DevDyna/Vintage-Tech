package com.synergy.vintagetech;

import java.util.List;

import com.devdyna.cakesticklib.api.FluidRenderUtils;
import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.api.blockfactory.transmission.TransmissionRenderer;
import com.synergy.vintagetech.client.particles.fan.AirFlowParticleProvider;
import com.synergy.vintagetech.init.builder.centrifuge.CentrifugeRenderer;
import com.synergy.vintagetech.init.builder.fan.FanRenderer;
import com.synergy.vintagetech.init.builder.millstone.MillstoneRenderer;
import com.synergy.vintagetech.init.types.zBlockEntities;
import com.synergy.vintagetech.init.types.zFluids;
import com.synergy.vintagetech.init.types.zParticles;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeMap;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.client.event.RegisterFluidModelsEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.fluid.FluidTintSource;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = Main.MODULE_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Main.MODULE_ID, value = Dist.CLIENT)
public class Client {

    public Client(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    public static void render(EntityRenderersEvent.RegisterRenderers event) {

        event.registerBlockEntityRenderer(
                zBlockEntities.TRANSMISSION.get(),
                TransmissionRenderer::new);

        event.registerBlockEntityRenderer(
                zBlockEntities.CREATIVE_ENGINE.get(),
                TransmissionRenderer::new);

        event.registerBlockEntityRenderer(
                zBlockEntities.STEAM_ENGINE.get(),
                TransmissionRenderer::new);

        event.registerBlockEntityRenderer(
                zBlockEntities.ELECTRIC_MOTOR.get(),
                TransmissionRenderer::new);

        event.registerBlockEntityRenderer(
                zBlockEntities.DYNAMO.get(),
                TransmissionRenderer::new);

        event.registerBlockEntityRenderer(
                zBlockEntities.FAN.get(),
                FanRenderer::new);

        event.registerBlockEntityRenderer(
                zBlockEntities.MILLSTONE.get(),
                MillstoneRenderer::new);

        event.registerBlockEntityRenderer(
                zBlockEntities.MECHANICAL_FARMLAND.get(),
                TransmissionRenderer::new);

        event.registerBlockEntityRenderer(
                zBlockEntities.CENTRIFUGE.get(),
                CentrifugeRenderer::new);

    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(
                zParticles.FAN_AIR_FLOW.get(),
                AirFlowParticleProvider::new);
    }

    @SubscribeEvent
    public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {

        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public Identifier getRenderOverlayTexture(Minecraft mc) {
                return x.parse("textures/misc/underwater.png");
            }
        }, zFluids.SALT_SOLUTION.getType(), zFluids.SOY_WHEY.getType());

    }

    @SubscribeEvent
    public static void onRegisterFluidModels(RegisterFluidModelsEvent event) {

        List.of(
                zFluids.LATEX,
                zFluids.RESIN,
                zFluids.SALT_SOLUTION,
                zFluids.SAP,
                zFluids.OIL,
                zFluids.SOY_WHEY).forEach(
                        f -> event.register(
                                FluidRenderUtils.createWaterModel(new FluidTintSource() {

                                    @Override
                                    public int color(FluidState state) {
                                        return f.getColor();
                                    }

                                }),
                                f.getSource(),
                                f.getFlowing()));

    }

    // Recipe collector client-side

    private static RecipeMap recipeCollector = RecipeMap.EMPTY;

    @SubscribeEvent
    public static void onRecipesSynced(RecipesReceivedEvent event) {
        if (ModList.get().isLoaded("jei"))
            recipeCollector = event.getRecipeMap();
    }

    @SubscribeEvent
    public static void onClientLogout(ClientPlayerNetworkEvent.LoggingOut event) {
        recipeCollector = RecipeMap.EMPTY;
    }

    public static RecipeMap getRecipeCollector() {
        return recipeCollector;
    }

}
