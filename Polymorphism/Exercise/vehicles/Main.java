package vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String [] carInfo = scanner.nextLine().split(" ");
    Vehicle car = new Car(Double.parseDouble(carInfo[1]),Double.parseDouble(carInfo[2]));
    String [] truckInfo = scanner.nextLine().split(" ");
    Vehicle truck = new Truck(Double.parseDouble(truckInfo[1]),Double.parseDouble(truckInfo[2]));
    int commands = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < commands; i++) {
            String info = scanner.nextLine();
            Double distLit = Double.parseDouble(info.split(" ")[2]);
            if(info.startsWith("Drive Car")){
                System.out.println(car.drive(distLit));
            }
            else if(info.startsWith("Drive Truck")){
                System.out.println(truck.drive(distLit));
            }
            else if(info.startsWith("Refuel Car")){car.refuel(distLit);}
            else if(info.startsWith("Refuel Truck")){truck.refuel(distLit);}

        }

        String remainingCar = String.format("%.2f",car.getFuel());
        String remainingTruck = String.format("%.2f",truck.getFuel());

        System.out.println("Car: "+remainingCar);
        System.out.println("Truck: "+remainingTruck);



    }
}
