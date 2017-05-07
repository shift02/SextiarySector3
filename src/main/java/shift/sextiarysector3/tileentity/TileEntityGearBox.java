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
import shift.sextiarysector3.api.energy.CapabilityGearForceHandler;
import shift.sextiarysector3.api.energy.IGearForceHandler;

public class TileEntityGearBox extends TileEntity implements ITickable {

    public GFTank tank;

    public boolean[] oldOutPowers;

    public TileEntityGearBox() {
        tank = new GFTank();
        this.oldOutPowers = new boolean[6];
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

        this.tank.setPower(0);

        int power = this.getSidePower();

        int size = 0;
        for (int i = 0; i < this.oldOutPowers.length; i++) {
            if (this.oldOutPowers[i]) size++;
        }

        if (size == 0) {
            this.tank.setPower(power);
        } else {
            this.tank.setPower(power / size);
        }

        for (int i = 0; i < this.oldOutPowers.length; i++) {
            this.oldOutPowers[i] = false;
        }

    }

    private int getSidePower() {

        int i = 0;
        int power = 0;

        for (EnumFacing f : EnumFacing.VALUES) {

            TileEntity te = this.worldObj.getTileEntity(getPos().offset(f));

            if (te == null) continue;
            if (!te.hasCapability(CapabilityGearForceHandler.GEAR_FORCE_CAPABILITY, f.getOpposite())) continue;

            IGearForceHandler gfh = te.getCapability(CapabilityGearForceHandler.GEAR_FORCE_CAPABILITY, f.getOpposite());

            if (gfh.getPower() == 0) continue;

            if (i == 0) {

                i = gfh.getPower();
                power = i;

            } else {

                if (i != gfh.getPower()) {
                    //TODO 壊れる
                    i = 0;
                    power = 0;
                    break;
                } else {
                    power += gfh.getPower();
                }

            }

        }

        return power;

    }

    // NBT関係
    @Override
    public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
        super.readFromNBT(par1nbtTagCompound);
        tank.setPower(par1nbtTagCompound.getInteger("power"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound par1nbtTagCompound) {
        NBTTagCompound nbt = super.writeToNBT(par1nbtTagCompound);
        nbt.setInteger("power", tank.getPower());
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

    //C
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

        if (capability == CapabilityGearForceHandler.GEAR_FORCE_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {

        if (capability == CapabilityGearForceHandler.GEAR_FORCE_CAPABILITY) {

            if (facing != null) this.oldOutPowers[facing.getIndex()] = true;
            return (T) tank;

        }

        return super.getCapability(capability, facing);
    }

}
