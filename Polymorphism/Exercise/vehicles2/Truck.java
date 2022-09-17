package vehicles2;

import java.text.DecimalFormat;

public class Truck extends Vehicle{

    public Truck(double fuel, double consumption, double capacity) {
        super(fuel, consumption,capacity);
    }


    @Override
    public String drive(double distance) {
        double fuelNeeded = distance*(getConsumption())+distance*1.6;
        if(fuelNeeded<=getFuel()){setFuel(getFuel()-fuelNeeded);
            DecimalFormat formatter = new DecimalFormat("##.##");
            return String.format("Truck travelled %s km",formatter.format(distance));
        }
        return "Truck needs refueling";}

    @Override
    public String drive(double distance, String people) {
        return null;
    }


    @Override
    public void refuel(double fuelAdded) {
        if(fuelAdded<=0){throw new IllegalArgumentException("Fuel must be a positive number");}
        else if((getFuel()+fuelAdded)>getCapacity()){throw new IllegalArgumentException("Cannot fit fuel in tank");}

        setFuel(getFuel()+0.95*fuelAdded);
    }
}
