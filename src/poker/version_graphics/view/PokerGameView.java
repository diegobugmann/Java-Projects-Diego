package poker.version_graphics.view;

import javafx.animation.SequentialTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.Player;
import poker.version_graphics.model.PokerGameModel;

public class PokerGameView {
	private GridPane players;
	private ControlArea controls;
	private MenuPane menu;
	
	private PokerGameModel model;
	
	public PokerGameView(Stage stage, PokerGameModel model) {
		this.model = model;
		
		// Create all of the player panes we need, and put them into an HBox
		players = new GridPane();
		for (int i = 0; i < PokerGame.numPlayers; i++) {
			PlayerPane pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
			players.add(pp, i, 0);
		}
		
		// Create the control area
		controls = new ControlArea();
		controls.linkDeck(model.getDeck()); // link DeckLabel to DeckOfCards in the logic
		
		//create the Menu
		menu = new MenuPane();
		
		// Put players and controls into a BorderPane
		BorderPane root = new BorderPane();
		root.setPrefHeight(625);
		root.setTop(menu);
		root.setCenter(players);
		root.setBottom(controls);
		
		// Disallow resizing - which is difficult to get right with images
		stage.setResizable(false);

        // Create the scene using our layout; then display it
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                getClass().getResource("poker.css").toExternalForm());
        stage.setTitle("Poker Miniproject");
        stage.setScene(scene);
        stage.show();		
	}
	
	public void updatePlayerPane() {
		players.getChildren().clear(); //delete all PlayerPanes
		int newPlayerNum = menu.getSelectedButton();
		PokerGame.setPlayerNum(newPlayerNum); //set new Player amount
		model.updatePlayerModel();
		for (int i = 0; i < newPlayerNum; i++) { //add the amount of Players wanted
			PlayerPane pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(i)); //link to player object in the logic
			if (i <= 1) players.add(pp, i, 0);
			else players.add(pp, i-2, 1);
		}
	}
	
	public PlayerPane getPlayerPane(int i) {
		return (PlayerPane) players.getChildren().get(i);
	}
	
	public Button getResetButton() {
		return this.menu.resetWinsBtn;
	}
	
	public Button getShuffleButton() {
		return controls.btnShuffle;
	}
	
	public Button getDealButton() {
		return controls.btnDeal;
	}
	
	public Label getWinnerLabel() {
		return controls.lblWinner;
	}
	
	public Button getSubmitButton() {
		return menu.submitButton;
	}
	
	public int getSelectedButton() {
		return menu.getSelectedButton();
	}
	
	public boolean getCheckBox() {
		return menu.autoShuffle.isSelected();
	}

	public void setWinner(Player winner) {
		SequentialTransition st = new SequentialTransition();
		for (int i = 0; i < PokerGame.numPlayers; i++) {
			if (winner == model.getPlayer(i))
				st.getChildren().add(this.getPlayerPane(i).updateWinLabel()); //updates the winLabel and adds its animation
		}
		
		Label lblWinner = controls.lblWinner;
		if (winner == null) lblWinner.setText("Winner: TIE");
		else lblWinner.setText("Winner: "+winner.getPlayerName()); //updates the winnerLabel
		st.getChildren().add(controls.winnerTransition); //adds the animation
		st.play(); //plays the animations in order
	}

}
