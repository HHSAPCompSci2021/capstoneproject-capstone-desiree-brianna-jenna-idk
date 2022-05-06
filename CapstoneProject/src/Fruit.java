<<<<<<< HEAD

public class Fruit {
	
	int row, col;
	boolean eaten;
	
=======
/**
 * 
 * Represents the fruits that have special abilities.
 * 
 * @author jennawang
 *
 */
public class Fruit {
	
	private int row, col;
	private boolean eaten;
	
	/**
	 * Constructs a fruit with a set row and col.
	 * @param row The row of the fruit's location.
	 * @param col The column of the fruit's location.
	 */
>>>>>>> 73d60609641afc01a940b685b98652ee79d701e7
	public Fruit(int row, int col)
	{
		this.row = row;
		this.col = col;
		eaten = false;
	}

<<<<<<< HEAD
	public void setLocation()
=======
	/**
	 * Gives the fruit a new location.
	 * @param row The new row you wish to place the fruit on.
	 * @param col The new column you wish the place the fruit on.
	 */
	public void setLocation(int row, int col)
>>>>>>> 73d60609641afc01a940b685b98652ee79d701e7
	{
		
	}
	
<<<<<<< HEAD
	public void remove()
	{
		
	}
	
	public void draw() 
	{
		
	}
=======
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
	
>>>>>>> 73d60609641afc01a940b685b98652ee79d701e7
}
