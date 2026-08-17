package com.synergy.vintagetech.compat.jei.categories;

import static com.synergy.vintagetech.Main.MODULE_ID;

import org.jspecify.annotations.Nullable;

import com.devdyna.cakesticklib.api.compat.jei.BaseCategory;
import com.devdyna.cakesticklib.api.primitive.Size;
import com.devdyna.cakesticklib.api.utils.x;
import com.devdyna.cakesticklib.setup.registry.LibItems;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zItems;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.types.IRecipeType;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.ItemLike;

public class CheeseCategory extends BaseCategory<CheeseCategory.CheesePage> {

    public static final IRecipeType<CheesePage> TYPE = IRecipeType.create(
            x.rl(MODULE_ID, "cheese"), CheesePage.class);

    public CheeseCategory(IGuiHelper helper) {
        super(helper);
    }

    @Override
    public IRecipeType<CheesePage> getRecipeType() {
        return TYPE;
    }

    @Override
    public String getTraslationKey() {
        return MODULE_ID + ".jei.cheese";
    }

    @Override
    public ItemLike getIconItem() {
        return zBlocks.FRESH_CHEESE.get();
    }

    // TODO API : add a warning when null , it collapse everything!
    @Override
    public Identifier setBackGround() {
        return x.rl(MODULE_ID, "");
    }

    @Override
    public Size setXY() {
        return Size.of(122, 70);
    }

    private static final int LEFT = 10 - 1;
    private static final int MIDDLE = 50 - 1 - 2 - 1;
    private static final int RIGHT = 90 + 1;
    private static final int Y = 28;

    @SuppressWarnings("deprecation")
    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CheesePage page, IFocusGroup focuses) {
        switch (page.type()) {
            case SEALING -> {
                builder.addInputSlot(LEFT, Y)
                        .add(zBlocks.FRESH_CHEESE.get())
                        .add(zBlocks.MATURED_CHEESE.get());

                builder.addInputSlot(MIDDLE, Y)
                        .add(LibItems.HONEY_SOLUTION.get());

                builder.addOutputSlot(RIGHT, Y)
                        .add(zBlocks.SEALED_CHEESE.get());
            }

            case AGING -> {
                builder.addInputSlot(LEFT, Y)
                        .add(zBlocks.SEALED_CHEESE.get());

                builder.addOutputSlot(RIGHT, Y)
                        .add(zBlocks.MATURED_CHEESE.get())
                        .add(zBlocks.AGED_CHEESE.get());
            }

            case UNSEALING -> {
                builder.addInputSlot(LEFT, Y)
                        .add(zBlocks.SEALED_CHEESE.get());

                builder.addInputSlot(MIDDLE, Y)
                        .add(x.itemIngredient(ItemTags.AXES));

                builder.addOutputSlot(RIGHT, Y)
                        .add(zBlocks.FRESH_CHEESE.get())
                        .add(zBlocks.MATURED_CHEESE.get())
                        .add(zBlocks.AGED_CHEESE.get());
            }

            case SLICING -> {
                builder.addInputSlot(LEFT, Y)
                        .add(zBlocks.FRESH_CHEESE.get())
                        .add(zBlocks.MATURED_CHEESE.get())
                        .add(zBlocks.AGED_CHEESE.get());

                builder.addOutputSlot(RIGHT, Y)
                        .add(zItems.FRESH_CHEESE_SLICE.get())
                        .add(zItems.MATURED_CHEESE_SLICE.get())
                        .add(zItems.AGED_CHEESE_SLICE.get());
            }
        }
    }

    @Override
    public void draw(CheesePage page, IRecipeSlotsView slots, GuiGraphicsExtractor graphics, double mouseX,
            double mouseY) {

        switch (page.type()) {
            case SEALING ->
                backgroundImage
                        .rl(x.rl(MODULE_ID, "textures/gui/jei/cheese/use.png"))
                        .render(helper, graphics);

            case AGING -> {

                backgroundImage
                        .rl(x.rl(MODULE_ID, "textures/gui/jei/cheese/aging.png"))
                        .render(helper, graphics);

            }

            case UNSEALING -> backgroundImage
                    .rl(x.rl(MODULE_ID, "textures/gui/jei/cheese/use.png"))
                    .render(helper, graphics);

            case SLICING -> backgroundImage
                    .rl(x.rl(MODULE_ID, "textures/gui/jei/cheese/slicing.png"))
                    .render(helper, graphics);
        }

        var stack = graphics.pose();

        stack.pushMatrix();

        stack.scale(0.75f);

        if (page.type() == CheesePageType.AGING)
            drawCenteredString(graphics, font,
                    Component.translatable(MODULE_ID + ".jei.cheese.aging.condition"),
                    61 + 10 + 10, 58 + 10,
                    0xFF404040, false);

        drawCenteredString(graphics, font,
                Component.translatable(MODULE_ID + ".jei.cheese." + page.getId()),
                61 + 10 + 10, 5 + 10 - ((page.type() == CheesePageType.AGING) ? 10 : 0),
                0xFF404040, false);

        stack.popMatrix();

    }

    @Override
    public @Nullable Identifier getIdentifier(CheesePage recipe) {
        return x.rl(MODULE_ID,recipe.getId());
    }

    public record CheesePage(CheesePageType type) {
        public String getId() {
            return type.id;
        }
    }

    public enum CheesePageType {
        SEALING("sealing"),
        AGING("aging"),
        UNSEALING("unsealing"),
        SLICING("slicing");

        private String id;

        private CheesePageType(String id) {
            this.id = id;
        }

    }
}