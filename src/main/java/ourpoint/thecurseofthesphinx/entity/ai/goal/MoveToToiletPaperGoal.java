package ourpoint.thecurseofthesphinx.entity.ai.goal;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import ourpoint.thecurseofthesphinx.init.TCOTSBlocks;

import javax.annotation.Nonnull;

public class MoveToToiletPaperGoal extends MoveToBlockGoal
{
    public MoveToToiletPaperGoal(CreatureEntity creatureIn, double speed, int length)
    {
        super(creatureIn, speed, length);
    }

    @Override
    public boolean shouldMove()
    {
        return this.timeoutCounter % 100 == 0;
    }

    @Override
    protected boolean shouldMoveTo(@Nonnull IWorldReader worldIn, @Nonnull BlockPos pos)
    {
        return worldIn.getBlockState(pos).isIn(TCOTSBlocks.TOILET_PAPER_BLOCK.get());
    }


    @Override
    protected int getRunDelay(@Nonnull CreatureEntity creatureIn)
    {
        return 40 + creatureIn.getRNG().nextInt(100);
    }

    @Override
    public boolean shouldExecute()
    {
        return super.shouldExecute() && this.creature instanceof CatEntity && !this.creature.isSleeping();
    }
}
