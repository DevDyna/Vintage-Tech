package com.synergy.vintagetech.init.builder;

import java.util.List;

import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.level.block.state.BlockState;

public class QuernBlock extends MonoDirectionalAxleBlock {

    public QuernBlock(Properties p) {
        super(p);
    }

    @Override
    public Direction getInputRotation(BlockState state) {
        return Direction.UP;
    }

    @Override
    public List<Axis> getRotationAxis(BlockState state) {
        return List.of(Axis.Y);
    }

}
