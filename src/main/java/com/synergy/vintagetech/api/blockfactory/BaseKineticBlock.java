package com.synergy.vintagetech.api.blockfactory;

import org.jspecify.annotations.Nullable;

import com.synergy.vintagetech.api.AxleHandler;
import com.synergy.vintagetech.api.blockfactory.transmission.TransmissionBE;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

public abstract class BaseKineticBlock extends Block implements AxleHandler {

    public BaseKineticBlock(Properties p) {
        super(p.pushReaction(PushReaction.BLOCK).noOcclusion());
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new TransmissionBE(p, s);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    // *if it work , DONT TOUCH IT*
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state;
    }

    public BlockState mirror(BlockState state, Mirror mirror) {
        return state;
    }

}
