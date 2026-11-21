package com.synergy.vintagetech.init.types;

import com.synergy.vintagetech.api.factories.trees.TreeFactory;
import com.synergy.vintagetech.api.factories.trees.TreeSet;
import com.synergy.vintagetech.init.Material;
import com.synergy.vintagetech.api.factories.trees.TreeBuilders.*;
import com.synergy.vintagetech.api.factories.trees.TreeOptions.*;

import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;

public final class zTrees {

        public static void register(IEventBus bus) {
        }

        public final static TreeSet IRONWOOD = TreeFactory.register(
                        "ironwood",
                        MapColor.COLOR_LIGHT_GRAY, MapColor.COLOR_BLACK,
                        2.0F, 3.0F, SoundType.WOOD,
                        BlockSetBuilder.SIMPLE, WoodTypeBuilder.SIMPLE, WoodOptionType.TREE, TreeParticleLeaves.DEFAULT,
                        i -> TreeGrowerBuilder.of(i)
                                        .tree(zWorldGenFeatures.ConfiguredFeatures.IRONWOOD),
                        true).build();

        public final static TreeSet OLIVE = TreeFactory.register(
                        "olive",
                        MapColor.COLOR_ORANGE, MapColor.COLOR_GRAY,
                        2.0F, 3.0F, SoundType.WOOD,
                        BlockSetBuilder.SIMPLE, WoodTypeBuilder.SIMPLE, WoodOptionType.TREE, TreeParticleLeaves.DEFAULT,
                        i -> TreeGrowerBuilder.of(i)
                                        .tree(zWorldGenFeatures.ConfiguredFeatures.OLIVE),
                        true).build();

        public final static TreeSet CORK_OAK = TreeFactory.register(
                        "cork_oak",
                        MapColor.COLOR_ORANGE, MapColor.COLOR_BROWN,
                        2.0F, 3.0F, SoundType.WOOD,
                        BlockSetBuilder.SIMPLE, WoodTypeBuilder.SIMPLE, WoodOptionType.TREE, TreeParticleLeaves.DEFAULT,
                        i -> TreeGrowerBuilder.of(i)
                                        .tree(zWorldGenFeatures.ConfiguredFeatures.CORK_OAK),
                        true)
                        .setStrippedLog((pr) -> Material.registerItemBlock(
                                        WoodOptionType.STRIPPED + pr.id() + pr.woodOptionType().suffix4(),
                                        p -> new RotatedPillarBlock(
                                                        p.strength(pr.hardness(), pr.resistance()).sound(pr.soundType())
                                                                        .ignitedByLava().mapColor(pr.plankColor()))))
                        .build();

}