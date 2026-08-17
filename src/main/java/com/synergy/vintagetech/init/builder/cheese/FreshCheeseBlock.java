package com.synergy.vintagetech.init.builder.cheese;

import com.synergy.vintagetech.api.factories.cheese.WaxableCheeseBlock;
import com.synergy.vintagetech.init.types.zItems;

import net.minecraft.world.item.Item;

public class FreshCheeseBlock extends WaxableCheeseBlock {

    public FreshCheeseBlock(Properties p) {
        super(p);
    }

    @Override
    public Item getItemOnUse() {
        return zItems.FRESH_CHEESE_SLICE.get();
    }

}
