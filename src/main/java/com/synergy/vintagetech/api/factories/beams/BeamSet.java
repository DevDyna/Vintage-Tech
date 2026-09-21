package com.synergy.vintagetech.api.factories.beams;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.function.Function;
import java.util.function.Supplier;

import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.api.datagen.BlockModelUtils;
import com.synergy.vintagetech.api.factories.trees.TreeOptions.WoodType;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

public record BeamSet(String id, DeferredHolder<Block, Block> beam_log, DeferredHolder<Block, Block> beam_stripped_log,
        DeferredHolder<Block, Block> beam_wood, DeferredHolder<Block, Block> beam_stripped_wood, Identifier logTexture,
        Identifier strippedTexture, WoodType type, Supplier<Block> log, Supplier<Block> log_stripped) {

    public boolean isTree() {
        return type.equals(WoodType.TREE);
    }

    public boolean isFungus() {
        return type.equals(WoodType.FUNGUS);
    }

    public boolean isSpecial() {
        return type.equals(WoodType.OTHER);
    }

    public void createModels(BlockModelGenerators models) {
        BlockModelUtils.createBeamBlock(models, beam_log.get(),
                x.rl(MODULE_ID, "block/beam/normal/" + id), logTexture);
        BlockModelUtils.createBeamBlock(models, beam_stripped_log.get(),
                x.rl(MODULE_ID, "block/beam/stripped/" + id), strippedTexture);

        if (isSpecial())
            return;

        BlockModelUtils.createBeamBlock(models, beam_wood.get(),
                logTexture, logTexture);

        BlockModelUtils.createBeamBlock(models, beam_stripped_wood.get(),
                strippedTexture, strippedTexture);

    }

    public void createBlockTags(Function<TagKey<Block>, TagAppender<Block, Block>> tags) {
        tags.apply(zTags.Blocks.BEAM_LOG).add(beam_log.get());
        tags.apply(zTags.Blocks.BEAM_STRIPPED_LOG).add(beam_stripped_log.get());

        if (isSpecial())
            return;

        tags.apply(zTags.Blocks.BEAM_WOOD).add(beam_wood.get());
        tags.apply(zTags.Blocks.BEAM_STRIPPED_WOOD).add(beam_stripped_wood.get());

    }

    public void createItemTags(Function<TagKey<Item>, TagAppender<Item, Item>> tags) {

        tags.apply(zTags.Items.BEAM_LOG).add(beam_log.get().asItem());
        tags.apply(zTags.Items.BEAM_STRIPPED_LOG).add(beam_stripped_log.get().asItem());

        if (!isFungus())
            tags.apply(zTags.Items.BEAMS_THAT_BURN)
                    .add(beam_log.get().asItem(), beam_stripped_log.get().asItem());

        if (isSpecial())
            return;

        tags.apply(zTags.Items.BEAM_WOOD).add(beam_wood.get().asItem());
        tags.apply(zTags.Items.BEAM_STRIPPED_WOOD).add(beam_stripped_wood.get().asItem());

        if (!isFungus())
            tags.apply(zTags.Items.BEAMS_THAT_BURN)
                    .add(beam_wood.get().asItem(), beam_stripped_wood.get().asItem());

    }
}