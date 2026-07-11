package com.synergy.vintagetech.init.builder.plants;

import java.util.ArrayList;
import java.util.List;

import com.devdyna.cakesticklib.api.RandomUtil;
import com.devdyna.cakesticklib.api.factories.plants.builder.BaseCropBlock;
import com.synergy.vintagetech.init.types.zItems;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Hemp extends BaseCropBlock {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    public static final int MAX_AGE = 3;

    public static final int[] HEIGHT_BY_AGE = new int[] { 2, 7, 12, 16 };

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getAge(BlockState state) {
        return state.getValue(this.getAgeProperty());
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    public Hemp(Properties p) {
        super(p);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.HEMP_SEEDS.get();
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(zTags.Blocks.SUPPORT_HEMP_PLANT)
                && (state.is(this) ? isMaxAge(state) : true);
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        if (RandomUtil.chance(level, 75))
            if (!isMaxAge(state)) {
                super.performBonemeal(level, random, pos, state);
            } else  {
                ParticleUtils.spawnParticleInBlock(level, pos.above(), 6 + random.nextInt(10),
                        ParticleTypes.HAPPY_VILLAGER);
                if (!hasAbove(level, pos))
                    level.setBlockAndUpdate(pos.above(), this.defaultBlockState());
                else
                    super.performBonemeal(level, random, pos.above(), level.getBlockState(pos.above()));
            }
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return !isFullyMature((Level) level, pos);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

        var below = level.getBlockState(pos.below());
        var above = level.getBlockState(pos.above());

        if (!isMaxAge(state)) {
            if (below.is(this) ? isMaxAge(below) : true)
                super.randomTick(state, level, pos, random);
            return;
        }

        // if (hasAbove(level, pos) && isMaxAge(state))
        //     super.randomTick(above, level, pos.above(), random);

        if (above.canBeReplaced() && !below.is(this))
            level.setBlockAndUpdate(pos.above(), this.defaultBlockState());

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Block.boxes(3, age -> Block.column(6.0, 0.0, HEIGHT_BY_AGE[age]))[getAge(state)];
    }

    @Override
    public boolean canHarvest(Context ctx) {

        var level = ctx.level();
        var pos = ctx.pos();

        // when player is null OR when is bonemealling -> automation interaction
        if (ctx.player() == null || ctx.player().getMainHandItem().getItem() instanceof BoneMealItem)
            return isFullyMature(level, pos);

        if (isDouble(level, pos)) {

            if (hasBelow(level, pos))
                return isMaxAge(ctx.state(Direction.DOWN));
        }

        // above can be ignored due it will be always max age if has above part

        return super.canHarvest(ctx);
    }

    @Override
    public List<ItemStack> getItemsResult(Context ctx) {
        var level = ctx.level();
        var pos = ctx.pos();

        List<ItemStack> result = new ArrayList<>();

        if (isMaxAge(ctx.state()))
            result.addAll(super.getItemsResult(ctx));

        if (isDouble(level, pos)) {

            if (hasBelow(level, pos) && isMaxAge(ctx.state(Direction.DOWN)))
                result.addAll(super.getItemsResult(ctx.modify(pos.below())));

            if (hasAbove(level, pos) && isMaxAge(ctx.state(Direction.UP)))
                result.addAll(super.getItemsResult(ctx.modify(pos.above())));

        }

        return result;
    }

    @Override
    public void replant(Context ctx) {
        var level = ctx.level();
        var pos = ctx.pos();

        if (isDouble(level, pos)) {
            if (hasBelow(level, pos)) {
                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                simpleAgeResetReplant(ctx.modify(pos.below()));
            }

            if (hasAbove(level, pos)) {
                level.setBlockAndUpdate(pos.above(), Blocks.AIR.defaultBlockState());
                simpleAgeResetReplant(ctx);
            }

        } else
            simpleAgeResetReplant(ctx);

    }

    private boolean hasAbove(Level l, BlockPos p) {
        return l.getBlockState(p.above()).is(this);
    }

    private boolean hasBelow(Level l, BlockPos p) {
        return l.getBlockState(p.below()).is(this);
    }

    private boolean isDouble(Level l, BlockPos p) {
        return hasAbove(l, p) || hasBelow(l, p);
    }

    private boolean isFullyMature(Level l, BlockPos p) {
        return isDouble(l, p)
                && isMaxAge(l.getBlockState(p))
                && isMaxAge(l.getBlockState(p.relative(
                        hasAbove(l, p)
                                ? Direction.UP
                                : Direction.DOWN)));
    }

}
