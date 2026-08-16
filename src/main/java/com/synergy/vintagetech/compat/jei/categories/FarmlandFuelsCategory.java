package com.synergy.vintagetech.compat.jei.categories;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.compat.jei.BaseRecipeCategory;
import com.devdyna.cakesticklib.api.compat.jei.JEIFluidTankHelper;
import com.devdyna.cakesticklib.api.primitive.Size;
import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.init.builder.mechanical_farmland.recipe.FarmlandFuelsRecipe;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

public class FarmlandFuelsCategory extends BaseRecipeCategory<FarmlandFuelsRecipe> {

    public FarmlandFuelsCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final IRecipeType<RecipeHolder<FarmlandFuelsRecipe>> TYPE = IRecipeType
            .create(zRecipeTypes.FARMLAND_FUELS.getType());

    @Override
    public IRecipeType<RecipeHolder<FarmlandFuelsRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTraslationKey() {
        return MODULE_ID + ".jei.farmland_fuels";
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.MECHANICAL_FARMLAND.get();
    }

    @Override
    public Identifier setBackGround() {
        return x.rl(MODULE_ID, "textures/gui/jei/farmland_fuels.png");
    }

    @Override
    public Size setXY() {
        return Size.of(36, 36);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FarmlandFuelsRecipe recipe, IFocusGroup focuses) {

        JEIFluidTankHelper.of()
                .fluid(recipe.getFluid())
                .offset(10, 18+13-2)
                .scale(1.0f, 1.0f)
                .build((x, y) -> builder.addInputSlot(x, y));

    }

    // @Override
    // public boolean enableTimerRender() {
    //     return false;
    // }

}