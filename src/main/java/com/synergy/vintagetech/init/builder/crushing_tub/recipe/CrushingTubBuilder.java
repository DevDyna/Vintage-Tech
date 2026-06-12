package com.synergy.vintagetech.init.builder.crushing_tub.recipe;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.LinkedHashMap;

import com.devdyna.cakesticklib.api.recipe.recipeBuilder.*;
import com.devdyna.cakesticklib.api.recipe.recipeOutput.ChanceOutputItem;
import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.api.BuilderChanceOutputItemAttach;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.fluids.FluidStackTemplate;

public class CrushingTubBuilder extends BaseRecipeBuilder
        implements ItemAttach.Input.NoItemCount<CrushingTubBuilder>,
        BuilderChanceOutputItemAttach<CrushingTubBuilder>, FluidAttach.Output.OutputFluid<CrushingTubBuilder> {

    private Ingredient input;
    private FluidStackTemplate fluid;
    private ChanceOutputItem output;

    private CrushingTubBuilder(HolderLookup.Provider p) {
        super(p);
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static CrushingTubBuilder of(HolderLookup.Provider p) {
        return new CrushingTubBuilder(p);
    }

    public CrushingTubBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    @Override
    public CrushingTubBuilder output(ChanceOutputItem output) {
        this.output = output;
        return this;
    }

    @Override
    public CrushingTubBuilder output(FluidStackTemplate fluid) {
        this.fluid = fluid;
        return this;
    }


    // public CrushingTubBuilder unlockedBy() {
    //     return unlockedBy(x.name(x.getItemsFromIngredient(input)[0]), InventoryChangeTrigger.TriggerInstance
    //             .hasItems(x.getItemsFromIngredient(input)));
    // }

    public CrushingTubBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public Recipe<?> createRecipe() {
        return new CrushingTubRecipe(input, output, fluid);
    }

    @Override
    public CrushingTubBuilder getBuilder() {
        return this;
    }

    @Override
    public Identifier getSuffix(String extra) {
        return x.rl(MODULE_ID, "crushing_tub/" + x.name(output.item())
                + extra);
    }

}