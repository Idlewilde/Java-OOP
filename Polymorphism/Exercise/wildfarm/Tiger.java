package wildfarm;

import java.text.DecimalFormat;

public class Tiger extends Feline{


    public Tiger(String type, String name, Double weight, String livingRegion) {
        super(type, name, weight, livingRegion);
    }

    @Override
    public void eat(Food food) {
        if (food instanceof Vegetable) {
            throw new IllegalArgumentException("Tigers are not eating that type of food!");
        }
        super.eat(food);
    }

    @Override
    public String makeSound() {
        return "ROAAR!!!";
    }


}
