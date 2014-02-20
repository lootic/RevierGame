package lootic.game.controllers;

import java.util.HashMap;

import lootic.game.entities.KeyboardBrain;
import lootic.game.models.Body;
import lootic.game.models.Region;
import lootic.game.models.Terrain;


/**
 * This class keeps track of all the rooms the game is using with multition,
 * load in the nested class Room is a Director-like function that populates the 
 * world.
 * 
 * @author lootic
 *
 */
public class Rooms {
	private static HashMap<Integer, Room> rooms = new HashMap<Integer, Room>();
	public static final Room TEST = new Room() {

		@Override
		public void load(World world) {
			Body player = new Body();
			Body creature = new Body();
			Terrain terrain = new Terrain();
			Terrain water = new Terrain();
			KeyboardBrain brain = new KeyboardBrain();
			
			brain.setControlled(player);

			player.moveX(40);
			
			Region r1 = new Region(10, 10, 20, 20);
			Region r6 = new Region(10, 10, 20, 20);

			Region r2 = new Region(600, 100, 2000, 2000);
			Region r3 = new Region(0, 480, 2000, 200);
			Region r4 = new Region(-5, 80, 20, 2000);
			Region r5 = new Region(400, 300, 50, 20);
			Region r7 = new Region(250, 300, 200, 200);

			Physics.SOLID.addAffector(terrain);
			Physics.SOLID.addAffected(player);
			Physics.MOVING.addAffected(player);
			Physics.GLOBAL_GRAVITY.addAffected(player);
			
			terrain.addCollisionBox(r2);
			terrain.addCollisionBox(r3);
			terrain.addCollisionBox(r4);
			terrain.addCollisionBox(r5);
			
			water.addCollisionBox(r7);
			
			creature.addCollisionBox(r6);
			player.addCollisionBox(r1);
			
			world.registerPhysicsRule(Physics.MOVING);
			world.registerPhysicsRule(Physics.GLOBAL_GRAVITY);
			world.registerPhysicsRule(Physics.SOLID);
			world.registerDrawable(player);
			world.registerDrawable(terrain);
			world.registerBrain(brain);
		}
		
	};
	
	public static void loadInstance(int roomNumber, World world) {
		rooms.get(roomNumber).load(world);
	}
	
	public static void addInstance(int roomNumber, Room room) {
		rooms.put(roomNumber, room);
	}
	
	public interface Room {
		public void load(World world);
	}
}
