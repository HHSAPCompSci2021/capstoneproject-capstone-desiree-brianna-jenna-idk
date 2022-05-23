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
	private int x, y, xi, yi;
	
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
		if(!hasKiwi)
		{
			Coordinate p = getKiwiPath(y / 30, x / 30, grid, player);
			ArrayList<Coordinate> fruitLoc = new ArrayList<Coordinate>();
			while(p.getParent() != null) {
				 fruitLoc.add(p);
				 p = p.getParent();
		     }
			 x = fruitLoc.get(fruitLoc.size() - 1).y * 30 + 15;
			 y = fruitLoc.get(fruitLoc.size() - 1).x * 30 + 15;
		}

		else if(hasKiwi)
		{
			 Coordinate p2 = getPlayerPath(y / 30, x / 30, grid, player);
			 ArrayList<Coordinate> playerLoc = new ArrayList<Coordinate>();
			 while(p2.getParent() != null)
			 {
				 playerLoc.add(p2);
				 p2 = p2.getParent();
			 }
			 x = playerLoc.get(playerLoc.size() - 1).y * 30 + 15;
			 y = playerLoc.get(playerLoc.size() - 1).x * 30 + 15;
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
		hasKiwi = false;
	}
	
	/**
	 * @return true if naM-caP has eaten a kiwi, false if not.
	 */
	public boolean hasEatenKiwi() {
		return hasKiwi;
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
		}
	}
	
	/**
	 * Changes hasKiwi to false
	 */
	public void setKiwiFalse() {
		hasKiwi=false;
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
	 * Uses BFS to find a Kiwi on the map.
	 * 
     * @param x The starting x position on the grid.
     * @param y The starting y position on the grid.
     * @param grid The Map.
     * @param player The Player.
     * @return A Coordinate of the Kiwi's location with parent Coordinates that form a path.
	 */
    public Coordinate getKiwiPath(int x, int y, Map grid, Player player) {

        Map map = new Map();
    	Queue<Coordinate> q = new LinkedList<Coordinate>();
        q.add(new Coordinate(x,y, null));
        
        for(int i = 0; i < grid.getLength(); i++)
        {
        	for(int j = 0; j < grid.getRowLength(i); j++)
        	{
        		map.set(i, j, grid.get(i, j));
        	}
        }

        while(!q.isEmpty()) {
            Coordinate p = q.remove();

            if (map.get(p.x, p.y) == 'k') {
                return p;
            }
            
            map.set(p.x, p.y, '!');

            if(isFree(p.x+1,p.y, map, player)) {
                Coordinate nextP = new Coordinate(p.x+1,p.y, p);
                q.add(nextP);
            }

            if(isFree(p.x-1,p.y, map, player)) {
                Coordinate nextP = new Coordinate(p.x-1,p.y, p);
                q.add(nextP);
            }

            if(isFree(p.x,p.y+1, map, player)) {
                Coordinate nextP = new Coordinate(p.x,p.y+1, p);
                q.add(nextP);
            }

             if(isFree(p.x,p.y-1, map, player)) {
                Coordinate nextP = new Coordinate(p.x,p.y-1, p);
                q.add(nextP);
            }
        }
        return null;
    }
    
    /**
     * Uses BFS to find the Player on the Map.
     * 
     * @param x The starting x position on the grid.
     * @param y The starting y position on the grid.
     * @param grid The Map.
     * @param player The Player.
     * @return A Coordinate of the Player's location with parent Coordinates that form a path.
     */
    public Coordinate getPlayerPath(int x, int y, Map grid, Player player) {

    	Map map = new Map();
    	Queue<Coordinate> q2 = new LinkedList<Coordinate>();
        q2.add(new Coordinate(x,y, null));

        for(int i = 0; i < grid.getLength(); i++)
        {
        	for(int j = 0; j < grid.getRowLength(i); j++)
        	{
        		map.set(i, j, grid.get(i, j));
        	}
        }
        
        while(!q2.isEmpty()) {
            Coordinate p = q2.remove();

            if(p.x == (player.getY()) / 30 && p.y == (player.getX()) / 30)
            {
            	return p;
            }

            map.set(p.x, p.y, '!');
            
            if(isFree2(p.x+1,p.y, map, player)) {
                Coordinate nextP = new Coordinate(p.x+1,p.y, p);
                q2.add(nextP);
            }

            if(isFree2(p.x-1,p.y, map, player)) {
                Coordinate nextP = new Coordinate(p.x-1,p.y, p);
                q2.add(nextP);
            }

            if(isFree2(p.x,p.y+1, map, player)) {
                Coordinate nextP = new Coordinate(p.x,p.y+1, p);
                q2.add(nextP);
            }

             if(isFree2(p.x,p.y-1, map, player)) {
                Coordinate nextP = new Coordinate(p.x,p.y-1, p);
                q2.add(nextP);
            }

        }
        return null;
    }

    /**
     * @param x The row number of the starting position.
     * @param y The column number of the starting position
     * @param grid The Map.
     * @param player The Player.
     * @return true if the location is in the boundaries of the Map and it contains either a path, or strawberry.
     */
    public boolean isFree(int x, int y, Map grid, Player player) {
    	//System.out.println("x: " + x + ", y: " + y + ", playerx: " + player.getX() / 30 + ", playery: " + player.getY() / 30);
    	// && x != player.getY() / 30 && y != player.getX() / 30
        return((x >= 0 && x < grid.getLength()) && (y >= 0 && y < grid.getRowLength(x)) && (grid.get(x, y) == '.' || grid.get(x, y) == 'k' || grid.get(x, y) == 's' || grid.get(x, y) == '*')); 
    	// return((x >= 0 && x < grid.getLength()) && (y >= 0 && y < grid.getRowLength(x)) && (grid.get(x, y) == '.' || grid.get(x, y) == 'k' || grid.get(x, y) == 's' )); 
    }
    
    /**
     * @param x The row number of the starting position.
     * @param y The column number of the starting position
     * @param grid The Map.
     * @param player The Player.
     * @return true if the location is in the boundaries of the Map and it contains either a path, strawberry, or Player.
     */
    public boolean isFree2(int x, int y, Map grid, Player player) {
    	//System.out.println("x: " + x + ", y: " + y + ", playerx: " + player.getX() / 30 + ", playery: " + player.getY() / 30);
    	//|| (x == player.getX() / 30 && y == player.getY() / 30)
    	return((x >= 0 && x < grid.getLength()) && (y >= 0 && y < grid.getRowLength(x)) && (grid.get(x, y) == '.' || grid.get(x, y) == 'k' || grid.get(x, y) == 's' || grid.get(x, y) == '*')); 
        //return((x >= 0 && x < grid.getLength()) && (y >= 0 && y < grid.getRowLength(x)) && (grid.get(x, y) == '.' || grid.get(x, y) == 'k' || grid.get(x, y) == 's')); 
       
    }
}
