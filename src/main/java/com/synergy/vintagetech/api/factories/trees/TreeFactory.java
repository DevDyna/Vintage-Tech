package com.synergy.vintagetech.api.factories.trees;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.synergy.vintagetech.api.factories.trees.TreeBuilders.*;
import com.synergy.vintagetech.api.factories.trees.TreeOptions.TreeParticleLeaves;
import com.synergy.vintagetech.api.factories.trees.TreeOptions.WoodOptionType;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;

public final class TreeFactory {

        private static final Map<String, TreeSet> TREES = new LinkedHashMap<>();

        public static Collection<TreeSet> getAll() {
                return TREES.values();
        }

        public static boolean contains(TreeSet set) {
                return TREES.containsKey(set.getId());
        }

        public static TreeSet get(TreeSet set) {
                return TREES.get(set.getId());
        }

        public static TreeSet register(String id, MapColor plank, MapColor log_side, float hardness, float resistance,
                        SoundType soundType, Function<String, BlockSetBuilder> blockSetBuilder,
                        BiFunction<String, BlockSetType, WoodTypeBuilder> woodTypeBuilder, WoodOptionType type,
                        TreeParticleLeaves particles, Function<String, TreeGrowerBuilder> grower, boolean burnable) {

                var blockSetType = blockSetBuilder.apply(id).build();
                var woodType = woodTypeBuilder.apply(id, blockSetType).build();

                var set = new TreeSet(TreeSet.TreeProperties.of(id, plank, log_side, hardness, resistance, soundType,
                                woodType, burnable, particles, blockSetType, type, grower));

                TREES.put(id, set);
                return set;
        }
}