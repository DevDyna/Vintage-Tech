package com.synergy.vintagetech.init.builder.dynamo;

import java.util.HashMap;
import java.util.Map;

import com.devdyna.cakesticklib.api.aspect.logic.EnergyProvider;
import com.devdyna.cakesticklib.setup.registry.LibHandlers;
import com.synergy.vintagetech.api.blockfactory.transmission.TransmissionBE;
import com.synergy.vintagetech.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class DynamoBE extends TransmissionBE implements EnergyProvider {

    public static final Map<Direction, BlockCapabilityCache<EnergyHandler, Direction>> cache = new HashMap<>();

    public DynamoBE(BlockPos p, BlockState s) {
        super(zBlockEntities.DYNAMO.get(), p, s);
    }

    @Override
    public void tickServer() {
        

        if (getEnergyStorage() == null)
            return;

        if (canReceive() && getBlockState().getValue(DynamoBlock.ENABLED)) {

            try (var tx = Transaction.openRoot()) {
                getEnergyStorage().insert(getFERate(), tx);
                tx.commit();
            }

            providePowerAdjacent(level, getBlockPos(), cache, getMaxTransferEnergy());
        }

       

    }

    // TODO API : optional
    @Override
    public ContainerData getContainerData() {
        return new SimpleContainerData(1);
    }

    @Override
    public EnergyHandler getEnergyStorage() {
        return getData(LibHandlers.ENERGY_STORAGE);
    }

    @Override
    public int getMaxEnergy() {
        return 1_000_000;
    }

    @Override
    public int getFERate() {
        return 1;
    }

}