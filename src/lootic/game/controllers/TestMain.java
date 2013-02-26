package lootic.game.controllers;

import javax.swing.JFrame;

import lootic.game.interfaces.CollisionRules;
import lootic.game.models.Creature;
import lootic.game.models.Player;
import lootic.game.models.Region;
import lootic.game.models.Terrain;

public class TestMain {
	public static void main(String[] args) { 
		Canvas canvas = new Canvas();
		Physics physics = new Physics();
		Player player = new Player();
		Creature creature = new Creature();
		Terrain terrain = new Terrain();
		Terrain water = new Terrain();
		JFrame frame = new JFrame();

		frame.add(canvas);
		frame.addKeyListener(new Input());
		player.moveX(40);

		canvas.registerDrawable(terrain);
		canvas.registerDrawable(water);
		canvas.registerDrawable(player);
		canvas.setDrawingRegions(true);

		Region r1 = new Region(10, 10, 20, 20);
		Region r6 = new Region(10, 10, 20, 20);

		Region r2 = new Region(600, 100, 2000, 2000);
		Region r3 = new Region(0, 480, 2000, 200);
		Region r4 = new Region(-5, 80, 20, 2000);
		Region r5 = new Region(400, 300, 50, 20);
		Region r7 = new Region(250, 300, 200, 200);

		creature.addCollisionBox(r6);
		player.addCollisionBox(r1);
		
		player.addCollisionRule(CollisionRules.JUMP_PLAYER);
		player.addCollisionRule(CollisionRules.WALL_COLLISION_PLAYER);

		terrain.addCollisionBox(r2);
		terrain.addCollisionBox(r3);
		terrain.addCollisionBox(r4);
		terrain.addCollisionBox(r5);
		terrain.addCollisionRule(CollisionRules.SOLID);
		terrain.addCollisionRule(CollisionRules.FALLSPEED_RESET);
		terrain.addCollisionRule(CollisionRules.FRICTION_NORMAL);
		
		water.addCollisionBox(r7);
		water.addCollisionRule(CollisionRules.FRICTION_ICE);
		water.addCollisionRule(CollisionRules.WATER);
		
		physics.registerCreature(player);
		physics.registerCreature(creature);
		physics.registerCollidable(terrain);
		physics.registerCollidable(water);
		
		canvas.registerDrawable(creature);

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
