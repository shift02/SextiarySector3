package shift.sextiarysector3.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityTickable extends TileEntity implements ITickable {

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

    public void updateServer() {

    }

}
