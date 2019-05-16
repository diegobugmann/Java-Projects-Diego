package Lottery.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Combination {
	
	private ArrayList<RegularNumber> numbers = new ArrayList<>();
	private SuperNumber superNumber;
	private CombinationType combinationType;
	
	//generate a random Combination of 6 Numbers and 1 SuperNumber
	public Combination() {
		for (int i = 0; i < 6; i++) {
			numbers.add(new RegularNumber());
			for (int j = 0; j < i; j++) { //make sure that every number is unique
				while (numbers.get(j).equals(numbers.get(i))) {
					numbers.remove(i);
					numbers.add(new RegularNumber());
				}
			}
		}
		Collections.sort(numbers);  //brings the numbers in order
		this.superNumber = new SuperNumber();
		this.combinationType = null;
	}
	
	public RegularNumber getNumber(int i) {
		return numbers.get(i);
	}
	
	public ArrayList<RegularNumber> getNumbers() {
		return this.numbers;
	}
	
	//needed to evaluate the tips
	public ArrayList<Integer> getNumbersAsInt() {
		return (ArrayList<Integer>) this.numbers.stream().map(c -> c.getValue()).collect(Collectors.toList());
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
	
	public void sortNumbers() {
		Collections.sort(numbers);
	}
	
	public CombinationType getCombinationType() {
		return this.combinationType;
	}
	
	public void setCombinationType(CombinationType combinationType) {
		this.combinationType = combinationType;
	}
}