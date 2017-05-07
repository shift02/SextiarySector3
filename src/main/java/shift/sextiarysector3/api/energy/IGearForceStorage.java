package shift.sextiarysector3.api.energy;

public interface IGearForceStorage {

    int receiveSpeed(int power, int maxReceive, boolean simulate);

    int extractSpeed(int power, int maxExtract, boolean simulate);

    int getSpeedStored();

    int getMaxSpeedStored();

    /** 動作する最小のパワー */
    int getMinPowerWorked();

    /** 動作する現在のパワー */
    int getPowerStored();

    /** 動作する最大のパワー */
    int getMaxPowerWorked();

    boolean canExtract();

    boolean canReceive();

}
