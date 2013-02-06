import java.awt.Image;
import java.util.ArrayList;

public class Creature implements Drawable, Movable, Weighing, Collidable {
	protected short up;
	protected short down;
	protected short left;
	protected short right;

	protected int maxMovementSpeed = 5000; // thousands of a pixel
	protected int movementSpeed;
	protected int movementSpeedAcceleration = 500;
	protected int decX;
	protected int decY;
	protected int prevX;
	protected int prevY;
	protected int x;
	protected int y;
	protected Image sprite; //maybe some AnimationModel class
	protected int weight = 50; // in hgs?
	protected int maxFallSpeed = 10000;
	protected int fallSpeed;

	private ArrayList<Region> collisionBoxes = new ArrayList<Region>();
	
	@Override
	public void addCollisionBox(Region r) {
		r.setOwner(this);
		collisionBoxes.add(r);
	}

	@Override
	public Iterable<Region> getCollisionBoxes() {
		return  collisionBoxes;
	}

	public int getPrevX() {
		return prevX;
	}

	public int getPrevY() {
		return prevY;
	}

	@Override
	public Image getSprite() {
		return sprite;
	}

	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
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
	
	public void moveBack() {
		x = prevX;
		y = prevY;
	}

	protected void moveX(int amount) {
		x += amount;
	}
	
	protected void moveY(int amount) {
		y += amount;
	}
	
	public void applyFriction(int amount) {
		if(movementSpeed < 0) {
			movementSpeed += amount;
			if(movementSpeed > 0) {
				movementSpeed = 0;
			}
		} else {
			movementSpeed -= amount;
			if(movementSpeed < 0) {
				movementSpeed = 0;
			}
		}
	}

	@Override
	public void setSprite(Image image) {
		this.sprite = image;
	}

	@Override
	public void updatePosition() {
		movementSpeed += (right - left)*movementSpeedAcceleration;
		if(Math.abs(movementSpeed) > maxMovementSpeed)
			movementSpeed = (right - left)*maxMovementSpeed;
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

	protected void groundCollision() {
		fallSpeed = 0;
	}
	
	protected void leftCollision() {
		movementSpeed = 100;
	}
	
	protected void rightCollision() {
		movementSpeed = -100;
	}


	public void roofCollision() {
		fallSpeed = 0;
	}
}
