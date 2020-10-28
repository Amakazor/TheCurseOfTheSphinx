package ourpoint.thecurseofthesphinx.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.init.TCOTSDimensions;
import ourpoint.thecurseofthesphinx.init.TCOTSTileEntities;
import ourpoint.thecurseofthesphinx.items.ScarabKeyItem;
import ourpoint.thecurseofthesphinx.world.storage.PyramidIdTracker;
import ourpoint.thecurseofthesphinx.world.teleporter.PyramidDimensionTeleporter;

import javax.annotation.Nonnull;
import java.util.Objects;

public class PyramidPortalTileEntity extends TileEntity
{
    private int id = 0;
    private int level = 0;

    public PyramidPortalTileEntity()
    {
        super(TCOTSTileEntities.PYRAMID_PORTAL_TILE_ENTITY.get());
    }

    @Override
    public void read(@Nonnull BlockState state, @Nonnull CompoundNBT nbt)
    {
        super.read(state, nbt);

        id = nbt.getInt(TheCurseOfTheSphinx.MOD_ID + "pyramidId");
        level = nbt.getInt(TheCurseOfTheSphinx.MOD_ID + "pyramidLevel");
    }

    @Override
    @Nonnull
    public CompoundNBT write(@Nonnull CompoundNBT compound)
    {
        compound = super.write(compound);

        compound.putInt(TheCurseOfTheSphinx.MOD_ID + "pyramidId", id);
        compound.putInt(TheCurseOfTheSphinx.MOD_ID + "pyramidLevel", level);

        return compound;
    }

    public void Activate(World worldIn, ScarabKeyItem scarabKey)
    {
        if (worldIn.getServer() != null)
        {
            level = scarabKey.getKeyLevel();
            id = worldIn.getServer().func_241755_D_().getSavedData().getOrCreate(PyramidIdTracker::new, PyramidIdTracker.NAME).IncrementId(level);
        }
        markDirty();
    }

    public boolean IsActive()
    {
        return id != 0 && level > 0 && level < 5;
    }

    @SuppressWarnings ("UnusedReturnValue")
    public boolean Teleport(ServerPlayerEntity player)
    {
        if (IsActive())
        {
            if (player.server.getWorld(TCOTSDimensions.PYRAMID_DIMENSION) != null)
            {
                BlockPos destinationPos = new BlockPos(
                        1024*((id/1024)+1)*(level % 2 == 0 ? 1 : -1),
                        74,
                        1024*((id%1024)+1)*((level-1) / 2 == 0 ? 1 : -1)
                );

                //EASY: -x, +Z
                //NORMAL: +x, +Z
                //HARD: -X, -Z
                //CURSED: +X, -Z

                player.changeDimension(Objects.requireNonNull(player.server.getWorld(TCOTSDimensions.PYRAMID_DIMENSION)), new PyramidDimensionTeleporter(destinationPos));
                return true;
            }
        }

        return false;
    }
}
