package com.synergy.vintagetech.api;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.List;
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
                        TextureSlot.TOP,
                        TextureSlot.SIDE);

        public static final ModelTemplate MODIFIER_TEMPLATE = new ModelTemplate(
                        Optional.of(x.rl(MODULE_ID, "block/template/modifier")),
                        Optional.empty(),
                        FRAME);

        public static class CHEESE {

                public static final ModelTemplate PIECE_1 = new ModelTemplate(
                                Optional.of(x.rl(MODULE_ID, "block/template/cheese/1")),
                                Optional.empty(),
                                TextureSlot.TOP,
                                TextureSlot.SIDE,
                                TextureSlot.BOTTOM,
                                TextureSlot.INSIDE);

                public static final ModelTemplate PIECE_2 = new ModelTemplate(
                                Optional.of(x.rl(MODULE_ID, "block/template/cheese/2")),
                                Optional.empty(),
                                TextureSlot.TOP,
                                TextureSlot.SIDE,
                                TextureSlot.BOTTOM,
                                TextureSlot.INSIDE);

                public static final ModelTemplate PIECE_3 = new ModelTemplate(
                                Optional.of(x.rl(MODULE_ID, "block/template/cheese/3")),
                                Optional.empty(),
                                TextureSlot.TOP,
                                TextureSlot.SIDE,
                                TextureSlot.BOTTOM,
                                TextureSlot.INSIDE);

                public static final ModelTemplate PIECE_4 = new ModelTemplate(
                                Optional.of(x.rl(MODULE_ID, "block/template/cheese/4")),
                                Optional.empty(),
                                TextureSlot.TOP,
                                TextureSlot.SIDE,
                                TextureSlot.BOTTOM,
                                TextureSlot.INSIDE);

                public static final ModelTemplate PIECE_5 = new ModelTemplate(
                                Optional.of(x.rl(MODULE_ID, "block/template/cheese/5")),
                                Optional.empty(),
                                TextureSlot.TOP,
                                TextureSlot.SIDE,
                                TextureSlot.BOTTOM,
                                TextureSlot.INSIDE);

                public static final ModelTemplate PIECE_6 = new ModelTemplate(
                                Optional.of(x.rl(MODULE_ID, "block/template/cheese/6")),
                                Optional.empty(),
                                TextureSlot.TOP,
                                TextureSlot.SIDE,
                                TextureSlot.BOTTOM,
                                TextureSlot.INSIDE);

                public static final ModelTemplate PIECE_7 = new ModelTemplate(
                                Optional.of(x.rl(MODULE_ID, "block/template/cheese/7")),
                                Optional.empty(),
                                TextureSlot.TOP,
                                TextureSlot.SIDE,
                                TextureSlot.BOTTOM,
                                TextureSlot.INSIDE);

                public static final ModelTemplate PIECE_8 = new ModelTemplate(
                                Optional.of(x.rl(MODULE_ID, "block/template/cheese/8")),
                                Optional.empty(),
                                TextureSlot.TOP,
                                TextureSlot.SIDE,
                                TextureSlot.BOTTOM,
                                TextureSlot.INSIDE);

                public static final List<ModelTemplate> ALL = List.of(
                                PIECE_1, PIECE_2, PIECE_3, PIECE_4,
                                PIECE_5, PIECE_6, PIECE_7, PIECE_8);
        }
}
