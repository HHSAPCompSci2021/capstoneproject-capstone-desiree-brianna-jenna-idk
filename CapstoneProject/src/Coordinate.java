/**
 * Sourced from Ivan Voroshilin and Jomnipotent17 from StackExchange. Used in
 * the BFS algorithm.
 * 
 * @author Jenna
 *
 */
public class Coordinate {
	int x;
	int y;
	Coordinate parent;

	/**
	 * 
	 * @param x      The X-coordinate.
	 * @param y      The Y-coordinate.
	 * @param parent The parent of this Coordinate.
	 */
	public Coordinate(int x, int y, Coordinate parent) {
		this.x = x;
		this.y = y;
		this.parent = parent;
	}

	/**
	 * 
	 * @return The parent Coordinate.
	 */
	public Coordinate getParent() {
		return this.parent;
	}

	public String toString() {
		return "x = " + x + " y = " + y;
	}
}
