package Lottery.Model;

import java.util.Random;

public class RegularNumber {
	
	private final int MAX_VALUE = 42;
	private int value;
	private Random rand = new Random();
	
	public RegularNumber(int value) {
		if (value > 0 && value <= MAX_VALUE)
			this.value = value;
	}
	
	public RegularNumber() {
		this.value = rand.nextInt(MAX_VALUE)+1;
	}
	
	//NEEDED?
	public void setNumber(int value) {
		if (value > 0 && value <= MAX_VALUE)
			this.value = value;
	}
	
	private int getValue() {
		return this.value;
	}

}
