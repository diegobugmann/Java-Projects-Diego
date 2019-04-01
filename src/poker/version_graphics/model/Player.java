package poker.version_graphics.model;

import java.util.ArrayList;
import java.util.Collections;

public class Player implements Comparable<Player> {
    public static final int HAND_SIZE = 5;
    
    private final String playerName; // This is the ID
    private final ArrayList<Card> cards = new ArrayList<>();
    private HandType handType;
    private int winCount = 0;
    
    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
    
    public void addCard(Card card) {
        if (cards.size() < HAND_SIZE) cards.add(card);
    }
    
    public void discardHand() {
        cards.clear();
        handType = null;
    }
    
    public int getNumCards() {
        return cards.size();
    }
    
    public HandType getHandType() {
    	return this.handType;
    }
    
    //setter and getter for the winCount
    public int increaseWins() {
    	winCount++;
    	return this.winCount;
    }
    
    public void resetWins() {
    	this.winCount = 0;
    }
    
    /**
     * If the hand has not been evaluated, but does have all cards, then evaluate it.
     */
    public HandType evaluateHand() {
        if (handType == null && cards.size() == HAND_SIZE) {
            handType = HandType.evaluateHand(cards);
        }
        return handType;
    }

    /**
     * Hands are compared, based on the evaluation they have.
     */
    @Override
    public int compareTo(Player o) {
        return handType.compareTo(o.handType);
    }
    
    //return the Player with the better cards or null if it's a tie
    public Player evaluateHighCard(Player otherPlayer) {
    	Player winner = this; //assuming that the calling player will win
    	ArrayList<Card> cCards = (ArrayList<Card>) this.cards.clone();
		ArrayList<Card> cCards2 = (ArrayList<Card>) otherPlayer.cards.clone();
    	Collections.sort(cCards);
    	Collections.sort(cCards2);
    	for (int r = cCards.size()-1; r >= 0; r--) {
    		if (cCards.get(r).compareTo(cCards2.get(r)) == 0) {
    			cCards.remove(r); //remove the highest card if it is the same
    			cCards2.remove(r);
    			}
    		}
    	if (cCards.size() > 0) { //if cards are left, compare the highest one
    		if (cCards.get(cCards.size()-1).compareTo(cCards2.get(cCards2.size()-1)) < 0)
    			winner = otherPlayer;
    		}
    	if (cCards.size() == 0) //if all cards got removed, it is a tie
    		winner = null;
			
    	return winner;
    }
    
    //return the Player with the better pair(s) or null if it's a tie
    public Player evaluateHighPair(Player otherPlayer) {
    	Player winner = this; //assuming that the calling player will win
    	ArrayList<Card> cCards = (ArrayList<Card>) this.cards.clone();
		ArrayList<Card> cCards2 = (ArrayList<Card>) otherPlayer.cards.clone();
    	Collections.sort(cCards);
    	Collections.sort(cCards2);
    	Card pairedCard = null; Card pairedCard2 = null;
    	
    	for (int i = 0; i < cCards.size()-1; i++) {
        	if (cCards.get(i).compareTo(cCards.get(i+1)) == 0)
        		pairedCard = cCards.get(i); 
        }
    	for (int i = 0 ; i < cCards2.size()-1; i++) {
        	if (cCards2.get(i).compareTo(cCards2.get(i+1)) == 0)
        		pairedCard2 = cCards2.get(i);	
        }
        if (pairedCard.compareTo(pairedCard2) < 0) //if the otherPlayer has the higher pair, set him as winner
        	winner = otherPlayer;
        
        if (pairedCard.compareTo(pairedCard2) == 0) { //they have the same pair? possible!
        	
        	if (winner.handType == HandType.OnePair) //if they only have one pair, evaluate the highest card now
        		winner = winner.evaluateHighCard(otherPlayer);
        	
        	if (winner.handType == HandType.TwoPair) { //if they have two pairs, compare the lower pair now
        		for (int i = cCards.size()-1; i > 0; i--) { //that's why we search the other way around now
            		if (cCards.get(i).compareTo(cCards.get(i-1)) == 0)
            			pairedCard = cCards.get(i);
            	}
        		for (int i = cCards.size()-1; i > 0; i--) {
            		if (cCards2.get(i).compareTo(cCards2.get(i-1)) == 0)
            			pairedCard2 = cCards2.get(i);	
        		}
        		if (pairedCard.compareTo(pairedCard2) < 0) //if the otherPlayer has the higher 2nd pair, set him as winner
                	winner = otherPlayer;
        		
        		if (pairedCard.compareTo(pairedCard2) == 0) //they have the same pair again? possible!
        			winner = winner.evaluateHighCard(otherPlayer); //now we search the higher card which is left
        	}
        }
    	return winner;
    }
    
    //return the Player with higher triples
    public Player evaluateHighTrips(Player otherPlayer) {
    	Player winner = this; //assuming that the calling player will win
    	ArrayList<Card> cCards = (ArrayList<Card>) this.cards.clone();
		ArrayList<Card> cCards2 = (ArrayList<Card>) otherPlayer.cards.clone();
    	Collections.sort(cCards);
    	Collections.sort(cCards2);
    	//Wird anhand der mittleren Karte, welche zum Drilling/Vierling gehören muss, verglichen
    	if (cCards.get(2).compareTo(cCards2.get(2)) < 0)
    		winner = otherPlayer;
    	
    	return winner;
    }
}
