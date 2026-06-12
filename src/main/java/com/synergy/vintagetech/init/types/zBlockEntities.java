package com.synergy.vintagetech.init.types;

import static com.synergy.vintagetech.Main.MODULE_ID;

import com.devdyna.cakesticklib.api.RegistryUtils;
import com.synergy.vintagetech.api.blockfactory.transmission.TransmissionBE;
import com.synergy.vintagetech.init.builder.basket.BasketBE;
import com.synergy.vintagetech.init.builder.crushing_tub.CrushingTubBE;
import com.synergy.vintagetech.init.builder.engine.EngineBE;
import com.synergy.vintagetech.init.builder.evaporation_basin.EvaporationBasinBE;
import com.synergy.vintagetech.init.builder.fan.FanBE;
import com.synergy.vintagetech.init.builder.saw.SawBE;

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

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EngineBE>> ENGINE = RegistryUtils
                        .createBlockEntity("engine", zTiles, EngineBE::new, zBlocks.ENGINE);

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
        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EvaporationBasinBE>> EVAPORATION_BASIN = RegistryUtils
                        .createBlockEntity("evaporation_basin", zTiles, EvaporationBasinBE::new, zBlocks.EVAPORATION_BASIN);

}
