import java.awt.Image;
import java.util.ArrayList;

public class Entity implements Drawable, Movable, Weighing, Collidable {
	protected short up;
	protected short down;
	protected short left;
	protected short right;

	protected int movementSpeed = 2500; // thousands of a pixel
	protected float movementSpeedMultiplier = 1000;
	private int decX;
	private int decY;
	private int prevX;
	private int prevY;
	private int x;
	private int y;
	private Image sprite; //maybe some AnimationModel class
	private int weight = 500; // in hgs?
	private int maxFallSpeed = 10000;
	private int fallSpeed;
	private short maxAccendFrames = 10;
	private short currentAccendFrames;

	private ArrayList<Region> collisionBoxes = new ArrayList<Region>();

	protected void accend() {
		if (maxAccendFrames > currentAccendFrames) {
			++currentAccendFrames;
			this.fallSpeed = -20000;
		}
	}

	@Override
	public void addCollisionBox(Region r) {
		r.setOwner(this);
		collisionBoxes.add(r);
	}

	@Override
	public CollisionType collides(Collidable c) {
		for (Region r1 : collisionBoxes) {
			for (Region r2 : c.getCollisionBoxes()) {
				CollisionType ct= r1.intersects(r2);
				if (ct != CollisionType.NONE) {
					return ct;
				}
			}
		}
		return CollisionType.NONE;
	}

	public void collisionEvent(CollisionType ct) {
		
		System.out.println(ct.toString());
		
		if(ct == CollisionType.TOP) {
			moveBackY();
			land();
		} else if (ct == CollisionType.LEFT) {
			moveBackX();
			slide();
		} else if (ct == CollisionType.RIGHT) {
			moveBackX();
			slide();
		}
	}

	@Override
	public Region[] getCollisionBoxes() {
		return (Region[]) collisionBoxes.toArray(new Region[collisionBoxes
				.size()]);
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
	
	protected void land() {
		currentAccendFrames = 0;
	}
	
	public void moveBack() {
		x = prevX;
		y = prevY;
	}

	protected void moveBackX() {
		x = prevX;
	}
	
	protected void moveBackY() {
		y = prevY;
	}

	@Override
	public void onCollision(Collidable c) {
		System.out.println("Collision! [x=" + x + ", y=" + y + ", prevX="
				+ prevX + ", prevY=" + getPrevY() + "]");
		moveBackY();
		currentAccendFrames = 0;
	}

	@Override
	public void setSprite(Image image) {
		this.sprite = image;
	}

	protected void slide() {
		fallSpeed = 2000;
	}

	@Override
	public void updatePosition() {
		if (up == 1) {
			accend();
		}
		decX += ((right - left) * movementSpeed * movementSpeedMultiplier) / 1000;
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
}
