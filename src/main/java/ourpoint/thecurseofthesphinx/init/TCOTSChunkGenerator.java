package ourpoint.thecurseofthesphinx.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.world.chunk_generator.PyramidChunkGenerator;

public class TCOTSChunkGenerator
{
    public static void registerChunkGenerator() {
        Registry.register(Registry.CHUNK_GENERATOR_CODEC, new ResourceLocation(TheCurseOfTheSphinx.MOD_ID, "pyramid_chunk_generator"), PyramidChunkGenerator.CODEC);
    }
}
