package com.synergy.vintagetech.api;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public interface AxleHandler extends EntityBlock {

  //TODO on half cut to make it more flexible
  public static final VoxelShape axleX = Shapes.box(0, 0.3125, 0.3125, 1, 0.6875, 0.6875);

  public static final VoxelShape axleY = Shapes.box(0.3125, 0, 0.3125, 0.6875, 1, 0.6875);

  public static final VoxelShape axleZ = Shapes.box(0.3125, 0.3125, 0, 0.6875, 0.6875, 1);

  public static final BooleanProperty ENABLED = HopperBlock.ENABLED;

  boolean canInputFrom(Direction dir, BlockState state);

  /**
   * Used to define output directions to extend the network
   */
  List<Direction> getOutputDirections(BlockState state);

  /**
   * Called when powered by the engine
   */
  default void setPowered(Level level, BlockPos pos, BlockState state) {
    if (!state.getValueOrElse(ENABLED, true))
      level.setBlockAndUpdate(pos, state.trySetValue(ENABLED, true));
  }

  /**
   * Used to define animation rotation
   */
  List<Direction.Axis> getRotationAxis(BlockState state);

}
