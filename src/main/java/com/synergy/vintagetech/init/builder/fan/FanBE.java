package com.synergy.vintagetech.init.builder.fan;

import com.synergy.vintagetech.api.blockfactory.transmission.TransmissionBE;
import com.synergy.vintagetech.init.types.zBlockEntities;
import com.synergy.vintagetech.init.types.zParticles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class FanBE extends TransmissionBE {

    public FanBE(BlockPos pos, BlockState state) {
        super(zBlockEntities.FAN.get(), pos, state);
    }

    @Override
    public void tickBoth() {

        if (!getBlockState().getValue(FanBlock.ENABLED))
            return;

        var facing = getBlockState().getValue(FanBlock.FACING);

        var inverted = getBlockState().getValue(FanBlock.INVERTED);

        int dirMul = inverted ? -1 : 1;

        Vec3 baseDir = new Vec3(
                facing.getStepX(),
                facing.getStepY(),
                facing.getStepZ()).scale(dirMul);

        for (int i = 0; i <= 5; i++) {

            var offset = getBlockPos().relative(facing, i);

            var particlePos = inverted ? offset : getBlockPos();

            if (level.getBlockState(offset).isSolidRender())
                break;

            for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, new AABB(offset)))
                entity.setDeltaMovement(entity.getDeltaMovement().add(new Vec3(
                        facing.getStepX() * 0.05D * dirMul,
                        facing.getStepY() * 0.05D * dirMul,
                        facing.getStepZ() * 0.05D * dirMul)));

            if (level.getRandom().nextFloat() < ((inverted ? (i / 5f) : (1f - i / 5f)) * 0.4f)) {
                level.addParticle(
                        zParticles.FAN_AIR_FLOW.get(),
                        particlePos.getX() + between(level, 0, 1),
                        particlePos.getY() + between(level, 0, 1),
                        particlePos.getZ() + between(level, 0, 1),
                        baseDir.x,
                        baseDir.y,
                        baseDir.z);
            }

        }

    }

    // TODO API : move to RandomUtils

    public static double between(Level l, double min, double max) {
        return min + l.getRandom().nextDouble() * (max - min);
    }

    // TODO API : COLORS create defaul colors variables

}
