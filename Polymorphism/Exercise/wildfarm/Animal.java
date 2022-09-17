package wildfarm;

public abstract class Animal {
    private String name;
    private String type;
    private double weight;
    private Integer foodEaten;

    public Animal(String type, String name, Double weight) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.foodEaten = 0;


    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public Integer getFoodEaten() {
        return foodEaten;
    }

    public abstract String makeSound();
    public void eat(Food food) {
        this.foodEaten += food.getQuantity();
    }
}
