package ourpoint.thecurseofthesphinx.entity.item;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import ourpoint.thecurseofthesphinx.init.TCOTSBlocks;
import ourpoint.thecurseofthesphinx.init.TCOTSEntityTypes;
import ourpoint.thecurseofthesphinx.init.TCOTSItems;

import javax.annotation.Nonnull;

public class ToiletPaperEntity extends ProjectileItemEntity
{
    public ToiletPaperEntity(EntityType<? extends ToiletPaperEntity> type, World world) {
        super(type, world);
        this.noClip = false;
    }

    public ToiletPaperEntity(World worldIn, LivingEntity throwerIn)
    {
        super(TCOTSEntityTypes.TOILET_PAPER_ENTITY.get(), throwerIn, worldIn);
        this.noClip = false;
    }

    @OnlyIn (Dist.CLIENT)
    public ToiletPaperEntity(World worldIn, double x, double y, double z)
    {
        super(TCOTSEntityTypes.TOILET_PAPER_ENTITY.get(), x, y, z, worldIn);
        this.noClip = false;
    }

    @Override
    @Nonnull
    protected Item getDefaultItem()
    {
        return TCOTSItems.TOILET_PAPER_ITEM.get();
    }

    @Override
    @Nonnull
    public IPacket<?> createSpawnPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }



//    @Override
//    protected void onImpact(@Nonnull RayTraceResult result)
//    {
//        super.onImpact(result);
//        Entity entity = this.func_234616_v_();
////        if (!this.world.isRemote && this.isAlive())
////        {
////            assert entity != null;
////            TheCurseOfTheSphinx.LOGGER.debug(world.getBlockState(entity.getPosition()));
////
////            this.getEntityWorld().setBlockState(new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ()), TCOTSBlocks.TOILET_PAPER_BLOCK.get().getDefaultState());
////        }
//
//        this.setVelocity(this.getMotion().getX(), 0.0D, this.getMotion().getZ());
//        TheCurseOfTheSphinx.LOGGER.debug(result.hitInfo);
//        TheCurseOfTheSphinx.LOGGER.debug(result.getHitVec());
//        TheCurseOfTheSphinx.LOGGER.debug(result.getType());
//
//        //this.remove();
//    }

    //protected void func_230299_a_;

    @Override
    protected void func_230299_a_(@Nonnull BlockRayTraceResult result)
    {
        Direction direction =  result.getFace();

        this.setVelocity(
                (direction == Direction.EAST || direction == Direction.WEST) ? this.getMotion().getX() * -0.2D : this.getMotion().getX() * 0.75,
                (direction == Direction.UP || direction == Direction.DOWN) ? this.getMotion().getY() * -0.2D : this.getMotion().getY() * 0.75,
                (direction == Direction.NORTH || direction == Direction.SOUTH) ? this.getMotion().getZ() * -0.2D : this.getMotion().getZ() * 0.75);
        if (this.getMotion().length() < 0.05D)
        {
            if (!this.world.isRemote && this.isAlive())
            {
                if (this.getEntityWorld().getBlockState(this.getPosition()).getBlock() == Blocks.AIR)
                {
                    this.getEntityWorld().setBlockState(this.getPosition(), TCOTSBlocks.TOILET_PAPER_BLOCK.get().getDefaultState());
                }
                else
                {
                    this.entityDropItem(new ItemStack(TCOTSItems.TOILET_PAPER_ITEM.get(), 1));
                }
                this.remove();
            }
        }
    }
}
