package vehicles2;

public abstract class Vehicle {
    private double fuel;
    private double consumption;
    private double capacity;

    public Vehicle(double fuel, double consumption,double capacity) {
        this.fuel = fuel;
        this.consumption = consumption;
        this.capacity=capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
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
    public abstract String drive(double distance,String people);
    public abstract void refuel(double liters);
}
