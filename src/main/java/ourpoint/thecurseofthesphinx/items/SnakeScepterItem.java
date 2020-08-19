package ourpoint.thecurseofthesphinx.items;

import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
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
    public SnakeScepterItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public void onUse(@Nonnull World worldIn, @Nonnull LivingEntity livingEntityIn, @Nonnull ItemStack stack, int count)
    {
        if (!worldIn.isRemote)
        {
            CompoundNBT COOLDOWN = stack.getOrCreateChildTag(TheCurseOfTheSphinx.MOD_ID + "cooldown");
            int cooldown = COOLDOWN.getInt(TheCurseOfTheSphinx.MOD_ID + "cooldown");

            if (cooldown == 0)
            {
                COOLDOWN.putInt(TheCurseOfTheSphinx.MOD_ID + "cooldown", 40);

                double d0 = 20.0D;
                Vector3d vector3d = livingEntityIn.getEyePosition(1.0F);
                Vector3d vector3d1 = livingEntityIn.getLook(1.0F);
                Vector3d vector3d2 = vector3d.add(vector3d1.x * d0, vector3d1.y * d0, vector3d1.z * d0);
                AxisAlignedBB axisalignedbb = livingEntityIn.getBoundingBox().expand(vector3d1.scale(d0)).grow(1.0D, 1.0D, 1.0D);
                EntityRayTraceResult entityraytraceresult = ProjectileHelper.rayTraceEntities(livingEntityIn, vector3d, vector3d2, axisalignedbb, null, d0*d0);

                BlockPos blockPos;

                TheCurseOfTheSphinx.LOGGER.debug("USED");
                if (entityraytraceresult != null)
                {
                    blockPos = entityraytraceresult.getEntity().getPosition();
                }
                else
                {
                    blockPos = worldIn.rayTraceBlocks(new RayTraceContext(vector3d, vector3d2,
                            RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, null)).getPos();
                }

                TheCurseOfTheSphinx.LOGGER.debug(blockPos);

                if (worldIn.getBlockState(blockPos) == Blocks.AIR.getDefaultState() || worldIn.getBlockState(blockPos.up()) == Blocks.AIR.getDefaultState())
                {
                    Minecraft.getInstance().particles.addParticle(ParticleTypes.CRIT, blockPos.getX()+0.5D, blockPos.getY(), blockPos.getZ()+0.5D, 0.0D, 0.2D, 0.0D);
                    worldIn.addEntity(new SnakeScepterSnakeEntity(worldIn, blockPos, 10, livingEntityIn));
                }

                super.onUse(worldIn, livingEntityIn, stack, count);
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
        CompoundNBT COOLDOWN = stack.getOrCreateChildTag(TheCurseOfTheSphinx.MOD_ID + "cooldown");
        int cooldown = COOLDOWN.getInt(TheCurseOfTheSphinx.MOD_ID + "cooldown");
        if (cooldown > 0)
        {
            COOLDOWN.putInt(TheCurseOfTheSphinx.MOD_ID + "cooldown", --cooldown);
        }

        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
    }
}
