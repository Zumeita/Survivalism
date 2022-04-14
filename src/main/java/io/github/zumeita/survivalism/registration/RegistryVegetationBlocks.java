package io.github.zumeita.survivalism.registration;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import io.github.zumeita.survivalism.Survivalism;
import io.github.zumeita.survivalism.definitions.VegetationBlocks;
import io.github.zumeita.survivalism.helpers.Helpers;
import io.github.zumeita.survivalism.item.ItemGroups;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Survivalism.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class RegistryVegetationBlocks {

    public static final Map<VegetationBlocks.Default, RegistryObject<Block>> VEG = Helpers.mapOfKeys(VegetationBlocks.Default.class, veg ->
            register(veg.nameFor(), veg::create, ItemGroups.ITEM_GROUP_VEGETATION)
    );

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, ItemGroup group)
    {
        return register(name, blockSupplier, block -> new BlockItem(block, new Item.Properties().tab(group)), true);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, Function<T, ? extends BlockItem> blockItemFactory, boolean hasItemBlock)
    {
        RegistryObject<T> block = DeferredRegisters.BLOCKS.register(name, blockSupplier);
        if (hasItemBlock)
        {
            DeferredRegisters.ITEMS.register(name, () -> blockItemFactory.apply(block.get()));

        }
        return block;
    }
}
