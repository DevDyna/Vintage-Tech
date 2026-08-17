package com.synergy.vintagetech.init.types;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.function.Function;

import com.synergy.vintagetech.api.EffectConsumableItem;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zItems {

        public static void register(IEventBus bus) {
                zItem.register(bus);
                zBucketItems.register(bus);
                zBlockItem.register(bus);
        }

        public static final DeferredRegister.Items zItem = DeferredRegister.createItems(MODULE_ID);
        public static final DeferredRegister.Items zBucketItems = DeferredRegister.createItems(MODULE_ID);
        public static final DeferredRegister.Items zBlockItem = DeferredRegister.createItems(MODULE_ID);

        // seeds
        public static final DeferredHolder<Item, Item> SOYBEANS = zItem.registerItem("soybeans",
                        p -> new BlockItem(zBlocks.SOYBEANS.get(), p.useItemDescriptionPrefix()
                                        .food(new FoodProperties(1, 5f, true))));

        public static final DeferredHolder<Item, Item> CAVE_WHEAT_SEEDS = zItem.registerItem("cave_wheat_seeds",
                        p -> new BlockItem(zBlocks.CAVE_WHEAT.get(), p.useItemDescriptionPrefix()
                                        .food(new FoodProperties(2, 3f, true))));

        public static final DeferredHolder<Item, Item> HEMP_SEEDS = zItem.registerItem("hemp_seeds",
                        p -> new BlockItem(zBlocks.HEMP.get(), p.useItemDescriptionPrefix()));

        public static final DeferredHolder<Item, Item> ALOE = zItem.registerItem("aloe",
                        p -> new BlockItem(zBlocks.ALOE_PLANT.get(), p.useItemDescriptionPrefix()));

        public static final DeferredHolder<Item, Item> BLUEBERRIES = zItem.registerItem("blueberries",
                        p -> new BlockItem(zBlocks.BLUEBERRY_BUSH.get(),
                                        p.useItemDescriptionPrefix().food(new FoodProperties(2, 1f, true))));

        // plant products

        public static final DeferredHolder<Item, Item> HEMP = zItem.registerSimpleItem("hemp");

        // ingredients

        public static final DeferredHolder<Item, Item> MASHED_SOYBEANS = zItem.registerSimpleItem("mashed_soybeans");
        public static final DeferredHolder<Item, Item> OKARA = zItem.registerSimpleItem("okara");
        public static final DeferredHolder<Item, Item> SOY_RENNET = zItem.registerSimpleItem("soy_rennet");
        public static final DeferredHolder<Item, Item> SOY_DOUGH = zItem.registerSimpleItem("soy_dough");

        public static final DeferredHolder<Item, Item> TOFU = zItem.registerSimpleItem("tofu",
                        p -> p.food(new FoodProperties(6, 7f, true)));

        public static final DeferredHolder<Item, Item> CLOTH = zItem.registerSimpleItem("cloth");
        public static final DeferredHolder<Item, Item> SALT = foodEffectsItems("salt",
                        new MobEffectInstance(MobEffects.HUNGER, 40, 3), 0, 0f, true, p -> p);

        public static final DeferredHolder<Item, Item> AMBER = zItem.registerSimpleItem("amber");
        public static final DeferredHolder<Item, Item> SAP = zItem.registerSimpleItem("sap");
        public static final DeferredHolder<Item, Item> GLUE = zItem.registerSimpleItem("glue");
        public static final DeferredHolder<Item, Item> BITUMEN = zItem.registerSimpleItem("bitumen");

        public static final DeferredHolder<Item, Item> HEMP_FIBER = zItem.registerSimpleItem("hemp_fiber");

        public static final DeferredHolder<Item, Item> IRONBERRIES = foodEffectsItems("ironberries",
                        new MobEffectInstance(MobEffects.HUNGER, 20, 1), 1, 2f, true, p -> p);

        public static final DeferredHolder<Item, Item> TINY_IRON_DUST = zItem.registerSimpleItem("tiny_iron_dust");

        public static final DeferredHolder<Item, Item> BLUEBERRIES_SOUP = zItem.registerSimpleItem("blueberries_soup",
                        p -> p
                                        .stacksTo(16)
                                        .usingConvertsTo(Items.BOWL)
                                        .craftRemainder(Items.BOWL)
                                        .food(new FoodProperties(12, 6f, true)));

        public static final DeferredHolder<Item, Item> BLUEBERRIES_MUFFIN = zItem.registerSimpleItem(
                        "blueberries_muffin",
                        p -> p
                                        .food(new FoodProperties(6, 6f, true)));

        public static final DeferredHolder<Item, Item> SWEET_BERRIES_SOUP = zItem.registerSimpleItem(
                        "sweet_berries_soup",
                        p -> p
                                        .stacksTo(16)
                                        .usingConvertsTo(Items.BOWL)
                                        .craftRemainder(Items.BOWL)
                                        .food(new FoodProperties(12, 10f, true)));

        public static final DeferredHolder<Item, Item> GLOW_BERRIES_SOUP = zItem.registerSimpleItem("glow_berries_soup",
                        p -> p
                                        .stacksTo(16)
                                        .usingConvertsTo(Items.BOWL)
                                        .craftRemainder(Items.BOWL)
                                        .food(new FoodProperties(12, 8f, true)));

        public static final DeferredHolder<Item, Item> IRONBERRIES_SOUP = zItem.registerSimpleItem("ironberries_soup",
                        p -> p
                                        .stacksTo(16)
                                        .usingConvertsTo(Items.BOWL)
                                        .craftRemainder(Items.BOWL)
                                        .food(new FoodProperties(12, 4f, true)));

        public static final DeferredHolder<Item, Item> MESH = zItem.registerSimpleItem("mesh", p -> p.stacksTo(16));
        // public static final DeferredHolder<Item, Item> CHEESE_MOLD =
        // zItem.registerSimpleItem("cheese_mold",
        // p -> p.stacksTo(16));

        public static final DeferredHolder<Item, Item> FRESH_CHEESE_SLICE = zItem.registerSimpleItem(
                        "fresh_cheese_slice",
                        p -> p
                                        .stacksTo(16)
                                        .food(new FoodProperties(6, 4f, false)));

        public static final DeferredHolder<Item, Item> MATURED_CHEESE_SLICE = zItem.registerSimpleItem(
                        "matured_cheese_slice",
                        p -> p
                                        .stacksTo(16)
                                        .food(new FoodProperties(9, 7f, false)));

        public static final DeferredHolder<Item, Item> AGED_CHEESE_SLICE = zItem.registerSimpleItem(
                        "aged_cheese_slice",
                        p -> p
                                        .stacksTo(16)
                                        .food(new FoodProperties(12, 10f, false)));

        public static final DeferredHolder<Item, Item> CONDENSED_MILK = zItem.registerSimpleItem("condensed_milk",
                        p -> p);
        public static final DeferredHolder<Item, Item> MILK_CURD = zItem.registerSimpleItem("milk_curd", p -> p);

        public static final DeferredHolder<Item, Item> CHEESE_CURD = zItem.registerSimpleItem("cheese_curd", p -> p);
        public static final DeferredHolder<Item, Item> MOZZARELLA_CURD = zItem.registerSimpleItem("mozzarella_curd",
                        p -> p);
        public static final DeferredHolder<Item, Item> MOZZARELLA = foodEffectsItems("mozzarella",
                        new MobEffectInstance(MobEffects.REGENERATION, 50, 1), 6, 10, false, p -> p);

        public static DeferredHolder<Item, Item> foodEffectsItems(String id, MobEffectInstance effect, int n,
                        float s,
                        Function<Properties, Properties> p) {
                return foodEffectsItems(id, effect, n, s, false, p);
        }

        public static DeferredHolder<Item, Item> foodEffectsItems(String id, MobEffectInstance effect, int n,
                        float s, boolean e,
                        Function<Properties, Properties> p) {
                return zItem.registerItem(id, pr -> new EffectConsumableItem(
                                p.apply(pr).food(new FoodProperties(n, s, e),
                                                Consumable.builder()
                                                                .onConsume(new ApplyStatusEffectsConsumeEffect(effect))
                                                                .build()),
                                effect));
        }

}
