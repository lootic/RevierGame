package lootic.game.models;

import lootic.game.interfaces.Positioned;

public class CircleRegion extends Region{
	public CircleRegion(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public CircleRegion(int x, int y, int width, int height, Positioned owner) {
		super(x, y, width, height, owner);
	}

	public boolean intersects(SquareRegion otherRegion){
		return false;
	}
	
	public boolean intersects(CircleRegion otherRegion){
		return false;
	}
	
	public int distance(SquareRegion otherRegion) {
		return 0;
	}
	
	public int distance(CircleRegion otherRegion) {
		return 0;
	}
}
