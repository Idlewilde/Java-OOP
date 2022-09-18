package christmasRaces.repositories;

import christmasRaces.entities.drivers.Driver;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DriverRepository implements Repository<Driver> {
    private final Map<String,Driver> models;

    public DriverRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Driver getByName(String name) {
        return models.get(name);
    }

    @Override
    public Collection<Driver> getAll() {
        return Collections.unmodifiableCollection(models.values());
    }

    @Override
    public void add(Driver model) {
        models.put(model.getName(),model);
    }

    @Override
    public boolean remove(Driver model) {
        Driver toRemove = models.remove(model.getName());
        return toRemove != null;
    }
}
