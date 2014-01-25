package lootic.game.controllers;

import javax.swing.JFrame;

import lootic.game.models.Camera;
import lootic.game.models.Creature;
import lootic.game.models.Decoration;
import lootic.game.models.Player;
import lootic.game.models.Terrain;
import lootic.game.models.Thing;

public class World {
	private Physics physics;
	private Life life;
	private Canvas canvas;
	
	public World() {
		physics = new Physics();
		
		life = new Life();

		Camera camera = new Camera(); 
		canvas = new Canvas();
		canvas.setCamera(camera);
		canvas.setDebug(true);
		
		JFrame frame = new JFrame();
		frame.add(canvas);
		frame.addKeyListener(new Input());
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public World(Physics physics, Life life, Canvas canvas) {
		this.physics = physics;
		this.life = life;
		this.canvas = canvas;
	}
	
	public void addPlayer(Player player) {
		physics.registerWeighing(player);
		physics.registerMovable(player);
		canvas.registerDrawable(player);
	}
	
	public void addCreature(Creature creature) {
		physics.registerWeighing(creature);
		physics.registerMovable(creature);
		canvas.registerDrawable(creature);
	}
	
	public void addDecoration(Decoration decoration) {
		canvas.registerDrawable(decoration);
	}
	
	public void addTerrain(Terrain terrain) {
		physics.registerCollidable(terrain);
		canvas.registerDrawable(terrain);
	}
	
	public void addThing(Thing thing) {
		physics.registerThing(thing);
		canvas.registerDrawable(thing);
	}
	
	public void run() {
		physics.setPaused(false);

		long i = 0;
		while (true) {
			try {
				Thread.sleep(17-i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i = System.currentTimeMillis();
			canvas.nextIteration();
			physics.nextIteration();
			i -= System.currentTimeMillis();
			
		}
	}
	
	public void pause() {
		physics.setPaused(true);
		
	}
}
