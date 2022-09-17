package CarShop2;

public class Seat implements Sellable {
    private final String model;
    private final String color;
    private final Integer horsePower;
    private final String countryProduced;
    private int TIRES = 4;
    private Double price;

    public Seat(String model, String color, Integer horsePower, String countryProduced, Double price) {
        this.model=model;
        this.color=color;
        this.horsePower=horsePower;
        this.countryProduced=countryProduced;
        this.price=price;

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
        return String.format("This is %s produced in %s and have %d tires%n%s sells for %.6f",model,countryProduced(),Car.TIRES,model,price);
    }

    @Override
    public Double getPrice() {
        return this.price;
    }
}
