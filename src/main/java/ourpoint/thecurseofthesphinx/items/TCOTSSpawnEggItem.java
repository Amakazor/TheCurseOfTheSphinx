package ourpoint.thecurseofthesphinx.items;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.NonNullSupplier;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TCOTSSpawnEggItem extends SpawnEggItem
{

    private final Lazy<? extends EntityType<?>> entityTypeSupplier;

    protected static final List<TCOTSSpawnEggItem> UNADDED_EGGS = new ArrayList<>();

    @SuppressWarnings ("ConstantConditions")
    public TCOTSSpawnEggItem(final RegistryObject<? extends EntityType<?>> entityTypeSupplier, final int primaryColor, final int secondaryColor, final Item.Properties properties)
    {
        super(null, primaryColor, secondaryColor, properties);
        this.entityTypeSupplier = Lazy.of(entityTypeSupplier);
        UNADDED_EGGS.add(this);
    }

    public static void initSpawnEggs()
    {
        final Map<EntityType<?>, SpawnEggItem> EGGS = ObfuscationReflectionHelper.getPrivateValue(SpawnEggItem.class, null, "EGGS");
        DefaultDispenseItemBehavior dispenseItemBehavior = new DefaultDispenseItemBehavior()
        {
            @Override
            protected ItemStack dispenseStack(IBlockSource source, ItemStack stack)
            {
                Direction direction  = source.getBlockState().get(DispenserBlock.FACING);
                EntityType<?> type = ((SpawnEggItem) stack.getItem()).getType(stack.getTag());
                type.spawn(source.getWorld(), stack, null, source.getBlockPos().offset(direction), SpawnReason.DISPENSER, direction != Direction.UP, false);
                stack.shrink(1);
                return stack;
            }
        };

        for (final SpawnEggItem spawnEgg : UNADDED_EGGS)
        {
            assert EGGS != null;
            EGGS.put(spawnEgg.getType(null), spawnEgg);
            DispenserBlock.registerDispenseBehavior(spawnEgg, dispenseItemBehavior);
        }
        UNADDED_EGGS.clear();
    }

    @Override
    @Nonnull
    public EntityType<?> getType(CompoundNBT compoundNBT)
    {
        return this.entityTypeSupplier.get();
    }
}
