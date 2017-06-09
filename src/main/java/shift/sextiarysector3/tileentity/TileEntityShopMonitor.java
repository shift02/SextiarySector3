package shift.sextiarysector3.tileentity;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import shift.sextiarysector3.api.energy.CapabilityGearForce;
import shift.sextiarysector3.api.energy.GearForceStorage;
import shift.sextiarysector3.block.BlockShopMonitor;

public class TileEntityShopMonitor extends TileEntity implements ITickable {

    public GearForceShopStorage storage = new GearForceShopStorage(1, 16, true, false);
    protected ItemStackHandler items = (ItemStackHandler) CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getDefaultInstance();

    //public boolean on;

    public int oldS = 0;

    @Override
    public void update() {

        if (this.worldObj.isRemote) return;

        if (this.storage.getSpeedStored() == 0 && getOn()) {

            this.changeON();

        }

        oldS = this.storage.getSpeedStored();

        this.storage.clearSpeed();

    }

    public void changeON() {

        if (!this.worldObj.isRemote) {
            if (!this.getOn() && oldS > 0) {

                //this.on = true;
                IBlockState state = this.worldObj.getBlockState(getPos());

                this.worldObj.setBlockState(pos, state.withProperty(BlockShopMonitor.SWITCH, Boolean.valueOf(true)), 3);

            } else {

                //this.on = false;
                IBlockState state = this.worldObj.getBlockState(getPos());
                //this.worldObj.notifyBlockUpdate(pos, state, state.withProperty(BlockShopMonitor.SWITCH, Boolean.valueOf(false)), 3);
                this.worldObj.setBlockState(pos, state.withProperty(BlockShopMonitor.SWITCH, Boolean.valueOf(false)), 3);

            }

            //this.worldObj.playSoundEffect(getPos().add(0.5, 0.5, 0.5), "random.click", 0.3F, 0.6F);

            this.worldObj.playSound(getPos().getX() + 0.5, getPos().getY() + 0.5, getPos().getZ() + 0.5, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, 0.6F, true);

        }

        //this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    public ItemStack getMemory() {

        return this.items.getStackInSlot(0);

    }

    public void setMemory(ItemStack item) {

        if (item != null) item.stackSize = 1;

        this.items.setStackInSlot(0, item);

    }

    public boolean getOn() {
        return this.worldObj.getBlockState(getPos()).getValue(BlockShopMonitor.SWITCH);
    }

    public void setOn(boolean on) {
        this.worldObj.getBlockState(getPos()).withProperty(BlockShopMonitor.SWITCH, Boolean.valueOf(on));
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

        if (capability == CapabilityGearForce.GEAR_FORCE && facing == EnumFacing.DOWN) {
            return true;
        }
        return super.hasCapability(capability, facing);

    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {

        if (capability == CapabilityGearForce.GEAR_FORCE && facing == EnumFacing.DOWN) {
            return (T) this.storage;
        }

        return super.getCapability(capability, facing);
    }

    //NBT関係
    @Override
    public void readFromNBT(NBTTagCompound nbt) {

        super.readFromNBT(nbt);

        CapabilityGearForce.GEAR_FORCE.readNBT(storage, null, nbt.getTag("speed"));

        //this.on = nbt.getBoolean("on");

        items.deserializeNBT(nbt.getCompoundTag("item"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

        super.writeToNBT(nbt);

        nbt.setTag("speed", CapabilityGearForce.GEAR_FORCE.writeNBT(storage, null));

        //nbt.setBoolean("on", on);

        nbt.setTag("item", items.serializeNBT());

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
        IBlockState state = this.worldObj.getBlockState(getPos());
        this.worldObj.notifyBlockUpdate(pos, state, state, 3);
    }

    public class GearForceShopStorage extends GearForceStorage {

        public GearForceShopStorage(int power, int capacity, boolean isReceive, boolean isExtract) {
            super(power, capacity, isReceive, isExtract);
        }

        protected void clearSpeed() {
            this.power = 0;
            this.speed = 0;
        }

        protected void setPowerSpeed(int pw, int sp) {
            this.power = pw;
            this.speed = sp;
        }

    }

    @Override
    public boolean canRenderBreaking() {
        return true;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }

}
