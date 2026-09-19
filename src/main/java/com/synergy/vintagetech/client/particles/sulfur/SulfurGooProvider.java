package com.synergy.vintagetech.client.particles.sulfur;

import com.devdyna.cakesticklib.api.utils.ColorUtils;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.GlowParticle;
import net.minecraft.client.particle.GlowParticle.WaxOnProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class SulfurGooProvider extends WaxOnProvider {

    private final SpriteSet sprite;

    public SulfurGooProvider(final SpriteSet sprite) {
        super(sprite);
        this.sprite = sprite;
    }

    @Override
    public Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z,
            double xAux, double yAux, double zAux, RandomSource random) {

        var particle = new GlowParticle(level, x, y, z, 0.0, 0.0, 0.0, this.sprite);

        var color = ColorUtils.YELLOW.SULFUR;

        particle.setColor(
                color.getRed() / 255.0F,
                color.getGreen() / 255.0F,
                color.getBlue() / 255.0F);

        particle.setParticleSpeed(xAux * 0.01 / 2.0, yAux * 0.01, zAux * 0.01 / 2.0);

        particle.setLifetime(random.nextInt(30) + 10);
        return particle;
    }

}
