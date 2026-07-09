package com.synergy.vintagetech.init.builder.treetap.recipe;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.List;
import com.devdyna.cakesticklib.api.recipe.recipeType.BaseRecipeType;
import com.devdyna.cakesticklib.api.utils.x;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.synergy.vintagetech.api.BlockOrTag;
import com.synergy.vintagetech.api.recipeinput.TreeTapInput;
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
import net.neoforged.neoforge.fluids.FluidStackTemplate;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class TreeTapRecipe extends BaseRecipeType<TreeTapInput> {

    private final BlockOrTag log;
    private final BlockOrTag leaves;
    private final FluidStackTemplate fluid;

    public TreeTapRecipe(BlockOrTag log, BlockOrTag leaves, FluidStackTemplate fluid) {
        this.log = log;
        this.leaves = leaves;
        this.fluid = fluid;
    }

    public static TreeTapRecipe of(BlockOrTag log, BlockOrTag leaves, FluidStackTemplate fluid) {
        return new TreeTapRecipe(log, leaves, fluid);
    }

    public boolean matches(TreeTapInput r, Level l) {
        return this.log.test(r.log().getBlock()) && this.leaves.test(r.leaves().getBlock());
    }

    @Override
    public ItemStack assemble(TreeTapInput r) {
        return x.item(this.fluid.create().copy().getFluid().getBucket());
    }

    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.copyOf(List.of(x.itemIngredient(this.fluid.create().copy().getFluid().getBucket())));
    }

    public BlockOrTag getLog() {
        return log;
    }

    public BlockOrTag getLeaves() {
        return leaves;
    }

    public FluidStackTemplate getFluid() {
        return fluid;
    }

    @Override
    public RecipeType<? extends Recipe<TreeTapInput>> getType() {
        return zRecipeTypes.TREE_TAP.getType();
    }

    @Override
    public RecipeSerializer<? extends Recipe<TreeTapInput>> getSerializer() {
        return zRecipeTypes.TREE_TAP.getSerializer();
    }

    @Override
    public String group() {
        return MODULE_ID;
    }

    @Override
    public Item getToastIcon() {
        return zBlocks.TREE_TAP.get().asItem();
    }

    public static final RecipeSerializer<TreeTapRecipe> serializer() {
        return new RecipeSerializer<>(CODEC, STREAM_CODEC);
    }

    public static final MapCodec<TreeTapRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            BlockOrTag.CODEC.fieldOf("logs").forGetter(TreeTapRecipe::getLog),
            BlockOrTag.CODEC.fieldOf("leaves").forGetter(TreeTapRecipe::getLeaves),
            FluidStackTemplate.CODEC.fieldOf("fluid").forGetter(TreeTapRecipe::getFluid))
            .apply(inst, TreeTapRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, TreeTapRecipe> STREAM_CODEC = StreamCodec.composite(
            BlockOrTag.STREAM_CODEC, TreeTapRecipe::getLog,
            BlockOrTag.STREAM_CODEC, TreeTapRecipe::getLeaves,
            FluidStackTemplate.STREAM_CODEC, TreeTapRecipe::getFluid,
            TreeTapRecipe::new);
}