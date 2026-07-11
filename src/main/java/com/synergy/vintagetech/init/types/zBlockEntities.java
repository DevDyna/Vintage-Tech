package com.synergy.vintagetech.init.types;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.RegistryUtils;
import com.synergy.vintagetech.api.blockfactory.engine.BaseEngineBE;
import com.synergy.vintagetech.api.blockfactory.transmission.TransmissionBE;
import com.synergy.vintagetech.init.builder.basket.BasketBE;
import com.synergy.vintagetech.init.builder.centrifuge.CentrifugeBE;
import com.synergy.vintagetech.init.builder.crushing_tub.CrushingTubBE;
import com.synergy.vintagetech.init.builder.drying_rack.DryingRackBE;
import com.synergy.vintagetech.init.builder.dynamo.DynamoBE;
import com.synergy.vintagetech.init.builder.electric_motor.ElectricMotorBE;
import com.synergy.vintagetech.init.builder.evaporation_basin.EvaporationBasinBE;
import com.synergy.vintagetech.init.builder.fan.FanBE;
import com.synergy.vintagetech.init.builder.mechanical_farmland.MechanicalFarmlandBE;
import com.synergy.vintagetech.init.builder.millstone.MillstoneBE;
import com.synergy.vintagetech.init.builder.saw.SawBE;
import com.synergy.vintagetech.init.builder.steam_engine.SteamEngineBE;
import com.synergy.vintagetech.init.builder.treetap.TreeTapBE;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zBlockEntities {

        public static void register(IEventBus bus) {
                zTiles.register(bus);
        }

        public static final DeferredRegister<BlockEntityType<?>> zTiles = DeferredRegister
                        .create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MODULE_ID);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SteamEngineBE>> STEAM_ENGINE = RegistryUtils
                        .createBlockEntity("steam_engine", zTiles, SteamEngineBE::new, zBlocks.STEAM_ENGINE);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BaseEngineBE>> CREATIVE_ENGINE = RegistryUtils
                        .createBlockEntity("creative_engine", zTiles, BaseEngineBE::new, zBlocks.CREATIVE_ENGINE);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SawBE>> SAW = RegistryUtils
                        .createBlockEntity("saw", zTiles, SawBE::new, zBlocks.SAW);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FanBE>> FAN = RegistryUtils
                        .createBlockEntity("fan", zTiles, FanBE::new, zBlocks.FAN);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TransmissionBE>> TRANSMISSION = RegistryUtils
                        .createBlockEntity("transmission", zTiles, TransmissionBE::new, zBlocks.AXLE, zBlocks.JUNCTION,
                                        zBlocks.GEARSHIFT);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BasketBE>> BASKET = RegistryUtils
                        .createBlockEntity("basket", zTiles, BasketBE::new, zBlocks.BASKET);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CrushingTubBE>> CRUSHING_TUB = RegistryUtils
                        .createBlockEntity("crushing_tub", zTiles, CrushingTubBE::new, zBlocks.CRUSHING_TUB);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DryingRackBE>> DRYING_RACK = RegistryUtils
                        .createBlockEntity("drying_rack", zTiles, DryingRackBE::new, zBlocks.DRYING_RACK);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EvaporationBasinBE>> EVAPORATION_BASIN = RegistryUtils
                        .createBlockEntity("evaporation_basin", zTiles, EvaporationBasinBE::new,
                                        zBlocks.EVAPORATION_BASIN);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MillstoneBE>> MILLSTONE = RegistryUtils
                        .createBlockEntity("millstone", zTiles, MillstoneBE::new,
                                        zBlocks.MILLSTONE);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MechanicalFarmlandBE>> MECHANICAL_FARMLAND = RegistryUtils
                        .createBlockEntity("mechanical_farmland", zTiles, MechanicalFarmlandBE::new,
                                        zBlocks.MECHANICAL_FARMLAND);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ElectricMotorBE>> ELECTRIC_MOTOR = RegistryUtils
                        .createBlockEntity("electric_motor", zTiles, ElectricMotorBE::new,
                                        zBlocks.ELECTRIC_MOTOR);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DynamoBE>> DYNAMO = RegistryUtils
                        .createBlockEntity("dynamo", zTiles, DynamoBE::new,
                                        zBlocks.DYNAMO);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CentrifugeBE>> CENTRIFUGE = RegistryUtils
                        .createBlockEntity("centrifuge", zTiles, CentrifugeBE::new, zBlocks.CENTRIFUGE);

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TreeTapBE>> TREE_TAP = RegistryUtils
                        .createBlockEntity("tree_tap", zTiles, TreeTapBE::new, zBlocks.TREE_TAP);

}
