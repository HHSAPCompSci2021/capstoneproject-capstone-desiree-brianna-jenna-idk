import java.awt.event.KeyEvent;
import processing.core.PApplet;
import processing.core.PFont;
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
	private PImage title, charFrame, play, ghost, arrow, tear, retry, home;
//	private PFont emulogic;
	private String whichGhost;
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
		grid = new boolean[28][30];
		whichGhost = "blinky";
	}

	public void setup() {
		namCap = new NamCap(loadImage("img/NamCap.png"), 50, 50);
		title = loadImage("img/title.png"); // change image later
		charFrame = loadImage("img/frame.png");
		play = loadImage("img/play.png");
		ghost = loadImage("img/blinky.png");
		arrow = loadImage("img/triangle.png");
		tear = loadImage("img/tear.png");
		retry = loadImage("img/retry.png");
		home = loadImage("img/home.png");
	//	emulogic = createFont("Emulogic-zrEw.ttf", 18);
	}

	/**
	 * draws things
	 */
	public void draw() {
		//mac
	//	textFont(emulogic);
		
		imageMode(CENTER);
		
		if (startScreen) {
			background(0);
			image(title, width / 2, height / 8, width / 3, height / 9);

			// character select
			textAlign(CENTER);
			text("Character", width / 5, height / 4 + height / 25);
			
			image(charFrame, width / 5, height / 2 + height / 20, width / 4, height / 2);
			image(ghost, width / 5, height / 2, width / 7, height / 5);
			image(arrow, width / 5 + width / 6, height / 2, width / 11, height / 8);
			
			text(whichGhost, width / 5, (height / 3) * 2);

			// map select

			// fruit select

			// play button
			image(play, width / 2, height - height / 8, width / 5, height / 10);
		}

		else if (playScreen) {
			background(0);

			if (map != null) {
				map.draw(this, 0, 0, width / 2, height / 2);
			}

			fill(255);
			textSize(15);
			textAlign(LEFT);
			text("POINTS: ", width / 40, height / 20);
			
			textAlign(RIGHT);
			text("HIGHSCORE: ", width - width / 40, height / 20);

			// namcap
			namCap.draw(this);
			
			player = new Player(loadImage("img/" + whichGhost + ".png"), 93, 93);
			player.draw(this);

			// lives on bottom left
		}

		else if (endScreen) {
			background(0);
			fill(255);
			textSize(50);
			textAlign(CENTER);
			fill(255, 255, 0);
			text("GAME OVER", 400, 100);

			
			// selected ghost with tear
			image(ghost, width / 4, height / 2, width / 3, height / 2);
			image(tear, width / 5 + width / 10, height / 2 + height / 13, width / 15, height / 10);
			
			textAlign(LEFT);
			textSize(20);
			fill(255);
			text("POINTS: ", width / 2 - width / 20, height / 2 - height / 10);
			text("HIGHSCORE: ", width / 2 - width / 20, height / 2);
			
			// buttons
			imageMode(CORNER);
			image(home, width / 2 - width / 20, height / 2 + height / 10, width / 5, height / 10);
			image(retry, width / 2 + width / 5, height / 2 + height / 10, width / 5, height / 10);

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
				player.setY(20 - curY);
			} else if (keyCode == KeyEvent.VK_DOWN) {
				player.setY(20 + curY);
			} else if (keyCode == KeyEvent.VK_LEFT) {
				player.setX(20 - curX);
			} else if (keyCode == KeyEvent.VK_RIGHT) {
				player.setX(20 + curX);
			}
			if (curX < 0) {
				curX = 0;
				player.setX(curX);
			}
			if (curX > grid[0].length) {
				curX = grid[0].length;
				player.setX(curX);
			}
			if (curY < 0) {
				curY = 0;
				player.setY(curY);
			}
			if (curY > grid.length) {
				curY = grid.length;
				player.setY(curY);
			}
			System.out.printf("Player coordinates: (%d, %d)%n", player.getX(), player.getY());
		}
	}

	/**
	 * Changes screens according to what button the player clicks.
	 */
	public void mousePressed() {
		if (startScreen) {
			// play button
			if (mouseX > 320 && mouseX < 480 && mouseY > 471 && mouseY < 579) // change coords for new button
			{
				startScreen = false;
				playScreen = true;
			}
			
			// right arrow
			if(mouseX > (width / 5 + width / 6) - width / 11 && mouseX < (width / 5 + width / 6) + width / 11 && mouseY > height / 2 - height / 8 && mouseY < height / 2 + height / 8)
			{
				if(whichGhost.equals("blinky"))
				{
					whichGhost = "pinky";
					ghost = loadImage("img/pinky.png");
					image(ghost, width / 5, height / 2, width / 7, height / 5);
				}
				
				else if(whichGhost.equals("pinky"))
				{
					whichGhost = "inky";
					ghost = loadImage("img/inky.png");
					image(ghost, width / 5, height / 2, width / 7, height / 5);
				}
				
				else if(whichGhost.equals("inky"))
				{
					whichGhost = "clyde";
					ghost = loadImage("img/clyde.png");
					image(ghost, width / 5, height / 2, width / 7, height / 5);
				}
				
				else if(whichGhost.equals("clyde"))
				{
					whichGhost = "blinky";
					ghost = loadImage("img/blinky.png");
					image(ghost, width / 5, height / 2, width / 7, height / 5);
				}
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
			
			// home button
			if(mouseX > width / 2 - width / 20 - width / 5 && mouseX < width / 2 - width / 20 + width / 5 && mouseY > height / 2 && mouseY < height / 2 + height / 5)
			{
				startScreen = true;
			}
			
			if(mouseX > width / 2 && mouseX < width / 2 + width / 5 + width / 5 && mouseY > height / 2 && mouseY < height / 2 + height / 5)
			{
				playScreen = true;
			}
		}

	}
}
