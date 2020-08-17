package ourpoint.thecurseofthesphinx.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;

public class TCOTSBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TheCurseOfTheSphinx.MOD_ID);

    //Bandage blocks
    public static final RegistryObject<Block> BANDAGE_BLOCK = BLOCKS.register("bandage_block",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOL).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
    public static final RegistryObject<Block> BANDAGE_BLOCK_WHITE = BLOCKS.register("bandage_block_white",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.SNOW).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
    public static final RegistryObject<Block> BANDAGE_BLOCK_ORANGE = BLOCKS.register("bandage_block_orange",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.ADOBE).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
    public static final RegistryObject<Block> BANDAGE_BLOCK_MAGENTA = BLOCKS.register("bandage_block_magenta",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.MAGENTA).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
    public static final RegistryObject<Block> BANDAGE_BLOCK_LIGHT_BLUE = BLOCKS.register("bandage_block_light_blue",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.LIGHT_BLUE).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
    public static final RegistryObject<Block> BANDAGE_BLOCK_YELLOW = BLOCKS.register("bandage_block_yellow",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.YELLOW).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
    public static final RegistryObject<Block> BANDAGE_BLOCK_LIME = BLOCKS.register("bandage_block_lime",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.LIME).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
    public static final RegistryObject<Block> BANDAGE_BLOCK_PINK = BLOCKS.register("bandage_block_pink",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.PINK).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
    public static final RegistryObject<Block> BANDAGE_BLOCK_GRAY = BLOCKS.register("bandage_block_gray",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.GRAY).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
    public static final RegistryObject<Block> BANDAGE_BLOCK_LIGHT_GRAY = BLOCKS.register("bandage_block_light_gray",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.LIGHT_GRAY).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
    public static final RegistryObject<Block> BANDAGE_BLOCK_CYAN = BLOCKS.register("bandage_block_cyan",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.CYAN).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
    public static final RegistryObject<Block> BANDAGE_BLOCK_PURPLE = BLOCKS.register("bandage_block_purple",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.PURPLE).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
    public static final RegistryObject<Block> BANDAGE_BLOCK_BLUE = BLOCKS.register("bandage_block_blue",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.BLUE).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
    public static final RegistryObject<Block> BANDAGE_BLOCK_BROWN = BLOCKS.register("bandage_block_brown",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.BROWN).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
    public static final RegistryObject<Block> BANDAGE_BLOCK_GREEN = BLOCKS.register("bandage_block_green",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.GREEN).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
    public static final RegistryObject<Block> BANDAGE_BLOCK_RED = BLOCKS.register("bandage_block_red",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.RED).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
    public static final RegistryObject<Block> BANDAGE_BLOCK_BLACK = BLOCKS.register("bandage_block_black",
            () -> new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.BLACK).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));

    public static final RegistryObject<Block> TOILET_PAPER_BLOCK = BLOCKS.register("toilet_paper_block",
            () -> new Block((AbstractBlock.Properties.create(Material.WOOL, MaterialColor.SNOW).hardnessAndResistance(0.8F).sound(SoundType.CLOTH))));
}
