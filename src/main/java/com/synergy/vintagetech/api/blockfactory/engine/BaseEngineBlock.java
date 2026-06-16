package com.synergy.vintagetech.api.blockfactory.engine;

import java.util.List;
import org.jspecify.annotations.Nullable;

import com.synergy.vintagetech.api.blockfactory.BaseKineticBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BaseEngineBlock extends BaseKineticBlock {

    public BaseEngineBlock(Properties p) {
        super(p);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level l, BlockState s, BlockEntityType<T> ty) {
        return (lvl, pos, b, t) -> {
            if (t instanceof BaseEngineBE be) {

                if (l == null)
                    return;

                be.tickBoth();
                if (l.isClientSide())
                    be.tickClient();
                else
                    be.tickServer();

            }

        };
    }

    @Override
    public boolean canInputFrom(Direction dir, BlockState state) {
        return false;
    }

    @Override
    public List<Direction> getOutputDirections(BlockState state) {
        return List.of();
    }

    /**
     * Define rotation inversion<br/>
     * <br/>
     * Atm not mostly useful except for define init rotation
     */
    public boolean getDefaultRotationState() {
        return false;
    }

    /**
     * Define generator output directions to propagate motion across the network
     */
    public abstract List<Direction> getGenDirections(Level level, BlockPos pos, BlockState state);

    /**
     * Define when active the generator
     */
    public abstract boolean getWhenActive(Level level, BlockPos pos, BlockState state);

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        if (level.getBlockEntity(pos) instanceof BaseEngineBE engine)
            engine.setRemoved();
        super.destroy(level, pos, state);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new BaseEngineBE(p, s);
    }

}
