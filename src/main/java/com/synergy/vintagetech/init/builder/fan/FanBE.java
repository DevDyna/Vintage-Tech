package com.synergy.vintagetech.init.builder.fan;

import com.devdyna.cakesticklib.api.RandomUtil;
import com.synergy.vintagetech.api.blockfactory.transmission.TransmissionBE;
import com.synergy.vintagetech.init.types.zBlockEntities;
import com.synergy.vintagetech.init.types.zParticles;
import com.synergy.vintagetech.init.types.zTags;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
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

            for (Entity entity : level.getEntitiesOfClass(Entity.class, new AABB(offset))) {
                if (entity instanceof Player player
                        && ((player.isCreative() && !player.onGround()) || player.isSpectator()))
                    return;

                if (entity.is(zTags.Entities.FAN_DENY_MOTION))
                    return;

                entity.setDeltaMovement(entity.getDeltaMovement().add(new Vec3(
                        facing.getStepX() * 0.05D * dirMul,
                        facing.getStepY() * 0.05D * dirMul,
                        facing.getStepZ() * 0.05D * dirMul)));
            }

            if (level.getRandom().nextFloat() < ((inverted ? (i / 5f) : (1f - i / 5f)) * 0.4f)) {
                level.addParticle(
                        zParticles.FAN_AIR_FLOW.get(),
                        particlePos.getX() + RandomUtil.between(level, 0.1, 0.9),
                        particlePos.getY() + RandomUtil.between(level, 0.1, 0.9),
                        particlePos.getZ() + RandomUtil.between(level, 0.1, 0.9),
                        baseDir.x,
                        baseDir.y,
                        baseDir.z);
            }

        }

    }

   

    

}
