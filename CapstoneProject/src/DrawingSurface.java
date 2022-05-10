import processing.core.PApplet;
import processing.core.PImage;

/**
 * draws the surface
 * @author desiree
 *
 */
public class DrawingSurface extends PApplet{

	// When you progress to a new prompt, modify this field.
	//private Image board;
	protected boolean[][] grid;
	private boolean startScreen, playScreen, endScreen;
	private Map map;
	private Kiwi kiwi;
	private NamCap namcap;
	private Player player;
	private Fruit fruit;
	
	/**
	 * Construct an empty 2D array with some default dimensions.
	 */
	public DrawingSurface() {
		startScreen = true;
		playScreen = false;
		endScreen = false;
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
		if(startScreen)
		{
			background(0);   
			PImage img = loadImage("title.png"); // change image later
			image(img, 200, -50, width/2, height/2);
			
			// character select
			
			// map select
			
			// fruit select
			
			// play button
			fill(255);
			rect(300, 500, 200, 50);
			fill(0);
			textAlign(CENTER);
			textSize(40);
			text("PLAY", 400, 540);
		}
			
		else if(playScreen)
		{
			background(0);
			
			
			if (map != null) {
				map.draw(this, 0, 0, width, height);
			}
			
			fill(255);
			textSize(20);
			text("POINTS: ", 50, 30);
			text("HIGHSCORE: ", 630, 30);
			
			// lives on bottom left
		}
		
		else if(endScreen)
		{
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
	 * will be used as key pressed for the player to move
	 */
	public void mousePressed() {
		if(startScreen)
		{
			// play button
			if(mouseX > 300 && mouseX < 500 && mouseY > 500 && mouseY < 550)
			{
				startScreen = false;
				playScreen = true;
			}
		}
		
		// for testing purposes only
		else if(playScreen)
		{
			playScreen = false;
			endScreen = true;
		}
		
		else if(endScreen)
		{
			endScreen = false;
			// depending on if the retry button is clicked or the exit button is clicked, change screens
		}
		
	}
}
	
	


