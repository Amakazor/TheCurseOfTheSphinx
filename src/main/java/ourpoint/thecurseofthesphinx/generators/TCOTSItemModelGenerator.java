package ourpoint.thecurseofthesphinx.generators;

import com.google.common.collect.ImmutableList;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.init.TCOTSItems;
import ourpoint.thecurseofthesphinx.items.ScarabKeyItem;

import java.util.Objects;

public class TCOTSItemModelGenerator extends ItemModelProvider
{

    public TCOTSItemModelGenerator(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper)
    {
        super(generator, modId, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        registerArmor();
        registerScarabKeys();
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

    private void registerScarabKeys()
    {
        ImmutableList<String> scarabKeyItems = ImmutableList.of
        (
            Objects.requireNonNull(TCOTSItems.SCARAB_KEY_EASY.get().getRegistryName()).toString(),
            Objects.requireNonNull(TCOTSItems.SCARAB_KEY_NORMAL.get().getRegistryName()).toString(),
            Objects.requireNonNull(TCOTSItems.SCARAB_KEY_HARD.get().getRegistryName()).toString(),
            Objects.requireNonNull(TCOTSItems.SCARAB_KEY_CURSED.get().getRegistryName()).toString()
        );

        for (String scarabKeyItem : scarabKeyItems)
        {
            singleTexture(scarabKeyItem,
                    new ResourceLocation("item/generated"),
                    "layer0",
                    new ResourceLocation(new StringBuilder(scarabKeyItem).insert(scarabKeyItem.indexOf(":")+1,"item/").toString()));
        }
    }
}
