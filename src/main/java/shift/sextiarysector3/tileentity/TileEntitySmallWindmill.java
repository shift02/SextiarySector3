package shift.sextiarysector3.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import shift.sextiarysector3.api.energy.CapabilityGearForceHandler;
import shift.sextiarysector3.api.energy.IGearForceHandler;

public class TileEntitySmallWindmill extends TileEntity {

    public IGearForceHandler tank;

    public TileEntitySmallWindmill() {
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
    }
}
