package com.synergy.vintagetech.compat.jei;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.List;

import com.devdyna.cakesticklib.api.compat.jei.JEIAliasesHelper;
import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.Client;
import com.synergy.vintagetech.init.types.zBlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IIngredientAliasRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;

@JeiPlugin
public class PluginJEI implements IModPlugin {

        @Override
        public Identifier getPluginUid() {
                return x.rl(MODULE_ID, "jei_plugin");
        }

        @Override
        public void registerRecipeCatalysts(IRecipeCatalystRegistration r) {

        }

        @Override
        public void registerCategories(IRecipeCategoryRegistration r) {

        }

        @Override
        public void registerRecipes(IRecipeRegistration r) {

        }

        @Override
        public void registerIngredientAliases(IIngredientAliasRegistration r) {

                JEIAliasesHelper.addAlias(r, List.of(
                                zBlocks.SAW),
                                JEIAliasesHelper.Aliases.BLOCK_BREAKER,
                                JEIAliasesHelper.Aliases.DRILL,
                                JEIAliasesHelper.Aliases.MINER,
                                JEIAliasesHelper.Aliases.TREE_CUTTER,
                                JEIAliasesHelper.Aliases.TREE_FELLER,
                                JEIAliasesHelper.Aliases.ENTITY_KILLER,
                                JEIAliasesHelper.Aliases.FAKEPLAYER);

                JEIAliasesHelper.addAlias(r, List.of(
                                zBlocks.BASKET),
                                JEIAliasesHelper.Aliases.HOPPER,
                                JEIAliasesHelper.Aliases.ITEM_COLLECTOR);

                JEIAliasesHelper.addAlias(r, List.of(
                                zBlocks.FAN),
                                JEIAliasesHelper.Aliases.ENTITY_MOVER);

                JEIAliasesHelper.addAlias(r, List.of(
                                zBlocks.AXLE, zBlocks.GEARSHIFT, zBlocks.JUNCTION),
                                MODULE_ID + ".jei.alias.rpm.transmission");

                JEIAliasesHelper.addAlias(r, List.of(
                                zBlocks.GEARSHIFT),
                                MODULE_ID + ".jei.alias.rpm.rotation.change");

                JEIAliasesHelper.addAlias(r, List.of(
                                zBlocks.CREATIVE_ENGINE, zBlocks.STEAM_ENGINE),
                                MODULE_ID + ".jei.alias.rpm.generator");

                JEIAliasesHelper.addAlias(r, List.of(
                                zBlocks.SAW, zBlocks.GEARSHIFT),
                                JEIAliasesHelper.Aliases.REDSTONE_SENSIBLE);

                JEIAliasesHelper.addAlias(r, List.of(
                                zBlocks.AXLE,
                                zBlocks.GEARSHIFT,
                                zBlocks.JUNCTION,
                                zBlocks.CREATIVE_ENGINE,
                                zBlocks.STEAM_ENGINE,
                                zBlocks.FAN,
                                zBlocks.SAW),
                                MODULE_ID + ".jei.alias.rpm.base");

        }

        @SuppressWarnings("unused")
        private <C extends RecipeInput, T extends Recipe<C>> List<RecipeHolder<T>> getRecipes(RecipeType<T> type) {
                return List.copyOf(Client.getRecipeCollector().byType(type));
        }
        

}
