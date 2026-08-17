package com.synergy.vintagetech.init.builder.windmill;

import java.util.List;
import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.devdyna.cakesticklib.api.RandomUtil;
import com.synergy.vintagetech.api.factories.engine.BaseEngineBlock;
import com.synergy.vintagetech.api.factories.handlers.RotableAxleBlock;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WindmillBlock extends BaseEngineBlock implements RotableAxleBlock {

    public static final BooleanProperty CRACKED = BlockStateProperties.CRACKED;

    public WindmillBlock(Properties p) {
        super(p.randomTicks());
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return !state.getValue(CRACKED);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.getValue(CRACKED))
            if (level.isRaining() && level.canSeeSky(pos))
                if (RandomUtil.chance(level, 0.25f))
                    level.setBlockAndUpdate(pos, state.setValue(CRACKED, true));
    }

    @Override
    protected InteractionResult useItemOn(ItemStack item, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (item.is(zTags.Items.WINDMILL_REPAIR) && state.getValue(CRACKED)) {
            item.shrink(1);
            level.setBlockAndUpdate(pos, state.setValue(CRACKED, false));
            level.playSound(player, pos, SoundEvents.SMITHING_TABLE_USE, SoundSource.BLOCKS);
            return InteractionResult.SUCCESS_SERVER;
        }

        return super.useItemOn(item, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> Shapes.box(0.3125, 0.3125, 0, 0.6875, 0.6875, 0.5);
            case NORTH -> Shapes.box(0.3125, 0.3125, 0.5, 0.6875, 0.6875, 1);
            case EAST -> Shapes.box(0, 0.3125, 0.3125, 0.5, 0.6875, 0.6875);
            case WEST -> Shapes.box(0.5, 0.3125, 0.3125, 1, 0.6875, 0.6875);
            case DOWN -> Shapes.box(0.3125, 0.5, 0.3125, 0.6875, 1, 0.6875);
            case UP -> Shapes.box(0.3125, 0, 0.3125, 0.6875, 0.5, 0.6875);
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return this.defaultBlockState()
                .setValue(CRACKED, false)
                .setValue(ENABLED, false)
                .setValue(FACING, c.getClickedFace());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(FACING, ENABLED, CRACKED);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new WindMillBE(p, s);
    }

    @Override
    public Map<Direction, Boolean> getAxis(BlockState state) {
        return Map.of((state.getValue(FACING).getAxis() == Axis.Y ? state.getValue(FACING)
                : state.getValue(FACING).getOpposite()), false);
    }

    @Override
    public List<Direction> getGenDirections(Level level, BlockPos pos, BlockState state) {
        return List.of(state.getValue(FACING).getOpposite());
    }

    @Override
    public boolean getWhenActive(Level level, BlockPos pos, BlockState state) {

        if (state.getValue(CRACKED))
            return false;

        var facing = state.getValue(FACING);

        var start = pos.relative(facing);

        Direction horizontal = facing.getAxis() == Direction.Axis.Y ? Direction.EAST : facing.getClockWise();
        Direction vertical = facing.getAxis() == Direction.Axis.Y ? Direction.SOUTH : Direction.UP;

        for (int x = -2; x <= 2; x++) {
            for (int y = -2; y <= 2; y++) {

                var point = start
                        .relative(horizontal, x)
                        .relative(vertical, y);

                if (level.getBlockState(point).is(this))
                    continue;

                if (!level.getBlockState(point).isAir())
                    return false;
            }
        }

        return true;
    }

}
