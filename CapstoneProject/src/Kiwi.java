import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * Kiwi gives naM-caP the ability to hunt down the player.
 * 
 * @author Jenna Wang
 *
 */
public class Kiwi extends Fruit {
	
	private int x, y;
	private PImage kiwi;
	
	/**
	 * Constructs a Kiwi with a set row and column.
	 * @param row The row of the kiwi's location.
	 * @param col The column of the kiwi's location.
	 */
	public Kiwi(PImage img, int x, int y)
	{
		super(x, y);
		kiwi = img;
	}
	
	/**
	 * Draws a kiwi.
	 * @param g The PApplet used to draw.
	 */
	public void draw(PApplet g)
	{
		g.image(kiwi, x, y, 30, 30);
	}
	
	
	
	/**
	 * Kiwi gives naM-caP the ability to eat the player.
	 */
	public void useAbility()
	{
		
	}
	
}
