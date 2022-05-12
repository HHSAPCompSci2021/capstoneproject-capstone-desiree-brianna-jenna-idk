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
		grid = new char[15][21];
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
				
				marker.rect(rx,  ry,  rw,  rh);
			}
		}
	}

} 
