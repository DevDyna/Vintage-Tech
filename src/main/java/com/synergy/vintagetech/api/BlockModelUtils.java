package com.synergy.vintagetech.api;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.api.blockfactory.cheese.BaseCheeseBlock;
import com.synergy.vintagetech.init.builder.cheese.SealedCheeseBlock;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.registries.DeferredHolder;

public class BlockModelUtils {

        public static void cropWithoutSeed(BlockModelGenerators b, Block block, Property<Integer> property,
                        int... stages) {

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

        public static void createDoubleCrop(   BlockModelGenerators b,  Block block,  IntegerProperty age,BooleanProperty isTop) {
                b.blockStateOutput.accept(
                                MultiVariantGenerator.dispatch(block)
                                                .with(
                                                                PropertyDispatch.initial(age, isTop)
                                                                                .generate((ageValue,
                                                                                                top) -> BlockModelGenerators
                                                                                                                .plainVariant(
                                                                                                                                b.createSuffixedVariant(
                                                                                                                                                block,
                                                                                                                                                "/" + (top ? "top"
                                                                                                                                                                : "bottom")
                                                                                                                                                                + "/"
                                                                                                                                                                + ageValue,
                                                                                                                                                ModelTemplates.CROSS,
                                                                                                                                                TextureMapping::cross)))));
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
                                TemplateCollection.BEAM_TEMPLATE.create(x.rl(MODULE_ID, "block/" + x.name(block)),
                                                new TextureMapping()
                                                                .put(TextureSlot.TOP, new Material(top))
                                                                .put(TextureSlot.SIDE, new Material(side)),
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

        public static void createSawToggleBlock(BlockModelGenerators b, Block block) {

                MultiVariant sawOff = BlockModelGenerators.plainVariant(
                                TemplateCollection.SAW_TEMPLATE.create(
                                                x.rl(MODULE_ID, "block/" + x.name(block) + "_off"),
                                                new TextureMapping()
                                                                .put(TemplateCollection.SAW, new Material(x.rl(
                                                                                MODULE_ID,
                                                                                "block/saw/blade/off"))),
                                                b.modelOutput));

                MultiVariant sawOn = BlockModelGenerators.plainVariant(
                                TemplateCollection.SAW_TEMPLATE.create(
                                                x.rl(MODULE_ID, "block/" + x.name(block) + "_on"),
                                                new TextureMapping()
                                                                .put(TemplateCollection.SAW,
                                                                                new Material(x.rl(MODULE_ID,
                                                                                                "block/saw/blade/on"))),
                                                b.modelOutput));

                b.blockStateOutput.accept(
                                MultiVariantGenerator.dispatch(block)
                                                .with(
                                                                PropertyDispatch.initial(
                                                                                BlockStateProperties.FACING,
                                                                                BlockStateProperties.ENABLED)

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
        }

        public static void createClutchBlock(BlockModelGenerators b, Block block) {

                MultiVariant off = BlockModelGenerators.plainVariant(
                                TemplateCollection.MODIFIER_TEMPLATE.create(
                                                x.rl(MODULE_ID, "block/" + x.name(block) + "_off"),
                                                new TextureMapping()
                                                                .put(TemplateCollection.FRAME, new Material(x.rl(
                                                                                MODULE_ID,
                                                                                "block/" + x.name(block) + "/off"))),
                                                b.modelOutput));

                MultiVariant on = BlockModelGenerators.plainVariant(
                                TemplateCollection.MODIFIER_TEMPLATE.create(
                                                x.rl(MODULE_ID, "block/" + x.name(block) + "_on"),
                                                new TextureMapping()
                                                                .put(TemplateCollection.FRAME, new Material(x.rl(
                                                                                MODULE_ID,
                                                                                "block/" + x.name(block) + "/on"))),
                                                b.modelOutput));

                b.blockStateOutput
                                .accept(MultiVariantGenerator.dispatch(block)
                                                .with(PropertyDispatch
                                                                .initial(BlockStateProperties.AXIS,
                                                                                BlockStateProperties.POWERED)

                                                                .select(Direction.Axis.Y, false, off)
                                                                .select(Direction.Axis.Y, true, on)

                                                                .select(Direction.Axis.Z, false,
                                                                                off.with(BlockModelGenerators.X_ROT_90))
                                                                .select(Direction.Axis.Z, true,
                                                                                on.with(BlockModelGenerators.X_ROT_90))

                                                                .select(Direction.Axis.X, false,
                                                                                off.with(BlockModelGenerators.X_ROT_90
                                                                                                .then(BlockModelGenerators.Y_ROT_90)))
                                                                .select(Direction.Axis.X, true,
                                                                                on.with(BlockModelGenerators.X_ROT_90
                                                                                                .then(BlockModelGenerators.Y_ROT_90)))

                                                ));

        }

        public static void createGearShiftBlock(BlockModelGenerators b, Block block) {

                MultiVariant off = BlockModelGenerators.plainVariant(
                                TemplateCollection.MODIFIER_TEMPLATE.create(
                                                x.rl(MODULE_ID, "block/" + x.name(block) + "_off"),
                                                new TextureMapping()
                                                                .put(TemplateCollection.FRAME, new Material(x.rl(
                                                                                MODULE_ID,
                                                                                "block/" + x.name(block) + "/off"))),
                                                b.modelOutput));

                MultiVariant off_alt = BlockModelGenerators.plainVariant(
                                TemplateCollection.MODIFIER_TEMPLATE.create(
                                                x.rl(MODULE_ID, "block/" + x.name(block) + "_off_alt"),
                                                new TextureMapping()
                                                                .put(TemplateCollection.FRAME, new Material(x.rl(
                                                                                MODULE_ID,
                                                                                "block/" + x.name(block)
                                                                                                + "/off_inverted"))),
                                                b.modelOutput));

                MultiVariant on = BlockModelGenerators.plainVariant(
                                TemplateCollection.MODIFIER_TEMPLATE.create(
                                                x.rl(MODULE_ID, "block/" + x.name(block) + "_on"),
                                                new TextureMapping()
                                                                .put(TemplateCollection.FRAME, new Material(x.rl(
                                                                                MODULE_ID,
                                                                                "block/" + x.name(block) + "/on"))),
                                                b.modelOutput));

                MultiVariant on_alt = BlockModelGenerators.plainVariant(
                                TemplateCollection.MODIFIER_TEMPLATE.create(
                                                x.rl(MODULE_ID, "block/" + x.name(block) + "_on_alt"),
                                                new TextureMapping()
                                                                .put(TemplateCollection.FRAME, new Material(x.rl(
                                                                                MODULE_ID,
                                                                                "block/" + x.name(block)
                                                                                                + "/on_inverted"))),
                                                b.modelOutput));

                b.blockStateOutput
                                .accept(MultiVariantGenerator.dispatch(block)
                                                .with(PropertyDispatch
                                                                .initial(BlockStateProperties.AXIS,
                                                                                BlockStateProperties.POWERED,
                                                                                BlockStateProperties.INVERTED)

                                                                .select(Direction.Axis.Y, false, false, off)
                                                                .select(Direction.Axis.Y, true, false, on)

                                                                .select(Direction.Axis.Z, false, false,
                                                                                off.with(BlockModelGenerators.X_ROT_90))
                                                                .select(Direction.Axis.Z, true, false,
                                                                                on.with(BlockModelGenerators.X_ROT_90))

                                                                .select(Direction.Axis.X, false, false,
                                                                                off.with(BlockModelGenerators.X_ROT_90
                                                                                                .then(BlockModelGenerators.Y_ROT_90)))
                                                                .select(Direction.Axis.X, true, false,
                                                                                on.with(BlockModelGenerators.X_ROT_90
                                                                                                .then(BlockModelGenerators.Y_ROT_90)))

                                                                .select(Direction.Axis.Y, false, true, off_alt)
                                                                .select(Direction.Axis.Y, true, true, on_alt)

                                                                .select(Direction.Axis.Z, false, true,
                                                                                off_alt.with(BlockModelGenerators.X_ROT_90))
                                                                .select(Direction.Axis.Z, true, true,
                                                                                on_alt.with(BlockModelGenerators.X_ROT_90))

                                                                .select(Direction.Axis.X, false, true,
                                                                                off_alt.with(BlockModelGenerators.X_ROT_90
                                                                                                .then(BlockModelGenerators.Y_ROT_90)))
                                                                .select(Direction.Axis.X, true, true,
                                                                                on_alt.with(BlockModelGenerators.X_ROT_90
                                                                                                .then(BlockModelGenerators.Y_ROT_90)))

                                                ));

        }

        public static void createCheeseBlock(BlockModelGenerators b, Block block) {

                var name = x.name(block).replace("_cheese", "");

                var dispatch = PropertyDispatch.initial(BaseCheeseBlock.PIECES, BaseCheeseBlock.FACING);

                for (int pieces = 1; pieces <= BaseCheeseBlock.MAX_PIECES; pieces++) {

                        var variant = BlockModelGenerators.plainVariant(
                                        TemplateCollection.CHEESE.ALL.get(pieces - 1).create(
                                                        x.rl(
                                                                        MODULE_ID,
                                                                        "block/cheese/" + name + "/" + pieces),

                                                        new TextureMapping()
                                                                        .put(
                                                                                        TextureSlot.SIDE,
                                                                                        new Material(x.rl(
                                                                                                        MODULE_ID,
                                                                                                        "block/cheese/" + name
                                                                                                                        + "/side")))
                                                                        .put(
                                                                                        TextureSlot.TOP,
                                                                                        new Material(x.rl(
                                                                                                        MODULE_ID,
                                                                                                        "block/cheese/" + name
                                                                                                                        + "/top")))
                                                                        .put(
                                                                                        TextureSlot.BOTTOM,
                                                                                        new Material(x.rl(
                                                                                                        MODULE_ID,
                                                                                                        "block/cheese/" + name
                                                                                                                        + "/bottom")))
                                                                        .put(
                                                                                        TextureSlot.INSIDE,
                                                                                        new Material(x.rl(
                                                                                                        MODULE_ID,
                                                                                                        "block/cheese/" + name
                                                                                                                        + "/inside"))),

                                                        b.modelOutput));

                        dispatch.select(
                                        pieces,
                                        Direction.NORTH,
                                        variant);

                        dispatch.select(
                                        pieces,
                                        Direction.EAST,
                                        variant.with(BlockModelGenerators.Y_ROT_90));

                        dispatch.select(
                                        pieces,
                                        Direction.SOUTH,
                                        variant.with(BlockModelGenerators.Y_ROT_180));

                        dispatch.select(
                                        pieces,
                                        Direction.WEST,
                                        variant.with(BlockModelGenerators.Y_ROT_270));
                }

                b.blockStateOutput.accept(
                                MultiVariantGenerator.dispatch(block)
                                                .with(dispatch));
        }

        public static void createSealedCheeseBlock(BlockModelGenerators b, Block block) {

                var name = x.name(block).replace("_cheese", "");

                var dispatch = PropertyDispatch.initial(
                                BaseCheeseBlock.PIECES,
                                SealedCheeseBlock.AGE,
                                BaseCheeseBlock.FACING);

                for (int age = 0; age <= SealedCheeseBlock.MAX_AGE; age++) {

                        var ageName = switch (age) {
                                case 0 -> "fresh";
                                case 1 -> "matured";
                                case 2 -> "aged";
                                default -> throw new IllegalStateException();
                        };

                        for (int pieces = 1; pieces <= BaseCheeseBlock.MAX_PIECES; pieces++) {

                                var variant = BlockModelGenerators.plainVariant(
                                                TemplateCollection.CHEESE.ALL.get(pieces - 1).create(
                                                                x.rl(
                                                                                MODULE_ID,
                                                                                "block/cheese/" + name + "/"
                                                                                                + ageName + "/"
                                                                                                + pieces),

                                                                new TextureMapping()
                                                                                .put(
                                                                                                TextureSlot.SIDE,
                                                                                                new Material(x.rl(
                                                                                                                MODULE_ID,
                                                                                                                "block/cheese/" + name
                                                                                                                                + "/side")))
                                                                                .put(
                                                                                                TextureSlot.TOP,
                                                                                                new Material(x.rl(
                                                                                                                MODULE_ID,
                                                                                                                "block/cheese/" + name
                                                                                                                                + "/top")))
                                                                                .put(
                                                                                                TextureSlot.BOTTOM,
                                                                                                new Material(x.rl(
                                                                                                                MODULE_ID,
                                                                                                                "block/cheese/" + name
                                                                                                                                + "/bottom")))
                                                                                .put(
                                                                                                TextureSlot.INSIDE,
                                                                                                new Material(x.rl(
                                                                                                                MODULE_ID,
                                                                                                                "block/cheese/" + name
                                                                                                                                + "/inside/"
                                                                                                                                + ageName))),

                                                                b.modelOutput));

                                dispatch.select(
                                                pieces,
                                                age,
                                                Direction.NORTH,
                                                variant);

                                dispatch.select(
                                                pieces,
                                                age,
                                                Direction.EAST,
                                                variant.with(BlockModelGenerators.Y_ROT_90));

                                dispatch.select(
                                                pieces,
                                                age,
                                                Direction.SOUTH,
                                                variant.with(BlockModelGenerators.Y_ROT_180));

                                dispatch.select(
                                                pieces,
                                                age,
                                                Direction.WEST,
                                                variant.with(BlockModelGenerators.Y_ROT_270));
                        }
                }

                b.blockStateOutput.accept(
                                MultiVariantGenerator.dispatch(block)
                                                .with(dispatch));
        }
}
