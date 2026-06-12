package com.synergy.vintagetech.init.builder.evaporation_basin.recipe;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.List;
import com.devdyna.cakesticklib.api.recipe.recipeInput.FluidInput;
import com.devdyna.cakesticklib.api.recipe.recipeType.BaseRecipeType;
import com.devdyna.cakesticklib.api.utils.x;
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
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

@SuppressWarnings("null")
public class EvaporationBasinRecipe extends BaseRecipeType<FluidInput.withNumber> {

    private final SizedFluidIngredient fluid;
    private final int ticks;
    private final ItemStackTemplate output;

    public EvaporationBasinRecipe(SizedFluidIngredient fluid,
            int ticks, ItemStackTemplate output) {
        this.fluid = fluid;
        this.ticks = ticks;
        this.output = output;
    }

    public static EvaporationBasinRecipe of(SizedFluidIngredient fluid,
            int ticks, ItemStackTemplate output) {
        return new EvaporationBasinRecipe(fluid, ticks, output);
    }

    public boolean matches(FluidInput.withNumber r, Level l) {
        return this.fluid.test(r.fluid());
    }

    @Override
    public ItemStack assemble(FluidInput.withNumber r) {
        return this.output.create().copy();
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List
                .of(x.itemIngredient(x.getFluidStacksFromIngredient(this.fluid).getFirst().getFluid().getBucket())));
    }

    public SizedFluidIngredient getFluid() {
        return fluid;
    }

    public int getTicks() {
        return ticks;
    }

    public ItemStackTemplate getOutput() {
        return output;
    }

    @Override
    public RecipeType<? extends Recipe<FluidInput.withNumber>> getType() {
        return zRecipeTypes.EVAPORATION_BASIN.getType();
    }

    @Override
    public RecipeSerializer<? extends Recipe<FluidInput.withNumber>> getSerializer() {
        return zRecipeTypes.EVAPORATION_BASIN.getSerializer();
    }

    @Override
    public String group() {
        return MODULE_ID;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.EVAPORATION_BASIN.get().asItem();
    }

    public static final RecipeSerializer<EvaporationBasinRecipe> serializer() {
        return new RecipeSerializer<>(CODEC, STREAM_CODEC);
    }

    public static final MapCodec<EvaporationBasinRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            SizedFluidIngredient.CODEC.fieldOf("fluid").forGetter(EvaporationBasinRecipe::getFluid),
            Codec.intRange(1, Integer.MAX_VALUE).fieldOf("ticks").forGetter(EvaporationBasinRecipe::getTicks),
            ItemStackTemplate.CODEC.fieldOf("output").forGetter(EvaporationBasinRecipe::getOutput))
            .apply(inst, (i, o, f) -> new EvaporationBasinRecipe(i, o, f)));

    public static final StreamCodec<RegistryFriendlyByteBuf, EvaporationBasinRecipe> STREAM_CODEC = StreamCodec
            .composite(
                    SizedFluidIngredient.STREAM_CODEC, EvaporationBasinRecipe::getFluid,
                    ByteBufCodecs.INT, EvaporationBasinRecipe::getTicks,
                    ItemStackTemplate.STREAM_CODEC, EvaporationBasinRecipe::getOutput,
                    (i, o, f) -> new EvaporationBasinRecipe(i, o, f));

}