package shift.sextiarysector3.api.energy;

public class GearForceStorage implements IGearForceStorage {

    protected int power;
    protected int powerCapacity;
    protected int speed;
    protected int speedCapacity;

    protected boolean isReceive;
    protected boolean isExtract;

    public GearForceStorage(int power, int capacity) {
        this(power, capacity, true, true);
    }

    public GearForceStorage(int power, int capacity, boolean isReceive, boolean isExtract) {
        this.powerCapacity = power;
        this.speedCapacity = capacity;
        this.isReceive = isReceive;
        this.isExtract = isExtract;
    }

    @Override
    public int receiveSpeed(int power, int maxReceive, boolean simulate) {

        if (!canReceive())
            return 0;

        if (power != powerCapacity) return 0;

        int energyReceived = Math.min(maxReceive, speedCapacity);
        if (!simulate) speed = energyReceived;
        return energyReceived;

    }

    @Override
    public int extractSpeed(int power, int maxExtract, boolean simulate) {

        if (!canExtract())
            return 0;

        if (power != powerCapacity) return 0;

        int energyExtracted = Math.min(speed, maxExtract);
        if (!simulate) speed = 0;
        return energyExtracted;

    }

    @Override
    public int getSpeedStored() {
        return speed;
    }

    @Override
    public int getMaxSpeedStored() {
        return speedCapacity;
    }

    @Override
    public int getMinPowerWorked() {
        return powerCapacity;
    }

    @Override
    public int getPowerStored() {
        return power;
    }

    @Override
    public int getMaxPowerWorked() {
        return powerCapacity;
    }

    @Override
    public boolean canExtract() {
        return this.isExtract;
    }

    @Override
    public boolean canReceive() {
        return this.isReceive;
    }

}
