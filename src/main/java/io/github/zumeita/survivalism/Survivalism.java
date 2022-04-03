package io.github.zumeita.survivalism;

import io.github.zumeita.survivalism.registration.DeferredRegisters;
import io.github.zumeita.survivalism.registration.RegistryVegetationBlocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod(Survivalism.MOD_ID)
public class Survivalism
{
    public static final String MOD_ID = "survivalism";
    private static final Logger LOGGER = LogManager.getLogger();
    public static Survivalism instance;

    public void Survivalism() {
        instance = this;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.register(this);

        DeferredRegisters.BLOCKS.register(modEventBus);
        DeferredRegisters.ITEMS.register(modEventBus);
    }

    @SubscribeEvent
    public void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("Setup");
    }
}