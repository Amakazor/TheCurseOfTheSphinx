package ourpoint.thecurseofthesphinx.util;

import net.minecraft.entity.EntityClassification;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.init.TCOTSEntityTypes;

@Mod.EventBusSubscriber(modid = TheCurseOfTheSphinx.MOD_ID)
public class TCOTSEntitySpawn
{
    @SubscribeEvent
    public static void onBiomeLoaded(final BiomeLoadingEvent event)
    {
        if (event.getCategory() == Biome.Category.DESERT)
        {
            MobSpawnInfo.Spawners mummySpawn = new MobSpawnInfo.Spawners(TCOTSEntityTypes.MUMMY_ENTITY.get(), 20, 1, 1);
            MobSpawnInfo.Spawners scarabSpawn = new MobSpawnInfo.Spawners(TCOTSEntityTypes.SCARAB_ENTITY.get(), 40, 2, 6);

            event.getSpawns()
                    .withSpawner(EntityClassification.MONSTER, mummySpawn)
                    .withSpawner(EntityClassification.CREATURE, scarabSpawn);
        }
    }
}
