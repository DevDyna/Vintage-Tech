package com.synergy.vintagetech.datagen.client;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.init.builder.saw.SawBlock;
import com.synergy.vintagetech.init.types.zBlocks;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class DataModel extends ModelProvider {

        public DataModel(PackOutput output) {
                super(output, MODULE_ID);
        }

        @Override
        protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

                // TODO IMP : change item model!

                blockModels.blockStateOutput
                                .accept(BlockModelGenerators.createSimpleBlock(zBlocks.JUNCTION.get(),
                                                BlockModelGenerators.plainVariant(
                                                                x.rl(MODULE_ID, "block/junction"))));

                blockModels.blockStateOutput
                                .accept(BlockModelGenerators.createSimpleBlock(zBlocks.RENDER_HALF_AXLE.get(),
                                                BlockModelGenerators.plainVariant(
                                                                x.rl(MODULE_ID, "block/render/half_axle"))));

                blockModels.blockStateOutput
                                .accept(BlockModelGenerators.createSimpleBlock(zBlocks.RENDER_FAN_BLADE.get(),
                                                BlockModelGenerators.plainVariant(
                                                                x.rl(MODULE_ID, "block/render/fan_blade"))));

                blockModels.blockStateOutput
                                .accept(BlockModelGenerators.createAxisAlignedPillarBlock(zBlocks.AXLE.get(),
                                                BlockModelGenerators.plainVariant(
                                                                x.rl(MODULE_ID, "block/axle"))));

                blockModels.blockStateOutput
                                .accept(BlockModelGenerators.createAxisAlignedPillarBlock(zBlocks.GEARSHIFT.get(),
                                                BlockModelGenerators.plainVariant(
                                                                x.rl(MODULE_ID, "block/gearshift"))));

                blockModels.blockStateOutput.accept(BlockModelGenerators
                                .createSimpleBlock(zBlocks.ENGINE.get(),
                                                BlockModelGenerators.plainVariant(x.rl(MODULE_ID, "block/engine")))
                                .with(PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
                                                .select(Direction.SOUTH, BlockModelGenerators.NOP)
                                                .select(Direction.WEST, BlockModelGenerators.Y_ROT_90)
                                                .select(Direction.NORTH, BlockModelGenerators.Y_ROT_180)
                                                .select(Direction.EAST, BlockModelGenerators.Y_ROT_270)));

                MultiVariant sawOff = BlockModelGenerators.plainVariant(
                                x.rl(MODULE_ID, "block/saw/off"));

                MultiVariant sawOn = BlockModelGenerators.plainVariant(
                                x.rl(MODULE_ID, "block/saw/on"));

                blockModels.blockStateOutput.accept(
                                MultiVariantGenerator.dispatch(zBlocks.SAW.get())
                                                .with(
                                                                PropertyDispatch.initial(
                                                                                BlockStateProperties.FACING,
                                                                                SawBlock.ENABLED)

                                                                                .select(Direction.UP, false, sawOff)
                                                                                .select(Direction.UP, true, sawOn)

                                                                                .select(Direction.DOWN, false,
                                                                                                sawOff.with(BlockModelGenerators.X_ROT_180))
                                                                                .select(Direction.DOWN, true,
                                                                                                sawOn.with(BlockModelGenerators.X_ROT_180))

                                                                                .select(Direction.NORTH, false,
                                                                                                sawOff.with(BlockModelGenerators.X_ROT_90))
                                                                                .select(Direction.NORTH, true,
                                                                                                sawOn.with(BlockModelGenerators.X_ROT_90))

                                                                                .select(Direction.EAST, false,
                                                                                                sawOff.with(BlockModelGenerators.X_ROT_90)
                                                                                                                .with(BlockModelGenerators.Y_ROT_90))
                                                                                .select(Direction.EAST, true,
                                                                                                sawOn.with(BlockModelGenerators.X_ROT_90)
                                                                                                                .with(BlockModelGenerators.Y_ROT_90))

                                                                                .select(Direction.SOUTH, false,
                                                                                                sawOff.with(BlockModelGenerators.X_ROT_90)
                                                                                                                .with(BlockModelGenerators.Y_ROT_180))
                                                                                .select(Direction.SOUTH, true,
                                                                                                sawOn.with(BlockModelGenerators.X_ROT_90)
                                                                                                                .with(BlockModelGenerators.Y_ROT_180))

                                                                                .select(Direction.WEST, false,
                                                                                                sawOff.with(BlockModelGenerators.X_ROT_90)
                                                                                                                .with(BlockModelGenerators.Y_ROT_270))
                                                                                .select(Direction.WEST, true,
                                                                                                sawOn.with(BlockModelGenerators.X_ROT_90)
                                                                                                                .with(BlockModelGenerators.Y_ROT_270))));

                blockModels.blockStateOutput.accept(BlockModelGenerators
                                .createSimpleBlock(zBlocks.FAN.get(),
                                                BlockModelGenerators.plainVariant(x.rl(MODULE_ID, "block/fan")))
                                .with(PropertyDispatch.modify(BlockStateProperties.FACING)
                                                .select(Direction.SOUTH, BlockModelGenerators.NOP)
                                                .select(Direction.WEST, BlockModelGenerators.Y_ROT_90)
                                                .select(Direction.NORTH, BlockModelGenerators.Y_ROT_180)
                                                .select(Direction.EAST, BlockModelGenerators.Y_ROT_270)
                                                .select(Direction.UP, BlockModelGenerators.X_ROT_90)
                                                .select(Direction.DOWN, BlockModelGenerators.X_ROT_270)));

                blockModels.blockStateOutput.accept(BlockModelGenerators
                                .createSimpleBlock(zBlocks.BASKET.get(),
                                                BlockModelGenerators.plainVariant(x.rl(MODULE_ID, "block/basket")))
                                .with(PropertyDispatch.modify(BlockStateProperties.FACING)
                                                .select(Direction.NORTH, BlockModelGenerators.NOP)
                                                .select(Direction.EAST, BlockModelGenerators.Y_ROT_90)
                                                .select(Direction.SOUTH, BlockModelGenerators.Y_ROT_180)
                                                .select(Direction.WEST, BlockModelGenerators.Y_ROT_270)
                                                .select(Direction.DOWN, BlockModelGenerators.X_ROT_90)
                                                .select(Direction.UP, BlockModelGenerators.X_ROT_270)));

                blockModels.blockStateOutput
                                .accept(BlockModelGenerators.createSimpleBlock(zBlocks.CRUSHING_TUB.get(),
                                                BlockModelGenerators.plainVariant(
                                                                x.rl(MODULE_ID, "block/crushing_tub"))));
                blockModels.blockStateOutput
                                .accept(BlockModelGenerators.createSimpleBlock(zBlocks.EVAPORATION_BASIN.get(),
                                                BlockModelGenerators.plainVariant(
                                                                x.rl(MODULE_ID, "block/evaporation_basin"))));
                blockModels.blockStateOutput
                                .accept(BlockModelGenerators.createSimpleBlock(zBlocks.DRYING_RACK.get(),
                                                BlockModelGenerators.plainVariant(
                                                                x.rl(MODULE_ID, "block/drying_rack"))));

        }

}
