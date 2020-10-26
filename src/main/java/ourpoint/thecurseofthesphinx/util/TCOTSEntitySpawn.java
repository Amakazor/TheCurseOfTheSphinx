package ourpoint.thecurseofthesphinx.util;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.registries.ForgeRegistries;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.init.TCOTSEntityTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TCOTSEntitySpawn
{
    public static void spawnMobs()
    {
        ForgeRegistries.BIOMES.getValues().stream()
                .filter(biome -> biome.getCategory() == Biome.Category.DESERT)
                .forEach(biome -> {
                    TheCurseOfTheSphinx.LOGGER.debug("Adding spawns for" + biome.toString());
                    makeSpawnersMapMutable(biome);
                    addMobSpawn(biome, EntityClassification.MONSTER, new MobSpawnInfo.Spawners(TCOTSEntityTypes.MUMMY_ENTITY.get(), 20, 1, 1));
                    addMobSpawn(biome, EntityClassification.CREATURE, new MobSpawnInfo.Spawners(TCOTSEntityTypes.SCARAB_ENTITY.get(), 40, 2, 4));
        });
    }

    public static void addMobSpawn(Biome biome, EntityClassification classification, MobSpawnInfo.Spawners spawner)
    {
        List<MobSpawnInfo.Spawners> spawners = new ArrayList<>(biome.getMobSpawnInfo().spawners.get(classification));
        spawners.add(spawner);
        biome.getMobSpawnInfo().spawners.put(classification, spawners);
    }

    public static void makeSpawnersMapMutable(Biome biome)
    {
        if (biome.getMobSpawnInfo().spawners instanceof ImmutableMap)
        {
            biome.getMobSpawnInfo().spawners = new HashMap<>(biome.getMobSpawnInfo().spawners);
        }
    }
}
