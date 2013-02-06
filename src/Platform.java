import java.awt.Image;
import java.util.ArrayList;

public class Platform implements Collidable, Positioned, Drawable {

	private int x;
	private int y;
	private ArrayList<Region<Platform>> collisionBoxes = new ArrayList<Region<Platform>>();
	private int friction = 20;

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public Image getSprite() {
		return null;
	}

	@Override
	public void setSprite(Image image) {
	}

	@Override
	public void addCollisionBox(Region<Platform> r) {
		r.setOwner(this);
		r.setCollisionAction(Region.FRICTION); 
		collisionBoxes.add(r);
	}

	@Override 
	public Iterable<Region<Platform>> getCollisionBoxes() {
		return collisionBoxes;
	}

	public int getFriction() {
		return friction;
	}

	@Override
	public int getPrevY() {
		return y;
	}

	@Override
	public int getPrevX() {
		return x;
	}

}
