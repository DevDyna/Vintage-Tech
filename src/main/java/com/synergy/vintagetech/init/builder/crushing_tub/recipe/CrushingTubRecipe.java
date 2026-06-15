package com.synergy.vintagetech.init.builder.crushing_tub.recipe;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.List;
import java.util.Optional;

import com.devdyna.cakesticklib.api.recipe.recipeInput.ItemInput;
import com.devdyna.cakesticklib.api.recipe.recipeOutput.ChanceOutputItem;
import com.devdyna.cakesticklib.api.recipe.recipeType.BaseRecipeType;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zRecipeTypes;

import com.synergy.vintagetech.api.RecipeUtils;

import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStackTemplate;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class CrushingTubRecipe extends BaseRecipeType<ItemInput.simple> {

    private final Ingredient input;
    private final FluidStackTemplate fluid;
    private final ChanceOutputItem output;

    public CrushingTubRecipe(Ingredient input,
            ChanceOutputItem output, FluidStackTemplate fluid) {
        this.input = input;
        this.fluid = fluid;
        this.output = output;
    }

    public static CrushingTubRecipe of(Ingredient input, ChanceOutputItem output, FluidStackTemplate fluid) {
        return new CrushingTubRecipe(input, output, fluid);
    }

    public boolean matches(ItemInput.simple r, Level l) {
        return this.input.test(r.item());
    }

    @Override
    public ItemStack assemble(ItemInput.simple r) {
        return this.output.item().create().copy();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(this.input));
    }

    public Ingredient getInput() {
        return input;
    }

    public ChanceOutputItem getOutput() {
        return output;
    }

    public FluidStackTemplate getFluid() {
        return fluid;
    }

    @Override
    public RecipeType<? extends Recipe<ItemInput.simple>> getType() {
        return zRecipeTypes.CRUSHING_TUB.getType();
    }

    @Override
    public RecipeSerializer<? extends Recipe<ItemInput.simple>> getSerializer() {
        return zRecipeTypes.CRUSHING_TUB.getSerializer();
    }

    @Override
    public String group() {
        return MODULE_ID;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.CRUSHING_TUB.get().asItem();
    }

    public static final RecipeSerializer<CrushingTubRecipe> serializer() {
        return new RecipeSerializer<>(CODEC, STREAM_CODEC);
    }

    public static final MapCodec<CrushingTubRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.fieldOf("input").forGetter(CrushingTubRecipe::getInput),
            ChanceOutputItem.CODEC.optionalFieldOf("output")
                    .forGetter(r -> ChanceOutputItem.optional(r.getOutput())),
            FluidStackTemplate.CODEC.optionalFieldOf("fluid", null)
                    .forGetter(r -> RecipeUtils.optionalCodec(r.getFluid())))
            .apply(inst, (i, o, f) -> new CrushingTubRecipe(i, o.orElse(null), f)));

    public static final StreamCodec<RegistryFriendlyByteBuf, CrushingTubRecipe> STREAM_CODEC = StreamCodec
            .composite(
                    Ingredient.CONTENTS_STREAM_CODEC, CrushingTubRecipe::getInput,
                    ByteBufCodecs.optional(ChanceOutputItem.STREAM_CODEC),
                    r -> ChanceOutputItem.optional(r.getOutput()),
                    ByteBufCodecs.optional(FluidStackTemplate.STREAM_CODEC),
                    r -> Optional.of(RecipeUtils.optionalCodec(r.getFluid())),
                    (i, o, f) -> new CrushingTubRecipe(i, o.orElse(null), f.orElse(null)));

}