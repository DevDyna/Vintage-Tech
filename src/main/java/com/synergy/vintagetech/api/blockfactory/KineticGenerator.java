package com.synergy.vintagetech.api.blockfactory;

import net.minecraft.core.BlockPos;

public interface KineticGenerator {

    /**
     * Create a basic element to store blockpos and rotation direction to trasmitt across the network
     */
    public record NetworkElement(BlockPos pos, boolean rotation) {

        public static NetworkElement create(BlockPos pos, boolean rotation) {
            return new NetworkElement(pos, rotation);
        }

    }
}