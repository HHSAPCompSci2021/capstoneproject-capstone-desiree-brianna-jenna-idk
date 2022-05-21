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
	private int x, y, xi, yi, direction;
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
		if(!hasKiwi)
		{
			Coordinate p = getKiwiPath(y / 30, x / 30, grid);
			while(p.getParent() != null) {
				//System.out.println("here");
				 fruitLoc.add(p);
				 p = p.getParent();
		     }
			
			//System.out.println(fruitLoc)
			 x = fruitLoc.get(fruitLoc.size() - 1).y * 30 + 15;
			 y = fruitLoc.get(fruitLoc.size() - 1).x * 30 + 15;
		}

		else if(hasKiwi)
		{
			 
			 Coordinate p2 = getPlayerPath(x / 30, y / 30, grid, player);
			 while(p2.getParent() != null)
			 {
				 System.out.println(playerLoc);
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
    
    /**
     * 
     * @param x
     * @param y
     * @param grid
     * @param player
     * @return
     */
    public boolean isFree2(int x, int y, Map grid, Player player) {
    	//System.out.println("x: " + x + ", y: " + y + ", playerx: " + player.getX() / 30 + ", playery: " + player.getY() / 30);
        return((x >= 0 && x < grid.getLength()) && (y >= 0 && y < grid.getRowLength(x)) && (grid.get(x, y) == '.'  || grid.get(x, y) == 'k' || (x == player.getX() && y == player.getY()))); 
       
    }
}
