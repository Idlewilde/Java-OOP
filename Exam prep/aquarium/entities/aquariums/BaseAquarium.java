package aquarium.entities.aquariums;

import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseAquarium implements Aquarium {
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    public BaseAquarium(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.decorations=new ArrayList<>();
        this.fish=new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    protected void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return decorations;
    }

    @Override
    public Collection<Fish> getFish() {
        return fish;
    }

    @Override
    public int calculateComfort() {
        return decorations.stream().mapToInt(Decoration::getComfort).sum();
    }

    @Override
    public void addFish(Fish fish) {
        if (capacity <= this.fish.size()) {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }
        this.fish.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        decorations.add(decoration);
    }

    @Override
    public void feed() {
        this.fish.forEach(Fish::eat);
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        List<String> fishNames = fish.stream().map(Fish::getName).collect(Collectors.toList());
        sb.append(String.format("%s (%s):", name, getClass().getSimpleName())).append(System.lineSeparator());
        if (fish.isEmpty()) {
            sb.append("Fish: none").append(System.lineSeparator());
        } else {
            sb.append("Fish: ").append(String.join(" ", fishNames)).append(System.lineSeparator());
        }
        sb.append(String.format("Decorations: %d", decorations.size())).append(System.lineSeparator())
                .append(String.format("Comfort: %d", calculateComfort())).append(System.lineSeparator());
        return sb.toString().trim();
    }
}


