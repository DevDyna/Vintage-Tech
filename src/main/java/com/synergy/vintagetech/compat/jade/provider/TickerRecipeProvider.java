package com.synergy.vintagetech.compat.jade.provider;

import static com.synergy.vintagetech.Main.MODULE_ID;

import javax.annotation.Nullable;

import com.devdyna.cakesticklib.api.datagen.LangUtils.TipColors;
import com.devdyna.cakesticklib.api.utils.StringUtil;
import com.devdyna.cakesticklib.api.utils.TimeUtil;
import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.api.TickerRecipe;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

public class TickerRecipeProvider {

  // TODO API : ticker CODEC & STREAMCODEC

  public record RecordProvider(int timer, float multiplier, boolean hide) {
  }

  public static final Identifier UUID = x.rl(MODULE_ID, "ticker_recipe");

  public static class ServerProvider implements StreamServerDataProvider<BlockAccessor, RecordProvider> {
    public static final ServerProvider INSTANCE = new ServerProvider();

    @Override
    public @Nullable RecordProvider streamData(BlockAccessor a) {

      if (a.getBlockEntity() instanceof TickerRecipe t)
        return new RecordProvider(
            (t.getTicker() == null ? 0 : t.getTicker().diff()), t.getMultiplier(),
            t.hideMultiplier());

      return null;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, RecordProvider> streamCodec() {
      return StreamCodec.composite(
          ByteBufCodecs.INT, RecordProvider::timer,
          ByteBufCodecs.FLOAT, RecordProvider::multiplier,
          ByteBufCodecs.BOOL, RecordProvider::hide,
          RecordProvider::new);
    }

    @Override
    public Identifier getUid() {
      return TickerRecipeProvider.UUID;
    }
  }

  public static class ClientProvider implements IBlockComponentProvider {
    public static final ClientProvider INSTANCE = new ClientProvider();

    @Override
    public void appendTooltip(ITooltip t, BlockAccessor a, IPluginConfig c) {

      var v = ServerProvider.INSTANCE.decodeFromData(a);

      if (v.isEmpty())
        return;

      var r = v.get();

      t.add((r.hide()
          ? Component.empty()
          : Component.translatable(MODULE_ID + ".jade.ticker_recipe.multiplier",
              (r.multiplier() >= 1.0f ? TipColors.GREEN : TipColors.RED)
                  + ((r.multiplier() > 0 && r.multiplier() < 0.1)
                      ? "⅟" + ((int) (1f / r.multiplier()))
                      : StringUtil.cut(r.multiplier()))))
          .append(
              Component.translatable(MODULE_ID + ".jade.ticker_recipe.time",
                  TimeUtil.getTimeValue(r.timer(), 0, false))));

    }

    @Override
    public Identifier getUid() {
      return TickerRecipeProvider.UUID;
    }

  }
}
