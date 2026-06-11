package com.synergy.vintagetech.init.builder.hopper;

import com.devdyna.cakesticklib.api.aspect.templates.TickingBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BasketBlock extends TickingBlock {

    public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;

    public BasketBlock(Properties p) {
        super(p.sound(SoundType.GRASS));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return this.defaultBlockState()
                .setValue(FACING, c.getClickedFace());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(FACING);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape = Shapes.empty();

        switch (state.getValue(FACING)) {
            case NORTH -> {
                shape = Shapes.join(shape, Shapes.box(0, 0, 0.75, 1, 1, 1), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.1875, 0.8125, 0, 0.8125, 1, 0.75), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0, 0.8125, 0.1875, 0.75), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0, 0, 0, 0.1875, 1, 0.75), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.8125, 0, 0, 1, 1, 0.75), BooleanOp.OR);
            }
            case SOUTH -> {
                shape = Shapes.join(shape, Shapes.box(0.75, 0, 0, 1, 1, 1), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0, 0.8125, 0.1875, 0.75, 1, 0.8125), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0, 0, 0.1875, 0.75, 0.1875, 0.8125), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0, 0, 0.8125, 0.75, 1, 1), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0, 0, 0, 0.75, 1, 0.1875), BooleanOp.OR);
            }
            case WEST -> {
                shape = Shapes.join(shape, Shapes.box(0.75, 0, 0, 1, 1, 1), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0, 0.1875, 0.8125, 0.75, 0.8125, 1), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0, 0.1875, 0, 0.75, 0.8125, 0.1875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0, 0, 0, 0.75, 0.1875, 1), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0, 0.8125, 0, 0.75, 1, 1), BooleanOp.OR);
            }
            case EAST -> {
                shape = Shapes.join(shape, Shapes.box(0, 0, 0, 0.25, 1, 1), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.25, 0.8125, 0.1875, 1, 1, 0.8125), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.1875, 1, 0.1875, 0.8125), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.25, 0, 0, 1, 1, 0.1875), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.8125, 1, 1, 1), BooleanOp.OR);
            }
            case UP -> {
                shape = Shapes.join(shape, Shapes.box(0, 0, 0, 1, 0.25, 1), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.8125, 0.25, 0.1875, 1, 1, 0.8125), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0, 0.25, 0.1875, 0.1875, 1, 0.8125), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0, 0.25, 0.8125, 1, 1, 1), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0, 0.25, 0, 1, 1, 0.1875), BooleanOp.OR);
            }
            case DOWN -> {
                shape = Shapes.join(shape, Shapes.box(0, 0.75, 0, 1, 1, 1), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0, 0, 0.1875, 0.1875, 0.75, 0.8125), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0.8125, 0, 0.1875, 1, 0.75, 0.8125), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0, 0, 0.8125, 1, 0.75, 1), BooleanOp.OR);
                shape = Shapes.join(shape, Shapes.box(0, 0, 0, 1, 0.75, 0.1875), BooleanOp.OR);
            }
        }

        return shape;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BasketBE(pos, state);
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

}
