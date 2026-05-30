package com.synergy.vintagetech.init.builder.engine;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import com.devdyna.cakesticklib.api.aspect.logic.SimpleFluidStorage;
import com.devdyna.cakesticklib.setup.registry.LibHandlers;
import com.synergy.vintagetech.api.AxleHandler;
import com.synergy.vintagetech.api.blockfactory.transmission.TransmissionBE;
import com.synergy.vintagetech.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class EngineBE extends TransmissionBE implements SimpleFluidStorage {

    public EngineBE(BlockPos p, BlockState s) {
        super(zBlockEntities.ENGINE.get(), p, s);
    }

    private Set<BlockPos> cache = new HashSet<>();

    private static final int FLUID_COST = 10;//TODO rework

    public void tickServer() {

        update();

        if (!getBlockState().getValue(EngineBlock.ENABLED))
            return;

        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> queue = new ArrayDeque<>();

        queue.add(getBlockPos().relative(getBlockState().getValue(EngineBlock.HORIZONTAL_FACING)));

        while (!queue.isEmpty()) {

            BlockPos currentPos = queue.poll();

            if (!visited.add(currentPos))
                continue;

            BlockState state = level.getBlockState(currentPos);

            if (!(state.getBlock() instanceof AxleHandler axle))
                continue;

            axle.setPowered(level, currentPos, state);

            for (Direction out : axle.getOutputDirections(state)) {

                BlockPos nextPos = currentPos.relative(out);
                BlockState nextState = level.getBlockState(nextPos);

                if (!(nextState.getBlock() instanceof AxleHandler nextAxle))
                    continue;

                if (!nextAxle.canInputFrom(out.getOpposite(), nextState))
                    continue;

                queue.add(nextPos);
            }
        }

        for (BlockPos oldPos : cache)
            if (!visited.contains(oldPos))
                if (level.getBlockState(oldPos).getBlock() instanceof AxleHandler)
                    level.setBlockAndUpdate(oldPos, level.getBlockState(oldPos).setValue(AxleHandler.ENABLED, false));

        cache = visited;

        try (var tx = Transaction.openRoot()) {
            if (getFluidStorage().getResource(0).isEmpty()) {
                tx.close();
                return;
            }

            getFluidStorage().extract(0, getFluidStorage().getResource(0), FLUID_COST, tx);
            tx.commit();
        }
    }

    public void update() {
        boolean state = getBlockState().getValue(EngineBlock.ENABLED);

        boolean active = CampfireBlock.isLitCampfire(level.getBlockState(getBlockPos().below()))
                && getFluidStorage().getAmountAsInt(0) > FLUID_COST
                && !getFluidStorage().getResource(0).isEmpty();

        if (state != active) {
            level.setBlockAndUpdate(
                    getBlockPos(),
                    getBlockState().setValue(EngineBlock.ENABLED, active));
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    @Override
    public FluidStacksResourceHandler getFluidStorage() {
        return getData(LibHandlers.FLUID_STORAGE);
    }

    @Override
    public int getTankCapacity() {
        return 10_000;
    }

    @Override
    public int getTanks() {
        return 1;
    }

    public void saveAdditional(ValueOutput output) {
        this.getFluidStorage().serialize(output);
        super.saveAdditional(output);
    }

    public void loadAdditional(ValueInput input) {
        this.getFluidStorage().deserialize(input);
        super.loadAdditional(input);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();

        if (level == null || level.isClientSide())
            return;

        for (BlockPos pos : cache) {
            BlockState state = level.getBlockState(pos);

            if (state.getBlock() instanceof AxleHandler &&
                    state.getValueOrElse(AxleHandler.ENABLED, false))
                level.setBlockAndUpdate(pos, state.setValue(AxleHandler.ENABLED, false));

        }

        cache.clear();
    }

}