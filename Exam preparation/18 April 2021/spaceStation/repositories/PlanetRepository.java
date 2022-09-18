package spaceStation.repositories;
import spaceStation.models.planets.Planet;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlanetRepository implements Repository <Planet>{
    private Map<String, Planet> planets;

    public PlanetRepository() {
        this.planets = new LinkedHashMap<>();
    }

    @Override
    public Collection<Planet> getModels() {
        return Collections.unmodifiableCollection(planets.values());
    }

    @Override
    public void add(Planet planet) {
        this.planets.put(planet.getName(),planet);
    }

    @Override
    public boolean remove(Planet planet) {
        if(planets.containsKey(planet.getName())){this.planets.remove(planet.getName());return true;}
        return false;
    }

    @Override
    public Planet findByName(String name) {
        return this.planets.get(name);
    }
}
