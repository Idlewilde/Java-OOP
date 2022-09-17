package CardsWithPower;

public class Card {
    private Suits suit;
    private Ranks rank;

    public Card(Suits suit, Ranks rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int calculatePower(){
        return this.rank.getValue()+this.suit.getValue();
    }

    public String generateInfo(){
        return "Card name: "+this.rank+" of "+this.suit+"; Card power: "+calculatePower();
    }
}
