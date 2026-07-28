package com.synergy.vintagetech.init.builder.mechanical_farmland.recipe;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.List;
import com.devdyna.cakesticklib.api.recipe.recipeInput.FluidInput;
import com.devdyna.cakesticklib.api.recipe.recipeType.BaseRecipeType;
import com.devdyna.cakesticklib.api.utils.x;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zRecipeTypes;

import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class FarmlandFuelsRecipe extends BaseRecipeType<FluidInput.withNumber> {

    private final SizedFluidIngredient fluid;

    public FarmlandFuelsRecipe(SizedFluidIngredient fluid) {
        this.fluid = fluid;
    }

    public static FarmlandFuelsRecipe of(SizedFluidIngredient fluid) {
        return new FarmlandFuelsRecipe(fluid);
    }

    public boolean matches(FluidInput.withNumber r, Level l) {
        return this.fluid.test(r.fluid());
    }

    @Override
    public ItemStack assemble(FluidInput.withNumber r) {
        return x.item(x.getFluidStacksFromIngredient(fluid).getFirst().getFluid().getBucket());
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List
                .of(x.itemIngredient(x.getFluidStacksFromIngredient(this.fluid).getFirst().getFluid().getBucket())));
    }

    public SizedFluidIngredient getFluid() {
        return fluid;
    }

    @Override
    public RecipeType<? extends Recipe<FluidInput.withNumber>> getType() {
        return zRecipeTypes.FARMLAND_FUELS.getType();
    }

    @Override
    public RecipeSerializer<? extends Recipe<FluidInput.withNumber>> getSerializer() {
        return zRecipeTypes.FARMLAND_FUELS.getSerializer();
    }

    @Override
    public String group() {
        return MODULE_ID;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.MECHANICAL_FARMLAND.get().asItem();
    }

    public static final RecipeSerializer<FarmlandFuelsRecipe> serializer() {
        return new RecipeSerializer<>(CODEC, STREAM_CODEC);
    }

    public static final MapCodec<FarmlandFuelsRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            SizedFluidIngredient.CODEC.fieldOf("fluid").forGetter(FarmlandFuelsRecipe::getFluid))
            .apply(inst, FarmlandFuelsRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, FarmlandFuelsRecipe> STREAM_CODEC = StreamCodec
            .composite(
                    SizedFluidIngredient.STREAM_CODEC, FarmlandFuelsRecipe::getFluid,
                    FarmlandFuelsRecipe::new);

}