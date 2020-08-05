package ourpoint.thecurseofthesphinx.util;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;
import ourpoint.thecurseofthesphinx.init.TCOTSEntityTypes;

public class TCOTSEntitySpawn
{
    public static void spawnMobs()
    {
        ForgeRegistries.BIOMES.getValues().stream()
                .filter(biome -> biome.getCategory() == Biome.Category.DESERT)
                .forEach(biome -> {
                    biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(TCOTSEntityTypes.MUMMY_ENTITY.get(), 30, 1, 1));
        });
    }
}
