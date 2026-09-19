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
            DeferredHolder<Block, Block> wood, DeferredHolder<Block, Block> stripped_wood) {
    }

    private static final Map<WoodType, BeamType> BEAMS = new EnumMap<>(WoodType.class);

    public static void register() {
        for (var type : WoodType.values()) {

            var normal = normal(type);

            var stripped = stripped(type);

            DeferredHolder<Block, Block> wood = null;
            DeferredHolder<Block, Block> stripped_wood = null;

            if (!type.isSpecial()) {
                wood = wood(type);

                stripped_wood = stripped_wood(type);
            }

            BEAMS.put(type, new BeamType(normal, stripped, wood, stripped_wood));
        }
    }

    private static DeferredHolder<Block, Block> normal(WoodType wood) {
        return create(wood, "", "", false);
    }

    private static DeferredHolder<Block, Block> stripped(WoodType wood) {
        return create(wood, "stripped_", "", true);
    }

    private static DeferredHolder<Block, Block> wood(WoodType wood) {
        return create(wood, "", "_wood", false);
    }

    private static DeferredHolder<Block, Block> stripped_wood(WoodType wood) {
        return create(wood, "stripped_", "_wood", true);
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
