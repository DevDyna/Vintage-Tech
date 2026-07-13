package com.synergy.vintagetech.compat.jei.categories;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.compat.jei.BaseRecipeCategory;
import com.devdyna.cakesticklib.api.compat.jei.JEIFluidTankHelper;
import com.devdyna.cakesticklib.api.primitive.Size;
import com.devdyna.cakesticklib.api.utils.ColorUtils;
import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.init.builder.treetap.recipe.TreeTapRecipe;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

public class TreeTapCategory extends BaseRecipeCategory<TreeTapRecipe> {

    public TreeTapCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final IRecipeType<RecipeHolder<TreeTapRecipe>> TYPE = IRecipeType
            .create(zRecipeTypes.TREE_TAP.getType());

    @Override
    public IRecipeType<RecipeHolder<TreeTapRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTraslationKey() {
        return MODULE_ID + ".jei.tree_tap";
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.TREE_TAP.get();
    }

    @Override
    public Identifier setBackGround() {
        return x.rl(MODULE_ID, "textures/gui/jei/tree_tap.png");
    }

    @Override
    public Size setXY() {
        return Size.of(122, 39);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, TreeTapRecipe recipe, IFocusGroup focuses) {

        var level = Minecraft.getInstance().level;

        JEIFluidTankHelper.of()
                .fluid(recipe.getFluid().create())
                .offset(105, 35 + 1)
                .scale(2.0f, 1.0f)
                .build((x, y) -> builder.addInputSlot(x, y));

        builder.addInputSlot(57, 21).add(recipe.getLog().getasIngredient(level));
        builder.addInputSlot(57, 2).add(recipe.getLeaves().getasIngredient(level));

    }

    @Override
    public void draw(TreeTapRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics,
            double mouseX, double mouseY) {

        guiGraphics.text(this.font, Component.literal(recipe.getChance() + "%"), 3+10+5, 27-1, ColorUtils.WHITE.getRGB());
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

    }

    @Override
    public boolean enableTimerRender() {
        return true;
    }

    @Override
    public int tickValue(TreeTapRecipe recipe) {
        return recipe.getDelay();
    }

    @Override
    public Size tickPos() {
        return Size.of(3+2+4, 9-2);
    }

    @Override
    public int tickColor() {
        return ColorUtils.WHITE.getRGB();
    }

}