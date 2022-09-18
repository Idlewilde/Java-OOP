package CounterStriker.repositories;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.models.guns.Gun;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class GunRepository implements Repository<Gun> {

    private Map<String, Gun> gunRepository;

    public GunRepository() {
        this.gunRepository = new LinkedHashMap<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return gunRepository.values();
    }

    @Override
    public void add(Gun model) {
        if (model == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_GUN_REPOSITORY);
        }
        gunRepository.put(model.getName(),model);
    }

    @Override
    public boolean remove(Gun model) {
        Gun toBeRemoved = gunRepository.remove(model.getName());
        return toBeRemoved != null;
    }

    @Override
    public Gun findByName(String name) {
        return gunRepository.get(name);
    }
}
