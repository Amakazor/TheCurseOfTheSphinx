package ourpoint.thecurseofthesphinx.generators;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TCOTSDataGenerators
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(new TCOTSBlockstateGenerator(generator, TheCurseOfTheSphinx.MOD_ID, existingFileHelper));
        generator.addProvider(new TCOTSRecipeGenerator(generator));
    }
}
