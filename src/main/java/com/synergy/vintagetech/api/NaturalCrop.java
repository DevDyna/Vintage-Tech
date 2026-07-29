package com.synergy.vintagetech.api;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public interface NaturalCrop {
    public static final BooleanProperty NATURAL = BooleanProperty.create("natural");

    default void growCrops(Level level, BlockPos pos, BlockState state) {
        level.setBlock(pos, getStateForAge(Math.min(getMaxAge(), getAge(state) +
                getBonemealAgeIncrease(level)))
                .setValue(NATURAL, state.getValue(NATURAL)), 2);
    }

    abstract int getBonemealAgeIncrease(Level level);

    abstract int getMaxAge();

    abstract int getAge(BlockState state);

    abstract BlockState getStateForAge(int age);
}
