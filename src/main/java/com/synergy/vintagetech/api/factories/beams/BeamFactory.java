package com.synergy.vintagetech.api.factories.beams;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.synergy.vintagetech.api.factories.trees.TreeOptions.WoodType;
import com.synergy.vintagetech.init.Material;
import com.synergy.vintagetech.init.builder.WoodenBeam;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
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

        public static BeamSet register(String id, WoodType woodType, Supplier<Block> log, Supplier<Block> log_strip,
                        Identifier texture, Identifier strippedTexture) {

                var normal = create(id + WoodType.BEAM, log);

                var stripped = create(WoodType.STRIPPED + id + WoodType.BEAM, log_strip);

                DeferredHolder<Block, Block> wood = null;
                DeferredHolder<Block, Block> strippedWood = null;

                if (!woodType.isSpecial()) {
                        wood = create(id + woodType.suffix6() + WoodType.BEAM, log);

                        strippedWood = create(WoodType.STRIPPED + id + woodType.suffix6()
                                        + WoodType.BEAM, log_strip);
                }

                var set = new BeamSet(id, normal, stripped, wood, strippedWood, texture, strippedTexture, woodType,log,log_strip);

                BEAMS.put(id, set);

                return set;
        }

        private static DeferredHolder<Block, Block> create(String id, Supplier<Block> b) {
                return Material.registerItemBlock(id,
                                (p, k) -> new WoodenBeam(Properties.ofFullCopy(b.get()).setId(k)));
        }
}