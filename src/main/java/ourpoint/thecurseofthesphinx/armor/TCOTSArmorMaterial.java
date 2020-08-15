package ourpoint.thecurseofthesphinx.armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.init.TCOTSItems;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public enum TCOTSArmorMaterial implements IArmorMaterial
{
    BANDAGE(TheCurseOfTheSphinx.MOD_ID + ":bandage", 5, new int[]{1, 2, 3, 1}, 35,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.5F, 0.0F,
            () -> {return  Ingredient.fromItems(TCOTSItems.BANDAGE.get());});

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairMaterial;

    TCOTSArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountIn,
                               int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn,
                               float knockbackResistanceIn, Supplier<Ingredient> repairMaterialIn)
    {
        this.name = nameIn;
        this.maxDamageFactor = maxDamageFactorIn;
        this.damageReductionAmountArray = damageReductionAmountIn;
        this.enchantability = enchantabilityIn;
        this.soundEvent = soundEventIn;
        this.toughness = toughnessIn;
        this.knockbackResistance = knockbackResistanceIn;
        this.repairMaterial = repairMaterialIn;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn)
    {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn)
    {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability()
    {
        return this.enchantability;
    }

    @Nonnull
    @Override
    public SoundEvent getSoundEvent()
    {
        return this.soundEvent;
    }

    @Nonnull

    @Override
    public Ingredient getRepairMaterial()
    {
        return this.repairMaterial.get();
    }

    @OnlyIn(Dist.CLIENT)
    @Nonnull
    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public float getToughness()
    {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance()
    {
        return this.knockbackResistance;
    }
}
