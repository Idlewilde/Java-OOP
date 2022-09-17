package collectionHierarchy;

import java.util.ArrayList;
import java.util.List;

public abstract class Collection {
    private List<String> items;
    private int maxSize;

    public List<String> getItems() {
        return items;
    }

    public Collection() {
        this.items = new ArrayList<>();
        this.maxSize=100;
    }
}
