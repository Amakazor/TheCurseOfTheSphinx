package ourpoint.thecurseofthesphinx.world.storage;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.storage.WorldSavedData;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;

import javax.annotation.Nonnull;

public class PyramidIdTracker extends WorldSavedData
{
    public static final String NAME = TheCurseOfTheSphinx.MOD_ID + "pyramidids";

    private int easyId = 0;
    private int normalId = 0;
    private int hardId = 0;
    private int cursedId = 0;

    public PyramidIdTracker()
    {
        super(NAME);
    }

    @Override
    public void read(@Nonnull CompoundNBT nbt)
    {
        easyId = nbt.getInt(TheCurseOfTheSphinx.MOD_ID + "easyId");
        normalId = nbt.getInt(TheCurseOfTheSphinx.MOD_ID + "normalId");
        hardId = nbt.getInt(TheCurseOfTheSphinx.MOD_ID + "hardId");
        cursedId = nbt.getInt(TheCurseOfTheSphinx.MOD_ID + "cursedId");
    }

    @Override
    @Nonnull
    public CompoundNBT write(@Nonnull CompoundNBT compound)
    {
        compound.putInt(TheCurseOfTheSphinx.MOD_ID + "easyId", easyId);
        compound.putInt(TheCurseOfTheSphinx.MOD_ID + "normalId", normalId);
        compound.putInt(TheCurseOfTheSphinx.MOD_ID + "hardId", hardId);
        compound.putInt(TheCurseOfTheSphinx.MOD_ID + "cursedId", cursedId);

        return compound;
    }

    public int IncrementId(int scarabKeyLevel){
        switch (scarabKeyLevel)
        {
            case 1:
                easyId++;
                markDirty();
                return easyId;
            case 2:
                normalId++;
                markDirty();
                return normalId;
            case 3:
                hardId++;
                markDirty();
                return hardId;
            case 4:
                cursedId++;
                markDirty();
                return cursedId;
            default:
                throw new IllegalArgumentException("Invalid scarab key level");
        }
    }
}
