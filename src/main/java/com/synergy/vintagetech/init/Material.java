package com.synergy.vintagetech.init;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.function.BiFunction;
import java.util.function.Function;

import com.devdyna.cakesticklib.api.utils.x;
import com.synergy.vintagetech.init.types.*;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;

public class Material {
        public static void register(IEventBus bus) {
                zItems.register(bus);
                zBlocks.register(bus);
                zBlockEntities.register(bus);
                zRecipeTypes.register(bus);
                zTags.register(bus);
                zParticles.register(bus);
                zFluids.register(bus);
                zCreativeTab.register(bus);
                zWorldGenFeatures.register(bus);
        }

        /**
         * Create a simple blockitem
         */
        public static DeferredHolder<Block, Block> registerItemBlock(String id,
                        Function<Properties, ? extends Block> p) {
                DeferredHolder<Block, Block> block = zBlocks.zBlockItem.registerBlock(id, p);
                zItems.zBlockItem.registerSimpleBlockItem(block);
                return block;
        }

        /**
         * Create a blockitem from {@code ResourceKey<Block>}
         * <br/>
         * <br/>
         * Useful when you need to use things like {@code Properties.ofFullCopy(<?>)}
         */
        public static DeferredHolder<Block, Block> registerItemBlock(String id,
                        BiFunction<Properties, ResourceKey<Block>, ? extends Block> p) {
                return registerItemBlock(id,
                                pr -> p.apply(pr, ResourceKey.create(Registries.BLOCK, x.rl(MODULE_ID, id))));
        }

        /**
         * Create a Block without an item related<br/>
         * <br/>
         * Mainly used to render debug blocks that cannot be broken or used entirely
         */
        public static DeferredHolder<Block, Block> renderBlock(String id) {
                return zBlocks.zRender.registerSimpleBlock(id, p -> p.noLootTable());
        }


}
