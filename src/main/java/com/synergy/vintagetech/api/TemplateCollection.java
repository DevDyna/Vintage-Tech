package com.synergy.vintagetech.api;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.Optional;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;

public class TemplateCollection {
    public static final TextureSlot SAW = TextureSlot.create("saw");
    public static final TextureSlot FRAME = TextureSlot.create("frame");

    public static final ModelTemplate SAW_TEMPLATE = new ModelTemplate(
            Optional.of(x.rl(MODULE_ID, "block/template/saw")),
            Optional.empty(),
            SAW);

    public static final ModelTemplate BEAM_TEMPLATE = new ModelTemplate(
            Optional.of(x.rl(MODULE_ID, "block/template/beam")),
            Optional.empty(),
            TextureSlot.TOP, TextureSlot.SIDE);

    public static final ModelTemplate MODIFIER_TEMPLATE = new ModelTemplate(
            Optional.of(x.rl(MODULE_ID, "block/template/modifier")),
            Optional.empty(),
            FRAME);

    public class CHEESE {

        public static final ModelTemplate PIECE_0 = new ModelTemplate(
                Optional.of(x.rl(MODULE_ID, "block/template/cheese/0")),
                Optional.empty(),
                TextureSlot.TOP, TextureSlot.SIDE, TextureSlot.BOTTOM, TextureSlot.INSIDE);

        public static final ModelTemplate PIECE_1 = new ModelTemplate(
                Optional.of(x.rl(MODULE_ID, "block/template/cheese/1")),
                Optional.empty(),
                TextureSlot.TOP, TextureSlot.SIDE, TextureSlot.BOTTOM, TextureSlot.INSIDE);

        public static final ModelTemplate PIECE_2 = new ModelTemplate(
                Optional.of(x.rl(MODULE_ID, "block/template/cheese/2")),
                Optional.empty(),
                TextureSlot.TOP, TextureSlot.SIDE, TextureSlot.BOTTOM, TextureSlot.INSIDE);

        public static final ModelTemplate PIECE_3 = new ModelTemplate(
                Optional.of(x.rl(MODULE_ID, "block/template/cheese/3")),
                Optional.empty(),
                TextureSlot.TOP, TextureSlot.SIDE, TextureSlot.BOTTOM, TextureSlot.INSIDE);

        public static final ModelTemplate[] ALL = new ModelTemplate[] { PIECE_0, PIECE_1, PIECE_2, PIECE_3 };

    }
}
