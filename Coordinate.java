class Coordinate {
	private int x;
	private int y;
	private int stepsTaken;

	public Coordinate(int x , int y, int stepsTaken) {
		this.x=x;
		this.y=y;
		this.stepsTaken = stepsTaken;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getStepsTaken() {
		return stepsTaken;
	}
}