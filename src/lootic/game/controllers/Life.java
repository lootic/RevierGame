package lootic.game.controllers;

import java.util.ArrayList;

import lootic.game.interfaces.Brain;
import lootic.game.interfaces.Looping;

public class Life implements Looping {
	
	private boolean isPaused = false;
	private ArrayList<Brain> brains = new ArrayList<Brain>();
	
	public void registerBrain(Brain brain) {
		brains.add(brain);
	}
	
	public void onTick() {
		for(Brain brain : brains) {
			brain.makeDecision();
		}
	}

	@Override
	public boolean isPaused() {
		return isPaused;
	}

	@Override
	public void setPaused(boolean paused) {	
		this.isPaused = paused;
	}
}
