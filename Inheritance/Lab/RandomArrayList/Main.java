public class Main {
    public static void main(String[] args) {
        RandomArrayList <Integer> rand = new RandomArrayList<>();
        rand.add(1);
        rand.add(3);
        rand.add(5);

        System.out.println(rand.getRandomElement());
    }
}
