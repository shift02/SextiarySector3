package shift.sextiarysector3.tileentity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import shift.sextiarysector3.block.BlockConveyor;

public class TileEntityConveyor extends TileEntity implements ITickable {

    protected static final AxisAlignedBB CONVEYOR_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);

    public long actionTime = 0;

    public final String MOVE_NOW = "move_now";

    @Override
    public void update() {

        actionTime = worldObj.getWorldInfo().getWorldTime();

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

        float ani = (1 / 16.0f) / 2.0f;

        for (Entity e : list) {
            if (e instanceof EntityPlayer) continue;

            if (this.isMoveNow(e)) continue;
            //e.motionX += (f.getFrontOffsetX() / 46.0f);
            //e.motionY += (f.getFrontOffsetY() / 46.0f);
            //e.motionZ += (f.getFrontOffsetZ() / 46.0f);
            e.setPosition(e.posX + f.getFrontOffsetX() * ani, e.posY + f.getFrontOffsetY() * ani, e.posZ + f.getFrontOffsetZ() * ani);

            e.getEntityData().setLong(MOVE_NOW, actionTime);

        }

    }

    public boolean isMoveNow(Entity e) {

        NBTTagCompound nbt = e.getEntityData();

        if (!nbt.hasKey(MOVE_NOW)) return false;
        if (nbt.getLong(MOVE_NOW) != this.actionTime) return false;

        return true;

    }

    public boolean hasPower() {
        return true;
    }

    public long getActionTime() {
        return this.actionTime;
    }

}
