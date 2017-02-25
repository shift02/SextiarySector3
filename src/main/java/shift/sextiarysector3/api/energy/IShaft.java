package shift.sextiarysector3.api.energy;

import net.minecraft.util.EnumFacing;

public interface IShaft {

    public float getRotateOldStep();

    public float getRotateNowStep();

    public void setRotateOldStep(float step);

    public void setRotateNowStep(float step);

    public EnumFacing getFacing();

}
