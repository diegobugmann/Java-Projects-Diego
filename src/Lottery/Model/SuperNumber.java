package Lottery.Model;

import java.util.Random;

public class SuperNumber {
	
	private final int MAX_VALUE = 6;
	private int value;
	private Random rand = new Random();
	
	public SuperNumber(int value) {
		if (value > 0 && value <= MAX_VALUE)
			this.value = value;
	}
	
	public SuperNumber() {
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
