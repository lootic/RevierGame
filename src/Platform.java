import java.awt.Image;
import java.util.ArrayList;


public class Platform implements Collidable, Drawable{

	private int x;
	private int y;
	private ArrayList<Region> collisionBoxes = new ArrayList<Region>();
	private int friction = 700;
	
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
	public void addCollisionBox(Region r) {
		collisionBoxes.add(r);
	}

	@Override
	public Region[] getCollisionBoxes() {
		return (Region[]) collisionBoxes.toArray(new Region[collisionBoxes.size()]);
	}
	
	public void onCollision(Collidable c) {
		//in general this is implemented empty, unless we want a springboard or
		//a damage effect
	}

	public int getFriction() {
		return friction ;
	}

}
