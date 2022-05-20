import processing.core.PApplet;

/**
 * 
 * Represents the fruits that have special abilities.
 * 
 * @author Brianna, Jenna
 *
 */
public abstract class Fruit {
	
	private int row, col;
	
	/**
	 * Constructs a fruit with a set row and column.
	 * 
	 * @param r The row of the fruit's location.
	 * @param c The column of the fruit's location.
	 */
	public Fruit(int r, int c) {
		row = r;
		col = c;
	}

	/**
	 * Gives the fruit a new location.
	 * 
	 * @param row The new row you wish to place the fruit on.
	 * @param col The new column you wish the place the fruit on.
	 */
	public void setLocation(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Removes the fruit from the map.
	 * 
	 * @param map The Map the fruit is on.
	 */
	public void remove(Map map) {
		map.set(row / 30, col / 30, '.');
	}
	
	/**
	 * Draws the fruit.
	 * 
	 * @param g The PApplet used to draw.
	 */
	public abstract void draw(PApplet g);
	
	/**
	 * @return X-Value of the Fruit.
	 */
	public int getX()
	{
		return col;
	}
	
	/**
	 * @return Y-value of the fruit.
	 */
	public int getY()
	{
		return row;
	}
	
}
