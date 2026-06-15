package com.synergy.vintagetech.api;

import com.devdyna.cakesticklib.api.recipe.recipeBuilder.BaseRecipeBuilder;
import com.devdyna.cakesticklib.api.recipe.recipeBuilder.BuilderAttach;
import com.devdyna.cakesticklib.api.recipe.recipeOutput.ChanceOutputItem;
import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;

//TODO API : move to ChanceOutputItem / recipe builder
public interface BuilderChanceOutputItemAttach<BUILDER extends BaseRecipeBuilder> extends BuilderAttach<BUILDER> {

    abstract BUILDER output(ChanceOutputItem output);

    default BUILDER output(ItemStackTemplate output, float chance) {
        return output(ChanceOutputItem.of(output, chance));
    }

    default BUILDER output(Item output, float chance) {
        return output(x.itemTemplate(output), chance);
    }

    default BUILDER output(ItemLike output, float chance) {
        return output(x.itemTemplate(output), chance);
    }

    default BUILDER output(DeferredHolder<Item, Item> output, float chance) {
        return output(output.get(), chance);
    }

    default BUILDER output(Item output, int count, float chance) {
        return output(x.itemTemplate(output, count), chance);
    }

    default BUILDER output(DeferredHolder<Item, Item> output, int count, float chance) {
        return output(output.get(), count, chance);
    }

}
