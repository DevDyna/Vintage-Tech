package com.synergy.vintagetech.api.factories;

import java.util.ArrayList;
import java.util.List;

import com.devdyna.cakesticklib.api.utils.ColorUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.item.Item;

//TODO API : move to api?
public class EffectConsumableItem extends Item {

    private List<MobEffectInstance> effects;

    public EffectConsumableItem(Properties p, List<MobEffectInstance> effects) {
        super(p);
        this.effects = effects;
    }

    public List<Component> getEffectToolTip() {

        var list = new ArrayList<Component>();

        for (var instance : effects) {

            var effect = instance.getEffect().value();

            var result = Component.empty()
                    .append(effect.getDisplayName());

            if (instance.getAmplifier() > 0)
                result.append(" ")
                        .append(Component.translatable(
                                "potion.potency." + instance.getAmplifier()));

            result.append(" (")
                    .append(MobEffectUtil.formatDuration(instance, 1.0F,
                            Minecraft.getInstance().level.tickRateManager().tickrate()))
                    .append(")");

            result.withStyle(style -> style.withColor(switch (effect.getCategory()) {
                case MobEffectCategory.BENEFICIAL -> ColorUtils.rgb(85, 85, 255);
                case MobEffectCategory.HARMFUL -> ColorUtils.rgb(255, 85, 85);
                case MobEffectCategory.NEUTRAL -> ColorUtils.rgb(170, 0, 170);
            }));

            list.add(result);
        }

        return list;

    }

}
