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

}
