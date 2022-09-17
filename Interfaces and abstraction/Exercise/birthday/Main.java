package birthday;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<Birthable> list = new ArrayList<>();

        while(!input.equals("End")){
            String [] elements = input.split(" ");
            if(input.startsWith("Pet")){
                Birthable pet = new Pet(elements[1],elements[2] );
                list.add(pet);
            }
            else if(input.startsWith("Robot")){
                Robot robot = new Robot(elements[1],elements[2] );
            }
            else if(input.startsWith("Citizen")){
                Birthable citizen = new Citizen(elements[1],Integer.parseInt(elements[2]),elements[3],elements[4] );
            list.add(citizen);
            }

            input = scanner.nextLine();
        }

        String year = scanner.nextLine();

        for (Birthable birthable : list) {
            if(birthable.getBirthDate().contains(year)){
                System.out.println(birthable.getBirthDate());
        }

        }
    }}


