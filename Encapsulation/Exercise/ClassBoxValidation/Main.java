package ClassBoxValidation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double length = Double.parseDouble(scanner.nextLine());
        double width = Double.parseDouble(scanner.nextLine());
        double height = Double.parseDouble(scanner.nextLine());

        Box box = new Box(length,width,height);

        System.out.println("Surface Area - "+String.format("%.2f",box.calculateSurfaceArea()));
        System.out.println("Lateral Surface Area - "+String.format("%.2f",box.calculateLateralSurfaceArea()));
        System.out.println("Volume - "+String.format("%.2f",box.calculateVolume()));
    }
}
