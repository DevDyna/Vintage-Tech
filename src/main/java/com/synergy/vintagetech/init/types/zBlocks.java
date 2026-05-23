package com.synergy.vintagetech.init.types;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.function.Function;

import com.synergy.vintagetech.init.builder.AxleBlock;
import com.synergy.vintagetech.init.builder.GearBoxBlock;
import com.synergy.vintagetech.init.builder.MonoDirectionalAxleBlock;
import com.synergy.vintagetech.init.builder.QuernBlock;
import com.synergy.vintagetech.init.builder.engine.EngineBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zBlocks {

    public static void register(IEventBus bus) {
        zBlock.register(bus);
        zBlockItem.register(bus);
    }

    public static final DeferredRegister.Blocks zBlock = DeferredRegister.createBlocks(MODULE_ID);
    public static final DeferredRegister.Blocks zBlockItem = DeferredRegister.createBlocks(MODULE_ID);



    public static final DeferredHolder<Block,Block> ENGINE = registerItemBlock("engine", p->new EngineBlock(p));
   
    public static final DeferredHolder<Block,Block> AXLE = registerItemBlock("axle", p->new AxleBlock(p));
    public static final DeferredHolder<Block,Block> GEARBOX = registerItemBlock("gearbox", p->new GearBoxBlock(p));
    public static final DeferredHolder<Block,Block> QUERN = registerItemBlock("quern", p->new QuernBlock(p));


    

    public static DeferredHolder<Block, Block> registerItemBlock(String blockname,
            Function<BlockBehaviour.Properties, ? extends Block> sup) {
        DeferredHolder<Block, Block> block = zBlockItem.registerBlock(blockname, sup);
        zItems.zBlockItem.registerSimpleBlockItem(block);
        return block;
    }

}
