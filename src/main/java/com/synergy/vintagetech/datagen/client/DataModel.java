package com.synergy.vintagetech.datagen.client;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.utils.x;
import com.mojang.math.Quadrant;
import com.synergy.vintagetech.init.types.zBlocks;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class DataModel extends ModelProvider {

        public DataModel(PackOutput output) {
                super(output, MODULE_ID);
        }

        @Override
        protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

                blockModels.blockStateOutput
                                .accept(BlockModelGenerators.createSimpleBlock(zBlocks.GEARBOX.get(),
                                                BlockModelGenerators.plainVariant(
                                                                x.rl(MODULE_ID, "block/gearbox"))));

                blockModels.blockStateOutput
                                .accept(BlockModelGenerators.createSimpleBlock(zBlocks.QUERN.get(),
                                                BlockModelGenerators.plainVariant(
                                                                x.rl(MODULE_ID, "block/quern"))));

                blockModels.blockStateOutput
                                .accept(BlockModelGenerators.createAxisAlignedPillarBlock(zBlocks.AXLE.get(),
                                                BlockModelGenerators.plainVariant(
                                                                x.rl(MODULE_ID, "block/axle"))));

                blockModels.blockStateOutput.accept(BlockModelGenerators
                                .createSimpleBlock(zBlocks.ENGINE.get(),
                                                BlockModelGenerators.plainVariant(x.rl(MODULE_ID, "block/engine")))
                                .with(PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
                                                .select(Direction.SOUTH, BlockModelGenerators.NOP)
                                                .select(Direction.WEST, BlockModelGenerators.Y_ROT_90)
                                                .select(Direction.NORTH, BlockModelGenerators.Y_ROT_180)
                                                .select(Direction.EAST, BlockModelGenerators.Y_ROT_270)));

        }

}
