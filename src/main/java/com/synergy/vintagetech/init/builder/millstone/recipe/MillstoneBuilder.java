package com.synergy.vintagetech.init.builder.millstone.recipe;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.LinkedHashMap;

import com.devdyna.cakesticklib.api.recipe.recipeBuilder.*;
import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

public class MillstoneBuilder extends BaseRecipeBuilder
        implements ItemAttach.Input.NoItemCount<MillstoneBuilder>,
        ItemAttach.Output.SimpleOutputItem<MillstoneBuilder> {

    private Ingredient input;
    private int ticks;
    private ItemStackTemplate output;

    private MillstoneBuilder(HolderLookup.Provider p) {
        super(p);
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
        this.ticks = 40;
    }

    public static MillstoneBuilder of(HolderLookup.Provider p) {
        return new MillstoneBuilder(p);
    }

    public MillstoneBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public MillstoneBuilder time(int ticks) {
        this.ticks = ticks;
        return this;
    }

    @Override
    public MillstoneBuilder output(ItemStackTemplate output) {
        this.output = output;
        return this;
    }

    // public CrushingTubBuilder unlockedBy() {
    // return unlockedBy(x.name(x.getItemsFromIngredient(input)[0]),
    // InventoryChangeTrigger.TriggerInstance
    // .hasItems(x.getItemsFromIngredient(input)));
    // }

    public MillstoneBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public Recipe<?> createRecipe() {
        return new MillstoneRecipe(input, ticks, output);
    }

    @Override
    public MillstoneBuilder getBuilder() {
        return this;
    }

    @Override
    public Identifier getSuffix(String extra) {
        return x.rl(MODULE_ID, "millstone/" + x.name(output)
                + extra);
    }

}