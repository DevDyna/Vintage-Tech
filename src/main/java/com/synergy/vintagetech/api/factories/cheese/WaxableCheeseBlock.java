package com.synergy.vintagetech.api.factories.cheese;

import com.synergy.vintagetech.init.builder.cheese.SealedCheeseBlock;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public abstract class WaxableCheeseBlock extends EatableCheeseBlock {

    public WaxableCheeseBlock(Properties p) {
        super(p);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (itemStack.is(zTags.Items.CHEESE_SEALER)) {

            if (!player.isCreative())
                if (itemStack.isDamageableItem())
                    itemStack.hurtAndBreak(1, player, hand);
                else
                    itemStack.shrink(1);

            level.setBlockAndUpdate(pos,
                    zBlocks.SEALED_CHEESE.get().defaultBlockState()
                            .setValue(PIECES, state.getValue(PIECES))
                            .setValue(SealedCheeseBlock.AGE, getAge(this))
                            .setValue(FACING, state.getValue(FACING)));

            return InteractionResult.SUCCESS_SERVER;
        }

        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }

}
