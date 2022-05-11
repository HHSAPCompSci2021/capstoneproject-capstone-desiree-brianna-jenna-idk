import processing.core.PApplet;

/**
 * Player is the protagonist of the game that the user controls. The objective of the Player is to catch naM-caP, unless naM-caP is in its superpower phase, and earn as many points as possible
 * 
 * @author Brianna Wang
 */
public class Player {
	private String type;
	private int score;
	private int xgrid; //x value of player on grid
	private int ygrid; //y value of player on grid
	
	/**
	 * Constructs a Player and initiates Player type to "" and the score to 0
	 * x and y should be center of grid
	 */
	public Player(int x, int y) {
		xgrid = x;
		ygrid = y;
		type="";
		score=0;
	}
	
	/**
	 * Moves the Player one space forward in the direction it is facing
	 */
	public void move() {
		
	}
	
	/**
	 * Turns the Player 90 degrees
	 */
	public void turn() {
		
	}
	
	/**
	 * Eats the fruit that is on the same space as it
	 */
	public void eatFruit(Fruit f) {
		
	}
	
	public int getX() {
		return xgrid;
	}
	
	public int getY() {
		return ygrid;
	}
	
	public void setX(int xval) {
		xgrid = xval;
	}
	
	public void setY (int yval) {
		ygrid = yval;
	}
	/**
	 * (Graphical UI)
	 * Draws the Player on a PApplet.
	 * The specific way the grid is depicted is up to the coder.
	 * 
	 * @param marker The PApplet used for drawing.
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 */
	public void draw(PApplet marker, float x, float y, float width, float height) {
		
	}
}
