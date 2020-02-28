public class Card
{
    char suit;
    int value;

    //to make a new card call this constructor

    //suits
    //C = clubs
    //D = diamonds
    //S = spades
    //H = hearts

    // value will be an integer value between 2 - 14, with the value of an
    // ace being 14, king 13, queen 12, jack 11, ten 10…..and so on

    Card(char suit, int value) {
        this.suit = suit;
        this.value = value;
    }
}
