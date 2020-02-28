import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class ThreeCardPokerGame extends Application {
	Player playerOne = new Player();
	Player playerTwo = new Player();
	Dealer theDealer = new Dealer();

	private TextField dealerTitle;
	private TextField player1Title;
	private TextField player2Title;

	private TextField player1Status;
	private TextField player2Status;

	private TextField player1Bets;
	private TextField player2Bets;

	private Button player1PlayButton;
	private Button player2PlayButton;
	private Button player1FoldButton;
	private Button player2FoldButton;
	private Button dealerDealButton;

	private ImageView dealerCard1Image;
	private ImageView dealerCard2Image;
	private ImageView dealerCard3Image;
	private ImageView player1Card1Image;
	private ImageView player1Card2Image;
	private ImageView player1Card3Image;
	private ImageView player2Card1Image;
	private ImageView player2Card2Image;
	private ImageView player2Card3Image;

	public static void main(String[] args) {
		//TODO: make sure methods and members specified are how they are specified in the PDF
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		theDealer.dealersHand = theDealer.dealHand();
		playerOne.hand = theDealer.dealHand();
		playerTwo.hand = theDealer.dealHand();

		primaryStage.setTitle("Let's Play Three Card Poker!!!");

		dealerTitle = new TextField("Dealer");
		player1Title = new TextField("Player One");
		player2Title = new TextField("Player Two");

		//Test
		Image image = new Image("13H.jpg");
		ImageView imageView = new ImageView(image);

		HBox hbox = new HBox(imageView);

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(dealerCard1Image);
		borderPane.setBottom(dealerTitle);

		VBox paneCenter = new VBox(10, player2Title);
		borderPane.setLeft(paneCenter);


		Scene scene = new Scene(hbox,1000,700);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
