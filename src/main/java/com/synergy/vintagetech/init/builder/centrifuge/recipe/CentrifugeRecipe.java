package com.synergy.vintagetech.init.builder.centrifuge.recipe;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.List;
import com.devdyna.cakesticklib.api.recipe.recipeType.BaseRecipeType;
import com.devdyna.cakesticklib.api.utils.x;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.synergy.vintagetech.api.recipeinput.CentrifugeInput;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zRecipeTypes;

import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.fluids.FluidStackTemplate;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class CentrifugeRecipe extends BaseRecipeType<CentrifugeInput> {

    private final SizedFluidIngredient input_fluid;
    private final SizedIngredient catalyst;
    private final int ticks;
    private final FluidStackTemplate output_fluid;

    public CentrifugeRecipe(SizedFluidIngredient input_fluid, SizedIngredient catalyst,
            int ticks, FluidStackTemplate output_fluid) {
        this.input_fluid = input_fluid;
        this.catalyst = catalyst;
        this.ticks = ticks;
        this.output_fluid = output_fluid;
    }

    public static CentrifugeRecipe of(SizedFluidIngredient input_fluid, SizedIngredient catalyst,
            int ticks, FluidStackTemplate output_fluid) {
        return new CentrifugeRecipe(input_fluid, catalyst, ticks, output_fluid);
    }

    public boolean matches(CentrifugeInput r, Level l) {
        return this.input_fluid.test(r.fluid()) && this.catalyst.test(r.catalyst());
    }

    @Override
    public ItemStack assemble(CentrifugeInput r) {
        return x.item(this.output_fluid.create().getFluid().getBucket()).copy();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(this.catalyst.ingredient()));
    }

    public SizedFluidIngredient getInputFluid() {
        return input_fluid;
    }

    public int getTicks() {
        return ticks;
    }

    public SizedIngredient getCatalyst() {
        return catalyst;
    }

    public FluidStackTemplate getOutputFluid() {
        return output_fluid;
    }

    @Override
    public RecipeType<? extends Recipe<CentrifugeInput>> getType() {
        return zRecipeTypes.CENTRIFUGE.getType();
    }

    @Override
    public RecipeSerializer<? extends Recipe<CentrifugeInput>> getSerializer() {
        return zRecipeTypes.CENTRIFUGE.getSerializer();
    }

    @Override
    public String group() {
        return MODULE_ID;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.CENTRIFUGE.get().asItem();
    }

    public static final RecipeSerializer<CentrifugeRecipe> serializer() {
        return new RecipeSerializer<>(CODEC, STREAM_CODEC);
    }

    public static final MapCodec<CentrifugeRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            SizedFluidIngredient.CODEC.fieldOf("input_fluid").forGetter(CentrifugeRecipe::getInputFluid),
            SizedIngredient.NESTED_CODEC.fieldOf("catalyst").forGetter(CentrifugeRecipe::getCatalyst),
            Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(CentrifugeRecipe::getTicks),
            FluidStackTemplate.CODEC.fieldOf("output_fluid").forGetter(CentrifugeRecipe::getOutputFluid))
            .apply(inst, CentrifugeRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, CentrifugeRecipe> STREAM_CODEC = StreamCodec
            .composite(
                    SizedFluidIngredient.STREAM_CODEC, CentrifugeRecipe::getInputFluid,
                    SizedIngredient.STREAM_CODEC, CentrifugeRecipe::getCatalyst,
                    ByteBufCodecs.INT, CentrifugeRecipe::getTicks,
                    FluidStackTemplate.STREAM_CODEC, CentrifugeRecipe::getOutputFluid,
                    CentrifugeRecipe::new);
}