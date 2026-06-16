package com.synergy.vintagetech;

import com.synergy.vintagetech.api.blockfactory.transmission.TransmissionRenderer;
import com.synergy.vintagetech.client.particles.fan.AirFlowParticleProvider;
import com.synergy.vintagetech.init.builder.fan.FanRenderer;
import com.synergy.vintagetech.init.types.zBlockEntities;
import com.synergy.vintagetech.init.types.zParticles;

import net.minecraft.world.item.crafting.RecipeMap;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
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
                zBlockEntities.ENGINE.get(),
                TransmissionRenderer::new);

        event.registerBlockEntityRenderer(
                zBlockEntities.STEAM_ENGINE.get(),
                TransmissionRenderer::new);

        event.registerBlockEntityRenderer(
                zBlockEntities.FAN.get(),
                FanRenderer::new);

    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(
                zParticles.FAN_AIR_FLOW.get(),
                AirFlowParticleProvider::new);
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
