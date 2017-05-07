package shift.sextiarysector3.api.energy;

import java.util.concurrent.Callable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityGearForce {

    @CapabilityInject(IGearForceStorage.class)
    public static Capability<IGearForceStorage> GEAR_FORCE = null;

    public static void register() {

        CapabilityManager.INSTANCE.register(IGearForceStorage.class,
                new IStorage<IGearForceStorage>() {
                    @Override
                    public NBTBase writeNBT(Capability<IGearForceStorage> capability, IGearForceStorage instance, EnumFacing side) {
                        return new NBTTagInt(instance.getSpeedStored());
                    }

                    @Override
                    public void readNBT(Capability<IGearForceStorage> capability, IGearForceStorage instance, EnumFacing side, NBTBase nbt) {

                        //((EnergyStorage) instance).energy = ((NBTTagInt) nbt).getInt();

                    }
                },
                new Callable<IGearForceStorage>() {
                    @Override
                    public IGearForceStorage call() throws Exception {
                        return new GearForceStorage();
                    }
                });

    }

}
