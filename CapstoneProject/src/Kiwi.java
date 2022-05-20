import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * Kiwi gives naM-caP the ability to hunt down the player.
 *
 * @author Brianna, Jenna, Desiree
 */
public class Kiwi extends Fruit {
	
	private PImage kiwi;
	
	/**
	 * Constructs a Kiwi with a set row and column.
	 * 
	 * @param img The image used for the kiwi.
	 * @param x The row of the kiwi's location.
	 * @param y The column of the kiwi's location.
	 */
	public Kiwi(PImage img, int y, int x) {
		super(y, x);
		kiwi = img;
	}
	
	/**
	 * Draws a kiwi.
	 * 
	 * @param g The PApplet used to draw.
	 */
	public void draw(PApplet g) {
		g.image(kiwi, getX(), getY(), 50, 50);
	}
	
}
