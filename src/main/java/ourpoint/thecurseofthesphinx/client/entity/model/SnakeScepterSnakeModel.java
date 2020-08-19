package ourpoint.thecurseofthesphinx.client.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class SnakeScepterSnakeModel<T extends Entity> extends SegmentedModel<T>
{
    private final ModelRenderer snakeBody;

    public SnakeScepterSnakeModel()
    {
        textureWidth = 32;
        textureHeight = 128;

        snakeBody = new ModelRenderer(this);
        snakeBody.setRotationPoint(0.0F, 24.0F, 0.0F);
        snakeBody.setTextureOffset(0, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 64.0F, 8.0F, 0.0F, false);
    }

    @Override
    @Nonnull
    public Iterable<ModelRenderer> getParts()
    {
        return ImmutableList.of(this.snakeBody);
    }

    @Override
    public void setRotationAngles(@Nonnull T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {

    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
