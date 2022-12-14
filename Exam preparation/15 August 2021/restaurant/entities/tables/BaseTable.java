package restaurant.entities.tables;

import restaurant.common.ExceptionMessages;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.Food;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseTable implements Table {
    private Collection <HealthyFood> healthyFood;
    private Collection <Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;

    public BaseTable(int number, int size, double pricePerPerson) {
        this.number = number;
        setSize(size);
        this.pricePerPerson = pricePerPerson;
        this.healthyFood=new ArrayList<>();
        this.beverages=new ArrayList<>();
    }

    protected void setSize(int size) {
        if(size<0){throw new IllegalArgumentException(ExceptionMessages.INVALID_TABLE_SIZE);}
        this.size = size;
    }

    @Override
    public int getTableNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int numberOfPeople() {
        return numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return isReservedTable;
    }

    @Override
    public double allPeople() {
        return allPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople=numberOfPeople;
        this.isReservedTable=true;
    }

    @Override
    public void orderHealthy(HealthyFood food) {
    this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
    this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        return healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum()
                +beverages.stream().mapToDouble(Beverages::getPrice).sum()
                +this.numberOfPeople*pricePerPerson;
    }

    @Override
    public void clear() {

    this.isReservedTable=false;
    this.numberOfPeople=0;
    this.healthyFood.clear();
    this.beverages.clear();
    }

    @Override
    public String tableInformation() {
        return String.format(
                "Table - %d%n" +
                "Size - %d%n"+
                "Type - %s%n"+
                "All price - %.2f",number, size, this.getClass().getSimpleName(), pricePerPerson);
    }
}
