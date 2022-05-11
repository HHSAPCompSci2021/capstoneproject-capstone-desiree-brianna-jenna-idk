import java.awt.event.KeyEvent;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * draws the surface
 * 
 * @author Desiree, Jenna
 *
 */
public class DrawingSurface extends PApplet {

	// When you progress to a new prompt, modify this field.
	// private Image board;
	protected boolean[][] grid;
	private boolean startScreen, playScreen, endScreen;
	private Map map;
	private NamCap namCap;
	private Player player;

	/**
	 * Construct an empty 2D array with some default dimensions.
	 */
	public DrawingSurface() {
		startScreen = true;
		playScreen = false;
		endScreen = false;
		map = new Map();
		player = new Player(10, 10);
		grid = new boolean[20][15];
	}

	public void setup() {
		smooth();
		namCap = new NamCap(loadImage("NamCap.png"), 50, 50);
	}

	/**
	 * draws things
	 */
	public void draw() {
		if (startScreen) {
			background(0);
			imageMode(CENTER);
			PImage title = loadImage("title.png"); // change image later
			image(title, 400, 100, width / 2, height / 2);

			// character select

			// map select

			// fruit select

			// play button
			PImage play = loadImage("play.png");
			image(play, 400, 500, width / 5, height / 10);
			System.out.println(width / 5);
			System.out.println(height / 10);
		}

		else if (playScreen) {
			background(0);

			if (map != null) {
				map.draw(this, 0, 0, width, height);
			}

			fill(255);
			textSize(20);
			text("POINTS: ", 50, 30);
			text("HIGHSCORE: ", 630, 30);

			// namcap
			namCap.draw(this);

			// lives on bottom left
		}

		else if (endScreen) {
			background(0);
			fill(255);
			textSize(50);
			textAlign(CENTER);
			text("GAME OVER", 400, 100);

			// put image of selected ghost and put an image of teardrop over their eye

			textAlign(LEFT);
			textSize(20);
			text("POINTS: ", 500, 320);
			text("HIGHSCORE: ", 500, 340);

		}
	}

	/**
	 * moves the player based on the keys pressed, using the up, down, left, right arrow keys
	 * if player attempts to go out of bounds, player is stopped
	 */
	public void keyPressed() {
		int curX = player.getX();
		int curY = player.getY();
		if (curX < grid[0].length) {
			if (keyCode == KeyEvent.VK_UP) {
				player.setY(--curY);
//				System.out.println("y minus 1");
//				System.out.println(curY - 1);
			} else if (keyCode == KeyEvent.VK_DOWN) {
				player.setY(++curY);
				System.out.println("y plus 1");
//				System.out.println(curY + 1);
			} else if (keyCode == KeyEvent.VK_LEFT) {
				player.setX(--curX);
//				System.out.println("x minus 1");
//				System.out.println(curX - 1);
			} else if (keyCode == KeyEvent.VK_RIGHT) {
				player.setX(++curX);
				System.out.println("x plus 1");
//				System.out.println(curX + 1);
			}
			if (curX < 0) {
				curX = 0;
				player.setX(curX);
//				System.out.println("notchangedX = 0");
//				System.out.println(player.getX());
			}
			if (curX < 0) {
				curX = 0;
				player.setX(curX);
				System.out.println("notchangedX");
				System.out.println(player.getX());
			}
			if (curY < 0) {
				curY = 0;
				player.setY(curY);
//				System.out.println("notchangedY = 0");
//				System.out.println(player.getY());
			}
			if (curY > grid.length) {
				curY = grid.length;
				player.setY(curY);
//				System.out.println("notchangedY = length");
//				System.out.println(player.getY());
			}
			System.out.printf("Player coordinates: (%d, %d)%n", player.getX(), player.getY());
		}
	}

	/**
	 * will be used as key pressed for the player to move
	 */
	public void mousePressed() {
		if (startScreen) {
			// play button
			if (mouseX > 300 && mouseX < 500 && mouseY > 500 && mouseY < 550) // change coords bc play button changed
			{
				startScreen = false;
				playScreen = true;
			}
		}

		// for testing purposes only
		else if (playScreen) {
			playScreen = false;
			endScreen = true;
		}

		else if (endScreen) {
			endScreen = false;
			// depending on if the retry button is clicked or the exit button is clicked,
			// change screens
		}

	}
}
