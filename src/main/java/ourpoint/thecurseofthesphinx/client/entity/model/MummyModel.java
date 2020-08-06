package ourpoint.thecurseofthesphinx.client.entity.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import java.util.function.Function;

public class MummyModel<T extends LivingEntity> extends AgeableModel<T> implements IHasArm, IHasHead
{
    private final ModelRenderer mummyBody;
    private final ModelRenderer mummyHead;
    private final ModelRenderer mummyRightLeg;
    private final ModelRenderer mummyLeftLeg;
    private final ModelRenderer mummyRightArm;
    private final ModelRenderer mummyLeftArm;

    public MummyModel(Function<ResourceLocation, RenderType> renderTypeIn, float modelSizeIn, float yOffsetIn, int textureWidthIn, int textureHeightIn) 
    {
        super(renderTypeIn, true, 16.0F, 0.0F, 2.0F, 2.0F, 24.0F);
        this.textureWidth = textureWidthIn;
        this.textureHeight = textureHeightIn;

        this.mummyBody = new ModelRenderer(this);
        this.mummyBody.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.mummyBody.setTextureOffset(16, 16)
                .addBox(-4.0F, -4.0F + yOffsetIn, -2.0F, 8.0F, 12.0F, 4.0F, modelSizeIn, false);

        this.mummyHead = new ModelRenderer(this);
        this.mummyHead.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.mummyHead.setTextureOffset(0, 0)
                .addBox(-4.0F, -4.0F + yOffsetIn, -4.0F, 8.0F, 8.0F, 8.0F, modelSizeIn, false);

        this.mummyRightLeg = new ModelRenderer(this);
        this.mummyRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.mummyRightLeg.setTextureOffset(0, 16)
                .addBox(-2.0F, 0.0F + yOffsetIn, -2.0F, 4.0F, 12.0F, 4.0F, modelSizeIn, false);

        this.mummyLeftLeg = new ModelRenderer(this);
        this.mummyLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.mummyLeftLeg.setTextureOffset(0, 16)
                .addBox(-2.0F, 0.0F + yOffsetIn, -2.0F, 4.0F, 12.0F, 4.0F, modelSizeIn, true);

        this.mummyRightArm = new ModelRenderer(this);
        this.mummyRightArm.setRotationPoint(-4.0F, 0.0F, 0.0F);
        this.mummyRightArm.setTextureOffset(40, 16)
                .addBox(-2.0F, 0.0F + yOffsetIn, 0.0F, 4.0F, 12.0F, 4.0F, modelSizeIn, false);

        this.mummyLeftArm = new ModelRenderer(this);
        this.mummyLeftArm.setRotationPoint(4.0F, 0.0F, 0.0F);
        this.mummyLeftArm.setTextureOffset(40, 16)
                .addBox(-2.0F, 0.0F + yOffsetIn, 0.0F, 4.0F, 12.0F, 4.0F, modelSizeIn, true);
    }

    protected Iterable<ModelRenderer> getHeadParts()
    {
        return ImmutableList.of(this.mummyHead);
    }

    protected Iterable<ModelRenderer> getBodyParts()
    {
        return ImmutableList.of(this.mummyBody, this.mummyRightArm, this.mummyLeftArm, this.mummyRightLeg, this.mummyLeftLeg);
    }

    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
    {
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
    }

    /**
     * Sets this entity's model rotation angles
     */
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
    {
        this.mummyHead.rotateAngleX = headPitch * ((float)Math.PI / 180F);

        this.mummyBody.rotateAngleY = 0.0F;

        this.mummyRightArm.rotationPointZ = 0.0F;
        this.mummyRightArm.rotationPointX = -5.0F;

        this.mummyLeftArm.rotationPointZ = 0.0F;
        this.mummyLeftArm.rotationPointX = 5.0F;

        this.mummyRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.1F - 1.5F;
        this.mummyLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.1F - 1.5F;

        this.mummyRightArm.rotateAngleZ = 0.0F;
        this.mummyLeftArm.rotateAngleZ = 0.0F;

        this.mummyRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount;
        this.mummyLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 0.7F * limbSwingAmount;

        this.mummyRightLeg.rotateAngleY = 0.0F;
        this.mummyLeftLeg.rotateAngleY = 0.0F;

        this.mummyRightLeg.rotateAngleZ = 0.0F;
        this.mummyLeftLeg.rotateAngleZ = 0.0F;

        this.mummyBody.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount;
        this.mummyLeftArm.rotationPointX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount + 6.0F;
        this.mummyRightArm.rotationPointX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount - 6.0F;
    }

    public void translateHand(HandSide sideIn, MatrixStack matrixStackIn) 
    {
        this.getArmForSide(sideIn).translateRotate(matrixStackIn);
    }

    protected ModelRenderer getArmForSide(HandSide side)
    {
        return side == HandSide.LEFT ? this.mummyLeftArm : this.mummyRightArm;
    }

    @Override
    public ModelRenderer getModelHead()
    {
        return this.mummyHead;
    }
}
