package ourpoint.thecurseofthesphinx.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class TCOTSTags
{
    public static void init ()
    {
        TCOTSTags.Blocks.init();
        TCOTSTags.Items.init();
    }

    public static class Blocks
    {
        private static void init() {}

        public static final Tags.IOptionalNamedTag<Block> BANDAGE_BLOCK = tag("bandage_block");

        private static Tags.IOptionalNamedTag<Block> tag(String name)
        {
            return BlockTags.createOptional(new ResourceLocation("thecurseofthesphinx", name));
        }
    }

    public static class Items
    {
        private static void init() {}

        public static final Tags.IOptionalNamedTag<Item> BANDAGE_BLOCK = tag("bandage_block");

        private static Tags.IOptionalNamedTag<Item> tag(String name)
        {
            return ItemTags.createOptional(new ResourceLocation("thecurseofthesphinx", name));
        }
    }
}
