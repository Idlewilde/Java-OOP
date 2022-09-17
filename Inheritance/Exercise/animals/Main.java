package animals;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String animal = scanner.nextLine();

    while(!animal.equals("Beast!")){
        String [] info = scanner.nextLine().split(" ");
        String name = info[0];
        int age = Integer.parseInt(info[1]);
        String gender = info[2];

        try{
       if(animal.equals("Cat")){
            Cat cat = new Cat(name,age,gender);
           System.out.println(cat);
       }
       else if(animal.equals("Dog")){
           Dog dog = new Dog(name,age,gender);
           System.out.println(dog);
       }
       else if(animal.equals("Frog")){
           Frog frog = new Frog(name,age,gender);
           System.out.println(frog);
       }
       else if(animal.equals("Kitten")){
           Kitten kitten = new Kitten(name,age);
           System.out.println(kitten);
       }
       else if(animal.equals("Tomcat")){
           Tomcat tomcat = new Tomcat(name,age);
           System.out.println(tomcat);

       }}catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        animal = scanner.nextLine();
    }
    }

}
