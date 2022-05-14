import java.awt.Point;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * naM-caP is the main antagonist of the game, whose goal is to eat kiwis to gain the ability to hunt the player down.
 * 
 * @author Jenna Wang
 * 
 */
public class NamCap {
	
	private boolean hasKiwi;
	private PImage image;	
	private int x, y, direction;
	private double step;
	
	/**
	 * Constructs the character naM-caP.
	 * @param img The image for naM-caP.
	 * @param x The x-coordinate of naM-caP.
	 * @param y The y-coordinate of naM-caP.
	 */
	public NamCap(PImage img, int x, int y)
	{
		image = img;
		this.x = x;
		this.y = y;
		hasKiwi = false;
		direction = 0;
		step = 30;
	}
	
	/**
	 * Draws naM-caP.
	 * @param g The PApplet used to draw.
	 */
	public void draw(PApplet g)
	{
		g.image(image, x, y, 30, 30);
	}
	
	/**
	 * If naM-caP eats a kiwi, for the next 5 seconds, naM-caP will try to hunt the player.
	 * Otherwise, naM-caP will try and travel to the nearest kiwi.
	 * 
	 * @param grid The Map naM-caP is on.
	 */
	public void act(Map grid)
	{	
		if(!hasKiwi)
		{
			ArrayList<Point> fruitLoc = find('k', grid);
			if(fruitLoc != null && fruitLoc.size() > 1)
			{
				x = (int) fruitLoc.get(fruitLoc.size() - 2).getY() * 30;
				y = (int) fruitLoc.get(fruitLoc.size() - 2).getX() * 30;
			}
		}
		
		else if(hasKiwi)
		{
			step = 3.5;
//			ArrayList<Point> playerLoc = find()
			// find shortest distance to player
			// chase the player
		}
	}
	
	/**
	 * Sets the location of naM-caP to a certain coordinate.
	 * @param x The x-coordinate of the location.
	 * @param y The y-coordinate of the location.
	 */
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the x value of the Player's location
	 * @return x value of Player's location
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the y value of the Player's location
	 * @return y value of the Player's location
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * If naM-caP hits a wall, naM-caP will turn to a direction they are able to move in.
	 */
	public void turn()
	{
		if(direction == 270)
		{
			direction = 0;
		}
		
		else
		{
			direction += 90;
		}
	}
	
	/**
	 * Eats the Fruit and gains a special ability depending on the Fruit.
	 * @param f The Fruit eaten.
	 */
	public void eatFruit(Fruit f)
	{
		f.remove();
		// figure out which fruit it is
		// if fruit is kiwi
		// hasKiwi == true 
	}
	
	/**
	* Solves the labyrinth in the smallest number of moves.
	* 
	* @param x The x coordinate of the starting point.
	* @param y The y coordinate of the starting point.
	* @return An ArrayList containing the coordinates of all locations on the shortest path to the exit, where the first 
	* element is the location of the starting point and the last element is the location of the exit, or null if no path can be found.
	*/
	public ArrayList<Point> find(char target, Map grid) {
		return(find(y / 30, x / 30, target, false, grid));
	}

	// Additional private recursive methods
	private ArrayList<Point> find(int i, int j, char target, boolean hasCloak, Map grid)
	{	
		// BASE CASES
		// Are you out of the grid bounds?
		if(i >= grid.getLength() || i < 0 || j > grid.getRowLength(i) || j < 0)
		{
			//System.out.println("Out of bounds.");
			return null;
		}

		// Are you in a wall?
		if(grid.get(i, j) == '#')
		{
			//System.out.println("At a #");
			return null;
		}
		
		// Are you somewhere you have been before, while holding the cloak?
		if(grid.get(i, j) == '$')
		{
			//System.out.println("At a $");
			return null;
		}

		// Are you somewhere you have been before while not holding the cloak, and you don't currently have the cloak?
		if(grid.get(i, j) == '!' && hasCloak == false)
		{
			//System.out.println("At a ! and hasCloak");
			return null;
		}

		// Are you at a monster, and you don't current have the cloak?
		if(grid.get(i, j) == 'A' && hasCloak == false)
		{
			//System.out.println("At a A and don't have cloak");
			return null;
		}

		// Are you at the exit?
		if(grid.get(i, j) == target)
		{
			//System.out.println("At an X");
			ArrayList<Point> path = new ArrayList<Point>();
			path.add(new Point(i, j));
			return path;
		}
				
		// RECURSIVE CASE
		else
		{
			// Save the character at grid location x,y in a local variable for later use
			char location = grid.get(i, j);
			
			// If this is the spot with the cloak, switch the hasCloak boolean to true
			if(location == '@')
			{
				hasCloak = true;
			}
			
			// Add a "breadcrumb" character to the grid at x,y. Use 2 different breadcrumb characters depending on whether hasCloak is true or not.
			if(hasCloak) // if holding cloak, breadcrumb $
			{
				grid.set(i, j, '$');
			}
			
			else // if not holding cloak, breadcrumb !
			{
				grid.set(i, j, '!');
			}
			
			// Recursively call findPath() 4 times - once in each of the 4 fundamental directions (one space up, down, left, and right). Save the ArrayList that is returned by each.
			// Of the 4 ArrayLists that are returned, find the ArrayList that is not null and has the smallest size.
			
			ArrayList<Point> one = find(i, j - 1, target, hasCloak, grid);
			ArrayList<Point> two = find(i - 1, j, target, hasCloak, grid);
			ArrayList<Point> three = find(i, j + 1, target, hasCloak, grid);
			ArrayList<Point> four = find(i + 1, j, target, hasCloak, grid);
			
			ArrayList<Point> min = new ArrayList<Point>();
			
			if(one != null)
			{
				min = one;
			}
			
			else if(two != null)
			{
				min = two;
			}
			
			else if(three != null)
			{
				min = three;
			}
			
			else if(four != null)
			{
				min = four;
			}
			
			if(two != null)
			{
				if(two.size() < min.size())
				{
					min = two;
				}
			}
			
			if(three != null)
			{
				if(three.size() < min.size())
				{
					min = three;
				}
			}
			
			if(four != null)
			{
				if(four.size() < min.size())
				{
					min = four;
				}
			}

			// Put the original saved character back into the grid at x,y (to remove the breadcrumb and prevent any permanent modification to the grid)
			grid.set(i, j, location);
			
			// If all 4 of the ArrayLists returned by the recursive calls are null:
			if(one == null && two == null && three == null && four == null)
			{
				return null;
			}
			
			// If you have found the smallest non-null ArrayList:
			if(min.size() != 0)
			{
				// Add this location to the beginning of the list, then return it.
				min.add(new Point(i, j)); 
				return min;
			}
		}
						
		return null;
	}

}
