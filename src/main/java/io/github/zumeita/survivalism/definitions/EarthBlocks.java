package io.github.zumeita.survivalism.definitions;

import io.github.zumeita.survivalism.registration.blocktypes.HumusBlock;
import io.github.zumeita.survivalism.registration.blocktypes.SoilBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.ForgeRegistries;

public class EarthBlocks {

    static AbstractBlock.Properties properties = AbstractBlock.Properties.of(Material.DIRT, MaterialColor.COLOR_BROWN).strength(0.5F).sound(SoundType.GRAVEL).harvestTool(ToolType.SHOVEL).randomTicks();
    static AbstractBlock.Properties propertiesClay = AbstractBlock.Properties.of(Material.CLAY).strength(0.8F).sound(SoundType.GRAVEL).harvestTool(ToolType.SHOVEL);

    public enum Type {

        HUMUS(new HumusBlock(properties)),
        TOPSOIL(new SoilBlock(properties)),
        SUBSOIL(new SoilBlock(properties)),
        SAND(new SoilBlock(properties)),
        CLAY(new SoilBlock(propertiesClay));

        private Block blockSupplier;

        Type(Block blockSupplier) {
            this.blockSupplier = blockSupplier;
        }

        public String getBlockRegistryName() {
            return ("earth/" + this.name().toLowerCase());
        }

        public Block getBlock() {
            return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("survivalism:" + this.getBlockRegistryName()));
        }

        public Block create() {
            return this.blockSupplier; //new SoilBlock(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.COLOR_BROWN).strength(0.5F).sound(SoundType.GRAVEL));
        }
    }

}
