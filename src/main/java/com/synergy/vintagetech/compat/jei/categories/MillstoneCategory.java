package com.synergy.vintagetech.compat.jei.categories;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.compat.jei.BaseRecipeCategory;
import com.devdyna.cakesticklib.api.primitive.Size;
import com.devdyna.cakesticklib.api.utils.ColorUtils;
import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.init.builder.millstone.recipe.MillstoneRecipe;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

public class MillstoneCategory extends BaseRecipeCategory<MillstoneRecipe> {

    public MillstoneCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final IRecipeType<RecipeHolder<MillstoneRecipe>> TYPE = IRecipeType
            .create(zRecipeTypes.MILLSTONE.getType());

    @Override
    public IRecipeType<RecipeHolder<MillstoneRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTraslationKey() {
        return MODULE_ID + ".jei.millstone";
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.MILLSTONE.get();
    }

    @Override
    public Identifier setBackGround() {
        return x.rl(MODULE_ID, "textures/gui/jei/millstone.png");
    }

    @Override
    public Size setXY() {
        return Size.of(96, 52);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MillstoneRecipe recipe, IFocusGroup focuses) {

        builder.addInputSlot(1, 1).add(recipe.getInput());
        builder.addOutputSlot(78, 25).add(recipe.getOutput());
    }

    @Override
    public boolean enableTimerRender() {
        return true;
    }

    @Override
    public int tickValue(MillstoneRecipe recipe) {
        return recipe.getTicks();
    }

    @Override
    public Size tickPos() {
        return Size.of(58, 4);
    }

    @Override
    public int tickColor() {
        return ColorUtils.WHITE.getRGB();
    }

}