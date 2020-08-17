package ourpoint.thecurseofthesphinx.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import ourpoint.thecurseofthesphinx.entity.item.ToiletPaperEntity;

import javax.annotation.Nonnull;

public class ToiletPaperItem extends Item
{

    public ToiletPaperItem(Properties properties)
    {
        super(properties);
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull PlayerEntity playerIn, @Nonnull Hand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        worldIn.playSound(playerIn, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isRemote)
        {
            ToiletPaperEntity toiletPaperEntity = new ToiletPaperEntity(worldIn, playerIn);
            toiletPaperEntity.setItem(itemstack);
            toiletPaperEntity.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.6F, 1.0F);
            worldIn.addEntity(toiletPaperEntity);
        }

        playerIn.addStat(Stats.ITEM_USED.get(this));
        if (!playerIn.abilities.isCreativeMode)
        {
            itemstack.shrink(1);
        }

        return ActionResult.func_233538_a_(itemstack, worldIn.isRemote());
    }
}
