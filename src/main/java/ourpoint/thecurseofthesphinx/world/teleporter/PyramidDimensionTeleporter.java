package ourpoint.thecurseofthesphinx.world.teleporter;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class PyramidDimensionTeleporter implements ITeleporter
{
    BlockPos destinationPos;

    public PyramidDimensionTeleporter(BlockPos pos)
    {
        destinationPos = pos;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity)
    {
        Entity e = repositionEntity.apply(false);

        if (!(e instanceof ServerPlayerEntity)) {
            return e;
        }

        ServerPlayerEntity player = (ServerPlayerEntity) e;

        player.addExperienceLevel(0);
        player.setPositionAndUpdate(destinationPos.getX() + 0.5D, destinationPos.getY() + 1D, destinationPos.getZ() + 0.5D);
        return e;
    }
}
