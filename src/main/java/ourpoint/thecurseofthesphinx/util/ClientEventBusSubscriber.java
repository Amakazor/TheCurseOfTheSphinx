package ourpoint.thecurseofthesphinx.util;

import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.client.entity.render.MummyRenderer;
import ourpoint.thecurseofthesphinx.init.TCOTSEntityTypes;
import ourpoint.thecurseofthesphinx.items.TCOTSSpawnEggItem;

@Mod.EventBusSubscriber(modid = TheCurseOfTheSphinx.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber
{
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(TCOTSEntityTypes.MUMMY_ENTITY.get(), MummyRenderer::new);
    }

    @SubscribeEvent
    public static void onLoadSpawns (FMLClientSetupEvent event)
    {
        TCOTSEntitySpawn.spawnMobs();
    }

    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event)
    {
        TCOTSSpawnEggItem.initSpawnEggs();
    }
}
