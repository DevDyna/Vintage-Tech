package com.synergy.vintagetech.init.builder.cheese;

import com.devdyna.cakesticklib.setup.registry.LibTags;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zItems;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class PlainCheeseBlock extends EatableCheeseBlock {

    public PlainCheeseBlock(Properties p) {
        super(p);
    }

    @Override
    public Item getItemOnUse() {
        return zItems.PLAIN_CHEESE_SLICE.get();
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {

        // TODO API : jei custom waxing and scrapping
        if (itemStack.is(LibTags.Items.WAXING)) {
            if (itemStack.isDamageableItem())
                itemStack.hurtAndBreak(1, player, hand);
            else
                itemStack.shrink(1);
            level.setBlockAndUpdate(pos, zBlocks.SEALED_CHEESE.get().defaultBlockState().setValue(BaseCheeseBlock.PIECES, state.getValue(PIECES)));

            return InteractionResult.SUCCESS_SERVER;
        }

        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }

}
