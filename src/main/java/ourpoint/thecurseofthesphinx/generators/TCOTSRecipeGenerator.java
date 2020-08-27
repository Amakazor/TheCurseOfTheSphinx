package ourpoint.thecurseofthesphinx.generators;

import net.minecraft.block.Block;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.RegistryObject;
import ourpoint.thecurseofthesphinx.init.TCOTSBlocks;
import ourpoint.thecurseofthesphinx.init.TCOTSItems;
import ourpoint.thecurseofthesphinx.init.TCOTSTags;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class TCOTSRecipeGenerator extends RecipeProvider
{

    public TCOTSRecipeGenerator(DataGenerator generatorIn)
    {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(@Nonnull Consumer<IFinishedRecipe> consumer)
    {
        registerBandageRecipes(consumer);
        registerArmorRecipes(consumer);
    }

    private void registerBandageRecipes(Consumer<IFinishedRecipe> consumer)
    {
        class BandageDyePair
        {
            public final RegistryObject<Block> block;
            public final ITag<Item> dye;
            
            BandageDyePair(RegistryObject<Block> blockIn, ITag<Item> dyeIn)
            {
                this.block = blockIn;
                this.dye = dyeIn;
            }
        }

        List<BandageDyePair> bandage_blocks = new ArrayList<>();

        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_WHITE, Tags.Items.DYES_WHITE));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_ORANGE, Tags.Items.DYES_ORANGE));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_MAGENTA, Tags.Items.DYES_MAGENTA));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_LIGHT_BLUE, Tags.Items.DYES_LIGHT_BLUE));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_YELLOW, Tags.Items.DYES_YELLOW));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_LIME, Tags.Items.DYES_LIME));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_PINK, Tags.Items.DYES_PINK));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_GRAY, Tags.Items.DYES_GRAY));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_LIGHT_GRAY, Tags.Items.DYES_LIGHT_GRAY));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_CYAN, Tags.Items.DYES_CYAN));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_PURPLE, Tags.Items.DYES_PURPLE));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_BLUE, Tags.Items.DYES_BLUE));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_BROWN, Tags.Items.DYES_BROWN));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_GREEN, Tags.Items.DYES_GREEN));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_RED, Tags.Items.DYES_RED));
        bandage_blocks.add(new BandageDyePair(TCOTSBlocks.BANDAGE_BLOCK_BLACK, Tags.Items.DYES_BLACK));

        for (BandageDyePair bandageDyePair:bandage_blocks)
        {
            ShapelessRecipeBuilder.shapelessRecipe(bandageDyePair.block.get())
                    .addIngredient(Ingredient.fromTag(TCOTSTags.Items.BANDAGE_BLOCK))
                    .addIngredient(Ingredient.fromTag(bandageDyePair.dye))
                    .addCriterion("has_bandage_block", hasItem(TCOTSBlocks.BANDAGE_BLOCK.get()))
                    .build(consumer, Objects.requireNonNull(bandageDyePair.block.get().getRegistryName()).toString().concat("_single"));

            ShapedRecipeBuilder.shapedRecipe(bandageDyePair.block.get(), 8)
                    .patternLine("xxx")
                    .patternLine("xdx")
                    .patternLine("xxx")
                    .key('x', TCOTSTags.Items.BANDAGE_BLOCK)
                    .key('d', bandageDyePair.dye)
                    .addCriterion("has_bandage_block", hasItem(TCOTSBlocks.BANDAGE_BLOCK.get()))
                    .build(consumer, Objects.requireNonNull(bandageDyePair.block.get().getRegistryName()).toString().concat("_multiple"));
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
