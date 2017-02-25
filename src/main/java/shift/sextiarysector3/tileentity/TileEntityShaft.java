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
import shift.sextiarysector3.api.energy.Shaft;
import shift.sextiarysector3.block.BlockShaft;

public class TileEntityShaft extends TileEntity implements IShaft, ITickable {

    public int speed;
    public int lastSpeed;

    //public float rotateStep = 360;
    //public float lastRotateStep = 360;

    public Shaft rotateStep;

    public TileEntityShaft() {
        super();

        rotateStep = new Shaft();

    }

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
    public EnumFacing getFacing() {
        IBlockState state = this.worldObj.getBlockState(getPos());
        EnumFacing f = state.getValue(BlockShaft.FACING);
        return f;
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

        if (!isCore()) return;

        rotateStep.setRotateOldStep(this.getRotateNowStep());
        rotateStep.setRotateNowStep(getRotateNowStep() + speed);

        this.setAllRotate(getRotateOldStep(), getRotateNowStep());

    }

    public void updateServer() {

        if (speed != lastSpeed) {
            IBlockState state = this.worldObj.getBlockState(getPos());
            this.worldObj.notifyBlockUpdate(pos, state, state, 3);
            lastSpeed = speed;
        }

    }

    private boolean isCore() {

        EnumFacing f = this.getFacing();
        if (f.getAxisDirection() == EnumFacing.AxisDirection.NEGATIVE) f = f.getOpposite();
        TileEntity tile = this.worldObj.getTileEntity(getPos().offset(f));

        if (tile == null) return true;
        if (!tile.hasCapability(CapabilityShaftHandler.SHAFT_CAPABILITY, f)) return true;

        EnumFacing f2 = tile.getCapability(CapabilityShaftHandler.SHAFT_CAPABILITY, f).getFacing();
        if (f2.getAxisDirection() == EnumFacing.AxisDirection.NEGATIVE) f2 = f2.getOpposite();
        if (f2 == f) return true;

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
        if (f.getAxisDirection() == EnumFacing.AxisDirection.POSITIVE) f = f.getOpposite();
        TileEntity tile = this.worldObj.getTileEntity(getPos().offset(f, ofset));

        if (tile == null) return false;
        if (!tile.hasCapability(CapabilityShaftHandler.SHAFT_CAPABILITY, f)) return false;

        EnumFacing f2 = tile.getCapability(CapabilityShaftHandler.SHAFT_CAPABILITY, f).getFacing();
        if (f2.getAxisDirection() == EnumFacing.AxisDirection.POSITIVE) f2 = f2.getOpposite();
        if (f2 != f) return false;

        return true;

    }

    private IShaft getShaft(int ofset) {

        EnumFacing f = this.getFacing();
        if (f.getAxisDirection() == EnumFacing.AxisDirection.POSITIVE) f = f.getOpposite();
        TileEntity tile = this.worldObj.getTileEntity(getPos().offset(f, ofset));

        return tile.getCapability(CapabilityShaftHandler.SHAFT_CAPABILITY, f);
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
            rotateStep.setFacing(getFacing());
            return (T) rotateStep;
        }
        return super.getCapability(capability, facing);
    }

}
