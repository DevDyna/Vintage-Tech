package com.synergy.vintagetech.init.types;

import static com.synergy.vintagetech.Main.MODULE_ID;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
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
                                        .food(new FoodProperties(1, 0.25f, true))));

        public static final DeferredHolder<Item, Item> CAVE_WHEAT_SEEDS = zItem.registerItem("cave_wheat_seeds",
                        p -> new BlockItem(zBlocks.CAVE_WHEAT.get(), p.useItemDescriptionPrefix()
                                        .food(new FoodProperties(2, 0.25f, true))));

        public static final DeferredHolder<Item, Item> HEMP_SEEDS = zItem.registerItem("hemp_seeds",
                        p -> new BlockItem(zBlocks.HEMP.get(), p.useItemDescriptionPrefix()));

        public static final DeferredHolder<Item, Item> ALOE = zItem.registerItem("aloe",
                        p -> new BlockItem(zBlocks.ALOE_PLANT.get(), p.useItemDescriptionPrefix()));

        public static final DeferredHolder<Item, Item> BLUEBERRIES = zItem.registerItem("blueberries",
                        p -> new BlockItem(zBlocks.BLUEBERRY_BUSH.get(), p.useItemDescriptionPrefix()));

        // plant products

        public static final DeferredHolder<Item, Item> HEMP = zItem.registerSimpleItem("hemp");

        // ingredients

        public static final DeferredHolder<Item, Item> MASHED_SOYBEANS = zItem.registerSimpleItem("mashed_soybeans");
        public static final DeferredHolder<Item, Item> OKARA = zItem.registerSimpleItem("okara");
        public static final DeferredHolder<Item, Item> SOY_RENNET = zItem.registerSimpleItem("soy_rennet");
        public static final DeferredHolder<Item, Item> SOY_DOUGH = zItem.registerSimpleItem("soy_dough");
      
        //TODO IMP : itemtags
        public static final DeferredHolder<Item, Item> TOFU = zItem.registerSimpleItem("tofu",
                        p -> p.food(new FoodProperties(6, 0.75f, true)));

}
