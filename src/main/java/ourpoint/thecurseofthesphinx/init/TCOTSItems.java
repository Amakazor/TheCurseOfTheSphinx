package ourpoint.thecurseofthesphinx.init;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.armor.TCOTSArmorMaterial;
import ourpoint.thecurseofthesphinx.items.ScarabKeyItem;
import ourpoint.thecurseofthesphinx.items.TCOTSSpawnEggItem;
import ourpoint.thecurseofthesphinx.items.ToiletPaperItem;

public class TCOTSItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheCurseOfTheSphinx.MOD_ID);

    //Items
    public static final RegistryObject<TCOTSSpawnEggItem> MUMMY_SPAWN_EGG = ITEMS.register("mummy_spawn_egg",
            () -> new TCOTSSpawnEggItem(TCOTSEntityTypes.MUMMY_ENTITY, 0xA8966C, 0xAE1225,
                    new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<TCOTSSpawnEggItem> SCARAB_SPAWN_EGG = ITEMS.register("scarab_spawn_egg",
            () -> new TCOTSSpawnEggItem(TCOTSEntityTypes.SCARAB_ENTITY, 0xFFFFFF, 0x000000,
                    new Item.Properties().group(TheCurseOfTheSphinx.TAB)));

    public static final RegistryObject<Item> ANKH_PART = ITEMS.register("ankh_part",
            () -> new Item(new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<Item> ANKH = ITEMS.register("ankh",
            () -> new Item(new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<Item> BANDAGE = ITEMS.register("bandage",
            () -> new Item(new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<Item> SCARAB = ITEMS.register("scarab",
            () -> new Item(new Item.Properties().group(TheCurseOfTheSphinx.TAB)));

    public static final RegistryObject<Item> SCARAB_KEY_EASY = ITEMS.register("scarab_key_easy",
            () -> new ScarabKeyItem(new Item.Properties().group(TheCurseOfTheSphinx.TAB), 1));
    public static final RegistryObject<Item> SCARAB_KEY_NORMAL = ITEMS.register("scarab_key_normal",
            () -> new ScarabKeyItem(new Item.Properties().group(TheCurseOfTheSphinx.TAB), 2));
    public static final RegistryObject<Item> SCARAB_KEY_HARD = ITEMS.register("scarab_key_hard",
            () -> new ScarabKeyItem(new Item.Properties().group(TheCurseOfTheSphinx.TAB), 3));
    public static final RegistryObject<Item> SCARAB_KEY_CURSED = ITEMS.register("scarab_key_cursed",
            () -> new ScarabKeyItem(new Item.Properties().group(TheCurseOfTheSphinx.TAB), 4));

    public static final RegistryObject<Item> TOILET_PAPER_ITEM = ITEMS.register("toilet_paper",
            () -> new ToiletPaperItem(new Item.Properties().group(TheCurseOfTheSphinx.TAB)));

    //Block Items
        //Bandage Blocks
    public static final RegistryObject<Item> BANDAGE_BLOCK_ITEM = ITEMS.register("bandage_block",
            () -> new BlockItem(TCOTSBlocks.BANDAGE_BLOCK.get(), new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<Item> BANDAGE_BLOCK_WHITE_ITEM = ITEMS.register("bandage_block_white",
            () -> new BlockItem(TCOTSBlocks.BANDAGE_BLOCK_WHITE.get(), new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<Item> BANDAGE_BLOCK_ORANGE_ITEM = ITEMS.register("bandage_block_orange",
            () -> new BlockItem(TCOTSBlocks.BANDAGE_BLOCK_ORANGE.get(), new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<Item> BANDAGE_BLOCK_MAGENTA_ITEM = ITEMS.register("bandage_block_magenta",
            () -> new BlockItem(TCOTSBlocks.BANDAGE_BLOCK_MAGENTA.get(), new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<Item> BANDAGE_BLOCK_LIGHT_BLUE_ITEM = ITEMS.register("bandage_block_light_blue",
            () -> new BlockItem(TCOTSBlocks.BANDAGE_BLOCK_LIGHT_BLUE.get(), new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<Item> BANDAGE_BLOCK_YELLOW_ITEM = ITEMS.register("bandage_block_yellow",
            () -> new BlockItem(TCOTSBlocks.BANDAGE_BLOCK_YELLOW.get(), new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<Item> BANDAGE_BLOCK_LIME_ITEM = ITEMS.register("bandage_block_lime",
            () -> new BlockItem(TCOTSBlocks.BANDAGE_BLOCK_LIME.get(), new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<Item> BANDAGE_BLOCK_PINK_ITEM = ITEMS.register("bandage_block_pink",
            () -> new BlockItem(TCOTSBlocks.BANDAGE_BLOCK_PINK.get(), new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<Item> BANDAGE_BLOCK_GRAY_ITEM = ITEMS.register("bandage_block_gray",
            () -> new BlockItem(TCOTSBlocks.BANDAGE_BLOCK_GRAY.get(), new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<Item> BANDAGE_BLOCK_LIGHT_GRAY_ITEM = ITEMS.register("bandage_block_light_gray",
            () -> new BlockItem(TCOTSBlocks.BANDAGE_BLOCK_LIGHT_GRAY.get(), new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<Item> BANDAGE_BLOCK_CYAN_ITEM = ITEMS.register("bandage_block_cyan",
            () -> new BlockItem(TCOTSBlocks.BANDAGE_BLOCK_CYAN.get(), new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<Item> BANDAGE_BLOCK_PURPLE_ITEM = ITEMS.register("bandage_block_purple",
            () -> new BlockItem(TCOTSBlocks.BANDAGE_BLOCK_PURPLE.get(), new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<Item> BANDAGE_BLOCK_BLUE_ITEM = ITEMS.register("bandage_block_blue",
            () -> new BlockItem(TCOTSBlocks.BANDAGE_BLOCK_BLUE.get(), new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<Item> BANDAGE_BLOCK_BROWN_ITEM = ITEMS.register("bandage_block_brown",
            () -> new BlockItem(TCOTSBlocks.BANDAGE_BLOCK_BROWN.get(), new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<Item> BANDAGE_BLOCK_GREEN_ITEM = ITEMS.register("bandage_block_green",
            () -> new BlockItem(TCOTSBlocks.BANDAGE_BLOCK_GREEN.get(), new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<Item> BANDAGE_BLOCK_RED_ITEM = ITEMS.register("bandage_block_red",
            () -> new BlockItem(TCOTSBlocks.BANDAGE_BLOCK_RED.get(), new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<Item> BANDAGE_BLOCK_BLACK_ITEM = ITEMS.register("bandage_block_black",
            () -> new BlockItem(TCOTSBlocks.BANDAGE_BLOCK_BLACK.get(), new Item.Properties().group(TheCurseOfTheSphinx.TAB)));

    public static final RegistryObject<Item> TOILET_PAPER_BLOCK_ITEM = ITEMS.register("toilet_paper_block",
            () -> new BlockItem(TCOTSBlocks.TOILET_PAPER_BLOCK.get(), new Item.Properties().group(TheCurseOfTheSphinx.TAB)));

    //Tools

    //Weapons

    //Armor
    public static final RegistryObject<ArmorItem> BANDAGE_HELMET = ITEMS.register("bandage_helmet",
            () -> new ArmorItem(TCOTSArmorMaterial.BANDAGE, EquipmentSlotType.HEAD, new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<ArmorItem> BANDAGE_CHESTPLATE = ITEMS.register("bandage_chestplate",
            () -> new ArmorItem(TCOTSArmorMaterial.BANDAGE, EquipmentSlotType.CHEST, new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<ArmorItem> BANDAGE_LEGGINGS = ITEMS.register("bandage_leggings",
            () -> new ArmorItem(TCOTSArmorMaterial.BANDAGE, EquipmentSlotType.LEGS, new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
    public static final RegistryObject<ArmorItem> BANDAGE_BOOTS = ITEMS.register("bandage_boots",
            () -> new ArmorItem(TCOTSArmorMaterial.BANDAGE, EquipmentSlotType.FEET, new Item.Properties().group(TheCurseOfTheSphinx.TAB)));
}
