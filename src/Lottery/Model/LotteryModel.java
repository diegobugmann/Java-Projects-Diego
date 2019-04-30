package Lottery.Model;

public class LotteryModel {
	
	private TipList tipList;
	private Combination drawing;
	
	public LotteryModel() {
		this.tipList = new TipList();
	}
	
	public void drawNumbers() {
		this.drawing = new Combination();
	}
	
	public TipList getTipList() {
		return this.tipList;
	}

}
