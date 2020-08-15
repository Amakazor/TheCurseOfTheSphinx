package ourpoint.thecurseofthesphinx.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ScarabKeyItem extends Item
{
    protected final int keyLevel;

    public ScarabKeyItem(Properties properties, int keyLevelIn)
    {
        super(properties);
        this.keyLevel = keyLevelIn;
    }

    public int getKeyLevel()
    {
        return keyLevel;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn,@Nonnull List<ITextComponent> tooltip,@Nonnull ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (this.keyLevel > 0 && this.keyLevel < 5)
        {
            tooltip.add((new TranslationTextComponent(TheCurseOfTheSphinx.MOD_ID + ".scarab_key.level." + this.getKeyLevel())).mergeStyle(TextFormatting.GOLD));
        }
    }

    @Override
    @Nonnull
    public ActionResultType onItemUse(@Nonnull ItemUseContext context)
    {
        //TODO: implement when portal keyhole is added
        return ActionResultType.PASS;
    }
}
