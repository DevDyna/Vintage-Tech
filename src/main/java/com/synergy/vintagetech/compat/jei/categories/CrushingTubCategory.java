package com.synergy.vintagetech.compat.jei.categories;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.compat.jei.BaseRecipeCategory;
import com.devdyna.cakesticklib.api.compat.jei.JEIFluidTankHelper;
import com.devdyna.cakesticklib.api.primitive.Size;
import com.devdyna.cakesticklib.api.utils.ColorUtils;
import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.init.builder.crushing_tub.recipe.CrushingTubRecipe;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

public class CrushingTubCategory extends BaseRecipeCategory<CrushingTubRecipe> {

    public CrushingTubCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final IRecipeType<RecipeHolder<CrushingTubRecipe>> TYPE = IRecipeType
            .create(zRecipeTypes.CRUSHING_TUB.getType());

    @Override
    public IRecipeType<RecipeHolder<CrushingTubRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTraslationKey() {
        return MODULE_ID + ".jei.crushing_tub";
    }

    @Override
    public boolean enableTimerRender() {
        return false;
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.CRUSHING_TUB.get();
    }

    @Override
    public Size setXY() {
        return Size.of(108, 72);
    }

    @Override
    public Identifier setBackGround() {
        return x.rl(MODULE_ID, "textures/gui/jei/crushing_tub.png");
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CrushingTubRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 21, 29).add(recipe.getInput());

        if (recipe.getFluid() != null && recipe.getFluid().amount() > 0)
            JEIFluidTankHelper.of()
                    .fluid(recipe.getFluid().create())
                    .offset(71, 39)
                    .scale(2.0f, 1.0f)
                    .build((x, y) -> builder.addOutputSlot(x, y));

        if (recipe.getOutput() != null)
            builder.addOutputSlot( 70 + 1, 46 + 1).add(recipe.getOutput().item());

    }

    @Override
    public void draw(CrushingTubRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics, double mouseX,
            double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        if (recipe.getOutput() != null && recipe.getOutput().chance() < 1)
            drawCentredStringFixed(guiGraphics, font,
                    Component.literal(
                            ((int) (recipe.getOutput().chance() * 100)) + "%"),
                    55, 53,
                    ColorUtils.WHITE.getRGB(), true);

    }

}