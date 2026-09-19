package com.synergy.vintagetech.api.factories.beams;

import java.util.EnumMap;
import java.util.Map;

import com.synergy.vintagetech.init.Material;
import com.synergy.vintagetech.init.builder.WoodenBeam;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.neoforge.registries.DeferredHolder;

public class BeamFactory {

    public record BeamType(DeferredHolder<Block, Block> normal, DeferredHolder<Block, Block> stripped,
            DeferredHolder<Block, Block> full, DeferredHolder<Block, Block> stripped_full) {
    }

    private static final Map<WoodType, BeamType> BEAMS = new EnumMap<>(WoodType.class);

    public static void register() {
        for (var wood : WoodType.values()) {

            var normal = normal(wood);

            var stripped = stripped(wood);

            DeferredHolder<Block, Block> full = null;
            DeferredHolder<Block, Block> stripped_full = null;

            if (!wood.isSpecial()) {
                full = full(wood);

                stripped_full = stripped_full(wood);
            }

            BEAMS.put(wood, new BeamType(normal, stripped, full, stripped_full));
        }
    }

    private static DeferredHolder<Block, Block> normal(WoodType wood) {
        return create(wood, "", "", false);
    }

    private static DeferredHolder<Block, Block> stripped(WoodType wood) {
        return create(wood, "stripped_", "", true);
    }

    private static DeferredHolder<Block, Block> full(WoodType wood) {
        return create(wood, "", "_full", false);
    }

    private static DeferredHolder<Block, Block> stripped_full(WoodType wood) {
        return create(wood, "stripped_", "_full", true);
    }

    private static DeferredHolder<Block, Block> create(WoodType wood, String prefix, String suffix,
            boolean isStripped) {
        return Material.registerItemBlock(
                prefix + wood.id() + suffix + "_beam",
                (p, key) -> new WoodenBeam(
                        Properties
                                .ofFullCopy(isStripped
                                        ? wood.stripped()
                                        : wood.log())
                                .setId(key)));
    }

    public static BeamType get(WoodType wood) {
        return BEAMS.get(wood);
    }
}
