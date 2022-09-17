package PizzaCalories;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Topping> toppings;

    public Pizza(String name, int numberOfToppings) {
        this.setName(name);
        this.setToppings(numberOfToppings);
        this.toppings = new ArrayList<>();
    }

    private void setName(String name) {
        if(name.trim().equals("")||name==null||name.length()>15){
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");}
        this.name = name;
    }

    private void setToppings(int numberOfToppings) {
        if(numberOfToppings<0||numberOfToppings>10){
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
        this.toppings = new ArrayList<>(numberOfToppings);
    }

    public String getName() {
        return name;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public void addTopping (Topping topping){
        if (this.toppings.size() == 10) {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
        this.toppings.add(topping);
    }

    public double getOverallCalories(){
        double overallCalories = this.dough.calculateCalories();
        for (Topping topping : toppings) {
            overallCalories=overallCalories+topping.calculateCalories();
        }
        return overallCalories;
    }
}
