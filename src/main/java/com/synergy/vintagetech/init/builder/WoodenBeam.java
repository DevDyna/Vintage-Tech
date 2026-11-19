package com.synergy.vintagetech.init.builder;

import java.util.ArrayList;
import java.util.Map;

import com.synergy.vintagetech.api.factories.handlers.AxleHandler;
import com.synergy.vintagetech.api.factories.handlers.RopeHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WoodenBeam extends Block implements RopeHandler {

    public static final BooleanProperty X = BooleanProperty.create("x");
    public static final BooleanProperty Y = BooleanProperty.create("y");
    public static final BooleanProperty Z = BooleanProperty.create("z");

    public static final Map<Axis, BooleanProperty> PROPERTY_BY_AXIS = Map.of(Axis.X, X, Axis.Y, Y, Axis.Z, Z);

    public WoodenBeam(Properties p) {
        super(p);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(X, false)
                .setValue(Y, true)
                .setValue(Z, false)
                .setValue(HAS_ROPE, false));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        var shape = Shapes.box(0.3125, 0.3125, 0.3125, 0.6875, 0.6875, 0.6875);

        if (state.getValue(X))
            shape = Shapes.join(shape, AxleHandler.axle_X, BooleanOp.OR);

        if (state.getValue(Y))
            shape = Shapes.join(shape, AxleHandler.axle_Y, BooleanOp.OR);

        if (state.getValue(Z))
            shape = Shapes.join(shape, AxleHandler.axle_Z, BooleanOp.OR);

        return shape.optimize();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        var dir = c.getClickedFace();
        var axis = dir.getAxis();
        var pos = c.getClickedPos();
        var level = c.getLevel();

        return this.defaultBlockState()
                .setValue(X, hasBeam(level, pos, Axis.X) || axis.equals(Axis.X))
                .setValue(Y, hasBeam(level, pos, Axis.Y) || axis.equals(Axis.Y))
                .setValue(Z, hasBeam(level, pos, Axis.Z) || axis.equals(Axis.Z))
                .setValue(HAS_ROPE, hasRope(level, pos, axis));
    }

    @Override
    protected BlockState updateShape(BlockState s, LevelReader l, ScheduledTickAccess t, BlockPos p, Direction d,
            BlockPos np, BlockState ns, RandomSource r) {
        return s.setValue(HAS_ROPE, hasRope(l, p, s))
                .setValue(X, hasBeam(l, p, Axis.X) || s.getValue(X))
                .setValue(Y, hasBeam(l, p, Axis.Y) || s.getValue(Y))
                .setValue(Z, hasBeam(l, p, Axis.Z) || s.getValue(Z))
                ;
    }

    private boolean hasBeam(LevelReader level, BlockPos pos, Axis axis) {
        return level.getBlockState(pos.relative(axis.getNegative())).getBlock() instanceof WoodenBeam
                || level.getBlockState(pos.relative(axis.getPositive())).getBlock() instanceof WoodenBeam;
    }

    public boolean hasRope(BlockGetter level, BlockPos pos, BlockState state) {

        var axis = new ArrayList<>();

        if (state.getValue(X))
            axis.add(Axis.X);

        if (state.getValue(Y))
            axis.add(Axis.Y);

        if (state.getValue(Z))
            axis.add(Axis.Z);

        return hasRope(level, pos, axis.toArray(Axis[]::new));
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(X, Y, Z, HAS_ROPE);
    }

    @Override
    public boolean isLadder(BlockState state, LevelReader level, BlockPos pos, LivingEntity entity) {
        return state.getValue(HAS_ROPE);
    }

}