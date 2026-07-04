package com.synergy.vintagetech.client.particles.fan;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;

public class AirFlowParticle extends SimpleAnimatedParticle {

    private final Vec3 dir;

    public AirFlowParticle(ClientLevel l,
            double x, double y, double z,
            SpriteSet sprites, Vec3 vec3, RandomSource r) {

        super(l, x, y, z, sprites, 0.0F);

        this.dir = vec3.normalize();

        this.friction = 0.92F;
        this.lifetime = 25 + r.nextInt(15);

        this.quadSize = 0.15F;
        this.setAlpha(0.5F);

        this.xd = dir.x * 0.12;
        this.yd = dir.y * 0.12;
        this.zd = dir.z * 0.12;
    }

    @Override
    public void tick() {
        super.tick();

        this.xd += dir.x * 0.015;
        this.yd += dir.y * 0.015;
        this.zd += dir.z * 0.015;

        this.xd *= 0.96;
        this.yd *= 0.96;
        this.zd *= 0.96;
    }
}
