package com.synergy.vintagetech.init.builder.engine;

import java.util.*;

import com.synergy.vintagetech.api.AxleHandler;
import com.synergy.vintagetech.api.blockfactory.KineticGenerator;
import com.synergy.vintagetech.api.blockfactory.transmission.TransmissionBE;
import com.synergy.vintagetech.init.builder.GearShiftBlock;
import com.synergy.vintagetech.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

public class EngineBE extends TransmissionBE implements KineticGenerator {

    private Set<BlockPos> cache = new HashSet<>();

    public EngineBE(BlockPos p, BlockState s) {
        super(zBlockEntities.ENGINE.get(), p, s);
    }

    public void tickServer() {

        update();

        if (!getBlockState().getValue(EngineBlock.ENABLED))
            return;

        Set<BlockPos> visited = new HashSet<>();
        Queue<NetworkElement> queue = new ArrayDeque<>();

        queue.add(NetworkElement.create(
                getBlockPos().relative(getBlockState().getValue(EngineBlock.HORIZONTAL_FACING)),
                false));

        while (!queue.isEmpty()) {

            var network = queue.poll();
            var currentPos = network.pos();
            var inverted = network.rotation();

            if (!visited.add(currentPos))
                continue;

            BlockState state = level.getBlockState(currentPos);

            if (!(state.getBlock() instanceof AxleHandler axle))
                continue;

            axle.setActive(level, currentPos, state, inverted);

            for (Direction out : axle.getOutputDirections(state)) {

                BlockPos nextPos = currentPos.relative(out);
                BlockState nextState = level.getBlockState(nextPos);

                if (!(nextState.getBlock() instanceof AxleHandler nextAxle))
                    continue;

                if (!nextAxle.canInputFrom(out.getOpposite(), nextState))
                    continue;

                boolean newInverted = inverted;

                if (nextState.getBlock() instanceof GearShiftBlock shift && shift.isActive(level, nextPos))
                    newInverted = !newInverted;

                // TODO BUG : dont work atm
                if (nextState.getBlock() instanceof EngineBlock)
                    explode(nextPos);

                // TODO IMP : when collide explode

                queue.add(NetworkElement.create(nextPos, newInverted));
            }
        }

        for (BlockPos oldPos : cache) {
            if (!visited.contains(oldPos)) {
                BlockState oldState = level.getBlockState(oldPos);

                if (oldState.getBlock() instanceof AxleHandler axle)
                    axle.setDeactive(level, oldPos, oldState, false);

            }
        }

        cache = visited;
    }

    public void explode(BlockPos pos) {
        level.explode(null,
                pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                0.5f, ExplosionInteraction.BLOCK);
    }

    public void update() {
        boolean state = getBlockState().getValue(EngineBlock.ENABLED);

        boolean active = CampfireBlock.isLitCampfire(
                level.getBlockState(getBlockPos().below()));

        if (state != active) {
            level.setBlockAndUpdate(
                    getBlockPos(),
                    getBlockState().setValue(EngineBlock.ENABLED, active));
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }

        if (!state)
            updateOnBreakNetwork();
    }

    protected void updateOnBreakNetwork() {
        for (BlockPos pos : cache) {
            BlockState state = level.getBlockState(pos);

            if (state.getBlock() instanceof AxleHandler axle &&
                    state.getValueOrElse(AxleHandler.ENABLED, false)) {
                axle.setDeactive(level, pos, state, false);
            }
        }

        cache.clear();
    }

    @Override
    public void setRemoved() {
        super.setRemoved();

        if (level == null || level.isClientSide())
            return;

        updateOnBreakNetwork();
    }
}