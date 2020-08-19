package ourpoint.thecurseofthesphinx.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EvokerFangsModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.projectile.EvokerFangsEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.client.entity.model.ScarabModel;
import ourpoint.thecurseofthesphinx.client.entity.model.SnakeScepterSnakeModel;
import ourpoint.thecurseofthesphinx.entity.ScarabEntity;
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
        TheCurseOfTheSphinx.LOGGER.debug("RENDER");
        float f = entityIn.getAnimationProgress(partialTicks);
        TheCurseOfTheSphinx.LOGGER.debug(f);
        if (f != 0.0F)
        {
            float f1 = 2.0F;
            if (f > 0.9F)
            {
                f1 = (float)((double)f1 * ((1.0D - (double)f) / (double)0.1F));
            }

            matrixStackIn.push();
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(90.0F - entityIn.rotationYaw));
            matrixStackIn.scale(-f1, -f1, f1);
            matrixStackIn.translate(0.0D, (double)-0.626F, 0.0D);
            matrixStackIn.scale(0.5F, 0.5F, 0.5F);
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
