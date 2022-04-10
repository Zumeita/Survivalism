package io.github.zumeita.survivalism.world.biome.classifications;

import io.github.zumeita.survivalism.world.biome.biomes.*;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;

import java.util.ArrayList;
import java.util.function.Supplier;

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
        POLAR(0.0, 1.4),
        SUBPOLAR(1.5, 2.9),
        BOREAL(3.0, 5.9),
        COOL_TEMPERATE(6.0, 11.9),
        WARM_TEMPERATE(12.0, 17.9),
        SUBTROPICAL(18.0, 23.9),
        TROPICAL(24.0, 30.0);

        private final double min_temp;
        private final double max_temp;

        BioTemperature(double min_temp, double max_temp) {
            this.min_temp = min_temp;
            this.max_temp = max_temp;
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

    public enum HumidityZones {
        SUPER_ARID,
        PERARID,
        ARID,
        SEMI_ARID,
        SUB_HUMID,
        HUMID,
        PER_HUMID,
        SUPER_HUMID;
    }


    public enum Biomes {

        POLAR_DESERT(0.0, 1.4, PolarBiomes::PolarDesert),

        SUBPOLAR_DRY_TUNDRA(1.5, 2.9, SubpolarBiomes::SubpolarDryTundra),
        SUBPOLAR_MOIST_TUNDRA(1.5, 2.9, SubpolarBiomes::SubpolarDryTundra),
        SUBPOLAR_WET_TUNDRA(1.5, 2.9, SubpolarBiomes::SubpolarDryTundra),
        SUBPOLAR_RAIN_TUNDRA(1.5, 2.9, SubpolarBiomes::SubpolarDryTundra),

        BOREAL_DESERT(3.0, 5.9, BorealBiomes::PolarDesert),
        BOREAL_DRY_SCRUB(3.0, 5.9, BorealBiomes::PolarDesert),
        BOREAL_MOIST_FOREST(3.0, 5.9, BorealBiomes::PolarDesert),
        BOREAL_WET_FOREST(3.0, 5.9, BorealBiomes::PolarDesert),
        BOREAL_RAIN_FOREST(3.0, 5.9, BorealBiomes::PolarDesert),

        COOL_TEMPERATE_DESERT(6.0, 11.9, CoolTemperateBiomes::PolarDesert),
        COOL_TEMPERATE_DESERT_SCRUB(6.0, 11.9, CoolTemperateBiomes::PolarDesert),
        COOL_TEMPERATE_STEPPE(6.0, 11.9, CoolTemperateBiomes::PolarDesert),
        COOL_TEMPERATE_MOIST_FOREST(6.0, 11.9, CoolTemperateBiomes::PolarDesert),
        COOL_TEMPERATE_WET_FOREST(6.0, 11.9, CoolTemperateBiomes::PolarDesert),
        COOL_TEMPERATE_RAIN_FOREST(6.0, 11.9, CoolTemperateBiomes::PolarDesert),

        WARM_TEMPERATE_DESERT(12.0, 17.9, WarmTemperateBiomes::PolarDesert),
        WARM_TEMPERATE_DESERT_SCRUB(12.0, 17.9, WarmTemperateBiomes::PolarDesert),
        WARM_TEMPERATE_THORN_STEPPE(12.0, 17.9, WarmTemperateBiomes::PolarDesert),
        WARM_TEMPERATE_DRY_FOREST(12.0, 17.9, WarmTemperateBiomes::PolarDesert),
        WARM_TEMPERATE_MOIST_FOREST(12.0, 17.9, WarmTemperateBiomes::PolarDesert),
        WARM_TEMPERATE_WET_FOREST(12.0, 17.9, WarmTemperateBiomes::PolarDesert),
        WARM_TEMPERATE_RAIN_FOREST(12.0, 17.9, WarmTemperateBiomes::PolarDesert),

        SUBTROPICAL_DESERT(18.0, 23.9, SubtropicalBiomes::PolarDesert),
        SUBTROPICAL_DESERT_SCRUB(18.0, 23.9, SubtropicalBiomes::PolarDesert),
        SUBTROPICAL_THORN_STEPPE(18.0, 23.9, SubtropicalBiomes::PolarDesert),
        SUBTROPICAL_DRY_FOREST(18.0, 23.9, SubtropicalBiomes::PolarDesert),
        SUBTROPICAL_MOIST_FOREST(18.0, 23.9, SubtropicalBiomes::PolarDesert),
        SUBTROPICAL_WET_FOREST(18.0, 23.9, SubtropicalBiomes::PolarDesert),
        SUBTROPICAL_RAIN_FOREST(18.0, 23.9, SubtropicalBiomes::PolarDesert),

        TROPICAL_DESERT(24.0, 30.0, TropicalBiomes::PolarDesert),
        TROPICAL_DESERT_SCRUB(24.0, 30.0, TropicalBiomes::PolarDesert),
        TROPICAL_THORN_STEPPE(24.0, 30.0, TropicalBiomes::PolarDesert),
        TROPICAL_VERY_DRY_FOREST(24.0, 30.0, TropicalBiomes::PolarDesert),
        TROPICAL_DRY_FOREST(24.0, 30.0, TropicalBiomes::PolarDesert),
        TROPICAL_MOIST_FOREST(24.0, 30.0, TropicalBiomes::PolarDesert),
        TROPICAL_WET_FOREST(24.0, 30.0, TropicalBiomes::PolarDesert),
        TROPICAL_RAIN_FOREST(24.0, 30.0, TropicalBiomes::PolarDesert);

        private double min_temp;
        private double max_temp;
        private Supplier<? extends Biome> biomeSupplier;

        Biomes(double min_temp, double max_temp, Supplier<? extends Biome> biomeSupplier) {
            this.min_temp = min_temp;
            this.max_temp = max_temp;
            this.biomeSupplier = biomeSupplier;
        }


        public double getMinimumTemperature() {
            return this.min_temp;
        }

        public double getMaximumTemperature() {
            return this.max_temp;
        }

        public Supplier<? extends Biome> getBiomeSupplier() { return this.biomeSupplier; }

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

        private RegistryKey<Biome> registryKey;

        public void addRegistryKey(RegistryKey<Biome> key) {
            this.registryKey = key;
        }

        public RegistryKey<Biome> getBiomeRegistryKey() {
            return this.registryKey;
        }
    }
}
