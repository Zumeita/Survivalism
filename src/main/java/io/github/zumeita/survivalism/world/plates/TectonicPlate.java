package io.github.zumeita.survivalism.world.plates;

public final class TectonicPlate {

    private final float chunkX;
    private final float chunkY;
    private final float driftX;
    private final float driftY;
    private final float height;
    private final boolean isOceanic;

    public TectonicPlate(float chunkX, float chunkY, float driftX, float driftY, float height, boolean isOceanic) {
        this.chunkX = chunkX;
        this.chunkY = chunkY;
        this.driftX = driftX;
        this.driftY = driftY;
        this.height = height;
        this.isOceanic = isOceanic;
    }

    public float getChunkX() { return chunkX; }
    public float getChunkY() { return chunkY; }
    public float getDriftX() { return driftX; }
    public float getDriftY() { return driftY; }
    public float getHeight() { return height; }
    public boolean isOceanic() { return isOceanic; }
}
