package com.synergy.vintagetech.init.builder.drying_rack.recipe;

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

public class DryingRackBuilder extends BaseRecipeBuilder
        implements ItemAttach.Input.NoItemCount<DryingRackBuilder>,
        ItemAttach.Output.SimpleOutputItem<DryingRackBuilder> {

    private Ingredient input;
    private int ticks;
    private ItemStackTemplate output;

    private DryingRackBuilder(HolderLookup.Provider p) {
        super(p);
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
        this.ticks = 200;
    }

    public static DryingRackBuilder of(HolderLookup.Provider p) {
        return new DryingRackBuilder(p);
    }

    public DryingRackBuilder input(Ingredient input) {
        this.input = input;
        return this;
    }

    public DryingRackBuilder time(int ticks) {
        this.ticks = ticks;
        return this;
    }

    @Override
    public DryingRackBuilder output(ItemStackTemplate output) {
        this.output = output;
        return this;
    }

    // public CrushingTubBuilder unlockedBy() {
    // return unlockedBy(x.name(x.getItemsFromIngredient(input)[0]),
    // InventoryChangeTrigger.TriggerInstance
    // .hasItems(x.getItemsFromIngredient(input)));
    // }

    public DryingRackBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public Recipe<?> createRecipe() {
        return new DryingRackRecipe(input, ticks, output);
    }

    @Override
    public DryingRackBuilder getBuilder() {
        return this;
    }

    @Override
    public Identifier getSuffix(String extra) {
        return x.rl(MODULE_ID, "drying_racks/" + x.name(output)
                + extra);
    }

}