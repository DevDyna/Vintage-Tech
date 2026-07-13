package com.synergy.vintagetech.compat.jei.categories;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.compat.jei.BaseRecipeCategory;
import com.devdyna.cakesticklib.api.compat.jei.JEIFluidTankHelper;
import com.devdyna.cakesticklib.api.primitive.Size;
import com.devdyna.cakesticklib.api.utils.ColorUtils;
import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.init.builder.centrifuge.recipe.CentrifugeRecipe;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

public class CentrifugeCategory extends BaseRecipeCategory<CentrifugeRecipe> {

    public CentrifugeCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final IRecipeType<RecipeHolder<CentrifugeRecipe>> TYPE = IRecipeType
            .create(zRecipeTypes.CENTRIFUGE.getType());

    @Override
    public IRecipeType<RecipeHolder<CentrifugeRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTraslationKey() {
        return MODULE_ID + ".jei.centrifuge";
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.CENTRIFUGE.get();
    }

    @Override
    public Identifier setBackGround() {
        return x.rl(MODULE_ID, "textures/gui/jei/centrifuge.png");
    }

    @Override
    public Size setXY() {
        return Size.of(83, 57);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CentrifugeRecipe recipe, IFocusGroup focuses) {

        JEIFluidTankHelper.of()
                .fluid(recipe.getInputFluid())
                .offset(2, 35 + 1)
                .scale(2.0f, 1.0f)
                .build((x, y) -> builder.addInputSlot(x, y));

        JEIFluidTankHelper.of()
                .fluid(recipe.getOutputFluid().create())
                .offset(65, 35 + 1)
                .scale(2.0f, 1.0f)
                .build((x, y) -> builder.addOutputSlot(x, y));

        builder.addInputSlot(2, 39).addItemStacks(x.getItemStacksFromIngredient(recipe.getCatalyst()));
        // TODO IMP : centrifuge output
        builder.addOutputSlot(65, 39).addItemStacks(x.getItemStacksFromIngredient(recipe.getCatalyst()));
    }

    @Override
    public boolean enableTimerRender() {
        return true;
    }

    @Override
    public int tickValue(CentrifugeRecipe recipe) {
        return recipe.getTicks();
    }

    @Override
    public Size tickPos() {
        return Size.of(26, 42);
    }

    @Override
    public int tickColor() {
        return ColorUtils.WHITE.getRGB();
    }

}