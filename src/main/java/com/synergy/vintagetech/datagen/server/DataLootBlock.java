package com.synergy.vintagetech.datagen.server;

import java.util.*;
import java.util.function.Predicate;

import com.devdyna.cakesticklib.api.utils.EnchantUtil;
import com.devdyna.cakesticklib.api.utils.LootTableHelper;
import com.synergy.vintagetech.api.blockfactory.cheese.BaseCheeseBlock;
import com.synergy.vintagetech.init.builder.cheese.SealedCheeseBlock;
import com.synergy.vintagetech.init.builder.crushing_tub.CrushingTubBlock;
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
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class DataLootBlock extends BlockLootSubProvider {

        public DataLootBlock(HolderLookup.Provider l) {
                super(Set.of(), FeatureFlags.DEFAULT_FLAGS, l);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
                return LootTableHelper.getValidBlocks(zBlocks.zBlock, zBlocks.zBlockItem);
        }

        List<Block> BLACKLIST = List.of(zBlocks.IRONWOOD_LEAVES.get(), zBlocks.IRONWOOD_SLAB.get(),
                        zBlocks.CRUSHING_TUB.get());

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
                                                                                .setRolls(UniformGenerator.between(1,
                                                                                                5))
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
                                                                                                                                                                0.25F,
                                                                                                                                                                0.275F,
                                                                                                                                                                0.3F,
                                                                                                                                                                0.325F))))

                );

                dropPottedContents(zBlocks.POTTED_IRONWOOD_SAPLING.get());
                add(zBlocks.IRONWOOD_SLAB.get(), b -> createSlabItemTable(b));

                add(zBlocks.CRUSHING_TUB.get(),
                                LootTable.lootTable()

                                                .withPool(
                                                                LootPool.lootPool()
                                                                                .setRolls(ConstantValue.exactly(1))
                                                                                .add(LootItem.lootTableItem(
                                                                                                zBlocks.CRUSHING_TUB
                                                                                                                .get())))

                                                .withPool(
                                                                LootPool.lootPool()
                                                                                .setRolls(ConstantValue.exactly(1))
                                                                                .add(LootItem.lootTableItem(
                                                                                                zItems.MESH.get()))
                                                                                .when(
                                                                                                LootItemBlockStatePropertyCondition
                                                                                                                .hasBlockStateProperties(
                                                                                                                                zBlocks.CRUSHING_TUB
                                                                                                                                                .get())
                                                                                                                .setProperties(
                                                                                                                                StatePropertiesPredicate.Builder
                                                                                                                                                .properties()
                                                                                                                                                .hasProperty(CrushingTubBlock.MESH,
                                                                                                                                                                true)))));

                addCheese(zBlocks.FRESH_CHEESE.get(), zItems.FRESH_CHEESE_SLICE.get());
                addCheese(zBlocks.MATURED_CHEESE.get(), zItems.MATURED_CHEESE_SLICE.get());
                addCheese(zBlocks.AGED_CHEESE.get(), zItems.AGED_CHEESE_SLICE.get());
                addAgedCheese(zBlocks.SEALED_CHEESE.get(), zBlocks.MATURED_CHEESE.get(), zBlocks.AGED_CHEESE.get(),
                                zItems.FRESH_CHEESE_SLICE.get(),
                                zItems.MATURED_CHEESE_SLICE.get(), zItems.AGED_CHEESE_SLICE.get());
        }

        private void addCheese(Block block, ItemLike slice) {
                var table = LootTable.lootTable();

                table.withPool(
                                LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1))
                                                .add(LootItem.lootTableItem(block))
                                                .when(hasSilkTouch())
                                                .when(LootItemBlockStatePropertyCondition
                                                                .hasBlockStateProperties(block)
                                                                .setProperties(
                                                                                StatePropertiesPredicate.Builder
                                                                                                .properties()
                                                                                                .hasProperty(
                                                                                                                BaseCheeseBlock.PIECES,
                                                                                                                4))));

                table.withPool(
                                LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1))
                                                .add(LootItem.lootTableItem(block))
                                                .when(hasSilkTouch())
                                                .when(LootItemBlockStatePropertyCondition
                                                                .hasBlockStateProperties(block)
                                                                .setProperties(
                                                                                StatePropertiesPredicate.Builder
                                                                                                .properties()
                                                                                                .hasProperty(
                                                                                                                BaseCheeseBlock.PIECES,
                                                                                                                8))));

                for (int pieces = 1; pieces <= BaseCheeseBlock.MAX_PIECES; pieces++) {
                        table.withPool(
                                        LootPool.lootPool()
                                                        .setRolls(ConstantValue.exactly(pieces))
                                                        .add(LootItem.lootTableItem(slice))
                                                        .when(LootItemBlockStatePropertyCondition
                                                                        .hasBlockStateProperties(block)
                                                                        .setProperties(
                                                                                        StatePropertiesPredicate.Builder
                                                                                                        .properties()
                                                                                                        .hasProperty(
                                                                                                                        BaseCheeseBlock.PIECES,
                                                                                                                        pieces)))
                                                        .when(InvertedLootItemCondition.invert(
                                                                        hasSilkTouch())));
                }

                for (int pieces = 1; pieces <= BaseCheeseBlock.MAX_PIECES; pieces++) {
                        if (pieces == 4 || pieces == 8)
                                continue;

                        table.withPool(
                                        LootPool.lootPool()
                                                        .setRolls(ConstantValue.exactly(pieces))
                                                        .add(LootItem.lootTableItem(slice))
                                                        .when(hasSilkTouch())
                                                        .when(LootItemBlockStatePropertyCondition
                                                                        .hasBlockStateProperties(block)
                                                                        .setProperties(
                                                                                        StatePropertiesPredicate.Builder
                                                                                                        .properties()
                                                                                                        .hasProperty(
                                                                                                                        BaseCheeseBlock.PIECES,
                                                                                                                        pieces))));
                }

                add(block, table);
        }

        private void addAgedCheese(Block block, Block matureBlock, Block agedBlock, ItemLike baseSlice,
                        ItemLike matureSlice, ItemLike agedSlice) {

                var table = LootTable.lootTable();

                for (int pieces = 1; pieces <= BaseCheeseBlock.MAX_PIECES; pieces++) {
                        if (pieces == 4 || pieces == 8) {
                                table.withPool(
                                                LootPool.lootPool()
                                                                .setRolls(ConstantValue.exactly(1))
                                                                .add(LootItem.lootTableItem(block))
                                                                .when(hasSilkTouch())
                                                                .when(LootItemBlockStatePropertyCondition
                                                                                .hasBlockStateProperties(block)
                                                                                .setProperties(
                                                                                                StatePropertiesPredicate.Builder
                                                                                                                .properties()
                                                                                                                .hasProperty(
                                                                                                                                BaseCheeseBlock.PIECES,
                                                                                                                                pieces)
                                                                                                                .hasProperty(
                                                                                                                                SealedCheeseBlock.AGE,
                                                                                                                                0))));

                                table.withPool(
                                                LootPool.lootPool()
                                                                .setRolls(ConstantValue.exactly(1))
                                                                .add(LootItem.lootTableItem(matureBlock))
                                                                .when(hasSilkTouch())
                                                                .when(LootItemBlockStatePropertyCondition
                                                                                .hasBlockStateProperties(block)
                                                                                .setProperties(
                                                                                                StatePropertiesPredicate.Builder
                                                                                                                .properties()
                                                                                                                .hasProperty(
                                                                                                                                BaseCheeseBlock.PIECES,
                                                                                                                                pieces)
                                                                                                                .hasProperty(
                                                                                                                                SealedCheeseBlock.AGE,
                                                                                                                                1))));

                                table.withPool(
                                                LootPool.lootPool()
                                                                .setRolls(ConstantValue.exactly(1))
                                                                .add(LootItem.lootTableItem(agedBlock))
                                                                .when(hasSilkTouch())
                                                                .when(LootItemBlockStatePropertyCondition
                                                                                .hasBlockStateProperties(block)
                                                                                .setProperties(
                                                                                                StatePropertiesPredicate.Builder
                                                                                                                .properties()
                                                                                                                .hasProperty(
                                                                                                                                BaseCheeseBlock.PIECES,
                                                                                                                                pieces)
                                                                                                                .hasProperty(
                                                                                                                                SealedCheeseBlock.AGE,
                                                                                                                                SealedCheeseBlock.MAX_AGE))));
                        }
                }

                for (int pieces = 1; pieces <= BaseCheeseBlock.MAX_PIECES; pieces++) {
                        table.withPool(
                                        LootPool.lootPool()
                                                        .setRolls(ConstantValue.exactly(pieces))
                                                        .add(LootItem.lootTableItem(baseSlice))
                                                        .when(LootItemBlockStatePropertyCondition
                                                                        .hasBlockStateProperties(block)
                                                                        .setProperties(
                                                                                        StatePropertiesPredicate.Builder
                                                                                                        .properties()
                                                                                                        .hasProperty(
                                                                                                                        BaseCheeseBlock.PIECES,
                                                                                                                        pieces)
                                                                                                        .hasProperty(
                                                                                                                        SealedCheeseBlock.AGE,
                                                                                                                        0)))
                                                        .when(InvertedLootItemCondition.invert(
                                                                        hasSilkTouch())));

                        table.withPool(
                                        LootPool.lootPool()
                                                        .setRolls(ConstantValue.exactly(pieces))
                                                        .add(LootItem.lootTableItem(matureSlice))
                                                        .when(LootItemBlockStatePropertyCondition
                                                                        .hasBlockStateProperties(block)
                                                                        .setProperties(
                                                                                        StatePropertiesPredicate.Builder
                                                                                                        .properties()
                                                                                                        .hasProperty(
                                                                                                                        BaseCheeseBlock.PIECES,
                                                                                                                        pieces)
                                                                                                        .hasProperty(
                                                                                                                        SealedCheeseBlock.AGE,
                                                                                                                        1)))
                                                        .when(InvertedLootItemCondition.invert(
                                                                        hasSilkTouch())));

                        table.withPool(
                                        LootPool.lootPool()
                                                        .setRolls(ConstantValue.exactly(pieces))
                                                        .add(LootItem.lootTableItem(agedSlice))
                                                        .when(LootItemBlockStatePropertyCondition
                                                                        .hasBlockStateProperties(block)
                                                                        .setProperties(
                                                                                        StatePropertiesPredicate.Builder
                                                                                                        .properties()
                                                                                                        .hasProperty(
                                                                                                                        BaseCheeseBlock.PIECES,
                                                                                                                        pieces)
                                                                                                        .hasProperty(
                                                                                                                        SealedCheeseBlock.AGE,
                                                                                                                        SealedCheeseBlock.MAX_AGE)))
                                                        .when(InvertedLootItemCondition.invert(
                                                                        hasSilkTouch())));
                }

                for (int pieces = 1; pieces <= BaseCheeseBlock.MAX_PIECES; pieces++) {
                        if (pieces == 4 || pieces == 8)
                                continue;

                        table.withPool(
                                        LootPool.lootPool()
                                                        .setRolls(ConstantValue.exactly(pieces))
                                                        .add(LootItem.lootTableItem(baseSlice))
                                                        .when(hasSilkTouch())
                                                        .when(LootItemBlockStatePropertyCondition
                                                                        .hasBlockStateProperties(block)
                                                                        .setProperties(
                                                                                        StatePropertiesPredicate.Builder
                                                                                                        .properties()
                                                                                                        .hasProperty(
                                                                                                                        BaseCheeseBlock.PIECES,
                                                                                                                        pieces)
                                                                                                        .hasProperty(
                                                                                                                        SealedCheeseBlock.AGE,
                                                                                                                        0))));

                        table.withPool(
                                        LootPool.lootPool()
                                                        .setRolls(ConstantValue.exactly(pieces))
                                                        .add(LootItem.lootTableItem(matureSlice))
                                                        .when(hasSilkTouch())
                                                        .when(LootItemBlockStatePropertyCondition
                                                                        .hasBlockStateProperties(block)
                                                                        .setProperties(
                                                                                        StatePropertiesPredicate.Builder
                                                                                                        .properties()
                                                                                                        .hasProperty(
                                                                                                                        BaseCheeseBlock.PIECES,
                                                                                                                        pieces)
                                                                                                        .hasProperty(
                                                                                                                        SealedCheeseBlock.AGE,
                                                                                                                        1))));

                        table.withPool(
                                        LootPool.lootPool()
                                                        .setRolls(ConstantValue.exactly(pieces))
                                                        .add(LootItem.lootTableItem(agedSlice))
                                                        .when(hasSilkTouch())
                                                        .when(LootItemBlockStatePropertyCondition
                                                                        .hasBlockStateProperties(block)
                                                                        .setProperties(
                                                                                        StatePropertiesPredicate.Builder
                                                                                                        .properties()
                                                                                                        .hasProperty(
                                                                                                                        BaseCheeseBlock.PIECES,
                                                                                                                        pieces)
                                                                                                        .hasProperty(
                                                                                                                        SealedCheeseBlock.AGE,
                                                                                                                        SealedCheeseBlock.MAX_AGE))));
                }

                add(block, table);
        }

        private void addDoubleCrop(Block block, ItemLike seed, ItemLike result, IntegerProperty age, int max_age,
                        BooleanProperty isTop) {

                add(block,
                                LootTable.lootTable()

                                                .withPool(
                                                                LootPool.lootPool()
                                                                                .setRolls(ConstantValue.exactly(1))
                                                                                .add(LootItem.lootTableItem(seed))
                                                                                .when(
                                                                                                LootItemBlockStatePropertyCondition
                                                                                                                .hasBlockStateProperties(
                                                                                                                                block)
                                                                                                                .setProperties(
                                                                                                                                StatePropertiesPredicate.Builder
                                                                                                                                                .properties()
                                                                                                                                                .hasProperty(
                                                                                                                                                                isTop,
                                                                                                                                                                false))))

                                                .withPool(
                                                                LootPool.lootPool()
                                                                                .setRolls(ConstantValue.exactly(1))
                                                                                .add(LootItem.lootTableItem(seed))
                                                                                .when(
                                                                                                LootItemBlockStatePropertyCondition
                                                                                                                .hasBlockStateProperties(
                                                                                                                                block)
                                                                                                                .setProperties(
                                                                                                                                StatePropertiesPredicate.Builder
                                                                                                                                                .properties()
                                                                                                                                                .hasProperty(
                                                                                                                                                                isTop,
                                                                                                                                                                false)
                                                                                                                                                .hasProperty(
                                                                                                                                                                age,
                                                                                                                                                                max_age))))

                                                .withPool(
                                                                LootPool.lootPool()
                                                                                .setRolls(ConstantValue.exactly(1))
                                                                                .add(LootItem.lootTableItem(
                                                                                                block))
                                                                                .when(
                                                                                                LootItemBlockStatePropertyCondition
                                                                                                                .hasBlockStateProperties(
                                                                                                                                block)
                                                                                                                .setProperties(
                                                                                                                                StatePropertiesPredicate.Builder
                                                                                                                                                .properties()
                                                                                                                                                .hasProperty(
                                                                                                                                                                isTop,
                                                                                                                                                                true)
                                                                                                                                                .hasProperty(
                                                                                                                                                                age,
                                                                                                                                                                max_age)))));

        }
}
