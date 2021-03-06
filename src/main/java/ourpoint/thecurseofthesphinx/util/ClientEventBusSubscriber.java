package ourpoint.thecurseofthesphinx.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.client.entity.render.MummyRenderer;
import ourpoint.thecurseofthesphinx.client.entity.render.ScarabRenderer;
import ourpoint.thecurseofthesphinx.client.entity.render.SnakeScepterSnakeRenderer;
import ourpoint.thecurseofthesphinx.init.TCOTSEntityTypes;
import ourpoint.thecurseofthesphinx.items.TCOTSSpawnEggItem;

@Mod.EventBusSubscriber(modid = TheCurseOfTheSphinx.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber
{
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(TCOTSEntityTypes.MUMMY_ENTITY.get(), MummyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(TCOTSEntityTypes.SCARAB_ENTITY.get(), ScarabRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(TCOTSEntityTypes.TOILET_PAPER_ENTITY.get(), renderManager -> new SpriteRenderer<>(renderManager, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(TCOTSEntityTypes.SNAKE_SCEPTER_SNAKE_ENTITY.get(), SnakeScepterSnakeRenderer::new);
    }
}
