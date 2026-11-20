
package com.synergy.vintagetech.mixin;

import com.synergy.vintagetech.api.factories.handlers.RopeHandler;
import com.synergy.vintagetech.init.builder.RopeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LanternBlock.class)
public abstract class LanternPlacementMixin implements RopeHandler {

    @Inject(method = "canSurvive", at = @At("HEAD"), cancellable = true)
    private void supportRequireRopes(BlockState state, LevelReader level, BlockPos pos,
            CallbackInfoReturnable<Boolean> cir) {
        if (level.getBlockState(pos.above()).getBlock() instanceof RopeBlock)
            cir.setReturnValue(true);
    }

    @Inject(method = "getStateForPlacement", at = @At("RETURN"), cancellable = true)
    private void placeHanging(BlockPlaceContext ctx, CallbackInfoReturnable<BlockState> cir) {
        var result = cir.getReturnValue();

        if (result != null && ctx.getLevel().getBlockState(ctx.getClickedPos().above()).getBlock() instanceof RopeBlock)
            cir.setReturnValue(result.setValue(LanternBlock.HANGING, true));

    }

    @Override
    public boolean getWhenRopeConnect(BlockState state, Direction dir) {
        return state.getValue(LanternBlock.HANGING) && dir == Direction.DOWN;
    }

}
