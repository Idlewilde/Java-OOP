package borderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        List<Identifiable> units = new ArrayList<>();

        while(!input.equals("End")){
            String [] info = input.split(" ");

            if(info.length==2){
                Identifiable robot = new Robot(info[0],info[1]);
                units.add(robot);
            }
            else{
                Identifiable citizen = new Citizen(info[0],Integer.parseInt(info[1]),info[2]);
                units.add(citizen);
            }

            input = scanner.nextLine();
        }
        String fake = scanner.nextLine();

        units.stream().filter(e -> e.getId().endsWith(fake)).forEach(e-> System.out.println(e.getId()));


    }
}
