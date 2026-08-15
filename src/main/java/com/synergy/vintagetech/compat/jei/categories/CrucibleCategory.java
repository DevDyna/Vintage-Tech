package com.synergy.vintagetech.compat.jei.categories;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.compat.jei.BaseRecipeCategory;
import com.devdyna.cakesticklib.api.compat.jei.JEIFluidTankHelper;
import com.devdyna.cakesticklib.api.primitive.Size;
import com.devdyna.cakesticklib.api.utils.ColorUtils;
import com.devdyna.cakesticklib.api.utils.TimeUtil;
import com.devdyna.cakesticklib.api.utils.x;

import com.synergy.vintagetech.init.builder.crucible.recipe.CrucibleRecipe;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zRecipeTypes;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.types.IRecipeType;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

public class CrucibleCategory extends BaseRecipeCategory<CrucibleRecipe> {

    public CrucibleCategory(IGuiHelper helper) {
        super(helper);
    }

    public static final IRecipeType<RecipeHolder<CrucibleRecipe>> TYPE = IRecipeType
            .create(zRecipeTypes.CRUCIBLE.getType());

    @Override
    public IRecipeType<RecipeHolder<CrucibleRecipe>> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTraslationKey() {
        return MODULE_ID + ".jei.crucible";
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.CRUCIBLE.get();
    }

    @Override
    public Identifier setBackGround() {
        return x.rl(MODULE_ID, "textures/gui/jei/crucible.png");
    }

    @Override
    public Size setXY() {
        return Size.of(172, 66);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CrucibleRecipe recipe, IFocusGroup focuses) {

        JEIFluidTankHelper.of()
                .fluid(recipe.getInputFluid())
                .offset(43, 49 + 1)
                .scale(2.0f, 1.0f)
                .build((x, y) -> builder.addInputSlot(x, y));

        if (recipe.getOutputFluid() != null)
            JEIFluidTankHelper.of()
                    .fluid(recipe.getOutputFluid().create())
                    .offset(149, 53 - 3 + 4)
                    .scale(2.5f, 1.0f)
                    .build((x, y) -> builder.addOutputSlot(x, y));

        for (int i = 0; i < recipe.getItemInputs().size() && i < 4; i++) {

            int col = i % 2;
            int row = i / 2;

            builder.addInputSlot(
                    7 + col * 18,
                    16 + row * 18)
                    .addItemStacks(x.getItemStacksFromIngredient(recipe.getItemInputs().get(i).ingredient()));
        }

        for (int i = 0; i < recipe.getOutputItems().size() && i < 4; i++) {

            int col = i % 2;
            int row = i / 2;

            builder.addOutputSlot(
                    113 + col * 18,
                    11 + row * 22).add(recipe.getOutputItems().get(i).item());
        }

    }

    @Override
    public void draw(CrucibleRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics,
            double mouseX, double mouseY) {

        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        var stack = guiGraphics.pose();

        stack.pushMatrix();
        stack.scale(0.6F, 0.6F);

        for (int i = 0; i < recipe.getOutputItems().size() && i < 4; i++) {

            int col = i % 2;
            int row = i / 2;

            var value = ((int) (recipe.getOutputItems().get(i).chance() * 100));

            if (value < 100)
                guiGraphics.text(font,
                        Component.literal(value + "%"),
                        16 + 16 + 16 + 8 + 123 + 8 + 4 + 1 + col * (18 + 8 + 4),
                        48 - 16 - 8 - 4 + 31 - 6 + 1 + 1 + row * (22 + 8 + 6), ColorUtils.WHITE.getRGB(), true);

        }

        stack.popMatrix();

    }

    @Override
    public boolean enableTimerRender() {
        return true;
    }

    @Override
    public int tickValue(CrucibleRecipe recipe) {
        return recipe.getTicks();
    }

    @Override
    public Size tickPos() {
        return Size.of(78 - 8-4, 13);
    }

    @Override
    public void renderTickDelay(CrucibleRecipe recipe, GuiGraphicsExtractor guiGraphics) {
        guiGraphics.text(this.font, Component.literal(TimeUtil.getTimeValue(this.tickValue(recipe), this.shortTicks())),
                this.tickPos().getX() - (recipe.getTicks() > 9 ? 5+4 : 0), this.tickPos().getY(), this.tickColor());
    }

    @Override
    public int tickColor() {
        return ColorUtils.WHITE.getRGB();
    }
}