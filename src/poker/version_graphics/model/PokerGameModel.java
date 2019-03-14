package poker.version_graphics.model;

import java.util.ArrayList;

import poker.version_graphics.PokerGame;

public class PokerGameModel {
	private final ArrayList<Player> players = new ArrayList<>();
	private DeckOfCards deck;
	
	public PokerGameModel() {
		for (int i = 0; i < PokerGame.numPlayers; i++) {
			players.add(new Player("Player " + (i+1)));
		}
		deck = new DeckOfCards();
	}
	
	public Player getPlayer(int i) {
		return players.get(i);
	}
	
	public DeckOfCards getDeck() {
		return deck;
	}
	
	//returns the winner, if it happens to be a tie it returns null
    public Player evaluateWinner() {
    	Player winner = players.get(0); //assuming that player 1 will win 
    	for (int f = 1; f < players.size(); f++) { //iterating through all players
       		if (winner.compareTo(players.get(f)) < 0)
       			winner = players.get(f);
       		else if (winner.compareTo(players.get(f)) == 0) { //search the winner if they have the same hand
       			
       			if (winner.getHandType() == HandType.HighCard || winner.getHandType() == HandType.Flush ||
       				winner.getHandType() == HandType.Straight || winner.getHandType() == HandType.StraightFlush)
       				winner = winner.evaluateHighCard(players.get(f));
       			
       			if (winner.getHandType() == HandType.OnePair || winner.getHandType() == HandType.TwoPair)
       				winner = winner.evaluateHighPair(players.get(f));		
       			
       			if (winner.getHandType() == HandType.ThreeOfAKind || winner.getHandType() == HandType.FourOfAKind ||
       				winner.getHandType() == HandType.FullHouse)
       				winner = winner.evaluateHighTrips(players.get(f));
       		}
    	}
    	return winner;
    }
	
	public void updatePlayerModel(int newPlayerNum) {
		players.clear(); //clear all players
		for (int i = 0; i < PokerGame.numPlayers; i++) { //add new amount
			players.add(new Player("Player " + (i+1)));
		}
	}
}
