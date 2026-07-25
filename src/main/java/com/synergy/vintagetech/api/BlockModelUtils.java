package com.synergy.vintagetech.api;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.Optional;

import com.devdyna.cakesticklib.api.utils.x;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.registries.DeferredHolder;

public class BlockModelUtils {

        public static void cropWithoutSeed(BlockModelGenerators b, Block block, Property<Integer> property,
                        int... stages) {

                if (property.getPossibleValues().size() != stages.length)
                        throw new IllegalArgumentException();
                else {
                        Int2ObjectMap<Identifier> models = new Int2ObjectOpenHashMap<>();
                        b.blockStateOutput
                                        .accept(
                                                        MultiVariantGenerator.dispatch(block)
                                                                        .with(
                                                                                        PropertyDispatch.initial(
                                                                                                        property)
                                                                                                        .generate(
                                                                                                                        i -> {
                                                                                                                                int stage = stages[i];
                                                                                                                                return BlockModelGenerators
                                                                                                                                                .plainVariant(
                                                                                                                                                                models.computeIfAbsent(
                                                                                                                                                                                stage,
                                                                                                                                                                                s -> b.createSuffixedVariant(
                                                                                                                                                                                                block,
                                                                                                                                                                                                "/" + s,
                                                                                                                                                                                                ModelTemplates.CROP,
                                                                                                                                                                                                TextureMapping::crop)));
                                                                                                                        })));
                }
        }

        // TODO API : move to api
        public static void fluid(BlockModelGenerators b, Block block) {

                ModelTemplate particleModel = new ModelTemplate(Optional.empty(), Optional.empty(),
                                TextureSlot.PARTICLE);
                Identifier modelId = ModelLocationUtils.getModelLocation(block);
                TextureMapping mapping = new TextureMapping().put(TextureSlot.PARTICLE,
                                getBlockTexture("fluid_source"));
                particleModel.create(modelId, mapping, b.modelOutput);
                b.blockStateOutput.accept(
                                MultiVariantGenerator.dispatch(block, BlockModelGenerators.plainVariant(modelId)));
        }

        public static Material getBlockTexture(String b) {
                return getGenericTexture("block/" + b);
        }

        public static Material getItemTexture(String b) {
                return getGenericTexture("item/" + b);
        }

        public static Material getGenericTexture(String b) {
                return new Material(x.rl(MODULE_ID, b));
        }

        public static void simplePlain(BlockModelGenerators b, DeferredHolder<Block, ?> block, String prefix,
                        String filter, String replace) {
                simplePlain(b, block, prefix + block.getId().getPath().replace(filter, replace));
        }

        public static void simplePlain(BlockModelGenerators b, DeferredHolder<Block, ?> block) {
                simplePlain(b, block, "block/" + block.getId().getPath());
        }

        public static void simplePlain(BlockModelGenerators b, DeferredHolder<Block, ?> block, String prefix) {
                b.blockStateOutput
                                .accept(BlockModelGenerators.createSimpleBlock(block.get(),
                                                BlockModelGenerators.plainVariant(
                                                                x.rl(MODULE_ID, prefix))));
        }

        public static void createBushBlock(BlockModelGenerators b, Block block, IntegerProperty prop) {
                b.blockStateOutput
                                .accept(
                                                MultiVariantGenerator.dispatch(block)
                                                                .with(
                                                                                PropertyDispatch.initial(prop)
                                                                                                .generate(
                                                                                                                age -> BlockModelGenerators
                                                                                                                                .plainVariant(
                                                                                                                                                b.createSuffixedVariant(
                                                                                                                                                                block,
                                                                                                                                                                "/" + age,
                                                                                                                                                                ModelTemplates.CROSS,
                                                                                                                                                                TextureMapping::cross)))));
        }

        public static void createHorizontalFacingBlock(BlockModelGenerators b, Block block, Identifier model,
                        boolean invertDirections) {
                b.blockStateOutput.accept(BlockModelGenerators
                                .createSimpleBlock(block,
                                                BlockModelGenerators
                                                                .plainVariant(model))
                                .with(PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
                                                .select((invertDirections ? Direction.NORTH : Direction.SOUTH),
                                                                BlockModelGenerators.NOP)
                                                .select((invertDirections ? Direction.EAST : Direction.WEST),
                                                                BlockModelGenerators.Y_ROT_90)
                                                .select((invertDirections ? Direction.SOUTH : Direction.NORTH),
                                                                BlockModelGenerators.Y_ROT_180)
                                                .select((invertDirections ? Direction.WEST : Direction.EAST),
                                                                BlockModelGenerators.Y_ROT_270)));
        }

        public static void createFacingBlock(BlockModelGenerators b, Block block, Identifier model,
                        boolean invertDirections) {

                b.blockStateOutput.accept(BlockModelGenerators
                                .createSimpleBlock(block,
                                                BlockModelGenerators.plainVariant(model))
                                .with(PropertyDispatch.modify(BlockStateProperties.FACING)
                                                .select((invertDirections ? Direction.SOUTH : Direction.NORTH),
                                                                BlockModelGenerators.NOP)
                                                .select((invertDirections ? Direction.WEST : Direction.EAST),
                                                                BlockModelGenerators.Y_ROT_90)
                                                .select((invertDirections ? Direction.NORTH : Direction.SOUTH),
                                                                BlockModelGenerators.Y_ROT_180)
                                                .select((invertDirections ? Direction.EAST : Direction.WEST),
                                                                BlockModelGenerators.Y_ROT_270)
                                                .select((invertDirections ? Direction.UP : Direction.DOWN),
                                                                BlockModelGenerators.X_ROT_90)
                                                .select((invertDirections ? Direction.DOWN : Direction.UP),
                                                                BlockModelGenerators.X_ROT_270)));

        }

        public static void createBeamBlock(BlockModelGenerators b, Block block, Identifier top, Identifier side) {

                MultiVariant beam = BlockModelGenerators.plainVariant(
                                new ModelTemplate(
                                                Optional.of(x.rl(MODULE_ID, "block/template/beam")), Optional.empty(),
                                                TextureSlot.TOP, TextureSlot.SIDE)
                                                .create(
                                                                x.rl(MODULE_ID, "block/" + x.name(block)),
                                                                new TextureMapping()
                                                                                .put(
                                                                                                TextureSlot.TOP,
                                                                                                new Material(
                                                                                                                top))
                                                                                .put(
                                                                                                TextureSlot.SIDE,
                                                                                                new Material(
                                                                                                                side)),
                                                                b.modelOutput));

                MultiVariant rope = BlockModelGenerators.plainVariant(
                                x.rl(MODULE_ID, "block/rope/on_beam"));

                b.blockStateOutput.accept(
                                MultiPartGenerator.multiPart(block)
                                                .with(BlockModelGenerators.condition()
                                                                .term(BlockStateProperties.AXIS, Direction.Axis.X),
                                                                beam.with(BlockModelGenerators.X_ROT_90
                                                                                .then(BlockModelGenerators.Y_ROT_90)))
                                                .with(BlockModelGenerators.condition()
                                                                .term(BlockStateProperties.AXIS, Direction.Axis.Y),
                                                                beam)
                                                .with(BlockModelGenerators.condition()
                                                                .term(BlockStateProperties.AXIS, Direction.Axis.Z),
                                                                beam.with(BlockModelGenerators.X_ROT_90))
                                                .with(BlockModelGenerators.condition()
                                                                .term(RopeHandler.HAS_ROPE, true), rope)

                );

        }

        public static void createRopeBeamBlock(BlockModelGenerators b, Block block) {

                b.blockStateOutput.accept(
                                MultiPartGenerator.multiPart(block)
                                                .with(BlockModelGenerators.condition()
                                                                .term(RopeHandler.HAS_ROPE, true),
                                                                BlockModelGenerators.plainVariant(
                                                                                x.rl(MODULE_ID, "block/rope/on_beam")))

                );

        }

}
