package wildfarm;

import java.text.DecimalFormat;

public class Cat extends Feline{
    private String breed;

    public Cat(String type,String name, double weight, String livingRegion, String breed) {
        super(type, name, weight, livingRegion);
        this.breed=breed;
    }

    @Override
    public String makeSound() {
    return "Meowwww";
    }

    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("##.##");
        return String.format("%s[%s, %s, %s, %s, %d]", getType(), getName(), this.breed,
                formatter.format(getWeight()), getLivingRegion(), getFoodEaten());
    }
}
