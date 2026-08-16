package com.synergy.vintagetech.init.builder.cheese;

import com.synergy.vintagetech.api.blockfactory.cheese.WaxableCheeseBlock;
import com.synergy.vintagetech.init.types.zItems;

import net.minecraft.world.item.Item;

public class MaturedCheeseBlock extends WaxableCheeseBlock {

    public MaturedCheeseBlock(Properties p) {
        super(p);
    }

    @Override
    public Item getItemOnUse() {
        return zItems.MATURED_CHEESE_SLICE.get();
    }

}
