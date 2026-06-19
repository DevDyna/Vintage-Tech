package com.synergy.vintagetech.datagen.server;

import java.util.*;

import com.devdyna.cakesticklib.api.utils.LootTableHelper;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;

public class DataLootBlock extends BlockLootSubProvider {

        public DataLootBlock(HolderLookup.Provider l) {
                super(Set.of(), FeatureFlags.DEFAULT_FLAGS, l);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
                return LootTableHelper.getValidBlocks(zBlocks.zBlock, zBlocks.zBlockItem);
        }

        @Override
        protected void generate() {

                 LootTableHelper.getValidBlocks(zBlocks.zBlockItem).forEach(this::dropSelf);

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

        }

}
