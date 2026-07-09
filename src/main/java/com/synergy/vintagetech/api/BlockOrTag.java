package com.synergy.vintagetech.api;

import com.devdyna.cakesticklib.api.utils.x;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public sealed interface BlockOrTag permits BlockOrTag.block, BlockOrTag.tag {

    Codec<BlockOrTag> CODEC = Codec.STRING.flatXmap(
            string -> {
                if (string.startsWith("#"))
                    return DataResult.success(new tag(TagKey.create(Registries.BLOCK, x.parse(string.substring(1)))));

                return DataResult.success(new block(x.getBlock(Identifier.parse(string))));
            },
            value -> switch (value) {
                case block block -> DataResult.success(x.rl(block.block()).toString());
                case tag tag -> DataResult.success("#" + tag.tag().location());
            });

    StreamCodec<RegistryFriendlyByteBuf, BlockOrTag> STREAM_CODEC = new StreamCodec<>() {

        @Override
        public BlockOrTag decode(RegistryFriendlyByteBuf buf) {
            if (buf.readBoolean())
                return new tag(TagKey.create(Registries.BLOCK, Identifier.STREAM_CODEC.decode(buf)));
            return new block(ByteBufCodecs.registry(Registries.BLOCK).decode(buf));
        }

        @Override
        public void encode(RegistryFriendlyByteBuf buf, BlockOrTag value) {
            switch (value) {
                case block block -> {
                    buf.writeBoolean(false);
                    ByteBufCodecs.registry(Registries.BLOCK).encode(buf, block.block());
                }
                case tag tag -> {
                    buf.writeBoolean(true);
                    Identifier.STREAM_CODEC.encode(buf, tag.tag().location());
                }
            }
        }
    };

    boolean test(Block block);

    record block(Block block) implements BlockOrTag {

        @Override
        public boolean test(Block block) {
            return this.block == block;
        }
    }

    record tag(TagKey<Block> tag) implements BlockOrTag {

        @Override
        public boolean test(Block block) {
            return block.defaultBlockState().is(tag);
        }
    }

    static BlockOrTag add(Block block) {
        return new block(block);
    }

    static BlockOrTag add(TagKey<Block> tag) {
        return new tag(tag);
    }
}