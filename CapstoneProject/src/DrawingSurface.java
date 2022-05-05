import processing.core.PApplet;

public class DrawingSurface extends PApplet{

	// When you progress to a new prompt, modify this field.
	//private Image board;
	protected boolean[][] grid;
	private Map map;
	private Kiwi kiwi;
	private NamCap namcap;
	private Player player1, player2, player3, player4;
	private PowerUp powerup1;
	
	/**
	 * Construct an empty 2D array with some default dimensions.
	 */
	public DrawingSurface() {
		map = new Map();
		kiwi = new Kiwi (5, 5);
		namcap = new NamCap();
		player1 = new Player();
		powerup1 = new PowerUp(10, 10);
	}

	public void draw() { 
		background(255);   
		fill(0);
		textAlign(LEFT);
		textSize(12);
			

		if (map != null) {
			map.draw(this, 0, 0, height, height);
		}
		
		kiwi.draw();
		namcap.draw();
		player1.draw();
		powerup1.draw();
		
		
	}
	
	public void mousePressed() {
	}
}
	
	


