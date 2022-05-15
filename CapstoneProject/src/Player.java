import processing.core.PApplet;
import processing.core.PImage;

/**
 * Player is the protagonist of the game that the user controls. The objective of the Player is to catch naM-caP, unless naM-caP is in its superpower phase, and earn as many points as possible
 * 
 * @author Brianna Wang
 */
public class Player {
	private PImage img;
	private int score;
	private int xgrid; //x value of player on grid
	private int ygrid; //y value of player on grid
	private int xi;
	private int yi;
	private int direction;
	private int step;
	private Map map;
	private int lives;
	

	/**
	 * Constructs a Player and initiates Player type to "" and the score to 0.
	 * X and Y should be the center of grid.
	 * 
	 * @param img The Player's image.
	 * @param x X-value of the Player. 
	 * @param y Y-value of the Player.
	 * @param m The Map the Player is on.
	 */
	public Player(PImage img, int x, int y, Map m) {
		this.img = img;
		xgrid = x;
		xi=x;
		ygrid = y;
		yi=y;
		score=0;
		direction=-1;
		step=30;
		map=m;
		lives=3;
	}
	
	/**
	 * Draws the player.
	 * 
	 * @param g The PApplet used to draw.
	 */
	public void draw(PApplet g)
	{
		g.image(img, xgrid, ygrid, 30, 30);
	}
	
	/**
	 * Moves the Player one space forward in the direction it is facing
	 */
	public void move() {
		int newX=xgrid;
		int newY=ygrid;
		if(direction==0) {
			newX+=step;
		}else if(direction==90) {
			newY-=step;
		}else if(direction==180) {
			newX-=step;
		}else if(direction==270) {
			newY+=step;
		}
		if(newX<0) {
			newX+=885;
		}else if (newX>=885) {
			newX-=885;
		}else if(newY<0) {
			newY+=585;
		}else if (newY>=585) {
			newY-=585;
		}
		if(map.isValidLocation(newX,newY)) {
			xgrid=newX;
			ygrid=newY;
		}
	}
 
	/**
	 * Sets the direction of the player.
	 * 
	 * @param dir The angle of the player's direction (0 is the +x axis).
	 */
	public void setDirection(int dir) {
		direction=dir;
	}
	
	/**
	 * @return The Player's direction.
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * Eats the fruit that is on the same space as it
	 * 
	 * @param f The Fruit the Player eats.
	 */
	public void eatFruit(Fruit f) {
		
	}

	/**
	 * Removes a life from the Player.
	 */
	public void loseLife() {
		lives--;
		reset();
	}
	
	/**
	 * Resets the Player to the starting position and direction.
	 */
	public void reset() {
		setX(xi);
		setY(yi);
		direction=-1;
	}
	
	/**
	 * @return The number of lives the Player has.
	 */
	public int getLives() {
		return lives;
	}
	
	/**
	 * Resets the number of lives back to 3.
	 */
	public void resetLives() {
		lives=3;
	}
	
	/**
	 * @return X-value of the Player's location.
	 */
	public int getX() {
		return xgrid;
	}
	
	/**
	 * @return Y-value of the Player's location.
	 */
	public int getY() {
		return ygrid;
	}
	
	/**
	 * Sets the X-value of the Player's location.
	 * 
	 * @param xval The new X-value.
	 */
	public void setX(int xval) {
		xgrid = xval;
	}
	
	/**
	 * Sets the Y-value of the Player's location.
	 * 
	 * @param yval The new Y-value.
	 */
	public void setY (int yval) {
		ygrid = yval;
	}
	
	/**
	 * Determines if the Player and naM-caP are at the same spot.
	 * 
	 * @param n naM-caP.
	 * @return true if at same location, false if not.
	 */
	public boolean atSameLocation(NamCap n) {
		if(n.getX()/30==xgrid/30 && n.getY()/30==ygrid/30) {
			return true;
		}
		return false;
	}
	
	/**
	 * Increases the Player's score by a certain amount.
	 * 
	 * @param points The amount of points to add to the score.
	 */
	public void increaseScore(int points) {
		score+=points;
		reset();
	}
	
	/**
	 * @return The score of the Player.
	 */
	public int getScore() {
		return score;
	}
}
