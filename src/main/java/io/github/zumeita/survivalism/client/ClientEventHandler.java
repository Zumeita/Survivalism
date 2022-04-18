package io.github.zumeita.survivalism.client;

import io.github.zumeita.survivalism.Survivalism;
import io.github.zumeita.survivalism.definitions.EarthBlocks;
import io.github.zumeita.survivalism.definitions.VegetationBlocks;
import io.github.zumeita.survivalism.registration.RegistryVegetationBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientEventHandler {

    public static void init() {

        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(ClientEventHandler::clientSetup);
        bus.addListener(ClientEventHandler::registerColorHandlerBlocks);
        bus.addListener(ClientEventHandler::registerParticleFactoriesAndOtherStuff);
    }
    public static void clientSetup(FMLClientSetupEvent event) {
        RenderType cutout = RenderType.cutout();
        final RenderType cutoutMipped = RenderType.cutoutMipped();

        // Vegetation
        RegistryVegetationBlocks.VEG.values().forEach(veg -> RenderTypeLookup.setRenderLayer(veg.get(), cutout));

        // Blocks
        RenderTypeLookup.setRenderLayer(EarthBlocks.Type.HUMUS.getBlock(), cutoutMipped);
    }

    public static void registerColorHandlerBlocks(final ColorHandlerEvent.Block event) {
        final BlockColors registry = event.getBlockColors();
        registry.register((state, worldIn, pos, tintIndex) -> ColorHandler.getFoliageColor(pos, tintIndex), EarthBlocks.Type.HUMUS.getBlock());
        registry.register((state, worldIn, pos, tintIndex) -> ColorHandler.getFoliageColor(pos, tintIndex), VegetationBlocks.Default.GRASS.getBlock());
    }

    public static void registerParticleFactoriesAndOtherStuff(ParticleFactoryRegisterEvent event)
    {
        IReloadableResourceManager resourceManager = (IReloadableResourceManager) Minecraft.getInstance().getResourceManager();
        resourceManager.registerReloadListener(new ColorMapReloadListener(ColorHandler::setFoliageColors, ColorHandler.FOLIAGE_COLORS_LOCATION));
    }
}