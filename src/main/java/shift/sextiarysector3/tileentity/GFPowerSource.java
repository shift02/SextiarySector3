package shift.sextiarysector3.tileentity;

import shift.sextiarysector3.api.energy.IGearForceStorage;

public class GFPowerSource implements IGearForceStorage {

    private int power;
    private int speed;

    public GFPowerSource(int power, int speed) {
        this.power = power;
        this.speed = speed;
    }

    @Override
    public int receiveSpeed(int power, int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractSpeed(int power, int maxExtract, boolean simulate) {
        return speed;
    }

    @Override
    public int getSpeedStored() {
        return speed;
    }

    @Override
    public int getMaxSpeedStored() {
        return speed;
    }

    @Override
    public int getMinPowerWorked() {
        return power;
    }

    @Override
    public int getPowerStored() {
        return power;
    }

    @Override
    public int getMaxPowerWorked() {
        return power;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return false;
    }

}
