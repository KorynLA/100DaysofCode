/***
- Uses Coordinate Objects to keep track of the Coorinates the wire runs through + the number of steps taken to
that coorinate
- 2 Coordinate objects are filled with the x,y, and steps taken in CrossedWires.txt
- The intersections of the 2 coordinate ArrayLists are found. Either the min Manhattan distance, or the step 
distance is printed
- Time Complexity: O(n^2) done when comparing the 2 Coordinate ArrayLists
- Space Complexity: O(N+M) Each ArrayList has the routes each wire took 
***/
import java.util.*;
import java.io.*;


class CrossedWires {

	public static void main(String[] args) throws FileNotFoundException{
		ArrayList<Coordinate> matrixWire1 = new ArrayList<Coordinate>();
		ArrayList<Coordinate> matrixWire2 = new ArrayList<Coordinate>();
		HashMap<Integer, ArrayList<Integer>> intersect = new HashMap<Integer, ArrayList<Integer>>();
		String path = System.getProperty("user.dir");
		String wire1Path;
		String wire2Path;
		try {
			File f = new File(path+"/CrossedWires.txt");
			Scanner scan = new Scanner(f);
			wire1Path = scan.nextLine();
			wire2Path = scan.nextLine();
		}
		catch(FileNotFoundException ex) {
			System.out.println("File not found!");
			return ;
		}
		Coordinate c1 = new Coordinate(0, 0, 0);
		matrixWire1.add(c1);
		matrixWire2.add(c1);
		buildGraph(wire1Path, matrixWire1);
		buildGraph(wire2Path, matrixWire2);
		//findIntersections(matrixWire1, matrixWire2, intersect);
		findIntersections(matrixWire1, matrixWire2);
		//manhattanDistance(intersect);

	}
	//Finds the Manhattan distance from the central port closest to their intersection
	//Does not count at start point & when wire crosses itself
	public static void manhattanDistance(HashMap<Integer, ArrayList<Integer>> distance) {
		int minSum=Integer.MAX_VALUE;
		for(Map.Entry<Integer, ArrayList<Integer>> entry : distance.entrySet()) {
			int key= entry.getKey();
			if(key != 0) {
				ArrayList<Integer> value= entry.getValue();
				for(int i=0; i < value.size(); i++) {
					minSum=Math.min(minSum, Math.abs(key)+Math.abs(value.get(i)));
				}
			}
		}
		System.out.println(minSum);
	}
	//put intersections into hashMap that will be used to calculate ManHattan Distance
	public static void findIntersections(ArrayList<Coordinate> wire1, ArrayList<Coordinate> wire2, HashMap<Integer, ArrayList<Integer>> intersected) {
		for(int i=0; i < wire1.size(); i++) {
			for(int j=0; j < wire2.size(); j++) {
				if(wire1.get(i).getX() == wire2.get(j).getX() && wire1.get(i).getY() == wire2.get(j).getY()) {
					if(!intersected.containsKey(wire1.get(i).getX())) {
						intersected.put(wire1.get(i).getX(), new ArrayList<Integer>());
						intersected.get(wire1.get(i).getX()).add(wire1.get(i).getY());
					}
					else {
						if(!intersected.get(wire1.get(i).getX()).contains(wire1.get(i).getY())) {
							intersected.get(wire1.get(i).getX()).add(wire1.get(i).getY());
						}
					}
				}
			}
		}
	}
	//Takes the found intersections and adds the amount of steps taken to get to that value.
	//Prints the minimum amount of steps taken to get to an intersection.
	public static void findIntersections(ArrayList<Coordinate> wire1, ArrayList<Coordinate> wire2) {
		int minSteps=Integer.MAX_VALUE;
		for(int i=0; i < wire1.size(); i++) {
			for(int j=0; j < wire2.size(); j++) {
				if(wire1.get(i).getX() == wire2.get(j).getX() && wire1.get(i).getY() == wire2.get(j).getY()) {
					if(wire1.get(i).getX() != 0 && wire1.get(i).getY() != 0) {
						minSteps=Math.min(minSteps, wire2.get(j).getStepsTaken() + wire1.get(i).getStepsTaken());
					}
				}
			}
		}
		System.out.println(minSteps);
	}
	//Builds graphs that will be compared to find intersections between them
	//Adds to map for every step/grid the wire goes through
	public static void buildGraph(String wire, ArrayList<Coordinate> board) {
		String[] movement = wire.split(",");
		//central port starts at arbitrary location (1,1)
		for(String m : movement) {
			int coord=Integer.parseInt(m.substring(1, m.length()));
			int lastAdded= board.size()-1;
			int prevX=board.get(lastAdded).getX();
			int prevY=board.get(lastAdded).getY();
			//board.add(new Coordinate(, ));
			//Wire goes left
			if(m.charAt(0) == 'L') {
				for(int i=prevX-1; i >= prevX-coord; i--) {
					int prevSteps=board.get(board.size()-1).getStepsTaken();
					board.add(new Coordinate(i, prevY, prevSteps+1));
				}
			}
			//Wire goes right
			if(m.charAt(0) == 'R') {
				//board.add(new Coordinate(prevX+coord, prevY));
				for(int i=prevX+1; i <= prevX+coord; i++) {
					int prevSteps=board.get(board.size()-1).getStepsTaken();
					board.add(new Coordinate(i, prevY, prevSteps+1));
				}
			}
			//Wire goes up
			if(m.charAt(0) == 'U') {
				//board.add(new Coordinate(prevX, prevY+coord));
				for(int i=prevY+1; i <= prevY+coord; i++) {
					int prevSteps=board.get(board.size()-1).getStepsTaken();
					board.add(new Coordinate(prevX, i, prevSteps+1));
				}
			}
			//Wire goes down
			if(m.charAt(0) == 'D') {
				//board.add(new Coordinate(prevX, prevY-coord));
				for(int i=prevY-1; i >= prevY-coord; i--) {
					int prevSteps=board.get(board.size()-1).getStepsTaken();
					board.add(new Coordinate(prevX, i, prevSteps+1));
				}
			}
		}
	}
}