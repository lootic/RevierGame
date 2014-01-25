package lootic.game.controllers;

import java.util.ArrayList;

import lootic.game.interfaces.Hurtable;
import lootic.game.interfaces.Intelligent;
import lootic.game.interfaces.Looping;
import lootic.game.interfaces.Percieveble;
import lootic.game.models.Region;

/**
 * Controller class for creatures so that they can make decisions and get damaged
 * and stuff. It also contains basic rules for vision and other stuff that 
 * affects what a creature might do.
 * @author lootic
 *
 */
public class Life implements Looping {
	
	private boolean isPaused;
	private ArrayList<Percieveble> percievebles = new ArrayList<Percieveble>();
	private ArrayList<Intelligent> intelligents = new ArrayList<Intelligent>();
	
	public void nextIteration() {
		//create new perception for all intelligents from percievebles
	}
	
	public boolean isPaused() {
		return isPaused;
	}
	
	public void setPaused(boolean paused) {
		this.isPaused = paused;
	}
	
	public void registerHurtable(Hurtable hurtable){
		
	}
	
	public void registerHitbox(Region hitbox) {
	}
}
