package com.synergy.vintagetech.init.types;

import static com.synergy.vintagetech.Main.MODULE_ID;

import java.util.function.Function;

import com.synergy.vintagetech.init.builder.GearShiftBlock;
import com.synergy.vintagetech.init.builder.engine.EngineBlock;
import com.synergy.vintagetech.init.builder.fan.FanBlock;
import com.synergy.vintagetech.init.builder.saw.SawBlock;
import com.synergy.vintagetech.init.builder.transmission.AxleBlock;
import com.synergy.vintagetech.init.builder.transmission.JunctionBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class zBlocks {

    public static void register(IEventBus bus) {
        zBlock.register(bus);
        zBlockItem.register(bus);
        zRender.register(bus);
    }

    public static final DeferredRegister.Blocks zRender = DeferredRegister.createBlocks(MODULE_ID);
    public static final DeferredRegister.Blocks zBlock = DeferredRegister.createBlocks(MODULE_ID);
    public static final DeferredRegister.Blocks zBlockItem = DeferredRegister.createBlocks(MODULE_ID);

    public static final DeferredHolder<Block, Block> ENGINE = registerItemBlock("engine", p -> new EngineBlock(p));

    public static final DeferredHolder<Block, Block> AXLE = registerItemBlock("axle", p -> new AxleBlock(p));
    public static final DeferredHolder<Block, Block> JUNCTION = registerItemBlock("junction", p -> new JunctionBlock(p));
    public static final DeferredHolder<Block, Block> SAW = registerItemBlock("saw", p -> new SawBlock(p));
    public static final DeferredHolder<Block, Block> FAN = registerItemBlock("fan", p -> new FanBlock(p));
   
    public static final DeferredHolder<Block, Block> GEARSHIFT = registerItemBlock("gearshift", p -> new GearShiftBlock(p));

    public static final DeferredHolder<Block, Block> RENDER_HALF_AXLE = zRender.registerSimpleBlock("render_half_axle",p->p.noLootTable());
    public static final DeferredHolder<Block, Block> RENDER_FAN_BLADE = zRender.registerSimpleBlock("render_fan_blade",p->p.noLootTable());

    public static DeferredHolder<Block, Block> registerItemBlock(String blockname,
            Function<BlockBehaviour.Properties, ? extends Block> sup) {
        DeferredHolder<Block, Block> block = zBlockItem.registerBlock(blockname, sup);
        zItems.zBlockItem.registerSimpleBlockItem(block);
        return block;
    }

}
