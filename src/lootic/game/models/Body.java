package lootic.game.models;

import lootic.game.interfaces.Movable;

public class Body extends Terrain implements Movable {
	protected float maxVerticalSpeed = 5f;
	protected float verticalSpeed;
	protected float verticalAcceleration = 0f;
	protected float maxHorizontalSpeed = 5f;
	protected float horizontalSpeed;
	protected float horizontalAcceleration = 0f;

	@Override
	public float getVerticalSpeed() {
		return verticalSpeed;
	}

	@Override
	public float getHorizontalSpeed() {
		return horizontalSpeed;
	}

	@Override
	public void moveX(float amount) {
		x += amount;
	}

	@Override
	public void moveY(float amount) {
		y += amount;
	}

	@Override
	public void setVerticalSpeed(float speed) {
		if(speed > maxVerticalSpeed){
			this.verticalSpeed = maxVerticalSpeed;
		} else if(speed < -maxVerticalSpeed) {
			this.verticalSpeed = -maxVerticalSpeed;
		} else {
			this.verticalSpeed = speed;
		}
	}

	@Override
	public void setHorizontalSpeed(float speed) {
		if(speed > maxHorizontalSpeed){
			this.horizontalSpeed = maxHorizontalSpeed;
		} else if(speed < -maxHorizontalSpeed) {
			this.horizontalSpeed = -maxHorizontalSpeed;
		} else {
			this.horizontalSpeed = speed;
		}
	}

	@Override
	public void setHorizontalAcceleration(float acceleration) {
		horizontalAcceleration = acceleration;
	}

	@Override
	public void setVerticalAcceleration(float acceleration) {
		verticalAcceleration = acceleration;
	}

	@Override
	public float getHorizontalAcceleration() {
		return horizontalAcceleration;
	}

	@Override
	public float getVerticalAcceleration() {
		return verticalAcceleration;
	}
}
