package shift.sextiarysector3.tileentity;

import java.util.concurrent.Callable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityGFShaftHandler {

    @CapabilityInject(IGFShaft.class)
    public static Capability<IGFShaft> GEAR_FORCE_CAPABILITY = null;

    public static void register() {

        CapabilityManager.INSTANCE.register(IGFShaft.class, new Capability.IStorage<IGFShaft>() {
            @Override
            public NBTBase writeNBT(Capability<IGFShaft> capability, IGFShaft instance, EnumFacing side) {
                NBTTagCompound tags = new NBTTagCompound();
                return tags;
            }

            @Override
            public void readNBT(Capability<IGFShaft> capability, IGFShaft instance, EnumFacing side, NBTBase nbt) {

                NBTTagCompound tags = (NBTTagCompound) nbt;

            }
        }, new Callable<IGFShaft>() {
            @Override
            public IGFShaft call() throws Exception {
                return new IGFShaft() {

                    int p = 0;

                    @Override
                    public int getPower() {
                        return p;
                    }

                    @Override
                    public void setPower(int power) {
                        p = power;

                    }

                };
            }
        });
    }

}
