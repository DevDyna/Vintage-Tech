package com.synergy.vintagetech.compat.jei;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.Arrays;
import java.util.List;

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
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;

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

                alias(r, List.of(
                                zBlocks.SAW),
                                MODULE_ID + ".jei.alias.block.break",
                                MODULE_ID + ".jei.alias.drill",
                                MODULE_ID + ".jei.alias.miner",
                                MODULE_ID + ".jei.alias.tree.cutter",
                                MODULE_ID + ".jei.alias.tree.feller",
                                MODULE_ID + ".jei.alias.entity.killer",
                                MODULE_ID + ".jei.alias.fakeplayer");

                alias(r, List.of(
                                zBlocks.BASKET),
                                MODULE_ID + ".jei.alias.hopper",
                                MODULE_ID + ".jei.alias.item.collector");

                alias(r, List.of(
                                zBlocks.FAN),
                                MODULE_ID + ".jei.alias.entity.mover");

                alias(r, List.of(
                                zBlocks.AXLE, zBlocks.GEARSHIFT, zBlocks.JUNCTION),
                                MODULE_ID + ".jei.alias.rpm.transmission");

                alias(r, List.of(
                                zBlocks.GEARSHIFT),
                                MODULE_ID + ".jei.alias.rpm.rotation.change");

                alias(r, List.of(
                                zBlocks.CREATIVE_ENGINE, zBlocks.STEAM_ENGINE),
                                MODULE_ID + ".jei.alias.rpm.generator");

                alias(r, List.of(
                                zBlocks.SAW, zBlocks.GEARSHIFT),
                                MODULE_ID + ".jei.alias.redstone.sensible");

                alias(r, List.of(
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

        private void alias(IIngredientAliasRegistration r,
                        List<DeferredHolder<? extends ItemLike, ? extends ItemLike>> i,
                        String... alias) {

                i.stream()
                                .map(DeferredHolder::get).map(x::item)
                                .forEach(e -> Arrays.asList(alias).forEach(a -> r.addAlias(e, a)));

        }

}
