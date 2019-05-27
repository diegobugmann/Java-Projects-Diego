package Lottery.Model;

public class LotteryModel {
	
	private TipList tipList;
	private Combination drawing;
	public static final int MAX_TIPS = 200;
	public static int drawCount = 0;
	public static double totalCosts = 0;
	public static int tipCount = 0;
	public static int totalWins = 0;
	
	
	public LotteryModel() {
		this.tipList = new TipList();
	}
	
	public Combination drawNumbers() {
		this.drawing = new Combination();
		drawCount++;
		return this.drawing;
	}
	
	public void updateStatistics() {
		tipCount += tipList.getTips().size();
		totalCosts = tipCount*2.5;
		totalWins += tipList.getWinSum();
	}
	
	public TipList getTipList() {
		return this.tipList;
	}
	
	public Combination getDrawing() {
		return this.drawing;
	}
}