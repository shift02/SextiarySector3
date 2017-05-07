package shift.sextiarysector3.api.industry;

import java.util.concurrent.Callable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityShaftHandler {

    @CapabilityInject(IShaft.class)
    public static Capability<IShaft> SHAFT_CAPABILITY = null;

    public static void register() {

        CapabilityManager.INSTANCE.register(IShaft.class, new Capability.IStorage<IShaft>() {
            @Override
            public NBTBase writeNBT(Capability<IShaft> capability, IShaft instance, EnumFacing side) {
                NBTTagCompound tags = new NBTTagCompound();
                tags.setFloat("old_step", instance.getRotateOldStep());
                tags.setFloat("now_step", instance.getRotateNowStep());
                return tags;
            }

            @Override
            public void readNBT(Capability<IShaft> capability, IShaft instance, EnumFacing side, NBTBase nbt) {

                NBTTagCompound tags = (NBTTagCompound) nbt;
                instance.setRotateOldStep(tags.getFloat("old_step"));
                instance.setRotateNowStep(tags.getFloat("now_step"));

            }

        }, new Callable<IShaft>() {

            @Override
            public IShaft call() throws Exception {
                return new IShaft() {

                    float old = 0;
                    float now = 0;
                    EnumFacing f = EnumFacing.WEST;

                    @Override
                    public float getRotateOldStep() {
                        return old;
                    }

                    @Override
                    public float getRotateNowStep() {
                        return now;
                    }

                    @Override
                    public void setRotateOldStep(float step) {
                        old = step;

                    }

                    @Override
                    public void setRotateNowStep(float step) {
                        now = step;

                    }

                    @Override
                    public EnumFacing getFacing() {
                        return f;
                    }
                };
            }
        });
    }

}