/**
 * 
 * Represents the fruits that have special abilities.
 * 
 * @author Jenna Wang
 *
 */
public class Fruit {
	
	private int row, col;
	private boolean eaten;
	
	/**
	 * Constructs a fruit with a set row and column.
	 * @param row The row of the fruit's location.
	 * @param col The column of the fruit's location.
	 */
	public Fruit(int r, int c)
	{
		this.row = r;
		this.col = c;
		eaten = false;
	}

	/**
	 * Gives the fruit a new location.
	 * @param row The new row you wish to place the fruit on.
	 * @param col The new column you wish the place the fruit on.
	 */
	public void setLocation(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Removes the fruit from the map.
	 */
	public void remove()
	{
		if(eaten)
		{
			// remove
		}
	}
	
	/**
	 * Activate the ability specific to the fruit.
	 */
	public void useAbility()
	{
		
	}
	
	/**
	 * Draws the fruit.
	 */
	public void draw()
	{
		
	}
	
	/**
	 * @return x location of the kiwi.
	 */
	public int getX()
	{
		return col;
	}
	
	/**
	 * @return y location of the kiwi.
	 */
	public int getY()
	{
		return row;
	}
	
}
