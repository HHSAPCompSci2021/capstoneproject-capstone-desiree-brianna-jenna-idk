import processing.core.PApplet;
import processing.core.PImage;

/**
 * Player is the protagonist of the game that the user controls. The objective
 * of the Player is to catch naM-caP, unless naM-caP is in its superpower phase,
 * and earn as many points as possible
 * 
 * @author Brianna, Desiree, Jenna
 */
public class Player {
	private PImage img, eyes;
	private int score, xi, yi, direction, step, lives, highscore, xgrid, ygrid; // x and y value of player on grid
	private boolean hasTint;
	private String tint;
	private Map map;

	/**
	 * Constructs a Player and initiates Player type to "" and the score to 0. X and
	 * Y should be the center of grid.
	 * 
	 * @param img  The Player's image.
	 * @param eyes An image of the Player's eyes.
	 * @param x    X-value of the Player.
	 * @param y    Y-value of the Player.
	 * @param m    The Map the Player is on.
	 */
	public Player(PImage img, PImage eyes, int x, int y, Map m) {
		this.img = img;
		xgrid = x;
		xi = x;
		ygrid = y;
		yi = y;
		score = 0;
		direction = -1;
		step = 30;
		map = m;
		lives = 3;
		hasTint = false;
		this.eyes = eyes;
	}

	/**
	 * Draws the player.
	 * 
	 * @param g The PApplet used to draw.
	 */
	public void draw(PApplet g) {
		if (hasTint) {
			g.push();
			g.tint(Integer.valueOf(tint.substring(0, 2), 16), Integer.valueOf(tint.substring(2, 4), 16),
					Integer.valueOf(tint.substring(4, 6), 16));
		}
		g.image(img, xgrid, ygrid, 30, 30);
		if (hasTint) {
			g.pop();
			g.image(eyes, xgrid, ygrid, 30, 30);
		}
	}

	/**
	 * Moves the Player one space forward in the direction it is facing
	 */
	public void move() {
		int newX = xgrid;
		int newY = ygrid;

		if (direction == 0) {
			newX += step;
		} else if (direction == 90) {
			newY -= step;
		} else if (direction == 180) {
			newX -= step;
		} else if (direction == 270) {
			newY += step;
		}
		if (newX < 0) {
			newX += 900;
		} else if (newX >= 885) {
			newX -= 900;
		} else if (newY < 0) {
			newY += 600;
		} else if (newY >= 585) {
			newY -= 600;
		}
		if (map.isValidLocation(newX, newY)) {
			xgrid = newX;
			ygrid = newY;
		}
	}

	/**
	 * Returns if the player can move one space forward in the direction.
	 * 
	 * @param dir The new direction of the Player.
	 * @return true if the player can move one space forward in the direction, false
	 *         if not.
	 */
	public boolean canMove(int dir) {
		int newX = xgrid;
		int newY = ygrid;

		if (dir == 0) {
			newX += step;
		} else if (dir == 90) {
			newY -= step;
		} else if (dir == 180) {
			newX -= step;
		} else if (dir == 270) {
			newY += step;
		}

		if (newX < 0) {
			newX += 900;
		} else if (newX >= 885) {
			newX -= 900;
		} else if (newY < 0) {
			newY += 600;
		} else if (newY >= 585) {
			newY -= 600;
		}

		return map.isValidLocation(newX, newY);
	}

	/**
	 * Sets the direction of the player.
	 * 
	 * @param dir The angle of the player's direction (0 is the +x axis).
	 */
	public void setDirection(int dir) {
		direction = dir;
	}

	/**
	 * @return The Player's direction.
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * Removes a life from the Player.
	 */
	public void loseLife() {
		lives--;
		reset();
	}

	/**
	 * Resets the Player to the starting position and direction.
	 */
	public void reset() {
		setX(xi);
		setY(yi);
		direction = -1;
	}

	/**
	 * @return The number of lives the Player has.
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * Resets the number of lives back to 3.
	 */
	public void resetLives() {
		lives = 3;
	}

	/**
	 * @return X-value of the Player's location.
	 */
	public int getX() {
		return xgrid;
	}

	/**
	 * @return Y-value of the Player's location.
	 */
	public int getY() {
		return ygrid;
	}

	/**
	 * Sets the X-value of the Player's location.
	 * 
	 * @param xval The new X-value.
	 */
	public void setX(int xval) {
		xgrid = xval;
	}

	/**
	 * Sets the Y-value of the Player's location.
	 * 
	 * @param yval The new Y-value.
	 */
	public void setY(int yval) {
		ygrid = yval;
	}

	/**
	 * Determines if the Player and naM-caP are at the same spot.
	 * 
	 * @param n naM-caP.
	 * @return true if at same location, false if not.
	 */
	public boolean atSameLocation(NamCap n) {
		if (n.getX() / 30 == xgrid / 30 && n.getY() / 30 == ygrid / 30) {
			return true;
		}
		return false;
	}

	/**
	 * @param f The Fruit.
	 * @return If the Player is at the same location as the given Fruit.
	 */
	public boolean atSameLocationFruit(Fruit f) {
		return (f.getX() / 30 == xgrid / 30 && f.getY() / 30 == ygrid / 30);
	}

	/**
	 * Eats the Fruit and gains a special ability depending on the Fruit.
	 * 
	 * @param f The Fruit eaten.
	 */
	public void eatFruit(Fruit f) {
		if (f instanceof Strawberry) {
			increaseScore(50);
		}
	}

	/**
	 * Increases the Player's score by a certain amount.
	 * 
	 * @param points The amount of points to add to the score.
	 */
	public void increaseScore(int points) {
		score += points;
	}

	/**
	 * @return The score of the Player.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @return The Player's high score.
	 */
	public int getHighScore() {
		return highscore;
	}

	/**
	 * Sets the high score of the Player.
	 * 
	 * @param hs The new high score.
	 */
	public void setHighScore(int hs) {
		highscore = hs;
	}

	/**
	 * Sets the score of the Player.
	 * 
	 * @param score The new score.
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Sets the tint coloring of the Player for choiceGhost.
	 * 
	 * @param tint A string of the HEX code for the ghost's color.
	 */
	public void setTint(String tint) {
		this.tint = tint;
		hasTint = true;
	}
}
