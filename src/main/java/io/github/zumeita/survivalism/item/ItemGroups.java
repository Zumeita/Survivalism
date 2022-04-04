package io.github.zumeita.survivalism.item;

import io.github.zumeita.survivalism.Survivalism;
import io.github.zumeita.survivalism.definitions.VegetationBlocks;
import io.github.zumeita.survivalism.registration.RegistryVegetationBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.Lazy;
import java.util.function.Supplier;

public class ItemGroups extends ItemGroup {

    public static final ItemGroup ITEM_GROUP_SEEDS = new ItemGroups("seeds", () -> new ItemStack(RegistryVegetationBlocks.VEG.get(VegetationBlocks.Default.SAGEBRUSH).get()));
    public static final ItemGroup ITEM_GROUP_FOOD = new ItemGroups("food", () -> new ItemStack(RegistryVegetationBlocks.VEG.get(VegetationBlocks.Default.SAGEBRUSH).get()));
    public static final ItemGroup ITEM_GROUP_VEGETATION = new ItemGroups("vegetation", () -> new ItemStack(RegistryVegetationBlocks.VEG.get(VegetationBlocks.Default.SAGEBRUSH).get()));

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
