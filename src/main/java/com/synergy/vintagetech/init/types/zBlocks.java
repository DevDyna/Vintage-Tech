package com.synergy.vintagetech.init.types;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.function.Function;

import com.synergy.vintagetech.init.builder.GearShiftBlock;
import com.synergy.vintagetech.init.builder.basket.BasketBlock;
import com.synergy.vintagetech.init.builder.creative_engine.CreativeEngineBlock;
import com.synergy.vintagetech.init.builder.crushing_tub.CrushingTubBlock;
import com.synergy.vintagetech.init.builder.drying_rack.DryingRackBlock;
import com.synergy.vintagetech.init.builder.evaporation_basin.EvaporationBasinBlock;
import com.synergy.vintagetech.init.builder.fan.FanBlock;
import com.synergy.vintagetech.init.builder.millstone.MillstoneBlock;
import com.synergy.vintagetech.init.builder.plants.Aloe;
import com.synergy.vintagetech.init.builder.plants.BlueBerry;
import com.synergy.vintagetech.init.builder.plants.CaveWheat;
import com.synergy.vintagetech.init.builder.plants.Hemp;
import com.synergy.vintagetech.init.builder.plants.Lavender;
import com.synergy.vintagetech.init.builder.plants.SoyBeans;
import com.synergy.vintagetech.init.builder.saw.SawBlock;
import com.synergy.vintagetech.init.builder.soil.SoilBlock;
import com.synergy.vintagetech.init.builder.steam_engine.SteamEngineBlock;
import com.synergy.vintagetech.init.builder.transmission.AxleBlock;
import com.synergy.vintagetech.init.builder.transmission.JunctionBlock;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
        public static final DeferredHolder<Block, Block> AXLE = registerItemBlock("axle", p -> new AxleBlock(p));
        public static final DeferredHolder<Block, Block> JUNCTION = registerItemBlock("junction",
                        p -> new JunctionBlock(p));
        public static final DeferredHolder<Block, Block> GEARSHIFT = registerItemBlock("gearshift",
                        p -> new GearShiftBlock(p));

        // machines
        public static final DeferredHolder<Block, Block> SAW = registerItemBlock("saw", p -> new SawBlock(p));
        public static final DeferredHolder<Block, Block> FAN = registerItemBlock("fan", p -> new FanBlock(p));
        public static final DeferredHolder<Block, Block> MILLSTONE = registerItemBlock("millstone",
                        p -> new MillstoneBlock(p));

        // utility
        public static final DeferredHolder<Block, Block> BASKET = registerItemBlock("basket", p -> new BasketBlock(p));

        public static final DeferredHolder<Block, Block> CRUSHING_TUB = registerItemBlock("crushing_tub",
                        p -> new CrushingTubBlock(p));
        public static final DeferredHolder<Block, Block> DRYING_RACK = registerItemBlock("drying_rack",
                        p -> new DryingRackBlock(p));
        public static final DeferredHolder<Block, Block> EVAPORATION_BASIN = registerItemBlock("evaporation_basin",
                        p -> new EvaporationBasinBlock(p));

        // TODO IMP : BLOCKS
        public static final DeferredHolder<Block, Block> SOIL = registerItemBlock("soil", p -> new SoilBlock(p));
        public static final DeferredHolder<Block, Block> CHEESE = registerItemBlock("cheese", p -> new SoilBlock(p));
        public static final DeferredHolder<Block, Block> CENTRIFUGE = registerItemBlock("centrifuge",
                        p -> new SoilBlock(p));
        public static final DeferredHolder<Block, Block> CRUCIBLE = registerItemBlock("crucible",
                        p -> new SoilBlock(p));
        public static final DeferredHolder<Block, Block> MIXING_BARREL = registerItemBlock("mixing_barrel",
                        p -> new SoilBlock(p));
        public static final DeferredHolder<Block, Block> HYDRAULIC_PRESS = registerItemBlock("hydraulic_press",
                        p -> new SoilBlock(p));
        public static final DeferredHolder<Block, Block> TURNTABLE = registerItemBlock("turntable",
                        p -> new SoilBlock(p));

        // renderer
        public static final DeferredHolder<Block, Block> RENDER_HALF_AXLE = renderBlock("render_half_axle");
        public static final DeferredHolder<Block, Block> RENDER_FAN_BLADE = renderBlock("render_fan_blade");
        public static final DeferredHolder<Block, Block> RENDER_MILLSTONE = renderBlock("render_millstone");
        public static final DeferredHolder<Block, Block> RENDER_CENTRIFUGE = renderBlock("render_centrifuge");
        public static final DeferredHolder<Block, Block> RENDER_PRESS_HEAD = renderBlock("render_press_head");

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
