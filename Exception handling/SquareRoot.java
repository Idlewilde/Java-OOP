import java.util.Scanner;

public class SquareRoot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String num = scanner.nextLine();


        try {
            int number = Integer.parseInt(num);
            System.out.println(String.format("%.2f", Math.pow(number, 0.5)));
        }
        catch(NumberFormatException ex){
            System.out.println("Invalid");
        } finally {
            System.out.println("Goodbye");
        }
    }}
