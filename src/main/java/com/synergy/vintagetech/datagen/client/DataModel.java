package com.synergy.vintagetech.datagen.client;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.Optional;

import com.synergy.vintagetech.api.ItemModelUtil;
import com.devdyna.cakesticklib.api.factories.plants.builder.BaseShortCropBlock;
import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.api.BlockModelUtils;
import com.synergy.vintagetech.init.builder.RopeBlock;
import com.synergy.vintagetech.init.builder.crushing_tub.CrushingTubBlock;
import com.synergy.vintagetech.init.builder.plants.Aloe;
import com.synergy.vintagetech.init.builder.plants.BlueBerry;
import com.synergy.vintagetech.init.builder.plants.Hemp;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zItems;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.BlockModelGenerators.PlantType;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction.Axis;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.FoliageColor;
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

                blockModels.woodProvider(zBlocks.IRONWOOD_LOG.get())
                                .logWithHorizontal(zBlocks.IRONWOOD_LOG.get())
                                .wood(zBlocks.IRONWOOD_WOOD.get());

                blockModels.woodProvider(zBlocks.STRIPPED_IRONWOOD_LOG.get())
                                .logWithHorizontal(zBlocks.STRIPPED_IRONWOOD_LOG.get())
                                .wood(zBlocks.STRIPPED_IRONWOOD_WOOD.get());

                blockModels.createTintedLeaves(zBlocks.IRONWOOD_LEAVES.get(), TexturedModel.LEAVES,
                                FoliageColor.FOLIAGE_DEFAULT);
                blockModels.createTrivialCube(zBlocks.IRONWOOD_PLANKS.get());
                blockModels.createPlantWithDefaultItem(zBlocks.IRONWOOD_SAPLING.get(),
                                zBlocks.POTTED_IRONWOOD_SAPLING.get(), PlantType.NOT_TINTED);

                blockModels.blockStateOutput.accept(
                                BlockModelGenerators.createStairs(
                                                zBlocks.IRONWOOD_STAIRS.get(),
                                                BlockModelGenerators.plainVariant(
                                                                ModelTemplates.STAIRS_INNER.create(
                                                                                zBlocks.IRONWOOD_STAIRS.get(),
                                                                                new TextureMapping()
                                                                                                .put(TextureSlot.BOTTOM,
                                                                                                                new Material(x.rl(
                                                                                                                                MODULE_ID,
                                                                                                                                "block/ironwood_planks")))
                                                                                                .put(TextureSlot.SIDE,
                                                                                                                new Material(x.rl(
                                                                                                                                MODULE_ID,
                                                                                                                                "block/ironwood_planks")))
                                                                                                .put(TextureSlot.TOP,
                                                                                                                new Material(x.rl(
                                                                                                                                MODULE_ID,
                                                                                                                                "block/ironwood_planks"))),
                                                                                blockModels.modelOutput)),

                                                BlockModelGenerators.plainVariant(
                                                                ModelTemplates.STAIRS_STRAIGHT.create(
                                                                                zBlocks.IRONWOOD_STAIRS.get(),
                                                                                new TextureMapping()
                                                                                                .put(TextureSlot.BOTTOM,
                                                                                                                new Material(x.rl(
                                                                                                                                MODULE_ID,
                                                                                                                                "block/ironwood_planks")))
                                                                                                .put(TextureSlot.SIDE,
                                                                                                                new Material(x.rl(
                                                                                                                                MODULE_ID,
                                                                                                                                "block/ironwood_planks")))
                                                                                                .put(TextureSlot.TOP,
                                                                                                                new Material(x.rl(
                                                                                                                                MODULE_ID,
                                                                                                                                "block/ironwood_planks"))),
                                                                                blockModels.modelOutput)),

                                                BlockModelGenerators.plainVariant(
                                                                ModelTemplates.STAIRS_OUTER.create(
                                                                                zBlocks.IRONWOOD_STAIRS.get(),
                                                                                new TextureMapping()
                                                                                                .put(TextureSlot.BOTTOM,
                                                                                                                new Material(x.rl(
                                                                                                                                MODULE_ID,
                                                                                                                                "block/ironwood_planks")))
                                                                                                .put(TextureSlot.SIDE,
                                                                                                                new Material(x.rl(
                                                                                                                                MODULE_ID,
                                                                                                                                "block/ironwood_planks")))
                                                                                                .put(TextureSlot.TOP,
                                                                                                                new Material(x.rl(
                                                                                                                                MODULE_ID,
                                                                                                                                "block/ironwood_planks"))),
                                                                                blockModels.modelOutput))));

                blockModels.blockStateOutput.accept(
                                BlockModelGenerators.createSlab(zBlocks.IRONWOOD_SLAB.get(),
                                                BlockModelGenerators.plainVariant(ModelTemplates.SLAB_BOTTOM
                                                                .create(zBlocks.IRONWOOD_SLAB.get(),
                                                                                new TextureMapping()
                                                                                                .put(TextureSlot.BOTTOM,
                                                                                                                new Material(x.rl(
                                                                                                                                MODULE_ID,
                                                                                                                                "block/ironwood_planks")))
                                                                                                .put(TextureSlot.SIDE,
                                                                                                                new Material(x.rl(
                                                                                                                                MODULE_ID,
                                                                                                                                "block/ironwood_planks")))
                                                                                                .put(TextureSlot.TOP,
                                                                                                                new Material(x.rl(
                                                                                                                                MODULE_ID,
                                                                                                                                "block/ironwood_planks"))),
                                                                                blockModels.modelOutput)),
                                                BlockModelGenerators.plainVariant(ModelTemplates.SLAB_TOP
                                                                .create(zBlocks.IRONWOOD_SLAB.get(),
                                                                                new TextureMapping()
                                                                                                .put(TextureSlot.BOTTOM,
                                                                                                                new Material(x.rl(
                                                                                                                                MODULE_ID,
                                                                                                                                "block/ironwood_planks")))
                                                                                                .put(TextureSlot.SIDE,
                                                                                                                new Material(x.rl(
                                                                                                                                MODULE_ID,
                                                                                                                                "block/ironwood_planks")))
                                                                                                .put(TextureSlot.TOP,
                                                                                                                new Material(x.rl(
                                                                                                                                MODULE_ID,
                                                                                                                                "block/ironwood_planks"))),
                                                                                blockModels.modelOutput)),
                                                BlockModelGenerators.plainVariant(ModelTemplates.CUBE_ALL
                                                                .createWithSuffix(zBlocks.IRONWOOD_SLAB.get(),
                                                                                "_double",
                                                                                TextureMapping.cube(new Material(x.rl(
                                                                                                MODULE_ID,
                                                                                                "block/ironwood_planks"))),
                                                                                blockModels.modelOutput))));

                BlockModelUtils.simplePlain(blockModels, zBlocks.JUNCTION);
                BlockModelUtils.simplePlain(blockModels, zBlocks.MILLSTONE);
                // BlockModelUtils.simplePlain(blockModels, zBlocks.CRUSHING_TUB);
                BlockModelUtils.simplePlain(blockModels, zBlocks.EVAPORATION_BASIN);

                BlockModelUtils.createHorizontalFacingBlock(blockModels, zBlocks.DRYING_RACK.get(),
                                x.rl(MODULE_ID, "block/drying_rack"), false);

                BlockModelUtils.simplePlain(blockModels, zBlocks.CENTRIFUGE);

                BlockModelUtils.createCheeseBlock(blockModels, zBlocks.PLAIN_CHEESE.get());
                BlockModelUtils.createAgedCheeseBlock(blockModels, zBlocks.SEALED_CHEESE.get());
                BlockModelUtils.createCheeseBlock(blockModels, zBlocks.AGED_CHEESE.get());

                BlockModelUtils.simplePlain(blockModels, zBlocks.CRUCIBLE);

                // TODO IMP : Models by logic

                BlockModelUtils.simplePlain(blockModels, zBlocks.CREATIVE_ENGINE);
                // BlockModelUtils.simplePlain(blockModels, zBlocks.HYDRAULIC_PRESS);
                // BlockModelUtils.simplePlain(blockModels, zBlocks.TURNTABLE);

                blockModels.blockStateOutput.accept(
                                MultiPartGenerator.multiPart(zBlocks.CRUSHING_TUB.get())
                                                .with(BlockModelGenerators.plainVariant(
                                                                x.rl(MODULE_ID, "block/crushing_tub/tub")))
                                                .with(BlockModelGenerators.condition().term(CrushingTubBlock.MESH,
                                                                true),
                                                                BlockModelGenerators.plainVariant(
                                                                                x.rl(MODULE_ID, "block/crushing_tub/mesh")))

                );

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

                BlockModelUtils.createRopeBeamBlock(blockModels, zBlocks.AXLE.get());

                BlockModelUtils.createBeamBlock(blockModels, zBlocks.OAK_BEAM.get(),
                                x.rl(MODULE_ID, "block/beam/normal/oak"),
                                x.mcLoc("block/oak_log"));
                BlockModelUtils.createBeamBlock(blockModels, zBlocks.BIRCH_BEAM.get(),
                                x.rl(MODULE_ID, "block/beam/normal/birch"),
                                x.mcLoc("block/birch_log"));
                BlockModelUtils.createBeamBlock(blockModels, zBlocks.SPRUCE_BEAM.get(),
                                x.rl(MODULE_ID, "block/beam/normal/spruce"),
                                x.mcLoc("block/spruce_log"));
                BlockModelUtils.createBeamBlock(blockModels, zBlocks.JUNGLE_BEAM.get(),
                                x.rl(MODULE_ID, "block/beam/normal/jungle"),
                                x.mcLoc("block/jungle_log"));
                BlockModelUtils.createBeamBlock(blockModels, zBlocks.ACACIA_BEAM.get(),
                                x.rl(MODULE_ID, "block/beam/normal/acacia"),
                                x.mcLoc("block/acacia_log"));
                BlockModelUtils.createBeamBlock(blockModels, zBlocks.DARK_OAK_BEAM.get(),
                                x.rl(MODULE_ID, "block/beam/normal/dark_oak"),
                                x.mcLoc("block/dark_oak_log"));
                BlockModelUtils.createBeamBlock(blockModels, zBlocks.CHERRY_BEAM.get(),
                                x.rl(MODULE_ID, "block/beam/normal/cherry"),
                                x.mcLoc("block/cherry_log"));
                BlockModelUtils.createBeamBlock(blockModels, zBlocks.MANGROVE_BEAM.get(),
                                x.rl(MODULE_ID, "block/beam/normal/mangrove"),
                                x.mcLoc("block/mangrove_log"));
                BlockModelUtils.createBeamBlock(blockModels, zBlocks.PALE_OAK_BEAM.get(),
                                x.rl(MODULE_ID, "block/beam/normal/pale_oak"),
                                x.mcLoc("block/pale_oak_log"));
                BlockModelUtils.createBeamBlock(blockModels, zBlocks.BAMBOO_BEAM.get(),
                                x.rl(MODULE_ID, "block/beam/normal/bamboo"),
                                x.rl(MODULE_ID, "block/beam/normal/bamboo"));

                BlockModelUtils.createBeamBlock(blockModels, zBlocks.STRIPPED_OAK_BEAM.get(),
                                x.rl(MODULE_ID, "block/beam/stripped/oak"),
                                x.mcLoc("block/stripped_oak_log"));
                BlockModelUtils.createBeamBlock(blockModels, zBlocks.STRIPPED_BIRCH_BEAM.get(),
                                x.rl(MODULE_ID, "block/beam/stripped/birch"),
                                x.mcLoc("block/stripped_birch_log"));
                BlockModelUtils.createBeamBlock(blockModels, zBlocks.STRIPPED_SPRUCE_BEAM.get(),
                                x.rl(MODULE_ID, "block/beam/stripped/spruce"),
                                x.mcLoc("block/stripped_spruce_log"));
                BlockModelUtils.createBeamBlock(blockModels, zBlocks.STRIPPED_JUNGLE_BEAM.get(),
                                x.rl(MODULE_ID, "block/beam/stripped/jungle"),
                                x.mcLoc("block/stripped_jungle_log"));
                BlockModelUtils.createBeamBlock(blockModels, zBlocks.STRIPPED_ACACIA_BEAM.get(),
                                x.rl(MODULE_ID, "block/beam/stripped/acacia"),
                                x.mcLoc("block/stripped_acacia_log"));
                BlockModelUtils.createBeamBlock(blockModels, zBlocks.STRIPPED_DARK_OAK_BEAM.get(),
                                x.rl(MODULE_ID, "block/beam/stripped/dark_oak"),
                                x.mcLoc("block/stripped_dark_oak_log"));
                BlockModelUtils.createBeamBlock(blockModels, zBlocks.STRIPPED_CHERRY_BEAM.get(),
                                x.rl(MODULE_ID, "block/beam/stripped/cherry"),
                                x.mcLoc("block/stripped_cherry_log"));
                BlockModelUtils.createBeamBlock(blockModels, zBlocks.STRIPPED_MANGROVE_BEAM.get(),
                                x.rl(MODULE_ID, "block/beam/stripped/mangrove"),
                                x.mcLoc("block/stripped_mangrove_log"));
                BlockModelUtils.createBeamBlock(blockModels, zBlocks.STRIPPED_PALE_OAK_BEAM.get(),
                                x.rl(MODULE_ID, "block/beam/stripped/pale_oak"),
                                x.mcLoc("block/stripped_pale_oak_log"));
                BlockModelUtils.createBeamBlock(blockModels, zBlocks.STRIPPED_BAMBOO_BEAM.get(),
                                x.rl(MODULE_ID, "block/beam/stripped/bamboo"),
                                x.rl(MODULE_ID, "block/beam/stripped/bamboo"));

                BlockModelUtils.createGearShiftBlock(blockModels, zBlocks.GEARSHIFT.get());
                BlockModelUtils.createClutchBlock(blockModels, zBlocks.CLUTCH.get());

                BlockModelUtils.createSawToggleBlock(blockModels, zBlocks.SAW.get());

                BlockModelUtils.createFacingBlock(blockModels, zBlocks.BASKET.get(), x.rl(MODULE_ID, "block/basket"),
                                false);
                BlockModelUtils.createFacingBlock(blockModels, zBlocks.FAN.get(), x.rl(MODULE_ID, "block/fan"), true);
                BlockModelUtils.createHorizontalFacingBlock(blockModels, zBlocks.TREE_TAP.get(),
                                x.rl(MODULE_ID, "block/tree_tap"), false);

                MultiVariant line = BlockModelGenerators.plainVariant(
                                x.rl(MODULE_ID, "block/rope/line"));

                MultiVariant dot = BlockModelGenerators.plainVariant(
                                x.rl(MODULE_ID, "block/rope/dot"));

                blockModels.blockStateOutput.accept(
                                MultiPartGenerator.multiPart(zBlocks.ROPE.get())
                                                .with(BlockModelGenerators.condition().term(RopeBlock.DOWN,
                                                                true), line)
                                                .with(BlockModelGenerators.condition().term(RopeBlock.UP,
                                                                true), line.with(BlockModelGenerators.X_ROT_180))
                                                .with(BlockModelGenerators.condition().term(RopeBlock.SOUTH,
                                                                true), line.with(BlockModelGenerators.X_ROT_90))
                                                .with(BlockModelGenerators.condition().term(RopeBlock.NORTH,
                                                                true),
                                                                line.with(BlockModelGenerators.X_ROT_90)
                                                                                .with(BlockModelGenerators.Y_ROT_180))
                                                .with(BlockModelGenerators.condition().term(RopeBlock.WEST,
                                                                true),
                                                                line.with(BlockModelGenerators.X_ROT_90)
                                                                                .with(BlockModelGenerators.Y_ROT_90))
                                                .with(BlockModelGenerators.condition().term(RopeBlock.EAST,
                                                                true),
                                                                line.with(BlockModelGenerators.X_ROT_90)
                                                                                .with(BlockModelGenerators.Y_ROT_270))
                                                .with(BlockModelGenerators.condition().term(RopeBlock.HAS_CORNER, true),
                                                                dot)

                );

                // TODO MODELS : create models for dynamo and electric motor
                BlockModelUtils.createFacingBlock(blockModels, zBlocks.WINDMILL.get(),
                                x.rl(MODULE_ID, "block/windmill"), true);
                BlockModelUtils.createHorizontalFacingBlock(blockModels, zBlocks.DYNAMO.get(),
                                x.rl(MODULE_ID, "block/steam_engine"), false);
                BlockModelUtils.createHorizontalFacingBlock(blockModels, zBlocks.ELECTRIC_MOTOR.get(),
                                x.rl(MODULE_ID, "block/steam_engine"), false);

                BlockModelUtils.cropWithoutSeed(blockModels, zBlocks.CAVE_WHEAT.get(), BaseShortCropBlock.AGE, 0, 1,
                                2, 3, 4, 5);
                                
                BlockModelUtils.createBushBlock(blockModels, zBlocks.SOYBEANS.get(), BaseShortCropBlock.AGE);




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

                blockModels.blockStateOutput.accept(BlockModelGenerators
                                .createSimpleBlock(zBlocks.MECHANICAL_FARMLAND.get(),
                                                BlockModelGenerators.plainVariant(
                                                                x.rl(MODULE_ID, "block/mechanical_farmland")))
                                .with(PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_AXIS)
                                                .select(Axis.X, BlockModelGenerators.NOP)
                                                .select(Axis.Z, BlockModelGenerators.Y_ROT_90)));

                // ITEM MODELS

                // blockitems
                itemModels.generateFlatItem(zBlocks.FAN.get().asItem(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(zBlocks.AGED_CHEESE.get().asItem(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(zBlocks.PLAIN_CHEESE.get().asItem(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(zBlocks.SEALED_CHEESE.get().asItem(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(zBlocks.LAVENDER.get().asItem(), ModelTemplates.FLAT_ITEM);
                itemModels.generateFlatItem(zBlocks.ROPE.get().asItem(), ModelTemplates.FLAT_ITEM);

                // itemModels.generateFlatItem(zBlocks.NODE.get().asItem(),
                // ModelTemplates.FLAT_ITEM);

                itemModels.itemModelOutput.accept(zBlocks.AXLE.get().asItem(),
                                ItemModelUtils.plainModel(
                                                new ModelTemplate(
                                                                Optional.of(x.rl(MODULE_ID, "block/template/beam")),
                                                                Optional.empty(),
                                                                TextureSlot.TOP, TextureSlot.SIDE)
                                                                .create(x.rl(MODULE_ID, "block/axle"),
                                                                                new TextureMapping()
                                                                                                .put(TextureSlot.TOP,
                                                                                                                new Material(x.rl(
                                                                                                                                MODULE_ID,
                                                                                                                                "block/beam/axle")))
                                                                                                .put(TextureSlot.SIDE,
                                                                                                                new Material(x.rl(
                                                                                                                                MODULE_ID,
                                                                                                                                "block/beam/axle"))),
                                                                                itemModels.modelOutput)));

                itemModels.itemModelOutput.accept(zBlocks.WINDMILL.get().asItem(),
                                ItemModelUtils.plainModel(x.rl(MODULE_ID, "item/windmill")));

                itemModels.itemModelOutput.accept(zBlocks.CRUSHING_TUB.get().asItem(),
                                ItemModelUtils.plainModel(x.rl(MODULE_ID, "block/crushing_tub/tub")));

                itemModels.itemModelOutput.accept(zBlocks.JUNCTION.get().asItem(),
                                ItemModelUtils.plainModel(x.rl(MODULE_ID, "item/junction")));

                itemModels.itemModelOutput.accept(zBlocks.MILLSTONE.get().asItem(),
                                ItemModelUtils.plainModel(x.rl(MODULE_ID, "item/millstone")));

                itemModels.itemModelOutput.accept(zBlocks.SAW.get().asItem(),
                                ItemModelUtils.plainModel(x.rl(MODULE_ID, "block/saw_off")));

                itemModels.itemModelOutput.accept(zBlocks.CLUTCH.get().asItem(),
                                ItemModelUtils.plainModel(x.rl(MODULE_ID, "item/clutch")));

                itemModels.itemModelOutput.accept(zBlocks.GEARSHIFT.get().asItem(),
                                ItemModelUtils.plainModel(x.rl(MODULE_ID, "item/gearshift")));

                itemModels.itemModelOutput.accept(zBlocks.MECHANICAL_FARMLAND.get().asItem(),
                                ItemModelUtils.plainModel(x.rl(MODULE_ID, "item/mechanical_farmland")));

                // TODO MODELS : create models for dynamo and electric motor
                itemModels.itemModelOutput.accept(zBlocks.ELECTRIC_MOTOR.get().asItem(),
                                ItemModelUtils.plainModel(x.rl(MODULE_ID, "block/steam_engine")));

                itemModels.itemModelOutput.accept(zBlocks.DYNAMO.get().asItem(),
                                ItemModelUtils.plainModel(x.rl(MODULE_ID, "block/steam_engine")));

        }

}
