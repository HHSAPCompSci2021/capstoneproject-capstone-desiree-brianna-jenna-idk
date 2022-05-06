/**
 * 
 * naM-caP is the main antagonist of the game, whose goal is to eat kiwis to gain the ability to hunt the player down.
 * 
 * @author Jenna Wang
 * 
 */
public class NamCap {
	
	private boolean hasKiwi;
	
	/**
	 * Constructs the character naM-caP.
	 */
	public NamCap()
	{
		hasKiwi = false;
	}
	
	/**
	 * If naM-caP eats a kiwi, for the next 5 seconds, naM-caP will try to hunt the player.
	 * Otherwise, naM-caP will try and travel to the nearest kiwi.
	 */
	public void act()
	{
		if(!hasKiwi)
		{
			// find the nearest kiwi
			// go to kiwi
		}
		
		else
		{
			// find shortest distance to player
			// chase the player
		}
	}
	
	/**
	 * If naM-caP hits a wall, naM-caP will turn to a direction they are able to move in.
	 */
	public void turn()
	{
		// if hit a wall, turn 90 degrees
	}

}
