package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public abstract class BaseCar implements Car{
    private String model;
    private int horsepower;
    private double cubicCentimeters;

    public BaseCar(String model, int horsepower, double cubicCentimeters) {
        setModel(model);
        setHorsepower(horsepower);
        this.cubicCentimeters = cubicCentimeters;
    }

    protected void setModel(String model) {
        if(model==null||model.trim().isEmpty()||model.length()<4){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_MODEL,model,4));
        }
        this.model = model;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return getCubicCentimeters() / getHorsePower() * laps;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getHorsePower() {
        return horsepower;
    }

    @Override
    public double getCubicCentimeters() {
        return cubicCentimeters;
    }

    protected void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }
}
