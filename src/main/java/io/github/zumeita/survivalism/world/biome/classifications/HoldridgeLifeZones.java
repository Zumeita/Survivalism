package io.github.zumeita.survivalism.world.biome.classifications;

import java.util.ArrayList;
import java.util.stream.Stream;

/*

Biome selection is based upon Holdridge Life Zones (https://en.wikipedia.org/wiki/Holdridge_life_zones)
I have chosen this climate classification system because it allows a large variety of biomes.
Compared to the Koppen Classification System, which is much broader.

When a biome is registered, it should have an Evaporation and Precipitation value.

A diagram of the classification: https://en.wikipedia.org/wiki/Holdridge_life_zones#/media/File:Lifezones_Pengo.svg

Other sources of relevant info:

https://wikidwelling.fandom.com/wiki/Holdridge_life_zones

 */
public class HoldridgeLifeZones {

    public static int getAmountOfBiomes() {
        return Biomes.values().length;
    }

    public enum BioTemperature {
        POLAR(0.0, 1.4, new Biomes[]{Biomes.DESERT}),
        SUBPOLAR(1.5, 2.9, new Biomes[]{Biomes.DRY_TUNDRA, Biomes.MOIST_TUNDRA, Biomes.WET_TUNDRA, Biomes.RAIN_TUNDRA}),
        BOREAL(3.0, 5.9, new Biomes[]{Biomes.DESERT, Biomes.DRY_SCRUB, Biomes.MOIST_FOREST, Biomes.WET_FOREST, Biomes.RAIN_FOREST}),
        COOL_TEMPERATE(6.0, 11.9, new Biomes[]{Biomes.DESERT, Biomes.DESERT_SCRUB, Biomes.STEPPE, Biomes.MOIST_FOREST, Biomes.WET_FOREST, Biomes.RAIN_FOREST}),
        WARM_TEMPERATE(12.0, 17.9, new Biomes[]{Biomes.DESERT, Biomes.DESERT_SCRUB, Biomes.THORN_STEPPE, Biomes.THORN_WOODLAND, Biomes.DRY_FOREST, Biomes.MOIST_FOREST, Biomes.WET_FOREST, Biomes.RAIN_FOREST}),
        SUBTROPICAL(18.0, 23.9, new Biomes[]{Biomes.DESERT, Biomes.DESERT_SCRUB, Biomes.THORN_STEPPE, Biomes.THORN_WOODLAND, Biomes.DRY_FOREST, Biomes.MOIST_FOREST, Biomes.WET_FOREST, Biomes.RAIN_FOREST}),
        TROPICAL(24.0, 30.0, new Biomes[]{Biomes.DESERT, Biomes.DESERT_SCRUB, Biomes.THORN_WOODLAND, Biomes.VERY_DRY_FOREST, Biomes.DRY_FOREST, Biomes.MOIST_FOREST, Biomes.WET_FOREST, Biomes.RAIN_FOREST});

        private final double min_temp;
        private final double max_temp;
        private final Biomes[] validBiomes;

        BioTemperature(double min_temp, double max_temp, Biomes[] validBiomes) {
            this.min_temp = min_temp;
            this.max_temp = max_temp;
            this.validBiomes = validBiomes;
        }

        /**
         * Use this function to get a lower case string of the BioTemperature zone name.
         * @return A Lower-case string of the BioTemperature name.
         */
        public String getName() {
            return this.name().toLowerCase();
        }

        public double getMinimumTemperature() {
            return this.min_temp;
        }

        public double getMaximumTemperature() {
            return this.max_temp;
        }

        public Biomes[] getValidBiomes() {
            return this.validBiomes;
        }

        /**
         * Use this function to get the BioTemperature entry (Latitudinal Region) from a average temperature value.
         * @param average_temp The average temperature of the biome as a Double.
         * @return The BiomeTemperature enum relevant to the average temperature of the biome.
         */
        public BioTemperature getRegionFromAverageTemperature(double average_temp) {
            for(BioTemperature i : BioTemperature.values()) {
                if(average_temp >= i.getMinimumTemperature() && average_temp <= i.getMaximumTemperature()) {
                    return i;
                }
            }

            if(average_temp > 30.0) {
                return TROPICAL;
            }
            else if(average_temp < 0.0) {
                return POLAR;
            }

            return null;
        }
    }

    public enum Biomes {

        DESERT(0.0, 30.0),
        DESERT_SCRUB(6.0, 30.0),

        DRY_SCRUB(3.0, 6.0),
        DRY_TUNDRA(1.5, 3.0),
        DRY_FOREST(12.0, 30.0),

        MOIST_TUNDRA(1.5, 3.0),
        MOIST_FOREST(3.0, 30.0),

        THORN_WOODLAND(12.0, 30.0),
        THORN_STEPPE(12.0, 24.0),

        STEPPE(6.0, 12.0),

        VERY_DRY_FOREST(24.0, 30.0),

        WET_TUNDRA(1.5, 3.0),
        WET_FOREST(3.0, 30.0),

        RAIN_TUNDRA(1.5, 3.0),
        RAIN_FOREST(24.0, 30.0);

        private final double min_temp;
        private final double max_temp;

        Biomes(double min_temp, double max_temp) {
            this.min_temp = min_temp;
            this.max_temp = max_temp;
        }

        public double getMinimumTemperature() {
            return this.min_temp;
        }

        public double getMaximumTemperature() {
            return this.max_temp;
        }

        public String getName() {
            return this.name().toLowerCase();
        }

        /**
         * Use this function to retrieve a list of Biomes that are useabole for a given average temperature.
         *
         * @param average_temp Average temperature value as a Double.
         * @return An ArrayList of Biomes for the parsed average temperature value.
         */
        public ArrayList<Biomes> getAvailableBiomesFromAverageTemperature(double average_temp) {

            ArrayList<Biomes> biomesToReturn = new ArrayList<Biomes>();

            if (average_temp > 30.0) {
                average_temp = 30.0;
            } else if (average_temp < 0.0) {
                average_temp = 0.0;
            }

            for (Biomes i : Biomes.values()) {
                if (average_temp >= i.getMinimumTemperature() && average_temp <= i.getMaximumTemperature()) {
                    biomesToReturn.add(i);
                }
            }
            return biomesToReturn;
        }
    }
}
