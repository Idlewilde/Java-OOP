package vehicles2;

import java.text.DecimalFormat;

public class Bus extends Vehicle {
    public Bus(double fuel, double consumption, double capacity) {
        super(fuel, consumption, capacity);
    }

    @Override
    public String drive(double distance) {
        double fuelNeeded = distance*(getConsumption())+distance*1.4;
        if(fuelNeeded<=getFuel()){setFuel(getFuel()-fuelNeeded);
            DecimalFormat formatter = new DecimalFormat("##.##");
            return String.format("Bus travelled %s km",formatter.format(distance));
        }
        return "Bus needs refueling";}

    @Override
    public String drive(double distance, String people) {
        double fuelNeeded = distance*(getConsumption());
        if(fuelNeeded<=getFuel()){setFuel(getFuel()-fuelNeeded);
            DecimalFormat formatter = new DecimalFormat("##.##");
            return String.format("Bus travelled %s km",formatter.format(distance));
        }
        return "Bus needs refueling";}

    @Override
    public void refuel(double fuelAdded) {
        if(fuelAdded<=0){throw new IllegalArgumentException("Fuel must be a positive number");}
        else if((getFuel()+fuelAdded)>getCapacity()){throw new IllegalArgumentException("Cannot fit fuel in tank");}
        else{setFuel(getFuel()+fuelAdded);}
    }
}

