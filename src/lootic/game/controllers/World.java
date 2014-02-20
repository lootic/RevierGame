package lootic.game.controllers;

import javax.swing.JFrame;

import lootic.game.entities.KeyboardBrain;
import lootic.game.interfaces.Collidable;
import lootic.game.interfaces.Drawable;
import lootic.game.interfaces.CollisionRule;
import lootic.game.interfaces.SimpleRule;
import lootic.game.models.Camera;

public class World {
	private Physics physics;
	private Canvas canvas;
	private JFrame frame;
	private Life life;
	private Camera camera;

	public World() {
		physics = new Physics();

		life = new Life();

		camera = new Camera();
		canvas = new Canvas();
		canvas.setCamera(camera);
		canvas.setDebug(true);

		frame = new JFrame();
		frame.add(canvas);
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public World(Physics physics, Canvas canvas) {
		this.physics = physics;
		this.canvas = canvas;
	}

	public void registerPhysicsRule(
			CollisionRule<? extends Collidable, ? extends Collidable> physicsRule) {
		physics.registerPhysicsRule(physicsRule);
	}
	
	public void registerPhysicsRule(
			SimpleRule<?> physicsRule) {
		physics.registerPhysicsRule(physicsRule);
	}

	public void registerBrain(KeyboardBrain brain) {
		life.registerBrain(brain);
		frame.addKeyListener(brain);
	}

	public void registerDrawable(Drawable drawable) {
		canvas.registerDrawable(drawable);
	}

	public void run() {
		physics.setPaused(false);

		long i = 0;
		while (true) {
			try {
				Thread.sleep(17 - i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i = System.currentTimeMillis();
			canvas.onTick();
			life.onTick();
			physics.onTick();
			i -= System.currentTimeMillis();

		}
	}

	public void pause() {
		physics.setPaused(true);

	}
}
