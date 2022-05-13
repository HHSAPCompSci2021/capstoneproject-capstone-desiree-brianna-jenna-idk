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
	private int x, y;
	private PImage namCap;	
	private int xgrid; //x value of player on grid
	private int ygrid; //y value of player on grid
	private int direction;
	private int step;
	private Map map;
	
	/**
	 * Constructs the character naM-caP.
	 * @param img The image for naM-caP.
	 * @param x The x-coordinate of naM-caP.
	 * @param y The y-coordinate of naM-caP.
	 */
	public NamCap(PImage img, int x, int y, Map m)
	{
		namCap = img;
		hasKiwi = false;
		this.x = x;
		this.y = y;
		direction = 0;
		xgrid = x;
		map = m;
		ygrid = y;
		step=3;
	}
	
	/**
	 * Draws naM-caP.
	 * @param g The PApplet used to draw.
	 */
	public void draw(PApplet g)
	{
		g.image(namCap, x, y, 30, 30);
	}
	
	/**
	 * If naM-caP eats a kiwi, for the next 5 seconds, naM-caP will try to hunt the player.
	 * Otherwise, naM-caP will try and travel to the nearest kiwi.
	 */
	public void act()
	{
		if(!hasKiwi)
		{
			// find the nearest kiwi
			// go to kiwi
		}
		
		else
		{
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
	 * Moves the Player one space forward in the direction it is facing
	 */
	public void move() {
		if(direction==0 && map.isValidLocation(xgrid+step,ygrid)) {
			xgrid+=step;
		}else if(direction==90 && map.isValidLocation(xgrid,ygrid-step)) {
			ygrid-=step;
		}else if(direction==180 && map.isValidLocation(xgrid-step,ygrid)) {
			xgrid-=step;
		}else if(direction==270 && map.isValidLocation(xgrid,ygrid+step)) {
			ygrid+=step;
		}
	}
	
	/**
	 * If naM-caP hits a wall, naM-caP will turn to a direction they are able to move in.
	 */
	public void turn()
	{
		// if hit a wall, turn 90 degrees
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
	
//	/**
//	* Solves the labyrinth in the smallest number of moves.
//	* 
//	* @param x The x coordinate of the starting point.
//	* @param y The y coordinate of the starting point.
//	* @return An ArrayList containing the coordinates of all locations on the shortest path to the exit, where the first 
//	* element is the location of the starting point and the last element is the location of the exit, or null if no path can be found.
//	*/
//	public ArrayList<Point> findPath(int x, int y) {
//		int width = 0, height = 0;
//		
//		// clicking outside of the grid given
//		for(int i = 0; i < grid.length; i++)
//		{
//			for(int j = 0; j < grid[i].length; j++)
//			{
//				if(grid[i][j] == '.' || grid[i][j] == 'X' || grid[i][j] == '#')
//				{
//					if(i > width)
//					{
//						width = i;
//					}
//					
//					if(j > height)
//					{
//						height = j;
//					}
//				}
//			}
//		}
//		
//		if(x > width || x < 0 || y > height || y < 0)
//		{
//			System.out.println("Out of bounds.");
//			return null;
//		}
//		
//		ArrayList<Point> answer = definePath(x, y, width, height, false);
//		if(answer != null)
//		{
//			for(int i = 0; i < answer.size(); i++)
//			{
//				grid[(int) answer.get(i).getX()][(int) answer.get(i).getY()] = ')';
//			}
//			
//			return definePath(x, y, width, height, false);
//		}
//		
//		else
//		{
//			System.out.println("No path found.");
//			return null;
//		}
//	}
//
//
//	// Additional private recursive methods
//	private ArrayList<Point> definePath(int i, int j, int width, int height, boolean hasCloak)
//	{	
//		// BASE CASES
//		// Are you out of the grid bounds?
//		if(i > width || i < 0 || j > height || j < 0)
//		{
//			//System.out.println("Out of bounds.");
//			return null;
//		}
//
//		// Are you in a wall?
//		if(grid[i][j] == '#')
//		{
//			//System.out.println("At a #");
//			return null;
//		}
//		
//		// Are you somewhere you have been before, while holding the cloak?
//		if(grid[i][j] == '$')
//		{
//			//System.out.println("At a $");
//			return null;
//		}
//
//		// Are you somewhere you have been before while not holding the cloak, and you don't currently have the cloak?
//		if(grid[i][j] == '!' && hasCloak == false)
//		{
//			//System.out.println("At a ! and hasCloak");
//			return null;
//		}
//
//		// Are you at a monster, and you don't current have the cloak?
//		if(grid[i][j] == 'A' && hasCloak == false)
//		{
//			//System.out.println("At a A and don't have cloak");
//			return null;
//		}
//
//		// Are you at the exit?
//		if(grid[i][j] == 'X')
//		{
//			//System.out.println("At an X");
//			ArrayList<Point> path = new ArrayList<Point>();
//			path.add(new Point(i, j));
//			return path;
//		}
//				
//		// RECURSIVE CASE
//		else
//		{
//			// Save the character at grid location x,y in a local variable for later use
//			char location = grid[i][j];
//			
//			// If this is the spot with the cloak, switch the hasCloak boolean to true
//			if(location == '@')
//			{
//				hasCloak = true;
//			}
//			
//			// Add a "breadcrumb" character to the grid at x,y. Use 2 different breadcrumb characters depending on whether hasCloak is true or not.
//			if(hasCloak) // if holding cloak, breadcrumb $
//			{
//				grid[i][j] = '$';
//			}
//			
//			else // if not holding cloak, breadcrumb !
//			{
//				grid[i][j] = '!';
//			}
//			
//			// Recursively call findPath() 4 times - once in each of the 4 fundamental directions (one space up, down, left, and right). Save the ArrayList that is returned by each.
//			// Of the 4 ArrayLists that are returned, find the ArrayList that is not null and has the smallest size.
//			
//			ArrayList<Point> one = definePath(i, j - 1, width, height, hasCloak);
//			ArrayList<Point> two = definePath(i - 1, j, width, height, hasCloak);
//			ArrayList<Point> three = definePath(i, j + 1, width, height, hasCloak);
//			ArrayList<Point> four = definePath(i + 1, j, width, height, hasCloak);
//			
////			System.out.println("one: " + one);
////			System.out.println("two: " + two);
////			System.out.println("three: " + three);
////			System.out.println("four: " + four);
//			
//			ArrayList<Point> min = new ArrayList<Point>();
//			
//			if(one != null)
//			{
//				min = one;
//			}
//			
//			else if(two != null)
//			{
//				min = two;
//			}
//			
//			else if(three != null)
//			{
//				min = three;
//			}
//			
//			else if(four != null)
//			{
//				min = four;
//			}
//			
//			if(two != null)
//			{
//				if(two.size() < min.size())
//				{
//					min = two;
//				}
//			}
//			
//			if(three != null)
//			{
//				if(three.size() < min.size())
//				{
//					min = three;
//				}
//			}
//			
//			if(four != null)
//			{
//				if(four.size() < min.size())
//				{
//					min = four;
//				}
//			}
//			
//			//System.out.println("min: " + min);
//
//			// Put the original saved character back into the grid at x,y (to remove the breadcrumb and prevent any permanent modification to the grid)
//			grid[i][j] = location;
//			
//			// If all 4 of the ArrayLists returned by the recursive calls are null:
//			if(one == null && two == null && three == null && four == null)
//			{
//				return null;
//			}
//			
//			// If you have found the smallest non-null ArrayList:
//			if(min.size() != 0)
//			{
//				// Add this location to the beginning of the list, then return it.
//				min.add(new Point(i, j)); 
//				return min;
//			}
//		}
//						
//		return null;
//	}

}
