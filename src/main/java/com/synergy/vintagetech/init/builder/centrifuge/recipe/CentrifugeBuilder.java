package com.synergy.vintagetech.init.builder.centrifuge.recipe;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.LinkedHashMap;

import com.devdyna.cakesticklib.api.recipe.recipeBuilder.*;
import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.fluids.FluidStackTemplate;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

public class CentrifugeBuilder extends BaseRecipeBuilder
        implements ItemAttach.Input.ItemCounted<CentrifugeBuilder>,
        FluidAttach.Input.SizedFluid<CentrifugeBuilder>,
        FluidAttach.Output.OutputFluid<CentrifugeBuilder> {

    private SizedFluidIngredient input_fluid;
    private SizedIngredient catalyst;
    private int ticks;
    private FluidStackTemplate output_fluid;

    private CentrifugeBuilder(HolderLookup.Provider p) {
        super(p);
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
        this.ticks = 40;
    }

    public static CentrifugeBuilder of(HolderLookup.Provider p) {
        return new CentrifugeBuilder(p);
    }

    @Override
    public CentrifugeBuilder input(SizedIngredient i) {
        this.catalyst = i;
        return this;
    }

    @Override
    public CentrifugeBuilder output(FluidStackTemplate f) {
        this.output_fluid = f;
        return this;
    }

    @Override
    public CentrifugeBuilder fluid(SizedFluidIngredient f) {
        this.input_fluid = f;
        return this;
    }

    public CentrifugeBuilder time(int ticks) {
        this.ticks = ticks;
        return this;
    }

    public CentrifugeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public Recipe<?> createRecipe() {
        return new CentrifugeRecipe(input_fluid, catalyst, ticks, output_fluid);
    }

    @Override
    public CentrifugeBuilder getBuilder() {
        return this;
    }

    @Override
    public Identifier getSuffix(String extra) {
        return x.rl(MODULE_ID, "centrifuge/" + x.name(output_fluid.fluid().value())
                + extra);
    }

}