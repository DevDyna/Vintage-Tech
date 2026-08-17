package com.synergy.vintagetech.api.factories.engine;

import java.util.*;

import com.synergy.vintagetech.api.factories.BaseKineticBlock;
import com.synergy.vintagetech.api.factories.handlers.AxleHandler;
import com.synergy.vintagetech.api.factories.handlers.GeneratorHandler;
import com.synergy.vintagetech.api.factories.transmission.MotionAlteratorBlock;
import com.synergy.vintagetech.api.factories.transmission.TransmissionBE;
import com.synergy.vintagetech.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BaseEngineBE extends TransmissionBE implements GeneratorHandler {

    public BaseEngineBE(BlockEntityType<? extends BaseEngineBE> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public BaseEngineBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.CREATIVE_ENGINE.get(), pos, state);
    }

    private Map<BlockPos, Boolean> cache = new HashMap<>();

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

        Map<BlockPos, Boolean> visited = new HashMap<>();
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

            if (visited.containsKey(currentPos))
                continue;

            visited.put(currentPos, inverted);

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

                queue.add(NetworkElement.create(nextPos, newActive, newInverted));
            }
        }

        for (var entry : cache.entrySet()) {
            var oldPos = entry.getKey();

            if (!visited.containsKey(oldPos)) {

                var oldState = level.getBlockState(oldPos);

                if (oldState.getBlock() instanceof AxleHandler axle)
                    axle.setDeactive(level, oldPos, oldState, entry.getValue());
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

        for (var entry : cache.entrySet()) {

            var pos = entry.getKey();
            var state = level.getBlockState(pos);

            if (state.getBlock() instanceof AxleHandler axle && state.getValue(AxleHandler.ENABLED))
                axle.setDeactive(level, pos, state, entry.getValue());

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