package vehicles2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String [] carInfo = scanner.nextLine().split(" ");
    Vehicle car = new Car(Double.parseDouble(carInfo[1]),Double.parseDouble(carInfo[2]),Double.parseDouble(carInfo[3]));
    String [] truckInfo = scanner.nextLine().split(" ");
    Vehicle truck = new Truck(Double.parseDouble(truckInfo[1]),Double.parseDouble(truckInfo[2]),Double.parseDouble(truckInfo[3]));
    String [] busInfo = scanner.nextLine().split(" ");
    Vehicle bus = new Bus(Double.parseDouble(busInfo[1]),Double.parseDouble(busInfo[2]),Double.parseDouble(busInfo[3]));

        int commands = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < commands; i++) {
            String info = scanner.nextLine();
            double distLit = Double.parseDouble(info.split(" ")[2]);
            if(info.startsWith("Drive Car")){
                System.out.println(car.drive(distLit));
            }
            else if(info.startsWith("Drive Truck")){
                System.out.println(truck.drive(distLit));
            }
            else if(info.startsWith("Drive Bus")){
                System.out.println(bus.drive(distLit));
            }
            else if(info.startsWith("DriveEmpty Bus")){
                System.out.println(bus.drive(distLit,"empty"));
            }
            try{
            if(info.startsWith("Refuel Car")){car.refuel(distLit);}
            else if(info.startsWith("Refuel Truck")){truck.refuel(distLit);}
            else if(info.startsWith("Refuel Bus")){bus.refuel(distLit);}}
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());}


            }

        String remainingCar = String.format("%.2f",car.getFuel());
        String remainingTruck = String.format("%.2f",truck.getFuel());
        String remainingBus= String.format("%.2f",bus.getFuel());

        System.out.println("Car: "+remainingCar);
        System.out.println("Truck: "+remainingTruck);
        System.out.println("Bus: "+remainingBus);



    }
}
