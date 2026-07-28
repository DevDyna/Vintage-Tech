package com.synergy.vintagetech.api.blockfactory.transmission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

import com.synergy.vintagetech.api.blockfactory.BaseKineticBlock;
import com.synergy.vintagetech.api.blockfactory.KineticGenerator.NetworkState;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class MotionAlteratorBlock extends BaseKineticBlock {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final EnumProperty<Axis> AXIS = BlockStateProperties.AXIS;

    public MotionAlteratorBlock(Properties p) {
        super(p);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return this.defaultBlockState()
                .setValue(ENABLED, false)
                .setValue(POWERED, isActive(c.getLevel(), c.getClickedPos()))
                .setValue(INVERTED, false)
                .setValue(AXIS, c.getClickedFace().getAxis());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(AXIS, INVERTED, POWERED, ENABLED);
    }

    @Override
    protected VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.block();
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos,
            CollisionContext context) {

        return switch (state.getValue(AXIS)) {
            case Direction.Axis.X ->
                Shapes.join(
                        Shapes.join(
                                Shapes.box(0, 0, 0, 0.25, 1, 1), Shapes.box(0.75, 0, 0, 1, 1, 1), BooleanOp.OR),
                        Shapes.box(0.25, 0.0625, 0.0625, 0.75, 0.9375, 0.9375), BooleanOp.OR);
            case Direction.Axis.Y ->
                Shapes.join(
                        Shapes.join(
                                Shapes.box(0, 0.75, 0, 1, 1, 1), Shapes.box(0, 0, 0, 1, 0.25, 1), BooleanOp.OR),
                        Shapes.box(0.0625, 0.25, 0.0625, 0.9375, 0.75, 0.9375), BooleanOp.OR);
            case Direction.Axis.Z ->
                Shapes.join(
                        Shapes.join(
                                Shapes.box(0, 0, 0, 1, 1, 0.25), Shapes.box(0, 0, 0.75, 1, 1, 1), BooleanOp.OR),
                        Shapes.box(0.0625, 0.0625, 0.25, 0.9375, 0.9375, 0.75), BooleanOp.OR);

        };
    }

    public boolean isActive(Level level, BlockPos pos) {
        return level.hasNeighborSignal(pos);
    }

    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block,
            @Nullable Orientation orientation, boolean movedByPiston) {
        if (level.isClientSide())
            return;

        if (state.getValue(POWERED) != level.hasNeighborSignal(pos))
            level.setBlock(pos, state.cycle(POWERED), 2);

    }

    @Override
    public Map<Direction, Boolean> getAxis(BlockState state) {
        Map<Direction, Boolean> map = new HashMap<>();

        for (Direction d : state.getValue(AXIS).getDirections())
            map.put(d, state.getValue(INVERTED));

        return map;
    }

    @Override
    public List<Direction> getOutputDirections(BlockState state) {
        return List.of(state.getValue(AXIS).getDirections());
    }

    @Override
    public boolean canInputFrom(Direction dir, BlockState state) {
        return dir.getAxis() == state.getValue(AXIS);
    }

    public NetworkState modifyNetwork(Level level, BlockPos pos, BlockState state, NetworkState status) {
        return status;
    }
}
