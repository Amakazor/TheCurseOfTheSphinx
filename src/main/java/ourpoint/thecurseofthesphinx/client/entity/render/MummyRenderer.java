package ourpoint.thecurseofthesphinx.client.entity.render;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.client.entity.model.MummyModel;
import ourpoint.thecurseofthesphinx.entity.MummyEntity;

public class MummyRenderer extends MobRenderer<MummyEntity, MummyModel<MummyEntity>>
{
    protected static final ResourceLocation TEXTURE = new ResourceLocation(TheCurseOfTheSphinx.MOD_ID, "textures/entity/mummy.png");

    public MummyRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new MummyModel<>(RenderType::getEntityCutoutNoCull, 0.0F, 0.0F, 64, 64), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(MummyEntity entity)
    {
        return TEXTURE;
    }
}
