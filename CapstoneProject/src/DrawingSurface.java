
import java.awt.Point;

public class DrawingSurface{

	// When you progress to a new prompt, modify this field.
	//private Image board;
	protected int[][] grid;
	private Kiwi kiwi;
	private NamCap namcap;
	private Player player1, player2, player3, player4;
	private PowerUp powerup1;
	
	/**
	 * Construct an empty 2D array with some default dimensions.
	 */
	public DrawingSurface() {
		grid = new int[20][20];
		kiwi = new Kiwi (5, 5);
		namcap = new NamCap();
		player1 = new Player();
		powerup1 = new PowerUp(10, 10);
	}

	public void draw() { 
		boolean wall = true;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (wall) {
					grid[i][j] = -1;
				} else {
					grid[i][j] = 1;
				}
			}
		}
		kiwi.draw();
		namcap.draw();
		player1.draw();
		powerup1.draw();
	}
	
	
	public void mousePressed() {
	}
}
	
	


