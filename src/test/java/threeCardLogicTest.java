import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class threeCardLogicTest {

	Player playerOne;
	Player playerTwo;
	Dealer theDealer;

	@BeforeEach
	void init() {
		playerOne = new Player();
		playerTwo = new Player();
		theDealer = new Dealer();
	}

	@Test
	void testPlayerOneClass() {
		assertEquals("Player", playerOne.getClass().getName(), "Player class not found");
	}

	@Test
	void testPlayerTwoClass() {
		assertEquals("Player", playerTwo.getClass().getName(), "Player class not found");
	}

	@Test
	void testDealerClass() {
		assertEquals("Dealer", theDealer.getClass().getName(), "Dealer class not found");
	}

	@Test
	void straightFlushTest1() {
		playerOne.hand.add(new Card('C', 1));
		playerOne.hand.add(new Card('C', 2));
		playerOne.hand.add(new Card('C', 3));
		assertEquals(1, ThreeCardLogic.evalHand(playerOne.hand), "Should be straight flush");
	}

	@Test
	void straightFlushTest2() {
		playerOne.hand.add(new Card('S', 3));
		playerOne.hand.add(new Card('S', 1));
		playerOne.hand.add(new Card('S', 2));
		assertEquals(1, ThreeCardLogic.evalHand(playerOne.hand), "Should be straight flush");
	}

	@Test
	void straightFlushTest3() {
		playerOne.hand.add(new Card('D', 3));
		playerOne.hand.add(new Card('D', 2));
		playerOne.hand.add(new Card('D', 1));
		assertEquals(1, ThreeCardLogic.evalHand(playerOne.hand), "Should be straight flush");
	}

	@Test
	void straightFlushTest4() {
		playerOne.hand.add(new Card('H', 3));
		playerOne.hand.add(new Card('H', 2));
		playerOne.hand.add(new Card('H', 1));
		assertEquals(1, ThreeCardLogic.evalHand(playerOne.hand), "Should be straight flush");
	}

	@Test
	void straightTest1() {
		playerOne.hand.add(new Card('C', 1));
		playerOne.hand.add(new Card('S', 2));
		playerOne.hand.add(new Card('D', 3));
		assertEquals(3, ThreeCardLogic.evalHand(playerOne.hand), "Should be straight");
	}

	@Test
	void straightTest2() {
		playerOne.hand.add(new Card('S', 3));
		playerOne.hand.add(new Card('D', 1));
		playerOne.hand.add(new Card('C', 2));
		assertEquals(3, ThreeCardLogic.evalHand(playerOne.hand), "Should be straight");
	}

	@Test
	void straightTest3() {
		playerOne.hand.add(new Card('D', 3));
		playerOne.hand.add(new Card('D', 2));
		playerOne.hand.add(new Card('H', 1));
		assertEquals(3, ThreeCardLogic.evalHand(playerOne.hand), "Should be straight");
	}

	@Test
	void straightTest4() {
		playerOne.hand.add(new Card('H', 13));
		playerOne.hand.add(new Card('S', 11));
		playerOne.hand.add(new Card('C', 12));
		assertEquals(3, ThreeCardLogic.evalHand(playerOne.hand), "Should be straight");
	}

	@Test
	void threeOfKindTest1() {
		playerOne.hand.add(new Card('H', 9));
		playerOne.hand.add(new Card('D', 9));
		playerOne.hand.add(new Card('S', 9));
		assertEquals(2, ThreeCardLogic.evalHand(playerOne.hand), "Should be three of a kind");
	}

	@Test
	void threeOfKindTest2() {
		playerOne.hand.add(new Card('H', 5));
		playerOne.hand.add(new Card('S', 5));
		playerOne.hand.add(new Card('C', 5));
		assertEquals(2, ThreeCardLogic.evalHand(playerOne.hand), "Should be three of a kind");
	}

	@Test
	void threeOfKindTest3() {
		playerOne.hand.add(new Card('D', 2));
		playerOne.hand.add(new Card('H', 2));
		playerOne.hand.add(new Card('S', 2));
		assertEquals(2, ThreeCardLogic.evalHand(playerOne.hand), "Should be three of a kind");
	}

	@Test
	void threeOfKindTest4() {
		playerOne.hand.add(new Card('H', 7));
		playerOne.hand.add(new Card('H', 7));
		playerOne.hand.add(new Card('H', 7));
		assertEquals(2, ThreeCardLogic.evalHand(playerOne.hand), "Should be three of a kind");
	}

	@Test
	void flushTest1() {
		playerOne.hand.add(new Card('C', 1));
		playerOne.hand.add(new Card('C', 3));
		playerOne.hand.add(new Card('C', 5));
		assertEquals(4, ThreeCardLogic.evalHand(playerOne.hand), "Should be a flush");
	}

	@Test
	void flushTest2() {
		playerOne.hand.add(new Card('S', 5));
		playerOne.hand.add(new Card('S', 2));
		playerOne.hand.add(new Card('S', 13));
		assertEquals(4, ThreeCardLogic.evalHand(playerOne.hand), "Should be a flush");
	}

	@Test
	void flushTest3() {
		playerOne.hand.add(new Card('D', 1));
		playerOne.hand.add(new Card('D', 6));
		playerOne.hand.add(new Card('D', 3));
		assertEquals(4, ThreeCardLogic.evalHand(playerOne.hand), "Should be a flush");
	}

	@Test
	void flushTest4() {
		playerOne.hand.add(new Card('H', 2));
		playerOne.hand.add(new Card('H', 4));
		playerOne.hand.add(new Card('H', 9));
		assertEquals(4, ThreeCardLogic.evalHand(playerOne.hand), "Should be a flush");
	}

	@Test
	void pairTest1() {
		playerOne.hand.add(new Card('C', 1));
		playerOne.hand.add(new Card('H', 1));
		playerOne.hand.add(new Card('S', 5));
		assertEquals(5, ThreeCardLogic.evalHand(playerOne.hand), "Should be a pair");
	}

	@Test
	void pairTest2() {
		playerOne.hand.add(new Card('D', 5));
		playerOne.hand.add(new Card('C', 5));
		playerOne.hand.add(new Card('S', 13));
		assertEquals(5, ThreeCardLogic.evalHand(playerOne.hand), "Should be a pair");
	}

	@Test
	void pairTest3() {
		playerOne.hand.add(new Card('S', 3));
		playerOne.hand.add(new Card('H', 6));
		playerOne.hand.add(new Card('D', 3));
		assertEquals(5, ThreeCardLogic.evalHand(playerOne.hand), "Should be a pair");
	}

	@Test
	void pairTest4() {
		playerOne.hand.add(new Card('C', 2));
		playerOne.hand.add(new Card('D', 4));
		playerOne.hand.add(new Card('H', 2));
		assertEquals(5, ThreeCardLogic.evalHand(playerOne.hand), "Should be a pair");
	}

	@Test
	void pairPlusBetTest1() {
		// straight flush
		playerOne.hand.add(new Card('C', 1));
		playerOne.hand.add(new Card('C', 2));
		playerOne.hand.add(new Card('C', 3));
		assertEquals(82, ThreeCardLogic.evalPPWinnings(playerOne.hand, 2), "Bet returned should be 82");
	}

	@Test
	void pairPlusBetTest2() {
		// straight
		playerOne.hand.add(new Card('S', 3));
		playerOne.hand.add(new Card('D', 1));
		playerOne.hand.add(new Card('C', 2));
		assertEquals(14, ThreeCardLogic.evalPPWinnings(playerOne.hand, 2), "Bet returned should be 14");
	}

	@Test
	void pairPlusBetTest3() {
		// three of a kind
		playerOne.hand.add(new Card('H', 9));
		playerOne.hand.add(new Card('D', 9));
		playerOne.hand.add(new Card('S', 9));
		assertEquals(62, ThreeCardLogic.evalPPWinnings(playerOne.hand, 2), "Bet returned should be 62");
	}

	@Test
	void pairPlusBetTest4() {
		// flush
		playerOne.hand.add(new Card('C', 1));
		playerOne.hand.add(new Card('C', 3));
		playerOne.hand.add(new Card('C', 5));
		assertEquals(8, ThreeCardLogic.evalPPWinnings(playerOne.hand, 2), "Bet returned should be 8");
	}

	@Test
	void pairPlusBetTest5() {
		// pair
		playerOne.hand.add(new Card('C', 1));
		playerOne.hand.add(new Card('H', 1));
		playerOne.hand.add(new Card('S', 5));
		assertEquals(4, ThreeCardLogic.evalPPWinnings(playerOne.hand, 2), "Bet returned should be 4");
	}

	@Test
	void pairPlusBetTest6() {
		// none
		playerOne.hand.add(new Card('C', 1));
		playerOne.hand.add(new Card('S', 3));
		playerOne.hand.add(new Card('H', 5));
		assertEquals(0, ThreeCardLogic.evalPPWinnings(playerOne.hand, 2), "Bet returned should be 0");
	}

	@Test
	void compareHandsTest1() {
		// pair
		playerOne.hand.add(new Card('C', 13));
		playerOne.hand.add(new Card('H', 13));
		playerOne.hand.add(new Card('S', 5));

		// none
		theDealer.dealersHand.add(new Card('C', 13));
		theDealer.dealersHand.add(new Card('S', 12));
		theDealer.dealersHand.add(new Card('H', 5));

		assertEquals(2, ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand), "Player has a winner hand");
	}

	@Test
	void compareHandsTest2() {
		// pair
		theDealer.dealersHand.add(new Card('C', 13));
		theDealer.dealersHand.add(new Card('H', 13));
		theDealer.dealersHand.add(new Card('S', 5));

		// none
		playerOne.hand.add(new Card('C', 11));
		playerOne.hand.add(new Card('S', 13));
		playerOne.hand.add(new Card('H', 5));

		assertEquals(1, ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand), "Dealer has a winner hand");
	}

	@Test
	void compareHandsTest3() {
		// pair
		theDealer.dealersHand.add(new Card('C', 1));
		theDealer.dealersHand.add(new Card('H', 1));
		theDealer.dealersHand.add(new Card('S', 5));

		// pair
		playerOne.hand.add(new Card('C', 1));
		playerOne.hand.add(new Card('H', 1));
		playerOne.hand.add(new Card('S', 5));

		assertEquals(0, ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand), "Tie hand, both hands are equal");
	}
}
