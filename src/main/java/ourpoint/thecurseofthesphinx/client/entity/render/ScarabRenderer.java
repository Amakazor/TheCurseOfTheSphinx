package ourpoint.thecurseofthesphinx.client.entity.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.client.entity.model.ScarabModel;
import ourpoint.thecurseofthesphinx.entity.ScarabEntity;

import javax.annotation.Nonnull;

public class ScarabRenderer extends MobRenderer<ScarabEntity, ScarabModel<ScarabEntity>>
{
    protected static final ResourceLocation TEXTURE = new ResourceLocation(TheCurseOfTheSphinx.MOD_ID, "textures/entity/scarab.png");
    private int prevBlockLight;
    private int prevSkyLight;

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

    @Override
    protected int getBlockLight(@Nonnull ScarabEntity entityIn, @Nonnull BlockPos blockPos)
    {
        int blockLight =super.getBlockLight(entityIn, blockPos);

        if (entityIn.isBurying())
        {
            int absBuryingTime = Math.abs(entityIn.getBuryingTime() - 60);

            if (absBuryingTime < 52)
            {
                return super.getBlockLight(entityIn, blockPos.up());
            }
            else if (absBuryingTime == 52)
            {
                if (blockLight != 0)
                {
                    if (prevBlockLight == 0)
                    {
                        prevBlockLight = blockLight;
                    }
                }
                else if (blockLight != prevBlockLight)
                {
                    return super.getBlockLight(entityIn, blockPos.up());
                }
            }
        }
        return blockLight;
    }

    @Override
    protected int func_239381_b_(@Nonnull ScarabEntity entityIn, @Nonnull BlockPos blockPos)
    {
        int skyLight = super.func_239381_b_(entityIn, blockPos);

        if (entityIn.isBurying())
        {
            int absBuryingTime = Math.abs(entityIn.getBuryingTime() - 60);

            if (absBuryingTime < 51)
            {
                return super.func_239381_b_(entityIn, blockPos.up());
            }
            else if (absBuryingTime < 53)
            {
                if (skyLight != 0)
                {
                    if (prevSkyLight == 0)
                    {
                        prevSkyLight = skyLight;
                    }
                }
                else if (skyLight != prevSkyLight)
                {
                    return super.func_239381_b_(entityIn, blockPos.up());
                }
            }
        }
        return skyLight;
    }

    @Nonnull
    @Override
    public ResourceLocation getEntityTexture(@Nonnull ScarabEntity entity)
    {
        return TEXTURE;
    }
}
