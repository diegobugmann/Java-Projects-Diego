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
			view.updatePlayerPane(); //updates the view AND the model (look at the method)
			shuffle(); //shuffle the cards
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
    }
    
    //Deal each player five cards, then evaluate the two hands, evaluate and show the winner
    private void deal() {
    	int cardsRequired = PokerGame.numPlayers * Player.HAND_SIZE;
    	DeckOfCards deck = model.getDeck();
    	if (cardsRequired <= deck.getCardsRemaining()) {
        	for (int i = 0; i < PokerGame.numPlayers; i++) {
        		Player p = model.getPlayer(i);
        		p.discardHand();
        		for (int j = 0; j < Player.HAND_SIZE; j++)
        			p.addCard(deck.dealCard()); //deal 5 cards
        		PlayerPane pp = view.getPlayerPane(i);
        		SequentialTransition animateCards = pp.updatePlayerDisplay(i);
        		if (i == PokerGame.numPlayers-1) { //true for the last player
        			String hand = p.evaluateHand().toString(); //evaluate hand (NEEDED)
        			Player winner = model.evaluateWinner(); //evaluate the winner out of all Players
        			animateCards.setOnFinished(( e -> {
        				pp.getLblEvaluation().setText(hand); //display the handType for the last Player (NEEDED)
        				view.setWinner(winner); //set the winner at the end of the last animation
        			}));
        		}
        		}
        	view.getShuffleButton().setDisable(false); //enable shuffle button when cards are dealt
   
    	} else {
            Alert alert = new Alert(AlertType.ERROR, "Not enough cards - shuffle first");
            alert.showAndWait();
    	}
    }
    
}
