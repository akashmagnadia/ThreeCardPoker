import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;

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
	private Button startButton;
	private Button exitButton;

	private ImageView dealerCard1Image;
	private ImageView dealerCard2Image;
	private ImageView dealerCard3Image;
	private ImageView player1Card1Image;
	private ImageView player1Card2Image;
	private ImageView player1Card3Image;
	private ImageView player2Card1Image;
	private ImageView player2Card2Image;
	private ImageView player2Card3Image;

	private HashMap<String, Scene> sceneMap;

	public static void main(String[] args) {
		//TODO: make sure methods and members specified are how they are specified in the PDF
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Let's Play Three Card Poker!!!");

		theDealer.dealersHand = theDealer.dealHand();
		playerOne.hand = theDealer.dealHand();
		playerTwo.hand = theDealer.dealHand();


		sceneMap = new HashMap<String,Scene>();
		dealerTitle = new TextField("Dealer");
		player1Title = new TextField("Player One");
		player2Title = new TextField("Player Two");

		//Test
		Image image = new Image("13H.jpg");
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(100);

		HBox hbox = new HBox(imageView);

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(dealerCard1Image);
		borderPane.setBottom(dealerTitle);

		VBox paneCenter = new VBox(10, player2Title);
		borderPane.setLeft(paneCenter);

		sceneMap.put("startingScene", startingScene());

		primaryStage.setScene(sceneMap.get("startingScene"));
		primaryStage.show();
	}

	public Scene startingScene() {
		BorderPane pane = new BorderPane();

		startButton = new Button("Start");
		exitButton = new Button("Exit");
		VBox vbox = new VBox(10, startButton, exitButton);
		vbox.setAlignment(Pos.BASELINE_CENTER);


		//setting the background
		Image backgroundImage = new Image("Poker_table_background.jpg");
		BackgroundSize bgSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
		BackgroundImage bgImage =
				new BackgroundImage(
						backgroundImage,
						BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT,
						BackgroundPosition.CENTER,
						bgSize);
		Background background = new Background(bgImage);
		vbox.setBackground(background);

		pane.setCenter(vbox);
		pane.setBackground(background);
		return new Scene (pane, 1000, 700);
	}
}
