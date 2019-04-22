package Lottery.Model;

public class LotteryModel {
	
	private TipList tips;
	private Combination drawing;
	
	public LotteryModel() {
		this.tips = new TipList();
	}
	
	public void drawNumbers() {
		this.drawing = new Combination();
	}

}
