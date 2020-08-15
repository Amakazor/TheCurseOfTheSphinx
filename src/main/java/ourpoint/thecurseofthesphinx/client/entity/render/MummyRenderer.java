package ourpoint.thecurseofthesphinx.client.entity.render;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.util.ResourceLocation;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.client.entity.model.MummyModel;
import ourpoint.thecurseofthesphinx.entity.MummyEntity;

import javax.annotation.Nonnull;

public class MummyRenderer extends BipedRenderer<MummyEntity, MummyModel<MummyEntity>>
{
    protected static final ResourceLocation TEXTURE = new ResourceLocation(TheCurseOfTheSphinx.MOD_ID, "textures/entity/mummy.png");

    public MummyRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new MummyModel<>(RenderType::getEntityCutoutNoCull, 0.0F, 0.0F, 64, 64), 0.7F);
        this.addLayer(new BipedArmorLayer<>(this, new MummyModel<>(0.2F, true),new MummyModel<>(0.2F, true)));
    }

    @Override
    @Nonnull
    public ResourceLocation getEntityTexture(@Nonnull MummyEntity entity)
    {
        return TEXTURE;
    }
}
