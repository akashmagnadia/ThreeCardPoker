import java.util.ArrayList;
import java.util.Arrays;

public class ThreeCardLogic {

    // 0 if the hand just has a high card
    // 1 for a straight flush
    // 2 for three of a kind
    // 3 for a straight
    // 4 for a flush
    // 5 for a pair
    public static int evalHand(ArrayList<Card> hand) {
        int card1Value = hand.get(0).value;
        int card2Value = hand.get(1).value;
        int card3Value = hand.get(2).value;
        int card1Suit = hand.get(0).suit;
        int card2Suit = hand.get(1).suit;
        int card3Suit = hand.get(2).suit;

        int[] tempValueArray = {card1Value, card2Value, card3Value};
        Arrays.sort(tempValueArray); //gets sort from small to large value

        // check straight flush
        if (((card1Value + 1) == card2Value) && ((card2Value + 1) == card3Value)) {
            if ((card1Suit == card2Suit) && (card2Suit == card3Suit)) {
                return 1;
            }
        }

        // check three of a kind
        if ((card1Value == card2Value) && (card2Value == card3Value)) {
            return 2;
        }

        // check straight
        if (((card1Value + 1) == card2Value) && ((card2Value + 1) == card3Value)) {
            return 3;
        }

        // check flush
        if ((card1Suit == card2Suit) && (card2Suit == card3Suit)) {
            return 4;
        }

        // check pair
        if ((card1Value == card2Value) || (card2Value == card3Value)) {
            return 5;
        }

        //default
        return 0;
    }

    // return the amount won for the PairPlus bet. It will
    //evaluate the hand and then evaluate the winnings and return the amount won. If the
    //player lost the Pair Plus bet, it will just return 0.
    public static int evalPPWinnings(ArrayList<Card> hand, int bet) {

        //Straight Flush
        if (evalHand(hand) == 1) {
            return bet + bet*40;
        }

        //Three of a Kind
        if (evalHand(hand) == 2) {
            return bet + bet*30;
        }

        //Straight
        if (evalHand(hand) == 3) {
            return bet + bet*6;
        }

        //Flush
        if (evalHand(hand) == 4) {
            return bet + bet*3;
        }

        //Pair
        if (evalHand(hand) == 5) {
            return bet + bet;
        }

        //default
        return 0;
    }

    // 0 if neither hand won
    // 1 if the dealer hand won
    // 2 if the player hand won
    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
        //check if there is at least a queen if not nobody wins
        if ((dealer.get(0).value < 12) && (dealer.get(1).value < 12) && (dealer.get(2).value < 12)) {
            return 0;
        }
        int dealerEvaluated = evalHand(dealer);
        int playerEvaluated = evalHand(player);

        //player wins
        if ((dealerEvaluated == 0) && (playerEvaluated != 0)) {
            return 2;
        }
        //dealer wins
        else if ((dealerEvaluated != 0) && (playerEvaluated == 0)) {
            return 1;
        }

        //both players have high cards
        else {
            int playerCard1Value = player.get(0).value;
            int playerCard2Value = player.get(1).value;
            int playerCard3Value = player.get(2).value;

            int dealerCard1Value = dealer.get(0).value;
            int dealerCard2Value = dealer.get(1).value;
            int dealerCard3Value = dealer.get(2).value;

            int[] playerValueArray = {playerCard1Value, playerCard2Value, playerCard3Value};
            int[] dealerValueArray = {dealerCard1Value, dealerCard2Value, dealerCard3Value};
            Arrays.sort(playerValueArray);
            Arrays.sort(dealerValueArray);

            if (playerValueArray[0] > dealerValueArray[0]) {
                //player wins
                return 2;
            } else if (playerValueArray[0] < dealerValueArray[0]) {
                //dealer wins
                return 1;
            } else {
                //biggest values are same
                //compare second value

                if (playerValueArray[1] > dealerValueArray[1]) {
                    //player wins
                    return 2;
                } else if (playerValueArray[1] < dealerValueArray[1]) {
                    //dealer wins
                    return 1;
                } else {
                    //second biggest values are same
                    //compare third value
                    if (playerValueArray[2] > dealerValueArray[2]) {
                        //player wins
                        return 2;
                    } else if (playerValueArray[2] < dealerValueArray[2]) {
                        //dealer wins
                        return 1;
                    } else {
                        //third biggest values are same
                        //both hands are the same
                        return 0;
                    }
                }
            }
        }
    }
}
