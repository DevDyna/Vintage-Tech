package com.synergy.vintagetech.init.types;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.Optional;
import net.minecraft.core.Direction;
import com.synergy.vintagetech.init.Material;
import com.synergy.vintagetech.init.builder.RopeBlock;
import com.synergy.vintagetech.init.builder.WoodenBeam;
import com.synergy.vintagetech.init.builder.basket.BasketBlock;
import com.synergy.vintagetech.init.builder.centrifuge.CentrifugeBlock;
import com.synergy.vintagetech.init.builder.cheese.AgedCheeseBlock;
import com.synergy.vintagetech.init.builder.cheese.FreshCheeseBlock;
import com.synergy.vintagetech.init.builder.cheese.MaturedCheeseBlock;
import com.synergy.vintagetech.init.builder.cheese.SealedCheeseBlock;
import com.synergy.vintagetech.init.builder.creative_engine.CreativeEngineBlock;
import com.synergy.vintagetech.init.builder.crucible.CrucibleBlock;
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
import com.synergy.vintagetech.init.builder.transmission.*;
import com.synergy.vintagetech.init.builder.treetap.TreeTapBlock;
import com.synergy.vintagetech.init.builder.windmill.WindmillBlock;

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
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
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

        public static final DeferredHolder<Block, Block> WINDMILL = Material.registerItemBlock("windmill",
                        p -> new WindmillBlock(p
                                        .strength(2.0F, 3.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()
                                        .mapColor(MapColor.WOOD)));

        public static final DeferredHolder<Block, Block> CREATIVE_ENGINE = Material.registerItemBlock("creative_engine",
                        p -> new CreativeEngineBlock(p));

        // transmissions
        public static final DeferredHolder<Block, Block> AXLE = Material.registerItemBlock("axle",
                        p -> new AxleBlock(p
                                        .mapColor(MapColor.WOOD)

                                        .strength(2.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()));

        public static final DeferredHolder<Block, Block> JUNCTION = Material.registerItemBlock("junction",
                        p -> new JunctionBlock(p
                                        .mapColor(MapColor.WOOD)

                                        .strength(2.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()));

        public static final DeferredHolder<Block, Block> GEARSHIFT = Material.registerItemBlock("gearshift",
                        p -> new GearShiftBlock(p
                                        .noOcclusion()
                                        .mapColor(MapColor.WOOD)

                                        .strength(2.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()));

        public static final DeferredHolder<Block, Block> CLUTCH = Material.registerItemBlock("clutch",
                        p -> new ClutchBlock(p
                                        .noOcclusion()
                                        .mapColor(MapColor.WOOD)

                                        .strength(2.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()));

        // machines
        public static final DeferredHolder<Block, Block> SAW = Material.registerItemBlock("saw",
                        p -> new SawBlock(p
                                        .mapColor(MapColor.WOOD)

                                        .strength(2.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()));

        public static final DeferredHolder<Block, Block> FAN = Material.registerItemBlock("fan",
                        p -> new FanBlock(p
                                        .mapColor(MapColor.METAL)

                                        .strength(3.5F)
                                        .noOcclusion()
                                        .sound(SoundType.METAL)));

        public static final DeferredHolder<Block, Block> MILLSTONE = Material.registerItemBlock("millstone",
                        p -> new MillstoneBlock(p
                                        .mapColor(MapColor.STONE)

                                        .strength(3.0F)
                                        .sound(SoundType.STONE)));

        public static final DeferredHolder<Block, Block> TREE_TAP = Material.registerItemBlock("tree_tap",
                        p -> new TreeTapBlock(p
                                        .mapColor(MapColor.WOOD)

                                        .strength(1.0F)
                                        .sound(SoundType.WOOD)));

        public static final DeferredHolder<Block, Block> CRUCIBLE = Material.registerItemBlock("crucible",
                        p -> new CrucibleBlock(p
                                        .mapColor(MapColor.METAL)
                                        .noOcclusion()
                                        .strength(2.0F)
                                        .sound(SoundType.METAL)));

        public static final DeferredHolder<Block, Block> CENTRIFUGE = Material.registerItemBlock("centrifuge",
                        p -> new CentrifugeBlock(p
                                        .mapColor(MapColor.WOOD)

                                        .strength(2.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()));

        // converters

        public static final DeferredHolder<Block, Block> ELECTRIC_MOTOR = Material.registerItemBlock("electric_motor",
                        p -> new ElectricMotorBlock(p));

        public static final DeferredHolder<Block, Block> DYNAMO = Material.registerItemBlock("dynamo",
                        p -> new DynamoBlock(p));

        // utility
        public static final DeferredHolder<Block, Block> BASKET = Material.registerItemBlock("basket",
                        p -> new BasketBlock(p
                                        .mapColor(MapColor.WOOD)
                                        .strength(0.75F)
                                        .sound(SoundType.GRASS)
                                        .ignitedByLava()));

        public static final DeferredHolder<Block, Block> CRUSHING_TUB = Material.registerItemBlock("crushing_tub",
                        p -> new CrushingTubBlock(p
                                        .strength(1.0F, 1.50F)
                                        .sound(SoundType.WOOD)

                                        .mapColor(MapColor.RAW_IRON)));

        public static final DeferredHolder<Block, Block> DRYING_RACK = Material.registerItemBlock("drying_rack",
                        p -> new DryingRackBlock(p
                                        .strength(1F, 2.25F)
                                        .sound(SoundType.WOOD)
                                        .mapColor(MapColor.WOOD)
                                        .noOcclusion()));

        public static final DeferredHolder<Block, Block> EVAPORATION_BASIN = Material.registerItemBlock("evaporation_basin",
                        p -> new EvaporationBasinBlock(p
                                        .mapColor(MapColor.COLOR_ORANGE)
                                        .strength(1F, 2.25F)
                                        .sound(SoundType.STONE)
                                        .mapColor(MapColor.TERRACOTTA_ORANGE)));

        public static final DeferredHolder<Block, Block> MECHANICAL_FARMLAND = Material.registerItemBlock("mechanical_farmland",
                        p -> new MechanicalFarmlandBlock(p
                                        .mapColor(MapColor.WOOD)

                                        .strength(2.0F)
                                        .noOcclusion()
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()));

        public static final DeferredHolder<Block, Block> ROPE = Material.registerItemBlock("rope",
                        p -> new RopeBlock(p
                                        .mapColor(MapColor.WOOD)

                                        .instabreak()
                                        .noOcclusion()
                                        .sound(SoundType.WOOL)
                                        .ignitedByLava()));

        public static final DeferredHolder<Block, Block> OAK_BEAM = Material.registerItemBlock("oak_beam",
                        (p, k) -> new WoodenBeam(Properties.ofFullCopy(Blocks.OAK_LOG).setId(k)));

        public static final DeferredHolder<Block, Block> SPRUCE_BEAM = Material.registerItemBlock("spruce_beam",
                        (p, k) -> new WoodenBeam(Properties.ofFullCopy(Blocks.SPRUCE_LOG).setId(k)));

        public static final DeferredHolder<Block, Block> BIRCH_BEAM = Material.registerItemBlock("birch_beam",
                        (p, k) -> new WoodenBeam(Properties.ofFullCopy(Blocks.BIRCH_LOG).setId(k)));

        public static final DeferredHolder<Block, Block> JUNGLE_BEAM = Material.registerItemBlock("jungle_beam",
                        (p, k) -> new WoodenBeam(Properties.ofFullCopy(Blocks.JUNGLE_LOG).setId(k)));

        public static final DeferredHolder<Block, Block> ACACIA_BEAM = Material.registerItemBlock("acacia_beam",
                        (p, k) -> new WoodenBeam(Properties.ofFullCopy(Blocks.ACACIA_LOG).setId(k)));

        public static final DeferredHolder<Block, Block> DARK_OAK_BEAM = Material.registerItemBlock("dark_oak_beam",
                        (p, k) -> new WoodenBeam(Properties.ofFullCopy(Blocks.DARK_OAK_LOG).setId(k)));

        public static final DeferredHolder<Block, Block> MANGROVE_BEAM = Material.registerItemBlock("mangrove_beam",
                        (p, k) -> new WoodenBeam(Properties.ofFullCopy(Blocks.MANGROVE_LOG).setId(k)));

        public static final DeferredHolder<Block, Block> CHERRY_BEAM = Material.registerItemBlock("cherry_beam",
                        (p, k) -> new WoodenBeam(Properties.ofFullCopy(Blocks.CHERRY_LOG).setId(k)));

        public static final DeferredHolder<Block, Block> PALE_OAK_BEAM = Material.registerItemBlock("pale_oak_beam",
                        (p, k) -> new WoodenBeam(Properties.ofFullCopy(Blocks.PALE_OAK_LOG).setId(k)));

        public static final DeferredHolder<Block, Block> BAMBOO_BEAM = Material.registerItemBlock("bamboo_beam",
                        (p, k) -> new WoodenBeam(Properties.ofFullCopy(Blocks.BAMBOO_BLOCK).setId(k)));

        public static final DeferredHolder<Block, Block> STRIPPED_OAK_BEAM = Material.registerItemBlock("stripped_oak_beam",
                        (p, k) -> new WoodenBeam(Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).setId(k)));

        public static final DeferredHolder<Block, Block> STRIPPED_SPRUCE_BEAM = Material.registerItemBlock(
                        "stripped_spruce_beam",
                        (p, k) -> new WoodenBeam(Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_LOG).setId(k)));

        public static final DeferredHolder<Block, Block> STRIPPED_BIRCH_BEAM = Material.registerItemBlock("stripped_birch_beam",
                        (p, k) -> new WoodenBeam(Properties.ofFullCopy(Blocks.STRIPPED_BIRCH_LOG).setId(k)));

        public static final DeferredHolder<Block, Block> STRIPPED_JUNGLE_BEAM = Material.registerItemBlock(
                        "stripped_jungle_beam",
                        (p, k) -> new WoodenBeam(Properties.ofFullCopy(Blocks.STRIPPED_JUNGLE_LOG).setId(k)));

        public static final DeferredHolder<Block, Block> STRIPPED_ACACIA_BEAM = Material.registerItemBlock(
                        "stripped_acacia_beam",
                        (p, k) -> new WoodenBeam(Properties.ofFullCopy(Blocks.STRIPPED_ACACIA_LOG).setId(k)));

        public static final DeferredHolder<Block, Block> STRIPPED_DARK_OAK_BEAM = Material.registerItemBlock(
                        "stripped_dark_oak_beam",
                        (p, k) -> new WoodenBeam(Properties.ofFullCopy(Blocks.STRIPPED_DARK_OAK_LOG).setId(k)));

        public static final DeferredHolder<Block, Block> STRIPPED_MANGROVE_BEAM = Material.registerItemBlock(
                        "stripped_mangrove_beam",
                        (p, k) -> new WoodenBeam(Properties.ofFullCopy(Blocks.STRIPPED_MANGROVE_LOG).setId(k)));

        public static final DeferredHolder<Block, Block> STRIPPED_CHERRY_BEAM = Material.registerItemBlock(
                        "stripped_cherry_beam",
                        (p, k) -> new WoodenBeam(Properties.ofFullCopy(Blocks.STRIPPED_CHERRY_LOG).setId(k)));

        public static final DeferredHolder<Block, Block> STRIPPED_PALE_OAK_BEAM = Material.registerItemBlock(
                        "stripped_pale_oak_beam",
                        (p, k) -> new WoodenBeam(Properties.ofFullCopy(Blocks.STRIPPED_PALE_OAK_LOG).setId(k)));

        public static final DeferredHolder<Block, Block> STRIPPED_BAMBOO_BEAM = Material.registerItemBlock(
                        "stripped_bamboo_beam",
                        (p, k) -> new WoodenBeam(Properties.ofFullCopy(Blocks.STRIPPED_BAMBOO_BLOCK).setId(k)));

        // TODO IMP : TURNTABLE
        // TODO IMP : PRESS?

        public static final DeferredHolder<Block, Block> FRESH_CHEESE = Material.registerItemBlock("fresh_cheese",
                        p -> new FreshCheeseBlock(p.forceSolidOn().strength(0.25F).sound(SoundType.WOOL)
                                        .pushReaction(PushReaction.DESTROY)));

        public static final DeferredHolder<Block, Block> SEALED_CHEESE = Material.registerItemBlock("sealed_cheese",
                        p -> new SealedCheeseBlock(p.forceSolidOn().strength(0.25F).sound(SoundType.WOOL)
                                        .pushReaction(PushReaction.DESTROY)));

        public static final DeferredHolder<Block, Block> MATURED_CHEESE = Material.registerItemBlock("matured_cheese",
                        p -> new MaturedCheeseBlock(p.forceSolidOn().strength(0.25F).sound(SoundType.WOOL)
                                        .pushReaction(PushReaction.DESTROY)));

        public static final DeferredHolder<Block, Block> AGED_CHEESE = Material.registerItemBlock("aged_cheese",
                        p -> new AgedCheeseBlock(p.forceSolidOn().strength(0.25F).sound(SoundType.WOOL)
                                        .pushReaction(PushReaction.DESTROY)));

        // item -> item
        // public static final DeferredHolder<Block, Block> HYDRAULIC_PRESS =
        // Material.registerItemBlock("hydraulic_press",
        // p -> new Block(p));

        // crops
        public static final DeferredHolder<Block, Block> CAVE_WHEAT = zBlock.registerBlock("cave_wheat",
                        p -> new CaveWheat(p));

        public static final DeferredHolder<Block, Block> SOYBEANS = zBlock.registerBlock("soybeans",
                        p -> new SoyBeans(p));

        public static final DeferredHolder<Block, Block> HEMP = zBlock.registerBlock("hemp",
                        p -> new Hemp(p));

        public static final DeferredHolder<Block, Block> LAVENDER = Material.registerItemBlock("lavender",
                        p -> new Lavender(MobEffects.INSTANT_HEALTH, 1.0F, p));

        public static final DeferredHolder<Block, Block> ALOE_PLANT = zBlock.registerBlock("aloe",
                        p -> new Aloe(p));

        public static final DeferredHolder<Block, Block> BLUEBERRY_BUSH = zBlock.registerBlock("blueberry_bush",
                        p -> new BlueBerry(p));

        public static final DeferredHolder<Block, Block> IRONWOOD_LOG = Material.registerItemBlock("ironwood_log",
                        p -> new RotatedPillarBlock(p
                                        .strength(2.0F, 3.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()

                                        .mapColor(
                                                        (s) -> s.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y
                                                                        ? MapColor.TERRACOTTA_BLACK
                                                                        : MapColor.RAW_IRON)));

        public static final DeferredHolder<Block, Block> STRIPPED_IRONWOOD_LOG = Material.registerItemBlock(
                        "stripped_ironwood_log", p -> new RotatedPillarBlock(p
                                        .strength(2.0F, 3.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()
                                        .mapColor(MapColor.RAW_IRON)));

        public static final DeferredHolder<Block, Block> IRONWOOD_WOOD = Material.registerItemBlock("ironwood_wood",
                        p -> new RotatedPillarBlock(p
                                        .strength(2.0F, 3.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()
                                        .mapColor(MapColor.TERRACOTTA_BLACK)));

        public static final DeferredHolder<Block, Block> STRIPPED_IRONWOOD_WOOD = Material.registerItemBlock(
                        "stripped_ironwood_wood", p -> new RotatedPillarBlock(p
                                        .strength(2.0F, 3.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()
                                        .mapColor(MapColor.RAW_IRON)));

        public static final DeferredHolder<Block, Block> IRONWOOD_PLANKS = Material.registerItemBlock("ironwood_planks",
                        p -> new Block(p
                                        .strength(2.0F, 3.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()
                                        .mapColor(MapColor.RAW_IRON)));

        public static final DeferredHolder<Block, Block> IRONWOOD_SAPLING = Material.registerItemBlock("ironwood_sapling",
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

        public static final DeferredHolder<Block, Block> IRONWOOD_LEAVES = Material.registerItemBlock("ironwood_leaves",
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

        public static final DeferredHolder<Block, Block> IRONWOOD_STAIRS = Material.registerItemBlock("ironwood_stairs",
                        p -> new StairBlock(IRONWOOD_PLANKS.get().defaultBlockState(), p
                                        .strength(2.0F, 3.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()
                                        .mapColor(MapColor.RAW_IRON)));

        public static final DeferredHolder<Block, Block> POTTED_IRONWOOD_SAPLING = zBlock.registerBlock(
                        "potted_ironwood_sapling",
                        p -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, IRONWOOD_SAPLING, p
                                        .instabreak()
                                        .noOcclusion()
                                        .pushReaction(PushReaction.DESTROY)));

        public static final DeferredHolder<Block, Block> IRONWOOD_SLAB = Material.registerItemBlock("ironwood_slab",
                        p -> new SlabBlock(p
                                        .strength(2.0F, 3.0F)
                                        .sound(SoundType.WOOD)
                                        .ignitedByLava()
                                        .mapColor(MapColor.RAW_IRON)));

        // TODO IMP : FLAX

        // renderer
        public static final DeferredHolder<Block, Block> RENDER_HALF_AXLE = Material.renderBlock("render_half_axle");
        public static final DeferredHolder<Block, Block> RENDER_FAN_BLADE = Material.renderBlock("render_fan_blade");
        public static final DeferredHolder<Block, Block> RENDER_MILLSTONE = Material.renderBlock("render_millstone");
        public static final DeferredHolder<Block, Block> RENDER_CENTRIFUGE = Material.renderBlock("render_centrifuge_blades");
        public static final DeferredHolder<Block, Block> RENDER_PRESS_HEAD = Material.renderBlock("render_press_head");
        public static final DeferredHolder<Block, Block> RENDER_WINDMILL = Material.renderBlock("render_windmill");

        

}
