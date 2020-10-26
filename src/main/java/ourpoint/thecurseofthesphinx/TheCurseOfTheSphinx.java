package ourpoint.thecurseofthesphinx;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ourpoint.thecurseofthesphinx.entity.MummyEntity;
import ourpoint.thecurseofthesphinx.entity.ScarabEntity;
import ourpoint.thecurseofthesphinx.entity.ai.goal.MoveToToiletPaperGoal;
import ourpoint.thecurseofthesphinx.init.TCOTSEntityTypes;
import ourpoint.thecurseofthesphinx.init.TCOTSInit;
import ourpoint.thecurseofthesphinx.init.TCOTSItems;

@Mod (TheCurseOfTheSphinx.MOD_ID)
public class TheCurseOfTheSphinx
{
    public static final String MOD_ID = "thecurseofthesphinx";
    public static final Logger LOGGER = LogManager.getLogger();

    public TheCurseOfTheSphinx()
    {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);
        TCOTSInit.Init(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SuppressWarnings ("deprecation")
    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("The Sphinx is watching");
        DeferredWorkQueue.runLater(
                ()-> GlobalEntityTypeAttributes.put(TCOTSEntityTypes.MUMMY_ENTITY.get(), MummyEntity.setCustomAttributes().create()));
        DeferredWorkQueue.runLater(
                ()-> GlobalEntityTypeAttributes.put(TCOTSEntityTypes.SCARAB_ENTITY.get(), ScarabEntity.setCustomAttributes().create()));
    }

    //Creative Tab
    public static final ItemGroup TAB = new ItemGroup("tcotstab")
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(TCOTSItems.ANKH.get());
        }
    };

    @SubscribeEvent (priority = EventPriority.HIGHEST)
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        Entity entity = event.getEntity();

        if (entity instanceof CatEntity)
        {
            ((CatEntity) entity).goalSelector.addGoal(4, new MoveToToiletPaperGoal((CatEntity)entity, 1.2F, 5));
        }
    }
}
