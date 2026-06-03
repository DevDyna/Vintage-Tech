package com.synergy.vintagetech.init.builder;

import java.util.HashMap;
import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.synergy.vintagetech.init.builder.transmission.AxleBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GearShiftBlock extends AxleBlock {

    public static final BooleanProperty POWERED = NoteBlock.POWERED;

    public GearShiftBlock(Properties p) {
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
                .setValue(POWERED, c.getLevel().hasNeighborSignal(c.getClickedPos()))
                .setValue(INVERTED, false)
                .setValue(AXIS, c.getClickedFace().getAxis());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(AXIS, INVERTED, POWERED, ENABLED);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.block();
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
}
