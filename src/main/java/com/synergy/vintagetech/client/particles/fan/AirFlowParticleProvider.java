package com.synergy.vintagetech.client.particles.fan;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;

public class AirFlowParticleProvider implements ParticleProvider<SimpleParticleType> {

    private final SpriteSet sprites;

    public AirFlowParticleProvider(SpriteSet s) {
        this.sprites = s;
    }

    @Override
    public Particle createParticle(SimpleParticleType type, ClientLevel level,
            double x, double y, double z,
            double xd, double yd, double zd,
            RandomSource r) {

        return new AirFlowParticle(
                level,
                x, y, z,
                sprites,
                new Vec3(xd, yd, zd), r);
    }

}