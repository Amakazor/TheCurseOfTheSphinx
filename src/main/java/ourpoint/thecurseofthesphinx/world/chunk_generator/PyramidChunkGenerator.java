package ourpoint.thecurseofthesphinx.world.chunk_generator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Blockreader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;

import javax.annotation.Nonnull;

public class PyramidChunkGenerator extends ChunkGenerator
{
    public static final Codec<PyramidChunkGenerator> CODEC = RecordCodecBuilder.create(
        (instance) -> instance.group(
                BiomeProvider.CODEC.fieldOf("biome_source").forGetter((surfaceChunkGenerator) -> surfaceChunkGenerator.biomeProvider)
        ).apply(instance, instance.stable(PyramidChunkGenerator::new)));

    public PyramidChunkGenerator(BiomeProvider biomeProvider) {
        super(biomeProvider, new DimensionStructuresSettings(false));
    }

    @Override
    @Nonnull
    protected Codec<? extends ChunkGenerator> func_230347_a_()
    {
        return CODEC;
    }

    @Override
    @Nonnull
    public ChunkGenerator func_230349_a_(long p_230349_1_)
    {
        return this;
    }

    @Override
    public void generateSurface(@Nonnull WorldGenRegion worldGenRegion, @Nonnull IChunk chunk)
    {
        if (chunk.getPos().x > 0 && chunk.getPos().z > 0 && chunk.getPos().x % 16 == 0 && chunk.getPos().z % 16 == 0)
        {
            for (int px = 0; px < 16; px++)
            {
                for (int pz = 0; pz < 16; pz++)
                {
                    chunk.setBlockState(new BlockPos(px, 64, pz), Blocks.SANDSTONE.getDefaultState(), false);
                }
            }
        }
    }

    @Override
    public void func_230352_b_(@Nonnull IWorld p_230352_1_,@Nonnull StructureManager p_230352_2_,@Nonnull IChunk p_230352_3_)
    {

    }

    @Override
    public int getHeight(int x, int z,@Nonnull Heightmap.Type heightmapType)
    {
        return 0;
    }

    @Override
    @Nonnull
    public IBlockReader func_230348_a_(int p_230348_1_, int p_230348_2_)
    {
        return new Blockreader(new BlockState[0]);
    }
}
