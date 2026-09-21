package com.synergy.vintagetech.common;

import com.synergy.vintagetech.api.factories.trees.TreeFactory;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

public class BlockEntityTypeModifiers {
    public static void register(BlockEntityTypeAddBlocksEvent event) {

        for (var tree : TreeFactory.getAll()) {
            event.modify(BlockEntityType.SIGN, tree.sign().get(),tree.wallSign().get());
            event.modify(BlockEntityType.HANGING_SIGN, tree.hangingSign().get(),tree.wallHangingSign().get());
            event.modify(BlockEntityType.SHELF, tree.shelf().get());
        }
    }
}
