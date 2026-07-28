package com.synergy.vintagetech.init.builder.crucible.recipe;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.LinkedHashMap;

import com.devdyna.cakesticklib.api.recipe.recipeBuilder.*;
import com.devdyna.cakesticklib.api.recipe.recipeOutput.ChanceOutput;
import com.devdyna.cakesticklib.api.recipe.recipeOutput.ChanceOutput.Item;
import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.fluids.FluidStackTemplate;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

public class CrucibleBuilder extends BaseRecipeBuilder
        implements ItemAttach.Input.ItemCounted<CrucibleBuilder>,
        FluidAttach.Input.SizedFluid<CrucibleBuilder>,
        FluidAttach.Output.OutputFluid<CrucibleBuilder>, ItemAttach.Output.ItemOutputChance<CrucibleBuilder> {

    private SizedFluidIngredient input_fluid;
    private SizedIngredient input_item;
    private int ticks;
    private FluidStackTemplate output_fluid;
    private ChanceOutput.Item output_item;

    private CrucibleBuilder(HolderLookup.Provider p) {
        super(p);
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
        this.ticks = 80;
    }

    public static CrucibleBuilder of(HolderLookup.Provider p) {
        return new CrucibleBuilder(p);
    }

    @Override
    public CrucibleBuilder input(SizedIngredient i) {
        this.input_item = i;
        return this;
    }

    @Override
    public CrucibleBuilder output(FluidStackTemplate f) {
        this.output_fluid = f;
        return this;
    }

    @Override
    public CrucibleBuilder fluid(SizedFluidIngredient f) {
        this.input_fluid = f;
        return this;
    }

    public CrucibleBuilder time(int ticks) {
        this.ticks = ticks;
        return this;
    }

    @Override
    public CrucibleBuilder output(Item output_item) {
        this.output_item = output_item;
        return this;
    }

    public CrucibleBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public Recipe<?> createRecipe() {
        return new CrucibleRecipe(input_fluid, input_item, ticks, output_fluid, output_item);
    }

    @Override
    public CrucibleBuilder getBuilder() {
        return this;
    }

    @Override
    public Identifier getSuffix(String extra) {
        return x.rl(MODULE_ID, "crucible/" + x.name(output_fluid)
                + extra);
    }

}