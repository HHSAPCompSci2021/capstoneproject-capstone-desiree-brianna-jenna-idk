import java.util.Timer;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Player is the protagonist of the game that the user controls. The objective of the Player is to catch naM-caP, unless naM-caP is in its superpower phase, and earn as many points as possible
 * 
 * @author Brianna Wang
 */
public class Player {
	private PImage img;
	private String type;
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
	 * Constructs a Player and initiates Player type to "" and the score to 0
	 * x and y should be center of grid
	 */
	public Player(PImage img, int x, int y, Map m) {
		this.img = img;
		xgrid = x;
		xi=x;
		ygrid = y;
		yi=y;
		type="";
		score=0;
		direction=-1;
		step=30;
		map=m;
		lives=3;
	}
	
	/**
	 * Draws the player.
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
	 * Sets the direction of the player
	 * @param dir the angle of the player's direction (0 is the +x axis)
	 */
	public void setDirection(int dir) {
		direction=dir;
	}
	
	public int getDirection() {
		return direction;
	}
	
	/**
	 * Eats the fruit that is on the same space as it
	 */
	public void eatFruit(Fruit f) {
		
	}

	/**
	 * Removes a life from the Player
	 */
	public void loseLife() {
		lives--;
		reset();
	}
	
	/**
	 * Resets the Player to the starting position
	 */
	public void reset() {
		setX(xi);
		setY(yi);
		direction=-1;
	}
	
	/**
	 * Gets the number of lives
	 * @return number of lives
	 */
	public int getLives() {
		return lives;
	}
	
	/**
	 * Resets the number of lives back to 3
	 */
	public void resetLives() {
		lives=3;
	}
	
	/**
	 * Gets the x value of the Player's location
	 * @return x value of Player's location
	 */
	public int getX() {
		return xgrid;
	}
	
	/**
	 * Gets the y value of the Player's location
	 * @return y value of the Player's location
	 */
	public int getY() {
		return ygrid;
	}
	
	/**
	 * Sets the x value of the Player's location
	 * @param xval new x value
	 */
	public void setX(int xval) {
		xgrid = xval;
	}
	
	/**
	 * Sets the y value of the Player's location
	 * @param yval new y value
	 */
	public void setY (int yval) {
		ygrid = yval;
	}
	
	/**
	 * Determines if the Player and NamCap are at the same spot
	 * @param n NamCap
	 * @return true if at same location, false if not
	 */
	public boolean atSameLocation(NamCap n) {
		if(n.getX()/30==xgrid/30 && n.getY()/30==ygrid/30) {
			return true;
		}
		return false;
	}
	
	/**
	 * Increases the Player's score by a certain amount
	 * @param points amount of points to add to the score
	 */
	public void increaseScore(int points) {
		score+=points;
	}
}
