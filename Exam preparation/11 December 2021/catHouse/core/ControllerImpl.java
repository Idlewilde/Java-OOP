package catHouse.core;

import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller{
    private ToyRepository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {
    if(type.equals("ShortHouse")){House house = new ShortHouse(name);houses.add(house);
        return "ShortHouse is successfully added.";
    }
    else if(type.equals("LongHouse")){House house = new LongHouse(name);houses.add(house);
        return "LongHouse is successfully added.";}

    else{throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
    }}

    @Override
    public String buyToy(String type) {
        if(type.equals("Ball")){
            Toy toy = new Ball();toys.buyToy(toy);
            return "Ball is successfully added.";
        }
        else if(type.equals("Mouse")){Toy toy = new Mouse();toys.buyToy(toy);
            return "Mouse is successfully added.";}

        else{throw new IllegalArgumentException(ExceptionMessages.INVALID_TOY_TYPE);
        }
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        if(toys.findFirst(toyType)==null){throw new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND,toyType));}
            Toy toy = toys.findFirst(toyType);
            toys.removeToy(toy);
        for (House house : houses) {
            if(house.getName().equals(houseName)){house.buyToy(toy);
        }}
        return String.format("Successfully added %s to %s.",toyType,houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        if(!catType.equals("ShorthairCat")&&!catType.equals("LonghairCat")){throw new IllegalArgumentException(ExceptionMessages.INVALID_CAT_TYPE);}

        Cat cat = null;
        if(catType.startsWith("Long")){
             cat = new LonghairCat(catName,catBreed,price);}
        else if(catType.startsWith("Short")){
             cat = new ShorthairCat(catName,catBreed,price);}

            for (House house : houses) {
                if(house.getName().equals(houseName)){
                    if(house.getClass().getSimpleName().startsWith("Long")
                            &&cat.getClass().getSimpleName().startsWith("Long")){house.addCat(cat);
                        return String.format("Successfully added %s to %s.",catType,houseName );}
                    else if(house.getClass().getSimpleName().startsWith("Short")
                            &&cat.getClass().getSimpleName().startsWith("Short")){house.addCat(cat);
                        return String.format("Successfully added %s to %s.",catType,houseName );}
                    }}
        return "Unsuitable house.";
    }

    @Override
    public String feedingCat(String houseName) {
        int catCount = 0;
        for (House house : houses) {
            if(house.getName().equals(houseName)){house.feeding();catCount=house.getCats().size();}
        }
        return String.format("Feeding a cat: %d",catCount);
    }

    @Override
    public String sumOfAll(String houseName) {
        double value = 0;
        for (House house : houses) {
            if (house.getName().equals(houseName)) {
                value = house.getCats().stream().mapToDouble(Cat::getPrice).sum() + house.getToys().stream().mapToDouble(Toy::getPrice).sum();
            }
        }
        return String.format("The value of House %s is %.2f.",houseName,value);
    }

    @Override
    public String getStatistics() {
        StringBuilder stringBuilder = new StringBuilder();
        for (House house : houses) {
            stringBuilder.append(house.getStatistics()).append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
