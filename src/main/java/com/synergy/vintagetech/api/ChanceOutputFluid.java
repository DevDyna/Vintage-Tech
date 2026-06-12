package com.synergy.vintagetech.api;

import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.fluids.FluidStackTemplate;

public record ChanceOutputFluid(FluidStackTemplate fluid, float chance) {

        public static final Codec<ChanceOutputFluid> CODEC = RecordCodecBuilder.create(inst -> inst.group(
                        FluidStackTemplate.CODEC.fieldOf("fluid").forGetter(ChanceOutputFluid::fluid),
                        Codec.floatRange(0, 1).fieldOf("chance").forGetter(ChanceOutputFluid::chance))
                        .apply(inst, ChanceOutputFluid::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, ChanceOutputFluid> STREAM_CODEC = StreamCodec
                        .composite(
                                        FluidStackTemplate.STREAM_CODEC, ChanceOutputFluid::fluid,
                                        ByteBufCodecs.FLOAT, ChanceOutputFluid::chance,
                                        ChanceOutputFluid::new);

        public static final ChanceOutputFluid of(FluidStackTemplate fluid, float chance) {
                return new ChanceOutputFluid(fluid, chance);
        }

        public static final Optional<ChanceOutputFluid> optional(ChanceOutputFluid t) {
                return t != null ? Optional.of(t) : Optional.empty();
        }

        public static final boolean valid(ChanceOutputFluid t) {
                return itemValid(t) && t.chance > 0f && t.chance <= 1f;
        }

        public static final boolean itemValid(ChanceOutputFluid t) {
                return t != null && t.fluid != null && t.fluid.fluid().value() != null;
        }
}