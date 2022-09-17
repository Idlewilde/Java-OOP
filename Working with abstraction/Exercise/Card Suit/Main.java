package Cards;

public class Main {
    public static void main(String[] args) {
        System.out.println("Card Suits:");
        for (Deck value : Deck.values()) {
            System.out.println("Ordinal value: "+value.ordinal()+"; Name value: "+value);
        }
    }
}
