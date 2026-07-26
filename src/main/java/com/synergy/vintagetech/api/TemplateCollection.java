package com.synergy.vintagetech.api;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.Optional;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;

public class TemplateCollection {
    public static final TextureSlot SAW = TextureSlot.create("saw");

    public static final ModelTemplate SAW_TEMPLATE = new ModelTemplate(
            Optional.of(x.rl(MODULE_ID, "block/template/saw")),
            Optional.empty(),
            SAW);

    public static final ModelTemplate BEAM_TEMPLATE = new ModelTemplate(
            Optional.of(x.rl(MODULE_ID, "block/template/beam")),
            Optional.empty(),
            TextureSlot.TOP, TextureSlot.SIDE);
}
