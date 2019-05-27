package Lottery.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Combination {
	
	private static ArrayList<RegularNumber> allNumbers = new ArrayList<>(); //list containing 42 Numbers
	private ArrayList<RegularNumber> numbers = new ArrayList<>();
	private SuperNumber superNumber;
	private CombinationType combinationType;
	
	//generate a random Combination of 6 Numbers and 1 SuperNumber
	public Combination() {
		if (allNumbers.isEmpty()) //once add RegularNumbers from 1-42 to allNumbers
			for (int i = 1; i <= 42; i++)
				allNumbers.add(new RegularNumber(i));
		Collections.shuffle(allNumbers);
		for (int i = 0; i < 6; i++) //add the first 6 numbers of the shuffled list to numbers
			numbers.add(new RegularNumber(allNumbers.get(i).getValue()));
		Collections.sort(numbers); //bring the 6 RegularNumbers in order
		this.superNumber = new SuperNumber(); //add a random SuperNumber
	}
	
	public RegularNumber getNumber(int i) {
		return numbers.get(i);
	}
	
	public ArrayList<RegularNumber> getNumbers() {
		return this.numbers;
	}
	
	//returns a list with the values of the RegularNumbers
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