package StudentSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentSystem studentSystem = new StudentSystem();
        String input = scanner.nextLine();
        while (!input.equals("Exit")) {
            String name = input.split(" ")[1];
            if(input.startsWith("Create")){
                Student student =registerStudent(input);
                studentSystem.add(student);
            }

            else if(input.startsWith("Show")){
                System.out.println(studentSystem.Show(name));}

                input=scanner.nextLine();
            }
        }

    private static Student registerStudent(String input) {
        String name = input.split(" ")[1];
        int age = Integer.parseInt(input.split(" ")[2]);
        double grade = Double.parseDouble(input.split(" ")[3]);
        return new Student(name,age,grade);
    }
}

