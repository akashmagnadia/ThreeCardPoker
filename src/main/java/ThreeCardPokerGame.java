import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;

import java.util.HashMap;

public class ThreeCardPokerGame extends Application {
	Player playerOne = new Player();
	Player playerTwo = new Player();
	Dealer theDealer = new Dealer();

	private TextField dealerTitle;
	private TextField player1Status;
	private TextField player2Status;
	private TextField player1PlayWager;
	private TextField player1PairPlusWager;
	private TextField player2PlayWager;
	private TextField player2PairPlusWager;
	private TextField player1AnteBets;
	private TextField player2AnteBets;
	private TextField player1Winnings;
	private TextField player2Winnings;
	private TextField messageBoard;

	private Button player1Title;
	private Button player2Title;
	private Button player1PlayButton;
	private Button player2PlayButton;
	private Button player1FoldButton;
	private Button player2FoldButton;
	private Button dealerDealButton;

	Image dealerCard1Image;
	Image dealerCard2Image;
	Image dealerCard3Image;
	Image player1Card1Image;
	Image player1Card2Image;
	Image player1Card3Image;
	Image player2Card1Image;
	Image player2Card2Image;
	Image player2Card3Image;

	private ImageView dealerCard1ImageView;
	private ImageView dealerCard2ImageView;
	private ImageView dealerCard3ImageView;
	private ImageView player1Card1ImageView;
	private ImageView player1Card2ImageView;
	private ImageView player1Card3ImageView;
	private ImageView player2Card1ImageView;
	private ImageView player2Card2ImageView;
	private ImageView player2Card3ImageView;

	private Boolean player1Playing;
	private Boolean player2Playing;
	private Boolean showScene1;
	private Boolean player1Done;
	private Boolean player2Done;

	private double twoMessageWait;
	private double threeMessageWait;

	private String player1StatusString;
	private String player2StatusString;
	private String player1PlayWagerString;
	private String player2PlayWagerString;
	private String player1AnteBetsString;
	private String player2AnteBetsString;
	private String player1PairPlusString;
	private String player2PairPlusString;
	private String player1WinningsString;
	private String player2WinningsString;
	private String messageString;
	private String player2MessageString;

	Menu menu;
	MenuItem freshStart;
	MenuItem newLook;
	MenuItem exit;
	MenuBar menuBar;

	private BorderPane pane;
	Image backgroundImage;
	BackgroundSize bgSize;
	BackgroundImage bgImage;
	 Background background;

	public static void main(String[] args) {
		//TODO: make sure methods and members specified are how they are specified in the PDF
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Let's Play Three Card Poker!!!");

		HashMap<String, Scene> sceneMap = new HashMap<String, Scene>();

		//both players start in the beginning of the game
		player1Playing = true;
		player2Playing = true;
		player1Done = false;
		player2Done = false;
		showScene1 = true;
		threeMessageWait = 2; //2 seconds


		//building menubar
		menu = new Menu("Options");

		freshStart = new MenuItem("Fresh Start");
		newLook = new MenuItem("New Look");
		exit = new MenuItem("Exit");

		menu.getItems().add(freshStart);
		menu.getItems().add(newLook);
		menu.getItems().add(exit);

		menuBar = new MenuBar();
		menuBar.getMenus().add(menu);

		freshStart.setOnAction(actionEvent -> {
			theDealer.theDeck.newDeck();
			theDealer.dealersHand.clear();
			playerOne.hand.clear();
			playerTwo.hand.clear();

			player1Playing = true;
			player2Playing = true;
			player1Done = false;
			player2Done = false;

			dealerCard1ImageView.setImage(new Image("blue_back.jpg"));
			dealerCard2ImageView.setImage(new Image("blue_back.jpg"));
			dealerCard3ImageView.setImage(new Image("blue_back.jpg"));
			player1Card1ImageView.setImage(new Image("blue_back.jpg"));
			player1Card2ImageView.setImage(new Image("blue_back.jpg"));
			player1Card3ImageView.setImage(new Image("blue_back.jpg"));
			player2Card1ImageView.setImage(new Image("blue_back.jpg"));
			player2Card2ImageView.setImage(new Image("blue_back.jpg"));
			player2Card3ImageView.setImage(new Image("blue_back.jpg"));

			playerOne.totalWinnings = 0;
			playerTwo.totalWinnings = 0;
			player1Winnings.setText("Wins: $" + playerOne.totalWinnings);
			player2Winnings.setText("Wins: $" + playerTwo.totalWinnings);

			player1AnteBets.clear();
			player1PlayWager.clear();
			player1PlayWager.clear();
			player2AnteBets.clear();
			player2PlayWager.clear();
			player2PlayWager.clear();

			player1Title.setDisable(false);
			player2Title.setDisable(false);
			player1PlayButton.setDisable(false);
			player1FoldButton.setDisable(false);
			player2PlayButton.setDisable(false);
			player2FoldButton.setDisable(false);

			messageBoard.setText("Hover over text fields or buttons");
			player1Status.setText("Playing");
			player2Status.setText("Playing");
			player1Status.setTooltip(new Tooltip("Player one is playing the game"));
			player2Status.setTooltip(new Tooltip("Player two is playing the game"));
		});

		newLook.setOnAction(actionEvent -> {
			showScene1 = !showScene1;
			setBackground();
			setStyle();
		});

		exit.setOnAction(actionEvent -> System.exit(0));

		//putting scene in hash map and calling it
		sceneMap.put("startingScene1", startingScene());

		primaryStage.setScene(sceneMap.get("startingScene1"));
		listenForActions();
		primaryStage.show();
	}

	public void listenForActions() {
		dealerDealButton.setOnAction(actionEvent -> {
			theDealer.dealersHand = theDealer.dealHand();
			if (player1Playing) {
				playerOne.hand = theDealer.dealHand();
				player1Done = false;
			} else {
				player1Done = true; //Player 1 will always be done if not playing
			}
			if (player2Playing) {
				player2Done = false;
				playerTwo.hand = theDealer.dealHand();
			} else {
				player2Done = true; //Player 2 will always be done if not playing
			}

			showPlayer1Cards();
			showPlayer2Cards();

			//activate both buttons
			player1Title.setDisable(false);
			player2Title.setDisable(false);
		});

		player1Title.setOnAction(actionEvent -> {
			if (player1Playing) {
				//if player 1 is playing and player 1 want to pause
				if (player2Playing) {
					player1Done = true; //if player one is not playing. It is always done

					player1Playing = false;
					messageBoard.setText("Player one is not playing anymore");
					updatePlayerStatus();
				} else {
					messageBoard.setText("At least one player must be playing");
				}
			} else {
				//if player 1 is paused and player 1 want to play
				player1Done = false;
				player1Playing = true;
				updatePlayerStatus();
			}
		});

		player2Title.setOnAction(actionEvent -> {
			if (player2Playing) {
				//if player 2 is playing and player 2 want to pause
				if (player1Playing) {
					player2Done = true; //if player one is not playing. It is always done

					player2Playing = false;
					messageBoard.setText("Player two is not playing anymore");
					updatePlayerStatus();
				} else {
					messageBoard.setText("At least one player must be playing");
				}
			} else {
				//if player 2 is paused and player 2 want to play
				player2Done = false;
				player2Playing = true;
				updatePlayerStatus();
			}
		});

		player1PlayButton.setOnAction(actionEvent -> {
			int anteBet = 0;
			if (!(player1AnteBets.getText().equals(""))) {
				anteBet = Integer.parseInt(player1AnteBets.getText());
			}

			if (anteBet <= 0) {
				messageBoard.setText("Player one's ante wager must be positive");
			} else {
				if ((anteBet < 5) || (anteBet > 25)) {
					messageBoard.setText("Player one's ante wager must be between 5 and 25");
				} else {
					if (!(player1PlayWager.getText().equals(String.valueOf(anteBet)))) {
						messageBoard.setText("Player one's play wager must be equal to ante wager");
					} else {
						player1Done = true; //player 1 has taken the turn

						if (player2Done) {
							showDealersCards(); //since both players are done

							//evaluate play and pair plus bet
							int player1TypeHand = ThreeCardLogic.evalHand(playerOne.hand);
							int player1PairPlusWon = ThreeCardLogic.evalPPWinnings(playerOne.hand, playerOne.pairPlusBet);
							int player1WinningPerson = ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand);

							String player1TypeHandMessage;
							if (player1TypeHand == 0) {
								player1TypeHandMessage = "Player 1 has a high card";
							} else if (player1TypeHand == 1) {
								player1TypeHandMessage = "Player 1 has a straight flush";
							}
							else if (player1TypeHand == 2) {
								player1TypeHandMessage = "Player 1 has three of a kind";
							}
							else if (player1TypeHand == 3) {
								player1TypeHandMessage = "Player 1 has a straight";
							}
							else if (player1TypeHand == 4) {
								player1TypeHandMessage = "Player 1 has a flush";
							} else {
								//5
								player1TypeHandMessage = "Player 1 has a pair";
							}

							String player1PairPlusWonMessage = "Player one pair plus winning is " + player1PairPlusWon;
							String player1Player1WinningPersonMessage;

							if (player1WinningPerson == 0) {
								player1Player1WinningPersonMessage = "Tie between Dealer and Player One.";
							} else if (player1WinningPerson == 1) {
								player1Player1WinningPersonMessage = "Player One lost to the dealer";
							} else {
								player1Player1WinningPersonMessage = "The Dealer lost to player one";
							}

							if (player2Playing) {
								//show both player messages because both players are done
								int player2TypeHand = ThreeCardLogic.evalHand(playerTwo.hand);
								int player2PairPlusWon = ThreeCardLogic.evalPPWinnings(playerTwo.hand, playerTwo.pairPlusBet);
								int player2WinningPerson = ThreeCardLogic.compareHands(theDealer.dealersHand, playerTwo.hand);

								String player2TypeHandMessage;
								if (player2TypeHand == 0) {
									player2TypeHandMessage = "Player 2 has a high card";
								} else if (player2TypeHand == 1) {
									player2TypeHandMessage = "Player 2 has a straight flush";
								}
								else if (player2TypeHand == 2) {
									player2TypeHandMessage = "Player 2 has three of a kind";
								}
								else if (player2TypeHand == 3) {
									player2TypeHandMessage = "Player 2 has a straight";
								}
								else if (player2TypeHand == 4) {
									player2TypeHandMessage = "Player 2 has a flush";
								} else {
									//5
									player2TypeHandMessage = "Player 2 has a pair";
								}

								String player2PairPlusWonMessage = "Player two pair plus winning is " + player2PairPlusWon;
								String player2Player1WinningPersonMessage;

								if (player2WinningPerson == 0) {
									player2Player1WinningPersonMessage = "Tie between Dealer and Player two.";
								} else if (player2WinningPerson == 1) {
									player2Player1WinningPersonMessage = "Player two lost to the dealer";
								} else {
									player2Player1WinningPersonMessage = "The Dealer lost to player two";
								}

								show3Message(
										player1TypeHandMessage + " " + player2TypeHandMessage,
										player1PairPlusWonMessage + " " + player2PairPlusWonMessage,
										player1Player1WinningPersonMessage + " " + player2Player1WinningPersonMessage,
										threeMessageWait);
							} else {
								//show only player 1 message
								show3Message(
										player1TypeHandMessage,
										player1PairPlusWonMessage,
										player1Player1WinningPersonMessage,
										threeMessageWait);
							}
						} else {
							//wait for player 2
							//player 2 must play now
							show1Message("Waiting for player 2 to take turn");
							player2Title.setDisable(true);
						}
					}
					}
			}
		});

		player1FoldButton.setOnAction(actionEvent -> {
			int pairPlusBet = 0;
			if (!(player1PairPlusWager.getText().equals(""))) {
				pairPlusBet = Integer.parseInt(player1PairPlusWager.getText());
			}

			if (pairPlusBet <= 0) {
				messageBoard.setText("Player one's pair plus wager must be positive");
			} else {
				if ((pairPlusBet < 5) || (pairPlusBet > 25)) {
					messageBoard.setText("Player one's pair plus wager must be between 5 and 25");
				} else {
					player1Done = true; //player 1 has taken the turn
					player1PairPlusWager.setText(String.valueOf(pairPlusBet)); //put ante bet in play wager

					player1PlayWager.clear();
					player1AnteBets.clear();

					if (player2Done) {
						showDealersCards(); //since both players are done
						//evaluate pair plus bet only
						int player1TypeHand = ThreeCardLogic.evalHand(playerOne.hand);
						int player1PairPlusWon = ThreeCardLogic.evalPPWinnings(playerOne.hand, playerOne.pairPlusBet);

						String player1TypeHandMessage;
						if (player1TypeHand == 0) {
							player1TypeHandMessage = "Player 1 has a high card";
						} else if (player1TypeHand == 1) {
							player1TypeHandMessage = "Player 1 has a straight flush";
						}
						else if (player1TypeHand == 2) {
							player1TypeHandMessage = "Player 1 has three of a kind";
						}
						else if (player1TypeHand == 3) {
							player1TypeHandMessage = "Player 1 has a straight";
						}
						else if (player1TypeHand == 4) {
							player1TypeHandMessage = "Player 1 has a flush";
						} else {
							//5
							player1TypeHandMessage = "Player 1 has a pair";
						}

						String player1PairPlusWonMessage = "Player one pair plus winning is " + player1PairPlusWon;

						if (player2Playing) {
							//show both player messages because both players are done
							int player2TypeHand = ThreeCardLogic.evalHand(playerTwo.hand);
							int player2PairPlusWon = ThreeCardLogic.evalPPWinnings(playerTwo.hand, playerTwo.pairPlusBet);

							String player2TypeHandMessage;
							if (player2TypeHand == 0) {
								player2TypeHandMessage = "Player 2 has a high card";
							} else if (player2TypeHand == 1) {
								player2TypeHandMessage = "Player 2 has a straight flush";
							} else if (player2TypeHand == 2) {
								player2TypeHandMessage = "Player 2 has three of a kind";
							} else if (player2TypeHand == 3) {
								player2TypeHandMessage = "Player 2 has a straight";
							} else if (player2TypeHand == 4) {
								player2TypeHandMessage = "Player 2 has a flush";
							} else {
								//5
								player2TypeHandMessage = "Player 2 has a pair";
							}

							String player2PairPlusWonMessage = "Player two pair plus winning is " + player2PairPlusWon;

							show2Message(
									player1TypeHandMessage + " " + player2TypeHandMessage,
									player1PairPlusWonMessage + " " + player2PairPlusWonMessage,
									twoMessageWait);
						} else {
							//show only player 1 message
							show2Message(
									player1TypeHandMessage,
									player1PairPlusWonMessage,
									twoMessageWait);
						}
					} else {
						//wait for player 2
						//player 2 must play now
						show1Message("Waiting for player 2 to take turn");
						player2Title.setDisable(true);
					}
				}
			}
		});

		player2PlayButton.setOnAction(actionEvent -> {
			int anteBet = 0;
			if (!(player2AnteBets.getText().equals(""))) {
				anteBet = Integer.parseInt(player2AnteBets.getText());
			}

			if (anteBet <= 0) {
				messageBoard.setText("Player two's ante wager must be positive");
			} else {
				if ((anteBet < 5) || (anteBet > 25)) {
					messageBoard.setText("Player two's ante wager must be between 5 and 25");
				} else {
					if (!(player2PlayWager.getText().equals(String.valueOf(anteBet)))) {
						messageBoard.setText("Player one's play wager must be equal to ante wager");
					} else {
						player2Done = true; //player 1 has taken the turn

						if (player1Done) {
							showDealersCards(); //since both players are done

							//evaluate play and pair plus bet
							int player2TypeHand = ThreeCardLogic.evalHand(playerTwo.hand);
							int player2PairPlusWon = ThreeCardLogic.evalPPWinnings(playerTwo.hand, playerTwo.pairPlusBet);
							int player2WinningPerson = ThreeCardLogic.compareHands(theDealer.dealersHand, playerTwo.hand);

							String player2TypeHandMessage;
							if (player2TypeHand == 0) {
								player2TypeHandMessage = "Player 2 has a high card";
							} else if (player2TypeHand == 1) {
								player2TypeHandMessage = "Player 2 has a straight flush";
							}
							else if (player2TypeHand == 2) {
								player2TypeHandMessage = "Player 2 has three of a kind";
							}
							else if (player2TypeHand == 3) {
								player2TypeHandMessage = "Player 2 has a straight";
							}
							else if (player2TypeHand == 4) {
								player2TypeHandMessage = "Player 2 has a flush";
							} else {
								//5
								player2TypeHandMessage = "Player 2 has a pair";
							}

							String player2PairPlusWonMessage = "Player two pair plus winning is " + player2PairPlusWon;
							String player2Player1WinningPersonMessage;

							if (player2WinningPerson == 0) {
								player2Player1WinningPersonMessage = "Tie between Dealer and Player two.";
							} else if (player2WinningPerson == 1) {
								player2Player1WinningPersonMessage = "Player two lost to the dealer";
							} else {
								player2Player1WinningPersonMessage = "The Dealer lost to player two";
							}

							if (player1Playing) {
								//show both player messages because both players are done
								int player1TypeHand = ThreeCardLogic.evalHand(playerOne.hand);
								int player1PairPlusWon = ThreeCardLogic.evalPPWinnings(playerOne.hand, playerOne.pairPlusBet);
								int player1WinningPerson = ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand);

								String player1TypeHandMessage;
								if (player1TypeHand == 0) {
									player1TypeHandMessage = "Player 1 has a high card";
								} else if (player1TypeHand == 1) {
									player1TypeHandMessage = "Player 1 has a straight flush";
								}
								else if (player1TypeHand == 2) {
									player1TypeHandMessage = "Player 1 has three of a kind";
								}
								else if (player1TypeHand == 3) {
									player1TypeHandMessage = "Player 1 has a straight";
								}
								else if (player1TypeHand == 4) {
									player1TypeHandMessage = "Player 1 has a flush";
								} else {
									//5
									player1TypeHandMessage = "Player 1 has a pair";
								}

								String player1PairPlusWonMessage = "Player one pair plus winning is " + player1PairPlusWon;
								String player1Player1WinningPersonMessage;

								if (player1WinningPerson == 0) {
									player1Player1WinningPersonMessage = "Tie between Dealer and Player One.";
								} else if (player1WinningPerson == 1) {
									player1Player1WinningPersonMessage = "Player One lost to the dealer";
								} else {
									player1Player1WinningPersonMessage = "The Dealer lost to player one";
								}

								show3Message(
										player1TypeHandMessage + " " + player2TypeHandMessage,
										player1PairPlusWonMessage + " " + player2PairPlusWonMessage,
										player1Player1WinningPersonMessage + " " + player2Player1WinningPersonMessage,
										threeMessageWait);
							}
							else {
								//show only player 2 message
								show3Message(
										player2TypeHandMessage,
										player2PairPlusWonMessage,
										player2Player1WinningPersonMessage,
										threeMessageWait);
							}

						} else {
							//wait for player 1
							//player 1 must play now
							show1Message("Waiting for player 1 to take turn");
							player1Title.setDisable(true);
						}
					}
				}
			}
		});

		player2FoldButton.setOnAction(actionEvent -> {
			int pairPlusBet = 0;
			if (!(player2PairPlusWager.getText().equals(""))) {
				pairPlusBet = Integer.parseInt(player2PairPlusWager.getText());
			}

			if (pairPlusBet <= 0) {
				messageBoard.setText("Player two's pair plus wager must be positive");
			} else {
				if ((pairPlusBet < 5) || (pairPlusBet > 25)) {
					messageBoard.setText("Player two's pair plus wager must be between 5 and 25");
				} else {
					player2Done = true; //player 2 has taken the turn
					player2PairPlusWager.setText(String.valueOf(pairPlusBet)); //put ante bet in play wager

					player2PlayWager.clear();
					player2AnteBets.clear();

					if (player1Done) {
						showDealersCards(); //since both players are done

						//evaluate pair plus bet only
						int player2TypeHand = ThreeCardLogic.evalHand(playerTwo.hand);
						int player2PairPlusWon = ThreeCardLogic.evalPPWinnings(playerTwo.hand, playerTwo.pairPlusBet);

						String player2TypeHandMessage;
						if (player2TypeHand == 0) {
							player2TypeHandMessage = "Player 2 has a high card";
						} else if (player2TypeHand == 1) {
							player2TypeHandMessage = "Player 2 has a straight flush";
						}
						else if (player2TypeHand == 2) {
							player2TypeHandMessage = "Player 2 has three of a kind";
						}
						else if (player2TypeHand == 3) {
							player2TypeHandMessage = "Player 2 has a straight";
						}
						else if (player2TypeHand == 4) {
							player2TypeHandMessage = "Player 2 has a flush";
						} else {
							//5
							player2TypeHandMessage = "Player 2 has a pair";
						}

						String player2PairPlusWonMessage = "Player two pair plus winning is " + player2PairPlusWon;


						if (player1Playing) {
							//show both player messages because both players are done
							int player1TypeHand = ThreeCardLogic.evalHand(playerOne.hand);
							int player1PairPlusWon = ThreeCardLogic.evalPPWinnings(playerOne.hand, playerOne.pairPlusBet);

							String player1TypeHandMessage;
							if (player1TypeHand == 0) {
								player1TypeHandMessage = "Player 1 has a high card";
							} else if (player1TypeHand == 1) {
								player1TypeHandMessage = "Player 1 has a straight flush";
							}
							else if (player1TypeHand == 2) {
								player1TypeHandMessage = "Player 1 has three of a kind";
							}
							else if (player1TypeHand == 3) {
								player1TypeHandMessage = "Player 1 has a straight";
							}
							else if (player1TypeHand == 4) {
								player1TypeHandMessage = "Player 1 has a flush";
							} else {
								//5
								player1TypeHandMessage = "Player 1 has a pair";
							}

							String player1PairPlusWonMessage = "Player one pair plus winning is " + player1PairPlusWon;

							show2Message(
									player1TypeHandMessage + " " + player2TypeHandMessage,
									player1PairPlusWonMessage + " " + player2PairPlusWonMessage,
									twoMessageWait);
						} else {
							//show only player 1 message
							show2Message(
									player2TypeHandMessage,
									player2PairPlusWonMessage,
									twoMessageWait);
						}
					} else {
						//wait for player 1
						//player 1 must play now
						show1Message("Waiting for player 1 to take turn");
						player1Title.setDisable(true);
					}
				}
			}
		});
	}

	public void show1Message(String message1) {
		messageBoard.setText(message1);
	}

	public void show2Message(String message1, String message2, double waitTime) {
		messageBoard.setText(message1);

		PauseTransition wait = new PauseTransition(Duration.seconds(waitTime));
		wait.setOnFinished((e) -> {
			messageBoard.setText(message2);
		});
		wait.play();
	}

	public void show3Message(String message1, String message2, String message3, double waitTime) {
		messageBoard.setText(message1);

		PauseTransition wait = new PauseTransition(Duration.seconds(waitTime));
		wait.setOnFinished((e) -> {
			messageBoard.setText(message2);

			PauseTransition wait2 = new PauseTransition(Duration.seconds(waitTime));
			wait2.setOnFinished((e2) -> {
				messageBoard.setText(message3);
			});
			wait2.play();
		});
		wait.play();
	}

	public void showAllCards() {
		dealerCard1ImageView.setImage(new Image(theDealer.dealersHand.get(0).value + String.valueOf(theDealer.dealersHand.get(0).suit)+".jpg"));
		dealerCard2ImageView.setImage(new Image(theDealer.dealersHand.get(1).value + String.valueOf(theDealer.dealersHand.get(1).suit)+".jpg"));
		dealerCard3ImageView.setImage(new Image(theDealer.dealersHand.get(2).value + String.valueOf(theDealer.dealersHand.get(2).suit)+".jpg"));

		if (player1Playing) {
			player1Card1ImageView.setImage(new Image(playerOne.hand.get(0).value + String.valueOf(playerOne.hand.get(0).suit)+".jpg"));
			player1Card2ImageView.setImage(new Image(playerOne.hand.get(1).value + String.valueOf(playerOne.hand.get(1).suit)+".jpg"));
			player1Card3ImageView.setImage(new Image(playerOne.hand.get(2).value + String.valueOf(playerOne.hand.get(2).suit)+".jpg"));
		}

		if (player2Playing) {
			player2Card1ImageView.setImage(new Image(playerTwo.hand.get(0).value + String.valueOf(playerTwo.hand.get(0).suit)+".jpg"));
			player2Card2ImageView.setImage(new Image(playerTwo.hand.get(1).value + String.valueOf(playerTwo.hand.get(1).suit)+".jpg"));
			player2Card3ImageView.setImage(new Image(playerTwo.hand.get(2).value + String.valueOf(playerTwo.hand.get(2).suit)+".jpg"));
		}
	}

	public void showDealersCards() {
		dealerCard1ImageView.setImage(new Image(theDealer.dealersHand.get(0).value + String.valueOf(theDealer.dealersHand.get(0).suit)+".jpg"));
		dealerCard2ImageView.setImage(new Image(theDealer.dealersHand.get(1).value + String.valueOf(theDealer.dealersHand.get(1).suit)+".jpg"));
		dealerCard3ImageView.setImage(new Image(theDealer.dealersHand.get(2).value + String.valueOf(theDealer.dealersHand.get(2).suit)+".jpg"));
	}

	public void showPlayer1Cards() {
		if (player1Playing) {
			player1Card1ImageView.setImage(new Image(playerOne.hand.get(0).value + String.valueOf(playerOne.hand.get(0).suit)+".jpg"));
			player1Card2ImageView.setImage(new Image(playerOne.hand.get(1).value + String.valueOf(playerOne.hand.get(1).suit)+".jpg"));
			player1Card3ImageView.setImage(new Image(playerOne.hand.get(2).value + String.valueOf(playerOne.hand.get(2).suit)+".jpg"));
		}
	}

	public void showPlayer2Cards() {
		if (player2Playing) {
			player2Card1ImageView.setImage(new Image(playerTwo.hand.get(0).value + String.valueOf(playerTwo.hand.get(0).suit)+".jpg"));
			player2Card2ImageView.setImage(new Image(playerTwo.hand.get(1).value + String.valueOf(playerTwo.hand.get(1).suit)+".jpg"));
			player2Card3ImageView.setImage(new Image(playerTwo.hand.get(2).value + String.valueOf(playerTwo.hand.get(2).suit)+".jpg"));
		}
	}

	public void updatePlayerStatus() {
		if (player1Playing) {
			player1Status.setText("Playing");
			player1PlayButton.setDisable(false);
			player1FoldButton.setDisable(false);
			player1Status.setTooltip(new Tooltip("Player one is playing the game"));
		} else {
			player1Status.setText("Not Playing");
			player1PlayButton.setDisable(true);
			player1FoldButton.setDisable(true);
			player1Status.setTooltip(new Tooltip("Player one is not playing the game"));

			player1Card1ImageView.setImage(new Image("blue_back.jpg"));
			player1Card2ImageView.setImage(new Image("blue_back.jpg"));
			player1Card3ImageView.setImage(new Image("blue_back.jpg"));
		}

		if (player2Playing) {
			player2Status.setText("Playing");
			player2PlayButton.setDisable(false);
			player2FoldButton.setDisable(false);
			player2Status.setTooltip(new Tooltip("Player two is playing the game"));
		} else {
			player2Status.setText("Not Playing");
			player2PlayButton.setDisable(true);
			player2FoldButton.setDisable(true);
			player2Status.setTooltip(new Tooltip("Player two is not playing the game"));

			player2Card1ImageView.setImage(new Image("blue_back.jpg"));
			player2Card2ImageView.setImage(new Image("blue_back.jpg"));
			player2Card3ImageView.setImage(new Image("blue_back.jpg"));
		}
	}

	public void initiateElements() {
		//text field
		dealerTitle = new TextField("Dealer");
		dealerTitle.setEditable(false);
		dealerTitle.setMaxSize(100, 100);
		dealerTitle.setFont(new Font(15));
		dealerTitle.setAlignment(Pos.CENTER);
		dealerTitle.autosize();
		dealerTitle.setTooltip(new Tooltip("Hi!! I am the dealer"));

		player1Title = new Button("Player One");
		player1Title.setMaxSize(100, 100);
		player1Title.setFont(new Font(15));
		player1Title.setAlignment(Pos.CENTER);
		player1Title.autosize();
		player1Title.setTooltip(new Tooltip("Click to enable/disable player one"));

		player1Status = new TextField("Playing");
		player1Status.setEditable(false);
		player1Status.setMaxSize(100, 100);
		player1Status.setFont(new Font(15));
		player1Status.setAlignment(Pos.CENTER);
		player1Status.autosize();
		player1Status.setTooltip(new Tooltip("Player one is playing the game"));

		player1PlayWager = new TextField("");
		player1PlayWager.setPromptText("Play Wager");
		player1PlayWager.setEditable(true);
		player1PlayWager.setMaxSize(100, 100);
		player1PlayWager.setFont(new Font(15));
		player1PlayWager.setAlignment(Pos.CENTER);
		player1PlayWager.autosize();
		player1PlayWager.setTooltip(new Tooltip("Play wager will be placed for you automatically if you choose to play"));
		player1PlayWager.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

		player1PairPlusWager = new TextField("");
		player1PairPlusWager.setPromptText("Pair Plus");
		player1PairPlusWager.setEditable(true);
		player1PairPlusWager.setMaxSize(100, 100);
		player1PairPlusWager.setFont(new Font(15));
		player1PairPlusWager.setAlignment(Pos.CENTER);
		player1PairPlusWager.autosize();
		player1PairPlusWager.setTooltip(new Tooltip("Place your pair plus wager here"));
		player1PairPlusWager.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

		player1AnteBets = new TextField("");
		player1AnteBets.setPromptText("Ante Bets");
		player1AnteBets.setEditable(true);
		player1AnteBets.setMaxSize(100, 100);
		player1AnteBets.setFont(new Font(15));
		player1AnteBets.setAlignment(Pos.CENTER);
		player1AnteBets.autosize();
		player1AnteBets.setTooltip(new Tooltip("Place your ante wager here"));
		player1AnteBets.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

		player1Winnings = new TextField("Wins: $" + playerOne.totalWinnings);
		player1Winnings.setEditable(false);
		player1Winnings.setMaxSize(100, 100);
		player1Winnings.setFont(new Font(15));
		player1Winnings.setAlignment(Pos.CENTER);
		player1Winnings.autosize();
		player1Winnings.setTooltip(new Tooltip("Total winnings for player one"));

		messageBoard = new TextField("Hover over text fields or buttons");
		messageBoard.setEditable(false);
//		player1Message.setMaxSize(340, 100);
		messageBoard.setFont(new Font(15));
		messageBoard.setAlignment(Pos.CENTER);
		messageBoard.autosize();
		messageBoard.setTooltip(new Tooltip("This is a message board"));

		player2Title = new Button("Player Two");
		player2Title.setMaxSize(100, 100);
		player2Title.setFont(new Font(15));
		player2Title.setAlignment(Pos.CENTER);
		player2Title.autosize();
		player2Title.setTooltip(new Tooltip("Click to enable/disable player two"));

		player2Status = new TextField("Playing");
		player2Status.setEditable(false);
		player2Status.setMaxSize(100, 100);
		player2Status.setFont(new Font(15));
		player2Status.setAlignment(Pos.CENTER);
		player2Status.autosize();
		player2Status.setTooltip(new Tooltip("Player two is playing the game"));

		player2PlayWager = new TextField("");
		player2PlayWager.setPromptText("Play Wager");
		player2PlayWager.setEditable(true);
		player2PlayWager.setMaxSize(100, 100);
		player2PlayWager.setFont(new Font(15));
		player2PlayWager.setAlignment(Pos.CENTER);
		player2PlayWager.autosize();
		player2PlayWager.setTooltip(new Tooltip("Play wager will be placed for you automatically if you choose to play"));
		player1PlayWager.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

		player2PairPlusWager = new TextField("");
		player2PairPlusWager.setPromptText("Pair Plus");
		player2PairPlusWager.setEditable(true);
		player2PairPlusWager.setMaxSize(100, 100);
		player2PairPlusWager.setFont(new Font(15));
		player2PairPlusWager.setAlignment(Pos.CENTER);
		player2PairPlusWager.autosize();
		player2PairPlusWager.setTooltip(new Tooltip("Place your pair plus wager here"));
		player1PlayWager.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

		player2AnteBets = new TextField("");
		player2AnteBets.setPromptText("Ante Bets");
		player2AnteBets.setEditable(true);
		player2AnteBets.setMaxSize(100, 100);
		player2AnteBets.setFont(new Font(15));
		player2AnteBets.setAlignment(Pos.CENTER);
		player2AnteBets.autosize();
		player2AnteBets.setTooltip(new Tooltip("Place your ante wager here"));
		player2AnteBets.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

		player2Winnings = new TextField("Wins: $" + playerTwo.totalWinnings);
		player2Winnings.setEditable(false);
		player2Winnings.setMaxSize(100, 100);
		player2Winnings.setFont(new Font(15));
		player2Winnings.setAlignment(Pos.CENTER);
		player2Winnings.autosize();
		player2Winnings.setTooltip(new Tooltip("Total winnings for player one"));

		//button
		dealerDealButton = new Button("Deal");
		dealerDealButton.setAlignment(Pos.CENTER);
		dealerDealButton.setFont(new Font(15));
		dealerDealButton.autosize();
		dealerDealButton.setTooltip(new Tooltip("Click to deal cards"));

		player1PlayButton = new Button("Play");
		player1PlayButton.setAlignment(Pos.CENTER);
		player1PlayButton.setFont(new Font(15));
		player1PlayButton.autosize();
		player1PlayButton.setTooltip(new Tooltip("Click to play your cards. Your cards will compared against dealer's cards"));

		player1FoldButton = new Button("Fold");
		player1FoldButton.setAlignment(Pos.CENTER);
		player1FoldButton.setFont(new Font(15));
		player1FoldButton.autosize();
		player1FoldButton.setTooltip(new Tooltip("Click to fold your cards. Your cards will compared with three card poker hands"));

		player2PlayButton = new Button("Play");
		player2PlayButton.setAlignment(Pos.CENTER);
		player2PlayButton.setFont(new Font(15));
		player2PlayButton.autosize();
		player2PlayButton.setTooltip(new Tooltip("Click to play your cards. Your cards will compared against dealer's cards"));

		player2FoldButton = new Button("Fold");
		player2FoldButton.setAlignment(Pos.CENTER);
		player2FoldButton.setFont(new Font(15));
		player2FoldButton.autosize();
		player2FoldButton.setTooltip(new Tooltip("Click to fold your cards. Your cards will compared with three card poker hands"));
	}

	public void setBackground() {
		if (showScene1) {
			//setting the background
			backgroundImage = new Image("Poker_table_background.jpg");
		} else {
			//setting the background
			backgroundImage = new Image("Poker_table_background2.png");
		}

		bgSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
		bgImage =
				new BackgroundImage(
						backgroundImage,
						BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT,
						BackgroundPosition.CENTER,
						bgSize);
		background = new Background(bgImage);
		pane.setBackground(background);

	}

	public void setStyle() {
		if (showScene1) {
			player1Title.setStyle("-fx-background-color: #ecff75");
			player2Title.setStyle("-fx-background-color: #ecff75");
			dealerDealButton.setStyle("-fx-background-color: #ecff75");
			player1PlayButton.setStyle("-fx-background-color: #ecff75");
			player1FoldButton.setStyle("-fx-background-color: #ecff75");
			player2PlayButton.setStyle("-fx-background-color: #ecff75");
			player2FoldButton.setStyle("-fx-background-color: #ecff75");

			dealerTitle.setStyle("-fx-background-color: #83c7ff");
			messageBoard.setStyle("-fx-background-color: #ff3f44");
			player1Status.setStyle("-fx-background-color: #83c7ff");
			player2Status.setStyle("-fx-background-color: #83c7ff");
			player1Winnings.setStyle("-fx-background-color: #83c7ff");
			player2Winnings.setStyle("-fx-background-color: #83c7ff");
		} else {
			player1Title.setStyle("-fx-background-color: #ff78a0");
			player2Title.setStyle("-fx-background-color: #ff78a0");
			dealerDealButton.setStyle("-fx-background-color: #ff78a0");
			player1PlayButton.setStyle("-fx-background-color: #ff78a0");
			player1FoldButton.setStyle("-fx-background-color: #ff78a0");
			player2PlayButton.setStyle("-fx-background-color: #ff78a0");
			player2FoldButton.setStyle("-fx-background-color: #ff78a0");

			dealerTitle.setStyle("-fx-background-color: #f2aaff");
			messageBoard.setStyle("-fx-background-color: #02c0ff");
			player1Status.setStyle("-fx-background-color: #f2aaff");
			player2Status.setStyle("-fx-background-color: #f2aaff");
			player1Winnings.setStyle("-fx-background-color: #f2aaff");
			player2Winnings.setStyle("-fx-background-color: #f2aaff");
		}
	}

	public Scene startingScene() {
		pane = new BorderPane();
		initiateElements();
		setStyle();
		setBackground();

		//dealer card 1
		dealerCard1Image = new Image("blue_back.jpg");
		dealerCard1ImageView = new ImageView();
		dealerCard1ImageView.setImage(dealerCard1Image);
		dealerCard1ImageView.setPreserveRatio(true);
		dealerCard1ImageView.setFitWidth(100);

		//dealer card 2
		dealerCard2Image = new Image("blue_back.jpg");
		dealerCard2ImageView = new ImageView();
		dealerCard2ImageView.setImage(dealerCard2Image);
		dealerCard2ImageView.setPreserveRatio(true);
		dealerCard2ImageView.setFitWidth(100);

		//dealer card 3
		dealerCard3Image = new Image("blue_back.jpg");
		dealerCard3ImageView = new ImageView();
		dealerCard3ImageView.setImage(dealerCard3Image);
		dealerCard3ImageView.setPreserveRatio(true);
		dealerCard3ImageView.setFitWidth(100);

		//player 1 Card 1
		player1Card1Image = new Image("blue_back.jpg");
		player1Card1ImageView = new ImageView();
		player1Card1ImageView.setImage(player1Card1Image);
		player1Card1ImageView.setPreserveRatio(true);
		player1Card1ImageView.setFitWidth(100);

		//player 1 Card 2
		player1Card2Image = new Image("blue_back.jpg");
		player1Card2ImageView = new ImageView();
		player1Card2ImageView.setImage(player1Card2Image);
		player1Card2ImageView.setPreserveRatio(true);
		player1Card2ImageView.setFitWidth(100);

		//player 1 Card 3
		player1Card3Image = new Image("blue_back.jpg");
		player1Card3ImageView = new ImageView();
		player1Card3ImageView.setImage(player1Card3Image);
		player1Card3ImageView.setPreserveRatio(true);
		player1Card3ImageView.setFitWidth(100);

		//player 1 Card 1
		player2Card1Image = new Image("blue_back.jpg");
		player2Card1ImageView = new ImageView();
		player2Card1ImageView.setImage(player2Card1Image);
		player2Card1ImageView.setPreserveRatio(true);
		player2Card1ImageView.setFitWidth(100);

		//player 1 Card 2
		player2Card2Image = new Image("blue_back.jpg");
		player2Card2ImageView = new ImageView();
		player2Card2ImageView.setImage(player2Card2Image);
		player2Card2ImageView.setPreserveRatio(true);
		player2Card2ImageView.setFitWidth(100);

		//player 2 Card 3
		player2Card3Image = new Image("blue_back.jpg");
		player2Card3ImageView = new ImageView();
		player2Card3ImageView.setImage(player2Card3Image);
		player2Card3ImageView.setPreserveRatio(true);
		player2Card3ImageView.setFitWidth(100);


		int padding = 25;
		int spacing = 20;


		//Making dealer view
		HBox dealerCards = new HBox(spacing, dealerCard1ImageView, dealerCard2ImageView, dealerCard3ImageView);
		dealerCards.setAlignment(Pos.CENTER);

		VBox dealerView = new VBox(spacing, dealerTitle, dealerCards, dealerDealButton);
		dealerView.setAlignment(Pos.TOP_CENTER);
		dealerView.setPadding(new Insets(padding, padding, padding, padding));


		//making player 1 view
		HBox player1StatusView = new HBox(spacing, player1Title, player1Status, player1Winnings);
		player1StatusView.setAlignment(Pos.CENTER);

		HBox player1BetsView = new HBox(spacing, player1PlayWager, player1AnteBets, player1PairPlusWager);
		player1StatusView.setAlignment(Pos.CENTER);

		HBox player1Cards = new HBox(spacing, player1Card1ImageView, player1Card2ImageView, player1Card3ImageView);
		player1Cards.setAlignment(Pos.CENTER);

		HBox player1Buttons = new HBox(spacing, player1PlayButton, player1FoldButton);
		player1Buttons.setAlignment(Pos.CENTER);

		VBox player1View = new VBox(spacing, player1StatusView, player1BetsView, player1Cards, player1Buttons);
		player1View.setAlignment(Pos.CENTER);
		player1View.setPadding(new Insets(padding, padding, padding, padding));


		//making player 2 view
		HBox player2StatusView = new HBox(spacing, player2Title, player2Status, player2Winnings);
		player2StatusView.setAlignment(Pos.CENTER);

		HBox player2BetsView = new HBox(spacing, player2PlayWager, player2AnteBets, player2PairPlusWager);
		player2StatusView.setAlignment(Pos.CENTER);

		HBox player2Cards = new HBox(spacing, player2Card1ImageView, player2Card2ImageView, player2Card3ImageView);
		player2Cards.setAlignment(Pos.CENTER);

		HBox player2Buttons = new HBox(spacing, player2PlayButton, player2FoldButton);
		player2Buttons.setAlignment(Pos.CENTER);

		VBox player2View = new VBox(spacing, player2StatusView, player2BetsView, player2Cards, player2Buttons);
		player2View.setAlignment(Pos.CENTER);
		player2View.setPadding(new Insets(padding, padding, padding, padding));


		HBox bothPlayerView = new HBox(player1View, player2View);
		bothPlayerView.setAlignment(Pos.CENTER);

		VBox messageAndPlayerView = new VBox(spacing, messageBoard, bothPlayerView);

		pane.setTop(menuBar);
		pane.setCenter(dealerView);
		pane.setBottom(messageAndPlayerView);
		return new Scene (pane, 780, 750);
	}
}
