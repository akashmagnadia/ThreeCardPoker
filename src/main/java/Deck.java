import java.util.ArrayList;
import java.util.Random;

public class Deck extends ArrayList<Card> {

    //create a new deck of 52 card sorted in random order
    Deck() {
        ArrayList<Card> theDeckTemp = new ArrayList<Card>(); //make a temporary deck with all cards in there
        int count = 0;
        for (int i = 2; i < 15; i++) {
            for (int j = 1; j < 5; j++) {
                char suit;
                switch (j) {
                    case 1:
                        suit = 'C'; //clubs
                        break;
                    case 2:
                        suit = 'D'; //diamonds
                        break;
                    case 3:
                        suit = 'S'; //spades
                        break;
                    case 4:
                        suit = 'H'; //hearts
                        break;
                    default:
                        suit = 'X'; //doesn't recognize
                        break;
                }
                theDeckTemp.add(count, new Card(suit, i));
                count++;
            }
        }

        // initialize the array
        for (int i = 0; i < 52; i++) {
            this.add(i, new Card('X', 0));
        }

        for (int i = 0; i < 52; i++) {
            boolean setCard = true;
            int value = ((new Random()).nextInt((14 - 2) + 1)) + 2; //between 2 and 14 inclusive
            int suitInt = ((new Random()).nextInt((4 - 1) + 1)) + 1; //between 1 and 4 inclusive
            char suit;
            switch (suitInt) {
                case 1:
                    suit = 'C'; //clubs
                    break;
                case 2:
                    suit = 'D'; //diamonds
                    break;
                case 3:
                    suit = 'S'; //spades
                    break;
                case 4:
                    suit = 'H'; //hearts
                    break;
                default:
                    suit = 'X'; //doesn't recognize
                    break;
            }

            Card card = theDeckTemp.get(i);

            int randomIndex = (new Random()).nextInt(52);
            while (this.get(randomIndex).suit != 'X') {
                randomIndex = (new Random()).nextInt(52);
            }
            this.set(randomIndex, card);
        }
    }

    // clear all the cards and create a brand new deck of 52 cards sorted in random order.
    void newDeck() {
        ArrayList<Card> theDeckTemp = new ArrayList<Card>(); //make a temporary deck with all cards in there
        int count = 0;
        for (int i = 2; i < 15; i++) {
            for (int j = 1; j < 5; j++) {
                char suit;
                switch (j) {
                    case 1:
                        suit = 'C'; //clubs
                        break;
                    case 2:
                        suit = 'D'; //diamonds
                        break;
                    case 3:
                        suit = 'S'; //spades
                        break;
                    case 4:
                        suit = 'H'; //hearts
                        break;
                    default:
                        suit = 'X'; //doesn't recognize
                        break;
                }
                theDeckTemp.add(count, new Card(suit, i));
                count++;
            }
        }

        // initialize the array
        for (int i = 0; i < 52; i++) {
            this.add(i, new Card('X', 0));
        }

        for (int i = 0; i < 52; i++) {
            boolean setCard = true;
            int value = ((new Random()).nextInt((14 - 2) + 1)) + 2; //between 2 and 14 inclusive
            int suitInt = ((new Random()).nextInt((4 - 1) + 1)) + 1; //between 1 and 4 inclusive
            char suit;
            switch (suitInt) {
                case 1:
                    suit = 'C'; //clubs
                    break;
                case 2:
                    suit = 'D'; //diamonds
                    break;
                case 3:
                    suit = 'S'; //spades
                    break;
                case 4:
                    suit = 'H'; //hearts
                    break;
                default:
                    suit = 'X'; //doesn't recognize
                    break;
            }

            Card card = theDeckTemp.get(i);

            int randomIndex = (new Random()).nextInt(52);
            while (this.get(randomIndex).suit != 'X') {
                randomIndex = (new Random()).nextInt(52);
            }
            this.set(randomIndex, card);
        }
    }
}
