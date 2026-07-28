package com.synergy.vintagetech.init.types;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.Optional;
import java.util.function.Function;

import net.minecraft.core.Direction;
import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.init.builder.RopeBlock;
import com.synergy.vintagetech.init.builder.WoodenBeam;
import com.synergy.vintagetech.init.builder.basket.BasketBlock;
import com.synergy.vintagetech.init.builder.centrifuge.CentrifugeBlock;
import com.synergy.vintagetech.init.builder.creative_engine.CreativeEngineBlock;
import com.synergy.vintagetech.init.builder.crushing_tub.CrushingTubBlock;
import com.synergy.vintagetech.init.builder.drying_rack.DryingRackBlock;
import com.synergy.vintagetech.init.builder.dynamo.DynamoBlock;
import com.synergy.vintagetech.init.builder.electric_motor.ElectricMotorBlock;
import com.synergy.vintagetech.init.builder.evaporation_basin.EvaporationBasinBlock;
import com.synergy.vintagetech.init.builder.fan.FanBlock;
import com.synergy.vintagetech.init.builder.mechanical_farmland.MechanicalFarmlandBlock;
import com.synergy.vintagetech.init.builder.millstone.MillstoneBlock;
import com.synergy.vintagetech.init.builder.plants.Aloe;
import com.synergy.vintagetech.init.builder.plants.BlueBerry;
import com.synergy.vintagetech.init.builder.plants.CaveWheat;
import com.synergy.vintagetech.init.builder.plants.Hemp;
import com.synergy.vintagetech.init.builder.plants.Lavender;
import com.synergy.vintagetech.init.builder.plants.SoyBeans;
import com.synergy.vintagetech.init.builder.saw.SawBlock;
import com.synergy.vintagetech.init.builder.steam_engine.SteamEngineBlock;
import com.synergy.vintagetech.init.builder.transmission.*;
import com.synergy.vintagetech.init.builder.treetap.TreeTapBlock;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zBlocks {

        public static void register(IEventBus bus) {
                zBlock.register(bus);
                zBlockFluids.register(bus);
                zBlockItem.register(bus);
                zRender.register(bus);
        }

        public static final DeferredRegister.Blocks zRender = DeferredRegister.createBlocks(MODULE_ID);
        public static final DeferredRegister.Blocks zBlock = DeferredRegister.createBlocks(MODULE_ID);
        public static final DeferredRegister.Blocks zBlockFluids = DeferredRegister.createBlocks(MODULE_ID);
        public static final DeferredRegister.Blocks zBlockItem = DeferredRegister.createBlocks(MODULE_ID);

        // generators
        public static final DeferredHolder<Block, Block> STEAM_ENGINE = registerItemBlock("steam_engine",
                        p -> new SteamEngineBlock(p));

        public static final DeferredHolder<Block, Block> CREATIVE_ENGINE = registerItemBlock("creative_engine",
                        p -> new CreativeEngineBlock(p));

        // transmissions
        public static final DeferredHolder<Block, Block> AXLE = registerItemBlock("axle",
                        p -> new AxleBlock(p
                                        .mapColor(MapColor.WOOD)
                                        .instrument(NoteBlockInstrument.BASS)
                                        .strength(2.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()));

        public static final DeferredHolder<Block, Block> JUNCTION = registerItemBlock("junction",
                        p -> new JunctionBlock(p
                                        .mapColor(MapColor.WOOD)
                                        .instrument(NoteBlockInstrument.BASS)
                                        .strength(2.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()));

        public static final DeferredHolder<Block, Block> GEARSHIFT = registerItemBlock("gearshift",
                        p -> new GearShiftBlock(p
                                        .noOcclusion()
                                        .mapColor(MapColor.WOOD)
                                        .instrument(NoteBlockInstrument.BASS)
                                        .strength(2.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()));

        public static final DeferredHolder<Block, Block> CLUTCH = registerItemBlock("clutch",
                        p -> new ClutchBlock(p
                                        .noOcclusion()
                                        .mapColor(MapColor.WOOD)
                                        .instrument(NoteBlockInstrument.BASS)
                                        .strength(2.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()));

        // machines
        public static final DeferredHolder<Block, Block> SAW = registerItemBlock("saw",
                        p -> new SawBlock(p
                                        .mapColor(MapColor.WOOD)
                                        .instrument(NoteBlockInstrument.BASS)
                                        .strength(2.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()));

        public static final DeferredHolder<Block, Block> FAN = registerItemBlock("fan",
                        p -> new FanBlock(p
                                        .mapColor(MapColor.METAL)
                                        .instrument(NoteBlockInstrument.BASS)
                                        .strength(3.5F)
                                        .noOcclusion()
                                        .sound(SoundType.METAL)));

        public static final DeferredHolder<Block, Block> MILLSTONE = registerItemBlock("millstone",
                        p -> new MillstoneBlock(p
                                        .mapColor(MapColor.STONE)
                                        .instrument(NoteBlockInstrument.BASS)
                                        .strength(3.0F)
                                        .sound(SoundType.STONE)));

        public static final DeferredHolder<Block, Block> TREE_TAP = registerItemBlock("tree_tap",
                        p -> new TreeTapBlock(p
                                        .mapColor(MapColor.WOOD)
                                        .instrument(NoteBlockInstrument.BASS)
                                        .strength(1.0F)
                                        .sound(SoundType.WOOD)));

        public static final DeferredHolder<Block, Block> CENTRIFUGE = registerItemBlock("centrifuge",
                        p -> new CentrifugeBlock(p
                                        .mapColor(MapColor.WOOD)
                                        .instrument(NoteBlockInstrument.BASS)
                                        .strength(2.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()));

        // converters

        public static final DeferredHolder<Block, Block> ELECTRIC_MOTOR = registerItemBlock("electric_motor",
                        p -> new ElectricMotorBlock(p));

        public static final DeferredHolder<Block, Block> DYNAMO = registerItemBlock("dynamo",
                        p -> new DynamoBlock(p));

        // utility
        public static final DeferredHolder<Block, Block> BASKET = registerItemBlock("basket",
                        p -> new BasketBlock(p
                                        .mapColor(MapColor.WOOD)
                                        .instrument(NoteBlockInstrument.BASS)
                                        .strength(2.0F)
                                        .sound(SoundType.GRASS)
                                        .ignitedByLava()));

        public static final DeferredHolder<Block, Block> CRUSHING_TUB = registerItemBlock("crushing_tub",
                        p -> new CrushingTubBlock(p
                                        .strength(2.0F, 3.0F)
                                        .sound(SoundType.WOOD)
                                        .instrument(NoteBlockInstrument.BASS)
                                        .mapColor(MapColor.RAW_IRON)));

        public static final DeferredHolder<Block, Block> DRYING_RACK = registerItemBlock("drying_rack",
                        p -> new DryingRackBlock(p
                                        .instrument(NoteBlockInstrument.BASEDRUM)
                                        .strength(1F, 2.25F)
                                        .sound(SoundType.WOOD)
                                        .mapColor(MapColor.WOOD)
                                        .noOcclusion()));

        public static final DeferredHolder<Block, Block> EVAPORATION_BASIN = registerItemBlock("evaporation_basin",
                        p -> new EvaporationBasinBlock(p
                                        .mapColor(MapColor.COLOR_ORANGE)
                                        .instrument(NoteBlockInstrument.BASEDRUM)
                                        .strength(1F, 2.25F)
                                        .sound(SoundType.STONE)
                                        .mapColor(MapColor.TERRACOTTA_ORANGE)));

        public static final DeferredHolder<Block, Block> MECHANICAL_FARMLAND = registerItemBlock("mechanical_farmland",
                        p -> new MechanicalFarmlandBlock(p
                                        .mapColor(MapColor.WOOD)
                                        .instrument(NoteBlockInstrument.BASS)
                                        .strength(2.0F)
                                        .noOcclusion()
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()));

        public static final DeferredHolder<Block, Block> ROPE = registerItemBlock("rope",
                        p -> new RopeBlock(p
                                        .mapColor(MapColor.WOOD)
                                        .instrument(NoteBlockInstrument.BASS)
                                        .instabreak()
                                        .noOcclusion()
                                        .sound(SoundType.WOOL)
                                        .ignitedByLava()));

        public static final DeferredHolder<Block, Block> OAK_BEAM = registerItemBlock("oak_beam",
                        p -> new WoodenBeam(Properties.ofFullCopy(Blocks.OAK_LOG)
                                        .setId(ResourceKey.create(Registries.BLOCK, x.rl(MODULE_ID, "oak_beam")))));

        public static final DeferredHolder<Block, Block> SPRUCE_BEAM = registerItemBlock("spruce_beam",
                        p -> new WoodenBeam(Properties.ofFullCopy(Blocks.SPRUCE_LOG)
                                        .setId(ResourceKey.create(Registries.BLOCK, x.rl(MODULE_ID, "spruce_beam")))));

        public static final DeferredHolder<Block, Block> BIRCH_BEAM = registerItemBlock("birch_beam",
                        p -> new WoodenBeam(Properties.ofFullCopy(Blocks.BIRCH_LOG)
                                        .setId(ResourceKey.create(Registries.BLOCK, x.rl(MODULE_ID, "birch_beam")))));

        public static final DeferredHolder<Block, Block> JUNGLE_BEAM = registerItemBlock("jungle_beam",
                        p -> new WoodenBeam(Properties.ofFullCopy(Blocks.JUNGLE_LOG)
                                        .setId(ResourceKey.create(Registries.BLOCK, x.rl(MODULE_ID, "jungle_beam")))));

        public static final DeferredHolder<Block, Block> ACACIA_BEAM = registerItemBlock("acacia_beam",
                        p -> new WoodenBeam(Properties.ofFullCopy(Blocks.ACACIA_LOG)
                                        .setId(ResourceKey.create(Registries.BLOCK, x.rl(MODULE_ID, "acacia_beam")))));

        public static final DeferredHolder<Block, Block> DARK_OAK_BEAM = registerItemBlock("dark_oak_beam",
                        p -> new WoodenBeam(Properties.ofFullCopy(Blocks.DARK_OAK_LOG)
                                        .setId(ResourceKey.create(Registries.BLOCK,
                                                        x.rl(MODULE_ID, "dark_oak_beam")))));

        public static final DeferredHolder<Block, Block> MANGROVE_BEAM = registerItemBlock("mangrove_beam",
                        p -> new WoodenBeam(Properties.ofFullCopy(Blocks.MANGROVE_LOG)
                                        .setId(ResourceKey.create(Registries.BLOCK,
                                                        x.rl(MODULE_ID, "mangrove_beam")))));

        public static final DeferredHolder<Block, Block> CHERRY_BEAM = registerItemBlock("cherry_beam",
                        p -> new WoodenBeam(Properties.ofFullCopy(Blocks.CHERRY_LOG)
                                        .setId(ResourceKey.create(Registries.BLOCK, x.rl(MODULE_ID, "cherry_beam")))));

        public static final DeferredHolder<Block, Block> PALE_OAK_BEAM = registerItemBlock("pale_oak_beam",
                        p -> new WoodenBeam(Properties.ofFullCopy(Blocks.PALE_OAK_LOG)
                                        .setId(ResourceKey.create(Registries.BLOCK,
                                                        x.rl(MODULE_ID, "pale_oak_beam")))));

        // TODO IMP : BLOCKS
        public static final DeferredHolder<Block, Block> CHEESE = registerItemBlock("cheese",
                        p -> new Block(p));

        // heat + fluid + item -> fluid + item
        public static final DeferredHolder<Block, Block> CRUCIBLE = registerItemBlock("crucible",
                        p -> new Block(p));

        // item -> item
        // public static final DeferredHolder<Block, Block> HYDRAULIC_PRESS = registerItemBlock("hydraulic_press",
        //                 p -> new Block(p));

        // crops
        public static final DeferredHolder<Block, Block> CAVE_WHEAT = zBlock.registerBlock("cave_wheat",
                        p -> new CaveWheat(p));

        public static final DeferredHolder<Block, Block> SOYBEANS = zBlock.registerBlock("soybeans",
                        p -> new SoyBeans(p));

        public static final DeferredHolder<Block, Block> HEMP = zBlock.registerBlock("hemp",
                        p -> new Hemp(p));

        public static final DeferredHolder<Block, Block> LAVENDER = registerItemBlock("lavender",
                        p -> new Lavender(MobEffects.INSTANT_HEALTH, 1.0F, p));

        public static final DeferredHolder<Block, Block> ALOE_PLANT = zBlock.registerBlock("aloe_plant",
                        p -> new Aloe(p));

        public static final DeferredHolder<Block, Block> BLUEBERRY_BUSH = zBlock.registerBlock("blueberry_bush",
                        p -> new BlueBerry(p));

        public static final DeferredHolder<Block, Block> IRONWOOD_LOG = registerItemBlock("ironwood_log",
                        p -> new RotatedPillarBlock(p
                                        .strength(2.0F, 3.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()
                                        .instrument(NoteBlockInstrument.BASS)
                                        .mapColor(
                                                        (s) -> s.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y
                                                                        ? MapColor.TERRACOTTA_BLACK
                                                                        : MapColor.RAW_IRON)));

        public static final DeferredHolder<Block, Block> STRIPPED_IRONWOOD_LOG = registerItemBlock(
                        "stripped_ironwood_log", p -> new RotatedPillarBlock(p
                                        .strength(2.0F, 3.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()
                                        .mapColor(MapColor.RAW_IRON)
                                        .instrument(NoteBlockInstrument.BASS)));

        public static final DeferredHolder<Block, Block> IRONWOOD_WOOD = registerItemBlock("ironwood_wood",
                        p -> new RotatedPillarBlock(p
                                        .strength(2.0F, 3.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()
                                        .mapColor(MapColor.TERRACOTTA_BLACK)
                                        .instrument(NoteBlockInstrument.BASS)));

        public static final DeferredHolder<Block, Block> STRIPPED_IRONWOOD_WOOD = registerItemBlock(
                        "stripped_ironwood_wood", p -> new RotatedPillarBlock(p
                                        .strength(2.0F, 3.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()
                                        .mapColor(MapColor.RAW_IRON)
                                        .instrument(NoteBlockInstrument.BASS)));

        public static final DeferredHolder<Block, Block> IRONWOOD_PLANKS = registerItemBlock("ironwood_planks",
                        p -> new Block(p
                                        .strength(2.0F, 3.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()
                                        .mapColor(MapColor.RAW_IRON)
                                        .instrument(NoteBlockInstrument.BASS)));

        public static final DeferredHolder<Block, Block> IRONWOOD_SAPLING = registerItemBlock("ironwood_sapling",
                        p -> new SaplingBlock(new TreeGrower(
                                        "ironwood",
                                        0.5F,
                                        Optional.empty(),
                                        Optional.empty(),
                                        Optional.of(zWorldGenFeatures.ConfiguredFeatures.IRONWOOD),
                                        Optional.empty(),
                                        Optional.empty(),
                                        Optional.empty()),
                                        p
                                                        .pushReaction(PushReaction.DESTROY)
                                                        .instabreak()
                                                        .sound(SoundType.GRASS)
                                                        .noCollision()
                                                        .randomTicks()
                                                        .ignitedByLava()
                                                        .mapColor(MapColor.PLANT)));

        public static final DeferredHolder<Block, Block> IRONWOOD_LEAVES = registerItemBlock("ironwood_leaves",
                        p -> new TintedParticleLeavesBlock(0.01f, p
                                        .strength(0.2F)
                                        .sound(SoundType.GRASS)
                                        .pushReaction(PushReaction.DESTROY)
                                        .noOcclusion()
                                        .ignitedByLava()
                                        .randomTicks()
                                        .isValidSpawn(Blocks::ocelotOrParrot)
                                        .isSuffocating((s, g, x) -> false)
                                        .isViewBlocking((s, g, x) -> false)
                                        .isRedstoneConductor((s, g, x) -> false)
                                        .mapColor(MapColor.PLANT)));

        public static final DeferredHolder<Block, Block> IRONWOOD_STAIRS = registerItemBlock("ironwood_stairs",
                        p -> new StairBlock(IRONWOOD_PLANKS.get().defaultBlockState(), p
                                        .strength(2.0F, 3.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()
                                        .mapColor(MapColor.RAW_IRON)
                                        .instrument(NoteBlockInstrument.BASS)));

        public static final DeferredHolder<Block, Block> POTTED_IRONWOOD_SAPLING = zBlock.registerBlock(
                        "potted_ironwood_sapling",
                        p -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, IRONWOOD_SAPLING, p
                                        .instabreak()
                                        .noOcclusion()
                                        .pushReaction(PushReaction.DESTROY)));

        public static final DeferredHolder<Block, Block> IRONWOOD_SLAB = registerItemBlock("ironwood_slab",
                        p -> new SlabBlock(p
                                        .strength(2.0F, 3.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()
                                        .mapColor(MapColor.RAW_IRON)
                                        .instrument(NoteBlockInstrument.BASS)));

        // TODO IMP : FLAX

        // renderer
        public static final DeferredHolder<Block, Block> RENDER_HALF_AXLE = renderBlock("render_half_axle");
        public static final DeferredHolder<Block, Block> RENDER_FAN_BLADE = renderBlock("render_fan_blade");
        public static final DeferredHolder<Block, Block> RENDER_MILLSTONE = renderBlock("render_millstone");
        public static final DeferredHolder<Block, Block> RENDER_CENTRIFUGE = renderBlock("render_centrifuge_blades");
        public static final DeferredHolder<Block, Block> RENDER_PRESS_HEAD = renderBlock("render_press_head");

        public static DeferredHolder<Block, Block> registerItemBlock(String blockname,
                        Function<BlockBehaviour.Properties, ? extends Block> sup) {
                DeferredHolder<Block, Block> block = zBlockItem.registerBlock(blockname, sup);
                zItems.zBlockItem.registerSimpleBlockItem(block);
                return block;
        }

        public static DeferredHolder<Block, Block> renderBlock(String id) {
                return zRender.registerSimpleBlock(id, p -> p.noLootTable());
        }

}
