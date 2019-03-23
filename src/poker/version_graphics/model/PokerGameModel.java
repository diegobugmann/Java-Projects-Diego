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
    	ArrayList<Player> winners = new ArrayList<Player>(); //list that contains the winner(s)
    	Player winner = players.get(0); //assuming that player 1 will win
    	winners.add(winner); //add him to the winner list
    	for (int f = 1; f < players.size(); f++) { //iterating through all players
       		if (winner.compareTo(players.get(f)) < 0) {
       			winner = players.get(f);
       			winners.clear();
       			winners.add(winner); //other Player has a better hand? set him as winner and add him to the list
       		}
       		
       		else if (winner.compareTo(players.get(f)) == 0) { //search the winner if they have the same hand
       			
       			if (winner.getHandType() == HandType.HighCard || winner.getHandType() == HandType.Flush ||
       				winner.getHandType() == HandType.Straight || winner.getHandType() == HandType.StraightFlush)
       				winner = winner.evaluateHighCard(players.get(f)); //evaluate the highest card
       			
       			if (winner.getHandType() == HandType.OnePair || winner.getHandType() == HandType.TwoPair)
       				winner = winner.evaluateHighPair(players.get(f)); //evaluate the highest pair
       			
       			if (winner.getHandType() == HandType.ThreeOfAKind || winner.getHandType() == HandType.FourOfAKind ||
       				winner.getHandType() == HandType.FullHouse)
       				winner = winner.evaluateHighTrips(players.get(f)); //evaluate the highest triples
       			
       			if (winner.getHandType() == HandType.RoyalFlush)
       				winner = null; //if 2 have a royal flush, it is a tie
       			
       			if (winner != null) { //add the new winner to the winners list 
       				winners.clear();
       				winners.add(winner);
       			} else { //true if it's a tie between the two players
       				winner = players.get(f); //set the winner to the player creating a tie
       				winners.add(winner); //add the winner to the winners list (without clearing!)
       			}
       		}
    	}
    	
    	if (winners.size() == 1) //return the winner
    		return winner;
    	else //more than one winner? return null (=TIE)
    		return null;
    }
	
	public void updatePlayerModel(int newPlayerNum) {
		players.clear(); //clear all players
		for (int i = 0; i < newPlayerNum; i++) { //add new amount
			players.add(new Player("Player " + (i+1)));
		}
	}
}
