package io.github.zumeita.survivalism.definitions;

import io.github.zumeita.survivalism.block.blocktypes.HerbBushBlock;
import io.github.zumeita.survivalism.block.blocktypes.NewFlowerBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.LinkedList;
import java.util.List;

public class VegetationBlocks {

    public enum VegSeasons {
        VEG_SEASON_WINTER,
        VEG_SEASON_SPRING,
        VEG_SEASON_SUMMER,
        VEG_SEASON_AUTUMN
    }
    public enum VegType {
        VEG_TYPE_HERB,
        VEG_TYPE_FLOWER,
        VEG_TYPE_GRASS,
        VEG_TYPE_BUSH
    }

    public enum Default {

        SAGEBRUSH(VegType.VEG_TYPE_BUSH, false, Blocks.DEAD_BUSH.getBlock(), null, null, null, null, false, null),
        BITTERBRUSH(VegType.VEG_TYPE_BUSH, false, Blocks.DEAD_BUSH.getBlock(), null, null, null, null, true, FloweringSeasonSpringSummer()),
        MILKVETCH(VegType.VEG_TYPE_BUSH, false, Blocks.DEAD_BUSH.getBlock(), null, null, null, null, true, FloweringSeasonSummer()),
        ADONIS(VegType.VEG_TYPE_FLOWER, false, null, null, null, null,  null, true, FloweringSeasonSpringSummer());

        private final VegType type;
        private final Boolean is_tall;
        private final Block harvested_block;
        private final Item main_drop;
        private final Item secondary_drop;
        private final Item seed_drop;
        private final BlockItem block_drop;
        private final Boolean flowering_cycle;
        private final List<VegSeasons> flowering_seasons;

        Default(VegType type, Boolean is_tall, Block harvested_block, Item main_drop, Item secondary_drop, Item seed_drop, BlockItem block_drop, Boolean flowering_cycle, List<VegSeasons> flowering_seasons) {

            this.type = type;
            this.is_tall = is_tall;
            this.harvested_block = harvested_block;
            this.main_drop = main_drop;
            this.secondary_drop = secondary_drop;
            this.seed_drop = seed_drop;
            this.block_drop = block_drop;
            this.flowering_cycle = flowering_cycle;
            this.flowering_seasons = flowering_seasons;

            Block block = ForgeRegistries.BLOCKS.getValue( ForgeRegistries.BLOCKS.getRegistryName());
        }

        public VegType GetVegetationCategory() { return type; }
        public Boolean IsVegetationTall() { return is_tall; }
        public Block GetVegetationHarvestedBlock() { return harvested_block; }
        public Item GetVegetationMainDrop() { return main_drop; }
        public Item GetVegetationSecondaryDrop() { return secondary_drop; }
        public Item GetVegetationSeedDrop() { return seed_drop; }
        public Item GetVegetationBlockDrop() { return block_drop; }
        public Boolean DoesVegetationFlower() { return flowering_cycle; }
        public List<VegSeasons> GetVegetationFloweringSeasons() { return flowering_seasons; }

        public Block create() {
            switch(this.GetVegetationCategory()) {
                case VEG_TYPE_HERB:
                case VEG_TYPE_GRASS:
                case VEG_TYPE_BUSH:
                    return new HerbBushBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.TERRACOTTA_GREEN).noCollission().instabreak().sound(SoundType.GRASS));
                case VEG_TYPE_FLOWER:
                    return new NewFlowerBlock(Effects.REGENERATION, 7, AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS));
                default:
                    return null;
            }
        }

        public String nameFor() {
            switch(this.GetVegetationCategory()) {
                case VEG_TYPE_FLOWER:
                    return ("veg/flower/" + this.name().toLowerCase());
                case VEG_TYPE_GRASS:
                    return ("veg/grass/" + this.name().toLowerCase());
                case VEG_TYPE_HERB:
                    return ("veg/herb/" + this.name().toLowerCase());
                case VEG_TYPE_BUSH:
                    return ("veg/bush/" + this.name().toLowerCase());
                default:
                    break;
            }
            return null;
        }

        @SuppressWarnings("unused")
        private static List<VegSeasons> FloweringSeasonSpring() {
            final List<VegSeasons> list = new LinkedList<VegSeasons> ();
            list.add(VegSeasons.VEG_SEASON_SPRING);
            return list;
        }

        private static List<VegSeasons> FloweringSeasonSpringSummer() {
            final List<VegSeasons> list = new LinkedList<VegSeasons>();
            list.add(VegSeasons.VEG_SEASON_SPRING);
            list.add(VegSeasons.VEG_SEASON_SUMMER);
            return list;
        }

        @SuppressWarnings("unused")
        private static List<VegSeasons> FloweringSeasonSummer() {
            final List<VegSeasons> list = new LinkedList<VegSeasons> ();
            list.add(VegSeasons.VEG_SEASON_SUMMER);
            return list;
        }

        @SuppressWarnings("unused")
        private static List<VegSeasons> FloweringSeasonAutumn() {
            final List<VegSeasons> list = new LinkedList<VegSeasons> ();
            list.add(VegSeasons.VEG_SEASON_AUTUMN);
            return list;
        }

        @SuppressWarnings("unused")
        private static List<VegSeasons> FloweringSeasonWinter() {
            final List<VegSeasons> list = new LinkedList<VegSeasons> ();
            list.add(VegSeasons.VEG_SEASON_WINTER);
            return list;
        }
    }
}
