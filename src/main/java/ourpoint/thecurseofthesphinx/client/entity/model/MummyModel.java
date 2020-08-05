package ourpoint.thecurseofthesphinx.client.entity.model;


import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.math.MathHelper;
import ourpoint.thecurseofthesphinx.entity.MummyEntity;

public class MummyModel<T extends MummyEntity> extends BipedModel<T>
{

    public MummyModel(float modelSize, float yOffsetIn, int textureWidthIn, int textureHeightIn)
    {
        super(modelSize, yOffsetIn, textureWidthIn, textureHeightIn);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.1F - 1.5F;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.1F - 1.5F;

    }

}
