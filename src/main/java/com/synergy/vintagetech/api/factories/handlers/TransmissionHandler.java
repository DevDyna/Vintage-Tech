package com.synergy.vintagetech.api.factories.handlers;

import com.devdyna.cakesticklib.api.aspect.logic.Tickable;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface TransmissionHandler extends Tickable {

    Level getLevel();

    BlockState getBlockState();

    default float getRotation(float partialTicks) {
        if (getLevel() == null)
            return getDeactiveSpeed();

        var speed = getBlockState().getValue(AxleHandler.ENABLED) ? getActiveSpeed() : getDeactiveSpeed();

        return ((getLevel().getGameTime() + partialTicks) * speed) % 360;
    }

    default float getActiveSpeed() {
        return 12f;
    }

    default float getDeactiveSpeed() {
        return 0f;
    }

    default boolean isAxlePowered() {
        return getBlockState().getValue(AxleHandler.ENABLED);
    }
}
