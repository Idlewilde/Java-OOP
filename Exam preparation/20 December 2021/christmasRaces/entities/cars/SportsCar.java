package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public class SportsCar extends BaseCar{
    public SportsCar(String model, int horsepower) {
        super(model, horsepower, 3000);
    }

    @Override
    protected void setHorsepower(int horsepower) {
        if(horsepower<250||horsepower>450){throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER,horsepower));}

        super.setHorsepower(horsepower);
    }
}
