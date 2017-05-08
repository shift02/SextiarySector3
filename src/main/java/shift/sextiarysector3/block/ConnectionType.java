package shift.sextiarysector3.block;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IStringSerializable;

public enum ConnectionType implements IStringSerializable {
    NORMAL, IN, OUT, BLOCK;

    @Override
    public String getName() {
        return this.name().toLowerCase();
    }

    public static ConnectionType[] newList() {

        ConnectionType[] types = { ConnectionType.NORMAL, ConnectionType.NORMAL, ConnectionType.NORMAL, ConnectionType.NORMAL, ConnectionType.NORMAL, ConnectionType.NORMAL };

        return types;

    }

    public static ConnectionType[] readFromNBT(NBTTagCompound par1nbtTagCompound) {

        ConnectionType[] types = newList();

        if (par1nbtTagCompound.hasKey("connectiot_types")) {

            NBTTagCompound nbt = par1nbtTagCompound.getCompoundTag("connectiot_types");

            int size = nbt.getInteger("size");
            types = new ConnectionType[size];

            for (int i = 0; i < size; i++) {
                types[i] = values()[nbt.getInteger("type_" + i)];
            }

        }

        return types;

    }

    public static NBTTagCompound writeToNBT(NBTTagCompound par1nbtTagCompound, ConnectionType[] tyeps) {

        NBTTagCompound nbt = new NBTTagCompound();
        par1nbtTagCompound.setTag("connectiot_types", nbt);

        int size = tyeps.length;
        nbt.setInteger("size", size);

        for (int i = 0; i < size; i++) {
            nbt.setInteger("type_" + i, tyeps[i].ordinal());
        }

        return par1nbtTagCompound;

    }

}
