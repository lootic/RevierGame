package lootic.game.controllers;

import java.io.Serializable;


/**
 * A class that controls the loading and unloading of objects in our different
 * controllers. Is top-level controller.
 * 
 * @author lootic
 *
 */
public class Map implements Serializable{
	
	private Physics physics;
	private Life life;
	private Canvas canvas;
	
	public Map(Physics physics, Life life, Canvas canvas) {
		this.physics = physics;
		this.life = life;
		this.canvas = canvas;
	}

	public void load(String fileURL) {
		
	}
	
	public void unload() {
		
	}
	
	public void save(String fileURL) {
		
	}
	
	public void run() {
		
	}
	
	public void pause() {
		
	}
}
