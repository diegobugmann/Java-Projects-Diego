package Lottery.Model;

import java.util.Random;

import javafx.beans.property.SimpleStringProperty;

public class RegularNumber {
	
	private final static int MAX_VALUE = 42;
	private SimpleStringProperty asString = new SimpleStringProperty();
	private int value;
	private Random rand = new Random();
	
	public RegularNumber(int value) {
		if (value > 0 && value <= MAX_VALUE)
			this.value = value;
		asString.setValue(value+"");
	}
	
	public RegularNumber() {
		this.value = rand.nextInt(MAX_VALUE)+1;
		asString.setValue(value+"");
	}
	
	//NEEDED?
	public void setNumber(int value) {
		if (value > 0 && value <= MAX_VALUE)
			this.value = value;
		asString.setValue(value+"");
	}
	
	public SimpleStringProperty getValueProperty() {
		return this.asString;
	}
	
	public int getValue() {
		return this.value;
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
