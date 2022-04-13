package io.github.zumeita.survivalism.world.biome.classifications;

public class BiomeTypes {

    public enum Biomes {
        OCEAN(0, false, false, false, true),
        DEEP_OCEAN(1, false, false, false, true),
        OCEAN_REEF(2, false, false, false, true),
        PLAINS(3, false, false, false, false),
        HILLS(4, false, false, false, false),
        MOUNTAINS(5, true, true, true, false),
        CANYONS(6, false, false, true, false),
        LOWLANDS(7, false, false, false, false),
        VOLCANIC_MOUNTAINS(8, true, false, false, false),
        VOLCANIC_OCEANIC_MOUNTAINS(9, true, false, false, true);

        private int biomeID;
        private boolean isSubduction;
        private boolean isOrogeny;
        private boolean isRift;
        private boolean isOceanic;

        Biomes(int biomeID, boolean isSubduction, boolean isOrogeny, boolean isRift, boolean isOceanic) {
            this.biomeID = biomeID;
            this.isSubduction = isSubduction;
            this.isOrogeny = isOrogeny;
            this.isRift = isRift;
            this.isOceanic = isOceanic;
        }

        public int getBiomeID() { return this.biomeID; }
        public boolean isSubductionBiome() { return this.isSubduction; }
        public boolean isOrogenyBiome() { return this.isOrogeny; }
        public boolean isRiftBiome() { return this.isRift; }
        public boolean isOceanicBiome() { return this.isOceanic; }

        public static final int[] getListOfSubductionBiomeIDs() {

            final int[] subductionBiomes = {
                    Biomes.VOLCANIC_OCEANIC_MOUNTAINS.getBiomeID(),
                    Biomes.VOLCANIC_OCEANIC_MOUNTAINS.getBiomeID(),
                    Biomes.VOLCANIC_MOUNTAINS.getBiomeID(),
                    Biomes.VOLCANIC_MOUNTAINS.getBiomeID(),
                    Biomes.MOUNTAINS.getBiomeID(),
                    Biomes.MOUNTAINS.getBiomeID()

            };

            return subductionBiomes;
        }

        public static final int[] getListOfOrogenyBiomeIDs() {

            final int[] orogenyBiomes = {
                    Biomes.MOUNTAINS.getBiomeID(),
                    Biomes.MOUNTAINS.getBiomeID(),
                    Biomes.MOUNTAINS.getBiomeID(),
                    Biomes.MOUNTAINS.getBiomeID(),
                    Biomes.HILLS.getBiomeID()
            };

            return orogenyBiomes;
        }

        public static final int[] getListOfRiftBiomeIDs() {

            final int[] riftBiomes = {
                    Biomes.CANYONS.getBiomeID(),
                    Biomes.CANYONS.getBiomeID(),
                    Biomes.CANYONS.getBiomeID(),
                    Biomes.CANYONS.getBiomeID(),
                    Biomes.HILLS.getBiomeID(),
                    Biomes.MOUNTAINS.getBiomeID()
            };

            return riftBiomes;
        }

        public static final int[] getListOfLowHeightBiomeIDs() {

            final int[] flatBiomes = {
                    Biomes.PLAINS.getBiomeID(),
                    Biomes.PLAINS.getBiomeID(),
                    Biomes.PLAINS.getBiomeID(),
                    Biomes.PLAINS.getBiomeID(),
                    Biomes.HILLS.getBiomeID(),
                    Biomes.HILLS.getBiomeID(),
                    Biomes.LOWLANDS.getBiomeID(),
                    Biomes.LOWLANDS.getBiomeID()
            };

            return flatBiomes;
        }

        public static final int[] getListOfMediumHeightBiomeIDs() {

            final int[] hillyBiomes = {
                    Biomes.PLAINS.getBiomeID(),
                    Biomes.HILLS.getBiomeID(),
                    Biomes.HILLS.getBiomeID(),
                    Biomes.HILLS.getBiomeID(),
                    Biomes.HILLS.getBiomeID(),
                    Biomes.HILLS.getBiomeID(),
                    Biomes.LOWLANDS.getBiomeID()
            };

            return hillyBiomes;
        }

        public static final int[] getListOfHighHeightBiomeIDs() {

            final int[] highBiomes = {
                    Biomes.HILLS.getBiomeID(),
                    Biomes.HILLS.getBiomeID(),
                    Biomes.HILLS.getBiomeID(),
                    Biomes.HILLS.getBiomeID(),
                    Biomes.MOUNTAINS.getBiomeID(),
                    Biomes.MOUNTAINS.getBiomeID(),
                    Biomes.MOUNTAINS.getBiomeID(),
                    Biomes.MOUNTAINS.getBiomeID()
            };

            return highBiomes;
        }
    }
}
