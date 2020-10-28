package ourpoint.thecurseofthesphinx.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ourpoint.thecurseofthesphinx.items.ScarabKeyItem;
import ourpoint.thecurseofthesphinx.tileentity.PyramidPortalTileEntity;

import javax.annotation.Nonnull;

public class PyramidPortal extends Block
{
    public PyramidPortal(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new PyramidPortalTileEntity();
    }

    @Override
    @Nonnull
    @SuppressWarnings ("deprecation")
    public ActionResultType onBlockActivated(@Nonnull BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand handIn, @Nonnull BlockRayTraceResult hit)
    {
        if (!worldIn.isRemote && handIn == Hand.MAIN_HAND)
        {
            PyramidPortalTileEntity pyramidPortalTileEntity = (PyramidPortalTileEntity) worldIn.getTileEntity(pos);
            if (pyramidPortalTileEntity != null)
            {
                if (!pyramidPortalTileEntity.IsActive())
                {
                    ItemStack itemStack = player.getHeldItem(handIn);

                    if (!itemStack.isEmpty() && itemStack.getItem() instanceof ScarabKeyItem)
                    {
                        pyramidPortalTileEntity.Activate(worldIn, (ScarabKeyItem) itemStack.getItem());

                        if (!player.isCreative())
                        {
                            itemStack.shrink(1);
                        }

                        return ActionResultType.SUCCESS;
                    }
                }
                else
                {
                    pyramidPortalTileEntity.Teleport((ServerPlayerEntity) player);
                }
            }
        }
        return ActionResultType.PASS;
    }
}
