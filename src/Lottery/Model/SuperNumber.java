package Lottery.Model;

import java.util.Random;

import javafx.beans.property.SimpleStringProperty;

public class SuperNumber extends RegularNumber {
	
	private final static int MAX_VALUE = 6;
	
	public SuperNumber(int value) {
		if (value > 0 && value <= MAX_VALUE)
			this.value = value;
		asString.setValue(value+"");
	}
	
	public SuperNumber() {
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
	
	public boolean equals(SuperNumber otherNumber) {
		return this.value == otherNumber.value;
	}

}
