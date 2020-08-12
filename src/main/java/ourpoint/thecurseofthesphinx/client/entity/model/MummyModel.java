package ourpoint.thecurseofthesphinx.client.entity.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import java.util.function.Function;

public class MummyModel<T extends LivingEntity> extends BipedModel<T>
{
    public MummyModel(float modelSize, boolean isArmor)
    {
        this(RenderType::getEntityCutoutNoCull, modelSize, 0.0F, 64, isArmor ? 32 : 64);
    }

    public MummyModel(Function<ResourceLocation, RenderType> renderTypeIn, float modelSizeIn, float yOffsetIn, int textureWidthIn, int textureHeightIn)
    {
        super(renderTypeIn, modelSizeIn, yOffsetIn, textureWidthIn, textureHeightIn);
        this.textureWidth = textureWidthIn;
        this.textureHeight = textureHeightIn;

        this.bipedBody = new ModelRenderer(this);
        this.bipedBody.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.bipedBody.setTextureOffset(16, 16)
                .addBox(-4.0F, -4.0F + yOffsetIn, -2.0F, 8.0F, 12.0F, 4.0F, modelSizeIn, false);

        this.bipedHead = new ModelRenderer(this);
        this.bipedHead.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.bipedHead.setTextureOffset(0, 0)
                .addBox(-4.0F, -4.0F + yOffsetIn, -4.0F, 8.0F, 8.0F, 8.0F, modelSizeIn, false);

        this.bipedRightLeg = new ModelRenderer(this);
        this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.bipedRightLeg.setTextureOffset(0, 16)
                .addBox(-2.0F, 0.0F + yOffsetIn, -2.0F, 4.0F, 12.0F, 4.0F, modelSizeIn, false);

        this.bipedLeftLeg = new ModelRenderer(this);
        this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.bipedLeftLeg.setTextureOffset(0, 16)
                .addBox(-2.0F, 0.0F + yOffsetIn, -2.0F, 4.0F, 12.0F, 4.0F, modelSizeIn, true);

        this.bipedRightArm = new ModelRenderer(this);
        this.bipedRightArm.setRotationPoint(-4.0F, 0.0F, 0.0F);
        this.bipedRightArm.setTextureOffset(40, 16)
                .addBox(-2.0F, 0.0F + yOffsetIn, 0.0F, 4.0F, 12.0F, 4.0F, modelSizeIn, false);

        this.bipedLeftArm = new ModelRenderer(this);
        this.bipedLeftArm.setRotationPoint(4.0F, 0.0F, 0.0F);
        this.bipedLeftArm.setTextureOffset(40, 16)
                .addBox(-2.0F, 0.0F + yOffsetIn, 0.0F, 4.0F, 12.0F, 4.0F, modelSizeIn, true);
    }

    protected Iterable<ModelRenderer> getHeadParts()
    {
        return ImmutableList.of(this.bipedHead);
    }

    protected Iterable<ModelRenderer> getBodyParts()
    {
        return ImmutableList.of(this.bipedBody, this.bipedRightArm, this.bipedLeftArm, this.bipedRightLeg, this.bipedLeftLeg);
    }

    @Override
    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
    {
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
    }

    /**
     * Sets this entity's model rotation angles
     */
    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
    {
        this.bipedHead.rotateAngleX = headPitch * ((float)Math.PI / 180F);

        this.bipedBody.rotateAngleY = 0.0F;

        this.bipedRightArm.rotationPointZ = 0.0F;
        this.bipedRightArm.rotationPointX = -5.0F;

        this.bipedLeftArm.rotationPointZ = 0.0F;
        this.bipedLeftArm.rotationPointX = 5.0F;

        this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.1F - 1.5F;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.1F - 1.5F;

        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;

        this.bipedRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 0.7F * limbSwingAmount;

        this.bipedRightLeg.rotateAngleY = 0.0F;
        this.bipedLeftLeg.rotateAngleY = 0.0F;

        this.bipedRightLeg.rotateAngleZ = 0.0F;
        this.bipedLeftLeg.rotateAngleZ = 0.0F;

        float limbSwingAmountClamped = MathHelper.clamp(limbSwingAmount, -0.2F, 0.2F);

        this.bipedBody.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmountClamped;
        this.bipedLeftArm.rotationPointX = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmountClamped + 6.0F;
        this.bipedRightArm.rotationPointX = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmountClamped - 6.0F;
    }

    public void translateHand(HandSide sideIn, MatrixStack matrixStackIn) 
    {
        this.getArmForSide(sideIn).translateRotate(matrixStackIn);
    }

    protected ModelRenderer getArmForSide(HandSide side)
    {
        return side == HandSide.LEFT ? this.bipedLeftArm : this.bipedRightArm;
    }

    @Override
    public ModelRenderer getModelHead()
    {
        return this.bipedHead;
    }
}
