import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * naM-caP is the main antagonist of the game, whose goal is to eat kiwis to gain the ability to hunt the player down.
 * 
 * @author Brianna, Desiree, Jenna
 * 
 */
public class NamCap {
	
	private boolean hasKiwi;
	private PImage image;	
	private int x, y, xi, yi, direction, hkCount, nkCount;
	private ArrayList<Coordinate> fruitLoc, playerLoc;
	//private ArrayList<Point> fruitLoc, playerLoc;
	
	/**
	 * Constructs the character naM-caP.
	 * 
	 * @param img The image for naM-caP.
	 * @param x The x-coordinate of naM-caP.
	 * @param y The y-coordinate of naM-caP.
	 */
	public NamCap(PImage img, int x, int y)
	{
		image = img;
		this.x = x;
		xi=x;
		this.y = y;
		yi=y;
		hasKiwi = false;
		direction = 0;
		fruitLoc = new ArrayList<Coordinate>();
	}
	
	/**
	 * Draws naM-caP.
	 * 
	 * @param g The PApplet used to draw.
	 */
	public void draw(PApplet g)
	{
		if(hasKiwi)
		{
			g.push();
			g.tint(0, 0, 255);
			g.image(image, x, y, 30, 30);
			g.pop();
		}
		
		else
		{
			g.image(image, x, y, 30, 30);
		}
	}
	
	/**
	 * If naM-caP eats a kiwi, for the next 5 seconds, naM-caP will try to hunt the player.
	 * Otherwise, naM-caP will try and travel to the nearest kiwi.
	 * 
	 * @param grid The Map naM-caP is on.
	 * @param player The Player on the grid.
	 */
	public void act(Map grid, Player player)
	{	
		// bfs
//		if(!hasKiwi)
//		{
//			Coordinate p = getKiwiPath(y / 30, x / 30, grid);
//			while(p.getParent() != null) {
//				//System.out.println("here");
//				 fruitLoc.add(p);
//				 p = p.getParent();
//		     }
//			
//			//System.out.println(fruitLoc)
//			 x = fruitLoc.get(fruitLoc.size() - 1).y * 30 + 15;
//			 y = fruitLoc.get(fruitLoc.size() - 1).x * 30 + 15;
//		}
		
//		else if(hasKiwi)
//		{
//			 
//			 Coordinate p2 = getPlayerPath(x / 30, y / 30, grid, player);
//			 while(p2.getParent() != null)
//			 {
//				 System.out.println(playerLoc);
//				 playerLoc.add(p2);
//				 p2 = p2.getParent();
//			 }
//			 
//			 
//			 x = playerLoc.get(playerLoc.size() - 1).y * 30 + 15;
//			 y = playerLoc.get(playerLoc.size() - 1).x * 30 + 15;
//		}
		

		// dfs
		if(!hasKiwi)
		{
			ArrayList<Point> fruitLoc = find('k', player, grid);
			//System.out.println(fruitLoc);
			if(fruitLoc != null && fruitLoc.size() > 1)
			{
				x = (int) fruitLoc.get(fruitLoc.size() - 2).getY() * 30 + 15;
				y = (int) fruitLoc.get(fruitLoc.size() - 2).getX() * 30 + 15;
			}
		}
		
		else if(hasKiwi)
		{
			ArrayList<Point> playerLoc = findLoc(player.getX(), player.getY(), grid);
			//System.out.println(playerLoc);
			if(playerLoc != null && playerLoc.size() > 1)
			{
				x = (int) playerLoc.get(playerLoc.size() - 2).getY() * 30 + 15;
				y = (int) playerLoc.get(playerLoc.size() - 2).getX() * 30 + 15;
			}
		//	System.out.println(playerLoc.get(playerLoc.size() - 2).getY() + " ");
		}
		
	}
	
	/**
	 * Sets the location of naM-caP to a certain coordinate.
	 * 
	 * @param x The x-coordinate of the location.
	 * @param y The y-coordinate of the location.
	 */
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return The x value of naM-caP's location.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return The y value of naM-caP's location.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Resets naM-caP to its initial position and direction.
	 */
	public void reset() {
		x=xi;
		y=yi;
		direction=0;
		hasKiwi = false;
	}
	
	/**
	 * @return true if naM-caP has eaten a kiwi, false if not.
	 */
	public boolean hasEatenKiwi() {
		return hasKiwi;
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
	 * 
	 * @param f The Fruit eaten.
	 */
	public void eatFruit(Fruit f)
	{
		if(f instanceof Kiwi)
		{
			hasKiwi = true;
			hkCount = 0;
		}
	}
	
	/**
	 * Changes hasKiwi to false
	 */
	public void setKiwiFalse() {
		hasKiwi=false;
		nkCount = 0;
	}
	
	/**
	 * Finds a character in the Map in the smallest number of moves.
	 * 
	 * @param target The target character.
	 * @param player The Player in the map.
	 * @param grid The Map of the game.
	 * @return An ArrayList of Points for the next moves naM-caP should make.
	 */
	public ArrayList<Point> find(char target, Player player, Map grid) {
		return(find(y / 30, x / 30, target, player, grid));
	}
	
	/**
	 * Finds a location in the Map's grid in the smallest number of moves.
	 * 
	 * @param i The row of the Map.
	 * @param j The column of the Map.
	 * @param grid The Map of the game.
	 * @return An ArrayList of Points for the next moves naM-caP should make.
	 */
	public ArrayList<Point> findLoc(int i, int j, Map grid)
	{
		return(findLoc(y / 30, x / 30, i / 30, j / 30, grid));
	}

	private ArrayList<Point> find(int i, int j, char target, Player player, Map grid)
	{	
		// BASE CASES
		// Are you out of the grid bounds?
		if(i >= grid.getLength() || i < 0 || j > grid.getRowLength(i) || j < 0)
		{
			return null;
		}

		//System.out.println("i: " + i + ", j: " + j + ", (i, j): " + grid.get(i, j));
		// Are you in a wall? Are you somewhere you have been before? Is there a player in the way?
		if(grid.get(i, j) == '#' || grid.get(i, j) == '!' || (i == player.getX() / 30 && j == player.getY() / 30) || grid.get(i, j) == '*')
		{
			//System.out.println("here1");
			return null;
		}

		// Are you at the exit?
		if(grid.get(i, j) == target)
		{
			ArrayList<Point> path = new ArrayList<Point>();
			path.add(new Point(i, j));
			return path;
		}
				
		// RECURSIVE CASE
		else
		{
			// Save the character at grid location x,y in a local variable for later use
			char location = grid.get(i, j);
			grid.set(i, j, '!');
			
			// Recursively call findPath() 4 times - once in each of the 4 fundamental directions (one space up, down, left, and right). Save the ArrayList that is returned by each.
			// Of the 4 ArrayLists that are returned, find the ArrayList that is not null and has the smallest size.
			
			ArrayList<Point> one = find(i, j - 1, target, player, grid);
			ArrayList<Point> two = find(i - 1, j, target, player, grid);
			ArrayList<Point> three = find(i, j + 1, target, player, grid);
			ArrayList<Point> four = find(i + 1, j, target, player, grid);
			
			ArrayList<Point> min = null;
			
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
	
	// x and y are namcap's coordinates
	// i and j are the target coordinates
	private ArrayList<Point> findLoc(int x, int y, int i, int j, Map grid)
	{	
		//System.out.println("x: " + x + ", y: " + y + ", i: " + i + ", j: " + j);
		// BASE CASES
		// Are you out of the grid bounds?
		if(x >= grid.getLength() || x < 0 || y > grid.getRowLength(x) || y < 0)
		{
			return null;
		}

		// Are you in a wall? Are you somewhere you have been before? Are you at the starting box?
		if(grid.get(x, y) == '#' || grid.get(x, y) == '!' || grid.get(x, y) == '*')
		{
			return null;
		}

		// Are you at the exit?
		if(x == i && y == j)
		{
			ArrayList<Point> path = new ArrayList<Point>();
			path.add(new Point(x, y));
			return path;
		}
				
		// RECURSIVE CASE
		else
		{
			// Save the character at grid location x,y in a local variable for later use
			char location = grid.get(x, y);
			grid.set(x, y, '!');
			
			// Recursively call findPath() 4 times - once in each of the 4 fundamental directions (one space up, down, left, and right). Save the ArrayList that is returned by each.
			// Of the 4 ArrayLists that are returned, find the ArrayList that is not null and has the smallest size.
			
			ArrayList<Point> one = findLoc(x, y - 1, i, j, grid);
			ArrayList<Point> two = findLoc(x - 1, y, i, j, grid);
			ArrayList<Point> three = findLoc(x, y + 1, i, j, grid);
			ArrayList<Point> four = findLoc(x + 1, y, i, j, grid);
			
			ArrayList<Point> min = null;
			
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
			grid.set(x, y, location);
			
			// If all 4 of the ArrayLists returned by the recursive calls are null:
			if(one == null && two == null && three == null && four == null)
			{
				return null;
			}
			
			// If you have found the smallest non-null ArrayList:
			if(min.size() != 0)
			{
				// Add this location to the beginning of the list, then return it.
				min.add(new Point(x, y)); 
				return min;
			}
		}
						
		return null;
	}
	
	/**
	 * Determines if the Fruit and naM-caP are at the same spot.
	 * 
	 * @param f The Fruit.
	 * @return true if at same location, false if not.
	 */
	public boolean atSameLocation(Fruit f) 
	{
		return(f.getX() / 30 == x / 30 && f.getY() / 30 == y / 30);
	}
	 
	/**
	 * 
	 * @param x
	 * @param y
	 * @param grid
	 * @return
	 */
    public Coordinate getKiwiPath(int x, int y, Map grid) {

    	Queue<Coordinate> q = new LinkedList<Coordinate>();
        q.add(new Coordinate(x,y, null));
        //System.out.println(grid.toString());

        while(!q.isEmpty()) {
            Coordinate p = q.remove();
            char location = grid.get(p.x, p.y);

            if (grid.get(p.x, p.y) == 'k') {
                return p;
            }

            if(isFree(p.x+1,p.y, grid)) {
                grid.set(p.x, p.y, '!');
                Coordinate nextP = new Coordinate(p.x+1,p.y, p);
                q.add(nextP);
            }

            if(isFree(p.x-1,p.y, grid)) {
            	grid.set(p.x, p.y, '!');
                Coordinate nextP = new Coordinate(p.x-1,p.y, p);
                q.add(nextP);
            }

            if(isFree(p.x,p.y+1, grid)) {
            	grid.set(p.x, p.y, '!');
                Coordinate nextP = new Coordinate(p.x,p.y+1, p);
                q.add(nextP);
            }

             if(isFree(p.x,p.y-1, grid)) {
            	grid.set(p.x, p.y, '!');
                Coordinate nextP = new Coordinate(p.x,p.y-1, p);
                q.add(nextP);
            }
             
            grid.set(p.x, p.y, location);
        }
        return null;
    }
    
    public Coordinate getPlayerPath(int x, int y, Map grid, Player player) {

    	Queue<Coordinate> q2 = new LinkedList<Coordinate>();
        q2.add(new Coordinate(x,y, null));

        while(!q2.isEmpty()) {
            Coordinate p = q2.remove();
            char location = grid.get(p.x, p.y);

            //System.out.println("x: " + p.x + ", y: " + p.y + ", playerx: " + player.getX() / 30 + ", playery: " + player.getY() / 30);
            if(p.x == player.getX() / 30 && p.y == player.getY() / 30)
            {
            	return p;
            }
//            if (grid.get(p.x, p.y) == 'k') {
//                //System.out.println("Exit is reached!");
//                return p;
//            }

            if(isFree2(p.x+1,p.y, grid, player)) {
                grid.set(p.x, p.y, '!');
                Coordinate nextP = new Coordinate(p.x+1,p.y, p);
                q2.add(nextP);
            }

            if(isFree2(p.x-1,p.y, grid, player)) {
            	grid.set(p.x, p.y, '!');
                Coordinate nextP = new Coordinate(p.x-1,p.y, p);
                q2.add(nextP);
            }

            if(isFree2(p.x,p.y+1, grid, player)) {
            	grid.set(p.x, p.y, '!');
                Coordinate nextP = new Coordinate(p.x,p.y+1, p);
                q2.add(nextP);
            }

             if(isFree2(p.x,p.y-1, grid, player)) {
            	grid.set(p.x, p.y, '!');
                Coordinate nextP = new Coordinate(p.x,p.y-1, p);
                q2.add(nextP);
            }
             
            grid.set(p.x, p.y, location);

        }
        return null;
    }

    /**
     * Checks if the location is in bounds and either a path or a kiwi.
     * 
     * @param x
     * @param y
     * @param grid
     * @return
     */
    public boolean isFree(int x, int y, Map grid) {
    	//System.out.println("x: " + x + ", y: " + y + ", playerx: " + player.getX() / 30 + ", playery: " + player.getY() / 30);
        return((x >= 0 && x < grid.getLength()) && (y >= 0 && y < grid.getRowLength(x)) && (grid.get(x, y) == '.' || grid.get(x, y) == 'k')); 
    }
    
    public boolean isFree2(int x, int y, Map grid, Player player) {
    	//System.out.println("x: " + x + ", y: " + y + ", playerx: " + player.getX() / 30 + ", playery: " + player.getY() / 30);
        return((x >= 0 && x < grid.getLength()) && (y >= 0 && y < grid.getRowLength(x)) && (grid.get(x, y) == '.'  || grid.get(x, y) == 'k' || (x == player.getX() && y == player.getY()))); 
       
    }
}
