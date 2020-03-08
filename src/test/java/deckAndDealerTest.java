import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class deckAndDealerTest {

    Player playerOne;
    Player playerTwo;
    Dealer theDealer;
    Deck deck;

    @BeforeEach
    void init() {
        theDealer = new Dealer();
    }

    @Test
    void classTest1() {
        assertEquals("Deck", theDealer.theDeck.getClass().getName(), "Class is supposed to be Deck");
    }

    @Test
    void classTest2() {
        assertEquals(0, theDealer.dealersHand.size(), "Dealer's hand should be null");
    }

    @Test
    void classTest3() {
        deck = new Deck();
        assertEquals("Deck", deck.getClass().getName(), "Class is supposed to be Deck");
    }

    @Test
    void classTest4() {
        deck = new Deck();
        deck.newDeck();
        assertEquals("Deck", deck.getClass().getName(), "Class is supposed to be Deck");
    }

    @Test
    void dealerHandSize1() {
        theDealer.dealersHand.add(new Card('H', 5));

        assertEquals(1, theDealer.dealersHand.size(), "Size of dealer's hand should be 1");
    }

    @Test
    void dealerHandSize2() {
        theDealer.dealersHand.add(new Card('C', 13));
        theDealer.dealersHand.add(new Card('S', 12));

        assertEquals(2, theDealer.dealersHand.size(), "Size of dealer's hand should be 2");
    }

    @Test
    void dealerHandSize3() {
        theDealer.dealersHand.add(new Card('C', 13));
        theDealer.dealersHand.add(new Card('S', 12));
        theDealer.dealersHand.add(new Card('H', 5));

        assertEquals(3, theDealer.dealersHand.size(), "Size of dealer's hand should be 3");
    }

    @Test
    void dealerHandSize4() {
        theDealer.dealersHand.add(new Card('C', 13));
        theDealer.dealersHand.add(new Card('S', 12));
        theDealer.dealersHand.add(new Card('H', 5));
        theDealer.dealersHand.clear();

        assertEquals(0, theDealer.dealersHand.size(), "Size of dealer's hand should be 0");
    }

    @Test
    void dealerHandSize5() {
        theDealer.dealersHand.add(new Card('C', 13));
        theDealer.dealersHand.add(new Card('S', 12));
        theDealer.dealersHand.clear();

        assertEquals(0, theDealer.dealersHand.size(), "Size of dealer's hand should be 0");
    }

    @Test
    void dealerHandSize6() {
        theDealer.dealersHand.add(new Card('C', 13));
        theDealer.dealersHand.clear();

        assertEquals(0, theDealer.dealersHand.size(), "Size of dealer's hand should be 0");
    }
}


