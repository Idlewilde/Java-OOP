package workingWithAbstraction.jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int x = dimensions[0];
        int y = dimensions[1];

        int [][]matrix=fillMatrix(x, y);

        String command = scanner.nextLine();

        long stars = 0;
        while (!command.equals("Let the Force be with you")) {
            int[] collectStarsDiagonal = Arrays.stream(command.split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] darkForceDiagonal = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int darkForceX = darkForceDiagonal[0];
            int darkForceY = darkForceDiagonal[1];

            darkForceAction(matrix, darkForceX, darkForceY);

            int starsCollectionX = collectStarsDiagonal[0];
            int starsCollectionY = collectStarsDiagonal[1];

            stars = starCollection(matrix, stars, starsCollectionX, starsCollectionY);

            command = scanner.nextLine();
        }

        System.out.println(stars);


    }

    private static long starCollection(int[][] matrix, long stars, int starsCollectionX, int starsCollectionY) {
        while (starsCollectionX >= 0 && starsCollectionY < matrix[1].length) {
            if (starsCollectionX < matrix.length && starsCollectionY >= 0 && starsCollectionY < matrix[0].length) {
                stars += matrix[starsCollectionX][starsCollectionY];
            }

            starsCollectionY++;
            starsCollectionX--;
        }
        return stars;
    }

    private static void darkForceAction(int[][] matrix, int darkForceX, int darkForceY) {
        while (darkForceX >= 0 && darkForceY >= 0) {
            if (darkForceX < matrix.length && darkForceY < matrix[0].length) {
                matrix[darkForceX][darkForceY] = 0;
            }
            darkForceX--;
            darkForceY--;
        }
    }

    private static int [][] fillMatrix(int x, int y) {
        int value = 0;
        int[][] matrix = new int [x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                matrix[i][j] = value++;
            }

        }
        return  matrix;
    }
}
