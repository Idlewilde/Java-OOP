package PizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String [] pizzaInfo = scanner.nextLine().split(" ");
        Pizza pizza = new Pizza(pizzaInfo[1],Integer.parseInt(pizzaInfo[2]));
        String [] doughInfo = scanner.nextLine().split(" ");
        Dough dough = new Dough(doughInfo[1],doughInfo[2],Double.parseDouble(doughInfo[3]));
        pizza.setDough(dough);
        String toppingInfo = scanner.nextLine();
        while(!toppingInfo.equals("END")){

            Topping topping = new Topping(toppingInfo.split(" ")[1],
                                          Double.parseDouble(toppingInfo.split(" ")[2]));
            pizza.addTopping(topping);
            toppingInfo = scanner.nextLine();
        }

        System.out.println(pizza.getName()+String.format(" - %.2f",pizza.getOverallCalories()));


    }
}
