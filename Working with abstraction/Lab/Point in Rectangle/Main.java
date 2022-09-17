package PointInRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int [] coordinates =  Arrays
                .stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        Point point1 = new Point(coordinates[0],coordinates[1]);
        Point point2 = new Point(coordinates[2],coordinates[3]);
        Rectangle rectangle = new Rectangle(point1,point2);

        int points = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < points; i++) {
            String input = scanner.nextLine();
            int x = Integer.parseInt(input.split(" ")[0]);
            int y = Integer.parseInt(input.split(" ")[1]);
            System.out.println(rectangle.Contains(x, y));

        }



    }
}
