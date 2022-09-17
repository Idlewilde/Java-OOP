package wildfarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Animal> animals = new ArrayList<>();

        String animalInput = scanner.nextLine();
        while (!animalInput.equals("End")) {
            String[] tokens = animalInput.split(" ");
            Animal animal = createAnimal(tokens);

            String foodInput = scanner.nextLine();
            Food food = getFood(foodInput.split(" "));

            System.out.println(animal.makeSound());

            try {
                animal.eat(food);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

            animals.add(animal);
            animalInput = scanner.nextLine();
        }

        for (Animal animal : animals) {

            System.out.println(animal);
        }
    }

    public static Food getFood(String[] tokens) {
        String type = tokens[0];
        int quantity = Integer.parseInt(tokens[1]);

        if (type.equals("Meat")) {
            return new Meat(quantity);
        } else return new Vegetable(quantity);
    }

    public static Animal createAnimal(String[] tokens) {
        String animalType = tokens[0];
        String animalName = tokens[1];
        double animalWeight = Double.parseDouble(tokens[2]);
        String animalLivingRegion = tokens[3];

        switch (animalType) {
            case "Mouse":
                return new Mouse(animalType, animalName, animalWeight, animalLivingRegion);
            case "Cat":
                return new Cat(animalType, animalName,  animalWeight, animalLivingRegion, tokens[4]);
            case "Zebra":
                return new Zebra(animalType, animalName,  animalWeight, animalLivingRegion);
            case "Tiger":
                return new Tiger(animalType, animalName,  animalWeight, animalLivingRegion);
            default:
                throw new IllegalArgumentException("No such animal");
        }
    }

}
