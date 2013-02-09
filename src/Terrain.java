import java.util.ArrayList;

public abstract class Terrain extends Decoration implements Collidable {
	private ArrayList<Region> collisionBoxes = new ArrayList<Region>();
	
	@Override
	public void addCollisionBox(Region r) {
		r.setOwner(this);
		collisionBoxes.add(r);
	}

	@Override 
	public Iterable<Region> getCollisionBoxes() {
		return collisionBoxes;
	}

	@Override
	public abstract void onCollision(Movable movable);
}
