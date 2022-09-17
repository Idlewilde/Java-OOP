package CarShop2;

public class Audi implements Rentable{

    private final String model;
    private final String color;
    private final Integer horsePower;
    private final String countryProduced;
    private final Integer minRentDay;
    private final Double pricePerDay;

    public Audi(String model, String color, Integer horsePower, String countryProduced, Integer minRentDay, Double pricePerDay) {
        this.model=model;
        this.color=color;
        this.horsePower=horsePower;
        this.countryProduced=countryProduced;
        this.minRentDay=minRentDay;
        this.pricePerDay=pricePerDay;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public Integer getHorsePower() {
        return this.horsePower;
    }

    @Override
    public String countryProduced() {
        return this.countryProduced;
    }

    @Override
    public String toString() {
        return String.format("This is %s produced in %s and have %d tires%nMinimum rental period of %d days. Price per day %.6f",model,countryProduced(),Car.TIRES,minRentDay,pricePerDay);
    }

    @Override
    public Integer getMinRentDay() {
        return this.minRentDay;
    }

    @Override
    public Double getPricePerDay() {
        return this.pricePerDay;
    }
}
