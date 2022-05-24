import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet;

/**
 * Draws the Map of the game.
 * 
 * @author Brianna, Desiree, Jenna
 *
 */
public class Map {

	private char[][] grid;
	private int kCount, sCount, kEaten, sEaten;
	private ArrayList<Point> kiwiLoc, strawberryLoc;

	/**
	 * Construct a 2D character array with some default dimensions.
	 */
	public Map() {
		grid = new char[20][30]; // y, x
		kiwiLoc = new ArrayList<Point>();
		strawberryLoc = new ArrayList<Point>();
	}

	/**
	 * Draws a map from a text file.
	 * 
	 * @param filename Text file to be drawn.
	 */
	public Map(String filename) {
		grid = new char[20][30];
		this.readData(filename, grid);
		kiwiLoc = new ArrayList<Point>();
		strawberryLoc = new ArrayList<Point>();
	}

	/**
	 * (Graphical UI) Draws the grid on a PApplet.
	 * 
	 * @param marker The PApplet used for drawing.
	 * @param x      The x pixel coordinate of the upper left corner of the grid
	 *               drawing.
	 * @param y      The y pixel coordinate of the upper left corner of the grid
	 *               drawing.
	 * @param width  The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 */
	public void draw(PApplet marker, float x, float y, float width, float height) {
		marker.fill(255);
		marker.noStroke();
		float rw = 30;
		float rh = 30;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				float rx = x + j * rw;
				float ry = y + i * rw;

				char a = grid[i][j];

				if (a == '#') {
					marker.fill(0);
					marker.rect(rx, ry, rw, rh);
				} else if (a == '.') {
					marker.fill(255);
					marker.rect(rx, ry, rw, rh);
				} else if (a == '*') {
					marker.fill(255, 0, 0);
					marker.rect(rx, ry, rw, rh);
				} else if (a == 'k') {
					marker.fill(255);
					marker.rect(rx, ry, rw, rh);
					if (kiwiLoc.size() < kCount - kEaten) {
						kiwiLoc.add(new Point(i, j));
					}
				} else if (a == 's') {
					marker.fill(255);
					marker.rect(rx, ry, rw, rh);
					if (strawberryLoc.size() < sCount - sEaten) {
						strawberryLoc.add(new Point(i, j));
					}
				}
			}
		}
	}

	/**
	 * Reads the text file to print in the grid.
	 * 
	 * @param filename Name of the text file to be read.
	 * @param gameData The grid that the game will be printed on.
	 */
	public void readData(String filename, char[][] gameData) {
		File dataFile = new File(filename);

		if (dataFile.exists()) {
			int count = 0;

			FileReader reader = null;
			Scanner in = null;
			try {
				reader = new FileReader(dataFile);
				in = new Scanner(reader);

				while (in.hasNext()) {
					String line = in.nextLine();
					for (int i = 0; i < line.length(); i++) {
						if (count < gameData.length && i < gameData[count].length)
							gameData[count][i] = line.charAt(i);
						if (line.charAt(i) == 'k') {
							kCount++;
						} else if (line.charAt(i) == 's') {
							sCount++;
						}
					}
					count++;
				}
			} catch (IOException ex) {
				throw new IllegalArgumentException("Data file " + filename + " cannot be read.");
			} finally {
				if (in != null)
					in.close();
			}
		} else {
			throw new IllegalArgumentException("Data file " + filename + " does not exist.");
		}
	}

	/**
	 * Checks if the location entered is valid.
	 * 
	 * @param x X-value of next location.
	 * @param y Y-value of next location.
	 * @return true if the next location is valid path or not.
	 */
	public boolean isValidLocation(int x, int y) {
		return grid[y / 30][x / 30] == '.' || grid[y / 30][x / 30] == 'k' || grid[y / 30][x / 30] == 's';
	}

	/**
	 * Gets the grid's character.
	 * 
	 * @param i Y-value.
	 * @param j X-value.
	 * @return character in the grid's location.
	 */
	public char get(int i, int j) {
		return grid[i][j];
	}

	/**
	 * Sets the character of the grid to a new character.
	 * 
	 * @param i Y-value.
	 * @param j X-value.
	 * @param c The new character.
	 */
	public void set(int i, int j, char c) {
		grid[i][j] = c;
	}

	/**
	 * @return The height of the grid.
	 */
	public int getLength() {
		return grid.length;
	}

	/**
	 * @param x The column number.
	 * @return The row length of the column.
	 */
	public int getRowLength(int x) {
		return grid[x].length;
	}

	/**
	 * @return An ArrayList of the location of the Kiwis on the map.
	 */
	public ArrayList<Point> getKiwiLoc() {
		return kiwiLoc;
	}

	/**
	 * Removes a location from the kiwiLoc ArrayList.
	 * 
	 * @param i The index of the Point.
	 */
	public void removeKiwiLoc(int i) {
		kiwiLoc.remove(i);
		kEaten++;
	}

	/**
	 * @return The Player's starting position.
	 */
	public Point getPlayerInitialLocation() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '*') {
					return new Point(j, i);
				}
			}
		}
		return new Point(1, 1);
	}

	public String toString() {
		String output = "";

		for (int i = 0; i < grid.length; i++) { // rows, x-axis
			for (int j = 0; j < grid[i].length; j++) { // columns, y-axis
				if (grid[i][j] == '#') {
					output += "#";
				} else if (grid[i][j] == '*') {
					output += "*";
				} else if (grid[i][j] == '.') {
					output += ".";
				} else if (grid[i][j] == 'k') {
					output += "k";
				}
			}
			output += "\n";
		}
		return output;
	}

	/**
	 * @return An ArrayList of the locations of the strawberries on the map.
	 */
	public ArrayList<Point> getStrawberryLoc() {
		return strawberryLoc;
	}

	/**
	 * Removes a location from the strawberryLoc ArrayList.
	 * 
	 * @param i The index of the Point.
	 */
	public void removeStrawberryLoc(int i) {
		strawberryLoc.remove(i);
		sEaten++;
	}
}
