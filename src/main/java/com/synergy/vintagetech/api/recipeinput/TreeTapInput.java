package com.synergy.vintagetech.api.recipeinput;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.block.state.BlockState;

public record TreeTapInput(BlockState log, BlockState leaves) implements RecipeInput {

    @Override
    public ItemStack getItem(int index) {
        return x.item(Items.OAK_LOG);
    }

    @Override
    public int size() {
        return 2;
    }

}
