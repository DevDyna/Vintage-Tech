package com.synergy.vintagetech.init.builder.cheese;

import com.synergy.vintagetech.api.factories.cheese.EatableCheeseBlock;
import com.synergy.vintagetech.init.types.zItems;

import net.minecraft.world.item.Item;

public class AgedCheeseBlock extends EatableCheeseBlock {

    public AgedCheeseBlock(Properties p) {
        super(p);
    }

    @Override
    public Item getItemOnUse() {
        return zItems.AGED_CHEESE_SLICE.get();
    }

}
