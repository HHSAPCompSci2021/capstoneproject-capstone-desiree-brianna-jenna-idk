import processing.core.PApplet;

/*
 * Player is the protagonist of the game that the user controls. The objective of the Player is to catch naM-caP, unless naM-caP is in its superpower phase, and earn as many points as possible
 * 
 * @author Brianna Wang
 */
public class Player {
	private String type;
	private int score;
	
	/*
	 * Constructs a Player and initiates Player type to "" and the score to 0
	 */
	public Player() {
		type="";
		score=0;
	}
	
	/*
	 * Moves the Player one space forward in the direction in it is facing
	 */
	public void move() {
		
	}
	
	/*
	 * turns the Player 90 degrees
	 */
	public void turn() {
		
	}
	
	/*
	 * Eats the fruit that is on the same space as it
	 */
	public void eatFruit(Fruit f) {
		
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
