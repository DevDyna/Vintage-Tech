package com.synergy.vintagetech.api.factories.handlers;

import net.minecraft.core.BlockPos;

public interface GeneratorHandler {

    /**
     * Create a basic element to store blockpos and rotation direction to trasmitt
     * across the network
     */
    public record NetworkElement(BlockPos pos, NetworkState state) {

        public static NetworkElement create(BlockPos pos, boolean activation, boolean rotation) {
            return new NetworkElement(pos, NetworkState.of(activation, rotation));
        }

    }

    public record NetworkState(boolean active, boolean rotation) {
        public static NetworkState of(boolean activation, boolean rotation) {
            return new NetworkState(activation, rotation);
        }
    }
}