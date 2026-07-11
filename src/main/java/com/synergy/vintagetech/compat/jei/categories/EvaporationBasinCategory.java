package com.synergy.vintagetech.compat.jei.categories;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.compat.jei.BaseRecipeCategory;
import com.devdyna.cakesticklib.api.compat.jei.JEIFluidTankHelper;
import com.devdyna.cakesticklib.api.primitive.Size;
import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.init.builder.evaporation_basin.recipe.EvaporationBasinRecipe;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

public class EvaporationBasinCategory extends BaseRecipeCategory<EvaporationBasinRecipe> {

    public EvaporationBasinCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final IRecipeType<RecipeHolder<EvaporationBasinRecipe>> TYPE = IRecipeType
            .create(zRecipeTypes.EVAPORATION_BASIN.getType());

    @Override
    public IRecipeType<RecipeHolder<EvaporationBasinRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTraslationKey() {
        return MODULE_ID + ".jei.evaporation_basin";
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.EVAPORATION_BASIN.get();
    }

    @Override
    public Identifier setBackGround() {
        return x.rl(MODULE_ID, "textures/gui/jei/evaporation_basin.png");
    }

    @Override
    public Size setXY() {
        return Size.of(108, 64);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, EvaporationBasinRecipe recipe, IFocusGroup focuses) {

        JEIFluidTankHelper.of()
                .fluid(recipe.getFluid())
                .offset(21, 49)
                .scale(2.0f, 1.0f)
                .build((x, y) -> builder.addInputSlot(x, y));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 71 + 1, 28 + 1).add(recipe.getOutput());
    }

    @Override
    public boolean enableTimerRender() {
        return true;
    }

    @Override
    public int tickValue(EvaporationBasinRecipe recipe) {
        return recipe.getTicks();
    }

    @Override
    public Size tickPos() {
        return Size.of(21, 53);
    }

}