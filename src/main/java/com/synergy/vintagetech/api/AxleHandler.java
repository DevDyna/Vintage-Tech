package com.synergy.vintagetech.api;

import java.util.List;
import java.util.Map;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DaylightDetectorBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public interface AxleHandler extends EntityBlock {

  public static final VoxelShape axle_north = Shapes.box(0.3125, 0.3125, 0, 0.6875, 0.6875, 0.5);
  public static final VoxelShape axle_south = Shapes.box(0.3125, 0.3125, 0.5, 0.6875, 0.6875, 1);
  public static final VoxelShape axle_east = Shapes.box(0.5, 0.3125, 0.3125, 1, 0.6875, 0.6875);
  public static final VoxelShape axle_west = Shapes.box(0, 0.3125, 0.3125, 0.5, 0.6875, 0.6875);
  public static final VoxelShape axle_up = Shapes.box(0.3125, 0.5, 0.3125, 0.6875, 1, 0.6875);
  public static final VoxelShape axle_down = Shapes.box(0.3125, 0, 0.3125, 0.6875, 0.5, 0.6875);

  public static final VoxelShape axle_X = Shapes.join(axle_east, axle_west, BooleanOp.OR);
  public static final VoxelShape axle_Y = Shapes.join(axle_up, axle_down, BooleanOp.OR);
  public static final VoxelShape axle_Z = Shapes.join(axle_north, axle_south, BooleanOp.OR);

  public static final BooleanProperty ENABLED = HopperBlock.ENABLED;
  public static final BooleanProperty INVERTED = DaylightDetectorBlock.INVERTED;

  boolean canInputFrom(Direction dir, BlockState state);

  /**
   * Used to define output directions to extend the network
   */
  List<Direction> getOutputDirections(BlockState state);

  /**
   * Called when powered
   */
  default void setActive(Level level, BlockPos pos, BlockState state, boolean inverted) {
    if (!state.getValueOrElse(ENABLED, true))
      level.setBlockAndUpdate(pos, state.trySetValue(ENABLED, true));

    if (state.hasProperty(INVERTED) && state.getValue(INVERTED) != inverted)
      level.setBlockAndUpdate(pos, state.setValue(INVERTED, inverted));

  }

  /**
   * Called when unpowered
   */
  default void setDeactive(Level level, BlockPos pos, BlockState state, boolean inverted) {
    level.setBlockAndUpdate(pos, state.trySetValue(ENABLED, false));
  }

  /**
   * Used to define axle animation
   */
  Map<Direction, Boolean> getAxis(BlockState state);

}
