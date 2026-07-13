package com.synergy.vintagetech.init.builder.plants;

import com.devdyna.cakesticklib.api.factories.plants.CropEntityInteraction;
import com.devdyna.cakesticklib.api.factories.plants.builder.BaseShortCropBlock;
import com.synergy.vintagetech.init.types.zItems;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.Tags;

public class BlueBerry extends BaseShortCropBlock implements CropEntityInteraction {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    public static final int MAX_AGE = 3;
    public static final int[] HEIGHT_BY_AGE = new int[] { 8, 12, 14, 15 };

    public BlueBerry(Properties p) {
        super(p.mapColor(MapColor.COLOR_LIGHT_GREEN));
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return zItems.BLUEBERRIES.get();
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(zTags.Blocks.SUPPORT_BLUEBERRY);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return mayPlaceOn(level.getBlockState(pos.below()), level, pos.below());
    }

    @Override
    public int getChanceToGrow(BlockState state, ServerLevel level, BlockPos pos) {
        return level.getBiome(pos).is(Tags.Biomes.IS_FOREST) ? 7 : super.getChanceToGrow(state, level, pos);
    }

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

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Block.boxes(3, age -> Block.column(8, 0.0, HEIGHT_BY_AGE[age]))[getAge(state)];
    }

    @Override
    public boolean hurtWhenInside() {
        return true;
    }

    @Override
    public boolean hurtWhenStep() {
        return true;
    }

    @Override
    public boolean stuckWhenInside() {
        return true;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
        if (level instanceof ServerLevel server)
                getStepEntityOn(server, pos, onState, entity);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity,
            InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        if (level instanceof ServerLevel server)
                getEntityInside(state, server, pos, entity);
    }

}