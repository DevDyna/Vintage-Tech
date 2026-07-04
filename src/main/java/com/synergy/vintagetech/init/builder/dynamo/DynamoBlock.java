package com.synergy.vintagetech.init.builder.dynamo;

import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.synergy.vintagetech.api.blockfactory.MonoDirectionalAxleBlock;
import com.synergy.vintagetech.init.builder.steam_engine.SteamEngineBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DynamoBlock extends MonoDirectionalAxleBlock {

    public final static EnumProperty<Direction> HORIZONTAL_FACING = SteamEngineBlock.HORIZONTAL_FACING;

    public DynamoBlock(Properties p) {
        super(p);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(HORIZONTAL_FACING).getAxis()) {
            case Direction.Axis.X -> axle_X;
            case Direction.Axis.Y -> axle_Y;
            case Direction.Axis.Z -> axle_Z;
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return this.defaultBlockState()
                .setValue(ENABLED, false)
                .setValue(INVERTED, false)
                .setValue(HORIZONTAL_FACING, c.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(HORIZONTAL_FACING, ENABLED, INVERTED);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new DynamoBE(p, s);
    }

    @Override
    public Map<Direction, Boolean> getAxis(BlockState state) {
        return Map.of(state.getValue(HORIZONTAL_FACING), state.getValue(INVERTED));
    }

    @Override
    public Direction getInputRotation(BlockState state) {
        return state.getValue(HORIZONTAL_FACING);
    }

     @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level l, BlockState s, BlockEntityType<T> ty) {
        return (lvl, pos, b, t) -> {
            if (t instanceof DynamoBE be) {

                if (l == null)
                    return;

                be.tickBoth();
                if (l.isClientSide())
                    be.tickClient();
                else
                    be.tickServer();

            }

        };
    }

}
