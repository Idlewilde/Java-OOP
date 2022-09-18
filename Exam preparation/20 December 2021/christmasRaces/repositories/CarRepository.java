package christmasRaces.repositories;

import christmasRaces.entities.cars.Car;
import christmasRaces.repositories.interfaces.Repository;

import java.util.*;

public class CarRepository implements Repository<Car> {

    private final Map<String, Car> models;

    public CarRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Car getByName(String name) {
        return this.models.get(name);
    }

    @Override
    public Collection<Car> getAll() {
        return Collections.unmodifiableCollection(models.values());
    }

    @Override
    public void add(Car model) {
        models.put(model.getModel(), model);
    }

    @Override
    public boolean remove(Car model) {
        Car carToRemove=models.remove(model.getModel());
        return carToRemove != null;
    }
}
