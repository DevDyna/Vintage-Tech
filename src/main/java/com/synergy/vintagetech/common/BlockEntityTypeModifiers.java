package com.synergy.vintagetech.common;

import com.synergy.vintagetech.api.factories.trees.TreeFactory;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

public class BlockEntityTypeModifiers {
    public static void register(BlockEntityTypeAddBlocksEvent event) {

        for (var tree : TreeFactory.getAll()) {
            event.modify(BlockEntityType.SIGN, tree.getSign().get(),tree.getWallSign().get());
            event.modify(BlockEntityType.HANGING_SIGN, tree.getHangingSign().get(),tree.getWallHangingSign().get());
            event.modify(BlockEntityType.SHELF, tree.getShelf().get());
        }
    }
}
