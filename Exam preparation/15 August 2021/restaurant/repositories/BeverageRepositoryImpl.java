package restaurant.repositories;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.repositories.interfaces.BeverageRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BeverageRepositoryImpl implements BeverageRepository <Beverages> {

    private Map<String,Beverages> beveragesRepository;

    public BeverageRepositoryImpl() {
        this.beveragesRepository = new LinkedHashMap<>();
    }

    @Override
    public Beverages beverageByName(String drinkName, String drinkBrand) {
        return beveragesRepository.get(drinkName+drinkBrand);
    }

    @Override
    public Collection<Beverages> getAllEntities() {
        return Collections.unmodifiableCollection(beveragesRepository.values());
    }

    @Override
    public void add(Beverages entity) {
        beveragesRepository.put(entity.getName()+entity.getBrand(),entity);
    }
}
