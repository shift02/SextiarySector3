package shift.sextiarysector3.tileentity;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import shift.sextiarysector3.block.BlockConveyor;
import shift.sextiarysector3.entity.EntityConveyorItem;
import shift.sextiarysector3.util.UtilCompat;

public class TileEntityConveyor extends TileEntity implements ITickable {

    protected static final AxisAlignedBB CONVEYOR_AABB0 = new AxisAlignedBB(0.0D, 0.0D, 0.1875D, 1.0D, 0.25D, 0.8125D);
    protected static final AxisAlignedBB CONVEYOR_AABB1 = new AxisAlignedBB(0.1875D, 0.0D, 0.0D, 0.8125D, 0.25D, 1.0D);

    public long actionTime = 0;

    public final String MOVE_NOW = "move_now";

    //インベントリ
    protected ItemStackHandler topItem = (ItemStackHandler) CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getDefaultInstance();

    //6
    protected ItemStackHandler inItem = (ItemStackHandler) CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getDefaultInstance();
    protected ItemStackHandler outItem = (ItemStackHandler) CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getDefaultInstance();

    //5
    protected ItemStackHandler centerItem = (ItemStackHandler) CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getDefaultInstance();

    //6
    protected ItemStackHandler leftItem = (ItemStackHandler) CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getDefaultInstance();
    protected ItemStackHandler rightItem = (ItemStackHandler) CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getDefaultInstance();

    @Override
    public void update() {

        actionTime = worldObj.getWorldInfo().getWorldTime();

        List<Entity> list = this.getOnEntity();

        if (worldObj.isRemote) {
            this.updateClient();
        } else {
            this.updateServer(list);
        }

        if (this.hasPower()) moveEntity(list);

    }

    public void updateClient() {

    }

    public void updateServer(List<Entity> list) {

        actionTime = worldObj.getWorldInfo().getWorldTime();

        moveItemStack();

        changeEntityItem(list);

        spawnItem();

    }

    public List<Entity> getOnEntity() {
        EnumFacing f = worldObj.getBlockState(getPos()).getValue(BlockConveyor.FACING);

        AxisAlignedBB axisalignedbb = getAABBFromEnumFacing(f).offset(getPos());

        List<Entity> list = worldObj.getEntitiesWithinAABBExcludingEntity((Entity) null, axisalignedbb);

        return list;
    }

    public void moveEntity(List<Entity> list) {

        EnumFacing f = worldObj.getBlockState(getPos()).getValue(BlockConveyor.FACING);

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

    public void changeEntityItem(List<Entity> list) {

        EnumFacing f = worldObj.getBlockState(getPos()).getValue(BlockConveyor.FACING);

        for (Entity e : list) {
            if (e instanceof EntityConveyorItem) continue;
            if (!(e instanceof EntityItem)) continue;
            if (e.isDead) continue;

            EntityConveyorItem ec = new EntityConveyorItem(worldObj, e.posX, e.posY + 0.3, e.posZ, ((EntityItem) e).getEntityItem());
            ec.delayBeforeCanPickup = ((EntityItem) e).delayBeforeCanPickup;
            ec.motionX = 0;
            ec.motionY = 0;
            ec.motionZ = 0;

            worldObj.spawnEntityInWorld(ec);
            e.setDead();

        }

    }

    public void spawnItem() {

        if (!UtilCompat.isNullFromItemStack(topItem.getStackInSlot(0))) {

            EntityConveyorItem ec = new EntityConveyorItem(worldObj, this.pos.getX() + 0.5, this.pos.getY() + 0.6, this.pos.getZ() + 0.5, topItem.extractItem(0, 64, false));
            ec.delayBeforeCanPickup = 80;
            ec.motionX = 0;
            ec.motionY = 0;
            ec.motionZ = 0;

            worldObj.spawnEntityInWorld(ec);

            //topItem.setStackInSlot(0, UtilCompat.getNullItemStack());

        }

    }

    //ItemStackの移動処理
    public void moveItemStack() {

    }

    public boolean isItemUpdate() {

        return true;
    }

    public boolean hasPower() {
        return true;
    }

    public long getActionTime() {
        return this.actionTime;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

        EnumFacing f = this.worldObj.getBlockState(getPos()).getValue(BlockConveyor.FACING);

        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && facing == EnumFacing.UP) {
            return true;
        }

        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && facing == f.getOpposite()) {
            return true;
        }

        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && facing == f) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {

        EnumFacing f = this.worldObj.getBlockState(getPos()).getValue(BlockConveyor.FACING);

        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && facing == EnumFacing.UP) {
            return (T) this.topItem;
        }

        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && facing == f.getOpposite()) {
            return (T) this.inItem;
        }

        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && facing == f) {
            return (T) this.inItem;
        }

        return super.getCapability(capability, facing);
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
