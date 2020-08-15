package ourpoint.thecurseofthesphinx.generators;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import ourpoint.thecurseofthesphinx.init.TCOTSBlocks;

import java.util.ArrayList;
import java.util.List;

public class TCOTSBlockstateGenerator extends BlockStateProvider
{

    public TCOTSBlockstateGenerator(DataGenerator gen, String modId, ExistingFileHelper exFileHelper)
    {
        super(gen, modId, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        generateBandageBlocks();
    }
    
    private void generateBandageBlocks()
    {
        List<RegistryObject<Block>> bandage_blocks = new ArrayList<>();

        bandage_blocks.add(TCOTSBlocks.BANDAGE_BLOCK);
        bandage_blocks.add(TCOTSBlocks.BANDAGE_BLOCK_WHITE);
        bandage_blocks.add(TCOTSBlocks.BANDAGE_BLOCK_ORANGE);
        bandage_blocks.add(TCOTSBlocks.BANDAGE_BLOCK_MAGENTA);
        bandage_blocks.add(TCOTSBlocks.BANDAGE_BLOCK_LIGHT_BLUE);
        bandage_blocks.add(TCOTSBlocks.BANDAGE_BLOCK_YELLOW);
        bandage_blocks.add(TCOTSBlocks.BANDAGE_BLOCK_LIME);
        bandage_blocks.add(TCOTSBlocks.BANDAGE_BLOCK_PINK);
        bandage_blocks.add(TCOTSBlocks.BANDAGE_BLOCK_GRAY);
        bandage_blocks.add(TCOTSBlocks.BANDAGE_BLOCK_LIGHT_GRAY);
        bandage_blocks.add(TCOTSBlocks.BANDAGE_BLOCK_CYAN);
        bandage_blocks.add(TCOTSBlocks.BANDAGE_BLOCK_PURPLE);
        bandage_blocks.add(TCOTSBlocks.BANDAGE_BLOCK_BLUE);
        bandage_blocks.add(TCOTSBlocks.BANDAGE_BLOCK_BROWN);
        bandage_blocks.add(TCOTSBlocks.BANDAGE_BLOCK_GREEN);
        bandage_blocks.add(TCOTSBlocks.BANDAGE_BLOCK_RED);
        bandage_blocks.add(TCOTSBlocks.BANDAGE_BLOCK_BLACK);

        for (RegistryObject<Block> block : bandage_blocks)
        {
            simpleBlock(block.get());
            simpleBlockItem(block.get(), cubeAll(block.get()));
        }
    }
}
