package io.github.zumeita.survivalism.client;

import io.github.zumeita.survivalism.Survivalism;
import io.github.zumeita.survivalism.block.BlockVars;
import io.github.zumeita.survivalism.item.ItemVars;
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

@Mod.EventBusSubscriber(modid = Survivalism.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)

public class ClientEventHandler {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        RenderType cutoutRenderType = RenderType.cutout();

        RenderTypeLookup.setRenderLayer(BlockVars.sagebrush, cutoutRenderType);
    }
}