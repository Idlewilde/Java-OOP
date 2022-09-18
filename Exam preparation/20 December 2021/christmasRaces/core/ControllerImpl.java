package christmasRaces.core;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.common.OutputMessages;
import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.CarRepository;
import christmasRaces.repositories.DriverRepository;
import christmasRaces.repositories.RaceRepository;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private Repository <Driver> driverRepository;
    private Repository <Car> carRepository;
    private Repository <Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = new DriverRepository();
        this.carRepository = new CarRepository();
        this.raceRepository = new RaceRepository();
    }

    @Override
    public String createDriver(String driver) {

        if(driverRepository.getByName(driver)!=null){throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_EXISTS,driver));}
        Driver toAdd = new DriverImpl(driver);
        driverRepository.add(toAdd);
        return String.format(OutputMessages.DRIVER_CREATED,driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        if(carRepository.getByName(model)!=null){throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_EXISTS,model));}
            Car car;
            if(type.equals("Muscle")){car=new MuscleCar(model,horsePower);}
            else{car=new SportsCar(model,horsePower);}
            carRepository.add(car);

        return String.format(OutputMessages.CAR_CREATED,car.getClass().getSimpleName(),model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = driverRepository.getByName(driverName);
        Car car = carRepository.getByName(carModel);
    if(driver==null){throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND,driverName));}
    if(car==null){throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_NOT_FOUND,carModel));}
    driver.addCar(car);


        return String.format(OutputMessages.CAR_ADDED,driverName,carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Driver driver = driverRepository.getByName(driverName);
        Race race = raceRepository.getByName(raceName);
        if(driver==null){throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND,driverName));}
        if(race==null){throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND,raceName));}
        race.addDriver(driver);


        return String.format(OutputMessages.DRIVER_ADDED,driverName,raceName);
    }


    @Override
    public String startRace(String raceName) {

        if(raceRepository.getByName(raceName)==null){throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND,raceName));}
        Race race = raceRepository.getByName(raceName);
        int laps=race.getLaps();
        if(race.getDrivers().size()<3){throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID,raceName,3));}
        List<Driver> top3=race.getDrivers().stream().sorted((e2,e1) -> Double.compare(e1.getCar().calculateRacePoints(laps),e2.getCar().calculateRacePoints(laps))).limit(3).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(OutputMessages.DRIVER_FIRST_POSITION,top3.get(0).getName(),raceName)).append(System.lineSeparator());
        sb.append(String.format(OutputMessages.DRIVER_SECOND_POSITION,top3.get(1).getName(),raceName)).append(System.lineSeparator());
        sb.append(String.format(OutputMessages.DRIVER_THIRD_POSITION,top3.get(2).getName(),raceName)).append(System.lineSeparator());
        return sb.toString().trim();
    }

    @Override
    public String createRace(String name, int laps) {
        if(raceRepository.getByName(name)!=null){throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS,name));}
        Race race = new RaceImpl(name,laps);
        raceRepository.add(race);
        return String.format(OutputMessages.RACE_CREATED,name);
    }
}
