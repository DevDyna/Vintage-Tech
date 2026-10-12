
package com.synergy.vintagetech.init.builder;

import java.util.ArrayList;
import java.util.List;

import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.mixin.IceMeltingInvoker;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;

public class SaltLayerBlock extends SnowLayerBlock {

    public SaltLayerBlock(Properties p) {
        super(p.randomTicks());
        this.registerDefaultState(this.stateDefinition.any().setValue(LAYERS, 1));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
            BlockHitResult hitResult) {

        if (!player.getMainHandItem().isEmpty()
                || !player.getOffhandItem().isEmpty())
            return InteractionResult.PASS;

        var top = getTopLayer(level, pos);
        var above = level.getBlockState(top);

        var layers = above.getValue(LAYERS);

        if (layers > 1)
            level.setBlock(top, above.setValue(LAYERS, layers - 1), UPDATE_ALL);
        else
            level.removeBlock(top, false);

        if (!level.isClientSide())
            Block.getDrops(zBlocks.SALT.get().defaultBlockState().setValue(LAYERS, 1), (ServerLevel) level, top, null)
                    .forEach(player::addItem);

        level.playSound(player, pos, this.soundType.getBreakSound(), SoundSource.BLOCKS);

        return InteractionResult.SUCCESS;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var below = level.getBlockState(pos.below());
        return Block.isFaceFull(below.getCollisionShape(level, pos.below()), Direction.UP)
                || (below.is(this) && below.getValue(LAYERS) == 8);
    }

    public static List<BlockPos> getMeltingArea(BlockPos pos) {
        return BlockPos.betweenClosedStream(pos.offset(-4, -4, -4), pos.offset(4, 4, 4))
                .map(BlockPos::immutable)
                .toList();
    }

    @Override
    protected void randomTick(BlockState s, ServerLevel level, BlockPos pos, RandomSource random) {
        var list = new ArrayList<BlockPos>();

        for (var off : getMeltingArea(pos)) {
            if (off.equals(pos))
                continue;

            var offstate = level.getBlockState(off);

            if (offstate.getBlock() instanceof SaltLayerBlock)
                continue;

            if (offstate.getBlock() instanceof SnowLayerBlock || offstate.getBlock() instanceof IceBlock)
                list.add(off);

        }

        if (list.isEmpty())
            return;

        var select = list.get(random.nextInt(list.size()));
        var state = level.getBlockState(select);

        if (state.getBlock() instanceof SnowLayerBlock) {
            Block.dropResources(state, level, select);
            level.removeBlock(select, false);
        }

        if (state.getBlock() instanceof IceBlock ice)
            ((IceMeltingInvoker) ice).forceMelt(state, level, select);

    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        List<ItemStack> items = super.getDrops(state, params);

        if (params.getOptionalParameter(LootContextParams.THIS_ENTITY) == null)
            for (int i = state.getValue(LAYERS); i > 1; i--)
                items.addAll(super.getDrops(state, params));

        return items;
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        var top = getTopLayer(level, pos);
        var above = level.getBlockState(top);
        if (above.getBlock() instanceof SaltLayerBlock) {
            var layers = above.getValue(LAYERS);

            if (layers > 1)
                level.setBlock(top, above.setValue(LAYERS, layers - 1), UPDATE_ALL);
            else
                level.removeBlock(top, false);
        } else
            level.removeBlock(top, false);

    }

    private BlockPos getTopLayer(LevelAccessor level, BlockPos pos) {
        while (level.getBlockState(pos.above()).getBlock() instanceof SaltLayerBlock)
            pos = pos.above();

        return pos;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, ItemStack toolStack,
            boolean willHarvest, FluidState fluid) {

        var top = getTopLayer(level, pos);
        var above = level.getBlockState(top);
        var layers = above.getValue(LAYERS);

        if (layers > 1) {
            if (!player.isCreative())
                dropResources(above, level, top, null, player, toolStack);

            level.setBlock(top, above.setValue(LAYERS, layers - 1), UPDATE_ALL);
            return false;
        }

        if (!top.equals(pos)) {
            if (!player.isCreative())
                dropResources(above, level, top, null, player, toolStack);

            level.removeBlock(top, false);
            return false;
        }

        return super.onDestroyedByPlayer(
                state, level, pos, player, toolStack, willHarvest, fluid);
    }
}
