package com.synergy.vintagetech.init.builder.crushing_tub;

import javax.annotation.Nullable;

import com.devdyna.cakesticklib.api.aspect.logic.BucketInteraction;
import com.devdyna.cakesticklib.api.aspect.logic.FluidTooltipWhenEmpty;
import com.devdyna.cakesticklib.api.aspect.templates.TickingBlock;
import com.synergy.vintagetech.api.aspects.FluidClearableTank;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;


public class CrushingTubBlock extends TickingBlock
        implements BucketInteraction, FluidClearableTank, FluidTooltipWhenEmpty {

    public CrushingTubBlock(Properties properties) {
        super(properties.strength(2.0F, 3.0F).sound(SoundType.WOOD)
                .instrument(NoteBlockInstrument.BASS).mapColor(MapColor.RAW_IRON));
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CrushingTubBE(pos, state);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter getter, BlockPos pos, Direction dir) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter getter, BlockPos pos, Direction dir) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter getter, BlockPos pos, Direction dir) {
        return 5;
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return Block.box(0, 0, 0, 16, 9, 16);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof CrushingTubBE)
            return bucketAction(stack, state, level, pos, player, hand, hitResult);
        return InteractionResult.PASS;
    }

    @Override
    public boolean showWhen(BlockEntity be) {
        return (be instanceof CrushingTubBE tank) ? tank.getStackInSlot(0).isEmpty() : true;
    }

    @Override
    public InteractionResult executeWhenNotBucket(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof CrushingTubBE be)
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
        return be instanceof CrushingTubBE tank ? tank.getFluidStorage() : null;
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        if (level.getBlockEntity(pos) instanceof CrushingTubBE be &&
            be.craft(true);
                entity.is(zTags.Entities.CRUSHING_TUB_ALLOW_CRUSHING))
        super.fallOn(level, state, pos, entity, fallDistance);
    }

    @Override
    public InteractionResult onTooltipFail(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof CrushingTubBE be)
            return be.itemUseOn(player, level, pos, hand);
        return InteractionResult.FAIL;
    }

}