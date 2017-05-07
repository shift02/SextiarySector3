package shift.sextiarysector3.api.industry;

import net.minecraft.util.EnumFacing;

public interface IShaft {

    public String getType();

    public float getRotateOldStep();

    public float getRotateNowStep();

    public void setRotateOldStep(float step);

    public void setRotateNowStep(float step);

    public EnumFacing getFacing();

}
