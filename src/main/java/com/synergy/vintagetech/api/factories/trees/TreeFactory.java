package com.synergy.vintagetech.api.factories.trees;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.devdyna.cakesticklib.api.RegistryUtils;
import com.synergy.vintagetech.api.factories.trees.TreeBuilders.*;
import com.synergy.vintagetech.api.factories.trees.TreeOptions.TreeParticleLeaves;
import com.synergy.vintagetech.api.factories.trees.TreeOptions.WoodType;
import com.synergy.vintagetech.init.Material;
import com.synergy.vintagetech.init.types.zBlocks;
import com.synergy.vintagetech.init.types.zItems;

import net.minecraft.core.Direction;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public final class TreeFactory {

        private static final Map<String, TreeSet> TREES = new LinkedHashMap<>();

        public static Collection<TreeSet> getAll() {
                return TREES.values();
        }

        public static boolean contains(TreeSet set) {
                return TREES.containsKey(set.id());
        }

        public static TreeSet get(TreeSet set) {
                return TREES.get(set.id());
        }

        public static TreeSet register(String id, MapColor plank, MapColor log_side, float hardness, float resistance,
                        SoundType generic, Function<String, BlockSetBuilder> blockSetBuilder,
                        BiFunction<String, BlockSetType, WoodTypeBuilder> woodTypeBuilder, WoodType type,
                        TreeParticleLeaves particles, Function<String, TreeGrowerBuilder> grower, boolean burnable) {

                var blockSetType = blockSetBuilder.apply(id).build();
                var woodType = woodTypeBuilder.apply(id, blockSetType).build();

                var log = Material.registerItemBlock(id + type.suffix4(),
                                p -> {

                                        p.strength(hardness, resistance).sound(generic);

                                        if (burnable)
                                                p.ignitedByLava();

                                        return new RotatedPillarBlock(p.mapColor(state -> state
                                                        .getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y
                                                                        ? log_side
                                                                        : plank));
                                });

                var strippedLog = Material.registerItemBlock(WoodType.STRIPPED + id + type.suffix4(),

                                p -> {

                                        p.strength(hardness, resistance).sound(generic);

                                        if (burnable)
                                                p.ignitedByLava();

                                        return new RotatedPillarBlock(p.mapColor(plank));
                                });

                var wood = Material.registerItemBlock(
                                id + type.suffix6(),

                                p -> {

                                        p.strength(hardness, resistance).sound(generic);

                                        if (burnable)
                                                p.ignitedByLava();

                                        return new RotatedPillarBlock(p.mapColor(log_side));
                                });

                var strippedWood = Material.registerItemBlock(
                                TreeOptions.WoodType.STRIPPED + id + type.suffix6(),

                                p -> {

                                        p.strength(hardness, resistance).sound(generic);

                                        if (burnable)
                                                p.ignitedByLava();

                                        return new RotatedPillarBlock(p.mapColor(plank));
                                });

                var planks = Material.registerItemBlock(
                                id + "_planks",

                                (p) -> {
                                        p.strength(hardness, resistance).sound(generic);

                                        if (burnable)
                                                p.ignitedByLava();

                                        return new Block(p.mapColor(plank));
                                });

                var leaves = Material.registerItemBlock(
                                id + "_leaves",
                                p -> new TintedParticleLeavesBlock(
                                                particles.get(),
                                                p.strength(0.2F)
                                                                .sound(SoundType.GRASS)
                                                                .pushReaction(PushReaction.DESTROY)
                                                                .noOcclusion()
                                                                .ignitedByLava()
                                                                .randomTicks()
                                                                .isValidSpawn(Blocks::ocelotOrParrot)
                                                                .isSuffocating((s, l, ps) -> false)
                                                                .isViewBlocking((s, l, ps) -> false)
                                                                .isRedstoneConductor((s, l, ps) -> false)
                                                                .mapColor(MapColor.PLANT)));

                var sapling = Material.registerItemBlock(id + "_sapling",
                                p -> new SaplingBlock(grower.apply(id).build(),
                                                p.pushReaction(PushReaction.DESTROY)
                                                                .instabreak()
                                                                .sound(SoundType.GRASS)
                                                                .noCollision()
                                                                .randomTicks()
                                                                .ignitedByLava()
                                                                .mapColor(MapColor.PLANT)));

                var pottedSapling = zBlocks.zTreeBlock.registerBlock("potted_" + id + "_sapling",
                                p -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, sapling,
                                                p.instabreak()
                                                                .noOcclusion()
                                                                .pushReaction(PushReaction.DESTROY)));

                var stairs = Material.registerItemBlock(id + "_stairs",
                                p -> new StairBlock(
                                                planks.get().defaultBlockState(),
                                                p.strength(hardness, resistance)
                                                                .sound(generic)
                                                                .ignitedByLava()
                                                                .mapColor(plank)));

                var slab = Material.registerItemBlock(id + "_slab",
                                p -> new SlabBlock(
                                                p.strength(hardness, resistance)
                                                                .sound(generic)
                                                                .ignitedByLava()
                                                                .mapColor(plank)));

                var shelf = Material.registerItemBlock(id + "_shelf",
                                p -> new ShelfBlock(
                                                p.strength(hardness, resistance)
                                                                .sound(generic)
                                                                .ignitedByLava()
                                                                .mapColor(plank)));

                var fence = Material.registerItemBlock(id + "_fence",
                                p -> new FenceBlock(
                                                p.strength(hardness, resistance)
                                                                .sound(generic)
                                                                .ignitedByLava()
                                                                .mapColor(plank)));

                var fenceGate = Material.registerItemBlock(id + "_fence_gate",
                                p -> new FenceGateBlock(woodType,
                                                p.strength(hardness, resistance)
                                                                .sound(generic)
                                                                .ignitedByLava()
                                                                .mapColor(plank)));

                var door = Material.registerItemBlock(id + "_door",
                                p -> new DoorBlock(blockSetType,
                                                p.strength(3.0F)
                                                                .ignitedByLava()
                                                                .mapColor(plank)));

                var trapdoor = Material.registerItemBlock(id + "_trapdoor",
                                p -> new TrapDoorBlock(blockSetType,
                                                p.strength(3.0F)
                                                                .ignitedByLava()
                                                                .noOcclusion()
                                                                .mapColor(plank)));

                var button = Material.registerItemBlock(id + "_button",
                                p -> new ButtonBlock(blockSetType, 30,
                                                p.strength(0.5F)
                                                                .noCollision()
                                                                .ignitedByLava()
                                                                .mapColor(plank)));

                var pressurePlate = Material.registerItemBlock(id + "_pressure_plate",
                                p -> new PressurePlateBlock(blockSetType,
                                                p.strength(0.5F)
                                                                .ignitedByLava()
                                                                .mapColor(plank)));

                var sign = zBlocks.zTreeBlock.registerBlock(id + "_sign",
                                p -> new StandingSignBlock(woodType,
                                                p.forceSolidOn()
                                                                .noCollision()
                                                                .strength(1.0F)));

                var wallSign = zBlocks.zTreeBlock.registerBlock(id + "_wall_sign",
                                p -> new WallSignBlock(woodType,
                                                p.forceSolidOn()
                                                                .noCollision()
                                                                .strength(1.0F)));

                var hangingSign = zBlocks.zTreeBlock.registerBlock(id + "_hanging_sign",
                                p -> new CeilingHangingSignBlock(woodType,
                                                p.forceSolidOn()
                                                                .noCollision()
                                                                .strength(1.0F)));

                var wallHangingSign = zBlocks.zTreeBlock.registerBlock(id + "_wall_hanging_sign",
                                p -> new WallHangingSignBlock(woodType,
                                                p.forceSolidOn()
                                                                .noCollision()
                                                                .strength(1.0F)));

                var signItem = zItems.zTreeItem.registerItem(id + "_sign",
                                p -> new SignItem(sign.get(), wallSign.get(), p));

                var hangingSignItem = zItems.zTreeItem.registerItem(id + "_hanging_sign",
                                p -> new HangingSignItem(hangingSign.get(), wallHangingSign.get(), p));

                var itemTag = RegistryUtils.tagItem(MODULE_ID, id + "_logs");

                var blockTag = RegistryUtils.tagBlock(MODULE_ID, id + "_logs");

                var set = new TreeSet(id,
                                log, strippedLog,
                                wood, strippedWood,
                                planks, leaves,
                                sapling, pottedSapling,
                                stairs, slab, shelf,
                                fence, fenceGate,
                                door, trapdoor,
                                button, pressurePlate,
                                sign, wallSign,
                                hangingSign, wallHangingSign,
                                signItem, hangingSignItem,
                                itemTag, blockTag);

                TREES.put(id, set);
                return set;
        }
}