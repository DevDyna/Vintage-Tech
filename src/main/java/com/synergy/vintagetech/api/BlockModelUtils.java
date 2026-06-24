package com.synergy.vintagetech.api;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.Optional;

import com.devdyna.cakesticklib.api.utils.x;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;

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

}
