package christmasRaces.entities.races;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;

public class RaceImpl implements Race{
    private String name;
    private int laps;
    private Collection <Driver> drivers;

    public RaceImpl(String name, int laps) {
        setName(name);
        setLaps(laps);
        this.drivers=new ArrayList<>();
    }

    protected void setName(String name) {
        if(name==null||name.trim().length()<5){throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NAME,name));}
        this.name = name;
    }

    protected void setLaps(int laps) {
        if(laps<1){throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_LAPS);}
        this.laps = laps;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLaps() {
        return laps;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return drivers;
    }

    @Override
    public void addDriver(Driver driver) {
    if(driver==null){throw new IllegalArgumentException(ExceptionMessages.DRIVER_INVALID);}
    if(!driver.getCanParticipate()){throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_PARTICIPATE,driver.getName()));}
    if(drivers.contains(driver)){throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_EXISTS,driver.getName()));}
    drivers.add(driver);
    }
}
