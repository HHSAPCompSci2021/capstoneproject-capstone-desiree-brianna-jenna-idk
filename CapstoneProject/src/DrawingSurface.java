import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

/**
 * Draws the graphics of the game.
 * 
 * @author Brianna, Desiree, Jenna
 *
 */
public class DrawingSurface extends PApplet {

	// private Image board;
	private boolean startScreen, playScreen, endScreen, strawberryButton, choiceGhost;
	private PImage title, play, ghost, rightarrow, tear, retry, home, kiwi, strawberry, life1, life2, cross, mapImg, eyes;
	private PFont emulogic;
	private String whichGhost, input;
	private Map map;
	private NamCap namCap;
	private Player player;
	private int playerCount, namCapCount, kiwiCount, tempDir, mapNum;
	private ArrayList<Kiwi> kiwis;

	/**
	 * Declares the values for specific variables.
	 */
	public DrawingSurface() {
		startScreen = true;
		playScreen = false;
		endScreen = false;
		mapNum=1;
		whichGhost = "blinky";
		input = "";
		tempDir = -1;
		kiwis = new ArrayList<Kiwi>();
		strawberryButton = true;
		choiceGhost = false;
	}

	/**
	 * Sets up all of the images and font for the graphics.
	 */
	public void setup() {
		title = loadImage("img/title.png");
		// namCap = new NamCap(loadImage("img/namcap/right.png"), 845, 45);
		namCap = new NamCap(loadImage("img/namcap.png"), 225, 555);
		play = loadImage("img/play.png");
		ghost = loadImage("img/blinky.png");
		rightarrow = loadImage("img/triangle.png");
		tear = loadImage("img/tear.png");
		retry = loadImage("img/retry.png");
		home = loadImage("img/home.png");
		kiwi = loadImage("img/kiwi.png");
		strawberry = loadImage("img/strawberry.png");
		emulogic = createFont("Emulogic-zrEw.ttf", 18);
		cross = loadImage("img/cross.png");
		mapImg =loadImage("img/map1.png");
		kiwiCount = 1;
		eyes = loadImage("img/eyes.png");
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
			
			if(choiceGhost)
			{
				text("#" + input, width / 6, height / 2 + height / 5);
				if(input.matches("[0-9A-Fa-f]{6}"))
				{
					push();
					tint(Integer.valueOf(input.substring(0, 2), 16), Integer.valueOf(input.substring(2, 4), 16), Integer.valueOf(input.substring(4, 6), 16));
					image(ghost, width / 6, height / 2, width / 7, height / 5);
					pop();
					image(eyes, width / 6, height / 2, width / 7, height / 5);
				}
				
				else
				{
					image(ghost, width / 6, height / 2, width / 7, height / 5);
				}
			}

			else
			{
				image(ghost, width / 6, height / 2, width / 7, height / 5);
			}
			
			image(rightarrow, width / 6 + width / 8, height / 2, width / 11, height / 8);
			text(whichGhost, width / 6, height / 2 + height / 6);
			
			// map select
			text("Map", width / 2, height / 4 + height / 10);
			image(mapImg, width/2, height/2, width/5, height/5);
			text("#" + mapNum, width / 2, height / 2 + height / 6);
			image(rightarrow, width/2+width/8 , height / 2, width/11, height / 8);
			map = new Map("map/test"+mapNum+".txt");
			
			// fruit select
			text("Fruit", width - width / 6, height / 4 + height / 10);

			textSize(14);
			textAlign(LEFT);

			// kiwi
			image(kiwi, width - width / 3 + width / 20, height / 4 + height / (float) 6.5, width / 20, height / 15);
			text("Kiwi", width - width / 4, height / 4 + height / 6);

			// strawberry
			image(strawberry, width - width / 3 + width / 20, height / 2 - height / 25, width / 30, height / 20);
			text("strawberry", width - width / 4, height / 2 - height / 30);

			// play button
			image(play, width / 2, height - height / 8, width / 5, height / 10);
			
			if(choiceGhost)
			{
				if(input.matches("[0-9A-Fa-f]{6}"))
				{
					player = new Player(loadImage("img/" + whichGhost + ".png"),
							loadImage("img/eyes.png"),
							((int) map.getPlayerInitialLocation().getX() * 30) + 15,
							((int) map.getPlayerInitialLocation().getY() * 30) + 15, map);	
					player.setTint(input);
				}
			}
			
			else
			{
				player = new Player(loadImage("img/" + whichGhost + ".png"),
						null,
						((int) map.getPlayerInitialLocation().getX() * 30) + 15,
						((int) map.getPlayerInitialLocation().getY() * 30) + 15, map);
			}
			
			life1 = loadImage("img/" + whichGhost + ".png");
			life2 = loadImage("img/" + whichGhost + ".png");

			// checkboxes
			if (strawberryButton) {
				image(cross, width - width / 3 + width / 20, height / 2 - height / 25, width / 25, height / 20);
			}
		} else if (playScreen) {
			background(0);
			if (map != null) {
				map.draw(this, 0, 0, width / 3, height);
			}

			fill(255);
			textSize(15);
			textAlign(LEFT);
			text("POINTS: " + player.getScore(), width / 40, height / 28);

			textAlign(RIGHT);
			text("HIGHSCORE: " + player.getHighScore(), width - width / 40, height / 28);

			// kiwi
			ArrayList<Point> kiwiLoc = map.getKiwiLoc();
			int kiwiNum = 0;
			for (Point p : kiwiLoc) {
				Kiwi k = new Kiwi(kiwi, p.x * 30 + 15, p.y * 30 + 16);
				k.draw(this);
				kiwiNum++;
				if (kiwis.size() < kiwiNum) {
					kiwis.add(k);
				}
			}

			for (int i = kiwis.size() - 1; i >= 0; i--) {
				if (namCap.atSameLocation(kiwis.get(i))) {
					namCap.eatFruit(kiwis.get(i));
					kiwiCount = 1;
					map.set((kiwis.get(i).getY() - 15) / 30, (kiwis.get(i).getX() - 15) / 30, '.'); // remove from map
					kiwis.remove(i); // remove from arraylist
					map.removeKiwiLoc(i);
					if (kiwis.size() == 0) {
						map = new Map("map/test"+mapNum+".txt");
					}
					break;
				}
			}
									
			if (namCap.hasEatenKiwi()) {
				kiwiCount++;
			}
			
			if (kiwiCount % 150 == 0) {
				namCap.setKiwiFalse();
			}

			// strawberry
			if (strawberryButton) {
				ArrayList<Point> strawberryLoc = map.getStrawberryLoc();
				ArrayList<Strawberry> strawberrys = new ArrayList<Strawberry>();
				for (Point p : strawberryLoc) {
					Strawberry sb = new Strawberry(strawberry, p.x * 30 + 15, p.y * 30 + 16);
					sb.draw(this);
					strawberrys.add(sb);
				}

				for (int j = 0; j < strawberrys.size(); j++) {
					if (player.atSameLocationFruit(strawberrys.get(j))) {
						player.eatFruit(strawberrys.get(j));
						map.set((strawberrys.get(j).getY() - 15) / 30, (strawberrys.get(j).getX() - 15) / 30, '.'); // remove
																													// from
																													// map
						strawberrys.remove(j); // remove from arraylist
						map.removeStrawberryLoc(j);
					}
				}
			}

			// namcap
			if (namCapCount % 8 == 0) {
				namCap.act(map, player);
				//System.out.println(namCap.getX()+","+namCap.getY()+":"+map.get(namCap.getY()/30,namCap.getX()/30)+" "+namCap.isFree(namCap.getY(),namCap.getX(),map,player)+","+namCap.isFree2(namCap.getY(),namCap.getX(),map,player));
				if (player.atSameLocation(namCap) && !namCap.hasEatenKiwi()) {
					player.increaseScore(100);
					player.reset();
					tempDir = -1;
					namCap.reset();
				} else if (player.atSameLocation(namCap) && namCap.hasEatenKiwi()) {
					player.loseLife();
					tempDir = -1;
					namCap.reset();
				}
			}
			namCap.draw(this);

			if (playerCount % 8 == 0) {
				if (player.canMove(tempDir)) {
					player.setDirection(tempDir);
				}
				player.move();
				if (player.atSameLocation(namCap) && !namCap.hasEatenKiwi()) {
					player.increaseScore(100);
					player.reset();
					tempDir = -1;
					namCap.reset();
				} else if (player.atSameLocation(namCap) && namCap.hasEatenKiwi()) {
					player.loseLife();
					tempDir = -1;
					namCap.reset();
				}
			}
			player.draw(this);

			// lives on bottom left
			if (player.getLives() >= 2) {
				if(choiceGhost)
				{
					push();
					tint(Integer.valueOf(input.substring(0, 2), 16), Integer.valueOf(input.substring(2, 4), 16), Integer.valueOf(input.substring(4, 6), 16));

				}
				image(life1, width / 22, height - height / 10, width / 30, width / 30);
				if(choiceGhost)
				{
					pop();
					image(eyes, width / 22, height - height / 10, width / 30, width / 30);
					
				}
				if (player.getLives() == 3) {
					if(choiceGhost)
					{
						push();
						tint(Integer.valueOf(input.substring(0, 2), 16), Integer.valueOf(input.substring(2, 4), 16), Integer.valueOf(input.substring(4, 6), 16));

					}
					image(life2, (width / 22) * 2, height - height / 10, width / 30, width / 30);
					if(choiceGhost)
					{
						pop();
						image(eyes, (width / 22) * 2, height - height / 10, width / 30, width / 30);
					}
						
				}
			}

			if (player.getLives() == 0) {
				endScreen = true;
				playScreen = false;
			}

		} else if (endScreen) {
			background(0);
			textSize(50);
			textAlign(CENTER);
			fill(255, 255, 0);

			if (player.getHighScore() < player.getScore()) {
				player.setHighScore(player.getScore());
			}

			text("GAME OVER", 400, 100);

			// selected ghost with tear
			if(choiceGhost)
			{
				push();
				tint(Integer.valueOf(input.substring(0, 2), 16), Integer.valueOf(input.substring(2, 4), 16), Integer.valueOf(input.substring(4, 6), 16));
			}
			image(ghost, width / 4, height / 2, width / 3, height / 2);
			if(choiceGhost)
			{
				pop();
				image(eyes, width / 4, height / 2, width / 3, height / 2);
			}
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
	 * Moves the player based on the keys pressed, using the up, down, left, right
	 * arrow keys.
	 */
	public void keyPressed() {
		if (keyCode == KeyEvent.VK_UP) {
			tempDir = 90;
		} else if (keyCode == KeyEvent.VK_DOWN) {
			tempDir = 270;
		} else if (keyCode == KeyEvent.VK_LEFT) {
			tempDir = 180;
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			tempDir = 0;
		} else if (keyCode == BACKSPACE) {
		    if (input.length() > 0) {
		      input = input.substring(0, input.length()-1);
		    }
		} else if (keyCode == DELETE) {
		    input = "";
		} else if (keyCode != SHIFT && keyCode != CONTROL && keyCode != ALT && keyCode != ENTER) {
		    input = input + key;
		}
	}

	/**
	 * Changes screens according to what button the player clicks.
	 */
	public void mousePressed() {
		if (startScreen) {
			// play button
			if (mouseX > width / 2 - width / 5 && mouseX < width / 2 + width / 5
					&& mouseY > height - height / 8 - height / 10 && mouseY < height - height / 8 + height / 10) {
				startScreen = false;
				playScreen = true;
			}

			// right arrow
			if (mouseX > (width / 5 + width / 6) - width / 10 && mouseX < (width / 5 + width / 6 - width / 20)
					&& mouseY > height / 2 - height / 30 && mouseY < height / 2 + height / 30) {
				if (whichGhost.equals("blinky")) {
					whichGhost = "pinky";
					ghost = loadImage("img/pinky.png");
				} else if (whichGhost.equals("pinky")) {
					whichGhost = "inky";
					ghost = loadImage("img/inky.png");
				} else if (whichGhost.equals("inky")) {
					whichGhost = "clyde";
					ghost = loadImage("img/clyde.png");
				} else if (whichGhost.equals("clyde")) {
					choiceGhost = true;
					whichGhost = "enter hex";
					ghost = loadImage("img/enter hex.png");
				} else if (whichGhost.equals("enter hex")) {
					choiceGhost = false;
					whichGhost = "blinky";
					ghost = loadImage("img/blinky.png");
				}
			}
			
			if(mouseX>width/2+width/8-width/50 && mouseX<width/2+width/8+width/50 && mouseY>height/2-height/35 && mouseY<height/2+height/35) {
				mapNum++;
				if(mapNum>3) {
					mapNum=1;
					mapImg = loadImage("img/map1.png");
				}
				else if(mapNum == 2)
				{
					mapImg = loadImage("img/map2.png");
				}
				else
				{
					mapImg = loadImage("img/map3.png");
				}
			}
			
			//image(strawberry, width - width / 3 + width / 20, height / 2 - height / 25, width / 30, height / 20);

			System.out.println("x: " + mouseX + ", y: " + mouseY);
			
			// checkboxes for fruit
			if (mouseX >= width / (float) 1.43
					&& mouseX <= width / (float) 1.36
					&& mouseY >= height / (float) 2.37
					&& mouseY <= height / (float) 2.12) {
				if (strawberryButton) {
					strawberryButton = false;
				} else {
					strawberryButton = true;
				}
			}

		} else if (endScreen) {
			endScreen = false;

			// home button
			if (mouseX > width / 2 - width / 20 - width / 5 && mouseX < width / 2 - width / 20 + width / 5
					&& mouseY > height / 2 && mouseY < height / 2 + height / 5) {
				startScreen = true;
			}

			if (mouseX > width / 2 && mouseX < width / 2 + width / 5 + width / 5 && mouseY > height / 2
					&& mouseY < height / 2 + height / 5) {
				player.setScore(0);
				playScreen = true;
				player.resetLives();
				player.reset();
				tempDir = -1;
				namCap.reset();
				map = new Map("map/test"+mapNum+".txt");
				kiwis.clear();
			}
		}
	}
}
