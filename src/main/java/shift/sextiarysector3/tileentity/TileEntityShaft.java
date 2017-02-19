package shift.sextiarysector3.tileentity;

import net.minecraft.tileentity.TileEntity;
import shift.sextiarysector3.api.energy.IShaft;

public class TileEntityShaft extends TileEntity implements IShaft {

    @Override
    public boolean canRenderBreaking() {
        return true;
    }

    @Override
    public int getRotateOldStep() {
        // TODO 自動生成されたメソッド・スタブ
        return 0;
    }

    @Override
    public int getRotateNowStep() {
        // TODO 自動生成されたメソッド・スタブ
        return 0;
    }

}
