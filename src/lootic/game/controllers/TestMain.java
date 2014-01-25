package lootic.game.controllers;

import lootic.game.interfaces.Rooms;

public class TestMain {
	public static void main(String[] args) { 
		World world = new World();
		Rooms.TEST.load(world);
		world.run();
	}
}
