package Lottery.Model;

import java.util.ArrayList;

import javafx.collections.ObservableList;

public class TipEvaluation {
	
	public static int[] counters = new int[8];
	
	public static void evaluateTips(ObservableList<Combination> tips, Combination drawing) {
		for (Combination c : tips) {
			c.setCombinationType(null); //Vor dem evaluieren den CombinationType resetten
			ArrayList<Integer> regNumList = c.getNumbersAsInt();
			regNumList.retainAll(drawing.getNumbersAsInt()); //remove all the not matching RegularNumbers from the list
			
			if (regNumList.size() >= 3) { //3 or more RegularNumbers match? A win for sure!
				boolean isSupNumGood = false;
				if (c.getSupNum().equals(drawing.getSupNum())) //is the superNumber the same?
					isSupNumGood = true;
				switch(regNumList.size()) {
					case 3: if (!isSupNumGood) {
								c.setCombinationType(CombinationType.Three);
								counters[0]++;
							} else {
								c.setCombinationType(CombinationType.ThreePlus);
								counters[1]++;
							}
							break;
					case 4: if (!isSupNumGood) {
								c.setCombinationType(CombinationType.Four);
								counters[2]++;
							}
							else {
								c.setCombinationType(CombinationType.FourPlus);
								counters[3]++;
							}
							break;
					case 5: if (!isSupNumGood) {
								c.setCombinationType(CombinationType.Five);
								counters[4]++;
							} else {
								c.setCombinationType(CombinationType.FivePlus);
								counters[5]++;
							}
							break;
					case 6: if (!isSupNumGood) {
								c.setCombinationType(CombinationType.Six);
								counters[6]++;
							} else {
								c.setCombinationType(CombinationType.SixPlus);
								counters[7]++;
							}
							break;
				}
			}
		}
		
		//return a list with all the winning Combinations
		//return winningTips;
	}

}
