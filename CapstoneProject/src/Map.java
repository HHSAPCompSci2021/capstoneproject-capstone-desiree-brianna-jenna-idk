import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;

/** 
 * draws the map
 * 
 * @author desiree
 *
 */
public class Map {

	protected char[][] grid;
	private ArrayList<Point> kiwis = new ArrayList<Point>();
	
	/**
	 * Construct an empty 2D array with some default dimensions.
	 */
	public Map() {
		grid = new char[20][30]; //y, x
	}
	
	/**
	 * draws a map from a text file
	 * @param filename textfile to be gridded
	 */
	public Map(String filename) {
		grid = new char[20][30];
		this.readData(filename, grid);
	}

	/**
	 * (Graphical UI)
	 * Draws the grid on a PApplet.
	 * The specific way the grid is depicted is up to the coder.
	 * 
	 * @param marker The PApplet used for drawing.
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 */
	public void draw(PApplet marker, float x, float y, float width, float height) {
		marker.fill(255);
		float rw = 30;
		float rh = 30;
		
		marker.noStroke();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {				
				float rx = x + j * rw;
				float ry = y + i * rw;
				
				char a = grid[i][j];
				
				if (a == '#') 
				{ 
					marker.fill(0);
				} 
				
				else if (a == '.') 
				{
					marker.fill(255);
				}
				else if (a == '*') {
					marker.fill(255, 0, 0);
				}
				
				else if(a == 'k')
				{
					kiwis.add(new Point((int)rx, ((int)ry)));
					
				}
				
				marker.rect(rx,  ry,  rw,  rh);
			}
		}
	}
	
	/**
	 * reads the textfile to print in the grid
	 * @param filename name of the textfile to be printed
	 * @param gameDatathe grid that the game will be printed on
	 */
	public void readData (String filename, char[][] gameData) {
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
						for(int i = 0; i < line.length(); i++)
							if (count < gameData.length && i < gameData[count].length)
								gameData[count][i] = line.charAt(i);

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
	 * checks if the next location is valid
	 * @param x x value of next location
	 * @param y y value of next location
	 * @return if the next location is valid path or not
	 */
	public boolean isValidLocation(int x, int y) {
		return grid[y/30][x/30]=='.';
	}

	public char get(int i, int j)
	{
		return grid[i][j];
	}
	
	public void set(int i, int j, char c)
	{
		grid[i][j] = c;
	}
	
	public int getLength()
	{
		return grid.length;
	}
	
	public int getRowLength(int x)
	{
		return grid[x].length;
	}
	
	public ArrayList<Point> getKiwis()
	{
		return kiwis;
	}
} 
