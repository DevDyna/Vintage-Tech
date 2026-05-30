package com.synergy.vintagetech.init.builder.engine;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.devdyna.cakesticklib.api.aspect.logic.BucketInteraction;
import com.devdyna.cakesticklib.api.aspect.logic.FluidTooltipWhenEmpty;
import com.synergy.vintagetech.api.blockfactory.BaseKineticBlock;
import com.synergy.vintagetech.api.blockfactory.HorizontalAxleBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;

public class EngineBlock extends BaseKineticBlock
        implements BucketInteraction, FluidTooltipWhenEmpty, HorizontalAxleBlock {

    public EngineBlock(Properties p) {
        super(p);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
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
                .setValue(HORIZONTAL_FACING, c.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(HORIZONTAL_FACING, ENABLED);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new EngineBE(p, s);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level l, BlockState s, BlockEntityType<T> ty) {
        return (lvl, pos, b, t) -> {
            if (t instanceof EngineBE be) {

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

    @Override
    public boolean canInputFrom(Direction dir, BlockState state) {
        return false;
    }

    @Override
    public List<Direction> getOutputDirections(BlockState state) {
        return List.of();
    }

    protected InteractionResult useItemOn(ItemStack stack, BlockState state,
            Level level, BlockPos pos, Player player,
            InteractionHand hand, BlockHitResult hitResult) {
        return bucketAction(stack, state, level, pos, player, hand, hitResult);
    }

    public InteractionResult executeWhenEmpty(ItemStack stack, BlockState state,
            Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        return sendFluidTooltip(stack, state, level, pos, player, hand, hitResult);
    }

    public FluidStacksResourceHandler getFluidTank(BlockEntity be, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        return be instanceof EngineBE tank ? tank.getFluidStorage() : null;
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        if (level.getBlockEntity(pos) instanceof EngineBE engine)
            engine.setRemoved();
        super.destroy(level, pos, state);
    }

    @Override
    public List<Direction> getRotationAxis(BlockState state) {
        return List.of(state.getValue(HORIZONTAL_FACING));
    }

}
