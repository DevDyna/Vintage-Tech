package com.synergy.vintagetech.init.builder.crucible;

import org.jspecify.annotations.Nullable;

import com.devdyna.cakesticklib.api.aspect.logic.BucketInteraction;
import com.devdyna.cakesticklib.api.aspect.logic.FluidClearableTank;
import com.devdyna.cakesticklib.api.aspect.logic.FluidTooltipWhenEmpty;
import com.devdyna.cakesticklib.api.aspect.templates.TickingBlock;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;

public class CrucibleBlock extends TickingBlock
        implements BucketInteraction, FluidTooltipWhenEmpty, FluidClearableTank {

    public static final BooleanProperty HEATED = BooleanProperty.create("heated");

    public CrucibleBlock(Properties p) {
        super(p);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return this.defaultBlockState()
                .setValue(HEATED, isHeated(c.getLevel(), c.getClickedPos().relative(c.getClickedFace().getOpposite())));
    }

    public boolean isHeated(Level level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(zTags.Blocks.CRUCIBLE_HEAT_SOURCES);
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        b.add(HEATED);
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
    public @Nullable BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new CrucibleBE(p, s);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level l, BlockState s, BlockEntityType<T> ty) {
        return (lvl, pos, b, t) -> {
            if (t instanceof CrucibleBE be) {

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
        if (level.getBlockEntity(pos) instanceof CrucibleBE be)
            be.collectItem(level, pos, entity);
        super.fallOn(level, state, pos, entity, fallDistance);
    }

    @Override
    public InteractionResult onTooltipFail(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof CrucibleBE be)
            return be.itemUseOn(player, level, pos, hand);
        return InteractionResult.FAIL;
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof CrucibleBE)
            return bucketAction(stack, state, level, pos, player, hand, hitResult);
        return InteractionResult.PASS;
    }

    @Override
    public boolean showWhen(BlockEntity be) {
        return (be instanceof CrucibleBE tank) ? tank.getStackInSlot(0).isEmpty() : true;
    }

    @Override
    public InteractionResult executeWhenNotBucket(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof CrucibleBE be)
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
        return be instanceof CrucibleBE tank ? tank.getFluidStorage() : null;
    }

}
