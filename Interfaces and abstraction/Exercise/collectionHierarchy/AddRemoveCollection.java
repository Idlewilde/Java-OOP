package collectionHierarchy;

public class AddRemoveCollection extends Collection implements AddRemovable{
    @Override
    public String remove() {
        return super.getItems().remove(getItems().size()-1);
    }

    @Override
    public int add(String element) {
        super.getItems().add(0,element);
        return 0;
    }
}
