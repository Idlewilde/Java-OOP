package foodShortage;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lines = Integer.parseInt(scanner.nextLine());
        Map<String, Citizen> citizens = new LinkedHashMap<>();
        Map<String, Rebel> rebels = new LinkedHashMap<>();

        for (int i = 0; i < lines; i++) {
            String[] input = scanner.nextLine().split(" ");
            if (input.length == 3) {
                Rebel rebel = new Rebel(input[0], Integer.parseInt(input[1]), input[2]);
                rebels.put(input[0], rebel);
            } else {
                Citizen citizen = new Citizen(input[0], Integer.parseInt(input[1]), input[2], input[3]);
                citizens.put(input[0], citizen);
            }

        }

        String name = scanner.nextLine();
        int sumFood = 0;

        while (!name.equals("End")) {
            if (citizens.containsKey(name)) {
                citizens.get(name).buyFood();
                sumFood = sumFood + 10;
            } else if (rebels.containsKey(name)) {
                rebels.get(name).buyFood();
                sumFood = sumFood + 5;
            }
            name = scanner.nextLine();
        }

        System.out.println(sumFood);


    }
}
