public class Coordinate {
	
	int x;
	int y;
	Coordinate parent;

	public Coordinate(int x, int y, Coordinate parent) {
		this.x = x;
		this.y = y;
		this.parent = parent;
	}
	
	public Coordinate getParent() {
		return this.parent;
	}

	public String toString() {
		return "x = " + x + " y = " + y;
	}
}
