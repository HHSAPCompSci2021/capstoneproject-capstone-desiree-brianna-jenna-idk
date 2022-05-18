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
	private int kCount, sCount;
	private ArrayList<Kiwi> kiwis = new ArrayList<Kiwi>();
	private ArrayList<Strawberry> strawberrys = new ArrayList<Strawberry>();
	
	/**
	 * Construct a 2D character array with some default dimensions.
	 */
	public Map() {
		grid = new char[20][30]; // y, x
	}
	
	/**
	 * Draws a map from a text file.
	 * @param filename Text file to be drawn.
	 */
	public Map(String filename) {
		grid = new char[20][30];
		this.readData(filename, grid);
	}

	/**
	 * (Graphical UI)
	 * Draws the grid on a PApplet.
	 * 
	 * @param marker The PApplet used for drawing.
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
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
				
				if (a == '#') 
				{ 
					marker.fill(0);
					marker.rect(rx,  ry,  rw,  rh);
				} 
				
				else if (a == '.') 
				{
					marker.fill(255);
					marker.rect(rx,  ry,  rw,  rh);
				}
				else if (a == '*') {
					marker.fill(255, 0, 0);
					marker.rect(rx,  ry,  rw,  rh);
				}
				
				else if(a == 'k')
				{
					//marker.fill(255);
					marker.rect(rx,  ry,  rw,  rh);
					Kiwi kiwi=new Kiwi(marker.loadImage("img/kiwi.png"),(int)ry+15,(int)rx+16);
					kiwi.draw(marker);
					if(kiwis.size() < kCount)
					{
						kiwis.add(kiwi);
					}
				}
				
				else if(a == 's')
				{
					marker.rect(rx, ry, rw, rh);
					Strawberry strawberry = new Strawberry(marker.loadImage("img/strawberry.png"), (int) ry + 15, (int) rx + 16);
					strawberry.draw(marker);
					if (strawberrys.size() < sCount)
					{
						strawberrys.add(strawberry);
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
						for(int i = 0; i < line.length(); i++)
						{
							if (count < gameData.length && i < gameData[count].length)
								gameData[count][i] = line.charAt(i);
							if(line.charAt(i) == 'k')
							{
								kCount++;
							} else if (line.charAt(i) == 's')
							{
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
		return grid[y/30][x/30]=='.' || grid[y/30][x/30]=='k' || grid[y/30][x/30] == 's';
	}

	/**
	 * Gets the grid's character.
	 * 
	 * @param i Y-value.
	 * @param j X-value.
	 * @return character in the grid's location.
	 */
	public char get(int i, int j)
	{
		return grid[i][j];
	}
	
	/** 
	 * Sets the character of the grid to a new character.
	 * @param i Y-value.
	 * @param j X-value.
	 * @param c The new character.
	 */
	public void set(int i, int j, char c)
	{
		grid[i][j] = c;
	}
	
	/**
	 * @return The height of the grid.
	 */
	public int getLength()
	{
		return grid.length;
	}
	
	/**
	 * @param x The column number.
	 * @return The row length of the column.
	 */
	public int getRowLength(int x)
	{
		return grid[x].length;
	}
	
	/**
	 * @return An ArrayList of the kiwis on the map.
	 */
	public ArrayList<Kiwi> getKiwis()
	{
		return kiwis;
	}
	
	/**
	 * @return An arraylist of the strawberrys on the map
	 */
	public ArrayList<Strawberry> getStrawberrys()
	{
		return strawberrys;
	}
} 
