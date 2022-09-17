package vehicles;

public abstract class Vehicle {
    private double fuel;
    private double consumption;

    public Vehicle(double fuel, double consumption) {
        this.fuel = fuel;
        this.consumption = consumption;
    }

    public double getFuel() {
        return fuel;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public abstract String drive(double distance);
    public abstract void refuel(double liters);
}
