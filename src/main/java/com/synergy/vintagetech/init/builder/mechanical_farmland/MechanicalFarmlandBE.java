package com.synergy.vintagetech.init.builder.mechanical_farmland;

import com.devdyna.cakesticklib.api.ItemLogisticUtils;
import com.devdyna.cakesticklib.api.RandomUtil;
import com.devdyna.cakesticklib.api.aspect.logic.SimpleFluidStorage;
import com.devdyna.cakesticklib.api.factories.plants.VanillaPlants;
import com.devdyna.cakesticklib.setup.registry.LibHandlers;
import com.synergy.vintagetech.api.blockfactory.transmission.TransmissionBE;
import com.synergy.vintagetech.init.builder.fan.FanBlock;
import com.synergy.vintagetech.init.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class MechanicalFarmlandBE extends TransmissionBE implements SimpleFluidStorage {

    public static final int FLUID_TANK = 0;

    public MechanicalFarmlandBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.MECHANICAL_FARMLAND.get(), pos, state);
    }

    @Override
    public FluidStacksResourceHandler getFluidStorage() {
        return getData(LibHandlers.FLUID_STORAGE);
    }

    @Override
    public int getTankCapacity() {
        return 1_000;
    }

    @Override
    public int getTanks() {
        return 1;
    }

    @Override
    public void tickServer() {

        var state = getBlockState();
        var pos = getBlockPos();
        var above = pos.above();
        var crop = level.getBlockState(above);

        if (!getBlockState().getValue(FanBlock.ENABLED))
            return;

        if (level.getGameTime() % 20 != 0)
            return;

        if (RandomUtil.chance(level, 50))
            return;

        if (getFluidStorage() == null)
            return;

        if (getAsStack(FLUID_TANK).isEmpty() && getBlockState().getValue(FanBlock.ENABLED)) {

            if (state.getValue(MechanicalFarmlandBlock.MOISTURE) > 0)
                level.setBlock(pos,
                        state.setValue(MechanicalFarmlandBlock.MOISTURE,
                                state.getValue(MechanicalFarmlandBlock.MOISTURE) - 1),
                        2);

            return;
        }

        try (var tx = Transaction.openRoot()) {
            getFluidStorage().extract(FLUID_TANK, FluidResource.of(getAsStack(FLUID_TANK)), 10, tx);
            tx.commit();
        }

        if (state.getValue(MechanicalFarmlandBlock.MOISTURE) < 7)
            level.setBlock(pos,
                    state.setValue(MechanicalFarmlandBlock.MOISTURE,
                            state.getValue(MechanicalFarmlandBlock.MOISTURE) + 1),
                    2);

        if (state.getValue(MechanicalFarmlandBlock.MOISTURE) >= 3) {
            var items = VanillaPlants.checkReplant(level, above, null, null);
            if (items != null)
                items.forEach(i -> ItemLogisticUtils.createLazyItemEntity(i, level,
                        above, 1200, true));
        }

        if (state.getValue(MechanicalFarmlandBlock.MOISTURE) >= 7)
            if (crop.getBlock() instanceof BonemealableBlock meal)
                if (crop.isRandomlyTicking())
                    if (meal.isValidBonemealTarget(level, above, crop))
                        meal.performBonemeal((ServerLevel) level, level.getRandom(), above, crop);

    }

}
