package Lottery.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TipList {
	
	private ObservableList<Combination> tips;
	
	public TipList() {
		this.tips = FXCollections.observableArrayList();
	}
	
	public void addTip(Combination tip) {
		this.tips.add(tip);
	}
	
	public void deleteTip(Combination tip) {
		this.tips.remove(tip);
	}
	
	public void clearTips() {
		this.tips.clear();
	}
	
	public ObservableList<Combination> getTips() {
		return this.tips;
	}
	
	public double getValueInCHF() {
		return tips.size()*2.5;
	}
	
	public String displayWins() {
		String winnings = "";
		for (int i = 0; i < tips.size(); i++) {
			if (tips.get(i).getCombinationType() != null)
				winnings += tips.get(i).getCombinationType()+" mit Tipp "+(i+1)+" gewonnen!\n";
		}
		return winnings;
	}
	
	public int getWinSum() {
		int totalWinnings = 0;
		for (Combination c : tips)
			if (c.getCombinationType() != null)
				totalWinnings += c.getCombinationType().getWin();
		return totalWinnings;
	}
}