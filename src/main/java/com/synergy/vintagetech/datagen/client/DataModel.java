package com.synergy.vintagetech.datagen.client;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.synergy.vintagetech.api.ItemModelUtil;
import com.devdyna.cakesticklib.api.factories.plants.builder.BaseShortCropBlock;
import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.api.BlockModelUtils;
import com.synergy.vintagetech.init.builder.plants.Aloe;
import com.synergy.vintagetech.init.builder.plants.BlueBerry;
import com.synergy.vintagetech.init.builder.plants.Hemp;
import com.synergy.vintagetech.init.builder.saw.SawBlock;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zItems;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class DataModel extends ModelProvider {

        public DataModel(PackOutput output) {
                super(output, MODULE_ID);
        }

        @Override
        protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

                // GENERATED

                zItems.zItem.getEntries().forEach(i -> itemModels.generateFlatItem(i.get(), ModelTemplates.FLAT_ITEM));
                zItems.zBucketItems.getEntries().forEach(b -> ItemModelUtil.createBucketItem(itemModels, b.get()));
                zBlocks.zBlockFluids.getEntries().forEach(b -> BlockModelUtils.fluid(blockModels, b.get()));
                zBlocks.zRender.getEntries().forEach(
                                b -> BlockModelUtils.simplePlain(blockModels, b, "block/render/", "render_", ""));

                // BLOCK MODELS

                BlockModelUtils.simplePlain(blockModels, zBlocks.JUNCTION);
                BlockModelUtils.simplePlain(blockModels, zBlocks.MILLSTONE);
                BlockModelUtils.simplePlain(blockModels, zBlocks.CRUSHING_TUB);
                BlockModelUtils.simplePlain(blockModels, zBlocks.EVAPORATION_BASIN);
                BlockModelUtils.simplePlain(blockModels, zBlocks.DRYING_RACK);

                // TODO IMP : Models by logic
                BlockModelUtils.simplePlain(blockModels, zBlocks.CHEESE);
                BlockModelUtils.simplePlain(blockModels, zBlocks.CREATIVE_ENGINE);
                BlockModelUtils.simplePlain(blockModels, zBlocks.HYDRAULIC_PRESS);
                BlockModelUtils.simplePlain(blockModels, zBlocks.CENTRIFUGE);
                BlockModelUtils.simplePlain(blockModels, zBlocks.MIXING_BARREL);
                BlockModelUtils.simplePlain(blockModels, zBlocks.CRUCIBLE);
                BlockModelUtils.simplePlain(blockModels, zBlocks.TURNTABLE);

                blockModels.blockStateOutput.accept(
                                BlockModelGenerators.createSimpleBlock(zBlocks.LAVENDER.get(),
                                                BlockModelGenerators.variants(

                                                                BlockModelGenerators.plainModel(ModelTemplates.CROSS
                                                                                .create(ModelLocationUtils
                                                                                                .getModelLocation(
                                                                                                                zBlocks.LAVENDER.get(),
                                                                                                                "/0"),
                                                                                                TextureMapping.singleSlot(
                                                                                                                TextureSlot.CROSS,
                                                                                                                new Material(x.rl(
                                                                                                                                MODULE_ID,
                                                                                                                                "block/lavender/0"))),
                                                                                                blockModels.modelOutput)),
                                                                BlockModelGenerators.plainModel(ModelTemplates.CROSS
                                                                                .create(ModelLocationUtils
                                                                                                .getModelLocation(
                                                                                                                zBlocks.LAVENDER.get(),
                                                                                                                "/1"),
                                                                                                TextureMapping.singleSlot(
                                                                                                                TextureSlot.CROSS,
                                                                                                                new Material(x.rl(
                                                                                                                                MODULE_ID,
                                                                                                                                "block/lavender/1"))),
                                                                                                blockModels.modelOutput)),
                                                                BlockModelGenerators.plainModel(ModelTemplates.CROSS
                                                                                .create(ModelLocationUtils
                                                                                                .getModelLocation(
                                                                                                                zBlocks.LAVENDER.get(),
                                                                                                                "/2"),
                                                                                                TextureMapping.singleSlot(
                                                                                                                TextureSlot.CROSS,
                                                                                                                new Material(x.rl(
                                                                                                                                MODULE_ID,
                                                                                                                                "block/lavender/2"))),
                                                                                                blockModels.modelOutput)))));

                blockModels.createRotatedVariantBlock(zBlocks.SOIL.get());

                blockModels.blockStateOutput
                                .accept(BlockModelGenerators.createAxisAlignedPillarBlock(zBlocks.AXLE.get(),
                                                BlockModelGenerators.plainVariant(
                                                                x.rl(MODULE_ID, "block/axle"))));

                blockModels.blockStateOutput
                                .accept(BlockModelGenerators.createAxisAlignedPillarBlock(zBlocks.GEARSHIFT.get(),
                                                BlockModelGenerators.plainVariant(
                                                                x.rl(MODULE_ID, "block/gearshift"))));

                blockModels.blockStateOutput.accept(BlockModelGenerators
                                .createSimpleBlock(zBlocks.STEAM_ENGINE.get(),
                                                BlockModelGenerators
                                                                .plainVariant(x.rl(MODULE_ID, "block/steam_engine")))
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

                BlockModelUtils.cropWithoutSeed(blockModels, zBlocks.CAVE_WHEAT.get(), BaseShortCropBlock.AGE, 0, 1,
                                2, 3, 4, 5);
                BlockModelUtils.cropWithoutSeed(blockModels, zBlocks.SOYBEANS.get(), BaseShortCropBlock.AGE, 0, 1,
                                2, 3, 4, 5);

                // BlockModelUtils.cropWithoutSeed(blockModels, zBlocks.ALOE_PLANT.get(), Aloe.AGE, 0, 1, 2);
                // BlockModelUtils.cropWithoutSeed(blockModels, zBlocks.BLUEBERRY_BUSH.get(), BlueBerry.AGE, 0, 1, 2, 3);


BlockModelUtils.createBushBlock(blockModels, zBlocks.ALOE_PLANT.get(), Aloe.AGE);
BlockModelUtils.createBushBlock(blockModels, zBlocks.BLUEBERRY_BUSH.get(), BlueBerry.AGE);



                blockModels.blockStateOutput.accept(
                                MultiVariantGenerator.dispatch(zBlocks.HEMP.get())
                                                .with(
                                                                PropertyDispatch.initial(
                                                                                Hemp.AGE)

                                                                                .select(0, BlockModelGenerators
                                                                                                .plainVariant(
                                                                                                                x.rl(MODULE_ID, "block/hemp/0")))
                                                                                .select(1, BlockModelGenerators
                                                                                                .plainVariant(
                                                                                                                x.rl(MODULE_ID, "block/hemp/1")))
                                                                                .select(2, BlockModelGenerators
                                                                                                .plainVariant(
                                                                                                                x.rl(MODULE_ID, "block/hemp/2")))
                                                                                .select(3, BlockModelGenerators
                                                                                                .plainVariant(
                                                                                                                x.rl(MODULE_ID, "block/hemp/3")))

                                                ));

                // ITEM MODELS

                // blockitems
                itemModels.generateFlatItem(zBlocks.FAN.get().asItem(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(zBlocks.CHEESE.get().asItem(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(zBlocks.LAVENDER.get().asItem(), ModelTemplates.FLAT_ITEM);

                itemModels.itemModelOutput.accept(zBlocks.JUNCTION.get().asItem(),
                                ItemModelUtils.plainModel(x.rl(MODULE_ID, "item/junction")));

                itemModels.itemModelOutput.accept(zBlocks.MILLSTONE.get().asItem(),
                                ItemModelUtils.plainModel(x.rl(MODULE_ID, "item/millstone")));

                itemModels.itemModelOutput.accept(zBlocks.SAW.get().asItem(),
                                ItemModelUtils.plainModel(x.rl(MODULE_ID, "block/saw/off")));

        }

}
