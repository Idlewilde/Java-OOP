package vehicles;

import java.text.DecimalFormat;

public class Truck extends Vehicle{

    public Truck(double fuel, double consumption) {
        super(fuel, consumption);
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
    public void refuel(double fuelAdded) {

        setFuel(getFuel()+0.95*fuelAdded);
    }
}
