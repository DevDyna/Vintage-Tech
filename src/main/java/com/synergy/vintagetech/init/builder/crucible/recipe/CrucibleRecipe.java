package com.synergy.vintagetech.init.builder.crucible.recipe;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.devdyna.cakesticklib.api.recipe.recipeOutput.ChanceOutput;
import com.devdyna.cakesticklib.api.recipe.recipeType.BaseRecipeType;
import com.devdyna.cakesticklib.api.utils.x;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.synergy.vintagetech.api.recipeinput.CrucibleInput;
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
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import net.neoforged.neoforge.fluids.FluidStackTemplate;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

public class CrucibleRecipe extends BaseRecipeType<CrucibleInput> {

    private final SizedFluidIngredient input_fluid;
    private final List<SizedIngredient> input_items;
    private final int ticks;
    private final FluidStackTemplate output_fluid;
    private final List<ChanceOutput.Item> output_items;

    public CrucibleRecipe(SizedFluidIngredient input_fluid, List<SizedIngredient> input_items, int ticks,
            FluidStackTemplate output_fluid, List<ChanceOutput.Item> output_items) {
        this.input_fluid = input_fluid;
        this.input_items = input_items;
        this.ticks = ticks;
        this.output_fluid = output_fluid;
        this.output_items = output_items;
    }

    public static CrucibleRecipe of(SizedFluidIngredient input_fluid, List<SizedIngredient> input_items, int ticks,
            FluidStackTemplate output_fluid, List<ChanceOutput.Item> output_items) {
        return new CrucibleRecipe(input_fluid, input_items, ticks, output_fluid, output_items);
    }

    @Override
    public boolean matches(CrucibleInput input, Level level) {

        if (!input_fluid.test(input.fluid()))
            return false;

        List<ItemStack> available = new ArrayList<>();

        for (ItemStack stack : input.items())
            available.add(stack.copy());

        for (SizedIngredient ingredient : input_items) {

            boolean found = false;
            for (ItemStack stack : available)
                if (ingredient.ingredient().test(stack) && stack.getCount() >= ingredient.count()) {

                    stack.shrink(ingredient.count());

                    found = true;
                    break;
                }

            if (!found)
                return false;
        }

        return true;
    }

    @Override
    public ItemStack assemble(CrucibleInput input) {

        if (output_fluid != null)
            return x.item(output_fluid.create().getFluid().getBucket()).copy();

        return ItemStack.EMPTY;
    }

    public NonNullList<Ingredient> getIngredients() {

        NonNullList<Ingredient> list = NonNullList.create();

        for (SizedIngredient ingredient : input_items)
            list.add(ingredient.ingredient());

        return list;
    }

    public SizedFluidIngredient getInputFluid() {
        return input_fluid;
    }

    public List<SizedIngredient> getItemInputs() {
        return input_items;
    }

    public int getTicks() {
        return ticks;
    }

    public FluidStackTemplate getOutputFluid() {
        return output_fluid;
    }

    public List<ChanceOutput.Item> getOutputItems() {
        return output_items;
    }

    @Override
    public RecipeType<? extends Recipe<CrucibleInput>> getType() {
        return zRecipeTypes.CRUCIBLE.getType();
    }

    @Override
    public RecipeSerializer<? extends Recipe<CrucibleInput>> getSerializer() {
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

    public static RecipeSerializer<CrucibleRecipe> serializer() {
        return new RecipeSerializer<>(CODEC, STREAM_CODEC);
    }

    public static final MapCodec<CrucibleRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(

            SizedFluidIngredient.CODEC
                    .fieldOf("input_fluid")
                    .forGetter(CrucibleRecipe::getInputFluid),

            SizedIngredient.NESTED_CODEC
                    .listOf()
                    .fieldOf("input_items")
                    .forGetter(CrucibleRecipe::getItemInputs),

            Codec.intRange(1, Integer.MAX_VALUE)
                    .fieldOf("ticks")
                    .forGetter(CrucibleRecipe::getTicks),

            FluidStackTemplate.CODEC
                    .optionalFieldOf("output_fluid")
                    .forGetter(r -> Optional.ofNullable(r.getOutputFluid())),

            ChanceOutput.Item.CODEC
                    .listOf()
                    .optionalFieldOf("output_items", List.of())
                    .forGetter(CrucibleRecipe::getOutputItems)

    ).apply(inst, (inf, ini, t, ouf, oui) -> new CrucibleRecipe(inf, ini, t, ouf.orElse(null), oui)));

    public static final StreamCodec<RegistryFriendlyByteBuf, CrucibleRecipe> STREAM_CODEC = StreamCodec.composite(

            SizedFluidIngredient.STREAM_CODEC,
            CrucibleRecipe::getInputFluid,

            SizedIngredient.STREAM_CODEC
                    .apply(ByteBufCodecs.list()),
            CrucibleRecipe::getItemInputs,

            ByteBufCodecs.INT,
            CrucibleRecipe::getTicks,

            ByteBufCodecs.optional(
                    FluidStackTemplate.STREAM_CODEC),
            r -> Optional.ofNullable(r.getOutputFluid()),

            ChanceOutput.Item.STREAM_CODEC
                    .apply(ByteBufCodecs.list()),
            CrucibleRecipe::getOutputItems,

            (inf, ini, t, ouf, oui) -> new CrucibleRecipe(inf, ini, t, ouf.orElse(null), oui)

    );
}