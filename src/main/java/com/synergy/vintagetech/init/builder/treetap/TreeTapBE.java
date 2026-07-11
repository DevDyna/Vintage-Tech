package com.synergy.vintagetech.init.builder.treetap;

import java.util.Optional;

import com.devdyna.cakesticklib.api.RandomUtil;
import com.devdyna.cakesticklib.api.aspect.templates.TickingBE;
import com.synergy.vintagetech.api.recipeinput.TreeTapInput;
import com.synergy.vintagetech.init.builder.evaporation_basin.EvaporationBasinBE;
import com.synergy.vintagetech.init.builder.treetap.recipe.TreeTapRecipe;
import com.synergy.vintagetech.init.types.zBlockEntities;
import com.synergy.vintagetech.init.types.zRecipeTypes;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.transfer.ResourceHandlerUtil;
import net.neoforged.neoforge.transfer.fluid.FluidResource;

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

            var facing = state.getValue(TreeTapBlock.FACING);

            var trunk = pos.relative(facing.getOpposite());

            if (!level.getBlockState(trunk).is(zTags.Blocks.TREE_TAP_LOGS))
                return;

            var log = level.getBlockState(trunk);

            for (int y = 0; y < 32 && leaves == null; y++) {

                var current = trunk.above(y);

                if (!level.getBlockState(current).is(log.getBlock()))
                    break;

                for (BlockPos offset : BlockPos.betweenClosed(
                        current.offset(-2, 0, -2),
                        current.offset(2, 0, 2))) {

                    var related = level.getBlockState(offset);

                    if (!related.is(zTags.Blocks.TREE_TAP_LEAVES))
                        continue;

                    leaves = related;
                    break;

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

            ResourceHandlerUtil.insertStacking(basin.getFluidStorage(), FluidResource.of(recipe.getFluid()),
                    recipe.getFluid().amount(), null);

        }

    }

}
