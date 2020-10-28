package ourpoint.thecurseofthesphinx.init;


import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;

public class TCOTSDimensions
{
    public static RegistryKey<World> PYRAMID_DIMENSION;

    public static void RegisterDimensions()
    {
        PYRAMID_DIMENSION = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(TheCurseOfTheSphinx.MOD_ID, "pyramid_easy"));
    }
}
