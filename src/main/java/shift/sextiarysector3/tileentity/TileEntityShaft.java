package shift.sextiarysector3.tileentity;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import shift.sextiarysector3.api.energy.CapabilityShaftHandler;
import shift.sextiarysector3.api.energy.IShaft;

public class TileEntityShaft extends TileEntity implements IShaft, ITickable {

    public int speed;
    public int lastSpeed;

    //public float rotateStep = 360;
    //public float lastRotateStep = 360;

    public IShaft rotateStep = CapabilityShaftHandler.SHAFT_CAPABILITY.getDefaultInstance();

    @Override
    public boolean canRenderBreaking() {
        return true;
    }

    @Override
    public float getRotateOldStep() {
        return rotateStep.getRotateOldStep();
    }

    @Override
    public float getRotateNowStep() {
        return rotateStep.getRotateNowStep();
    }

    @Override
    public void update() {
        if (worldObj.isRemote) {
            this.updateClient();
        } else {
            this.updateServer();
        }
    }

    public void updateClient() {

        rotateStep.setRotateOldStep(this.getRotateNowStep());
        rotateStep.setRotateNowStep(getRotateNowStep() + speed);

    }

    public void updateServer() {

        if (speed != lastSpeed) {
            IBlockState state = this.worldObj.getBlockState(getPos());
            this.worldObj.notifyBlockUpdate(pos, state, state, 3);
            lastSpeed = speed;
        }

    }

    // NBT関係
    @Override
    public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
        super.readFromNBT(par1nbtTagCompound);
        this.speed = par1nbtTagCompound.getInteger("speed");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound par1nbtTagCompound) {
        NBTTagCompound nbt = super.writeToNBT(par1nbtTagCompound);
        nbt.setInteger("speed", speed);
        return nbt;
    }

    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 8, this.getUpdateTag());
    }

    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());
        //this.worldObj.markBlockForUpdate(this.pos);
        IBlockState state = this.worldObj.getBlockState(getPos());
        this.worldObj.notifyBlockUpdate(pos, state, state, 3);
    }

    @Override
    public void setRotateOldStep(float step) {

        this.rotateStep.setRotateOldStep(step);

    }

    @Override
    public void setRotateNowStep(float step) {

        this.rotateStep.setRotateNowStep(step);

    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityShaftHandler.SHAFT_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityShaftHandler.SHAFT_CAPABILITY) {
            return (T) rotateStep;
        }
        return super.getCapability(capability, facing);
    }

}
