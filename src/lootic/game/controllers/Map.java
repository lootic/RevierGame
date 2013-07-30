package lootic.game.controllers;


/**
 * A class that controls the loading and unloading of objects in our different
 * controllers. Is top-level controller.
 * 
 * @author lootic
 *
 */
public class Map {
	
	private Physics physics;
	private Life life;
	private Canvas canvas;
	
	public Map(Physics physics, Life life, Canvas canvas) {
		this.physics = physics;
		this.life = life;
		this.canvas = canvas;
	}
	
	public void clear() {
		/**
		 * physics.clear();
		 * life.clear();
		 * canvas.clear();
		 */
	}

	public void load(String fileURL) {
		
	}
	
	public void save(String fileURL) {
		
	}
}
