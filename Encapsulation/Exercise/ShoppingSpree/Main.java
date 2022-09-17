package ShoppingSpree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String [] peopleInfo = scanner.nextLine().split(";");
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < peopleInfo.length; i++) {
            Person person = new Person(peopleInfo[i].split("=")[0],
                    Double.parseDouble(peopleInfo[i].split("=")[1]));
            people.add(person);
        }
        String [] productInfo = scanner.nextLine().split(";");
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < productInfo.length; i++) {
            Product product = new Product(productInfo[i].split("=")[0],
                    Double.parseDouble(productInfo[i].split("=")[1]));
            products.add(product);
        }

        String input = scanner.nextLine();

        while(!input.equals("END")){
String name = input.split(" ")[0];
String productName = input.split(" ")[1];
Person person = null;
Product product = null;
            for (Product product1 : products) {
                if(product1.getName().equals(productName)){product=product1;}
            }
            for (Person person1 : people) {
                if(person1.getName().equals(name)){person=person1;}
            }
            person.buyProduct(product);

            input = scanner.nextLine();
        }

        for (Person person : people) {
            if(person.getProducts().isEmpty()){
                System.out.println(person.getName()+" - Nothing bought");
        }
            else{
                System.out.println(person.getName()+" - "+String.join(", ",person.getProducts().toString()));



    }
}}}
