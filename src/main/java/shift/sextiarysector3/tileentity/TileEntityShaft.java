package shift.sextiarysector3.tileentity;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import shift.sextiarysector3.api.energy.CapabilityGearForce;
import shift.sextiarysector3.api.energy.GearForceStorage;
import shift.sextiarysector3.api.energy.IGearForceStorage;
import shift.sextiarysector3.api.industry.CapabilityShaftHandler;
import shift.sextiarysector3.api.industry.IShaft;
import shift.sextiarysector3.api.industry.Shaft;
import shift.sextiarysector3.block.BlockShaft;

public class TileEntityShaft extends TileEntityTickable {

    public int speed;
    public int lastSpeed;

    public Shaft rotateStep;
    public GearForceShaftStorage storageIn;
    public GearForceShaftStorage storageOut;

    public TileEntityShaft() {
        super();

        rotateStep = new Shaft("wood");
        storageIn = new GearForceShaftStorage(1, 16, true, false);
        storageOut = new GearForceShaftStorage(1, 16, false, true);

    }

    @Override
    public void updateClient() {

        if (!isCore()) return;

        rotateStep.setRotateOldStep(rotateStep.getRotateNowStep());
        rotateStep.setRotateNowStep(rotateStep.getRotateNowStep() + speed);

        this.setAllRotate(rotateStep.getRotateOldStep(), rotateStep.getRotateNowStep());

    }

    @Override
    public void updateServer() {

        //Clientと同期
        if (speed != lastSpeed) {
            IBlockState state = this.worldObj.getBlockState(getPos());
            this.worldObj.notifyBlockUpdate(pos, state, state, 3);
        }
        lastSpeed = speed;

        addEnergy();

        speed = storageIn.getSpeedStored();

        storageOut.setPowerSpeed(storageIn.getPowerStored(), speed);

        storageIn.clearSpeed();

    }

    public void addEnergy() {

        if (storageOut.getSpeedStored() == 0) return;

        EnumFacing f = this.getFacing();

        TileEntity te = this.worldObj.getTileEntity(getPos().offset(f));
        if (te == null) return;
        if (!te.hasCapability(CapabilityGearForce.GEAR_FORCE, f.getOpposite())) return;

        IGearForceStorage gfs = te.getCapability(CapabilityGearForce.GEAR_FORCE, f.getOpposite());
        if (!gfs.canReceive()) return;

        gfs.receiveSpeed(storageOut.getPowerStored(), storageOut.getSpeedStored(), false);

    }

    public EnumFacing getFacing() {
        IBlockState state = this.worldObj.getBlockState(getPos());
        EnumFacing f = state.getValue(BlockShaft.FACING);
        return f;
    }

    private boolean isCore() {

        EnumFacing f = this.getFacing().getOpposite();

        TileEntity te = this.worldObj.getTileEntity(getPos().offset(f));
        if (te == null) return true;
        if (!te.hasCapability(CapabilityShaftHandler.SHAFT_CAPABILITY, f.getOpposite())) return true;
        IShaft is = te.getCapability(CapabilityShaftHandler.SHAFT_CAPABILITY, f.getOpposite());
        if (is.getType() != this.rotateStep.getType()) return true;
        if (is.getFacing() != this.getFacing()) return true;

        return false;

    }

    private void setAllRotate(float old, float now) {

        for (int i = 1; isShaft(i); i++) {

            IShaft s = getShaft(i);
            s.setRotateOldStep(old);
            s.setRotateNowStep(now);

        }

    }

    private boolean isShaft(int ofset) {

        EnumFacing f = this.getFacing();

        TileEntity te = this.worldObj.getTileEntity(getPos().offset(f, ofset));

        if (te == null) return false;
        if (!te.hasCapability(CapabilityShaftHandler.SHAFT_CAPABILITY, f.getOpposite())) return false;
        IShaft is = te.getCapability(CapabilityShaftHandler.SHAFT_CAPABILITY, f.getOpposite());
        if (is.getType() != this.rotateStep.getType()) return false;
        if (is.getFacing() != this.getFacing()) return false;

        return true;

    }

    private IShaft getShaft(int ofset) {

        EnumFacing f = this.getFacing();

        TileEntity te = this.worldObj.getTileEntity(getPos().offset(f, ofset));

        return te.getCapability(CapabilityShaftHandler.SHAFT_CAPABILITY, f.getOpposite());

    }

    protected int getGFPower() {
        return 1;
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

    //Cap
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

        if (capability == CapabilityShaftHandler.SHAFT_CAPABILITY) {
            return true;
        }
        if (capability == CapabilityGearForce.GEAR_FORCE && this.hasGearForce(facing)) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityShaftHandler.SHAFT_CAPABILITY) {
            rotateStep.setFacing(getFacing());
            return (T) rotateStep;
        }
        if (capability == CapabilityGearForce.GEAR_FORCE && this.getFacing().getOpposite() == facing) {
            return (T) storageIn;
        }
        if (capability == CapabilityGearForce.GEAR_FORCE && this.getFacing() == facing) {
            return (T) storageOut;
        }
        if (capability == CapabilityGearForce.GEAR_FORCE && null == facing) {
            return (T) storageOut;
        }

        return super.getCapability(capability, facing);
    }

    public boolean hasGearForce(EnumFacing facing) {

        if (facing == null) return true;
        if (this.getFacing() == facing) return true;
        if (this.getFacing().getOpposite() == facing) return true;

        return false;

    }

    public class GearForceShaftStorage extends GearForceStorage {

        public GearForceShaftStorage(int power, int capacity, boolean isReceive, boolean isExtract) {
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

}
