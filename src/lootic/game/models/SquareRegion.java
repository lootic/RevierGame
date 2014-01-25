package lootic.game.models;

import lootic.game.interfaces.Positioned;

public class SquareRegion extends Region{
	public SquareRegion(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public SquareRegion(int x, int y, int width, int height, Positioned owner) {
		super(x, y, width, height, owner);
	}

	public boolean intersects(SquareRegion otherRegion){
		return false;
	}
	
	public boolean intersects(CircleRegion otherRegion){
		return false;
	}
	
	public int distance(SquareRegion otherRegion) {
		int xDistance = Math.abs(this.getCenterX() - otherRegion.getCenterX())
				- (this.width + otherRegion.width);
		int yDistance = Math.abs(this.getCenterY() - otherRegion.getCenterY())
				- (this.height + otherRegion.height);

		if (xDistance < 0 && yDistance < 0) {
			return -1;
		}
		return xDistance > yDistance ? xDistance : yDistance;
	}
	
	public int distance(CircleRegion otherRegion) {
		int xDistance = Math.abs(this.getCenterX() - otherRegion.getCenterX())
				- (this.width + otherRegion.width);
		int yDistance = Math.abs(this.getCenterY() - otherRegion.getCenterY())
				- (this.height + otherRegion.height);
		
		double angle = Math.atan2(yDistance, xDistance);
		double thisLength = Math.sqrt(Math.pow((this.getWidth()*Math.cos(angle)), 2) + Math.pow((this.getHeight()*Math.sin(angle)), 2));
		double otherLength = Math.sqrt(Math.pow((otherRegion.getWidth()*Math.cos(angle)), 2) + Math.pow((otherRegion.getHeight()*Math.sin(angle)), 2));
		
		double distance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2))-thisLength-otherLength;
		
		return (int)Math.round(distance);
	}
}
