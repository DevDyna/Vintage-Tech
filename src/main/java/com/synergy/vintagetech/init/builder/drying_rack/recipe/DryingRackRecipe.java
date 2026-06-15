package com.synergy.vintagetech.init.builder.drying_rack.recipe;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.List;
import com.devdyna.cakesticklib.api.recipe.recipeInput.ItemInput;
import com.devdyna.cakesticklib.api.recipe.recipeType.BaseRecipeType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zRecipeTypes;

import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class DryingRackRecipe extends BaseRecipeType<ItemInput.simple> {

    private final Ingredient input;
    private final int ticks;
    private final ItemStackTemplate output;

    public DryingRackRecipe(Ingredient input,
            int ticks, ItemStackTemplate output) {
        this.input = input;
        this.ticks = ticks;
        this.output = output;
    }

    public static DryingRackRecipe of(Ingredient input, int ticks, ItemStackTemplate output) {
        return new DryingRackRecipe(input, ticks, output);
    }

    public boolean matches(ItemInput.simple r, Level l) {
        return this.input.test(r.item());
    }

    @Override
    public ItemStack assemble(ItemInput.simple r) {
        return this.output.create().copy();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(this.input));
    }

    public Ingredient getInput() {
        return input;
    }

    public int getTicks() {
        return ticks;
    }

    public ItemStackTemplate getOutput() {
        return output;
    }

    @Override
    public RecipeType<? extends Recipe<ItemInput.simple>> getType() {
        return zRecipeTypes.DRYING_RACK.getType();
    }

    @Override
    public RecipeSerializer<? extends Recipe<ItemInput.simple>> getSerializer() {
        return zRecipeTypes.DRYING_RACK.getSerializer();
    }

    @Override
    public String group() {
        return MODULE_ID;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.DRYING_RACK.get().asItem();
    }

    public static final RecipeSerializer<DryingRackRecipe> serializer() {
        return new RecipeSerializer<>(CODEC, STREAM_CODEC);
    }

    public static final MapCodec<DryingRackRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.fieldOf("input").forGetter(DryingRackRecipe::getInput),
            Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(DryingRackRecipe::getTicks),
            ItemStackTemplate.CODEC.fieldOf("output").forGetter(DryingRackRecipe::getOutput))
            .apply(inst, DryingRackRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, DryingRackRecipe> STREAM_CODEC = StreamCodec
            .composite(
                    Ingredient.CONTENTS_STREAM_CODEC, DryingRackRecipe::getInput,
                    ByteBufCodecs.INT, DryingRackRecipe::getTicks,
                    ItemStackTemplate.STREAM_CODEC, DryingRackRecipe::getOutput,
                    DryingRackRecipe::new);
}