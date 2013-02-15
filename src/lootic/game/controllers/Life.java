package lootic.game.controllers;

import lootic.game.interfaces.Looping;

/**
 * Controller class for creatures so that they can make decisions and get damaged
 * and stuff. It also contains basic rules for vision and other stuff that 
 * affects what a creature might do.
 * @author lootic
 *
 */
public class Life implements Looping{
	
	private boolean isPaused;
	
	public void nextIteration() {
		
	}
	
	public boolean isPaused() {
		return isPaused;
	}
	
	public void setPaused(boolean paused) {
		this.isPaused = paused;
	}
}
