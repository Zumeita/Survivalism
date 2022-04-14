package io.github.zumeita.survivalism.registration;

import io.github.zumeita.survivalism.Survivalism;
import io.github.zumeita.survivalism.item.ItemGroups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Survivalism.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterItems {

    public static final RegistryObject<Item> CLAY = DeferredRegisters.ITEMS.register("clay",
            () -> new Item(new Item.Properties().stacksTo(16).tab(ItemGroup.TAB_MATERIALS)));

}
