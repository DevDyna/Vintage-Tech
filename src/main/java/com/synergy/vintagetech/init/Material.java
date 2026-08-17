package com.synergy.vintagetech.init;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.api.EffectConsumableItem;
import com.synergy.vintagetech.init.types.*;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;

public class Material {
        public static void register(IEventBus bus) {
                zItems.register(bus);
                zBlocks.register(bus);
                zBlockEntities.register(bus);
                zRecipeTypes.register(bus);
                zTags.register(bus);
                zParticles.register(bus);
                zFluids.register(bus);
                zCreativeTab.register(bus);
                zWorldGenFeatures.register(bus);
        }

        /**
         * Create a simple blockitem
         */
        public static DeferredHolder<Block, Block> registerItemBlock(String id,
                        Function<Properties, ? extends Block> p) {
                DeferredHolder<Block, Block> block = zBlocks.zBlockItem.registerBlock(id, p);
                zItems.zBlockItem.registerSimpleBlockItem(block);
                return block;
        }

        /**
         * Create a blockitem from {@code ResourceKey<Block>}
         * <br/>
         * <br/>
         * Useful when you need to use things like {@code Properties.ofFullCopy(<?>)}
         */
        public static DeferredHolder<Block, Block> registerItemBlock(String id,
                        BiFunction<Properties, ResourceKey<Block>, ? extends Block> p) {
                return registerItemBlock(id,
                                pr -> p.apply(pr, ResourceKey.create(Registries.BLOCK, x.rl(MODULE_ID, id))));
        }

        /**
         * Create a Block without an item related<br/>
         * <br/>
         * Mainly used to render debug blocks that cannot be broken or used entirely
         */
        public static DeferredHolder<Block, Block> renderBlock(String id) {
                return zBlocks.zRender.registerSimpleBlock(id, p -> p.noLootTable());
        }

        @Deprecated
        public static DeferredHolder<Item, Item> registerFoodEffectItem(String id, MobEffectInstance effect,
                        int n,
                        float s, boolean e,
                        Function<Item.Properties, Item.Properties> p) {
                return registerFoodEffectItem(id, p, new FoodProperties(n, s, e), Map.of(List.of(effect), 1f));
        }

        /**
         * One effect
         */
        public static DeferredHolder<Item, Item> registerFoodEffectItem(
                        String id,
                        Function<Item.Properties, Item.Properties> function,
                        FoodProperties food,
                        MobEffectInstance effect,
                        float chance) {

                return registerFoodEffectItem(
                                id,
                                function,
                                food,
                                List.of(effect), chance);
        }

        /**
         * One effect and 100% success
         */
        public static DeferredHolder<Item, Item> registerFoodEffectItem(
                        String id,
                        Function<Item.Properties, Item.Properties> function,
                        FoodProperties food,
                        MobEffectInstance effect) {

                return registerFoodEffectItem(
                                id,
                                function,
                                food,
                                effect, 1f);
        }

        /**
         * Every effect has same
         * chance
         */
        public static DeferredHolder<Item, Item> registerFoodEffectItem(
                        String id,
                        Function<Item.Properties, Item.Properties> function,
                        FoodProperties food,
                        List<MobEffectInstance> effects,
                        float chance) {

                return registerFoodEffectItem(
                                id,
                                function,
                                food,
                                Map.of(effects, chance));
        }

        /**
         * Create an food item with a dedicated tooltip to show mobEffects
         */
        public static DeferredHolder<Item, Item> registerFoodEffectItem(String id,
                        Function<Item.Properties, Item.Properties> function,
                        FoodProperties food,
                        Map<List<MobEffectInstance>, Float> effect) {

                var builder = Consumable.builder();

                effect.forEach((r, c) -> builder.onConsume(new ApplyStatusEffectsConsumeEffect(r, c)));

                var list = new ArrayList<MobEffectInstance>();

                effect.forEach((r, c) -> r.forEach(list::add));

                return zItems.zItem.registerItem(id,
                                p -> new EffectConsumableItem(function.apply(p)
                                                .food(food, builder.build()), list));
        }

}
