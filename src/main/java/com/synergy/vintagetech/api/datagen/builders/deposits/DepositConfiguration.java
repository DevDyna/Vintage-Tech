package com.synergy.vintagetech.api.datagen.builders.deposits;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class DepositConfiguration implements FeatureConfiguration {

        public static final Codec<DepositConfiguration> CODEC = RecordCodecBuilder
                        .create(instance -> instance.group(

                                        BlockStateProvider.CODEC
                                                        .fieldOf("block")
                                                        .forGetter(DepositConfiguration::getBlock),

                                        Codec.BOOL
                                                        .fieldOf("replace")
                                                        .forGetter(DepositConfiguration::replace),
                                        Codec.BOOL
                                                        .fieldOf("stack")
                                                        .forGetter(DepositConfiguration::stack),

                                        Codec.intRange(1, 16)
                                                        .fieldOf("xz")
                                                        .forGetter(DepositConfiguration::radius)

                        ).apply(instance, DepositConfiguration::new));

        private final BlockStateProvider block;
        private final boolean replace;
        private final boolean stack;
        private final int radius;

        public DepositConfiguration(BlockStateProvider block,boolean replace, boolean stack,int radius) {
                this.block = block;
                this.replace = replace;
                this.stack = stack;
                this.radius = radius;
        }

        public BlockStateProvider getBlock() {
                return block;
        }

        public boolean replace() {
                return replace;
        }
        public boolean stack() {
                return stack;
        }

        public int radius() {
                return radius;
        }

       

}