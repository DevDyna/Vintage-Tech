package com.synergy.vintagetech.api.factories.beams;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.synergy.vintagetech.api.factories.trees.TreeOptions.WoodOptionType;
import com.synergy.vintagetech.init.Material;
import com.synergy.vintagetech.init.builder.WoodenBeam;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredHolder;

public final class BeamFactory {

        private static final Map<String, BeamSet> BEAMS = new LinkedHashMap<>();

        public static Collection<BeamSet> getAll() {
                return BEAMS.values();
        }

        public static boolean contains(BeamSet v) {
                return BEAMS.containsKey(v.id());
        }

        public static BeamSet get(BeamSet v) {
                return BEAMS.get(v.id());
        }

        public static BeamSet register(String id, WoodOptionType type, Supplier<Block> log, Supplier<Block> log_strip,
                        Identifier texture, Identifier strippedTexture, MapColor light, MapColor dark) {

                var normal = create(id + WoodOptionType.BEAM, log, light, dark);

                var stripped = create(WoodOptionType.STRIPPED + id + WoodOptionType.BEAM, log_strip, light, dark);

                DeferredHolder<Block, Block> wood = null;
                DeferredHolder<Block, Block> strippedWood = null;

                if (!type.isSpecial()) {
                        wood = create(id + type.suffix6() + WoodOptionType.BEAM, log, light, dark);

                        strippedWood = create(WoodOptionType.STRIPPED + id + type.suffix6()
                                        + WoodOptionType.BEAM, log_strip, light, dark);
                }

                var set = new BeamSet(id, normal, stripped, wood, strippedWood, texture, strippedTexture, type, log,
                                log_strip);

                BEAMS.put(id, set);

                return set;
        }

        private static DeferredHolder<Block, Block> create(String id, Supplier<Block> b, MapColor light,
                        MapColor dark) {
                return Material.registerItemBlock(id,
                                (p, k) -> new WoodenBeam(Properties.ofFullCopy(b.get())
                                                .mapColor(s -> s.getValue(WoodenBeam.Y) ? light : dark)
                                                .setId(k)));
        }
}