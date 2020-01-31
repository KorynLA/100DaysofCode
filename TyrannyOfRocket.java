/***
Advent of code 2019 Day 1: https://adventofcode.com/2019/day/1
***/

import java.util.*;
import java.math.*;
import java.lang.*;
import java.io.*;

public class TyrannyOfRocket{
	/***
	Recursive function
	Sum is the value changing with every fuel amount added for the previous fuel added.
	***/	
	public static int fuelRequired(int sum, int m) {
		//base case, once it is less than or equal to zero, no more fuel is needed
		if((m/3)-2 <= 0) {
			return sum;
		}
		else {
			sum+=((m/3)-2);
			return fuelRequired(sum, (m/3)-2);
		}
	}

	//Prints the amout of fuel needed to go to the modules
	public static void main(String[] args) throws FileNotFoundException{
		int m=0;
		int fuel=0;
		int sum=0;
		
		String path = System.getProperty("user.dir");
		File f = new File(path+"/TyrannyOfRocketInput.txt");
		
		try {
			Scanner scan = new Scanner(f);
			while(scan.hasNextLine()) {
				m=Integer.parseInt(scan.nextLine());
				fuel+=fuelRequired(sum, m);
			}
		}
		catch(FileNotFoundException ex) {
			System.out.println("File not found");
			return ;
		}
		
		System.out.println(fuel);
	}
}