package ourpoint.thecurseofthesphinx.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.tileentity.PyramidPortalTileEntity;

public class TCOTSTileEntities
{
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, TheCurseOfTheSphinx.MOD_ID);

    @SuppressWarnings ("ConstantConditions")
    public static final RegistryObject<TileEntityType<PyramidPortalTileEntity>> PYRAMID_PORTAL_TILE_ENTITY = TILE_ENTITIES.register("pyramid_portal_tile_entity",
            () -> TileEntityType.Builder.create(PyramidPortalTileEntity::new, TCOTSBlocks.PYRAMID_PORTAL_BLOCK.get()).build(null));
}
