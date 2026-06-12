package com.synergy.vintagetech.init.builder.evaporation_basin;

import javax.annotation.Nullable;

import com.devdyna.cakesticklib.api.aspect.logic.BucketInteraction;
import com.devdyna.cakesticklib.api.aspect.logic.FluidTooltipWhenEmpty;
import com.devdyna.cakesticklib.api.aspect.templates.TickingBlock;
import com.synergy.vintagetech.api.aspects.FluidClearableTank;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;

@SuppressWarnings("null")
public class EvaporationBasinBlock extends TickingBlock
        implements BucketInteraction, FluidClearableTank, FluidTooltipWhenEmpty {

    public EvaporationBasinBlock(Properties properties) {
        super(properties.mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).strength(1F,
                2.25F));
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EvaporationBasinBE(pos, state);
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return Block.box(1, 0, 1, 15, 5, 15);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof EvaporationBasinBE)
            return bucketAction(stack, state, level, pos, player, hand, hitResult);
        return InteractionResult.PASS;
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
        return (be instanceof EvaporationBasinBE tank) ? tank.getFluidStorage() : null;
    }

    @Override
    public boolean showWhen(BlockEntity be) {
        return (be instanceof EvaporationBasinBE tank) ? tank.getStackInSlot(0).isEmpty() : true;
    }

    @Override
    public InteractionResult onTooltipFail(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof EvaporationBasinBE be)
            return be.itemUseOn(player, level, pos, hand);
        return InteractionResult.FAIL;
    }

    @Override
    public InteractionResult executeWhenNotBucket(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof EvaporationBasinBE be)
            return be.itemUseOn(player, level, pos, hand);
        return InteractionResult.FAIL;
    }

}