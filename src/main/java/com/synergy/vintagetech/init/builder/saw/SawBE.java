package com.synergy.vintagetech.init.builder.saw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.devdyna.cakesticklib.api.ItemLogisticUtils;
import com.devdyna.cakesticklib.api.aspect.templates.TickingBE;
import com.devdyna.cakesticklib.api.factories.plants.VanillaPlants;
import com.devdyna.cakesticklib.setup.Config;
import com.synergy.vintagetech.init.types.zBlockEntities;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;

public class SawBE extends TickingBE {

    public static final AtomicInteger UUID_GENERATOR = new AtomicInteger();

    private int UUID = -UUID_GENERATOR.incrementAndGet();

    public SawBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.SAW.get(), pos, state);
    }

    private float progress = -1f;

    @Override
    public void tickServer() {

        if (!getBlockState().getValue(SawBlock.ENABLED)) {
            resetBreak();
            return;
        }

        var relative = level.getBlockState(getOffset());
        var speed = relative.getDestroySpeed(level, getOffset());

        if (relative.isAir()
                || relative.getBlock() instanceof LiquidBlock
                || speed == -1
                || relative.is(zTags.Blocks.SAW_DENY_BREAK)) {
            resetBreak();
            return;
        }

        progress += (isFast(relative) ? 0.75F : 0.25F) / speed;

        level.playSound(null, getOffset(), relative
                .getSoundType(level, getOffset(), null)
                .getHitSound(), SoundSource.BLOCKS, .25f, 1);

        if (progress >= 10) {

            var tree = checkTree(level, getOffset());

            if (tree != null) {
                ItemLogisticUtils.unifyDrops(tree).forEach(i -> {
                    var item = new ItemEntity(level,
                            getOffset().getX() + 0.5f,
                            getOffset().getY() + 0.5f,
                            getOffset().getZ() + 0.5f,
                            i);

                    item.lifespan = 2400;
                    item.setDeltaMovement(0, 0, 0);

                    level.addFreshEntity(item);
                });
            } else {
                level.playSound(null, getOffset(), relative.getSoundType(level, getOffset(), null).getBreakSound(),
                        SoundSource.BLOCKS);
                level.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, getOffset(), Block.getId(relative));


                Block.getDrops(relative, (ServerLevel) level, getOffset(), null)
                        .forEach(i -> ItemLogisticUtils.createLazyItemEntity(i, level, getOffset(), 2400, true));

                level.setBlockAndUpdate(getOffset(), Blocks.AIR.defaultBlockState());

            }

            resetBreak();
            return;
        }

        level.destroyBlockProgress(UUID, getOffset(), Math.min((int) progress, 10));

    }

    public boolean isFast(BlockState s) {
        return s.is(zTags.Blocks.MINEABLE_WITH_SAW);
    }


    public void resetBreak() {
        level.destroyBlockProgress(UUID, getOffset(), -1);
        progress = -1;
    }

    public BlockPos getOffset() {
        return getBlockPos().relative(getBlockState().getValue(SawBlock.FACING));
    }

    // TODO API : rework api to support method injection on result block
    @Deprecated
    public static List<ItemStack> checkTree(Level level, BlockPos pos) {

        var state = level.getBlockState(pos);

        boolean canProcede = false;

        if (state.is(BlockTags.COMPLETES_FIND_TREE_TUTORIAL))
            for (Direction dir : Direction.values()) {
                if (level.getBlockState(pos.relative(dir)).is(BlockTags.COMPLETES_FIND_TREE_TUTORIAL)) {
                    canProcede = true;
                    break;
                }
            }

        if (canProcede) {

            ArrayList<ItemStack> itemList = new ArrayList<>();

            ArrayList<SoundEvent> souldList = new ArrayList<>();

            souldList.add(state.getSoundType(level, pos, null).getBreakSound());

            Queue<BlockPos> queue = new LinkedList<>();
            Set<BlockPos> visited = new HashSet<>();

            queue.add(pos);
            visited.add(pos);

            int checkBlocks = 0;
            boolean toolFlag = false;

            while (!queue.isEmpty()) {
                BlockPos currentPos = queue.poll();

                for (List<Integer> off : VanillaPlants.getTreeDirections()) {
                    BlockPos adjacentPos = currentPos.offset(off.get(0), off.get(1), off.get(2));
                    BlockState adjacentState = level.getBlockState(adjacentPos);

                    if (adjacentState.is(BlockTags.COMPLETES_FIND_TREE_TUTORIAL) && !visited.contains(adjacentPos)) {
                        queue.add(adjacentPos);
                        visited.add(adjacentPos);

                        Block.getDrops(adjacentState, (ServerLevel) level, adjacentPos, null)
                                .forEach(t -> itemList.add(t));

                        level.setBlockAndUpdate(adjacentPos, Blocks.AIR.defaultBlockState());

                        if (!souldList.contains(adjacentState.getSoundType(level, adjacentPos, null).getBreakSound())) {
                            souldList.add(adjacentState.getSoundType(level, adjacentPos, null).getBreakSound());
                        }
                    }
                }
                checkBlocks++;

                if (checkBlocks >= Config.TREE_CUTTING_LIMIT.get())
                    break;

                if (toolFlag)
                    break;
            }

            Block.getDrops(state, (ServerLevel) level, pos, null).forEach(t -> itemList.add(t));

            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());

            souldList.forEach(s -> level.playSound(null, pos, s, SoundSource.BLOCKS));

            return itemList;
        }

        return null;
    }

    @Override
    public void preRemoveSideEffects(BlockPos arg0, BlockState arg1) {
        resetBreak();
        super.preRemoveSideEffects(arg0, arg1);
    }

}
