package lootic.game.controllers;

import javax.swing.JFrame;

import lootic.game.Creature;
import lootic.game.Player;
import lootic.game.Region;
import lootic.game.Terrain;
import lootic.game.interfaces.CollisionRules;

public class Main {
	public static void main(String[] args) { 
		Canvas canvas = new Canvas();
		Physics physics = new Physics();
		Player player = new Player();
		Creature creature = new Creature();
		Terrain terrain = new Terrain();
		JFrame frame = new JFrame();

		frame.add(canvas);
		frame.addKeyListener(new Input());
		player.moveX(40);
//		creature.moveX(200);

		canvas.registerDrawable(terrain);
		canvas.registerDrawable(player);
		canvas.setDrawingRegions(true);

		Region r1 = new Region(10, 10, 20, 20);
		Region r6 = new Region(10, 10, 20, 20);

		Region r2 = new Region(600, 100, 2000, 2000);
		Region r3 = new Region(0, 480, 2000, 200);
		Region r4 = new Region(-5, 80, 20, 2000);
		Region r5 = new Region(400, 270, 50, 20);

		creature.addCollisionBox(r6);
		player.addCollisionBox(r1);

		terrain.addCollisionBox(r2);
		terrain.addCollisionBox(r3);
		terrain.addCollisionBox(r4);
		terrain.addCollisionBox(r5);
		terrain.addCollisionRule(CollisionRules.SOLID);
		terrain.addCollisionRule(CollisionRules.FRICTION_ICE);
		
		physics.registerCreature(player);
		physics.registerCreature(creature);
		physics.registerCollidable(terrain);
		
		canvas.registerDrawable(creature);

		System.out.println(System.getProperty("user.dir"));

		frame.setSize(1500, 1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			Thread.sleep(17);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		physics.setPaused(false);

		while (true) {
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			canvas.nextIteration();
			physics.nextIteration();
			Input.reset();
		}

	}
}
