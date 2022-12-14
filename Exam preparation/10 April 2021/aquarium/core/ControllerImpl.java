package aquarium.core;

import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.core.Controller;
import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;
import aquarium.repositories.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class ControllerImpl implements Controller {

    private Repository decorations;
    private Collection<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        Aquarium aquarium;
        switch (aquariumType) {
            case "FreshwaterAquarium":
                aquarium = new FreshwaterAquarium(aquariumName);
                break;
            case "SaltwaterAquarium":
                aquarium = new SaltwaterAquarium(aquariumName);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_AQUARIUM_TYPE);
        }
        this.aquariums.add(aquarium);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        Decoration decoration;
        switch (type) {
            case "Ornament":
                decoration = new Ornament();
                break;
            case "Plant":
                decoration = new Plant();
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_DECORATION_TYPE);
        }
        this.decorations.add(decoration);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Decoration decoration = decorations.findByType(decorationType);
        if (decoration == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_DECORATION_FOUND, decorationType));
        }
        this.aquariums.stream()
                .filter(e -> e.getName().equals(aquariumName))
                .findFirst()
                .orElse(null)
                .addDecoration(decoration);
        this.decorations.remove(decoration);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        Fish fish;
        switch (fishType) {
            case "FreshwaterFish":
                fish = new FreshwaterFish(fishName, fishSpecies, price);
                break;
            case "SaltwaterFish":
                fish = new SaltwaterFish(fishName, fishSpecies, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_FISH_TYPE);
        }
        Aquarium aquarium = aquariums.stream()
                .filter(e -> e.getName().equals(aquariumName))
                .findFirst()
                .orElse(null);

        int capacity = 0;
        if (aquarium.getClass().getSimpleName().equals("FreshwaterAquarium")) {
            capacity = 50;
        } else if (aquarium.getClass().getSimpleName().equals("SaltwaterAquarium")) {
            capacity = 25;
        }

        if (aquarium.getFish().size() >= capacity) {
            return ConstantMessages.NOT_ENOUGH_CAPACITY;
        } else if (!aquarium.getClass().getSimpleName().substring(0, 4).equals(fishType.substring(0, 4))) {
            return ConstantMessages.WATER_NOT_SUITABLE;
        } else {
            aquarium.addFish(fish);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
        }
    }

    @Override
    public String feedFish(String aquariumName) {
        Aquarium aquarium = aquariums.stream()
                .filter(e -> e.getName().equals(aquariumName))
                .findFirst()
                .orElse(null);
        aquarium.feed();
        return String.format(ConstantMessages.FISH_FED, aquarium.getFish().size());
    }

    @Override
    public String calculateValue(String aquariumName) {
        Aquarium aquarium = aquariums.stream()
                .filter(e -> e.getName().equals(aquariumName))
                .findFirst()
                .orElse(null);
        double value = aquarium.getDecorations().stream().mapToDouble(Decoration::getPrice).sum()
                     + aquarium.getFish().stream().mapToDouble(Fish::getPrice).sum();
        return String.format(ConstantMessages.VALUE_AQUARIUM, aquariumName, value);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        for (Aquarium aquarium : aquariums) {
            sb.append(aquarium.getInfo()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
