package com.synergy.vintagetech.init.builder.crucible.recipe;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.List;
import java.util.Optional;

import com.devdyna.cakesticklib.api.recipe.recipeOutput.ChanceOutput;
import com.devdyna.cakesticklib.api.recipe.recipeType.BaseRecipeType;
import com.devdyna.cakesticklib.api.utils.x;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.synergy.vintagetech.api.recipeinput.FluidAndItemInput;
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

public class CrucibleRecipe extends BaseRecipeType<FluidAndItemInput> {

    private final SizedFluidIngredient input_fluid;
    private final SizedIngredient catalyst;
    private final int ticks;
    private final FluidStackTemplate output_fluid;
    private final ChanceOutput.Item output_item;

    public CrucibleRecipe(SizedFluidIngredient input_fluid, SizedIngredient catalyst,
            int ticks, FluidStackTemplate output_fluid, ChanceOutput.Item output_item) {
        this.input_fluid = input_fluid;
        this.catalyst = catalyst;
        this.ticks = ticks;
        this.output_fluid = output_fluid;
        this.output_item = output_item;
    }

    public static CrucibleRecipe of(SizedFluidIngredient input_fluid, SizedIngredient catalyst,
            int ticks, FluidStackTemplate output_fluid, ChanceOutput.Item output_item) {
        return new CrucibleRecipe(input_fluid, catalyst, ticks, output_fluid, output_item);
    }

    public boolean matches(FluidAndItemInput r, Level l) {
        return this.input_fluid.test(r.fluid()) && this.catalyst.test(r.catalyst());
    }

    @Override
    public ItemStack assemble(FluidAndItemInput r) {
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

    public SizedIngredient getItemInput() {
        return catalyst;
    }

    public FluidStackTemplate getOutputFluid() {
        return output_fluid;
    }

    public ChanceOutput.Item getOutputItem() {
        return output_item;
    }

    @Override
    public RecipeType<? extends Recipe<FluidAndItemInput>> getType() {
        return zRecipeTypes.CRUCIBLE.getType();
    }

    @Override
    public RecipeSerializer<? extends Recipe<FluidAndItemInput>> getSerializer() {
        return zRecipeTypes.CRUCIBLE.getSerializer();
    }

    @Override
    public String group() {
        return MODULE_ID;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.CRUCIBLE.get().asItem();
    }

    public static final RecipeSerializer<CrucibleRecipe> serializer() {
        return new RecipeSerializer<>(CODEC, STREAM_CODEC);
    }

    public static final MapCodec<CrucibleRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            SizedFluidIngredient.CODEC.fieldOf("input_fluid").forGetter(CrucibleRecipe::getInputFluid),
            SizedIngredient.NESTED_CODEC.fieldOf("input_item").forGetter(CrucibleRecipe::getItemInput),
            Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(CrucibleRecipe::getTicks),
            FluidStackTemplate.CODEC.optionalFieldOf("output_fluid").forGetter(r -> Optional.of(r.getOutputFluid())),
            ChanceOutput.Item.CODEC.optionalFieldOf("output_item")
                    .forGetter(r -> ChanceOutput.Item.optional(r.getOutputItem())))
            .apply(inst,
                    (inf, ini, ti, of, oi) -> new CrucibleRecipe(inf, ini, ti, of.orElse(null), oi.orElse(null))));

    public static final StreamCodec<RegistryFriendlyByteBuf, CrucibleRecipe> STREAM_CODEC = StreamCodec
            .composite(
                    SizedFluidIngredient.STREAM_CODEC, CrucibleRecipe::getInputFluid,
                    SizedIngredient.STREAM_CODEC, CrucibleRecipe::getItemInput,
                    ByteBufCodecs.INT, CrucibleRecipe::getTicks,
                    ByteBufCodecs.optional(FluidStackTemplate.STREAM_CODEC), f -> Optional.of(f.getOutputFluid()),
                    ByteBufCodecs.optional(ChanceOutput.Item.STREAM_CODEC),
                    f -> ChanceOutput.Item.optional(f.getOutputItem()),
                    (inf, ini, ti, of, oi) -> new CrucibleRecipe(inf, ini, ti, of.orElse(null), oi.orElse(null)));
}