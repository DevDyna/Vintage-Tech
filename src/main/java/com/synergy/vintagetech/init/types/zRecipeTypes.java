package com.synergy.vintagetech.init.types;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.synergy.vintagetech.api.RecipeRegister;
import com.synergy.vintagetech.init.builder.centrifuge.recipe.CentrifugeRecipe;
import com.synergy.vintagetech.init.builder.crushing_tub.recipe.CrushingTubRecipe;
import com.synergy.vintagetech.init.builder.drying_rack.recipe.DryingRackRecipe;
import com.synergy.vintagetech.init.builder.evaporation_basin.recipe.EvaporationBasinRecipe;
import com.synergy.vintagetech.init.builder.millstone.recipe.MillstoneRecipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zRecipeTypes {

    public static void register(IEventBus bus) {
        SERIALIZERS.register(bus);
        TYPES.register(bus);
    }

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister
            .create(Registries.RECIPE_SERIALIZER, MODULE_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registries.RECIPE_TYPE,
            MODULE_ID);

    public static final RecipeRegister<CrushingTubRecipe> CRUSHING_TUB = RecipeRegister.of("crushing_tub",
            () -> CrushingTubRecipe.serializer());

    public static final RecipeRegister<EvaporationBasinRecipe> EVAPORATION_BASIN = RecipeRegister.of(
            "evaporation_basin",
            () -> EvaporationBasinRecipe.serializer());

    public static final RecipeRegister<DryingRackRecipe> DRYING_RACK = RecipeRegister.of("drying_rack",
            () -> DryingRackRecipe.serializer());

    public static final RecipeRegister<MillstoneRecipe> MILLSTONE = RecipeRegister.of("millstone",
            () -> MillstoneRecipe.serializer());

    public static final RecipeRegister<CentrifugeRecipe> CENTRIFUGE = RecipeRegister.of("centrifuge",
            () -> CentrifugeRecipe.serializer());

}
