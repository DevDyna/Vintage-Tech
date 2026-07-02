package com.synergy.vintagetech.init.builder.mechanical_farmland;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.devdyna.cakesticklib.api.aspect.logic.BucketInteraction;
import com.devdyna.cakesticklib.api.aspect.logic.FluidClearableTank;
import com.devdyna.cakesticklib.api.aspect.logic.FluidTooltipWhenEmpty;
import com.devdyna.cakesticklib.api.aspect.templates.TickingBE;
import com.synergy.vintagetech.api.AxleHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;

public class MechanicalFarmlandBlock extends FarmlandBlock
        implements AxleHandler, BucketInteraction, FluidClearableTank, FluidTooltipWhenEmpty {

    public static final EnumProperty<Axis> HORIZONTAL_AXIS = BlockStateProperties.HORIZONTAL_AXIS;

    public MechanicalFarmlandBlock(Properties p) {
        super(p.noOcclusion());
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos,
            Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        return state;
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos,
            Entity entity, double fallDistance) {
        entity.causeFallDamage(fallDistance, 1.0F, entity.damageSources().fall());
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level l, BlockState s, BlockEntityType<T> ty) {
        return (lvl, pos, b, t) -> {
            if (t instanceof TickingBE be) {
                if (l == null) {
                    return;
                }

                be.tickBoth();
                if (l.isClientSide()) {
                    be.tickClient();
                } else {
                    be.tickServer();
                }
            }

        };
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.join(Block.box(1, 0, 1, 15, 14, 15), Block.box(0, 14, 0, 16, 16, 16), BooleanOp.OR);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new MechanicalFarmlandBE(worldPosition, blockState);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof MechanicalFarmlandBE)
            return bucketAction(stack, state, level, pos, player, hand, hitResult);
        return InteractionResult.PASS;
    }

    @Override
    public FluidStacksResourceHandler getFluidTank(BlockEntity be, BlockState state, Level level, BlockPos pos,
            Player player,
            InteractionHand hand, BlockHitResult hitResult) {
        return be instanceof MechanicalFarmlandBE tank ? tank.getFluidStorage() : null;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return this.defaultBlockState()
                .setValue(HORIZONTAL_AXIS, c.getHorizontalDirection().getAxis())
                .setValue(INVERTED, false)
                .setValue(ENABLED, false)
                .setValue(MOISTURE, 0);
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> b) {
        b.add(HORIZONTAL_AXIS, MOISTURE, ENABLED, INVERTED);
    }

    @Override
    public List<Direction> getOutputDirections(BlockState state) {
        return List.of(state.getValue(HORIZONTAL_AXIS).getDirections());
    }

    @Override
    public boolean canInputFrom(Direction dir, BlockState state) {
        return dir.getAxis() == state.getValue(HORIZONTAL_AXIS);
    }

    @Override
    public Map<Direction, Boolean> getAxis(BlockState state) {
        Map<Direction, Boolean> map = new HashMap<>();

        for (Direction d : state.getValue(HORIZONTAL_AXIS).getDirections())
            map.put(d, state.getValue(INVERTED));

        return map;
    }

}
