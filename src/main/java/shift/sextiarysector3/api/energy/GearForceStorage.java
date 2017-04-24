package shift.sextiarysector3.api.energy;

public class GearForceStorage implements IGearForceStorage {

    @Override
    public int receiveSpeed(int power, int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractSpeed(int power, int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getSpeedStored() {
        return 0;
    }

    @Override
    public int getMaxSpeedStored() {
        return 0;
    }

    @Override
    public int getMinPowerWorked() {
        return 0;
    }

    @Override
    public int getPowerStored() {
        return 0;
    }

    @Override
    public int getMaxPowerWorked() {
        return 0;
    }

    @Override
    public boolean canExtract() {
        return false;
    }

    @Override
    public boolean canReceive() {
        return false;
    }

}
