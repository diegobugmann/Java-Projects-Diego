package poker.version_graphics.model;

import java.util.ArrayList;
import java.util.Collections;

public enum HandType {
    HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush;
    
    /**
     * Determine the value of this hand. Note that this does not
     * account for any tie-breaking
     */
	
    public static HandType evaluateHand(ArrayList<Card> cards) {
        HandType currentEval = HighCard;
        
        if (isOnePair(cards)) currentEval = OnePair;
        if (isTwoPair(cards)) currentEval = TwoPair;
        if (isThreeOfAKind(cards)) currentEval = ThreeOfAKind;
        if (isStraight(cards)) currentEval = Straight;
        if (isFlush(cards)) currentEval = Flush;
        if (isFullHouse(cards)) currentEval = FullHouse;
        if (isFourOfAKind(cards)) currentEval = FourOfAKind;
        if (isStraightFlush(cards)) currentEval = StraightFlush;
        
        return currentEval;
    }
    
    public static boolean isOnePair(ArrayList<Card> cards) {
        boolean found = false;
        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i+1; j < cards.size() && !found; j++) {
                if (cards.get(i).getRank() == cards.get(j).getRank()) found = true;
            }
        }
        return found;
    }
    
    public static boolean isTwoPair(ArrayList<Card> cards) {
        // Clone the cards, because we will be altering the list
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();

        // Find the first pair; if found, remove the cards from the list
        boolean firstPairFound = false;
        for (int i = 0; i < clonedCards.size() - 1 && !firstPairFound; i++) {
            for (int j = i+1; j < clonedCards.size() && !firstPairFound; j++) {
                if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
                    firstPairFound = true;
                    clonedCards.remove(j);  // Remove the later card
                    clonedCards.remove(i);  // Before the earlier one
                }
            }
        }
        // If a first pair was found, see if there is a second pair
        return firstPairFound && isOnePair(clonedCards);
    }
    
    public static boolean isThreeOfAKind(ArrayList<Card> cards) {
    	for (int i = 0; i < cards.size() - 2; i++) {
        	for (int j = i+1; j < cards.size() - 1; j++) {
        		for (int o = j+1; o < cards.size(); o++) {
        			if (cards.get(i).getRank() == cards.get(j).getRank() &&
        				cards.get(i).getRank() == cards.get(o).getRank())
        				return true;
        		}
        	}
        }
        return false;
    }
    
    public static boolean isStraight(ArrayList<Card> cards) {
    	//ArrayList<Card> cCards = (ArrayList<Card>) cards.clone();
    	//Collections.sort(cCards);
    	//for (int i = 1; i < cCards.size(); i++) {
    		//if (cCards.get(0).getRank().ordinal() != cCards.get(i).getRank().ordinal()-i)
    			//return false;
    	//}
    	//return true;
    	
    	int minOrdinal = 12; //12 because it is the highest possible ordinal
    	for (Card c : cards)
    		minOrdinal = Math.min(c.getRank().ordinal(), minOrdinal); //sets the smallest ordinal
    	
    	//checks out whether the smallest ordinal has 4 following ones to make it a straight
    	for (Card a : cards) {
    		if (a.getRank().ordinal() == minOrdinal+1) {
    			for (Card b : cards) {
    				if (b.getRank().ordinal() == minOrdinal+2) {
    					for (Card c : cards) {
    						if (c.getRank().ordinal() == minOrdinal+3) {
    							for (Card d : cards) {
    								if (d.getRank().ordinal() == minOrdinal+4)
    									return true;
    								else if (minOrdinal == 0 && d.getRank().ordinal() == 12)
    									return true; //makes sure A2345 is also considered a straight
    							}
    						}
    					}
    				}
    			}
    		}
    	}
    	return false;
    }
    
    public static boolean isFlush(ArrayList<Card> cards) {
        int i = 0;
        for (int j= i+1; j < cards.size(); j++) {
        	if (cards.get(i).getSuit() != cards.get(j).getSuit())
        		return false;
        }
        return true;
    }
    
    public static boolean isFullHouse(ArrayList<Card> cards) {
    	for (int i = 0; i < cards.size() - 2; i++) {
        	for (int j = i+1; j < cards.size() - 1; j++) {
        		for (int o = j+1; o < cards.size(); o++) {
        			if (cards.get(i).getRank() == cards.get(j).getRank() &&
        				cards.get(i).getRank() == cards.get(o).getRank()) {
        				ArrayList<Card> cCards = (ArrayList<Card>) cards.clone(); //clone the cards
        				cCards.remove(o); cCards.remove(j); cCards.remove(i); //remove the triples
        				if (cCards.get(0).getRank() == cCards.get(1).getRank())
        					return true;
        			}
        		}
        	}
        }
        return false;
    }
    
    public static boolean isFourOfAKind(ArrayList<Card> cards) {
    	ArrayList<Card> cCards = (ArrayList<Card>) cards.clone();
    	Collections.sort(cCards); //sorting the cards considering the ordinal (ascending)
    	if (cCards.get(0).getRank() == cCards.get(3).getRank() || 
    		cCards.get(1).getRank() == cCards.get(4).getRank())
    		return true;
    	return false;
    }
    
    public static boolean isStraightFlush(ArrayList<Card> cards) {
        if (isStraight(cards) && isFlush(cards))
        	return true;
        return false;
    }
}
