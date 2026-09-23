package com.synergy.vintagetech.init.types;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.synergy.vintagetech.init.Material;
import com.synergy.vintagetech.init.builder.RopeBlock;
import com.synergy.vintagetech.init.builder.StickyFarmlandBlock;
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
import com.synergy.vintagetech.init.builder.plants.*;
import com.synergy.vintagetech.init.builder.saw.SawBlock;
import com.synergy.vintagetech.init.builder.transmission.*;
import com.synergy.vintagetech.init.builder.treetap.TreeTapBlock;
import com.synergy.vintagetech.init.builder.windmill.WindmillBlock;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
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
                zTreeBlock.register(bus);
        }

        public static final DeferredRegister.Blocks zRender = DeferredRegister.createBlocks(MODULE_ID);
        public static final DeferredRegister.Blocks zBlock = DeferredRegister.createBlocks(MODULE_ID);
        public static final DeferredRegister.Blocks zBlockFluids = DeferredRegister.createBlocks(MODULE_ID);
        public static final DeferredRegister.Blocks zBlockItem = DeferredRegister.createBlocks(MODULE_ID);
        public static final DeferredRegister.Blocks zTreeBlock = DeferredRegister.createBlocks(MODULE_ID);

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

        public static final DeferredHolder<Block, Block> EVAPORATION_BASIN = Material.registerItemBlock(
                        "evaporation_basin",
                        p -> new EvaporationBasinBlock(p
                                        .mapColor(MapColor.COLOR_ORANGE)
                                        .strength(1F, 2.25F)
                                        .sound(SoundType.STONE)
                                        .mapColor(MapColor.TERRACOTTA_ORANGE)));

        public static final DeferredHolder<Block, Block> MECHANICAL_FARMLAND = Material.registerItemBlock(
                        "mechanical_farmland",
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

       

        public static final DeferredHolder<Block, Block> STICKY_FARMLAND = Material.registerItemBlock("sticky_farmland",
                        p -> new StickyFarmlandBlock(p
                                        .mapColor(MapColor.DIRT)
                                        .randomTicks()
                                        .strength(0.6F)
                                        .sound(SoundType.GRAVEL)
                                        .isViewBlocking((a, b, c) -> true)
                                        .isSuffocating((a, b, c) -> true)));

        // renderer
        public static final DeferredHolder<Block, Block> RENDER_HALF_AXLE = Material.renderBlock("render_half_axle");
        public static final DeferredHolder<Block, Block> RENDER_FAN_BLADE = Material.renderBlock("render_fan_blade");
        public static final DeferredHolder<Block, Block> RENDER_MILLSTONE = Material.renderBlock("render_millstone");
        public static final DeferredHolder<Block, Block> RENDER_CENTRIFUGE = Material
                        .renderBlock("render_centrifuge_blades");
        public static final DeferredHolder<Block, Block> RENDER_PRESS_HEAD = Material.renderBlock("render_press_head");
        public static final DeferredHolder<Block, Block> RENDER_WINDMILL = Material.renderBlock("render_windmill");

}
