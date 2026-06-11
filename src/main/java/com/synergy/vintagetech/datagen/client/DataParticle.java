package com.synergy.vintagetech.datagen.client;

import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.init.types.zParticles;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.data.ParticleDescriptionProvider;

public class DataParticle extends ParticleDescriptionProvider {

    public DataParticle(PackOutput output) {
        super(output);
    }

    @Override
    protected void addDescriptions() {
        spriteSet(
                zParticles.FAN_AIR_FLOW.get(),
                x.mcLoc("generic_0"),
                x.mcLoc("generic_1"),
                x.mcLoc("generic_2"),
                x.mcLoc("generic_3")
        );
    }

}
