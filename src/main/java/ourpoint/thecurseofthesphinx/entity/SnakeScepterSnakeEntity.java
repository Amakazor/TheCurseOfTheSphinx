package ourpoint.thecurseofthesphinx.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import ourpoint.thecurseofthesphinx.init.TCOTSEntityTypes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class SnakeScepterSnakeEntity extends Entity
{
    private static final DataParameter<Integer> TICKS_TO_LIVE = EntityDataManager.createKey(SnakeScepterSnakeEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> WARMUP_TICKS = EntityDataManager.createKey(SnakeScepterSnakeEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> IS_VISIBLE = EntityDataManager.createKey(SnakeScepterSnakeEntity.class, DataSerializers.BOOLEAN);

    private LivingEntity caster;
    private UUID casterUuid;

    public SnakeScepterSnakeEntity(EntityType<?> entityTypeIn, World worldIn)
    {
        super(entityTypeIn, worldIn);
    }

    public SnakeScepterSnakeEntity(World worldIn, BlockPos blockPos, int warmup, LivingEntity casterIn)
    {
        this(TCOTSEntityTypes.SNAKE_SCEPTER_SNAKE_ENTITY.get(), worldIn);

        this.setCaster(casterIn);
        this.setPosition(blockPos.getX()+0.5D, blockPos.getY()-3.0D, blockPos.getZ()+0.5D);

        this.setWarmupTicks(warmup);
        this.setTicksToLive(16);
    }

    public void setCaster(@Nullable LivingEntity caster)
    {
        this.caster = caster;
        this.casterUuid = caster == null ? null : caster.getUniqueID();
    }

    @Nullable
    public LivingEntity getCaster()
    {
        if (this.caster == null && this.casterUuid != null && this.world instanceof ServerWorld)
        {
            Entity entity = ((ServerWorld)this.world).getEntityByUuid(this.casterUuid);
            if (entity instanceof LivingEntity)
            {
                this.caster = (LivingEntity)entity;
            }
        }

        return this.caster;
    }

    @Override
    protected void registerData()
    {
        this.dataManager.register(IS_VISIBLE, false);
        this.dataManager.register(TICKS_TO_LIVE, 0);
        this.dataManager.register(WARMUP_TICKS, 0);
    }

    public void setTicksToLive(int ticksToLive)
    {
        this.dataManager.set(TICKS_TO_LIVE, ticksToLive);
    }

    public int getTicksToLive()
    {
        return this.dataManager.get(TICKS_TO_LIVE);
    }

    public void setWarmupTicks(int warmupTicks)
    {
        this.dataManager.set(WARMUP_TICKS, warmupTicks);
    }

    public int getWarmupTicks()
    {
        return this.dataManager.get(WARMUP_TICKS);
    }

    public void setVisible(boolean visible)
    {
        this.dataManager.set(IS_VISIBLE, visible);
    }

    public boolean getVisible()
    {
        return this.dataManager.get(IS_VISIBLE);
    }

    @Override
    protected void readAdditional(@Nonnull CompoundNBT compound) {}

    @Override
    protected void writeAdditional(@Nonnull CompoundNBT compound) {}

    @Override
    @Nonnull
    public IPacket<?> createSpawnPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick()
    {
        super.tick();

        int ticksToLive = getTicksToLive();
        int warmupTicks = getWarmupTicks();

        if (warmupTicks <= 0)
        {
            if (!this.getVisible())
            {
                this.setVisible(true);
            }

            this.setPosition(this.getPosX(), ticksToLive <= 8 ? this.getPosY() + 0.375D * -1.0D : this.getPosY() + 0.375D * 1.0D, this.getPosZ());

            for(LivingEntity livingEntity : this.world.getEntitiesWithinAABB(LivingEntity.class, this.getBoundingBox().grow(0.2D)))
            {
                if (ticksToLive > 8)
                {
                    this.damageEntity(livingEntity);
                }
            }

            this.setTicksToLive(ticksToLive-1);

            if (ticksToLive <= 0)
            {
                this.remove();
            }
        }
        else
        {
            this.setWarmupTicks(warmupTicks-1);
        }
    }

    private void damageEntity(LivingEntity target)
    {
        LivingEntity caster = this.getCaster();

        if (target != null && target.isAlive() && !target.isInvulnerable())
        {
            if (!this.world.isRemote() && target != caster)
            {
                target.attackEntityFrom(caster != null ? DamageSource.causeIndirectMagicDamage(this, caster) : DamageSource.MAGIC, 6.0F);
            }

            if (!target.world.isRemote() && !(target instanceof PlayerEntity))
            {
                target.setMotion
                (
                    target.getMotion().getX() + (rand.nextFloat() * (target.isOnGround() ? 0.2D : 0.05D) * (rand.nextBoolean() ? -1.0D : 1.0D)),
                    target.getMotion().getY() + (target.isOnGround() ? 0.5D : 0.125D),
                    target.getMotion().getZ() + (rand.nextFloat() * (target.isOnGround() ? 0.2D : 0.05D) * (rand.nextBoolean() ? -1.0D : 1.0D))
                );
            }
            else if (target.world.isRemote && (target instanceof PlayerEntity))
            {
                target.setMotion
                (
                    target.getMotion().getX() + (rand.nextFloat() * (target.isOnGround() ? 0.4D : 0.1D) * (rand.nextBoolean() ? -1.0D : 1.0D)),
                    target.getMotion().getY() + (target.isOnGround() ? 1.0D : 0.25D),
                    target.getMotion().getZ() + (rand.nextFloat() * (target.isOnGround() ? 0.1D : 0.1D) * (rand.nextBoolean() ? -1.0D : 1.0D))
                );
            }
        }
    }
}
