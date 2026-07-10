package com.synergy.vintagetech.init.builder.treetap;

import java.util.Optional;

import com.devdyna.cakesticklib.api.RandomUtil;
import com.devdyna.cakesticklib.api.aspect.templates.TickingBE;
import com.synergy.vintagetech.api.recipeinput.TreeTapInput;
import com.synergy.vintagetech.init.builder.evaporation_basin.EvaporationBasinBE;
import com.synergy.vintagetech.init.builder.treetap.recipe.TreeTapRecipe;
import com.synergy.vintagetech.init.types.zBlockEntities;
import com.synergy.vintagetech.init.types.zRecipeTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class TreeTapBE extends TickingBE {

    public TreeTapBE(BlockPos arg1, BlockState arg2) {
        super(zBlockEntities.TREE_TAP.get(), arg1, arg2);
    }

    @Override
    public void tickServer() {

        var pos = getBlockPos();
        var state = getBlockState();

        if (level.getBlockEntity(pos.below()) instanceof EvaporationBasinBE basin) {

            BlockState leaves = null;

            var trunk = pos.relative(state.getValue(TreeTapBlock.FACING));

            if (!level.getBlockState(trunk).is(BlockTags.LOGS))
                return;

            var log = level.getBlockState(trunk);

            for (int y = 0; y < 32 && leaves == null; y++) {
                var current = trunk.above(y);

                if (!level.getBlockState(current).is(BlockTags.LOGS))
                    break;

                for (int dx = -2; dx <= 2 && leaves == null; dx++)
                    for (int dz = -2; dz <= 2 && leaves == null; dz++)
                        if (level.getBlockState(current.offset(dx, 0, dz))
                                .is(BlockTags.LEAVES)) {
                            leaves = level.getBlockState(current.offset(dx, 0, dz));
                        }

            }

            if (leaves == null)
                return;

            Optional<RecipeHolder<TreeTapRecipe>> r = level.getServer().getRecipeManager()
                    .getRecipeFor(zRecipeTypes.TREE_TAP.getType(),
                            new TreeTapInput(log, leaves), level);

            if (r.isEmpty())
                return;

            var recipe = r.get().value();

            if ((level.getGameTime() + getBlockPos().asLong()) % recipe.getDelay() != 0)
                return;

            if (!RandomUtil.chance(level, recipe.getChance()))
                return;

            try (var tx = Transaction.openRoot()) {
                if (basin.getFluidStorage().getResource(0).is(recipe.getFluid().create().getFluidType())
                        && basin.getFluidStorage().getAmountAsInt(0) < basin.getFluidStorage().getCapacityAsInt(0,
                                basin.getFluidStorage().getResource(0))) {
                    basin.getFluidStorage().insert(0, FluidResource.of(recipe.getFluid()), recipe.getFluid().amount(),
                            tx);
                    tx.commit();
                }
            }

        }

    }

    // TODO API : unify to api?
    public static boolean isFacingTree(Level level, BlockPos pos, Direction facing) {
        var trunk = pos.relative(facing);

        if (!level.getBlockState(trunk).is(BlockTags.LOGS))
            return false;

        for (int y = 0; y < 32; y++) {
            var current = trunk.above(y);
            var state = level.getBlockState(current);

            if (!state.is(BlockTags.LOGS))
                break;

            for (int dx = -2; dx <= 2; dx++)
                for (int dz = -2; dz <= 2; dz++)
                    if (level.getBlockState(current.offset(dx, 0, dz))
                            .is(BlockTags.LEAVES))
                        return true;

        }

        return false;
    }

}
