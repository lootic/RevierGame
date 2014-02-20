package lootic.game.controllers;


public class TestMain {
	public static void main(String[] args) {
		World world = new World();
		Rooms.TEST.load(world);
		world.run();
	}
}
