package lootic.game.models;

import lootic.game.interfaces.Positioned;

public class TriangleRegion extends Region{

	public TriangleRegion(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public TriangleRegion(int x, int y, int width, int height, Positioned owner) {
		super(x, y, width, height, owner);
	}

	public boolean intersects(SquareRegion otherRegion){
		return false;
	}
	
	public boolean intersects(CircleRegion otherRegion){
		return false;
	}
	
	public boolean intersects(TriangleRegion otherRegion){
		return false;
	}
	
	public int distance(SquareRegion otherRegion) {
		return 0;
	}
	
	public int distance(CircleRegion otherRegion) {
		return 0;
	}
	
	public int distance(TriangleRegion otherRegion) {
		return 0;
	}
}
