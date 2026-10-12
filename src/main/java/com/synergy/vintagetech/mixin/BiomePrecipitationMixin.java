
package com.synergy.vintagetech.mixin;

import com.synergy.vintagetech.init.builder.SaltLayerBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public abstract class BiomePrecipitationMixin {

    @Inject(method = "shouldFreeze(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z", at = @At("HEAD"), cancellable = true)
    private void preventFreezing(LevelReader level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (hasSaltNear(level, pos))
            cir.setReturnValue(false);
    }

    @Inject(method = "shouldFreeze(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Z)Z", at = @At("HEAD"), cancellable = true)
    private void preventFreezingNeighbors(LevelReader level, BlockPos pos, boolean checkNeighbors,
            CallbackInfoReturnable<Boolean> cir) {
        if (hasSaltNear(level, pos))
            cir.setReturnValue(false);
    }

    @Inject(method = "shouldSnow(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z", at = @At("HEAD"), cancellable = true)
    private void preventSnowing(LevelReader level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (hasSaltNear(level, pos))
            cir.setReturnValue(false);
    }

    private boolean hasSaltNear(LevelReader l, BlockPos p) {
        for (var pos : SaltLayerBlock.getMeltingArea(p))
            if (l.getBlockState(pos).getBlock() instanceof SaltLayerBlock)
                return true;

        return false;
    }
}
