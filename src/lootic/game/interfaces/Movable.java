package lootic.game.interfaces;

public interface Movable extends Positioned, Collidable {

	public void moveX(float amount);

	public void moveY(float amount);
	
	public float getHorizontalSpeed();

	public float getVerticalSpeed();

	public float getHorizontalAcceleration();

	public float getVerticalAcceleration();

	public void setHorizontalSpeed(float f);

	public void setHorizontalAcceleration(float acceleration);

	public void setVerticalSpeed(float speed);

	public void setVerticalAcceleration(float acceleration);
}