package shift.sextiarysector3.api.energy;

import java.util.concurrent.Callable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityGearForceHandler {

    @CapabilityInject(IGearForceHandler.class)
    public static Capability<IGearForceHandler> GEAR_FORCE_CAPABILITY = null;

    public static void register() {

        CapabilityManager.INSTANCE.register(IGearForceHandler.class, new Capability.IStorage<IGearForceHandler>() {
            @Override
            public NBTBase writeNBT(Capability<IGearForceHandler> capability, IGearForceHandler instance, EnumFacing side) {
                NBTTagCompound tags = new NBTTagCompound();
                return tags;
            }

            @Override
            public void readNBT(Capability<IGearForceHandler> capability, IGearForceHandler instance, EnumFacing side, NBTBase nbt) {

                NBTTagCompound tags = (NBTTagCompound) nbt;

            }
        }, new Callable<IGearForceHandler>() {
            @Override
            public IGearForceHandler call() throws Exception {
                return new IGearForceHandler() {
                    @Override
                    public int getPower() {
                        return 0;
                    }
                };
            }
        });
    }

}
