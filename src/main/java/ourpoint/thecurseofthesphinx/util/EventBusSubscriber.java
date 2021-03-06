package ourpoint.thecurseofthesphinx.util;

import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.items.TCOTSSpawnEggItem;

@Mod.EventBusSubscriber(modid = TheCurseOfTheSphinx.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventBusSubscriber
{
    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event)
    {
        TCOTSSpawnEggItem.initSpawnEggs();
    }
}
