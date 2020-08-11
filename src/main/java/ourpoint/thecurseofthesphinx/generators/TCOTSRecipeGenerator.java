package ourpoint.thecurseofthesphinx.generators;

import net.minecraft.block.Block;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.TagRegistry;
import net.minecraftforge.fml.RegistryObject;
import ourpoint.thecurseofthesphinx.init.TCOTSBlocks;
import ourpoint.thecurseofthesphinx.init.TCOTSItems;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TCOTSRecipeGenerator extends RecipeProvider
{

    public TCOTSRecipeGenerator(DataGenerator generatorIn)
    {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer)
    {
        registerBandageRecipes(consumer);
        registerArmorRecipes(consumer);
    }

    private void registerBandageRecipes(Consumer<IFinishedRecipe> consumer)
    {
        class BandageDyePair
        {
            public final RegistryObject<Block> block;
            public final String dye;
            
            BandageDyePair(RegistryObject<Block> blockIn, String dyeIn)
            {
                this.block = blockIn;
                this.dye = dyeIn;
            }
        }
        
        TagRegistry<Item> collection = new TagRegistry<>();
        
        List<BandageDyePair> bandage_blocks = new ArrayList<>();

        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_WHITE, "forge:dyes/white"));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_ORANGE, "forge:dyes/orange"));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_MAGENTA, "forge:dyes/magenta"));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_LIGHT_BLUE, "forge:dyes/light_blue"));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_YELLOW, "forge:dyes/yellow"));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_LIME, "forge:dyes/lime"));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_PINK, "forge:dyes/pink"));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_GRAY, "forge:dyes/gray"));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_LIGHT_GRAY, "forge:dyes/light_gray"));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_CYAN, "forge:dyes/cyan"));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_PURPLE, "forge:dyes/purple"));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_BLUE, "forge:dyes/blue"));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_BROWN, "forge:dyes/brown"));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_GREEN, "forge:dyes/green"));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_RED, "forge:dyes/red"));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_BLACK, "forge:dyes/black"));

        for (BandageDyePair bandageDyePair:bandage_blocks)
        {
            ShapelessRecipeBuilder.shapelessRecipe(bandageDyePair.block.get())
                    .addIngredient(Ingredient.fromTag(collection.func_232937_a_("thecurseofthesphinx:bandage_block")))
                    .addIngredient(Ingredient.fromTag(collection.func_232937_a_(bandageDyePair.dye)))
                    .addCriterion("has_bandage_block", hasItem(TCOTSBlocks.BANDAGE_BLOCK.get()))
                    .build(consumer, bandageDyePair.block.get().getRegistryName().toString().concat("_single"));

            ShapedRecipeBuilder.shapedRecipe(bandageDyePair.block.get(), 8)
                    .patternLine("xxx")
                    .patternLine("xdx")
                    .patternLine("xxx")
                    .key('x', collection.func_232937_a_("thecurseofthesphinx:bandage_block"))
                    .key('d', collection.func_232937_a_(bandageDyePair.dye))
                    .addCriterion("has_bandage_block", hasItem(TCOTSBlocks.BANDAGE_BLOCK.get()))
                    .build(consumer, bandageDyePair.block.get().getRegistryName().toString().concat("_multiple"));
        }

        ShapedRecipeBuilder.shapedRecipe((TCOTSBlocks.BANDAGE_BLOCK.get()))
                .patternLine("xx")
                .patternLine("xx")
                .key('x', TCOTSItems.BANDAGE.get())
                .addCriterion("has_bandage", hasItem(TCOTSItems.BANDAGE.get()))
                .build(consumer);
    }

    private void registerArmorRecipes(Consumer<IFinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shapedRecipe(TCOTSItems.BANDAGE_HELMET.get())
                .patternLine("xxx")
                .patternLine("x x")
                .key('x', TCOTSItems.BANDAGE.get())
                .addCriterion("has_bandage", hasItem(TCOTSItems.BANDAGE.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(TCOTSItems.BANDAGE_BOOTS.get())
                .patternLine("x x")
                .patternLine("x x")
                .key('x', TCOTSItems.BANDAGE.get())
                .addCriterion("has_bandage", hasItem(TCOTSItems.BANDAGE.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(TCOTSItems.BANDAGE_LEGGINGS.get())
                .patternLine("xxx")
                .patternLine("x x")
                .patternLine("x x")
                .key('x', TCOTSItems.BANDAGE.get())
                .addCriterion("has_bandage", hasItem(TCOTSItems.BANDAGE.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(TCOTSItems.BANDAGE_CHESTPLATE.get())
                .patternLine("x x")
                .patternLine("xxx")
                .patternLine("xxx")
                .key('x', TCOTSItems.BANDAGE.get())
                .addCriterion("has_bandage", hasItem(TCOTSItems.BANDAGE.get()))
                .build(consumer);
    }
}
