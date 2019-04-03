package poker.version_graphics.controller;

import javafx.animation.SequentialTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.DeckOfCards;
import poker.version_graphics.model.Player;
import poker.version_graphics.model.PokerGameModel;
import poker.version_graphics.view.PlayerPane;
import poker.version_graphics.view.PokerGameView;

public class PokerGameController {
	private PokerGameModel model;
	private PokerGameView view;
	
	public PokerGameController(PokerGameModel model, PokerGameView view) {
		this.model = model;
		this.view = view;
		
		view.getShuffleButton().setOnAction( e -> shuffle() );
		view.getDealButton().setOnAction( e -> deal() );
		view.getSubmitButton().setOnAction( e -> {
			view.updatePlayerPane(); //updates the view AND the model
			shuffle(); //shuffle the cards
			});
		view.getResetButton().setOnAction( e -> {
			for (int i = 0; i < PokerGame.numPlayers; i++)
				view.getPlayerPane(i).resetWins();
				});
	}

    //Remove all cards from players hands, and shuffle the deck
    private void shuffle() {
    	for (int i = 0; i < PokerGame.numPlayers; i++) {
    		Player p = model.getPlayer(i);
    		p.discardHand();
    		PlayerPane pp = view.getPlayerPane(i);
    		pp.updatePlayerDisplay(0);
    		view.getWinnerLabel().setText("Winner:");
    	}
    	model.getDeck().shuffle();
    	view.getShuffleButton().setDisable(true); //disable shuffle button when shuffled
    	view.getDealButton().setDisable(false); //make sure the deal button is enabled after shuffling
    }
    
    //Deal each player five cards, then evaluate the two hands, evaluate and show the winner
    private void deal() {
    	if (view.getCheckBox()) shuffle();  //shuffle if auto shuffle is enabled
    	int cardsRequired = PokerGame.numPlayers * Player.HAND_SIZE;
    	DeckOfCards deck = model.getDeck();
    	if (cardsRequired <= deck.getCardsRemaining()) {
        	view.getDealButton().setDisable(true); //prevent dealing while hand is running
        	view.getShuffleButton().setDisable(true); //prevent shuffling while hand is running
        	for (int i = 0; i < PokerGame.numPlayers; i++) {
        		Player p = model.getPlayer(i);
        		p.discardHand(); //clear the players current hand
        		for (int j = 0; j < Player.HAND_SIZE; j++)
        			p.addCard(deck.dealCard()); //deal 5 cards
        		PlayerPane pp = view.getPlayerPane(i);
        		SequentialTransition updateAnimation = pp.updatePlayerDisplay(i);
        		if (i == PokerGame.numPlayers-1) { //true for the last player
        			String hand = p.evaluateHand().toString(); //evaluate hand (NEEDED)
        			Player winner = model.evaluateWinner(); //evaluate the winner out of all Players
        			updateAnimation.setOnFinished(( e -> {
        				pp.getLblEvaluation().setText(hand); //display the handType for the last Player (NEEDED)
        				view.setWinner(winner); //set the winner at the end of the last animation
        				view.getDealButton().setDisable(false); //enable deal button at the end of the hand
        				view.getShuffleButton().setDisable(false); //enable shuffle button at the end of the hand
        			}));
        		}
        		}
   
    	} else {
            Alert alert = new Alert(AlertType.ERROR, "Not enough cards - shuffle first");
            alert.showAndWait();
            view.getDealButton().setDisable(true); // when error is shown, disable deal button
    	}
    }
}
