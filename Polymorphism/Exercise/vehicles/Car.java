package vehicles;

import java.text.DecimalFormat;

public class Car extends Vehicle{
    public Car(double fuel, double consumption) {
        super(fuel, consumption);
    }

    @Override
    public String drive(double distance) {
        double fuelNeeded = distance*(getConsumption())+distance*0.9;
        if(fuelNeeded<=getFuel()){setFuel(getFuel()-fuelNeeded);
            DecimalFormat formatter = new DecimalFormat("##.##");
            return String.format("Car travelled %s km",formatter.format(distance));
    }
        return "Car needs refueling";}

    @Override
    public void refuel(double fuelAdded) {
        setFuel(getFuel()+fuelAdded);
    }
}
