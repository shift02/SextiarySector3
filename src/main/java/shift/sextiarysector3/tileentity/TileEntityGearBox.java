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
import shift.sextiarysector3.api.energy.CapabilityGearForce;
import shift.sextiarysector3.api.energy.GearForceStorage;
import shift.sextiarysector3.api.energy.IGearForceStorage;
import shift.sextiarysector3.block.ConnectionType;

public class TileEntityGearBox extends TileEntity implements ITickable {

    ///public GFTank tank;

    public boolean[] oldOutPowers;

    public ConnectionType[] connects;

    public GearForceGearBoxStorage[] storagesIn;
    public GearForceGearBoxStorage[] storagesOut;

    public TileEntityGearBox() {
        //tank = new GFTank();
        this.oldOutPowers = new boolean[6];
        this.connects = ConnectionType.newList();

        this.storagesIn = new GearForceGearBoxStorage[6];
        for (int i = 0; i < this.storagesIn.length; i++) {
            this.storagesIn[i] = new GearForceGearBoxStorage(getGFPower(), 32, true, false);
        }

        this.storagesOut = new GearForceGearBoxStorage[6];
        for (int i = 0; i < this.storagesIn.length; i++) {
            this.storagesOut[i] = new GearForceGearBoxStorage(getGFPower(), 32, false, true);
        }

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

    }

    private void updateServer() {

        for (GearForceGearBoxStorage st : storagesOut) {
            st.clearSpeed();
        }

        int speed = this.getSideSpeed();

        int size = 0;
        for (ConnectionType con : connects) {
            if (con == ConnectionType.OUT) size++;
        }

        if (size > 1) {
            speed = speed / size;
        }

        if (speed != 0) addEnergy(speed);

    }

    private int getSideSpeed() {

        int i = 0;
        int speed = 0;

        for (GearForceGearBoxStorage gfh : storagesIn) {

            if (gfh.getPowerStored() == 0) continue;

            if (i == 0) {

                i = gfh.getSpeedStored();
                speed = i;

            } else {

                if (i != gfh.getSpeedStored()) {
                    //TODO 壊れる
                    i = 0;
                    speed = 0;
                    break;

                } else {
                    speed += gfh.getSpeedStored();
                }

            }

            gfh.clearSpeed();

        }

        return speed;

    }

    public void addEnergy(int speed) {

        for (EnumFacing f : EnumFacing.VALUES) {
            if (connects[f.getIndex()] == ConnectionType.OUT) {

                TileEntity te = this.worldObj.getTileEntity(getPos().offset(f));
                if (te == null) continue;
                if (!te.hasCapability(CapabilityGearForce.GEAR_FORCE, f.getOpposite())) continue;

                IGearForceStorage gfs = te.getCapability(CapabilityGearForce.GEAR_FORCE, f.getOpposite());
                if (!gfs.canReceive()) continue;

                gfs.receiveSpeed(this.getGFPower(), speed, false);

            }
        }

    }

    public void changeConnect(EnumFacing side) {

        ConnectionType ct = connects[side.getIndex()];
        if (ct.ordinal() > 2) {
            connects[side.getIndex()] = ConnectionType.values()[0];
        } else {
            connects[side.getIndex()] = ConnectionType.values()[ct.ordinal() + 1];
        }

        IBlockState state = this.worldObj.getBlockState(getPos());
        this.worldObj.notifyBlockUpdate(pos, state, state, 3);

    }

    public ConnectionType getConnectionType(EnumFacing side) {
        return this.connects[side.getIndex()];
    }

    protected int getGFPower() {
        return 1;
    }

    // NBT関係
    @Override
    public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
        super.readFromNBT(par1nbtTagCompound);
        //tank.setPower(par1nbtTagCompound.getInteger("power"));
        this.connects = ConnectionType.readFromNBT(par1nbtTagCompound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound par1nbtTagCompound) {
        NBTTagCompound nbt = super.writeToNBT(par1nbtTagCompound);
        //nbt.setInteger("power", tank.getPower());
        nbt = ConnectionType.writeToNBT(nbt, connects);
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

    //Cap
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

        if (capability == CapabilityGearForce.GEAR_FORCE && this.hasConnect(facing)) {
            return true;
        }

        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {

        if (capability == CapabilityGearForce.GEAR_FORCE && this.connects[facing.getIndex()] == ConnectionType.IN) {
            return (T) this.storagesIn[facing.getIndex()];
        }

        if (capability == CapabilityGearForce.GEAR_FORCE && this.connects[facing.getIndex()] == ConnectionType.OUT) {
            return (T) this.storagesOut[facing.getIndex()];
        }

        return super.getCapability(capability, facing);

    }

    public boolean hasConnect(EnumFacing facing) {

        if (this.connects[facing.getIndex()] == ConnectionType.IN) return true;
        if (this.connects[facing.getIndex()] == ConnectionType.OUT) return true;

        return false;

    }

    public class GearForceGearBoxStorage extends GearForceStorage {

        public GearForceGearBoxStorage(int power, int capacity, boolean isReceive, boolean isExtract) {
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
