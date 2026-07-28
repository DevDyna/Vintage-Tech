package com.synergy.vintagetech.init.builder.mechanical_farmland.recipe;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.LinkedHashMap;

import com.devdyna.cakesticklib.api.recipe.recipeBuilder.*;
import com.devdyna.cakesticklib.api.utils.x;
import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

public class FarmlandFuelsBuilder extends BaseRecipeBuilder
        implements FluidAttach.Input.SizedFluid<FarmlandFuelsBuilder> {

    private SizedFluidIngredient fluid;

    private FarmlandFuelsBuilder(HolderLookup.Provider p) {
        super(p);
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static FarmlandFuelsBuilder of(HolderLookup.Provider p) {
        return new FarmlandFuelsBuilder(p);
    }

    @Override
    public FarmlandFuelsBuilder fluid(SizedFluidIngredient fluid) {
        this.fluid = fluid;
        return this;

    }

    public FarmlandFuelsBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public Recipe<?> createRecipe() {
        return new FarmlandFuelsRecipe(fluid);
    }

    @Override
    public FarmlandFuelsBuilder getBuilder() {
        return this;
    }

    @Override
    public Identifier getSuffix(String extra) {
        return x.rl(MODULE_ID, "farmland_fuels/" + fluid.ingredient().toString()
                + extra);
    }

}