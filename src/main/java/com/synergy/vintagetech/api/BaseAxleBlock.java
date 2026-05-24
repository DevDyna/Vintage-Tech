package com.synergy.vintagetech.api;

import org.jspecify.annotations.Nullable;

import com.synergy.vintagetech.init.builder.axle.KineticBE;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

public abstract class BaseAxleBlock extends Block implements AxleHandler{
    
    public BaseAxleBlock(Properties p) {
        super(p.pushReaction(PushReaction.BLOCK));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new KineticBE(p, s);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

}
