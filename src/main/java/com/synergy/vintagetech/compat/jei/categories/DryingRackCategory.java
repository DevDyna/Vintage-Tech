package com.synergy.vintagetech.compat.jei.categories;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.compat.jei.BaseRecipeCategory;
import com.devdyna.cakesticklib.api.primitive.Size;
import com.devdyna.cakesticklib.api.utils.ColorUtils;
import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.init.builder.drying_rack.recipe.DryingRackRecipe;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

public class DryingRackCategory extends BaseRecipeCategory<DryingRackRecipe> {

    public DryingRackCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final IRecipeType<RecipeHolder<DryingRackRecipe>> TYPE = IRecipeType
            .create(zRecipeTypes.DRYING_RACK.getType());

    @Override
    public IRecipeType<RecipeHolder<DryingRackRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTraslationKey() {
        return MODULE_ID + ".jei.drying_rack";
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.DRYING_RACK.get();
    }

    @Override
    public Identifier setBackGround() {
        return x.rl(MODULE_ID, "textures/gui/jei/drying_rack.png");
    }

    @Override
    public Size setXY() {
        return Size.of(69, 38);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DryingRackRecipe recipe, IFocusGroup focuses) {
        builder.addInputSlot( 3 , 10).add(recipe.getInput());
        builder.addOutputSlot( 50, 10).add(recipe.getOutput());
    }

    @Override
    public boolean enableTimerRender() {
        return true;
    }

    @Override
    public int tickValue(DryingRackRecipe recipe) {
        return recipe.getTicks();
    }

    @Override
    public Size tickPos() {
        return Size.of(19, 29);
    }

    @Override
    public int tickColor() {
        return ColorUtils.WHITE.getRGB();
    }

}