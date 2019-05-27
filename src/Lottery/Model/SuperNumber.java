package Lottery.Model;

import java.util.Random;

public class SuperNumber extends RegularNumber {
	
	private final static int MAX_VALUE = 6;
	private Random rand = new Random();
	
	public SuperNumber() {
		super(0); //leaves value empty
		this.value = rand.nextInt(MAX_VALUE)+1;
		asString.setValue(value+"");
	}
	
	public static boolean isValid(String valueAsString) {
		try {
			int value = Integer.parseInt(valueAsString);
			return (value > 0 && value <= MAX_VALUE);
		} catch (NumberFormatException e) {
			return false;
		}
	}
}