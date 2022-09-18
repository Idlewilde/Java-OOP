package aquarium.repositories;

import aquarium.entities.decorations.Decoration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DecorationRepository implements Repository{

    private Collection<Decoration> decorations;

    public DecorationRepository()
    {
        this.decorations = new ArrayList<>();
    }

    public Collection<Decoration> getDecorations() {
        return Collections.unmodifiableCollection(decorations);
    }

    @Override
    public void add(Decoration decoration) {
    this.decorations.add(decoration);
    }

    @Override
    public boolean remove(Decoration decoration) {
        boolean result = decorations.remove(decoration);
        return result;
    }

    @Override
    public Decoration findByType(String type) {
        return decorations.stream().filter(e->e.getClass().getSimpleName().equals(type)).findFirst().orElse(null);
    }
}
