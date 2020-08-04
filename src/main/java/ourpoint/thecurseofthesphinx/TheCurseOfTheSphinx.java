package ourpoint.thecurseofthesphinx;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ourpoint.thecurseofthesphinx.init.TCOTSInit;

import java.util.stream.Collectors;

@Mod ("thecurseofthesphinx")
public class TheCurseOfTheSphinx
{
    public static final String MOD_ID = "thecurseofthesphinx";
    private static final Logger LOGGER = LogManager.getLogger();

    public TheCurseOfTheSphinx()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        TCOTSInit.Init(FMLJavaModLoadingContext.get().getModEventBus());

        //Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("The Sphinx is watching");
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
        //do something that can only be done on the client
    }

   /* private void enqueueIMC(final InterModEnqueueEvent event)
    {
        //some example code to dispatch IMC to another mod);
    }*/

    /*private void processIMC(final InterModProcessEvent event)
    {
        //some example code to receive and process InterModComms from other mods
    }*/
}
