package ourpoint.thecurseofthesphinx.client.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class ScarabModel<T extends AnimalEntity> extends SegmentedModel<T>
{
    public final ModelRenderer scarabBody;

    public ScarabModel()
    {
        textureWidth = 64;
        textureHeight = 64;

        scarabBody = new ModelRenderer(this);
        scarabBody.setRotationPoint(0.0F, 21.5F, 0.0F);
        scarabBody.setTextureOffset(0, 1).addBox(-3.0F, -1.5F, -5.0F, 6.0F, 2.0F, 10.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(@Nonnull T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {

    }

    @Nonnull
    @Override
    public Iterable<ModelRenderer> getParts()
    {
        return ImmutableList.of(this.scarabBody);
    }

}
