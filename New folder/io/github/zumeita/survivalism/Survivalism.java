package io.github.zumeita.survivalism;

import io.github.zumeita.survivalism.client.ClientEventHandler;
import io.github.zumeita.survivalism.registration.DeferredRegisters;
import io.github.zumeita.survivalism.world.biome.BiomeRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Survivalism.MOD_ID)
public class Survivalism
{
    public static final String MOD_ID = "survivalism";
    private static final Logger LOGGER = LogManager.getLogger();
   // public static Survivalism instance;

    public Survivalism() {
        //instance = this;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        //modEventBus.register(this);

        //ModItems.register(modEventBus);
        //DeferredRegisters.register(modEventBus);
        DeferredRegisters.ITEMS.register(modEventBus);
        DeferredRegisters.BLOCKS.register(modEventBus);
        BiomeRegistry.register(modEventBus);

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientEventHandler::init);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
        }
    }

    @SubscribeEvent
    public void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("Setup");
    }
}