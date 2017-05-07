package shift.sextiarysector3.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import shift.sextiarysector3.api.energy.CapabilityGearForce;
import shift.sextiarysector3.api.energy.IGearForceStorage;

public class TileEntityCreativeGFTank extends TileEntityTickable {

    public GFPowerSource gf;

    public TileEntityCreativeGFTank() {
        super();
        this.gf = new GFPowerSource(1, 20);
    }

    @Override
    public void updateServer() {

        for (EnumFacing f : EnumFacing.VALUES) {

            TileEntity te = this.worldObj.getTileEntity(getPos().offset(f));
            if (te == null) continue;
            if (!te.hasCapability(CapabilityGearForce.GEAR_FORCE, f.getOpposite())) continue;

            IGearForceStorage gfs = te.getCapability(CapabilityGearForce.GEAR_FORCE, f.getOpposite());
            if (!gfs.canReceive()) continue;

            gfs.receiveSpeed(gf.getPowerStored(), gf.getSpeedStored(), false);

        }

    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityGearForce.GEAR_FORCE) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityGearForce.GEAR_FORCE) {
            return (T) gf;
        }
        return super.getCapability(capability, facing);
    }

    /*
    public IGearForceHandler tank;
    
    public TileEntityCreativeGFTank() {
        super();
    
        tank = CapabilityGearForceHandler.GEAR_FORCE_CAPABILITY.getDefaultInstance();
    
    }
    
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
            return (T) tank;
        }
        return super.getCapability(capability, facing);
    }*/

}
