package io.github.zumeita.survivalism.item;

import io.github.zumeita.survivalism.Survivalism;
import io.github.zumeita.survivalism.definitions.EarthBlocks;
import io.github.zumeita.survivalism.definitions.VegetationBlocks;
import io.github.zumeita.survivalism.registration.RegisterEarthBlocks;
import io.github.zumeita.survivalism.registration.RegisterItems;
import io.github.zumeita.survivalism.registration.RegistryVegetationBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.Lazy;
import java.util.function.Supplier;

public class ItemGroups extends ItemGroup {

    public static final ItemGroup ITEM_GROUP_SEEDS = new ItemGroups("seeds", () -> new ItemStack(RegistryVegetationBlocks.VEG.get(VegetationBlocks.Default.SAGEBRUSH).get()));
    public static final ItemGroup ITEM_GROUP_FOOD = new ItemGroups("food", () -> new ItemStack(RegistryVegetationBlocks.VEG.get(VegetationBlocks.Default.SAGEBRUSH).get()));
    public static final ItemGroup ITEM_GROUP_VEGETATION = new ItemGroups("vegetation", () -> new ItemStack(RegistryVegetationBlocks.VEG.get(VegetationBlocks.Default.SAGEBRUSH).get()));
    public static final ItemGroup ITEM_GROUP_MATERIALS = new ItemGroups("materials", () -> new ItemStack(RegisterItems.CLAY.get()));

    public static final ItemGroup ITEM_GROUP_EARTH = new ItemGroups("earth", () -> new ItemStack(RegisterEarthBlocks.EARTH_BLOCKS.get(EarthBlocks.Type.HUMUS).get()));

    private final Lazy<ItemStack> iconStack;

    private ItemGroups(String label, Supplier<ItemStack> iconSupplier)
    {
        super(Survivalism.MOD_ID + "." + label);
        this.iconStack = Lazy.of(iconSupplier);
    }

    @Override
    public ItemStack makeIcon()
    {
        return iconStack.get();
    }

}
