package collectionHierarchy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String [] items = scanner.nextLine().split(" ");
        AddCollection addCollection = new AddCollection();
        AddRemoveCollection addRemoveCollection = new AddRemoveCollection();
        MyListImpl myListImpl = new MyListImpl();

        for (int i = 0; i < items.length; i++) {
            System.out.print(addCollection.add(items[i])+" ");

        }
        System.out.println();
        for (int i = 0; i < items.length; i++) {
            System.out.print(addRemoveCollection.add(items[i])+" ");

        }
        System.out.println();
        for (int i = 0; i < items.length; i++) {
            System.out.print(myListImpl.add(items[i])+" ");

        }
        System.out.println();

        int remove = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < remove; i++) {
            System.out.print(addRemoveCollection.remove()+" ");
        }
        System.out.println();

        for (int i = 0; i < remove; i++) {
            System.out.print(myListImpl.remove()+" ");
        }
    }
}
