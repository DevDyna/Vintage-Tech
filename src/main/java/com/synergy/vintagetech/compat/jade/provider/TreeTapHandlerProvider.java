package com.synergy.vintagetech.compat.jade.provider;

import static com.synergy.vintagetech.Main.MODULE_ID;

import javax.annotation.Nullable;

import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.api.factories.handlers.TreeTapHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

public class TreeTapHandlerProvider {

  public static final Identifier UUID = x.rl(MODULE_ID, "tree_tap");

  public static class ServerProvider implements StreamServerDataProvider<BlockAccessor, Boolean> {
    public static final ServerProvider INSTANCE = new ServerProvider();

    @Override
    public @Nullable Boolean streamData(BlockAccessor a) {
      return a.getBlockEntity() instanceof TreeTapHandler;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, Boolean> streamCodec() {
      return ByteBufCodecs.BOOL.cast();
    }

    @Override
    public Identifier getUid() {
      return TreeTapHandlerProvider.UUID;
    }
  }

  public static class ClientProvider implements IBlockComponentProvider {
    public static final ClientProvider INSTANCE = new ClientProvider();

    @Override
    public void appendTooltip(ITooltip t, BlockAccessor a, IPluginConfig c) {

      var v = ServerProvider.INSTANCE.decodeFromData(a);

      if (v.isEmpty())
        return;

      if (v.get())
        t.add(Component
            .translatable(MODULE_ID +
                (Minecraft.getInstance().hasControlDown()
                    ? ".jade.tree_tap"
                    : ".jade.hold.ctrl")));

    }

    @Override
    public Identifier getUid() {
      return TreeTapHandlerProvider.UUID;
    }

  }
}
