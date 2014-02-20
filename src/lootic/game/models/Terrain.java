package lootic.game.models;

import java.util.ArrayList;

import lootic.game.interfaces.Collidable;

public class Terrain extends Decoration implements Collidable {
	private ArrayList<Region> collisionBoxes = new ArrayList<Region>();

	@Override
	public void addCollisionBox(Region r1) {
		r1.setOwner(this);
		collisionBoxes.add(r1);
	}

	@Override
	public Iterable<Region> getCollisionBoxes() {
		return collisionBoxes;
	}
}
