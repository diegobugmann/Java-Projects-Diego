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

}
