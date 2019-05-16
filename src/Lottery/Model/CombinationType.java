package Lottery.Model;

public enum CombinationType {
	Three("3+0 Richtige", 10),
	ThreePlus("3+1 Richtige", 25),
	Four("4+0 Richtige", 75),
	FourPlus("4+1 Richtige", 150),
	Five("5+0 Richtige", 1000),
	FivePlus("5+1 Richtige", 10000),
	Six("6+0 Richtige", 1000000),
	SixPlus("6+1 Richtige", 9999999);
	
	private String message;
	private int win;
	
	private CombinationType(String message, int win) {
		this.message = message;
		this.win = win;
	}
	
	public String toString() {
		return this.message+" = CHF "+win;
	}
	
	public int getWin() {
		return this.win;
	}
}