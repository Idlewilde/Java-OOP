package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public class MuscleCar extends BaseCar{
    public MuscleCar(String model, int horsepower) {
        super(model, horsepower, 5000);
    }

    @Override
    protected void setHorsepower(int horsepower) {
        if(horsepower<400||horsepower>600){throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER,horsepower));}

        super.setHorsepower(horsepower);
    }
}
