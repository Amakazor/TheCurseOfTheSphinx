package ourpoint.thecurseofthesphinx.generators;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;

public class TCOTSItemModelGenerator extends ItemModelProvider
{

    public TCOTSItemModelGenerator(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper)
    {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        registerArmor();
    }

    private void registerArmor()
    {
        singleTexture("bandage_helmet",
                new ResourceLocation("item/generated"),
                "layer0",
                new ResourceLocation("thecurseofthesphinx", "item/bandage_helmet"));
        singleTexture("bandage_chestplate",
                new ResourceLocation("item/generated"),
                "layer0",
                new ResourceLocation("thecurseofthesphinx", "item/bandage_chestplate"));
        singleTexture("bandage_leggings",
                new ResourceLocation("item/generated"),
                "layer0",
                new ResourceLocation("thecurseofthesphinx", "item/bandage_leggings"));
        singleTexture("bandage_boots",
                new ResourceLocation("item/generated"),
                "layer0",
                new ResourceLocation("thecurseofthesphinx", "item/bandage_boots"));
    }
}
