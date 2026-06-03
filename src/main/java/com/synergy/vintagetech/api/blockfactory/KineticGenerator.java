package com.synergy.vintagetech.api.blockfactory;

import net.minecraft.core.BlockPos;

public interface KineticGenerator {

    public record NetworkElement(BlockPos pos, boolean rotation) {
        public static NetworkElement create(BlockPos p,boolean i){
            return new NetworkElement(p, i);
        }
    }
}