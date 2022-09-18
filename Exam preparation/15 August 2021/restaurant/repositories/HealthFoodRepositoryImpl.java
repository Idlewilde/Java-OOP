package restaurant.repositories;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.repositories.interfaces.HealthFoodRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class HealthFoodRepositoryImpl implements HealthFoodRepository <HealthyFood> {
    private Map<String, HealthyFood> healthyfoodsRepository;

    public HealthFoodRepositoryImpl() {
        this.healthyfoodsRepository = new LinkedHashMap<>();
    }

    @Override
    public HealthyFood foodByName(String name) {
        return healthyfoodsRepository.get(name);
    }

    @Override
    public Collection<HealthyFood> getAllEntities() {
        return Collections.unmodifiableCollection(healthyfoodsRepository.values());
    }

    @Override
    public void add(HealthyFood entity) {
        healthyfoodsRepository.put(entity.getName(),entity);
    }
}
