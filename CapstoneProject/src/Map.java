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

	/** Add a field to represent the grid. This time, make it a 2D array of characters.
	 *  It is OK to make this field protected so it can be directly accessed by subclasses.  **/
	
	protected boolean[][] grid;
	
	/**
	 * Construct an empty 2D array with some default dimensions.
	 */
	public Map() {
		grid = new boolean[20][20];
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
		float rw = width/grid[0].length;
		float rh = height/grid.length;
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = false;
				float rx = x + j * rw;
				float ry = y + i * rw;
				
				boolean a = grid[i][j];
				if (a) { //path light green
					marker.fill(209, 243, 197);
				} else if (!a) { //path light red
					marker.fill(244, 194, 194);
				}
				marker.rect(rx,  ry,  rw,  rh);
			}
		}
	}
	
	/**
	 * (Graphical UI)
	 * Determines which element of the grid matches with a particular pixel coordinate.
	 * This supports interaction with the grid using mouse clicks in the window.
	 * 
	 * @param p A Point object containing a graphical pixel coordinate.
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 * @return A Point object representing a coordinate within the grid, or null if the pixel coordinate
	 * falls completely outside of the grid.
	 */
	public Point clickToIndex(Point p, float x, float y, float width, float height) {
		
		int clickX = p.x;
		int clickY = p.y;
		
		int ry = (int) (grid[0].length * clickX / width);
		int rx = (int) (grid.length * clickY/ height);
		
		Point result = new Point(rx, ry);
		
		return result;
	}
}
