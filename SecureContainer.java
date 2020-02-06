import java.util.*;

/***
However, they do remember a few key facts about the password:

It is a six-digit number.
The value is within the range given in your puzzle input.
Two adjacent digits are the same (like 22 in 122345).
Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).
Other than the range rule, the following are true:

111111 meets these criteria (double 11, never decreases).
223450 does not meet these criteria (decreasing pair of digits 50).
123789 does not meet these criteria (no double).
How many different passwords within the range given in your puzzle input meet these criteria?
Your puzzle input is 168630-718098
***/

class SecureContainer {

	private int passwordPossibilities=0;
	
	public static void main(String[] args) {
		SecureContainer p = new SecureContainer();
		//first number could be 168631 if fits the pattern
		int possibleP = 168631;
		//seach until the number hits the max
		
		while(possibleP < 718098) {
			//all numbers have to increase and have at least 2 that stay the same
			p.passwordValidation(possibleP);
			//increase to see if next number fits pattern
			possibleP++;
		}
		//p.passwordValidation(112233);
		//p.passwordValidation(123444);
		//p.passwordValidation(111122);
		p.printPossibilities();
		
	}

	//Validates password
	public void passwordValidation(int possible) {
		//convert to string to compare
		String possibleS = Integer.toString(possible);
		int doubles = 0;
		//determine it is a 6 digit number
		if(possibleS.length() != 6) { return ; }

		//go through string and compare every value to ensure that the value is greater than or equal to the previous
		//stop when 1 is reached
		for(int i=possibleS.length()-1; i > 0; i--) {
			if(possibleS.charAt(i) > possibleS.charAt(i-1)) { continue; }
			//increment doubles to see if the double criteria is met
			else if(possibleS.charAt(i) == possibleS.charAt(i-1)) { doubles++; }
			else { return ; }
		}
		//There was only one double found, not part of a larger group
		if(doubles == 1) { passwordPossibilities++; }
		else if(doubles > 1) { passwordValidation(possibleS); }
		else { return ; }
	}

	//Validates the String password based on the amount of values grouped ( part 2 of Advent of Code )
	public void passwordValidation(String possibleS) {

		HashMap<Character, ArrayList<Integer>> doubleIndices = new HashMap<Character, ArrayList<Integer>>();
		for(int i=0; i < possibleS.length()-1; i++) {
			if(possibleS.charAt(i) == possibleS.charAt(i+1)) {
				if(!doubleIndices.containsKey(possibleS.charAt(i))) {
					doubleIndices.put(possibleS.charAt(i), new ArrayList());
					doubleIndices.get(possibleS.charAt(i)).add(i);
				}
				else {
					doubleIndices.get(possibleS.charAt(i)).add(i);
				}
			}
		}
		//if there are more than 2 indexes in the list, then it is part of a larger group (there will not be
		//a repition of numbers at another index outside of the group because that means the array decreased
		//in value)
		for(ArrayList<Integer> values : doubleIndices.values()) {
			if(values.size() < 2) {
				passwordPossibilities++;
				return ;
			}
		}
	}

	//Prints the password possibilities available
	public void printPossibilities() {
		System.out.println("Possible permutations: "+ passwordPossibilities);
	}
}