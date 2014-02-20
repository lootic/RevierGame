package lootic.game.interfaces;

import lootic.game.models.Region;

public interface Collidable extends Positioned {
	public void addCollisionBox(Region region);

	public Iterable<Region> getCollisionBoxes();
}
