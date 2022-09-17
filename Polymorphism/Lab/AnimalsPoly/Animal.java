package AnimalsPoly;

public abstract class Animal {
    public Animal(String name, String favouriteFood) {
        this.name = name;
        this.favouriteFood = favouriteFood;
    }

    private String name;
    private String favouriteFood;

    public abstract String explainSelf();

    @Override
    public String toString() {
        return "I am "+ name +" and my favourite food is " + favouriteFood;
    }
}
