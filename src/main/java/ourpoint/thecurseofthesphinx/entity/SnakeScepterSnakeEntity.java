package ourpoint.thecurseofthesphinx.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.init.TCOTSEntityTypes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class SnakeScepterSnakeEntity extends Entity
{
    private int warmupDelayTicks;
    private boolean clientSideAttackStarted;
    private LivingEntity caster;
    private UUID casterUuid;
    private int lifeTicks = 40;

    public SnakeScepterSnakeEntity(EntityType<?> entityTypeIn, World worldIn)
    {
        super(entityTypeIn, worldIn);
    }

    public SnakeScepterSnakeEntity(World worldIn, BlockPos blockPos, int warmup, LivingEntity casterIn)
    {
        this(TCOTSEntityTypes.SNAKE_SCEPTER_SNAKE_ENTITY.get(), worldIn);
        this.warmupDelayTicks = warmup;
        this.setCaster(casterIn);
        this.setPosition(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public void setCaster(@Nullable LivingEntity p_190549_1_) {
        this.caster = p_190549_1_;
        this.casterUuid = p_190549_1_ == null ? null : p_190549_1_.getUniqueID();
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

    }

    @Override
    protected void readAdditional(CompoundNBT compound)
    {
        this.warmupDelayTicks = compound.getInt("Warmup");
        if (compound.hasUniqueId("Owner"))
        {
            this.casterUuid = compound.getUniqueId("Owner");
        }

    }

    @Override
    protected void writeAdditional(CompoundNBT compound)
    {
        compound.putInt("Warmup", this.warmupDelayTicks);
        if (this.casterUuid != null)
        {
            compound.putUniqueId("Owner", this.casterUuid);
        }

    }

    @Override
    @Nonnull
    public IPacket<?> createSpawnPacket()
    {
        return new SSpawnObjectPacket(this);
    }

    @Override
    public void tick()
    {
        TheCurseOfTheSphinx.LOGGER.debug("TEST");
        TheCurseOfTheSphinx.LOGGER.debug(this.warmupDelayTicks);
        TheCurseOfTheSphinx.LOGGER.debug(this.lifeTicks);
        TheCurseOfTheSphinx.LOGGER.debug(this.getPosition());

        super.tick();
        if (--this.warmupDelayTicks < 0)
        {
            this.clientSideAttackStarted = true;
            if (--this.lifeTicks > 0)
            {
                this.setPosition(this.getPosX(), this.getPosY()+0.1, this.getPosZ());
                for(LivingEntity livingEntity : this.world.getEntitiesWithinAABB(LivingEntity.class, this.getBoundingBox().grow(0.1D)))
                {
                    this.damage(livingEntity);
                }
            }
            else
            {
                this.remove();
            }
        }
    }

    private void damage(LivingEntity target)
    {
        LivingEntity caster = this.getCaster();
        if (target != null && target.isAlive() && !target.isInvulnerable() && target != caster)
        {
            if (caster != null)
            {
                target.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, caster), 3.0F);
            }
            else
            {
                target.attackEntityFrom(DamageSource.MAGIC, 3.0F);
            }

            target.applyKnockback(0.6F, 0.0D, 0.0D);
        }
    }

    @OnlyIn (Dist.CLIENT)
    public float getAnimationProgress(float partialTicks) {
        if (!this.clientSideAttackStarted) {
            return 0.0F;
        } else {
            int i = this.lifeTicks - 2;
            return i <= 0 ? 1.0F : 1.0F - ((float)i - partialTicks) / 20.0F;
        }
    }
}
