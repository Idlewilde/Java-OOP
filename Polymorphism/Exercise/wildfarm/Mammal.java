package wildfarm;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal{
    private String livingRegion;

    public Mammal(String type, String name, Double weight, String livingRegion) {
        super(type, name, weight);
        this.livingRegion=livingRegion;
    }


    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        return String.format("%s[%s, %s, %s, %d]", getType(), getName(),
                decimalFormat.format(getWeight()), this.livingRegion, getFoodEaten());
    }

    public String getLivingRegion() {
        return livingRegion;
    }
}
