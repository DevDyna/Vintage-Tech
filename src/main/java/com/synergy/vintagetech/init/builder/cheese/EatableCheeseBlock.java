package com.synergy.vintagetech.init.builder.cheese;

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
    protected InteractionResult useItemOn(
            ItemStack itemStack,
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            InteractionHand hand,
            BlockHitResult hitResult) {
        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }

    @Override
    protected InteractionResult useWithoutItem(
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            BlockHitResult hitResult) {

        if (level.isClientSide())
            return InteractionResult.SUCCESS;

        int pieces = state.getValue(PIECES);

        if (pieces < MAX_PIECES) {
            level.setBlockAndUpdate(
                    pos,
                    state.setValue(PIECES, pieces + 1));
        } else {
            level.removeBlock(pos, false);
        }

        player.addItem(x.item(getItemOnUse()));

        return InteractionResult.SUCCESS;
    }

}
