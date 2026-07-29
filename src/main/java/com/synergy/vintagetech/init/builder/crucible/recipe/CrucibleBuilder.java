package com.synergy.vintagetech.init.builder.crucible.recipe;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.devdyna.cakesticklib.api.recipe.recipeBuilder.*;
import com.devdyna.cakesticklib.api.recipe.recipeOutput.ChanceOutput;
import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import net.neoforged.neoforge.fluids.FluidStackTemplate;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

public class CrucibleBuilder extends BaseRecipeBuilder
        implements ItemAttach.Input.ListedItemCount<CrucibleBuilder>,
        FluidAttach.Input.SizedFluid<CrucibleBuilder>,
        FluidAttach.Output.OutputFluid<CrucibleBuilder> {

    private SizedFluidIngredient input_fluid;
    private List<SizedIngredient> input_items;
    private int ticks;
    private FluidStackTemplate output_fluid;
    private List<ChanceOutput.Item> output_item;

    public static final int MAX_ITEMS = 4;

    private CrucibleBuilder(HolderLookup.Provider p) {
        super(p);
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
        this.ticks = 80;
        this.input_items = new ArrayList<>(MAX_ITEMS);
        this.output_item = new ArrayList<>(MAX_ITEMS);
    }

    public static CrucibleBuilder of(HolderLookup.Provider p) {
        return new CrucibleBuilder(p);
    }

    @Override
    public CrucibleBuilder add(SizedIngredient i) {
        if (input_items.size() >= MAX_ITEMS)
            throw new IndexOutOfBoundsException("CrucibleBuilder.add has reached the max amount of item inputs!");

        this.input_items.add(i);
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

    public CrucibleBuilder output(ChanceOutput.Item i) {
        if (output_item.size() >= MAX_ITEMS)
            throw new IndexOutOfBoundsException("CrucibleBuilder.add has reached the max amount of item inputs!");

        this.output_item.add(i);
        return this;
    }

    public CrucibleBuilder output(ItemLike i, int count, float chance) {
        return output(ChanceOutput.Item.of(x.itemTemplate(i.asItem(), count), chance));
    }

    public CrucibleBuilder output(ItemLike i, float chance) {
        return output(ChanceOutput.Item.of(x.itemTemplate(i.asItem()), chance));
    }

    public CrucibleBuilder output(ItemLike i) {
        return output(ChanceOutput.Item.of(x.itemTemplate(i.asItem()), 1f));
    }

    public CrucibleBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public Recipe<?> createRecipe() {
        return new CrucibleRecipe(input_fluid, input_items, ticks, output_fluid, output_item);
    }

    @Override
    public CrucibleBuilder getBuilder() {
        return this;
    }

    //TODO API : add prefix on save()

    @Override
    public Identifier getSuffix(String extra) {
        return x.rl(MODULE_ID, "crucible/" + (output_fluid == null ? x.name(x.getItemsFromIngredient(input_items.getFirst().ingredient())[0]) : x.name(output_fluid))
                + extra);
    }

}