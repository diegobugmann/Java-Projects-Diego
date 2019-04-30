package Lottery.Model;

public class Combination {
	
	private RegularNumber[] numbers = new RegularNumber[6];
	private SuperNumber superNumber;
	
	public Combination(RegularNumber[] numbers, SuperNumber superNumber) {
		this.numbers = numbers;
		this.superNumber = superNumber;
	}
	
	public Combination() {
		for (int i = 0; i < 6; i++) {
			numbers[i] = new RegularNumber();
			for (int j = 0; j < i; j++) { //sicherstellen, dass keine Zahl doppelt vorkommt
				while (numbers[j].getValue() == numbers[i].getValue())
					numbers[i] = new RegularNumber();
			}
		}
		this.superNumber = new SuperNumber();
	}
	
	public RegularNumber getNumber(int i) {
		return numbers[i];
	}
	
	public SuperNumber getSupNum() {
		return this.superNumber;
	}
	
	public boolean contains(String num) {
		for (RegularNumber n : numbers) {
			if (n.getValue() == Integer.parseInt(num))
				return true;
		}
		return false;
	}
	
}
