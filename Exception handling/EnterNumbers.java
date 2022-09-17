import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnterNumbers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Integer> numbers = new ArrayList<>();

        while (numbers.size() < 10) {
            String input = scan.nextLine();

            try {
                int num = Integer.parseInt(input);

                try {
                    getAddToList(numbers, num);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }

            } catch (NumberFormatException ex) {
                System.out.println("Invalid Number!");
            }

        }
        System.out.println(String.join(", ", numbers.toString().replaceAll("\\[", "").replaceAll("]", "")));
    }

    private static void getAddToList(List<Integer> numbers, int num) {
        if (numbers.isEmpty()) {
            if (num <= 1 || num >= 100) {
                throw new IllegalArgumentException("Your number is not in range 1 - 100!");
            }  else {
                numbers.add(num);
            }
        } else {
            int lastEnteredNum = numbers.get(numbers.size() - 1);
            if (num <= lastEnteredNum) {
                throw new IllegalArgumentException(String.format("Your number is not in range %d - 100!",lastEnteredNum));
            }  else {
                numbers.add(num);
            }
        }
    }
}
