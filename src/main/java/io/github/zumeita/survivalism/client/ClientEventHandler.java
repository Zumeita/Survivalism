package io.github.zumeita.survivalism.client;

import io.github.zumeita.survivalism.Survivalism;
import io.github.zumeita.survivalism.block.BlockVars;
import io.github.zumeita.survivalism.definitions.VegetationBlocks;
import io.github.zumeita.survivalism.item.ItemVars;
import io.github.zumeita.survivalism.registration.DeferredRegisters;
import io.github.zumeita.survivalism.registration.RegistryVegetationBlocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(modid = Survivalism.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)

public class ClientEventHandler {

    public static void init() {

        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(ClientEventHandler::clientSetup);
    }
    public static void clientSetup(FMLClientSetupEvent event) {
        RenderType cutout = RenderType.cutout();

        // Vegetation
        RegistryVegetationBlocks.VEG.values().forEach(veg -> RenderTypeLookup.setRenderLayer(veg.get(), cutout));
    }
}