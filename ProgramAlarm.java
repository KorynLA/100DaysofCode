/***
Advent of code day 2
***/
import java.util.*;
import java.math.*;
import java.lang.*;
import java.io.*;

public class ProgramAlarm {
	
	public static void main(String[] args) throws FileNotFoundException{
		ArrayList<Integer> rocketCodes = new ArrayList<Integer>();
		uploadRocketData(rocketCodes);
		//go through array
		System.out.println(rocketCodes.size());
		for(int i=0; i < rocketCodes.size(); i+=4) {
			//get first value if 1, add
			if(rocketCodes.get(i) == 1)
				opcode1Add(rocketCodes, i);
			//if 2, multiply
			else if(rocketCodes.get(i) == 2)
				opcode2Multiply(rocketCodes, i);
			//if 99 halt (return)
			else if(rocketCodes.get(i) == 99) {
				System.out.println("Halting");
				break;
			}
			//Error unknown
			else {
				System.out.println("Something went wrong");
			}
		}
		System.out.println(rocketCodes);
	}
	/***
	Performs addition of values at the index+1 and index+2, updates the ArrayList at the location provided in
	index+3 with the sum found
	***/
	public static void opcode1Add(ArrayList<Integer> rocketCode, int index) {
		int loc1=rocketCode.get(index+1);
		int loc2=rocketCode.get(index+2);
		int loc3=rocketCode.get(index+3);
		int sum=rocketCode.get(loc1)+rocketCode.get(loc2);
		rocketCode.set(loc3, sum);
	}
	/***
	Performs multiplication of values at the index+1 and index+2, updates the ArrayList at the location provided in
	index+3 with the product found
	***/
	public static void opcode2Multiply(ArrayList<Integer> rocketCode, int index) {
		int loc1=rocketCode.get(index+1);
		int loc2=rocketCode.get(index+2);
		int loc3=rocketCode.get(index+3);
		int multiply=rocketCode.get(loc1)*rocketCode.get(loc2);
		rocketCode.set(loc3, multiply);
	}	
	/***
	Takes comma deliminated data from ProgramAlarm.txt, parses and updates the rocketCodes ArrayList passed 
	in main.
	***/
	public static void uploadRocketData(ArrayList<Integer> rocketCodes) throws FileNotFoundException {
		String path = System.getProperty("user.dir");
		File f = new File(path+"/ProgramAlarm.txt");
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