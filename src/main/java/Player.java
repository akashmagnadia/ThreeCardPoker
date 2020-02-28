import java.util.ArrayList;

//This class represents a player in the game. It keeps track of each games current hand
//and current bets as well as the total winnings for that player across multiple games. If
//the player has lost more than he/she has won, that number can be negative. Provide a
//no argument constructor for this class

public class Player {
    ArrayList<Card> hand;
    int anteBet;
    int playBet;
    int pairPlusBet;
    int totalWinnings;

    Player() {
        this.hand = new ArrayList<Card>();
        this.anteBet = 0;
        this.playBet = 0;
        this.pairPlusBet = 0;
        this.totalWinnings = 0;
    }
}
