import java.awt.event.KeyEvent;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

/**
 * Draws the graphics of the game.
 * 
 * @author Desiree, Jenna
 *
 */
public class DrawingSurface extends PApplet {

	// private Image board;
	private boolean startScreen, playScreen, endScreen;
	private PImage title, play, ghost, rightarrow, tear, retry, home, kiwi, strawberry, life1, life2;
	private PFont emulogic;
	private String whichGhost;
	private Map map;
	private NamCap namCap;
	private Player player;
	private int playerCount, namCapCount, kiwiCount;

	/**
	 * Declares the values for specific variables.
	 */
	public DrawingSurface() {
		startScreen = true;
		playScreen = false;
		endScreen = false;
		map = new Map("map/test1.txt");
		whichGhost = "blinky";
	}

	/**
	 * Sets up all of the images and font for the graphics.
	 */
	public void setup() {
		title = loadImage("img/title.png");
		namCap = new NamCap(loadImage("img/namcap/right.png"), 75, 135);
		play = loadImage("img/play.png");
		ghost = loadImage("img/blinky.png");
		rightarrow = loadImage("img/triangle.png");
		tear = loadImage("img/tear.png");
		retry = loadImage("img/retry.png");
		home = loadImage("img/home.png");
		kiwi = loadImage("img/kiwi.png");
		strawberry = loadImage("img/strawberry.png");
		emulogic = createFont("Emulogic-zrEw.ttf", 18);
		kiwiCount=1;
	}

	/**
	 * Draws all the graphics.
	 */
	public void draw() {
		playerCount++;
		namCapCount++;
		textFont(emulogic);
		imageMode(CENTER);
		
		if (startScreen) {
			background(0);
			image(title, width / 2, height / 8, width / 3, height / 9);

			// character select
			textAlign(CENTER);
			text("Character", width / 6, height / 2 - height / 7);
			image(ghost, width / 6, height / 2, width / 7, height / 5);
			image(rightarrow, width / 6 + width / 8, height / 2, width / 11, height / 8);
			text(whichGhost, width / 6, height / 2 + height / 6);

			// map select
			text("Map", width/ 2, height / 4 + height / 10);
			
			// fruit select
			text("Fruit", width - width / 6, height / 4 + height / 10);
			
			textSize(14);
			textAlign(LEFT);
			// kiwi
			image(kiwi, width - width / 3 + width / 20, height / 4 + height / (float)6.5, width/20, height/15);
			text("Kiwi", width - width / 4, height / 4 + height / 6);
			
			// strawberry
			image(strawberry, width - width / 3 + width / 20, height / 2 - height / 25, width/30, height/20);
			text("strawberry", width - width / 4, height / 2 - height / 30);
			
			// play button
			image(play, width / 2, height - height / 8, width / 5, height / 10);
			player = new Player(loadImage("img/" + whichGhost + ".png"), 45, 45, map);
			life1 = loadImage("img/" + whichGhost + ".png");
			life2 = loadImage("img/" + whichGhost + ".png");
		}

		else if (playScreen) {
			background(0);

			if (map != null) {
				map.draw(this, 0, 0, width/3, height);
			}

			fill(255);
			textSize(15);
			textAlign(LEFT);
			text("POINTS: "+player.getScore(), width / 40, height / 28);
			
			textAlign(RIGHT);
			text("HIGHSCORE: " + player.getHighScore(), width - width / 40, height / 28);
			
			//fruit
			ArrayList<Kiwi> kiwis = map.getKiwis();
			for(int i = 0; i < kiwis.size(); i++)
			{
				if(namCap.atSameLocation(kiwis.get(i)))
				{
					namCap.eatFruit(kiwis.get(i));
					kiwiCount=1;
					kiwis.get(i).remove(map);
					kiwis.remove(i);
				}
			}
			if(namCap.hasEatenKiwi()) {
				kiwiCount++;
				if(kiwiCount%100==0) {
					namCap.wearOutKiwi();
					if(kiwis.size()==0) {
						map = new Map("map/test1.txt");
						player.reset();
						namCap.reset();
					}
				}
			}			
			// namcap
			if(namCapCount%8==0)
			{
				namCap.act(map, player);
				if(player.atSameLocation(namCap) && !namCap.hasEatenKiwi()) {
					//System.out.println("player eats namCap");
					player.increaseScore(50);
					namCap.reset();
				}else if(player.atSameLocation(namCap) && namCap.hasEatenKiwi()) {
					//System.out.println("namCap eats player");
					player.loseLife();
					namCap.reset();
				}
			}
			
			namCap.draw(this);
			
			//makes player move by grid and slower
			if (playerCount%8==0) {
				player.move();
				if(player.atSameLocation(namCap) && !namCap.hasEatenKiwi()) {
					//System.out.println("player eats namCap");
					player.increaseScore(50);
					namCap.reset();
				}else if(player.atSameLocation(namCap) && namCap.hasEatenKiwi()) {
					//System.out.println("namCap eats player");
					player.loseLife();
					namCap.reset();
				}
			}
			
			player.draw(this);

			// lives on bottom left
			if(player.getLives() >= 2)
			{
				image(life1, width / 22, height - height / 10, width / 30, width / 30);
				if(player.getLives() == 3)
				{
					image(life2, (width / 22) * 2, height - height / 10, width / 30, width / 30);
				}
			}
			
			if(player.getLives()==0) {
				endScreen=true;
				playScreen=false;
			}
		}
		
		else if (endScreen) {
			background(0);
			textSize(50);
			textAlign(CENTER);
			fill(255, 255, 0);
			
			if(player.getHighScore() < player.getScore())
			{
				player.setHighScore(player.getScore());
			}
			
			text("GAME OVER", 400, 100);

			// selected ghost with tear
			image(ghost, width / 4, height / 2, width / 3, height / 2);
			image(tear, width / 5 + width / 10, height / 2 + height / 13, width / 15, height / 10);
			
			textAlign(LEFT);
			textSize(20);
			fill(255);
			text("POINTS: " + player.getScore(), width / 2 - width / 15, height / 2 - height / 10);
			text("HIGHSCORE: " + player.getHighScore(), width / 2 - width / 15, height / 2);
			
			// buttons
			imageMode(CORNER);
			image(home, width / 2 - width / 20, height / 2 + height / 10, width / 5, height / 10);
			image(retry, width / 2 + width / 5, height / 2 + height / 10, width / 5, height / 10);
		}
	}
	
	/**
	 * Moves the player based on the keys pressed, using the up, down, left, right arrow keys.
	 */
	public void keyPressed() {
		if (keyCode == KeyEvent.VK_UP) {
			player.setDirection(90);
		} else if (keyCode == KeyEvent.VK_DOWN) {
			player.setDirection(270);
		} else if (keyCode == KeyEvent.VK_LEFT) {
			player.setDirection(180);
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			player.setDirection(0);
		}
	}

	/**
	 * Changes screens according to what button the player clicks.
	 */
	public void mousePressed() {
		if (startScreen) {
			// play button
			if (mouseX > width / 2 - width / 5 && mouseX < width / 2 + width / 5 && mouseY > height - height / 8 - height / 10 && mouseY < height - height / 8 + height / 10) // change coords for new button
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
				}
				
				else if(whichGhost.equals("pinky"))
				{
					whichGhost = "inky";
					ghost = loadImage("img/inky.png");
				}
				
				else if(whichGhost.equals("inky"))
				{
					whichGhost = "clyde";
					ghost = loadImage("img/clyde.png");
				}
				
				else if(whichGhost.equals("clyde"))
				{
					whichGhost = "blinky";
					ghost = loadImage("img/blinky.png");
				}
			}
			
			// checkboxes for fruit
		
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
				player.setScore(0);
				playScreen = true;
				player.resetLives();
				player.reset();
				namCap.reset();
			}
		}

	}
}
