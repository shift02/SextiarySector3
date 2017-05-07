package shift.sextiarysector3.tileentity;

import shift.sextiarysector3.api.energy.IGearForceHandler;

public class GFTank implements IGearForceHandler {

    int i = 0;

    @Override
    public int getPower() {
        return i;
    }

    public void setPower(int i) {
        this.i = i;
    }

}