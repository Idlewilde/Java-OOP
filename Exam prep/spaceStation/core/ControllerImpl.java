package spaceStation.core;

import spaceStation.common.ConstantMessages;
import spaceStation.common.ExceptionMessages;
import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;
import spaceStation.repositories.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private Repository<Astronaut> astronautRepository;
    private Repository<Planet> planetRepository;
    private Mission mission;
    private int explored;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
        this.mission = new MissionImpl();
        this.explored = 0;
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;
        switch (type) {
            case "Biologist":
                astronaut = new Biologist(astronautName);
                astronautRepository.add(astronaut);
                return String.format(ConstantMessages.ASTRONAUT_ADDED, type, astronautName);
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                astronautRepository.add(astronaut);
                return String.format(ConstantMessages.ASTRONAUT_ADDED, type, astronautName);
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                astronautRepository.add(astronaut);
                return String.format(ConstantMessages.ASTRONAUT_ADDED, type, astronautName);
            default:
                throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_INVALID_TYPE);
        }
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);
        List<String> planetItems = new ArrayList<>(Arrays.asList(items));
        planet.getItems().addAll(planetItems);
        planetRepository.add(planet);
        return String.format(ConstantMessages.PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        if (astronautRepository.findByName(astronautName) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.ASTRONAUT_DOES_NOT_EXIST, astronautName));
        } else {
            astronautRepository.remove(astronautRepository.findByName(astronautName));
            return String.format(ConstantMessages.ASTRONAUT_RETIRED, astronautName);
        }
    }

    @Override
    public String explorePlanet(String planetName) {
        Collection<Astronaut> astronauts = astronautRepository.getModels().stream().filter(e -> e.getOxygen() > 60).collect(Collectors.toList());
        if (astronauts.size() == 0) {
            throw new IllegalArgumentException(ExceptionMessages.PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }
        mission.explore(planetRepository.findByName(planetName), astronauts);
        explored++;
        long dead = astronauts.stream().filter(e -> e.getOxygen() == 0).count();
        return String.format(ConstantMessages.PLANET_EXPLORED, planetName, dead);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ConstantMessages.REPORT_PLANET_EXPLORED, explored)).append(System.lineSeparator());
        sb.append(ConstantMessages.REPORT_ASTRONAUT_INFO).append(System.lineSeparator());
        for (Astronaut astronaut : astronautRepository.getModels()) {
            sb.append(String.format(ConstantMessages.REPORT_ASTRONAUT_NAME, astronaut.getName())).append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.REPORT_ASTRONAUT_OXYGEN, astronaut.getOxygen())).append(System.lineSeparator());
            if (astronaut.getBag().getItems().size() == 0) {
                sb.append(String.format(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS, "none")).append(System.lineSeparator());}
            else{sb.append(String.format(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS,
                    String.join(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER,astronaut.getBag().getItems()))).append(System.lineSeparator());
            }}
            return sb.toString().trim();
        }
    }
