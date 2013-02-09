
public class Thing extends Terrain implements Weighing, Movable{
	protected int maxFallSpeed = 10000;
	protected int fallSpeed;
	protected int weight = 50;
	protected int maxMovementSpeed = 5000;
	protected int movementSpeed;
	protected int movementSpeedAcceleration = 500;
	protected int prevX;
	protected int prevY;
	protected int decX;
	protected int decY;
	
	@Override
	public int getPrevX() {
		return prevX;
	}
	
	@Override
	public int getPrevY() {
		return prevY;
	}
	
	@Override
	public int getFallSpeed() {
		return fallSpeed;
	}

	@Override
	public int getMovementSpeed() {
		return 0;
	}

	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public void increaseFallSpeed(int increase) {
		if (fallSpeed == maxFallSpeed) {
			return;
		} else if (fallSpeed + increase > maxFallSpeed) {
			fallSpeed = maxFallSpeed;
		} else {
			fallSpeed += increase;
		}
	}
	
	@Override
	public void moveX(int amount) {
		x += amount;
	}

	@Override
	public void moveY(int amount) {
		y += amount;
	}

	@Override
	public void onCollision(Collidable collidable) {
	}

	@Override
	public void setFallSpeed(int speed) {
		this.fallSpeed = speed;
	}

	@Override
	public void setMovementSpeed(int speed) {
	}

	@Override
	public void updatePosition() {
		movementSpeed += (right - left) * movementSpeedAcceleration;
		if (Math.abs(movementSpeed) > maxMovementSpeed)
			movementSpeed = (right - left) * maxMovementSpeed;
		decX += movementSpeed;
		decY += fallSpeed;
		if (Math.abs(decX) >= 1000 || Math.abs(decY) >= 1000) {
			prevX = x;
			prevY = y;
			x += decX / 1000;
			y += decY / 1000;
			decX %= 1000;
			decY %= 1000;
		}
	}

	@Override
	public void onCollision(Movable movable) {
	}
}
