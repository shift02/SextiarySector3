package shift.sextiarysector3.tileentity;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import shift.sextiarysector3.block.BlockConveyor;

public class TileEntityConveyor extends TileEntity implements ITickable {

    protected static final AxisAlignedBB CONVEYOR_AABB0 = new AxisAlignedBB(0.0D, 0.0D, 0.1875D, 1.0D, 0.25D, 0.8125D);
    protected static final AxisAlignedBB CONVEYOR_AABB1 = new AxisAlignedBB(0.1875D, 0.0D, 0.0D, 0.8125D, 0.25D, 1.0D);

    public long actionTime = 0;

    public final String MOVE_NOW = "move_now";

    //インベントリ
    protected ItemStackHandler topItem = (ItemStackHandler) CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getDefaultInstance();

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

        EnumFacing f = worldObj.getBlockState(getPos()).getValue(BlockConveyor.FACING);

        AxisAlignedBB axisalignedbb = getAABBFromEnumFacing(f).offset(getPos());

        List<Entity> list = worldObj.getEntitiesWithinAABBExcludingEntity((Entity) null, axisalignedbb);

        float ani = (1 / 16.0f) / 2.0f;

        for (Entity e : list) {
            if (e instanceof EntityPlayer) continue;

            if (this.isMoveNow(e)) continue;
            e.setPosition(e.posX + f.getFrontOffsetX() * ani, e.posY + f.getFrontOffsetY() * ani, e.posZ + f.getFrontOffsetZ() * ani);

            moveCenter(f, e);

            e.getEntityData().setLong(MOVE_NOW, actionTime);

        }

    }

    public boolean isMoveNow(Entity e) {

        NBTTagCompound nbt = e.getEntityData();

        if (!nbt.hasKey(MOVE_NOW)) return false;
        if (nbt.getLong(MOVE_NOW) != this.actionTime) return false;

        return true;

    }

    public AxisAlignedBB getAABBFromEnumFacing(EnumFacing f) {

        if (f == EnumFacing.EAST || f == EnumFacing.WEST) {
            return CONVEYOR_AABB0;
        } else {
            return CONVEYOR_AABB1;
        }

    }

    public void moveCenter(EnumFacing f, Entity e) {

        //Xの場合
        if (f == EnumFacing.EAST || f == EnumFacing.WEST) {

            double pos = e.posZ % 1.0d;
            if (pos < 0) pos += 1.0d;

            if (pos < 0.4) {
                e.setPosition(e.posX, e.posY, e.posZ + 0.05);
            } else if (0.6 < pos) {
                e.setPosition(e.posX, e.posY, e.posZ - 0.05);
            }

        } else {

            double pos = e.posX % 1.0d;
            if (pos < 0) pos += 1.0d;

            if (pos < 0.4) {

                e.setPosition(e.posX + 0.05, e.posY, e.posZ);

            } else if (0.6 < pos) {

                e.setPosition(e.posX - 0.05, e.posY, e.posZ);

            }

        }

    }

    public boolean hasPower() {
        return true;
    }

    public long getActionTime() {
        return this.actionTime;
    }

    // NBT関係
    @Override
    public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
        super.readFromNBT(par1nbtTagCompound);
        this.topItem.deserializeNBT(par1nbtTagCompound.getCompoundTag("top_item"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound par1nbtTagCompound) {
        NBTTagCompound nbt = super.writeToNBT(par1nbtTagCompound);
        nbt.setTag("top_item", this.topItem.serializeNBT());
        return nbt;
    }

    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 8, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());
        //this.worldObj.markBlockForUpdate(this.pos);
        IBlockState state = this.worldObj.getBlockState(getPos());
        this.worldObj.notifyBlockUpdate(pos, state, state, 3);
    }

}
