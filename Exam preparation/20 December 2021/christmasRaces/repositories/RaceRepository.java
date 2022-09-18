package christmasRaces.repositories;
import christmasRaces.entities.races.Race;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class RaceRepository implements Repository<Race> {
    private final Map<String, Race> models;

    public RaceRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Race getByName(String name) {
        return models.get(name);
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(models.values());
    }

    @Override
    public void add(Race model) {
        models.put(model.getName(),model);
    }

    @Override
    public boolean remove(Race model) {
        Race toRemove = models.remove(model.getName());
        return toRemove != null;
    }
}
