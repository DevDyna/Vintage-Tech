package com.synergy.vintagetech.init.builder.engine;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import javax.annotation.Nullable;

import com.devdyna.cakesticklib.api.aspect.logic.MachineFluidAutomation;
import com.devdyna.cakesticklib.api.aspect.logic.SimpleFluidStorage;
import com.devdyna.cakesticklib.api.aspect.templates.TickingBE;
import com.devdyna.cakesticklib.api.aspect.templates.storage.fluid.TickingTankBE;
import com.devdyna.cakesticklib.api.aspect.templates.storage.fluid.TickingTankBlock;
import com.devdyna.cakesticklib.setup.registry.LibHandlers;
import com.mojang.logging.LogUtils;
import com.synergy.vintagetech.api.AxleHandler;
import com.synergy.vintagetech.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;

public class EngineBE extends TickingBE //implements SimpleFluidStorage
 {

    public EngineBE(BlockPos p, BlockState s) {
        super(zBlockEntities.ENGINE.get(), p, s);
    }

    private Set<BlockPos> lastNetwork = new HashSet<>();

    private static final int FLUID_COST = 10;

    @Override
    public void tickServer() {

        update();

        if (!getBlockState().getValue(EngineBlock.ENABLED))
            return;

        

        Direction startDir = getBlockState().getValue(EngineBlock.FACING);
        BlockPos startPos = getBlockPos().relative(startDir);

        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> queue = new ArrayDeque<>();

        queue.add(startPos);

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

        for (BlockPos oldPos : lastNetwork) {
            if (!visited.contains(oldPos)) {
                BlockState oldState = level.getBlockState(oldPos);

                if (oldState.getBlock() instanceof AxleHandler) {
                    level.setBlock(oldPos,
                            oldState.setValue(AxleHandler.ENABLED, false),
                            3);
                }
            }
        }

        lastNetwork = visited;

        // try (var tx = Transaction.openRoot()) {
        //     if (getFluidStorage().getResource(0).isEmpty()) {
        //         tx.close();
        //         return;
        //     }

        //     getFluidStorage().extract(0, getFluidStorage().getResource(0), FLUID_COST, tx);
        //     tx.commit();
        // }
    }

    public void update() {
    boolean state = getBlockState().getValue(EngineBlock.ENABLED);

    boolean active =
            CampfireBlock.isLitCampfire(level.getBlockState(getBlockPos().below()))
            // && getFluidStorage().getAmountAsInt(0) > FLUID_COST
            // && !getFluidStorage().getResource(0).isEmpty()
            ;

    if (state != active) {
        level.setBlockAndUpdate(
                getBlockPos(),
                getBlockState().setValue(EngineBlock.ENABLED, active)
        );
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
    }
}

    // @Override
    // public FluidStacksResourceHandler getFluidStorage() {
    //     return getData(LibHandlers.FLUID_STORAGE);
    // }

    // @Override
    // public int getTankCapacity() {
    //     return 10_000;
    // }

    // @Override
    // public int getTanks() {
    //     return 1;
    // }

    // protected void saveAdditional(ValueOutput output) {
    //     this.getFluidStorage().serialize(output);
    //     super.saveAdditional(output);
    // }

    // protected void loadAdditional(ValueInput input) {
    //     this.getFluidStorage().deserialize(input);
    //     super.loadAdditional(input);
    // }

    // @Nullable
    // public Packet<ClientGamePacketListener> getUpdatePacket() {
    //     return ClientboundBlockEntityDataPacket.create(this);
    // }

    // public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
    //     return this.saveWithoutMetadata(pRegistries);
    // }


@Override
public void setRemoved() {
    super.setRemoved();

    if (level == null || level.isClientSide())
        return;

    for (BlockPos pos : lastNetwork) {
        BlockState state = level.getBlockState(pos);

        if (state.getBlock() instanceof AxleHandler
                && state.hasProperty(AxleHandler.ENABLED)
                && state.getValue(AxleHandler.ENABLED)) {

            level.setBlock(
                    pos,
                    state.setValue(AxleHandler.ENABLED, false),
                    3
            );
        }
    }

    lastNetwork.clear();
}

}