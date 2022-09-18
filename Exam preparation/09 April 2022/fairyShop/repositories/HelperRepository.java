package fairyShop.repositories;

import fairyShop.models.Helper;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class HelperRepository implements Repository <Helper>{

    private Map<String,Helper> helpers;

    public HelperRepository() {
        this.helpers = new LinkedHashMap<>();
    }

    @Override
    public Collection<Helper> getModels() {
        return Collections.unmodifiableCollection(this.helpers.values());
    }

    @Override
    public void add(Helper model) {
    this.helpers.put(model.getName(),model);
    }

    @Override
    public boolean remove(Helper model) {
        if(this.helpers.containsKey(model.getName())){this.helpers.remove(model.getName());return true;}
        return false;
    }

    @Override
    public Helper findByName(String name) {
        return this.helpers.get(name);
    }
}
