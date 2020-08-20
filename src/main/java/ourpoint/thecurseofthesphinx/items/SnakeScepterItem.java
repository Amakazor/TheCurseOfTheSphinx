package ourpoint.thecurseofthesphinx.items;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.entity.SnakeScepterSnakeEntity;

import javax.annotation.Nonnull;

public class SnakeScepterItem extends Item
{
    public static final int COOLDOWN_TIME = 20;

    public SnakeScepterItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public void onUse(@Nonnull World worldIn, @Nonnull LivingEntity livingEntityIn, @Nonnull ItemStack stack, int count)
    {
        if (!worldIn.isRemote)
        {
            double distance = 20.0D;
            Vector3d startVector = livingEntityIn.getEyePosition(1.0F);
            Vector3d lookVector = livingEntityIn.getLook(1.0F);
            Vector3d endVector = startVector.add(lookVector.x * distance, lookVector.y * distance, lookVector.z * distance);
            AxisAlignedBB axisalignedbb = livingEntityIn.getBoundingBox().expand(lookVector.scale(distance)).grow(1.0D, 1.0D, 1.0D);

            EntityRayTraceResult entityraytraceresult = ProjectileHelper.rayTraceEntities(livingEntityIn, startVector, endVector, axisalignedbb, entity -> entity.getClassification(false) != EntityClassification.MISC, distance*distance);
            BlockRayTraceResult blockRayTraceResult = worldIn.rayTraceBlocks(new RayTraceContext(startVector, endVector, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, null));

            BlockPos castPosition = null;

            Vector3d casterVector = livingEntityIn.getPositionVec();

            if (entityraytraceresult != null && entityraytraceresult.getEntity().getDistanceSq(casterVector) < blockRayTraceResult.getPos().distanceSq(casterVector.getX(), casterVector.y, casterVector.z, true))
            {
                castPosition = entityraytraceresult.getEntity().getPosition();
            }
            else if(blockRayTraceResult.getType() != RayTraceResult.Type.MISS)
            {
                castPosition = blockRayTraceResult.getPos();
            }

            this.summonSnake(worldIn, livingEntityIn, castPosition, stack);

            super.onUse(worldIn, livingEntityIn, stack, count);
        }
    }

    public void summonSnake(@Nonnull World worldIn, @Nonnull LivingEntity livingEntityIn, BlockPos castPosition, @Nonnull ItemStack stack)
    {
        CompoundNBT COOLDOWN = stack.getOrCreateChildTag(TheCurseOfTheSphinx.MOD_ID + "cooldown");

        if (castPosition != null && ((worldIn.getBlockState(castPosition.down()) != Blocks.AIR.getDefaultState() && worldIn.getBlockState(castPosition) == Blocks.AIR.getDefaultState()) || (worldIn.getBlockState(castPosition) != Blocks.AIR.getDefaultState() && worldIn.getBlockState(castPosition.up()) == Blocks.AIR.getDefaultState())))
        {
            TheCurseOfTheSphinx.LOGGER.debug(COOLDOWN.getInt(TheCurseOfTheSphinx.MOD_ID + "cooldown"));

            if (livingEntityIn instanceof PlayerEntity || COOLDOWN.getInt(TheCurseOfTheSphinx.MOD_ID + "cooldown") == 0)
            {
                if (!(livingEntityIn instanceof PlayerEntity))
                {
                    COOLDOWN.putInt(TheCurseOfTheSphinx.MOD_ID + "cooldown", COOLDOWN_TIME);
                }
                else
                {
                    ((PlayerEntity) livingEntityIn).getCooldownTracker().setCooldown(this, COOLDOWN_TIME);
                }

                worldIn.addEntity(new SnakeScepterSnakeEntity(worldIn, castPosition, 10, livingEntityIn));
            }
        }
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull PlayerEntity playerIn, @Nonnull Hand handIn)
    {
        this.onUse(worldIn, playerIn, playerIn.getHeldItem(handIn), playerIn.getHeldItem(handIn).getCount());
        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }

    @Override
    public void inventoryTick(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull Entity entityIn, int itemSlot, boolean isSelected)
    {
        this.decrementCooldown(stack);
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    public void decrementCooldown(ItemStack stack)
    {
        CompoundNBT COOLDOWN = stack.getOrCreateChildTag(TheCurseOfTheSphinx.MOD_ID + "cooldown");
        int cooldown = COOLDOWN.getInt(TheCurseOfTheSphinx.MOD_ID + "cooldown");
        if (cooldown > 0)
        {
            COOLDOWN.putInt(TheCurseOfTheSphinx.MOD_ID + "cooldown", --cooldown);
        }
    }
}
