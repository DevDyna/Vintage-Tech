package com.synergy.vintagetech.api.blockfactory.engine;

import java.util.*;

import com.synergy.vintagetech.api.AxleHandler;
import com.synergy.vintagetech.api.blockfactory.BaseKineticBlock;
import com.synergy.vintagetech.api.blockfactory.KineticGenerator;
import com.synergy.vintagetech.api.blockfactory.transmission.MotionAlteratorBlock;
import com.synergy.vintagetech.api.blockfactory.transmission.TransmissionBE;
import com.synergy.vintagetech.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BaseEngineBE extends TransmissionBE implements KineticGenerator {

    public BaseEngineBE(BlockEntityType<? extends BaseEngineBE> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public BaseEngineBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.CREATIVE_ENGINE.get(), pos, state);
    }

    private Set<BlockPos> cache = new HashSet<>();

    public BaseEngineBlock getBlock() {
        return (BaseEngineBlock) getBlockState().getBlock();
    }

    /**
     * If you want to add activation conditions use
     * {@code getWhenActive(Level, BlockPos, BlockState)} intend of this!
     */
    public void tickServer() {

        var pos = getBlockPos();
        var state = getBlockState();

        update();

        if (!state.getValue(BaseEngineBlock.ENABLED))
            return;

        // TODO IMP : rework to use QueueUtils

        Set<BlockPos> visited = new HashSet<>();
        Queue<NetworkElement> queue = new ArrayDeque<>();

        for (Direction dir : getBlock().getGenDirections(level, pos, state))
            if (level.getBlockState(pos.relative(dir)).getBlock() instanceof BaseKineticBlock axle)
                if (axle.canInputFrom(dir, level.getBlockState(pos.relative(dir))))
                    queue.add(NetworkElement.create(pos.relative(dir), getBlock().getWhenActive(level, pos, state),
                            getBlock().getDefaultRotationState()));

        while (!queue.isEmpty()) {

            var network = queue.poll();
            var currentPos = network.pos();
            var inverted = network.state().rotation();
            var active = network.state().active();

            if (!visited.add(currentPos))
                continue;

            var offsetstate = level.getBlockState(currentPos);

            // TODO IMP : when collide explode

            if (!(offsetstate.getBlock() instanceof AxleHandler axle))
                continue;

            if (active)
                axle.setActive(level, currentPos, offsetstate, inverted);
            else
                axle.setDeactive(level, currentPos, offsetstate, inverted);

            for (Direction out : axle.getOutputDirections(offsetstate)) {

                var nextPos = currentPos.relative(out);
                var nextState = level.getBlockState(nextPos);

                if (!(nextState.getBlock() instanceof AxleHandler nextAxle))
                    continue;

                if (!nextAxle.canInputFrom(out.getOpposite(), nextState))
                    continue;

                var newActive = active;
                var newInverted = inverted;

                if (nextState.getBlock() instanceof MotionAlteratorBlock alterator) {
                    var result = alterator.modifyNetwork(
                            level,
                            nextPos,
                            nextState,
                            NetworkState.of(newActive, newInverted));

                    newActive = result.active();
                    newInverted = result.rotation();
                }

                // if (nextState.getBlock() instanceof GearShiftBlock shift &&
                // shift.isActive(level, nextPos))
                // newInverted = !newInverted;

                queue.add(NetworkElement.create(nextPos, newActive, newInverted));
            }
        }

        for (BlockPos oldPos : cache)
            if (!visited.contains(oldPos)) {
                var oldState = level.getBlockState(oldPos);

                if (oldState.getBlock() instanceof AxleHandler axle)
                    axle.setDeactive(level, oldPos, oldState, false);

            }

        cache = visited;
    }

    public void explode(BlockPos pos) {
        level.explode(null,
                pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                0.5f, ExplosionInteraction.BLOCK);
    }

    public void update() {
        var state = getBlockState().getValue(BaseEngineBlock.ENABLED);

        var active = getBlock().getWhenActive(level, getBlockPos(), getBlockState());

        if (state != active) {
            level.setBlockAndUpdate(
                    getBlockPos(),
                    getBlockState().setValue(BaseEngineBlock.ENABLED, active));
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }

        if (!state)
            updateOnBreakNetwork();
    }

    protected void updateOnBreakNetwork() {
        for (BlockPos pos : cache) {
            var state = level.getBlockState(pos);

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