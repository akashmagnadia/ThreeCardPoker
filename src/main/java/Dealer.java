import java.util.ArrayList;

//  the Dealer class must check to see if there are more than 34 cards left in the deck.
//  If not, theDeck must be reshuffled with a new set of 52 cards in random order.
public class Dealer {
    Deck theDeck;

    // hold the dealers hand in each game
    ArrayList<Card> dealersHand;

    //initialize theDeck
    Dealer() {
        this.theDeck = new Deck();
        this.dealersHand = new ArrayList<Card>();
    }

    //will return an ArrayList<Card> of three cards removed from theDeck
    public ArrayList<Card> dealHand() {
        ArrayList<Card> toReturn = new ArrayList<Card>();
        int count = 0;
        int i = 0;
        while (true) {
            //put 3 cards in array and mark down when card is chosen from the deck
            //check if new deck needs to be made
            if (this.theDeck.get(i).suit != 'X') {
                toReturn.add(this.theDeck.get(i));
                this.theDeck.set(i, new Card('X', 0));
                count++;
            } else {
                i++;
                continue;
            }

            if (count == 3) {
                break;
            }

            //count== how many cards were taken from the deck
            //check if there are more than 34 cards left in the deck
            //if less than or equal to 34 cards left in the deck
            if (this.theDeck.get(18).suit == 'X') {
                this.theDeck.newDeck();
            }
        }
        return toReturn;
    }
}
