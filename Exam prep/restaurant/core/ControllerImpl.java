package restaurant.core;

import restaurant.common.ExceptionMessages;
import restaurant.common.OutputMessages;
import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.healthyFoods.Food;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.BeverageRepositoryImpl;
import restaurant.repositories.HealthFoodRepositoryImpl;
import restaurant.repositories.TableRepositoryImpl;
import restaurant.repositories.interfaces.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ControllerImpl implements Controller {

    private HealthFoodRepository <HealthyFood> healthFoodRepository;
    private BeverageRepository <Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;
    private double total;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository, BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {
    this.healthFoodRepository=healthFoodRepository;
    this.beverageRepository=beverageRepository;
    this.tableRepository=tableRepository;

    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
    HealthyFood food;
        if(type.equals("Salad")){food=new Salad(name,price);}
        else{food=new VeganBiscuits(name,price);}
        if (healthFoodRepository.foodByName(name)!=null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_EXIST,name));}
        else{healthFoodRepository.add(food);return String.format(OutputMessages.FOOD_ADDED,name);}
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name){
        Beverages beverage;
        String brandToSee=brand;
        String nameToSee=name;
        if(type.equals("Fresh")){beverage=new Fresh(name,counter,brand);}
        else{beverage=new Smoothie(name,counter,brand);}
        if (beverageRepository.beverageByName(name,brand)!=null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.BEVERAGE_EXIST,name));}
        else{beverageRepository.add(beverage);return String.format(OutputMessages.BEVERAGE_ADDED,beverage.getClass().getSimpleName(),brand);}
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table;
        if(type.equals("Indoors")){table=new Indoors(tableNumber,capacity);}
        else{table=new InGarden(tableNumber,capacity);}
        if (tableRepository.byNumber(tableNumber)!=null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.TABLE_IS_ALREADY_ADDED,tableNumber));}
        else{tableRepository.add(table);return String.format(OutputMessages.TABLE_ADDED,tableNumber);}
    }

    @Override
    public String reserve(int numberOfPeople) {
        Collection <Table> tables = tableRepository.getAllEntities();
        Table suitable = tables.stream().filter(e->!e.isReservedTable()&&e.getSize()>=numberOfPeople).findFirst().orElse(null);
        if(suitable==null){return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE,numberOfPeople);}
        else{suitable.reserve(numberOfPeople);return String.format(OutputMessages.TABLE_RESERVED,suitable.getTableNumber(),numberOfPeople);}
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        Table table = this.tableRepository.byNumber(tableNumber);
        HealthyFood healthyFood=this.healthFoodRepository.foodByName(healthyFoodName);

        if(table==null){return String.format(OutputMessages.WRONG_TABLE_NUMBER,tableNumber);}
        else if(healthyFood==null){return String.format(OutputMessages.NONE_EXISTENT_FOOD,healthyFoodName);}
        else{table.orderHealthy(healthyFood);return String.format(OutputMessages.FOOD_ORDER_SUCCESSFUL,healthyFoodName,tableNumber);}

    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        Table table = this.tableRepository.byNumber(tableNumber);
        Beverages beverages=this.beverageRepository.beverageByName(name,brand);

        if(table==null){return String.format(OutputMessages.WRONG_TABLE_NUMBER,tableNumber);}
        else if(beverages==null){return String.format(OutputMessages.NON_EXISTENT_DRINK,name,brand);}
        else{table.orderBeverages(beverages);return String.format(OutputMessages.BEVERAGE_ORDER_SUCCESSFUL,name,tableNumber);}

    }

    @Override
    public String closedBill(int tableNumber) {
        Table table = this.tableRepository.byNumber(tableNumber);
        double bill = table.bill();
        this.total=this.total+bill;
        table.clear();
        return String.format(OutputMessages.BILL,tableNumber,bill);
    }


    @Override
    public String totalMoney() {
        return String.format(OutputMessages.TOTAL_MONEY,this.total);
    }
}
