package poker.version_graphics.model;

import java.util.ArrayList;
import java.util.Collections;

public enum HandType {
    HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush, RoyalFlush;
    
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
        if (isRoyalFlush(cards)) currentEval = RoyalFlush;
        
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
    	ArrayList<Card> cCards = (ArrayList<Card>) cards.clone();
    	Collections.sort(cCards); //sorting the cards considering the ordinal (ascending)
    	for (int i = 1; i < cCards.size(); i++) {
    		if (cCards.get(0).getRank().ordinal() != cCards.get(i).getRank().ordinal()-i) {
    			if (i == cCards.size()-1) { //true for the last card, to take A2345 into account
    				if (cCards.get(0).getRank().ordinal() == 0 && cCards.get(cCards.size()-1).getRank().ordinal() == 12)
    					return true; //makes sure A2345 is also considered a straight
    			}
    			return false; //returns false as soon as the ordinals are not following (except for the A2345 case)
    		}
    	}
    	return true;
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
    
    public static boolean isRoyalFlush(ArrayList<Card> cards) {
    	ArrayList<Card> cCards = (ArrayList<Card>) cards.clone();
    	Collections.sort(cCards);
        if (isStraightFlush(cards) && cCards.get(3).getRank().ordinal() == 11)
        	return true;
        return false;
    }
    
}
