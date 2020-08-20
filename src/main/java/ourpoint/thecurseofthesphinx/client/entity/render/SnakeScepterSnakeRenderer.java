package ourpoint.thecurseofthesphinx.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.client.entity.model.SnakeScepterSnakeModel;
import ourpoint.thecurseofthesphinx.entity.SnakeScepterSnakeEntity;

import javax.annotation.Nonnull;

@OnlyIn (Dist.CLIENT)
public class SnakeScepterSnakeRenderer extends EntityRenderer<SnakeScepterSnakeEntity>
{
    protected static final ResourceLocation TEXTURE = new ResourceLocation(TheCurseOfTheSphinx.MOD_ID, "textures/entity/snake_scepter_snake.png");
    private final SnakeScepterSnakeModel<SnakeScepterSnakeEntity> model = new SnakeScepterSnakeModel<>();


    public SnakeScepterSnakeRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn);
    }

    public void render(@Nonnull SnakeScepterSnakeEntity entityIn, float entityYaw, float partialTicks, @Nonnull MatrixStack matrixStackIn, @Nonnull IRenderTypeBuffer bufferIn, int packedLightIn)
    {
        if (entityIn.isAlive() && entityIn.getVisible())
        {
            matrixStackIn.push();
            this.model.setRotationAngles(entityIn, partialTicks, 0.0F, 0.0F, entityIn.rotationYaw, entityIn.rotationPitch);
            IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.model.getRenderType(TEXTURE));
            this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.pop();
            super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        }
    }

    @Nonnull
    @Override
    public ResourceLocation getEntityTexture(@Nonnull SnakeScepterSnakeEntity entity)
    {
        return TEXTURE;
    }
}
