package com.synergy.vintagetech.init.builder.evaporation_basin.recipe;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.LinkedHashMap;

import com.devdyna.cakesticklib.api.recipe.recipeBuilder.*;
import com.devdyna.cakesticklib.api.utils.x;
import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

public class EvaporationBasinBuilder extends BaseRecipeBuilder
        implements ItemAttach.Output.SimpleOutputItem<EvaporationBasinBuilder>,
        FluidAttach.Input.SizedFluid<EvaporationBasinBuilder> {

    private SizedFluidIngredient fluid;
    private int ticks;
    private ItemStackTemplate output;

    private EvaporationBasinBuilder(HolderLookup.Provider p) {
        super(p);
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
        this.ticks = 20;
    }

    public static EvaporationBasinBuilder of(HolderLookup.Provider p) {
        return new EvaporationBasinBuilder(p);
    }

    public EvaporationBasinBuilder time(int ticks) {
        this.ticks = ticks;
        return this;
    }

    @Override
    public EvaporationBasinBuilder fluid(SizedFluidIngredient fluid) {
        this.fluid = fluid;
        return this;

    }

    @Override
    public EvaporationBasinBuilder output(ItemStackTemplate output) {
        this.output = output;
        return this;
    }

    public EvaporationBasinBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public Recipe<?> createRecipe() {
        return new EvaporationBasinRecipe(fluid, ticks, output);
    }

    @Override
    public EvaporationBasinBuilder getBuilder() {
        return this;
    }

    @Override
    public Identifier getSuffix(String extra) {
        return x.rl(MODULE_ID, "evaporation_basin/" + x.name(output)
                + extra);
    }

}