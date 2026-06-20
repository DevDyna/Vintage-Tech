package com.synergy.vintagetech.init.builder.millstone;

import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.api.blockfactory.MonoDirectionalAxleBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class MillstoneBlock extends MonoDirectionalAxleBlock {

    public MillstoneBlock(Properties p) {
        super(p);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return this.defaultBlockState()
                .setValue(ENABLED, false)
                .setValue(INVERTED, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(ENABLED,INVERTED);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.25, 0.3125, 0.25, 0.75, 0.4375, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 1, 0.3125, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.4375, 0.3125, 0.6875, 0.625, 0.6875), BooleanOp.OR);
        return shape;

    }

    @Override
    public Map<Direction, Boolean> getAxis(BlockState state) {
        return Map.of(Direction.UP, state.getValue(INVERTED));
    }

    @Override
    public Direction getInputRotation(BlockState state) {
        return Direction.DOWN;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new MillstoneBE(p, s);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level l, BlockState s, BlockEntityType<T> ty) {
        return (lvl, pos, b, t) -> {
            if (t instanceof MillstoneBE be) {

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
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {

        if (entity instanceof ItemEntity item
                && level.getBlockEntity(pos) instanceof MillstoneBE millstone
                && !millstone.isFull()) {

            try (var tx = Transaction.openRoot()) {

                var copy = item.getItem().copy();

                var insered = millstone.getItemStorage().insert(MillstoneBE.INPUT, ItemResource.of(copy), copy.count(),
                        tx);

                if (insered > 0) {
                    if (insered >= item.getItem().count()) {
                        item.setItem(ItemStack.EMPTY);
                        item.discard();
                    } else
                        item.setItem(x.item(item.getItem().getItem(), item.getItem().count() - insered));

                    tx.commit();
                }

                tx.close();
            }

        }

        super.fallOn(level, state, pos, entity, fallDistance);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof MillstoneBE be)
            return be.itemUseOn(player, level, pos, hand);
        return InteractionResult.FAIL;
    }

}
