package shift.sextiarysector3.tileentity;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import shift.sextiarysector3.api.energy.CapabilityGearForce;
import shift.sextiarysector3.api.energy.IGearForceStorage;
import shift.sextiarysector3.block.BlockSmallWindmill;

public class TileEntitySmallWindmill extends TileEntity implements ITickable {

    public GFPowerSource gf;

    //public GFTank tank;

    public float rotateStep = 0;

    public boolean work = false;
    public boolean oldWork = false;

    public TileEntitySmallWindmill() {
        super();

        //tank = new GFTank();

        this.gf = new GFPowerSource(1, 8);

    }

    @Override
    public void update() {

        if (this.worldObj.isRemote) {
            this.updateClientEntity();
        } else {

            this.updateServerEntity();

        }

    }

    public void updateClientEntity() {

        if (!this.isWorkR()) {
            return;
        }

        if (this.rotateStep > 360) {
            this.rotateStep -= 360;
        }

        this.rotateStep += 2;

    }

    public void updateServerEntity() {

        work = this.isWork();

        if (work != oldWork) {
            IBlockState state = this.worldObj.getBlockState(getPos());
            this.worldObj.notifyBlockUpdate(pos, state, state, 3);
        }

        if (work) {
            this.addEnergy();
        }

        oldWork = work;

    }

    public boolean isWork() {

        EnumFacing d1 = this.getFacing().rotateAround(Axis.Y);

        EnumFacing d2 = this.getFacing();

        int ra1 = 2;
        for (int i = ra1 * -1; i <= ra1; i++) {

            int ra2 = 2;
            for (int j = ra2 * -1; j <= ra2; j++) {

                //他の風車を調べる
                int ra3 = 2;
                for (int k = ra3 * -1; k <= ra3; k++) {

                    //int x = this.getPos().getX() + d1.getFrontOffsetX() * j;
                    //int y = this.getPos().getY() + i;
                    //int z = this.getPos().getZ() + d1.getFrontOffsetZ() * j;

                    BlockPos pos = this.getPos().offset(d1, j).offset(EnumFacing.UP, i);

                    if (i == 0 && j == 0) continue;

                    if (this.worldObj.getTileEntity(pos.offset(d2, k)) instanceof TileEntitySmallWindmill && k != 0) return false;

                    if (!this.worldObj.isAirBlock(pos)) return false;

                }

            }

        }

        return true;
    }

    public void addEnergy() {

        EnumFacing f = this.getFacing().getOpposite();

        TileEntity te = this.worldObj.getTileEntity(getPos().offset(f));
        if (te == null) return;
        if (!te.hasCapability(CapabilityGearForce.GEAR_FORCE, f.getOpposite())) return;

        IGearForceStorage gfs = te.getCapability(CapabilityGearForce.GEAR_FORCE, f.getOpposite());
        if (!gfs.canReceive()) return;

        gfs.receiveSpeed(gf.getPowerStored(), gf.getSpeedStored(), false);

    }

    public boolean isWorkR() {
        return this.work;
    }

    public float getRotateStep() {
        return -rotateStep;
    }

    public EnumFacing getFacing() {
        IBlockState state = this.worldObj.getBlockState(getPos());
        EnumFacing f = state.getValue(BlockSmallWindmill.FACING);
        return f;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        EnumFacing f = this.getFacing().getOpposite();

        /*
        if (capability == CapabilityGearForceHandler.GEAR_FORCE_CAPABILITY && f == facing) {
            return true;
        }*/
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {

        EnumFacing f = this.getFacing().getOpposite();

        /*
        if (capability == CapabilityGearForceHandler.GEAR_FORCE_CAPABILITY && f == facing) {
            tank.setPower(work ? 4 : 0);
            return (T) tank;
        }*/
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean canRenderBreaking() {
        return true;
    }

    // NBT関係
    @Override
    public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
        super.readFromNBT(par1nbtTagCompound);
        this.work = par1nbtTagCompound.getBoolean("work");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound par1nbtTagCompound) {
        NBTTagCompound nbt = super.writeToNBT(par1nbtTagCompound);
        nbt.setBoolean("work", work);
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

}
