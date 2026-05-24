package com.synergy.vintagetech.init.builder;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class QuernBlock extends MonoDirectionalAxleBlock {

    public QuernBlock(Properties p) {
        super(p);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.join(
                Shapes.join(axleY,
                        Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.25, 0.9375),
                        BooleanOp.OR),
                Shapes.box(0.125, 0.25, 0.125, 0.875, 0.5, 0.875),
                BooleanOp.OR)
                .optimize();
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
