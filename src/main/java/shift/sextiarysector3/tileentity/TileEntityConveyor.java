package shift.sextiarysector3.tileentity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import shift.sextiarysector3.block.BlockConveyor;

public class TileEntityConveyor extends TileEntity implements ITickable {

    protected static final AxisAlignedBB CONVEYOR_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);

    public long actionTime = 0;

    @Override
    public void update() {
        if (worldObj.isRemote) {
            this.updateClient();
        } else {
            this.updateServer();
        }

        if (this.hasPower()) moveEntity();

    }

    public void updateClient() {

    }

    public void updateServer() {

        actionTime = worldObj.getWorldInfo().getWorldTime();

    }

    public void moveEntity() {

        AxisAlignedBB axisalignedbb = CONVEYOR_AABB.offset(getPos());

        List<Entity> list = worldObj.getEntitiesWithinAABBExcludingEntity((Entity) null, axisalignedbb);

        EnumFacing f = worldObj.getBlockState(getPos()).getValue(BlockConveyor.FACING);

        for (Entity e : list) {
            if (e instanceof EntityPlayer) continue;
            e.motionX += (f.getFrontOffsetX() / 40.0f);
            e.motionY += (f.getFrontOffsetY() / 40.0f);
            e.motionZ += (f.getFrontOffsetZ() / 40.0f);
        }

    }

    public boolean hasPower() {
        return true;
    }

    public long getActionTime() {
        return this.actionTime;
    }

}
