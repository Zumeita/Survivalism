package io.github.zumeita.survivalism.definitions;

import io.github.zumeita.survivalism.block.blocktypes.HerbBushBlock;
import io.github.zumeita.survivalism.block.blocktypes.HumusBlock;
import io.github.zumeita.survivalism.block.blocktypes.SoilBlock;
import io.github.zumeita.survivalism.registration.DeferredRegisters;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class EarthBlocks {

    static AbstractBlock.Properties properties = AbstractBlock.Properties.of(Material.DIRT, MaterialColor.COLOR_BROWN).strength(0.5F).sound(SoundType.GRAVEL).randomTicks();

    public enum Type {

        HUMUS(new HumusBlock(properties)),
        TOPSOIL(new SoilBlock(properties)),
        SUBSOIL(new SoilBlock(properties)),
        SAND(new SoilBlock(properties));

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
