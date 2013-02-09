public interface Movable extends Positioned, Collidable{
	public void updatePosition();
	public void moveX(int amount);
	public void moveY(int amount);
	public void setMovementSpeed(int speed);
	public void setFallSpeed(int speed);
	public int getMovementSpeed();
	public int getFallSpeed();
	public void onCollision(Collidable collidable);
}