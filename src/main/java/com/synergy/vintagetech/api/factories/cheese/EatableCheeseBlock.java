package com.synergy.vintagetech.api.factories.cheese;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public abstract class EatableCheeseBlock extends BaseCheeseBlock {

    public EatableCheeseBlock(Properties p) {
        super(p);
    }

    public abstract Item getItemOnUse();

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (itemStack.is(this.asItem()) && state.getValue(PIECES) == 4) {

            if (!player.isCreative())
                itemStack.shrink(1);

            level.setBlockAndUpdate(pos, state.setValue(PIECES, 8));

            return InteractionResult.SUCCESS_SERVER;
        }

        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
            BlockHitResult hitResult) {

        if (level.isClientSide())
            return InteractionResult.SUCCESS;

        var pieces = state.getValue(PIECES);

        if (pieces > 1)
            level.setBlockAndUpdate(pos, state.setValue(PIECES, pieces - 1));
        else
            level.removeBlock(pos, false);

        player.addItem(x.item(getItemOnUse()));

        return InteractionResult.SUCCESS;
    }
}
