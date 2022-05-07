import processing.core.PApplet;

/**
 * draws the surface
 * @author desiree
 *
 */
public class DrawingSurface extends PApplet{

	// When you progress to a new prompt, modify this field.
	//private Image board;
	protected boolean[][] grid;
	private Map map;
	private Kiwi kiwi;
	private NamCap namcap;
	private Player player;
	private Fruit fruit;
	
	/**
	 * Construct an empty 2D array with some default dimensions.
	 */
	public DrawingSurface() {
		map = new Map();
		kiwi = new Kiwi (5, 5);
		namcap = new NamCap();
		player = new Player();
		fruit = new Fruit(10, 10);
	}

	/**
	 * draws things
	 */
	public void draw() { 
		background(255);   
		fill(0);
		textAlign(LEFT);
		textSize(12);
			

		if (map != null) {
			map.draw(this, 0, 0, height, height);
		}
		
//		kiwi.draw();
//		namcap.draw();
//		player1.draw();
//		fruit1.draw();
		
	}
	
	/**
	 * will be used as key pressed for the player to move
	 */
	public void mousePressed() {
	}
}
	
	


