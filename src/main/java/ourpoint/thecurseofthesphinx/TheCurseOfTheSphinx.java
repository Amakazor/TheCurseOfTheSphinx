package ourpoint.thecurseofthesphinx;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
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
import ourpoint.thecurseofthesphinx.entity.MummyEntity;
import ourpoint.thecurseofthesphinx.init.TCOTSEntityTypes;
import ourpoint.thecurseofthesphinx.init.TCOTSInit;
import ourpoint.thecurseofthesphinx.items.TCOTSSpawnEggItem;

import java.util.stream.Collectors;

@Mod (TheCurseOfTheSphinx.MOD_ID)
public class TheCurseOfTheSphinx
{
    public static final String MOD_ID = "thecurseofthesphinx";
    public static final Logger LOGGER = LogManager.getLogger();

    public TheCurseOfTheSphinx()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        TCOTSInit.Init(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("The Sphinx is watching");
        DeferredWorkQueue.runLater(
                ()-> GlobalEntityTypeAttributes.put(TCOTSEntityTypes.MUMMY_ENTITY.get(), MummyEntity.setCustomAttributes().create()));
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
