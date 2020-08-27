package ourpoint.thecurseofthesphinx.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import ourpoint.thecurseofthesphinx.init.TCOTSItems;
import ourpoint.thecurseofthesphinx.items.SnakeScepterItem;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class MummyEntity extends ZombieEntity
{
    public MummyEntity(EntityType<MummyEntity> type, World worldIn)
    {
        super(type, worldIn);
        this.experienceValue = 8;
        Arrays.fill(this.inventoryArmorDropChances, 0.0F);
        Arrays.fill(this.inventoryHandsDropChances, 0.0F);
    }

    //basic parameters
    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        // func_233666_p_ => registerAttributes
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 40.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.15D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 35.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 4.0D)
                .createMutableAttribute(Attributes.ZOMBIE_SPAWN_REINFORCEMENTS, 0.0D);
    }

    @Override
    protected boolean shouldBurnInDay()
    {
        return true;
    }

    @Override
    @Nonnull
    protected ItemStack getSkullDrop()
    {
        return ItemStack.EMPTY;
    }

    @Override
    protected boolean shouldDrown()
    {
        return false;
    }

    @Override
    public void setChild(boolean childZombie)
    {}

    //settings
    @Override
    public ILivingEntityData onInitialSpawn(@Nonnull IServerWorld worldIn, @Nonnull DifficultyInstance difficultyIn, @Nonnull SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag)
    {
        this.setEquipmentBasedOnDifficulty(difficultyIn);
        return spawnDataIn;
    }

    @Override
    public void livingTick()
    {

        ItemStack itemStack =this.getItemStackFromSlot(EquipmentSlotType.HEAD);
        if (this.isAlive() && this.shouldBurnInDay() && this.isInDaylight() && (itemStack.isEmpty() || itemStack.getItem() == TCOTSItems.BANDAGE_HELMET.get()))
        {
            this.setFire(8);
        }

        super.livingTick();
    }

    //equipment
    @Override
    protected void setEquipmentBasedOnDifficulty(@Nonnull DifficultyInstance difficulty)
    {
        //MAINHAND
        if (this.rand.nextFloat() < (difficulty.getAdditionalDifficulty() * 0.05))
        {
            this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(TCOTSItems.SNAKE_SCEPTER.get()));
        }

        //OFFHAND
        if (this.rand.nextFloat() < 0.01F)
        {
            this.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(TCOTSItems.TOILET_PAPER_ITEM.get()));
        }

        //ARMOR
        float difficulty_calculated = MathHelper.clamp((difficulty.getAdditionalDifficulty() - 0.75F) * 0.33f, 0.25F, 1.0F);

        for (EquipmentSlotType equipmentslottype : EquipmentSlotType.values())
        {
            if (equipmentslottype.getSlotType() == EquipmentSlotType.Group.ARMOR)
            {
                ItemStack itemStack = this.getItemStackFromSlot(equipmentslottype);
                if (itemStack.isEmpty() && this.rand.nextFloat() < difficulty_calculated)
                {
                    Item item = getArmor(equipmentslottype);
                    if (item != null)
                    {
                        this.setItemStackToSlot(equipmentslottype, new ItemStack(item));
                    }
                }
            }
        }
    }

    public static Item getArmor(EquipmentSlotType slotIn)
    {
        switch (slotIn)
        {
            case HEAD:
                return TCOTSItems.BANDAGE_HELMET.get();
            case CHEST:
                return TCOTSItems.BANDAGE_CHESTPLATE.get();
            case LEGS:
                return TCOTSItems.BANDAGE_LEGGINGS.get();
            case FEET:
                return TCOTSItems.BANDAGE_BOOTS.get();
            default:
                return null;
        }
    }

    //attack
    @Override
    public boolean attackEntityAsMob(@Nonnull Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);
        if (flag && this.getHeldItemMainhand().isEmpty() && entityIn instanceof LivingEntity)
        {
            float f = this.world.getDifficultyForLocation(this.getPosition()).getAdditionalDifficulty();
            ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.HUNGER, 200 * (int)f));
        }

        return flag;
    }

    //sound
    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_HUSK_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_HUSK_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_HUSK_DEATH;
    }

    @Override
    @Nonnull
    protected SoundEvent getStepSound()
    {
        return SoundEvents.ENTITY_HUSK_STEP;
    }

    //AI
    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(2, new AttackWithScepterGoal(this, 1.0D, false));
        this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::isBreakDoorsTaskSet));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));

        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp(ZombifiedPiglinEntity.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.TARGET_DRY_BABY));
    }

    static class AttackWithScepterGoal extends ZombieAttackGoal
    {
        MobEntity entity;

        AttackWithScepterGoal(MobEntity entity, double speedIn, boolean longMemoryIn)
        {
            super((ZombieEntity) entity, speedIn, longMemoryIn);
            this.entity = entity;
        }

        @Override
        public boolean shouldExecute()
        {
            if (this.entity.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem() == TCOTSItems.SNAKE_SCEPTER.get() && this.entity.getAttackTarget() != null && this.entity.getAttackTarget().isAlive() && this.entity.getAttackTarget().getDistanceSq(this.entity) < 64)
            {
                return true;
            }
            else return super.shouldExecute();
        }

        @Override
        public void startExecuting()
        {
            super.startExecuting();
        }

        @Override
        public void tick()
        {
            ItemStack handStack = this.entity.getItemStackFromSlot(EquipmentSlotType.MAINHAND);

            ((SnakeScepterItem) handStack.getItem()).decrementCooldown(handStack);

            if (this.entity.getAttackTarget() != null && handStack.getItem() == TCOTSItems.SNAKE_SCEPTER.get() && this.entity.getAttackTarget() != null && this.entity.getAttackTarget().isAlive() && this.entity.getAttackTarget().getDistanceSq(this.entity) < 64)
            {
                ((SnakeScepterItem) handStack.getItem()).summonSnake(this.entity.world, this.entity, this.entity.getAttackTarget().getPosition(), handStack);
            }
            super.tick();
        }
    }
}
