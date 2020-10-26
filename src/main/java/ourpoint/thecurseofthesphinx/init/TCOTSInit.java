package ourpoint.thecurseofthesphinx.init;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public class TCOTSInit
{
    public static void Init(IEventBus bus)
    {
        List<DeferredRegister<?>> Registries = new ArrayList<>();

        Registries.add(TCOTSBlocks.BLOCKS);
        Registries.add(TCOTSItems.ITEMS);
        Registries.add(TCOTSEntityTypes.ENTITY_TYPES);
        Registries.add(TCOTSBiomes.BIOMES);
        Registries.add(TCOTSStructures.STRUCTURES);
        Registries.add(TCOTSSounds.SOUNDS);
        Registries.add(TCOTSEnchantments.ENCHANTMENTS);

        for (DeferredRegister<?> register: Registries)
        {
            register.register(bus);
        }

        TCOTSChunkGenerator.registerChunkGenerator();
    }
}
