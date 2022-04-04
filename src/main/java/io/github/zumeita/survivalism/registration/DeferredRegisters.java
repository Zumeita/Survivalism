package io.github.zumeita.survivalism.registration;

import io.github.zumeita.survivalism.Survivalism;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DeferredRegisters {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Survivalism.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Survivalism.MOD_ID);

    /*public static final RegistryObject<Block> TEST = registerBlock("sagebrush",
            () -> new Block(AbstractBlock.Properties.of(Material.STONE)));

    public static void register (IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(ModItemGroup.TUTORIAL_GROUP)));
    }

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }*/

}
