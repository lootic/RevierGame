package lootic.game.interfaces;

import java.util.HashMap;

import lootic.game.controllers.Input;
import lootic.game.controllers.PlayerController;
import lootic.game.controllers.World;
import lootic.game.models.Creature;
import lootic.game.models.Player;
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
	public static final Room TEST = new Room(){

		@Override
		public void load(World world) {
			Player player = new Player();
			Creature creature = new Creature();
			Terrain terrain = new Terrain();
			Terrain water = new Terrain();
			
			player.moveX(40);
			
			Input.setControllable(new PlayerController(player));
			
			Region r1 = new Region(10, 10, 20, 20);
			Region r6 = new Region(10, 10, 20, 20);

			Region r2 = new Region(600, 100, 2000, 2000);
			Region r3 = new Region(0, 480, 2000, 200);
			Region r4 = new Region(-5, 80, 20, 2000);
			Region r5 = new Region(400, 300, 50, 20);
			Region r7 = new Region(250, 300, 200, 200);

			
			terrain.addCollisionBox(r2);
			terrain.addCollisionBox(r3);
			terrain.addCollisionBox(r4);
			terrain.addCollisionBox(r5);
			terrain.addCollisionRule(CollisionRules.SOLID);
			terrain.addCollisionRule(CollisionRules.FALLSPEED_RESET);
			terrain.addCollisionRule(CollisionRules.FRICTION_ICE);
			terrain.addCollisionRule(CollisionRules.FLOOR_COLLISION);
			terrain.addCollisionRule(CollisionRules.WALL_COLLISION_LEFT);
			terrain.addCollisionRule(CollisionRules.WALL_COLLISION_RIGHT);
			
			water.addCollisionBox(r7);
			water.addCollisionRule(CollisionRules.FRICTION_ICE);
			water.addCollisionRule(CollisionRules.WATER);
			

			creature.addCollisionBox(r6);
			player.addCollisionBox(r1);
			
			player.addCollisionRule(CollisionRules.WALL_COLLISION_PLAYER);
			
			world.addCreature(creature);
			world.addPlayer(player);
			world.addTerrain(terrain);
			world.addTerrain(water);
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
