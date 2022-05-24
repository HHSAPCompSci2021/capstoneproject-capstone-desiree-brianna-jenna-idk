import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * Strawberry awards the player 50 points if eaten.
 * 
 * @author Brianna, Jenna, Desiree
 *
 */
public class Strawberry extends Fruit {

	private PImage strawberry;

	/**
	 * Constructs a Strawberry with a set row and column.
	 * 
	 * @param img The image used for the strawberry.
	 * @param x   The row of the kiwi's location.
	 * @param y   The column of the kiwi's location.
	 */
	public Strawberry(PImage img, int y, int x) {
		super(y, x);
		strawberry = img;
	}

	/**
	 * Draws a strawberry.
	 * 
	 * @param g The PApplet used to draw.
	 */
	public void draw(PApplet g) {
		g.image(strawberry, getX(), getY(), 35, 35);
	}

}
