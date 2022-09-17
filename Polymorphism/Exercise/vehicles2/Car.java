package vehicles2;

import java.text.DecimalFormat;

public class Car extends Vehicle{
    public Car(double fuel, double consumption, double capacity) {
        super(fuel, consumption, capacity);
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
    public String drive(double distance, String people) {
        return null;
    }

    @Override
    public void refuel(double fuelAdded) {
        if(fuelAdded<=0){throw new IllegalArgumentException("Fuel must be a positive number");}
        else if((getFuel()+fuelAdded)>getCapacity()){throw new IllegalArgumentException("Cannot fit fuel in tank");}
        else{setFuel(getFuel()+fuelAdded);}
    }
}
