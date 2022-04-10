package io.github.zumeita.survivalism.world.biome.classifications;

import io.github.zumeita.survivalism.world.biome.classifications.HoldridgeLifeZones.Biomes;

public class LifeZones {

    private static Biomes POLAR_DESERT = Biomes.POLAR_DESERT;

    private static Biomes SUBPOLAR_DRY_TUNDRA = Biomes.SUBPOLAR_DRY_TUNDRA;
    private static Biomes SUBPOLAR_MOIST_TUNDRA = Biomes.SUBPOLAR_MOIST_TUNDRA;
    private static Biomes SUBPOLAR_WET_TUNDRA = Biomes.SUBPOLAR_WET_TUNDRA;
    private static Biomes SUBPOLAR_RAIN_TUNDRA = Biomes.SUBPOLAR_RAIN_TUNDRA;

    private static Biomes BOREAL_DESERT = Biomes.BOREAL_DESERT;
    private static Biomes BOREAL_DRY_SCRUB = Biomes.BOREAL_DRY_SCRUB;
    private static Biomes BOREAL_MOIST_FOREST = Biomes.BOREAL_MOIST_FOREST;
    private static Biomes BOREAL_WET_FOREST = Biomes.BOREAL_WET_FOREST;
    private static Biomes BOREAL_RAIN_FOREST = Biomes.BOREAL_RAIN_FOREST;

    private static Biomes COOL_TEMPERATE_DESERT = Biomes.COOL_TEMPERATE_DESERT;
    private static Biomes COOL_TEMPERATE_DESERT_SCRUB = Biomes.COOL_TEMPERATE_DESERT_SCRUB;
    private static Biomes COOL_TEMPERATE_STEPPE = Biomes.COOL_TEMPERATE_STEPPE;
    private static Biomes COOL_TEMPERATE_MOIST_FOREST = Biomes.COOL_TEMPERATE_MOIST_FOREST;
    private static Biomes COOL_TEMPERATE_WET_FOREST = Biomes.COOL_TEMPERATE_WET_FOREST;
    private static Biomes COOL_TEMPERATE_RAIN_FOREST = Biomes.COOL_TEMPERATE_RAIN_FOREST;

    private static Biomes WARM_TEMPERATE_DESERT = Biomes.WARM_TEMPERATE_DESERT;
    private static Biomes WARM_TEMPERATE_DESERT_SCRUB = Biomes.WARM_TEMPERATE_DESERT_SCRUB;
    private static Biomes WARM_TEMPERATE_THORN_STEPPE = Biomes.WARM_TEMPERATE_THORN_STEPPE;
    private static Biomes WARM_TEMPERATE_DRY_FOREST = Biomes.WARM_TEMPERATE_DRY_FOREST;
    private static Biomes WARM_TEMPERATE_MOIST_FOREST = Biomes.WARM_TEMPERATE_MOIST_FOREST;
    private static Biomes WARM_TEMPERATE_WET_FOREST = Biomes.WARM_TEMPERATE_WET_FOREST;
    private static Biomes WARM_TEMPERATE_RAIN_FOREST = Biomes.WARM_TEMPERATE_RAIN_FOREST;

    private static Biomes SUBTROPICAL_DESERT = Biomes.SUBTROPICAL_DESERT;
    private static Biomes SUBTROPICAL_DESERT_SCRUB = Biomes.SUBTROPICAL_DESERT_SCRUB;
    private static Biomes SUBTROPICAL_THORN_STEPPE = Biomes.SUBTROPICAL_THORN_STEPPE;
    private static Biomes SUBTROPICAL_DRY_FOREST = Biomes.SUBTROPICAL_DRY_FOREST;
    private static Biomes SUBTROPICAL_MOIST_FOREST = Biomes.SUBTROPICAL_MOIST_FOREST;
    private static Biomes SUBTROPICAL_WET_FOREST = Biomes.SUBTROPICAL_WET_FOREST;
    private static Biomes SUBTROPICAL_RAIN_FOREST = Biomes.SUBTROPICAL_RAIN_FOREST;

    private static Biomes TROPICAL_DESERT = Biomes.TROPICAL_DESERT;
    private static Biomes TROPICAL_DESERT_SCRUB = Biomes.TROPICAL_DESERT_SCRUB;
    private static Biomes TROPICAL_THORN_STEPPE = Biomes.TROPICAL_THORN_STEPPE;
    private static Biomes TROPICAL_VERY_DRY_FOREST = Biomes.TROPICAL_VERY_DRY_FOREST;
    private static Biomes TROPICAL_DRY_FOREST = Biomes.TROPICAL_DRY_FOREST;
    private static Biomes TROPICAL_MOIST_FOREST = Biomes.TROPICAL_MOIST_FOREST;
    private static Biomes TROPICAL_WET_FOREST = Biomes.TROPICAL_WET_FOREST;
    private static Biomes TROPICAL_RAIN_FOREST = Biomes.TROPICAL_RAIN_FOREST;

    // Basically an array mapping of https://en.wikipedia.org/wiki/Holdridge_life_zones#/media/File:Lifezones_Pengo.svg
    // Ugly as hell, but for now this is how I can get the climate layer to work with one integer as a return value.
    // Temperature Value from 0(Polar) to 6(Tropical), Precipitation value from 0(Super Arid) to 7(Super Humid)

    public static int[] getLifeZonesInts() {
        // 7 Latitudinal Regions, 8 Precipitation values, 8 * 7 = 56
        int[] out = new int[56];
        for(int i = 0; i < 56; i++) {
            out[i] = lifeZones[i].ordinal();
        }
        return out;
    }

    private static Biomes[] values = Biomes.values();
    public static Biomes lookup(int i) {return values[i];}

    public static final Biomes[] lifeZones = new Biomes[] {
        POLAR_DESERT,           POLAR_DESERT,                   POLAR_DESERT,                   POLAR_DESERT,               POLAR_DESERT,                   POLAR_DESERT,                   POLAR_DESERT,                   POLAR_DESERT,
        SUBPOLAR_DRY_TUNDRA,    SUBPOLAR_DRY_TUNDRA,            SUBPOLAR_DRY_TUNDRA,            SUBPOLAR_MOIST_TUNDRA,      SUBPOLAR_WET_TUNDRA,            SUBPOLAR_RAIN_TUNDRA,           SUBPOLAR_RAIN_TUNDRA,           SUBPOLAR_RAIN_TUNDRA,
        BOREAL_DESERT,          BOREAL_DESERT,                  BOREAL_DRY_SCRUB,               BOREAL_MOIST_FOREST,        BOREAL_MOIST_FOREST,            BOREAL_WET_FOREST,              BOREAL_RAIN_FOREST,             BOREAL_RAIN_FOREST,
        COOL_TEMPERATE_DESERT,  COOL_TEMPERATE_DESERT,          COOL_TEMPERATE_DESERT_SCRUB,    COOL_TEMPERATE_STEPPE,      COOL_TEMPERATE_MOIST_FOREST,    COOL_TEMPERATE_WET_FOREST,      COOL_TEMPERATE_RAIN_FOREST,     COOL_TEMPERATE_RAIN_FOREST,
        WARM_TEMPERATE_DESERT,  WARM_TEMPERATE_DESERT_SCRUB,    WARM_TEMPERATE_THORN_STEPPE,    WARM_TEMPERATE_DRY_FOREST,  WARM_TEMPERATE_MOIST_FOREST,    WARM_TEMPERATE_MOIST_FOREST,    WARM_TEMPERATE_WET_FOREST,      WARM_TEMPERATE_RAIN_FOREST,
        SUBTROPICAL_DESERT,     SUBTROPICAL_DESERT_SCRUB,       SUBTROPICAL_THORN_STEPPE,       SUBTROPICAL_DRY_FOREST,     SUBTROPICAL_MOIST_FOREST,       SUBTROPICAL_MOIST_FOREST,       SUBTROPICAL_WET_FOREST,         SUBTROPICAL_RAIN_FOREST,
        TROPICAL_DESERT,        TROPICAL_DESERT_SCRUB,          TROPICAL_THORN_STEPPE,        TROPICAL_VERY_DRY_FOREST,   TROPICAL_DRY_FOREST,            TROPICAL_MOIST_FOREST,          TROPICAL_WET_FOREST,            TROPICAL_RAIN_FOREST,
    };

}
