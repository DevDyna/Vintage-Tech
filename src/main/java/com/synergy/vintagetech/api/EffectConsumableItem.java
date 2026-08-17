package com.synergy.vintagetech.api;

import com.devdyna.cakesticklib.api.utils.ColorUtils;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.item.Item;

//TODO API : move to api?
public class EffectConsumableItem extends Item {

    private MobEffectInstance instance;

    public EffectConsumableItem(Properties p, MobEffectInstance instance) {
        super(p);
        this.instance = instance;
    }

    public Component getEffectToolTip() {

        var effect = instance.getEffect().value();

        var result = Component.empty()
                .append(effect.getDisplayName());

        if (instance.getAmplifier() > 0) {
            result.append(" ")
                    .append(Component.translatable(
                            "potion.potency." + instance.getAmplifier()));
        }

        result.append(" (")
                .append(MobEffectUtil.formatDuration(
                        instance,
                        1.0F,
                        20.0F))
                .append(")");

        int color;

        if (effect.getCategory() == MobEffectCategory.BENEFICIAL) {
            color = ColorUtils.rgb(85, 85, 255);
        } else if (effect.getCategory() == MobEffectCategory.HARMFUL) {
            color = ColorUtils.rgb(255, 85, 85);
        } else {
            color = ColorUtils.rgb(170, 0, 170);
        }

        return result.withStyle(style -> style.withColor(color));

    }

}
