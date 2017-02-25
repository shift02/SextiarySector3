package shift.sextiarysector3.api.energy;

import net.minecraft.util.EnumFacing;

public class Shaft implements IShaft {

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

    public void setFacing(EnumFacing facing) {
        this.f = facing;
    }

}
