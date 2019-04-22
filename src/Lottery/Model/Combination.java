package Lottery.Model;

public class Combination {
	
	private RegularNumber[] numbers = new RegularNumber[6];
	private SuperNumber superNumber;
	private static int drawingCount = 0;
	
	public Combination(RegularNumber[] numbers, SuperNumber superNumber) {
		this.numbers = numbers;
		this.superNumber = superNumber;
	}
	
	public Combination() {
		for (int i = 0; i < 6; i++) {
			RegularNumber num = new RegularNumber();
			this.numbers[i] = num;
		}
		this.superNumber = new SuperNumber();
		this.drawingCount++;
	}
	
}
