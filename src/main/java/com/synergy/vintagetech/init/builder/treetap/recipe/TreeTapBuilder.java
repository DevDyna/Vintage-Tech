package com.synergy.vintagetech.init.builder.treetap.recipe;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.LinkedHashMap;

import com.devdyna.cakesticklib.api.recipe.recipeBuilder.*;
import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.api.BlockOrTag;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.fluids.FluidStackTemplate;

public class TreeTapBuilder extends BaseRecipeBuilder implements FluidAttach.Output.OutputFluid<TreeTapBuilder> {

    private BlockOrTag log;
    private BlockOrTag leaves;
    private FluidStackTemplate fluid;

    private TreeTapBuilder(HolderLookup.Provider p) {
        super(p);
        this.criteria = new LinkedHashMap<String, Criterion<?>>();
    }

    public static TreeTapBuilder of(HolderLookup.Provider p) {
        return new TreeTapBuilder(p);
    }

    public TreeTapBuilder log(Block log) {
        this.log = BlockOrTag.add(log);
        return this;
    }

    public TreeTapBuilder log(TagKey<Block> log) {
        this.log = BlockOrTag.add(log);
        return this;
    }

    public TreeTapBuilder leaves(Block leaves) {
        this.leaves = BlockOrTag.add(leaves);
        return this;
    }

    public TreeTapBuilder leaves(TagKey<Block> leaves) {
        this.leaves = BlockOrTag.add(leaves);
        return this;
    }

    @Override
    public TreeTapBuilder output(FluidStackTemplate fluid) {
        this.fluid = fluid;
        return this;
    }

    public TreeTapBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public Recipe<?> createRecipe() {
        return new TreeTapRecipe(log, leaves, fluid);
    }

    @Override
    public Identifier getSuffix(String extra) {// TODO API : move to api x.name(<Resource>StackTemplate)
        return x.rl(MODULE_ID, "tree_tap/" + x.name(fluid.fluid().value())
                + extra);
    }

    @Override
    public TreeTapBuilder getBuilder() {
        return this;
    }

}