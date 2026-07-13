package com.synergy.vintagetech.init.builder.centrifuge;

import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.devdyna.cakesticklib.api.aspect.logic.BucketInteraction;
import com.devdyna.cakesticklib.api.aspect.logic.FluidClearableTank;
import com.devdyna.cakesticklib.api.aspect.logic.FluidTooltipWhenEmpty;
import com.synergy.vintagetech.api.blockfactory.MonoDirectionalAxleBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;

public class CentrifugeBlock extends MonoDirectionalAxleBlock
        implements BucketInteraction, FluidTooltipWhenEmpty, FluidClearableTank {

    public CentrifugeBlock(Properties p) {
        super(p);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return this.defaultBlockState()
                .setValue(ENABLED, false)
                .setValue(INVERTED, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(ENABLED, INVERTED);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 1, 0.125, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.125, 0.375, 0.625, 0.6875, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.125, 0, 1, 1, 0.125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.125, 0.875, 1, 1, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.125, 0.125, 0.125, 1, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.875, 0.125, 0.125, 1, 1, 0.875), BooleanOp.OR);
        return shape;
    }

    @Override
    public Map<Direction, Boolean> getAxis(BlockState state) {
        return Map.of(Direction.UP, state.getValue(INVERTED));
    }

    @Override
    public Direction getInputRotation(BlockState state) {
        return Direction.DOWN;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new CentrifugeBE(p, s);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level l, BlockState s, BlockEntityType<T> ty) {
        return (lvl, pos, b, t) -> {
            if (t instanceof CentrifugeBE be) {

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
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        if (level.getBlockEntity(pos) instanceof CentrifugeBE be)
            be.collectItem(level,pos, entity);
        super.fallOn(level, state, pos, entity, fallDistance);
    }

    @Override
    public InteractionResult onTooltipFail(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof CentrifugeBE be)
            return be.itemUseOn(player, level, pos, hand);
        return InteractionResult.FAIL;
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof CentrifugeBE)
            return bucketAction(stack, state, level, pos, player, hand, hitResult);
        return InteractionResult.PASS;
    }

    @Override
    public boolean showWhen(BlockEntity be) {
        return (be instanceof CentrifugeBE tank) ? tank.getStackInSlot(0).isEmpty() : true;
    }

    @Override
    public InteractionResult executeWhenNotBucket(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof CentrifugeBE be)
            return be.itemUseOn(player, level, pos, hand);
        return InteractionResult.FAIL;
    }

    @Override
    public InteractionResult executeWhenEmpty(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (player.isCrouching())
            return useItemToClear(state, level, pos, player, hitResult);
        else
            return sendFluidTooltip(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public FluidStacksResourceHandler getFluidTank(BlockEntity be, BlockState state, Level level, BlockPos pos,
            Player player,
            InteractionHand hand, BlockHitResult hitResult) {
        return be instanceof CentrifugeBE tank ? tank.getFluidStorage() : null;
    }

   

}
