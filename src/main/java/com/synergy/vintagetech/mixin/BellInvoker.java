package com.synergy.vintagetech.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(BellBlock.class)
public interface BellInvoker {

  @Invoker("getConnectedDirection")
    Direction getConnectedDir(BlockState state);

}
