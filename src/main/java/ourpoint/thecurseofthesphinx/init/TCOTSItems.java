package ourpoint.thecurseofthesphinx.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.items.TCOTSSpawnEggItem;

public class TCOTSItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheCurseOfTheSphinx.MOD_ID);

    //Items
    public static final RegistryObject<TCOTSSpawnEggItem> MUMMY_SPAWN_EGG = ITEMS.register("mummy_spawn_egg",
            () -> new TCOTSSpawnEggItem(TCOTSEntityTypes.MUMMY_ENTITY, 0xA8966C, 0xAE1225,
                    new Item.Properties().group(ItemGroup.MATERIALS).maxStackSize(16)));

    //Block Items

    //Tools

    //Weapons

    //Armor
}
