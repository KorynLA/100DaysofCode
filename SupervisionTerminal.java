/***
Advent of code day 5
***/
import java.util.*;
import java.math.*;
import java.lang.*;
import java.io.*;

public class SupervisionTerminal {
	
	public static void main(String[] args) throws FileNotFoundException{
		ArrayList<Integer> rocketCodes = new ArrayList<Integer>();
		uploadRocketData(rocketCodes);
		int i=0;
		String opcode="";
		//default values for opcodes is 0
		int dE=0;
		int a=0;
		int b=0;
		int c=0;
		while( i < rocketCodes.size()) {
			System.out.println(i);
			/***
			need to reparse the opcodes --- 
			ABCDE 1002
			DE - two-digit opcode,      02 == opcode 2
			C - mode of 1st parameter,  0 == position mode
			B - mode of 2nd parameter,  1 == immediate mode
			A - mode of 3rd parameter,  0 == position mode, omitted due to being a leading zero
            ***/
            opcode=Integer.toString(rocketCodes.get(i));
            if(opcode.length() > 1) { dE=Integer.parseInt(opcode.charAt(opcode.length()-2)+""+opcode.charAt(opcode.length()-1)); }
            else { dE=Integer.parseInt(opcode); }
            if(opcode.length() > 2) { c=Character.getNumericValue(opcode.charAt(opcode.length()-3)); 
            	System.out.println(opcode.charAt(opcode.length()-3)); }
            if(opcode.length() > 3) { b=Character.getNumericValue(opcode.charAt(opcode.length()-4)); }
            if(opcode.length() > 4) { a=Character.getNumericValue(opcode.charAt(opcode.length()-5)); }
            	System.out.println("dE" +dE);
			//get first value if 1, add
			if(dE== 1) {
				opcode1Add(rocketCodes, i, b);
				i+=4;
			}
			//if 2, multiply
			else if(dE == 2) {
				opcode2Multiply(rocketCodes, i, b);
				i+=4;
			}
			//UPDATE: adding 2 new instructions for Day 5
			else if (dE == 3) {
				//takes integer as input and saves it to position given by integer
				opcode3Save(rocketCodes, i, b);
				i+=2;
			}
			else if (dE == 4) {
				//outputs value at the address
				opcode4Output(rocketCodes, i, b);
				i+=2;
			}
			//if 99 halt (return)
			else if(dE == 99) {
				System.out.println("Halting");
				break;
			}
			//Error unknown
			else {
				System.out.println("Something went wrong");
				break;
			}
		}
		System.out.println(rocketCodes);
	}
	
	//UPDATE: Day 5 add opcode 3
	public static void opcode3Save(ArrayList<Integer> rocketCode, int index, int b) {
		//input is 1 for now
		//mde 1 immediate mode parameter is simply itself 
		Scanner input = new Scanner(System.in);
		System.out.print("Enter diagnostic code: ");
		int num = input.nextInt();
		System.out.println("Thanks!");
		int loc1=0;
		if(b == 1) { loc1=index+1; }
		else{ loc1=rocketCode.get(index+1); }
		rocketCode.set(loc1, num);
	}
	
	//UPDATE: Day 5 add opcode 4
	public static void opcode4Output(ArrayList<Integer> rocketCode, int index, int b) {
		int val=0;
		if(b == 1) { val=index+1; }
		else { val=rocketCode.get(index+1); }
		System.out.println(rocketCode.get(val));
	}
	
	/***
	Performs addition of values at the index+1 and index+2, updates the ArrayList at the location provided in
	index+3 with the sum found
	***/
	public static void opcode1Add(ArrayList<Integer> rocketCode, int index, int b) {
		int loc1=rocketCode.get(index+1);
		int loc2=rocketCode.get(index+2);
		int loc3=rocketCode.get(index+3);
		int sum=0;
		if(b == 1) { sum=rocketCode.get(loc1)+loc2; }
		else { sum=rocketCode.get(loc1)+rocketCode.get(loc2); }
		rocketCode.set(loc3, sum);
	}
	
	/***
	Performs multiplication of values at the index+1 and index+2, updates the ArrayList at the location provided in
	index+3 with the product found
	***/
	public static void opcode2Multiply(ArrayList<Integer> rocketCode, int index, int b) {
		System.out.println(rocketCode);
		int loc1=rocketCode.get(index+1);
		int loc2=rocketCode.get(index+2);
		int loc3=rocketCode.get(index+3);
		int multiply=0;
		//mde 1 immediate mode parameter is simply itself 
		if(b == 1) { multiply=rocketCode.get(loc1)*loc2; }
		else { multiply=rocketCode.get(loc1)*rocketCode.get(loc2); }
		rocketCode.set(loc3, multiply);
	}	
	
	/***
	Takes comma deliminated data from ProgramAlarm.txt, parses and updates the rocketCodes ArrayList passed 
	in main.
	***/
	public static void uploadRocketData(ArrayList<Integer> rocketCodes) throws FileNotFoundException {
		String path = System.getProperty("user.dir");
		File f = new File(path+"/SupervisionTerminal.txt");
		String codes="";
		try {
			//File is only one line. Do not need to use a while loop to go through each line
			Scanner scan = new Scanner(f);
			codes=scan.nextLine();
			String [] parsedLine=codes.split(",");
			//take values in string array and convert to int array
			//can be more streamlined in Java 8
			for(int i=0; i < parsedLine.length; i++) {
				rocketCodes.add(Integer.parseInt(parsedLine[i]));
			}
		}
		catch(FileNotFoundException ex) {
			System.out.println("File Not found!");
		}
	}
}