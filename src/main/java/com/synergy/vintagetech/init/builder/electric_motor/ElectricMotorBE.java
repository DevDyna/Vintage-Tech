package com.synergy.vintagetech.init.builder.electric_motor;

import com.devdyna.cakesticklib.api.aspect.logic.EnergyBlock;
import com.devdyna.cakesticklib.setup.registry.LibHandlers;
import com.synergy.vintagetech.api.blockfactory.engine.BaseEngineBE;
import com.synergy.vintagetech.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;

public class ElectricMotorBE extends BaseEngineBE implements EnergyBlock {

    public ElectricMotorBE(BlockPos p, BlockState s) {
        super(zBlockEntities.ELECTRIC_MOTOR.get(), p, s);
    }

    @Override
    public EnergyHandler getEnergyStorage() {
        return getData(LibHandlers.ENERGY_STORAGE);
    }

    @Override
    public int getMaxEnergy() {
        return 1_000_000;
    }

}