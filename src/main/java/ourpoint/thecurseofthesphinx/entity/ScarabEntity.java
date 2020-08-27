package ourpoint.thecurseofthesphinx.entity;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class ScarabEntity extends AnimalEntity
{
    private static final DataParameter<Boolean> BURYING = EntityDataManager.createKey(ScarabEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> BURYING_TIME = EntityDataManager.createKey(ScarabEntity.class, DataSerializers.VARINT);

    public ScarabEntity(EntityType<? extends ScarabEntity> type, World worldIn)
    {
        super(type, worldIn);
    }

    //basic parameters

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return MobEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 8.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 0.0D);

    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(@Nonnull ServerWorld p_241840_1_, @Nonnull AgeableEntity p_241840_2_)
    {
        // func_241840_a => prolly createChild()
        return null;
    }

    @Override
    protected void registerData()
    {
        super.registerData();
        this.dataManager.register(BURYING, false);
        this.dataManager.register(BURYING_TIME, 0);
    }

    @Nonnull
    @Override
    public CreatureAttribute getCreatureAttribute()
    {
        return CreatureAttribute.ARTHROPOD;
    }

    @Override
    public boolean canBreatheUnderwater()
    {
        return true;
    }

    @Override
    public boolean isPushedByWater()
    {
        return true;
    }

    @Override
    public boolean canBreed()
    {
        return false;
    }

    @Override
    protected float getStandingEyeHeight(@Nonnull Pose poseIn, EntitySize sizeIn)
    {
        return 0.2F * sizeIn.height;
    }

    public boolean isBurying()
    {
        return this.dataManager.get(BURYING);
    }

    public void setBurying(boolean burying)
    {
        this.dataManager.set(BURYING, burying);
    }

    public int getBuryingTime()
    {
        return this.dataManager.get(BURYING_TIME);
    }


    public void setBuryingTime(int buryingTime)
    {
        this.dataManager.set(BURYING_TIME, buryingTime);
    }

    //sound
    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_BAT_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_BAT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_BAT_DEATH;
    }

    //AI

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new BuryGoal<>(this, PlayerEntity.class, 5.0F, 0.5D, 1.2D));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0F));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 10.0F));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
    }

    @Override
    public void applyKnockback(float strength, double ratioX, double ratioZ)
    {
        if (!this.isBurying())
        {
            super.applyKnockback(strength, ratioX, ratioZ);
        }
    }

    @Override
    public void setRevengeTarget(@Nullable LivingEntity livingBase)
    {
        if (!this.isBurying())
        {
            super.setRevengeTarget(livingBase);
        }
    }

    public static class BuryGoal<T extends LivingEntity> extends AvoidEntityGoal<T>
    {
        private final ScarabEntity scarab;

        public BuryGoal(CreatureEntity scarabIn, Class<T> classToAvoidIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn)
        {
            super(scarabIn, classToAvoidIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);
            this.scarab = (ScarabEntity) scarabIn;
        }

        @Override
        public boolean shouldExecute()
        {
            Block block = this.scarab.world.getBlockState(this.scarab.getPosition().down()).getBlock();

            if (super.shouldExecute())
            {
                if (this.scarab.getRNG().nextInt(5) == 0 && (block.isIn(Tags.Blocks.SAND) || block == Blocks.DIRT))
                {
                    this.scarab.setBurying(true);
                }
                return true;
            }
            return false;
        }

        @Override
        public void startExecuting()
        {
            if (this.scarab.isBurying())
            {
                this.scarab.noClip = true;
                this.scarab.setNoGravity(true);
                this.scarab.setBuryingTime(120);
                this.scarab.setVelocity(0.0D, 0.0D, 0.0D);
            }
            else
            {
                super.startExecuting();
            }
        }

        @Override
        public boolean shouldContinueExecuting()
        {
            if (this.scarab.isBurying())
            {
                return this.scarab.isAlive() && this.scarab.getBuryingTime() != 0;
            }
            else
            {
                return super.shouldContinueExecuting();
            }
        }

        protected void reset()
        {
            this.scarab.setBurying(false);
            this.scarab.noClip = false;
            this.scarab.setNoGravity(false);
        }

        @Override
        public void tick()
        {
            if (this.scarab.isBurying())
            {
                int buryTime = this.scarab.getBuryingTime();

                if (this.scarab.isAlive() && this.scarab.getBuryingTime() != 0)
                {
                    //If block not available stop burying
                    Block testBlock;
                    if (Math.abs(buryTime - 60) < 52)
                    {
                        testBlock = this.scarab.world.getBlockState(this.scarab.getPosition()).getBlock();
                    }
                    else
                    {
                        testBlock = this.scarab.world.getBlockState(this.scarab.getPosition().down()).getBlock();
                    }
                    if (!(testBlock.isIn(Tags.Blocks.SAND) || testBlock == Blocks.DIRT))
                    {
                        this.reset();
                    }

                    //Emit particles
                    if (buryTime % 8 == 0)
                    {
                        BlockPos blockPos = this.scarab.getPosition();

                        this.scarab.world.playEvent(2001, buryTime < 58 ? blockPos.up() : blockPos,
                                Block.getStateId(this.scarab.world.getBlockState(buryTime < 118 ? blockPos : blockPos.down())));
                    }

                    //Change position
                    if (buryTime % 5 == 0)
                    {
                        this.scarab.move(MoverType.SELF, new Vector3d(0.0D, this.scarab.getBuryingTime() > 60 ? -0.05D : 0.05D, 0.0D));
                    }

                    //Teleport
                    if (buryTime == 60)
                    {
                        BlockPos blockPos = this.scarab.getPosition();
                        Random random = this.scarab.getRNG();
                        BlockPos newBlockPos;
                        World world = this.scarab.world;
                        Block block;
                        Block block2;

                        for (int i = 0; i < 4096; i++)
                        {
                            newBlockPos = new BlockPos
                                    (
                                            blockPos.getX() + ((random.nextInt(8) + 8) * ((random.nextInt(2) == 0) ? -1 : 1)),
                                            blockPos.getY() + (random.nextInt(16) - 8),
                                            blockPos.getZ() + ((random.nextInt(8) + 8) * ((random.nextInt(2) == 0) ? -1 : 1))
                                    );
                            block = world.getBlockState(newBlockPos).getBlock();
                            block2 = world.getBlockState(newBlockPos.up()).getBlock();

                            if ((block.isIn(Tags.Blocks.SAND) || block == Blocks.DIRT) && (block2 == Blocks.AIR || block2 instanceof IPlantable))
                            {
                                this.scarab.setInvisible(true);
                                this.scarab.setPositionAndUpdate(newBlockPos.up().getX()+0.5D, newBlockPos.up().getY()-0.5D, newBlockPos.up().getZ()+0.5D);
                                this.scarab.setInvisible(false);
                                break;
                            }
                        }
                    }

                    //End
                    if (buryTime == 1)
                    {
                        this.reset();
                    }

                    this.scarab.setBuryingTime(--buryTime);
                }
            }
            else
            {
                super.tick();
            }
        }
    }
}
