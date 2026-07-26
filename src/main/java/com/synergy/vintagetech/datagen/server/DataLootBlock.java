package com.synergy.vintagetech.datagen.server;

import java.util.*;
import java.util.function.Predicate;

import com.devdyna.cakesticklib.api.utils.EnchantUtil;
import com.devdyna.cakesticklib.api.utils.LootTableHelper;
import com.synergy.vintagetech.init.builder.plants.Aloe;
import com.synergy.vintagetech.init.builder.plants.CaveWheat;
import com.synergy.vintagetech.init.builder.plants.Hemp;
import com.synergy.vintagetech.init.builder.plants.SoyBeans;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zItems;

import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class DataLootBlock extends BlockLootSubProvider {

        public DataLootBlock(HolderLookup.Provider l) {
                super(Set.of(), FeatureFlags.DEFAULT_FLAGS, l);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
                return LootTableHelper.getValidBlocks(zBlocks.zBlock, zBlocks.zBlockItem);
        }

        List<Block> BLACKLIST = List.of(zBlocks.IRONWOOD_LEAVES.get(),zBlocks.IRONWOOD_SLAB.get());

        @Override
        protected void generate() {

                LootTableHelper.getValidBlocks(zBlocks.zBlockItem)
                .stream().filter(Predicate.not(BLACKLIST::contains)).forEach(this::dropSelf);

                add(zBlocks.CAVE_WHEAT.get(), createCropDrops(zBlocks.CAVE_WHEAT.get(),
                                Items.WHEAT, zItems.CAVE_WHEAT_SEEDS.get(),
                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(zBlocks.CAVE_WHEAT.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                .hasProperty(CaveWheat.AGE, CaveWheat.MAX_AGE))));

                add(zBlocks.SOYBEANS.get(), createCropDrops(zBlocks.SOYBEANS.get(),
                                zItems.SOYBEANS.get(), zItems.SOYBEANS.get(),
                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(zBlocks.SOYBEANS.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                .hasProperty(SoyBeans.AGE, SoyBeans.MAX_AGE))));

                add(zBlocks.HEMP.get(), createCropDrops(zBlocks.HEMP.get(),
                                zItems.HEMP.get(), zItems.HEMP_SEEDS.get(),
                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(zBlocks.HEMP.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                .hasProperty(Hemp.AGE, Hemp.MAX_AGE))));

                add(zBlocks.ALOE_PLANT.get(), createCropDrops(zBlocks.ALOE_PLANT.get(),
                                zItems.ALOE.get(), zItems.ALOE.get(),
                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(zBlocks.ALOE_PLANT.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                .hasProperty(Aloe.AGE, Aloe.MAX_AGE))));

                add(zBlocks.BLUEBERRY_BUSH.get(), createCropDrops(zBlocks.BLUEBERRY_BUSH.get(),
                                zItems.BLUEBERRIES.get(), zItems.BLUEBERRIES.get(),
                                LootItemBlockStatePropertyCondition
                                                .hasBlockStateProperties(zBlocks.BLUEBERRY_BUSH.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                .hasProperty(Hemp.AGE, Hemp.MAX_AGE))
                                                .or(LootItemBlockStatePropertyCondition
                                                                .hasBlockStateProperties(zBlocks.BLUEBERRY_BUSH.get())
                                                                .setProperties(StatePropertiesPredicate.Builder
                                                                                .properties()
                                                                                .hasProperty(Hemp.AGE,
                                                                                                Hemp.MAX_AGE - 1)))));

                add(zBlocks.IRONWOOD_LEAVES.get(),
                                b -> createLeavesDrops(b, zBlocks.IRONWOOD_SAPLING.get(),
                                                NORMAL_LEAVES_SAPLING_CHANCES)
                                                .withPool(
                                                                LootPool.lootPool()
                                                                                .setRolls(ConstantValue.exactly(1.0F))
                                                                                .when(
                                                                                                hasShears().or(hasSilkTouch())
                                                                                                                .invert())
                                                                                .add(
                                                                                                applyExplosionCondition(
                                                                                                                b,
                                                                                                                LootItem.lootTableItem(
                                                                                                                                zItems.IRONBERRIES
                                                                                                                                                .get()))
                                                                                                                .when(
                                                                                                                                BonusLevelTableCondition
                                                                                                                                                .bonusLevelFlatChance(
                                                                                                                                                                EnchantUtil.getEnchantHolder(
                                                                                                                                                                                registries,
                                                                                                                                                                                Enchantments.FORTUNE),
                                                                                                                                                                0.05F,
                                                                                                                                                                0.055555557F,
                                                                                                                                                                0.0625F,
                                                                                                                                                                0.08333334F,
                                                                                                                                                                0.25F))))

                );

                add(zBlocks.IRONWOOD_SLAB.get(), b -> createSlabItemTable(b));

        }

}
