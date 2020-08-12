package ourpoint.thecurseofthesphinx.client.entity.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.client.entity.model.ScarabModel;
import ourpoint.thecurseofthesphinx.entity.ScarabEntity;

import javax.annotation.Nonnull;

public class ScarabRenderer extends MobRenderer<ScarabEntity, ScarabModel<ScarabEntity>>
{
    protected static final ResourceLocation TEXTURE = new ResourceLocation(TheCurseOfTheSphinx.MOD_ID, "textures/entity/scarab.png");

    public ScarabRenderer(EntityRendererManager renderManagerIn)
    {
        this(renderManagerIn, new ScarabModel<>(), 0.4F);
    }

    public ScarabRenderer(EntityRendererManager renderManagerIn, ScarabModel<ScarabEntity> entityModelIn, float shadowSizeIn)
    {
        super(renderManagerIn, entityModelIn, shadowSizeIn);
    }

    @Override
    protected float getDeathMaxRotation(@Nonnull ScarabEntity scarabEntity)
    {
        return 180.0F;
    }

    @Nonnull
    @Override
    public ResourceLocation getEntityTexture(@Nonnull ScarabEntity entity)
    {
        return TEXTURE;
    }
}
