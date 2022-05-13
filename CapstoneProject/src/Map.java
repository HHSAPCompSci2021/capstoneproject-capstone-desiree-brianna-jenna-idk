import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
	
	/**
	 * Construct an empty 2D array with some default dimensions.
	 */
	public Map() {
		grid = new char[31][28]; //y, x
	}
	
	public Map(String filename) {
		grid = new char[31][28];
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
		float rw = width/grid[0].length * 2;
		float rh = height/grid.length * 2;
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				// black space for stats
				if(i == 0 || j == 0 || i == grid.length - 1 || j == grid[0].length - 1)
				{
					grid[i][j] = '#';
				}
				
				// actual map space
				else
				{
					grid[i][j] = '.';
				}
				
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
				
				marker.rect(rx,  ry,  rw,  rh);
			}
		}
	}
	
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

} 
